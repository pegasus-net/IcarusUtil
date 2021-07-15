package a.icarus.component;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import a.icarus.utils.WindowUtil;

@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity {
    protected Application application;
    protected Window mWindow;
    protected View decorView;
    protected String TAG;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "TAG:" + getClass().getSimpleName();
        application = getApplication();
        if (application instanceof MonitorApplication) {
            ((MonitorApplication) application).addActivity(this);
        }
        mWindow = getWindow();
        decorView = mWindow.getDecorView();
        initTheme();
        initView();
        initData();
        initListener();
    }

    protected void initTheme() {
        WindowUtil.setTranslucentStatusBar(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        if (application instanceof MonitorApplication) {
            ((MonitorApplication) application).removeActivity(this);
        }
        super.onDestroy();
    }

    protected void setBackView(@IdRes int id) {
        findViewById(id).setOnClickListener(v -> onBackPressed());
    }

    protected void setFinishView(@IdRes int id) {
        findViewById(id).setOnClickListener(v -> finish());
    }

}
