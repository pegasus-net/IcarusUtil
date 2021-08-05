package a.icarus.component;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import a.icarus.R;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import a.icarus.utils.WindowUtil;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity {
    protected Application application;
    protected Window mWindow;
    protected View decorView;
    protected String TAG;
    protected final int USE_DATA_BINDING = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "TAG:" + getClass().getSimpleName();
        application = getApplication();
        if (application instanceof MonitorApplication) {
            ((MonitorApplication) application).addActivity(this);
        }
        mWindow = getWindow();
        initTheme();

        decorView = mWindow.getDecorView();

        if (setLayout() != USE_DATA_BINDING) {
            if (setLayout() == 0) {
                setContentView(R.layout.empty);
            } else {
                setContentView(setLayout());
            }
        }
        initView();
        initData();
        initListener();
    }

    @LayoutRes
    protected abstract int setLayout();

    protected void initTheme() {
        WindowUtil.setTranslucentStatusBar(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
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

    protected void setTitle(@IdRes int id, @NonNull String title) {
        ((TextView) findViewById(id)).setText(title);
    }

    @NonNull
    protected ViewModelProvider getViewModelProvider() {
        return new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory());
    }

    @NonNull
    protected ViewModelProvider getViewModelProviderAndroid() {
        return new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(application));
    }
}
