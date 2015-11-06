package Lesson7.Chat;

import java.util.Vector;

/**
 * Created by Rapid Blaze on 03.11.2015.
 */

class ClientsList extends Thread {
    private Vector<Client> mClients = new Vector<Client>();
    private Vector<String> mMessageQueue = new Vector<String>();

    public synchronized void addClient(Client client) {
        mClients.add(client);
    }

    public synchronized void dispatchMessage(String aMessage) {
        mMessageQueue.add(aMessage);
        notify();
    }

    private synchronized String getNextMessageFromQueue()
            throws InterruptedException {
        while (mMessageQueue.size() == 0)
            wait();
        String message = mMessageQueue.get(0);
        mMessageQueue.removeElementAt(0);
        return message;
    }

    public synchronized void deleteClient(Client client) {
        int clientIndex = mClients.indexOf(client);
        if (clientIndex != -1) mClients.removeElementAt(clientIndex);
    }

    private synchronized void sendMessageToAllClients(String aMessage) {
        for (Object mClient : mClients) {
            Client client = (Client) mClient;
            client.mClientSender.sendMessage(aMessage);
        }
    }

    public void run() {
        try {
            while (true) {
                String message = getNextMessageFromQueue();
                sendMessageToAllClients(message);
            }
        } catch (InterruptedException ie) {
        }
    }
}

