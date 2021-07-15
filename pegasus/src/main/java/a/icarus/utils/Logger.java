package a.icarus.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
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
    public static final int SAVE = 1 << 6;

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

    public static void save(Throwable e, boolean crash) {
        if ((level & SAVE) == 0) return;
        FileOutputStream fos = null;
        try {
            File dataDir = Icarus.getContext().getExternalCacheDir().getParentFile();
            File logDir = new File(dataDir, "logs");
            if (!logDir.exists()) {
                boolean b = logDir.mkdir();
            }
            File log = new File(logDir,
                    Strings.concat(crash ? "_CRASH_" : "",
                            DateUtil.format(DateUtil.FILE_FORMAT),
                            e.getClass().getSimpleName(), ".log"));
            fos = new FileOutputStream(log);
            save(e, fos);
        } catch (Exception ignore) {
        } finally {
            Recycle.close(fos);
        }
    }

    public static void save(String msg) {
        if ((level & SAVE) == 0) return;
        FileOutputStream fos = null;
        try {
            File dataDir = Icarus.getContext().getExternalCacheDir().getParentFile();
            File logDir = new File(dataDir, "logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            File log = new File(logDir,
                    Strings.concat(DateUtil.format(DateUtil.FILE_FORMAT), "msg.log"));
            fos = new FileOutputStream(log);
            fos.write(msg.getBytes());
        } catch (Exception ignore) {
        } finally {
            Recycle.close(fos);
        }
    }

    public static void save(Throwable e, OutputStream os) {
        if ((level & SAVE) == 0) return;
        if (e == null || os == null) return;
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
