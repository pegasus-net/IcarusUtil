package a.icarus.utils;

@SuppressWarnings("unused")
public class TextUtil {

    public static String concat(String... arr) {
        String result = "";
        for (String str : arr) {
            result = result.concat(str);
        }
        return result;
    }
}
