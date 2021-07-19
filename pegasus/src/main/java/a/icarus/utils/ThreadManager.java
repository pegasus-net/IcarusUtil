package a.icarus.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SuppressWarnings("unused")
public class ThreadManager {
    private static ExecutorService cachePool;
    private static ScheduledExecutorService scheduledPool;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    ;

    public static void createCachePool() {
        if (cachePool == null) {
            synchronized (ThreadManager.class) {
                if (cachePool == null) {
                    cachePool = Executors.newCachedThreadPool();
                }
            }
        }
    }

    public static ScheduledExecutorService getScheduledPool() {
        if (scheduledPool == null) {
            synchronized (ThreadManager.class) {
                if (scheduledPool == null) {
                    scheduledPool = Executors.newScheduledThreadPool(10);
                }
            }
        }
        return scheduledPool;
    }

    public static void runOnThreadPool(Runnable runnable) {
        if (cachePool == null) {
            createCachePool();
        }
        if (cachePool.isShutdown()) {
            cachePool = null;
            createCachePool();
        }
        cachePool.execute(runnable);
    }

    public static void exit() {
        if (cachePool != null) {
            cachePool.shutdown();
        }
        mainHandler.removeCallbacksAndMessages(null);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
            return;
        }
        mainHandler.post(runnable);
    }

    public static void runOnUiThreadDelay(Runnable runnable, long delay) {
        Handler handler = new Handler(Looper.getMainLooper());
        mainHandler.postDelayed(runnable, delay);
    }
}
