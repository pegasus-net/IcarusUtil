package a.icarus.component;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public abstract class BaseDialog extends Dialog {

    protected final String TAG;

    protected Context context;
    protected ViewGroup decorView;
    protected Window window;

    public BaseDialog(@NonNull Context context, @LayoutRes int resId) {
        this(context, resId, 0);
    }

    public BaseDialog(@NonNull Context context, @LayoutRes int resId, int themeResId) {
        super(context, themeResId);
        TAG = "TAG:" + getClass().getSimpleName();
        this.context = context;
        setContentView(resId);
        window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setDimAmount(0.5f);
        decorView = (ViewGroup) window.getDecorView();
    }

    public void setViewOnClickListener(@IdRes int resID, View.OnClickListener listener) {
        findViewById(resID).setOnClickListener(listener);
    }


    public TextView findTextView(@IdRes int resID) {
        return findViewById(resID);
    }

    public ImageView findImageView(@IdRes int resID) {
        return findViewById(resID);
    }

    public Button findButtonView(@IdRes int resID) {
        return findViewById(resID);
    }


    public interface OnPositiveListener {
        void click();
    }

    public static class Builder {
        private final Context context;
        private int layoutId = 0;
        private int positiveViewId = 0;
        private int negativeViewId = 0;
        private OnPositiveListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setLayout(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder setPositiveView(@IdRes int positiveViewId, @NonNull OnPositiveListener listener) {
            this.positiveViewId = positiveViewId;
            this.listener = listener;
            return this;
        }

        public Builder setNegativeView(@IdRes int negativeViewId) {
            this.negativeViewId = negativeViewId;
            return this;
        }

        public BaseDialog builder() {
            if (layoutId == 0) {
                throw new NullPointerException("Dialog is missing layout file");
            }
            BaseDialog dialog = new BaseDialog(context, layoutId) {
            };
            if (positiveViewId != 0) {
                dialog.setViewOnClickListener(positiveViewId,
                        v -> {
                            listener.click();
                            dialog.dismiss();
                        });
            }
            if (negativeViewId != 0) {
                dialog.setViewOnClickListener(negativeViewId,
                        v -> dialog.dismiss());
            }
            return dialog;
        }
    }
}
