package a.icarus.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    public static String valueOf(Object object) {
        Map<String, String> map = new HashMap<>();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                String type = field.getType().getSimpleName();
                Object o = field.get(object);
                StringBuilder value = new StringBuilder();
                if (null == o) {
                    value.append("null");
                } else if (type.contains("List")) {
                    List<Object> list = Cast.cast(o);
                    value.append("[");
                    if (list.size() == 0) {
                        value.append("]");
                    }
                    for (int i = 0; i < list.size(); i++) {
                        value.append(Strings.valueOf(list.get(i)));
                        if (i + 1 < list.size()) {
                            value.append(", ");
                        } else {
                            value.append("]");
                        }
                    }
                } else {
                    String str = o.toString();
                    if (!str.contains("@") || str.startsWith("java")) {
                        value.append(str);
                    } else {
                        value.append(Strings.valueOf(o));
                    }
                }
                map.put(name, value.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Strings.concat(object.getClass().getSimpleName(), ":", map.toString());
    }
}
