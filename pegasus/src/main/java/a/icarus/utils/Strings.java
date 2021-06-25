package a.icarus.utils;

import android.text.TextUtils;

import java.util.Locale;

@SuppressWarnings("unused")
public class Strings {

    public static String concat(String... arr) {
        String result = "";
        for (String str : arr) {
            result = result.concat(str);
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
