package a.icarus.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

@SuppressWarnings("unused")
public class Logger {
    public static final int NONE = 0;
    public static final int VERBOSE = 1;
    public static final int DEBUG = 1 << 2;
    public static final int INFO = 1 << 3;
    public static final int WARN = 1 << 4;
    public static final int ERROR = 1 << 5;

    private static String TAG = "Logger:TAG-->";
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
        Log.v(TAG, Strings.concat(arr));
    }

    public static void d(Object... arr) {
        if ((level & DEBUG) == 0) return;
        Log.d(TAG, Strings.concat(arr));
    }

    public static void i(Object... arr) {
        if ((level & INFO) == 0) return;
        Log.i(TAG, Strings.concat(arr));
    }

    public static void w(Object... arr) {
        if ((level & WARN) == 0) return;
        Log.w(TAG, Strings.concat(arr));
    }

    public static void e(Object... arr) {
        if ((level & ERROR) == 0) return;
        Log.e(TAG, Strings.concat(arr));
    }

    public static void save(Throwable e) {
        File dataDir = Icarus.getContext().getExternalCacheDir().getParentFile();
        File logDir = new File(dataDir, "logs");
        if (!logDir.exists()) {
            boolean b = logDir.mkdir();
        }
        File log = new File(logDir, Strings.concat(System.currentTimeMillis() / 1000,
                e.getClass().getSimpleName(), ".log"));
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(log);
            save(e, fos);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } finally {
            Recycle.close(fos);
        }
    }

    public static void save(Throwable e, OutputStream os) {
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

    public static void setLevel(int level) {
        Logger.level = level;
    }
}
