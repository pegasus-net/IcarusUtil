package a.icarus.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.File;

@SuppressWarnings("unused")
public class Icarus {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static Context getContext() {
        if (mContext == null) {
            throw new IcarusInitException("Icarus has't init ,please use Icarus.init() to init;");
        }
        return mContext;
    }

    public static class IcarusInitException extends NullPointerException {
        public IcarusInitException() {
        }

        public IcarusInitException(String s) {
            super(s);
        }
    }
}
