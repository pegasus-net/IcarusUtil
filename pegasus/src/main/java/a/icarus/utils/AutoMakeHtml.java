package a.icarus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class AutoMakeHtml {
    public static void main(String[] args) throws Exception {
        change(new File("C:\\Users\\1\\Desktop\\隐私政策.txt"),
                new File("C:\\Users\\1\\Desktop\\隐私政策.html"));
    }

    public static void change(File source, File html) throws Exception {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(html);
        InputStreamReader isr = new InputStreamReader(fis, "gbk");
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        char[] buffer = new char[1024];
        int len;
        StringBuilder builder = new StringBuilder();
        while ((len = isr.read(buffer)) != -1) {
            builder.append(new String(buffer, 0, len));
        }
        String text = builder.toString();
        text = text.replaceAll("\r", "");
        text = text.replaceAll("\n\n+", "<br>\n");
        text = text.replaceAll("\n", "<br>\n");
        osw.write(text);
        osw.close();
        isr.close();
    }
}
