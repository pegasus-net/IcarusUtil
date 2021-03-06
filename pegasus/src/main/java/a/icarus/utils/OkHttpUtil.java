package a.icarus.utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@SuppressWarnings("unused")
public class OkHttpUtil {
    private OkHttpUtil() {
    }

    public static ResponseBody getBody(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getString(String url) {
        ResponseBody body = getBody(url);
        try {
            return body != null ? body.string() : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream getInputStream(String url) {
        ResponseBody body = getBody(url);
        return body != null ? body.byteStream() : null;
    }

    @Deprecated
    public static void asyncCall(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ThreadManager.runOnUiThread(() -> callback.onFailure(call, e));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                ThreadManager.runOnUiThread(() -> {
                    try {
                        callback.onResponse(call, response);
                    } catch (IOException e) {
                        ThreadManager.runOnUiThread(() -> callback.onFailure(call, e));
                    }
                });
            }
        });
    }
}
