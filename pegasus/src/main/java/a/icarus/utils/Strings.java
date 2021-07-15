package a.icarus.utils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Strings {
    public static final String EMPTY = "";

    public static String concat(Object... arr) {
        String result = EMPTY;
        if (EmptyCheck.isEmpty(arr)) return EMPTY;
        for (Object o : arr) {
            result = result.concat(String.valueOf(o));
        }
        return result;
    }

    public static boolean isEmpty(String str) {
        return EmptyCheck.isEmpty(str);
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.CHINA, format, args);
    }

    public static Matcher regex(CharSequence input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
