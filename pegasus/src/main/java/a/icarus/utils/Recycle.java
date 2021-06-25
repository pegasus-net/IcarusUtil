package a.icarus.utils;

import java.io.Closeable;
import java.io.IOException;

@SuppressWarnings("unused")
public class Recycle {
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
