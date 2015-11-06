package Lesson7.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Created by Rapid Blaze on 03.11.2015.
 */

public class ChatClient extends JFrame {

    private JButton sendButton;
    private JTextField inputText;
    private JTextField loginText;
    private JTextArea chat;
    private BufferedReader in = null;
    private PrintWriter out = null;

    /**
     * Send button handler
     */
    private final ActionListener sendAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            out.write(loginText.getText() + ": " + inputText.getText() + "\n");
            out.flush();
            inputText.setText(""); //reset text on send
        }
    };

    public void init() {
        initComponents();
        connect();
        startServerListener();
    }

    private void startServerListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Read messages from the server and print them
                    String messageFromServer = ""; // some message from server
                    while ((messageFromServer = in.readLine()) != null) {
                        System.out.println(messageFromServer);
                        addMessageFromServer(messageFromServer);
                    }
                } catch (IOException ioe) {
                    System.err.println("Connection to server broken.");
                    ioe.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Adds String message to GUI component
     *
     * @param message
     */
    private void addMessageFromServer(String message) {
        chat.append(message + "\n");
    }

    private void initComponents() {
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(300, 200));
        container.setLayout(null);
        setContentPane(container);

        JLabel inputLabel = new JLabel("Введите текст:");
        inputLabel.setSize(150, 20);
        inputLabel.setLocation(5, 105);
        container.add(inputLabel);

        sendButton = new JButton("Send");
        sendButton.setSize(80, 30);
        sendButton.setLocation(105, 125);
        container.add(sendButton);

        sendButton.addActionListener(sendAction);

        inputText = new JTextField();
        inputText.setSize(100, 20);
        inputText.setBorder(BorderFactory.createLineBorder(Color.black));
        inputText.setLocation(5, 128);
        inputText.addActionListener(sendAction);
        container.add(inputText);

        loginText = new JTextField("Логин");
        loginText.setSize(100, 20);
        loginText.setBorder(BorderFactory.createLineBorder(Color.black));
        loginText.setLocation(5, 155);
        loginText.addActionListener(sendAction);
        container.add(loginText);

        chat = new JTextArea();
        chat.setSize(200, 200);
        chat.setEditable(false);
        chat.setFont(new Font("Arial", Font.BOLD, 12));
        chat.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.black));

        JScrollPane scroll = new JScrollPane(chat);
        scroll.setSize(200, 100);
        scroll.setLocation(5, 5);
        container.add(scroll);

        pack();
        setLocation(200, 150);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void connect() {
        String hostName = "localhost";
        int portNumber = 7777;

        //TODO: put connect logic here
        try {
            Socket socket = new Socket(hostName, portNumber);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Connected to server " +
                    hostName + ":" + portNumber);
        } catch (IOException ioe) {
            System.err.println("Can not establish connection to " +
                    hostName + ":" + portNumber);
            ioe.printStackTrace();
        }
    }

    public static void main(String... args) {
        ChatClient cc = new ChatClient();
        cc.init();
    }
}
