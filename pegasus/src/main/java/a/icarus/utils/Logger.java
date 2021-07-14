package a.icarus.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("unused")
public class Logger {
    public static final int NONE = 0;
    public static final int VERBOSE = 1;
    public static final int DEBUG = 1 << 2;
    public static final int INFO = 1 << 3;
    public static final int WARN = 1 << 4;
    public static final int ERROR = 1 << 5;
    public static final int CRASH = 1 << 6;

    private static String TAG = "Logger:TAG -->";
    private static final String EMPTY = "empty";
    private static int level = INFO | WARN | ERROR;
    private static int type = INFO;

    public static void t(Object... arr) {
        switch (type) {
            case VERBOSE:
                v(arr);
                break;
            case DEBUG:
                d(arr);
                break;
            case INFO:
                i(arr);
                break;
            case WARN:
                w(arr);
                break;
            case ERROR:
                e(arr);
                break;
        }
    }

    public static void v(Object... arr) {
        if ((level & VERBOSE) == 0) return;
        String msg = Strings.concat(arr);
        if (TextUtils.isEmpty(msg)) {
            msg = EMPTY;
        }
        Log.v(TAG, msg);
    }

    public static void d(Object... arr) {
        if ((level & DEBUG) == 0) return;
        String msg = Strings.concat(arr);
        if (TextUtils.isEmpty(msg)) {
            msg = EMPTY;
        }
        Log.d(TAG, msg);
    }

    public static void i(Object... arr) {
        if ((level & INFO) == 0) return;
        String msg = Strings.concat(arr);
        if (TextUtils.isEmpty(msg)) {
            msg = EMPTY;
        }
        Log.i(TAG, msg);
    }

    public static void w(Object... arr) {
        if ((level & WARN) == 0) return;
        String msg = Strings.concat(arr);
        if (TextUtils.isEmpty(msg)) {
            msg = EMPTY;
        }
        Log.w(TAG, msg);
    }

    public static void e(Object... arr) {
        if ((level & ERROR) == 0) return;
        String msg = Strings.concat(arr);
        if (TextUtils.isEmpty(msg)) {
            msg = EMPTY;
        }
        Log.e(TAG, msg);
    }

    public static void save(Throwable e) {
        if ((level & CRASH) == 0) return;
        File dataDir = Icarus.getContext().getExternalCacheDir().getParentFile();
        File logDir = new File(dataDir, "logs");
        if (!logDir.exists()) {
            boolean b = logDir.mkdir();
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd%HH_mm_ss", Locale.CHINA);
        String dateString = format.format(date);
        File log = new File(logDir, Strings.concat(dateString,
                e.getClass().getSimpleName(), ".log"));
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(log);
            save(e, fos);
        } catch (Exception ignore) {
        } finally {
            Recycle.close(fos);
        }
    }

    public static void save(Throwable e, OutputStream os) {
        if ((level & CRASH) == 0) return;
        PrintStream printStream = new PrintStream(os);
        e.printStackTrace(printStream);
        Recycle.close(printStream);
        Recycle.close(os);
    }

    public static void setTAG(String TAG) {
        Logger.TAG = TAG;
    }

    public static void setType(int type) {
        Logger.type = type;
    }

    public static void addType(int type) {
        Logger.type = Logger.type | type;
    }

    public static void setLevel(int level) {
        Logger.level = level;
    }

}
