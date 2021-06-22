package a.icarus.utils;

import java.util.Locale;

@SuppressWarnings("unused")
public class FormatUtil {
    public static String formatBytes(long size) {
        long kb = size / 1024;
        if (kb >= 1024L * 1024 * 1024 * 1024) {
            return format("%.2fPB", kb / 1024f / 1024f / 1024f / 1024f);
        } else if (kb >= 1024L * 1024 * 1024) {
            return format("%.2fTB", kb / 1024f / 1024f / 1024f);
        } else if (kb >= 1024 * 1024) {
            return format("%.2fGB", kb / 1024f / 1024f);
        } else if (kb >= 1024 * 100) {
            return format("%dMB", (int) (kb / 1024f + 0.5f));
        } else if (kb >= 1024) {
            return format("%.2fMB", kb / 1024f);
        } else if (kb >= 0) {
            return format("%dKB", kb);
        } else {
            return "0KB";
        }
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.CHINA, format, args);
    }
}
