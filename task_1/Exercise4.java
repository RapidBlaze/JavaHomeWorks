package Lesson1;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rapid Blaze on 14.09.2015.
 */
public class Exercise4 {
    public static void main(String[] args) {

        String s = "This \"huge test\" is pointless Smith-Hopper test--hyphens can't long pop-";

        Pattern pattern = Pattern.compile("(\"(\\w*\\s\\w*)*\"\\s*)|(\\w*'\\w\\s*)|\\w*--|\\w+-\\w*\\s*|\\w*-\\w+\\s*|(\\w+\\s*)");
        Matcher m = pattern.matcher(s);
        String l;
        while (m.find()) {
            l = s.substring(m.start(), m.end());
            l = l.replaceAll("--","");
            l = l.replaceAll("[\"]","");
            l = l.trim();
            System.out.print(l + ", ");
        }
    }
}
