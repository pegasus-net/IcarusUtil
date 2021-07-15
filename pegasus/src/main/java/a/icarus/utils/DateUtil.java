package a.icarus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("unused")
public class DateUtil {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String EXACT_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String FILE_FORMAT = "yyyy-MM-dd(HH-mm-ss)";

    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        return dateFormat.format(date);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    public static String current() {
        return format(new Date(), EXACT_FORMAT);
    }

    public static String format(String pattern) {
        return format(new Date(), pattern);
    }
}
