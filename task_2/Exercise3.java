package Lesson2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

/**
 * Created by Rapid Blaze on 30.09.2015.
 */
public class Exercise3 {
    public static final String input = "C:/input.txt";
    public static final String input2 = "C:/input2.txt";
    public static final String output = "C:/output.txt";

    public static void main(String[] args) throws IOException {
        FileInputStream inp1 = new FileInputStream(input);
        FileInputStream inp2 = new FileInputStream(input2);
        SequenceInputStream seq = new SequenceInputStream(inp1, inp2);
        FileOutputStream out = new FileOutputStream(output);

        int ch = seq.read();
        while (ch != -1) {
            out.write(ch);
            ch = seq.read();
        }

        inp1.close();
        inp2.close();
        seq.close();
        out.close();
    }
}
