package Lesson1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rapid Blaze on 11.09.2015.
 */
public class Exercise1 {
    public static void main(String[] args) {
        String s = "hello test and test";
        Pattern pattern = Pattern.compile("test");
        Matcher m = pattern.matcher(s);
        while (m.find()) {
            System.out.println(m.start());
            System.out.println(m.end());
        }

        pattern = Pattern.compile("(.*)test(.*)");
        m = pattern.matcher(s);
        System.out.println(m.matches());

    }
}
