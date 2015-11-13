package Lesson7.Chat;

import java.io.*;
import java.net.Socket;

/**
 * Created by Rapid Blaze on 03.11.2015.
 */

public class ClientListener extends Thread {
    private ClientsList mClientsList;
    private Client mClient;
    private BufferedReader mIn;
    private File history = new File("D:/", "history.txt");

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
                historyWriter(message);
            }
        } catch (IOException ignored) {
        }

        mClient.mClientSender.interrupt();
        mClientsList.deleteClient(mClient);
    }

    public void historyWriter(String message) {
        try {
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(history, true)));
            out.println(message);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
