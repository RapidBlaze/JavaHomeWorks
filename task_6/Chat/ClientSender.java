package Lesson7.Chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by Rapid Blaze on 03.11.2015.
 */
public class ClientSender extends Thread {
    private Vector<String> mMessageQueue = new Vector<String>();

    private ClientsList mClientsList;
    private Client mClient;
    private PrintWriter mOut;

    public ClientSender(Client aClient, ClientsList aClientsList)
            throws IOException {
        mClient = aClient;
        mClientsList = aClientsList;
        Socket socket = aClient.mSocket;
        mOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public synchronized void sendMessage(String aMessage) {
        mMessageQueue.add(aMessage);
        notify();
    }

    private synchronized String getNextMessageFromQueue() throws InterruptedException {
        while (mMessageQueue.size() == 0)
            wait();
        String message = mMessageQueue.get(0);
        mMessageQueue.removeElementAt(0);
        return message;
    }

    private void sendMessageToClient(String aMessage) {
        mOut.println(aMessage);
        mOut.flush();
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                String message = getNextMessageFromQueue();
                sendMessageToClient(message);
            }
        } catch (Exception ignored) {

        }

        mClient.mClientListener.interrupt();
        mClientsList.deleteClient(mClient);
    }
}
