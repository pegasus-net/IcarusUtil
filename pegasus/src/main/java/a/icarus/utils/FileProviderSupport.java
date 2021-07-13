package a.icarus.utils;

import androidx.core.content.FileProvider;

public class FileProviderSupport extends FileProvider {
    public static final String AUTHORITIES
            = Icarus.getContext().getPackageName() + ".icarus._file_provider";
}
