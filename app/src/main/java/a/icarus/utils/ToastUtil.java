package a.icarus.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ToastUtil {
    private static final List<Toast> toasts = new ArrayList<>();

    public static void show(Context context, String msg) {
        for (Toast t : toasts) {
            t.cancel();
        }
        toasts.clear();
        Toast toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toasts.add(toast);
        toast.show();
    }
}
