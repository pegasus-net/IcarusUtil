package a.icarus.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class WindowUtil {
    public static void setTranslucent(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            WindowUtil.setWhiteStatus(activity);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void setBlackStatus(Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        if (decorView != null) {
            int option = decorView.getSystemUiVisibility();
            option |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
        }
    }

    public static void setWhiteStatus(Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        if (decorView != null) {
            int option = decorView.getSystemUiVisibility();
            option &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
        }
    }

    public static int getStatusBarHeight() {
        Resources res = Icarus.getContext().getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return ConversionTool.dp2px(20);
        }
    }

    public static int getNavBarHeight() {
        Resources res = Icarus.getContext().getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }
}
