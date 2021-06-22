package a.icarus.component;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import a.icarus.utils.StatusUtil;

public abstract class BaseActivity extends AppCompatActivity {
    protected Application application;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getApplication();
        if (application instanceof MonitorApplication) {
            ((MonitorApplication) application).addActivity(this);
        }
        initTheme();
        initView();
        initData();
        initListener();
    }

    protected void initTheme() {
        StatusUtil.setTranslucent(this);
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
}
