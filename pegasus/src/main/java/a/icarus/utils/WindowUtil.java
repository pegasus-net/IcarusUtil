package a.icarus.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

@SuppressWarnings("unused")
public class WindowUtil {
    public static void setTranslucentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            fillInStatusBar(activity);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void setTranslucentNavBar(Activity activity) {
        Window window = activity.getWindow();
        window.setNavigationBarColor(Color.TRANSPARENT);
        fillInNavBar(activity);
    }

    public static void setBlackStatusText(Activity activity) {
        View decorView = getDecorView(activity);
        if (decorView != null) {
            int option = decorView.getSystemUiVisibility();
            option |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            if (decorView.getSystemUiVisibility() != option) {
                decorView.setSystemUiVisibility(option);
            }
        }
    }

    public static void setWhiteStatusText(Activity activity) {
        View decorView = getDecorView(activity);
        if (decorView != null) {
            int option = decorView.getSystemUiVisibility();
            option &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            if (decorView.getSystemUiVisibility() != option) {
                decorView.setSystemUiVisibility(option);
            }
        }
    }

    public static void fillInStatusBar(Activity activity) {
        View decorView = getDecorView(activity);
        if (decorView != null) {
            int option = decorView.getSystemUiVisibility();
            option |= (View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            if (decorView.getSystemUiVisibility() != option) {
                decorView.setSystemUiVisibility(option);
            }
        }
    }

    public static void setImmersive(Activity activity) {
        View decorView = getDecorView(activity);
        if (decorView != null) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public static void clearImmersive(Activity activity, boolean status, boolean nav) {
        View decorView = getDecorView(activity);
        if (decorView != null) {
            decorView.setSystemUiVisibility(0);
            if (status) {
                fillInStatusBar(activity);
            }
            if (nav) {
                fillInNavBar(activity);
            }
        }
    }

    private static final int TAG_OFFSET = 900;

    public static void setViewFitSystemWindows(View view) {
        Object haveSetOffset = view.getTag(TAG_OFFSET);
        if (haveSetOffset != null && (Boolean) haveSetOffset) return;
        view.setPadding(view.getPaddingLeft(),
                view.getPaddingTop() + getStatusBarHeight(),
                view.getPaddingRight(),
                view.getPaddingBottom());
        view.setTag(TAG_OFFSET, true);
    }

    public static void fillInNavBar(Activity activity) {
        View decorView = getDecorView(activity);
        if (decorView != null) {
            int option = decorView.getSystemUiVisibility();
            option |= (View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            if (decorView.getSystemUiVisibility() != option) {
                decorView.setSystemUiVisibility(option);
            }
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

    private static View getDecorView(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                return window.getDecorView();
            }
        }
        return null;
    }

}
