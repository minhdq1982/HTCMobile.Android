package com.android.lib.http;

import com.android.lib.model.ProgressListener;
import com.android.lib.model.response.DownloadResponse;
import com.android.lib.util.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class DownloadInterceptor implements Interceptor {
    private ProgressListener listener;

    public DownloadInterceptor(ProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Logger.e("Change:--->>>>>>");
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadResponse(originalResponse.body(), listener))
                .build();
    }
}
