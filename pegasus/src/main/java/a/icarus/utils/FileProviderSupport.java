package a.icarus.utils;

import android.util.Log;

import androidx.core.content.FileProvider;

public class FileProviderSupport extends FileProvider {
    public static String AUTHORITIES;

    @Override
    public boolean onCreate() {
        AUTHORITIES = getContext().getPackageName() + ".icarus._file_provider";
        return super.onCreate();
    }
}
