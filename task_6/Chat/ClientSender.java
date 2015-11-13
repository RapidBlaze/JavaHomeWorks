package Lesson7.Chat;

import Lesson7.libs.ReversedLinesFileReader;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Rapid Blaze on 03.11.2015.
 */
public class ClientSender extends Thread {
    private Vector<String> mMessageQueue = new Vector<String>();

    private ClientsList mClientsList;
    private Client mClient;
    private PrintWriter mOut;
    private File history = new File("D:/", "history.txt");

    public ClientSender(Client aClient, ClientsList aClientsList)
            throws IOException {
        mClient = aClient;
        mClientsList = aClientsList;
        Socket socket = aClient.mSocket;
        mOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        sendHistory();
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

    private synchronized void sendHistory() {
        ArrayList<String> historyMessages = new ArrayList<>();
        try {
            ReversedLinesFileReader in = new ReversedLinesFileReader(history);
            try {
                String s = in.readLine();
                if (s != null) {
                    String message = "[History]";
                    sendMessage(message);
                    historyMessages.add(s);
                    for (int i = 0; i < 4; i++) {
                        try {
                            message = in.readLine();
                            if(message != null){
                                historyMessages.add(message);
                            }
                        } catch (NullPointerException e) {
                            break;
                        }
                    }
                    for (int i = historyMessages.size() - 1; i >= 0; i--){
                        sendMessage(historyMessages.get(i));
                    }
                    sendMessage("[New]");
                }
            } catch (NullPointerException ignored) {
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
