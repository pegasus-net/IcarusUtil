package a.icarus.utils;

import android.content.Context;

import a.icarus.component.Icarus;

@SuppressWarnings("unused")
public class ConversionTool {
    public static int dp2px(float dp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2dp(float px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    public static int float2int(float f) {
        return (int) (f + 0.5f);
    }
}
