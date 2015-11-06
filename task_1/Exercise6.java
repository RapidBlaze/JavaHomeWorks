package Lesson1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rapid Blaze on 14.09.2015.
 */
public class Exercise6 {
    public static void main(String[] args) {
        String s = "lololollo";
        Pattern pattern = Pattern.compile(".*(\\w)\\1.*");
        Matcher matcher = pattern.matcher(s);
        System.out.println(matcher.matches());
    }

}
