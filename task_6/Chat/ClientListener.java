package Lesson7.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Rapid Blaze on 03.11.2015.
 */

public class ClientListener extends Thread {
    private ClientsList mClientsList;
    private Client mClient;
    private BufferedReader mIn;

    public ClientListener(Client aClient, ClientsList aClientsList) throws IOException {
        mClient = aClient;
        mClientsList = aClientsList;
        Socket socket = aClient.mSocket;
        mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                String message = mIn.readLine();
                if (message == null)
                    break;
                mClientsList.dispatchMessage(message);
            }
        } catch (IOException ignored) {

        }

        mClient.mClientSender.interrupt();
        mClientsList.deleteClient(mClient);
    }
}
