package com.android.lib.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiDownloadInterface {
    @Streaming
    @GET
    Call<ResponseBody> downloadCatalog(@Url String url);

    @Streaming
    @GET("CalculateInterest/MobileExportCalculateInterest")
    Call<ResponseBody> downloadCalculateInterest(@Query("Borrow") double borrow, @Query("NumberMonth") int numberMonth, @Query("InterestRate1") double interestRate1, @Query("InterestRate2") double interestRate2);
}
