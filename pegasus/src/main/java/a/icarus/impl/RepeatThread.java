package a.icarus.impl;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class RepeatThread extends Thread {
    private volatile boolean isRunning;
    private final Runnable task;

    public RepeatThread(@NonNull Runnable task) {
        this.task = task;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            task.run();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    synchronized public void cancel() {
        isRunning = false;
    }
}
