package Lesson1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rapid Blaze on 16.09.2015.
 */
public class Exercise7 {
    public static void main(String[] args) {
        String s = "T5est";
        Pattern pattern1 = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])*.*$");
        Matcher m1 = pattern1.matcher(s);
        if(m1.matches()){
            Pattern pattern2 = Pattern.compile("\\w*((\\w)\\2\\2)+\\w*", Pattern.CASE_INSENSITIVE);
            Matcher m2 = pattern2.matcher(s);
            if(!m2.matches()){
                System.out.println("Nice");
            } else {
                System.out.println(!m2.matches());
            }
        } else {
            System.out.println(m1.matches());
        }
    }
}
