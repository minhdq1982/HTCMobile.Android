package com.android.lib.http;

import com.android.lib.model.LibLocalDataManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by NE on 2018-01-07.
 */

public class HeaderInterceptor implements Interceptor {
    private static final String ACCEPT_HEADER = "Accept";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String JSON_TYPE = "application/json";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder().addHeader(ACCEPT_HEADER, JSON_TYPE);
        String token = getLoginToken();
        if (token != null && isAuthorization(request)) {
            builder.addHeader(AUTHORIZATION_HEADER,
                    token);
        }
        request = builder.build();
        return chain.proceed(request);
    }

    private String getLoginToken() {
        String accessToken = LibLocalDataManager.getInstance().getAccessToken();
        if (accessToken!=null) {
            return "Bearer " + accessToken;
        }
        return null;
    }

    private boolean isAuthorization(Request request) {
        boolean isAuthorization = true;

        if (request.url().url().toString().contains("https://www.googleapis.com/youtube/v3/search")) {
            isAuthorization = false;
        }

        return isAuthorization;
    }
}
