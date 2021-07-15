package a.icarus.utils;

import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ToastUtil {
    private static Toast toast;

    public static void show(String msg) {
        show(msg, 1000);
    }


    public static void show(String msg, long time) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(Icarus.getContext(), msg, Toast.LENGTH_LONG);
        toast.show();
        if (time >= 3500) return;
        Handler handler = new Handler();
        handler.postDelayed(toast::cancel, time);
    }
}
