package a.icarus.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

@SuppressWarnings("unused")
public class SoftInputUtil {
    public static void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void copy(String text) {
        copy(text, true);
    }

    public static void copy(String text, boolean notify) {
        if (Strings.isEmpty(text)) {
            if (notify) {
                ToastUtil.show("复制内容不能为空");
            }
            return;
        }
        ClipboardManager manager =
                (ClipboardManager) Icarus.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText("text", text));
        if (notify) {
            ToastUtil.show("复制成功");
        }
    }
}
