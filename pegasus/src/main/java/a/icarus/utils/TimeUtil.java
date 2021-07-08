package a.icarus.utils;

public class TimeUtil {
    public static long lastSignTime;

    public void current() {
        Logger.t(System.currentTimeMillis()); 
    }

    public void sign() {
        Logger.t(System.currentTimeMillis() - lastSignTime);
        lastSignTime = System.currentTimeMillis();
    }
}
