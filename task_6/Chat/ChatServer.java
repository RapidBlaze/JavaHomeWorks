package Lesson7.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Rapid Blaze on 03.11.2015.
 */

public class ChatServer extends Thread {

    // TODO: store remembered clients here

    public static void main(String... args) {
        int portNumber = 7777;
        try (
                ServerSocket serverSocket =
                        new ServerSocket(portNumber);

        ) {
            System.out.println("Started Chat Server");

            ClientsList mClientsList = new ClientsList();
            mClientsList.start();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress().getHostName());
                Client client = new Client();
                client.mSocket = socket;
                ClientListener clientListener =
                        new ClientListener(client, mClientsList);
                ClientSender clientSender =
                        new ClientSender(client, mClientsList);
                client.mClientListener = clientListener;
                client.mClientSender = clientSender;
                clientListener.start();
                clientSender.start();
                mClientsList.addClient(client);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
