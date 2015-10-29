package Lesson1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rapid Blaze on 11.09.2015.
 */
public class Exercise3 {
    public static void main(String[] args) {
        String s = "http://example.com/";
        Pattern pattern = Pattern.compile("(http|https)://(\\w+)([.](\\w+))+(/\\w*)*");
        Matcher m = pattern.matcher(s);
        System.out.println(m.matches());

        m = pattern.matcher("example.com");
        System.out.println(m.matches());
        m = pattern.matcher("кремль.рф");
        System.out.println(m.matches());
        m = pattern.matcher("http://a.google.com/drive/");
        System.out.println(m.matches());
        m = pattern.matcher("https://drive.mail.ru/bc/");
        System.out.println(m.matches());
    }
}
