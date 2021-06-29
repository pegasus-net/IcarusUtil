package a.icarus.utils;

@SuppressWarnings("unused")
public class RepeatThread extends Thread {

    private volatile boolean isRunning;
    private final Runnable task;

    public RepeatThread(Runnable task) {
        this.task = task;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            task.run();
        }
    }
    synchronized public void cancel() {
        isRunning = false;
    }
}
