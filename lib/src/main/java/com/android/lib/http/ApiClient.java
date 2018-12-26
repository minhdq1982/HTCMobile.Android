package com.android.lib.http;

import com.android.lib.RESTManager;
import com.android.lib.model.ProgressListener;
import com.android.lib.util.LibConstants;
import com.android.lib.util.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ThinhNH on 3/22/2018.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(RESTManager.API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(createOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientDownload(ProgressListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RESTManager.API_URL)
                .client(createOkHttpClientDownload(listener))
                .build();
        return retrofit;
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(createHttpLogging())
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(LibConstants.REQUEST_TIMEOUT_60S, TimeUnit.MILLISECONDS)
                .readTimeout(LibConstants.REQUEST_TIMEOUT_60S, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    private static OkHttpClient createOkHttpClientDownload(ProgressListener listener) {
        return new OkHttpClient.Builder()
                .addInterceptor(new DownloadInterceptor(listener))
                .addInterceptor(new HeaderInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(LibConstants.REQUEST_TIMEOUT_60S, TimeUnit.MILLISECONDS)
                .readTimeout(LibConstants.REQUEST_TIMEOUT_60S, TimeUnit.MILLISECONDS)
                .build();
    }


    private static HttpLoggingInterceptor createHttpLogging() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Logger.d(message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }
}
