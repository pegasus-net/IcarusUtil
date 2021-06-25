package a.icarus.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.LogRecord;

import a.icarus.component.Icarus;

@SuppressWarnings("unused")
public class ToastUtil {
    private static final List<Toast> toasts = new ArrayList<>();

    public static void show(String msg) {
        show(msg, 1000);
    }


    public static void show(String msg, long time) {
        for (Toast t : toasts) {
            t.cancel();
        }
        toasts.clear();
        Toast toast = Toast.makeText(Icarus.getContext(), msg, Toast.LENGTH_LONG);
        toasts.add(toast);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(toast::cancel, time);
    }
}
