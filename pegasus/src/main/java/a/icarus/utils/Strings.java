package a.icarus.utils;

import android.text.TextUtils;

import java.util.Locale;

@SuppressWarnings("unused")
public class Strings {

    public static String concat(Object... arr) {
        String result = "";
        for (Object o : arr) {
            result = result.concat(String.valueOf(o));
        }
        return result;
    }

    public static boolean isEmpty(String str) {
        if (str == null) return false;
        return !TextUtils.isEmpty(str.trim());
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.CHINA, format, args);
    }

}
