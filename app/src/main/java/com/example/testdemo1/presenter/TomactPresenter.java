package com.example.testdemo1.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TomactPresenter {
    public void sendHttpData(String url, final Handler handler) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d("zhangziyan", "访问失败");

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Log.d("zhangziyan", "访问成功");
                        Message msg = handler.obtainMessage();
                        msg.what = 0;
                        msg.obj = response.body().bytes();
                        handler.sendMessage(msg);
                    }
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
