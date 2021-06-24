package a.icarus.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPool {
    private static ExecutorService cachePool;
    private static ScheduledExecutorService scheduledPool;

    public static ExecutorService getCachePool() {
        if (cachePool == null) {
            synchronized (ThreadPool.class) {
                if (cachePool == null) {
                    cachePool = Executors.newCachedThreadPool();
                }
            }
        }
        return cachePool;
    }

    public static ScheduledExecutorService getScheduledPool() {
        if (scheduledPool == null) {
            synchronized (ThreadPool.class) {
                if (scheduledPool == null) {
                    scheduledPool = Executors.newScheduledThreadPool(10);
                }
            }
        }
        return scheduledPool;
    }
}
