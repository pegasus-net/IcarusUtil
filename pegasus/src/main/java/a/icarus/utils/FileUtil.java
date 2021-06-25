package a.icarus.utils;

import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

import a.icarus.component.Icarus;

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

    public static String formatBytes(long size) {
        long kb = size / 1024;
        if (kb >= 1024L * 1024 * 1024 * 1024) {
            return StringUtil.format("%.2fPB", kb / 1024f / 1024f / 1024f / 1024f);
        } else if (kb >= 1024L * 1024 * 1024) {
            return StringUtil.format("%.2fTB", kb / 1024f / 1024f / 1024f);
        } else if (kb >= 1024 * 1024) {
            return StringUtil.format("%.2fGB", kb / 1024f / 1024f);
        } else if (kb >= 1024 * 100) {
            return StringUtil.format("%dMB", (int) (kb / 1024f + 0.5f));
        } else if (kb >= 1024) {
            return StringUtil.format("%.2fMB", kb / 1024f);
        } else if (kb >= 0) {
            return StringUtil.format("%dKB", kb);
        } else {
            return "0KB";
        }
    }

    public static Uri getUri(File file) {
        Uri uri;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getUri(file);
    }
}