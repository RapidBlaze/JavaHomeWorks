package Lesson7.Sum;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Rapid Blaze on 02.11.2015.
 */
public class SumClient implements Runnable {

    public static final int PORT = 7001;
    public static final String HOST = "127.0.0.1";

    public SumClient() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        try {
            Socket socket = new Socket(HOST, PORT);
            Scanner scanner = new Scanner(System.in);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                out.write(scanner.nextLine() + "\n");
                out.flush();
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SumClient();
    }
}
