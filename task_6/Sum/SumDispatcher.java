package Lesson7.Sum;

import java.io.*;
import java.net.Socket;

/**
 * Created by Rapid Blaze on 02.11.2015.
 */
public class SumDispatcher implements Runnable{
    private Socket socket;

    public SumDispatcher(Socket client) {
        Thread thread = new Thread(this);
        this.socket = client;
        thread.start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Connect new Client" + socket.getLocalAddress());
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String inputLine = in.readLine();
                String[] strings = inputLine.split(" ");

                try {
                    if (strings.length > 2) {
                        out.write("Argument count <N>, expected 2" + "\n");
                        out.flush();
                    } else {
                        if (strings.length <= 2) {
                            int a = Integer.parseInt(strings[0]);
                            int b = Integer.parseInt(strings[1]);
                            int result = a + b;
                            out.write(String.valueOf(a) + " + " + String.valueOf(b) + " = " + String.valueOf(result) + "\n");
                            out.flush();
                        }
                    }
                } catch (Exception e) {
                    out.write("error!" + "\n");
                    out.flush();
                }
            }

        } catch (Exception ignored) {
        }
    }
}
