package a.icarus.utils;

import android.text.TextUtils;

import java.util.List;

@SuppressWarnings("unused")
public class EmptyCheck {
    public static boolean isEmpty(String str, boolean ignoreCase, String... ignores) {
        if (str == null) {
            return true;
        }
        for (String ignore : ignores) {
            if (ignoreCase) {
                if (str.equalsIgnoreCase(ignore)) {
                    return true;
                }
            } else {
                if (str.equals(ignore)) {
                    return true;
                }
            }
        }
        return TextUtils.isEmpty(str.trim());
    }

    public static boolean isEmpty(String str, String... ignores) {
        return isEmpty(str, false, ignores);
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }
}
