package a.icarus.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import a.icarus.utils.AppFrontBackHelper;

@SuppressWarnings("unused")
public class MonitorApplication extends Application implements AppFrontBackHelper.OnAppStatusListener {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Icarus.init(context);
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
    public void onFront(Activity activity) {
        if (!onFrontIgnore(activity)) {
            appBackgroundToFront(activity);
        }
    }

    @Override
    public void onBack(Activity activity) {
        appFrontToBackground(activity);
    }
}
