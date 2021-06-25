package a.icarus.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class CommandUtil {
    public static final String SUCCESS_MSG = "success";
    public static final String FAILED_MSG = "error";


    public static Map<String, String> exec(String command) {
        Map<String, String> result = new HashMap<>();
        BufferedReader successReader = null;
        BufferedReader errorReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            String lineStr;
            successReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            errorReader = new BufferedReader(new InputStreamReader(
                    process.getErrorStream()));
            StringBuilder successBuilder = new StringBuilder();
            while ((lineStr = successReader.readLine()) != null) {
                successBuilder.append(lineStr).append("/n");
            }
            result.put(SUCCESS_MSG, successBuilder.toString());
            StringBuilder errorBuilder = new StringBuilder();
            while ((lineStr = errorReader.readLine()) != null) {
                errorBuilder.append(lineStr).append("/n");
            }
            result.put(FAILED_MSG, errorBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Recycle.close(successReader);
            Recycle.close(errorReader);
            if (process != null) {
                process.destroy();
            }
        }
        return result;
    }

    public static String getPublicNetIp() {
        return OkHttpUtil.getString("https://api.ipify.org/");
    }

}
