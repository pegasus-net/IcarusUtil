package a.icarus.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class CommandUtil {

    public static Result exec(String command) {
        Result result = null;
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
            StringBuilder errorBuilder = new StringBuilder();
            while ((lineStr = errorReader.readLine()) != null) {
                errorBuilder.append(lineStr).append("/n");
            }
            result = new Result(successBuilder.toString(), errorBuilder.toString());
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
    public static class Result {
        public final String SUCCESS_MSG;
        public final String ERROR_MSG;

        public Result(String SUCCESS_MSG, String ERROR_MSG) {
            this.SUCCESS_MSG = SUCCESS_MSG;
            this.ERROR_MSG = ERROR_MSG;
        }
    }

}
