package a.icarus.java;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.icarus.utils.Logger;
import a.icarus.utils.Strings;

public class TestMain {
    public static void main(String[] args) throws Exception {
        char c = '严';
        char v = '\\';
        String s1 ="C:\\Users\\1\\Desktop";
        OutputStream os =new FileOutputStream(new File("C:\\Users\\1\\Desktop\\a.txt"));
        PrintStream printStream = new PrintStream(os);
        PrintWriter writer = new PrintWriter(os);
        StringWriter stringWriter = new StringWriter();
        stringWriter.write("1111");
        System.out.println(stringWriter.toString());
        new Exception("sdfsfdds").printStackTrace(printStream);
    }
}
