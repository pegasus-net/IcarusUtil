package a.icarus.utils;

import android.content.Context;

@SuppressWarnings("unused")
public class DpUtil {
    public static float dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return dp * density;
    }
}
