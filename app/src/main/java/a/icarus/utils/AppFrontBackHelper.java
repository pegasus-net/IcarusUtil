package a.icarus.utils;

import android.app.Activity;
import android.app.Application;

import org.jetbrains.annotations.NotNull;

import a.icarus.simpleIml.SimpleActivityLifecycleCallbacks;

public class AppFrontBackHelper {
    private OnAppStatusListener mOnAppStatusListener;
    private int activityStartCount = 0;

    public AppFrontBackHelper(Application application) {

        SimpleActivityLifecycleCallbacks lifecycleCallbacks = new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityStarted(@NotNull Activity activity) {
                activityStartCount++;
                if (activityStartCount == 1) {
                    if (mOnAppStatusListener != null) {
                        mOnAppStatusListener.onFront(activity);
                    }
                }
            }

            @Override
            public void onActivityStopped(@NotNull Activity activity) {

                activityStartCount--;

                if (activityStartCount == 0) {

                    if (mOnAppStatusListener != null) {

                        mOnAppStatusListener.onBack(activity);

                    }

                }

            }
        };
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public void setOnAppStatusListener(OnAppStatusListener listener) {
        mOnAppStatusListener = listener;

    }


    public interface OnAppStatusListener {
        void onFront(Activity activity);

        void onBack(Activity activity);
    }

}
