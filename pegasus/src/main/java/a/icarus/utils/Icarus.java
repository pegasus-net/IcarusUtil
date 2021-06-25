package a.icarus.utils;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressWarnings("unused")
public class Icarus {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
