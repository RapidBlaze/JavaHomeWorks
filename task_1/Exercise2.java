package Lesson1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rapid Blaze on 11.09.2015.
 */
public class Exercise2 {
    public static void main(String[] args) {
        String s = "2000/12/01 12:10";
        Pattern pattern = Pattern.compile("((1\\d{3})|(20(0\\d|1[0-2])))/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|30)\\s([01]\\d|2[0-4]):([0-5]\\d)");
        Matcher m = pattern.matcher(s);
        System.out.println(m.matches());

        s = "1005/05/29 22:59";
        m = pattern.matcher(s);
        System.out.println(m.matches());
        s = "2013/12/01 12:10";
        m = pattern.matcher(s);
        System.out.println(m.matches());
        s = "2000/12/01 12:10";
        m = pattern.matcher(s);
        System.out.println(m.matches());
    }
}
