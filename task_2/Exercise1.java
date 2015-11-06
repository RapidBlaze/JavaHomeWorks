package Lesson2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Rapid Blaze on 30.09.2015.
 */
public class Exercise1 {
    public static final String input = "C:/input.txt";
    public static final String output = "C:/output.txt";

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream(input);
        FileOutputStream out = new FileOutputStream(output);
        int ch = in.read();
        while (ch != -1) {
            out.write(ch);
            ch = in.read();
        }
        in.close();
        out.close();
    }
}
