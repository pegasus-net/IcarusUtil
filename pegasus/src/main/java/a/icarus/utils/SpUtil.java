package a.icarus.utils;

import android.content.Context;
import android.content.SharedPreferences;

@SuppressWarnings("unused")
public class SpUtil {
    private static String name = "config";

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static long getLong(String key, long defValue) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void putLong(String key, long value) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public static float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    public static float getFloat(String key, float defValue) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static void putFloat(String key, float value) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defValue) {
        SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putString(String key, String value) {
        if (value != null) {
            SharedPreferences sp = Icarus.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(key, value);
            edit.apply();
        }
    }

    public static void setName(String name) {
        SpUtil.name = name;
    }
}
