package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnectionHandler {
    public static int SERVER_PORT = 12345;
    public static String SERVER_ADDRESS = "localhost";
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;


    //Establish a connection to the Server
    public ClientConnectionHandler() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (socket == null) {
            System.err.println("Socket is null");
        } else {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                String message = in.readLine();//connected to server (msg from server)
//                System.out.println(message);
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    //Send new text to the serverThread so it can add it to the chat database
    public void write(String text) {
        out.println(text);
    }


}
