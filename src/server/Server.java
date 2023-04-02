package server;

import server.assets.Users;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Server {

    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) throws Exception {
        System.out.println("The Chat Server is running. Listening to port " + SERVER_PORT);
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        try {
            while (true) {
                new ClientConnectionHandler(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

}
