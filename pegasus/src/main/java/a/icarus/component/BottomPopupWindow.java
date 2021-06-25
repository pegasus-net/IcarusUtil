package a.icarus.component;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.PopupWindow;

import a.icarus.R;

@SuppressWarnings("unused")
public class BottomPopupWindow extends PopupWindow {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    private ValueAnimator animator;
    private Window attachWindow;
    private float windowAlpha =1.0f;

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
        setAnimationStyle(R.style.popupWindow_anim);
        setOnDismissListener(() -> screenAlphaAnimStart(windowAlpha, 1.0f, 300));
    }

    public void setWindow(Window window) {
        attachWindow = window;
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        if (getContentView() == null) {
            return;
        }
        showAtLocation(getContentView(), Gravity.BOTTOM, 0, 0);
        screenAlphaAnimStart(windowAlpha, 0.5f, 500);
    }

    private void screenAlphaAnimStart(float from, float to, int duration) {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
        animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(duration);
        animator.addUpdateListener(animation -> {
            if (attachWindow != null) {
                WindowManager.LayoutParams attributes = attachWindow.getAttributes();
                windowAlpha = attributes.alpha = (float) animation.getAnimatedValue();
                attachWindow.setAttributes(attributes);
            }
        });
        animator.start();
    }
}
