package a.icarus.utils;

public class Maths {
    public static <T extends Comparable<T>> T interval(T value, T min, T max) {
        if (value.compareTo(max) > 0) {
            return max;
        }
        if (value.compareTo(min) < 0) {
            return min;
        }
        return value;
    }
}
