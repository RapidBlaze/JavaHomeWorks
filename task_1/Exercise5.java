package Lesson1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rapid Blaze on 14.09.2015.
 */
public class Exercise5 {
    public static void main(String[] args) {
        String s = "This task starts at the beginning of the day";
        Pattern pattern = Pattern.compile("(^t\\w*)|(\\st\\w*)", Pattern.CASE_INSENSITIVE);//
        Matcher m = pattern.matcher(s);
        while (m.find()) {
            System.out.println(m.start());
            System.out.println(m.end());
            String out = s.substring(m.start(), m.end());
            System.out.println(out.trim());
        }
    }
}
