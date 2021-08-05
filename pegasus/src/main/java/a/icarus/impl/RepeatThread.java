package a.icarus.impl;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class RepeatThread extends Thread {
    private volatile boolean isRunning;
    private final Runnable task;
    private int duration = 0;

    public RepeatThread(@NonNull Runnable task) {
        this.task = task;
    }

    public RepeatThread(int duration, @NonNull Runnable task) {
        this.task = task;
        this.duration = duration;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            long t = System.currentTimeMillis();
            task.run();
            t = System.currentTimeMillis() - t;
            if (t < duration) {
                try {
                    sleep(duration - t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    synchronized public void cancel() {
        isRunning = false;
    }
}
