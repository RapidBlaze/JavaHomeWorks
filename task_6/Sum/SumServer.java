package Lesson7.Sum;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Rapid Blaze on 02.11.2015.
 */
public class SumServer {
    public static final int LISTENING_PORT = 7001;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(LISTENING_PORT);
            System.out.println("Server starting on port: " + LISTENING_PORT);
            while (true) {
                Socket client = serverSocket.accept();
                new SumDispatcher(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
