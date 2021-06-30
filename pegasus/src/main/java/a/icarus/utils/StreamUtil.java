package a.icarus.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public class StreamUtil {
    public static void copy(InputStream is, OutputStream os) {
        copy(is, os, new byte[1024]);
    }

    public static void copy(InputStream is, OutputStream os, byte[] buffer) {
        int len;
        try {
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Recycle.close(is);
            Recycle.close(os);
        }
    }

    public static String toString(InputStream is) {
        return toString(is, new byte[1024]);
    }

    public static String toString(InputStream is, byte[] buffer) {
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            Recycle.close(is);
        }
        return bos.toString();
    }
}
