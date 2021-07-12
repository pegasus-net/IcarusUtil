package a.icarus.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import a.icarus.utils.AppFrontBackHelper;
import a.icarus.utils.Icarus;
import a.icarus.utils.Logger;
import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class MonitorApplication extends Application implements AppFrontBackHelper.OnAppStatusListener {
    protected String TAG;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = "TAG:" + getClass().getSimpleName();
        context = getApplicationContext();
        Icarus.init(context);
//        Logger.addType(Logger.CRASH);
//        Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(((t, e) -> {
//            Logger.save(e);
//            if (handler != null) {
//                handler.uncaughtException(t, e);
//            }
//        }));
        AppFrontBackHelper helper = new AppFrontBackHelper(this);
        helper.setOnAppStatusListener(this);
    }


    public static Context getContext() {
        return context;
    }

    synchronized public void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    synchronized public void removeActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
        }
    }

    synchronized public void finishAll() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
    }

    protected boolean onFrontIgnore(Activity activity) {
        return false;
    }

    protected void appBackgroundToFront(Activity activity) {

    }

    protected void appFrontToBackground(Activity activity) {

    }

    @Override
    public final void onFront(Activity activity) {
        if (!onFrontIgnore(activity)) {
            appBackgroundToFront(activity);
        }
    }

    @Override
    public final void onBack(Activity activity) {
        appFrontToBackground(activity);
    }
}
