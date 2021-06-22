package a.icarus.utils;

import java.io.File;

@SuppressWarnings("unused")
public class FileUtil {
    public static long getSize(File file) {
        long size = 0;
        if (file == null) {
            return size;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                if (children != null) {
                    for (File child : children) {
                        size += getSize(child);
                    }
                }
            } else {
                size = file.length();
            }
        }
        return size;
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        boolean success = true;
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                if (children != null) {
                    for (File child : children) {
                        success = success & delete(child);
                    }
                }
            } else {
                return file.delete();
            }
        }
        return success;
    }
}
