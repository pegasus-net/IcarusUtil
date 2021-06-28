package a.icarus.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.CheckBox;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SuppressWarnings("unused")
public class ThreadUtil {
    private static ExecutorService cachePool;
    private static ScheduledExecutorService scheduledPool;

    public static ExecutorService getCachePool() {
        if (cachePool == null) {
            synchronized (ThreadUtil.class) {
                if (cachePool == null) {
                    cachePool = Executors.newCachedThreadPool();
                }
            }
        }
        return cachePool;
    }

    public static ScheduledExecutorService getScheduledPool() {
        if (scheduledPool == null) {
            synchronized (ThreadUtil.class) {
                if (scheduledPool == null) {
                    scheduledPool = Executors.newScheduledThreadPool(10);
                }
            }
        }
        return scheduledPool;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    public static void runOnUiThreadDelay(Runnable runnable, long delay) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, delay);
    }
}
