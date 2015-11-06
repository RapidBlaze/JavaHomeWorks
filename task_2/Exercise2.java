package Lesson2;

import java.io.*;

/**
 * Created by Rapid Blaze on 30.09.2015.
 */
public class Exercise2 {
    public static final String input = "C:/input.txt";
    public static final String output = "C:/output.txt";

    public static void main(String[] args) throws IOException {
        FileInputStream filein = new FileInputStream(input);
        BufferedInputStream in = new BufferedInputStream(filein);
        FileOutputStream fileout = new FileOutputStream(output);
        BufferedOutputStream out = new BufferedOutputStream(fileout);

        in.skip(5);
        int ch = in.read();
        int i = 0;

        while (ch != -1) {
            i++;
            if (i == 5) {
                in.mark(ch);
            }
            out.write(ch);
            ch = in.read();
        }

        in.reset();
        ch = in.read();

        while (ch != -1) {
            out.write(ch);
            ch = in.read();
        }

        in.close();
        out.close();
        filein.close();
        fileout.close();
    }
}
