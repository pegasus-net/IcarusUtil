package a.icarus.utils;

import androidx.core.content.FileProvider;

public class FileProviderSupport extends FileProvider {
    public static String AUTHORITIES() {
        return Icarus.getContext().getPackageName() + ".icarus._file_provider";
    }
}
