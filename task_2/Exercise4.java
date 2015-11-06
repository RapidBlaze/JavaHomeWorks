package Lesson2;

import java.io.*;

/**
 * Created by Rapid Blaze on 01.10.2015.
 */
public class Exercise4 {
    public static final String input = "C:/input.txt";
    public static final String output = "C:/output.txt";

    public static void main(String[] args) throws IOException {
        buffered();
        fileIOS();
    }

    public static void buffered() throws IOException {
        FileInputStream filein = new FileInputStream(input);
        BufferedInputStream in = new BufferedInputStream(filein);
        FileOutputStream fileout = new FileOutputStream(output);
        BufferedOutputStream out = new BufferedOutputStream(fileout);

        long startTime = System.currentTimeMillis();
        int ch = in.read();
        while (ch != -1) {
            out.write(ch);
            ch = in.read();
        }
        in.close();
        out.close();
        filein.close();
        fileout.close();

        long time = System.currentTimeMillis() - startTime;
        System.out.println(time);
    }

    public static void fileIOS() throws IOException {
        FileInputStream in = new FileInputStream(input);
        FileOutputStream out = new FileOutputStream(output);

        long startTime = System.currentTimeMillis();
        int ch = in.read();
        while (ch != -1) {
            out.write(ch);
            ch = in.read();
        }
        in.close();
        out.close();

        long time = System.currentTimeMillis() - startTime;
        System.out.println(time);
    }

}

