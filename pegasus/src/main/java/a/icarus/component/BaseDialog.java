package a.icarus.component;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

@SuppressWarnings("unused")
public abstract class BaseDialog extends Dialog {
    protected Context context;
    protected View rootView;
    protected Window window;

    public BaseDialog(@NonNull Context context, @LayoutRes int resId) {
        this(context, resId, 0);
    }

    public BaseDialog(@NonNull Context context, @LayoutRes int resId, int themeResId) {
        super(context, themeResId);
        this.context = context;
        setContentView(resId);
        window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setDimAmount(0.5f);
        init();
    }


    abstract protected void init();

    public BaseDialog setNegativeButton(@IdRes int resID) {
        setViewOnClickListener(resID, v -> dismiss());
        return this;
    }

    public BaseDialog setPositiveButton(@IdRes int resID, OnPositiveListener listener) {
        setViewOnClickListener(resID, v -> {
            listener.click(v);
            dismiss();
        });
        return this;
    }

    @Override
    public void setContentView(int layoutResID) {
        rootView = LayoutInflater.from(context).inflate(layoutResID, null);
        setContentView(rootView);
    }

    protected void setViewOnClickListener(@IdRes int resID, View.OnClickListener listener) {
        View view = rootView.findViewById(resID);
        view.setOnClickListener(listener);
    }


    protected TextView findTextView(@IdRes int resID) {
        return rootView.findViewById(resID);
    }

    protected ImageView findImageView(@IdRes int resID) {
        return rootView.findViewById(resID);
    }

    protected Button findButtonView(@IdRes int resID) {
        return rootView.findViewById(resID);
    }

    public interface OnPositiveListener {
        void click(View v);
    }
}
