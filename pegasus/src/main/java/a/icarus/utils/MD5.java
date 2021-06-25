package a.icarus.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("unused")
public class MD5 {
    public static String encode(String string, boolean upperCase) {
        String encode = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));
            encode = ConversionTool.bytes2HexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (upperCase) {
            return encode.toUpperCase();
        } else {
            return encode.toLowerCase();
        }
    }

    public static String encode(String string) {
        return encode(string, true);
    }
}
