package a.icarus.utils;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.Nullable;

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

    public static void copy(File source, File target) {
        try {
            InputStream is = new FileInputStream(source);
            OutputStream os = new FileOutputStream(target);
            StreamUtil.copy(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String formatBytes(long size) {
        long kb = size / 1024;
        if (kb >= 1024L * 1024 * 1024 * 1024) {
            return Strings.format("%.2fPB", kb / 1024f / 1024f / 1024f / 1024f);
        } else if (kb >= 1024L * 1024 * 1024) {
            return Strings.format("%.2fTB", kb / 1024f / 1024f / 1024f);
        } else if (kb >= 1024 * 1024) {
            return Strings.format("%.2fGB", kb / 1024f / 1024f);
        } else if (kb >= 1024 * 100) {
            return Strings.format("%dMB", (int) (kb / 1024f + 0.5f));
        } else if (kb >= 1024) {
            return Strings.format("%.2fMB", kb / 1024f);
        } else if (kb >= 0) {
            return Strings.format("%dKB", kb);
        } else {
            return "0KB";
        }
    }

    @Nullable
    public static Uri getUri(File file) {
        Uri uri;
        if (file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProviderSupport.getUriForFile(Icarus.getContext(), FileProviderSupport.AUTHORITIES, file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    public static Uri getEmptyUri(File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException ignore) {

        }
        return getUri(file);
    }

    public static long getAvailableBytes() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        return stat.getAvailableBytes();
    }

    public static long getTotalBytes() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        return stat.getTotalBytes();
    }
}
