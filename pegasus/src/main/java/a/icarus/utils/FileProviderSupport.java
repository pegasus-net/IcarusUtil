package a.icarus.utils;

import android.util.Log;

import androidx.core.content.FileProvider;

public class FileProviderSupport extends FileProvider {
    @Override
    public boolean onCreate() {
        Log.i("TAG", "onCreate: ");
        return super.onCreate();
    }

    public static String AUTHORITIES() {
        return Icarus.getContext().getPackageName() + ".icarus._file_provider";
    }
}
