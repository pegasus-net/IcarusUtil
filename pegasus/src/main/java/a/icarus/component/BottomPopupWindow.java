package a.icarus.component;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

@SuppressWarnings("unused")
public class BottomPopupWindow extends PopupWindow {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    public BottomPopupWindow() {
        this(null, MATCH_PARENT, WRAP_CONTENT);
    }

    public BottomPopupWindow(View contentView) {
        this(contentView, MATCH_PARENT, WRAP_CONTENT);
    }

    public BottomPopupWindow(int width, int height) {
        this(null, width, height);
    }

    public BottomPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, true);
    }

    public BottomPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        setBackgroundDrawable(new ColorDrawable(0));
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        if (getContentView() == null) {
            return;
        }
        showAtLocation(getContentView(), Gravity.BOTTOM, 0, 0);
    }
}
