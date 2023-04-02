package server;

import server.assets.Messages;
import server.assets.Types;
import server.assets.Users;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ClientConnectionHandler extends Thread {
    private String name;
    private Users user;
    private Socket SOCKET;

    private static final HashMap<String, Users> users = new HashMap<>();
    private static HashSet<ObjectOutputStream> streams = new HashSet<>();
    private static ArrayList<Users> userList = new ArrayList<>();

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientConnectionHandler(Socket socket) throws IOException {
        this.SOCKET = socket;
    }

    public void run() {
        System.out.println("Connecting to user...");
        try {
            inputStream = SOCKET.getInputStream();
            outputStream = SOCKET.getOutputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            objectOutputStream = new ObjectOutputStream(outputStream);

            Messages firstMessage = (Messages) objectInputStream.readObject();
            streams.add(objectOutputStream);
            //notify(firstMessage);
            newUserConnected();

            while (SOCKET.isConnected()) {
                Messages message = (Messages) objectInputStream.readObject();
                if (message != null) {
                    System.out.println(message.getType() + " - " + message.getName() + ": " + message.getMessage());
                    switch (message.getType()) {
                        case USER:
                            writeMessage(message);
                            break;
                        case CONNECTED:
                            newUserConnected();
                            break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Messages newUserConnected() throws IOException {
        Messages message = new Messages();
        message.setMessage("A new user has joined, Welcome!");
        message.setType(Types.CONNECTED);
        message.setName("SERVER");
        writeMessage(message);
        return message;
    }

    /**
     * Create a Message of the given type, and send to all users in list
     * @param message - String message
     * @throws IOException
     */
    private void writeMessage(Messages message) throws IOException {
        for (ObjectOutputStream stream : streams) {
            message.setUserlist(users);
            message.setUsers(userList);
            message.setUserCount(users.size());
            stream.writeObject(message);
            stream.reset();
        }
    }

    /**
     * Message that outputs when a user leaves the server
     * @return
     * @throws IOException
     */
    private Messages removeFromList() throws IOException {
        Messages message = new Messages();
        message.setMessage("left the chat.");
        message.setType(Types.DISCONNECTED);
        message.setName("SERVER");
        message.setUserlist(users);
        writeMessage(message);
        return message;
    }

    /**
     * Method to close connections once a user is removed
     */
    private synchronized void closeConnections() {
        if (name != null) {
            users.remove(name);
            System.out.println("User: " + name + " has been disconnected.");
        }
        if (user != null) {
            userList.remove(user);
        }
        if (objectOutputStream != null) {
            streams.remove(objectOutputStream);
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            removeFromList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Closed connections.");
    }

}
