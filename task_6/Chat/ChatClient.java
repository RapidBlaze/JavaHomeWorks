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

    private JTextField inputText;
    private static JTextField loginText;
    private JTextField hostText;
    private JTextField portText;
    private JTextArea chat;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private static String login = null;
    private String hostName;
    private int portNumber;

    /**
     * Send button handler
     */
    private final ActionListener sendAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (inputText.getText().length() > 0) {
                out.write(getLogin() + ": " + inputText.getText() + "\n");
                out.flush();
                inputText.setText(""); //reset text on send
            }
        }
    };

    private final ActionListener connectAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (hostText.getText().length() > 0) {
                hostName = hostText.getText();
            }
            if (portText.getText().length() > 0) {
                portNumber = Integer.valueOf(portText.getText());
            }
            connect();
            startServerListener();
        }
    };

    private String getLogin() {
        if (loginText.getText() != null) {
            login = loginText.getText();
        }
        return login;
    }

    public void init() {
        initComponents();
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
        container.setPreferredSize(new Dimension(550, 205));
        container.setLayout(null);
        setContentPane(container);

        JLabel hostLabel = new JLabel("Hostname:");
        hostLabel.setSize(150, 20);
        hostLabel.setLocation(5, 5);
        container.add(hostLabel);

        hostText = new JTextField("127.0.0.1");
        hostText.setSize(200, 20);
        hostText.setBorder(BorderFactory.createLineBorder(Color.black));
        hostText.setLocation(5, 30);
        hostText.addActionListener(connectAction);
        container.add(hostText);

        JLabel portLabel = new JLabel("Port:");
        portLabel.setSize(150, 20);
        portLabel.setLocation(5, 55);
        container.add(portLabel);

        portText = new JTextField("7777");
        portText.setSize(200, 20);
        portText.setBorder(BorderFactory.createLineBorder(Color.black));
        portText.setLocation(5, 80);
        portText.addActionListener(connectAction);
        container.add(portText);

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setSize(150, 20);
        loginLabel.setLocation(5, 105);
        container.add(loginLabel);

        loginText = new JTextField();
        loginText.setSize(200, 20);
        loginText.setBorder(BorderFactory.createLineBorder(Color.black));
        loginText.setLocation(5, 130);
        loginText.addActionListener(connectAction);
        container.add(loginText);

        JButton connectButton = new JButton("Connect");
        connectButton.setSize(100, 30);
        connectButton.setLocation(50, 165);
        container.add(connectButton);

        connectButton.addActionListener(connectAction);

        JLabel inputLabel = new JLabel("Your message:");
        inputLabel.setSize(150, 20);
        inputLabel.setLocation(255, 150);
        container.add(inputLabel);

        JButton sendButton = new JButton("Send");
        sendButton.setSize(80, 30);
        sendButton.setLocation(465, 170);
        container.add(sendButton);

        sendButton.addActionListener(sendAction);

        inputText = new JTextField();
        inputText.setSize(200, 20);
        inputText.setBorder(BorderFactory.createLineBorder(Color.black));
        inputText.setLocation(255, 175);
        inputText.addActionListener(sendAction);
        container.add(inputText);

        chat = new JTextArea();
        chat.setSize(200, 200);
        chat.setEditable(false);
        chat.setFont(new Font("Arial", Font.BOLD, 12));
        chat.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.black));

        JScrollPane scroll = new JScrollPane(chat);
        scroll.setSize(290, 137);
        scroll.setLocation(255, 5);
        container.add(scroll);

        pack();
        setLocation(250, 200);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void connect() {

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

    public static void main(String[] args) {
        ChatClient cc = new ChatClient();
        cc.init();
        if (args.length > 0) {
            login = args[0];
            loginText.setEnabled(false);
        }
    }
}
