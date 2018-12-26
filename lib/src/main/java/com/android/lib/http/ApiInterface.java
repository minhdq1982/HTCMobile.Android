package com.android.lib.http;

import com.android.lib.model.response.ActionCarResponse;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.AppointmentResponse;
import com.android.lib.model.response.AreaResponse;
import com.android.lib.model.response.CarCategoryResponse;
import com.android.lib.model.response.CarFilterResponse;
import com.android.lib.model.response.CarVersionNameResponse;
import com.android.lib.model.response.CarVersionResponse;
import com.android.lib.model.response.CardListResponse;
import com.android.lib.model.response.CardResponse;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.CityResponse;
import com.android.lib.model.response.DetailCarResponse;
import com.android.lib.model.response.FacebookGroupResponse;
import com.android.lib.model.response.GroupNewsResponse;
import com.android.lib.model.response.GuideBookResponse;
import com.android.lib.model.response.GuildBookItemDetailResponse;
import com.android.lib.model.response.HistoryEndowResponse;
import com.android.lib.model.response.HistoryPointResponse;
import com.android.lib.model.response.HistoryUsePointResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.MaintenanceDetailResponse;
import com.android.lib.model.response.MaintenanceLevelResponse;
import com.android.lib.model.response.NewsFeedResponse;
import com.android.lib.model.response.NewsListResponse;
import com.android.lib.model.response.NewsResponse;
import com.android.lib.model.response.NotificationDetailResponse;
import com.android.lib.model.response.NotificationResponse;
import com.android.lib.model.response.PostAppointmentResponse;
import com.android.lib.model.response.PriceAdviceResponse;
import com.android.lib.model.response.PromotionListResponse;
import com.android.lib.model.response.RegisterTestDriveResponse;
import com.android.lib.model.response.SelectCarResponse;
import com.android.lib.model.response.SettingResponse;
import com.android.lib.model.response.SimpleResponse;
import com.android.lib.model.response.SurveyResponse;
import com.android.lib.model.response.TechnicalGuideResponse;
import com.android.lib.model.response.TermResponse;
import com.android.lib.model.response.UpdateResponse;
import com.android.lib.model.response.WarrantyPolicyResponse;
import com.android.lib.model.response.YoutubeResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ThinhNH on 3/22/2018.
 */

public interface ApiInterface {

    @POST("TokenAuth/MobileLogin")
    Call<SimpleResponse> login(@Body Map<String, Object> params);

    @POST("TokenAuth/MobileVerifyLogin")
    Call<LoginResponse> verifyLogin(@Body Map<String, Object> params);

    @POST("Complain/MobilePost")
    Call<SimpleResponse> feedback(@Body Map<String, Object> params);

    @GET("Profile/MobileGetDetail")
    Call<LoginResponse> getProfile(@Query("Id") int id);

    @POST("Profile/MobileUpdateSetting")
    Call<UpdateResponse> updateSetting(@Body Map<String, Object> params);

    @POST("Profile/MobileUpdate")
    Call<SimpleResponse> updateProfile(@Body Map<String, Object> params);

    @GET("UserNotification/GetAll")
    Call<NotificationResponse> getNotification(
            @Query("SkipCount") int skipCount,
            @Query("MaxResultCount") int maxResultCount
    );

    @PUT("UserNotification/SetRead")
    Call<NotificationDetailResponse> setRead(@Body Map<String, Object> params);

    @GET("UserNotification/GetDetail")
    Call<NotificationDetailResponse> getNotificationDetail(@Query("Id") long id);

    @GET("Setting/MobileGet")
    Call<SettingResponse> getSetting();

    @DELETE("Membership/MobileRemoveCard")
    Call<SimpleResponse> deleteCard(@Query("Id") int id);

    @Multipart
    @POST("Profile/MobileUpdateAvatar")
    Call<SimpleResponse> updateAvatar(@Part MultipartBody.Part part);

    @POST("Profile/MobilePostCar")
    Call<ActionCarResponse> addCar(@Body Map<String, Object> params);

    @POST("Car/MobileRegisterTestDrive")
    Call<RegisterTestDriveResponse> registerTestDrive(@Body Map<String, Object> params);

    @POST("Membership/MobileAddCard")
    Call<CardResponse> addCard(@Body Map<String, Object> params);

    @DELETE("Profile/MobileDeleteCar")
    Call<ActionCarResponse> deleteCar(@Query("Id") int id);

    @GET("Agency/MobileGetList")
    Call<AgenciesResponse> getAgencies();

    @GET("City/MobileGetList")
    Call<CityResponse> getCities();

    @GET("Membership/MobileGetIncentiveUsingHistory")
    Call<HistoryEndowResponse> getEndow(
            @Query("SkipCount") int skipCount,
            @Query("MaxResultCount") int maxResultCount,
            @Query("TransactionCode") String transactionCode,
            @Query("CardNumber") String cardNumber,
            @Query("ProgramCode") String programCode,
            @Query("ProgramName") String programName,
            @Query("FromDate") String fromDate,
            @Query("ToDate") String toDate
    );

    @GET("Membership/MobileGetUsingPointHistory")
    Call<HistoryUsePointResponse> getUsePoint(
            @Query("SkipCount") int skipCount,
            @Query("MaxResultCount") int maxResultCount,
            @Query("TransactionCode") String transactionCode,
            @Query("CardNumber") String cardNumber,
            @Query("FromDate") String fromDate,
            @Query("ToDate") String toDate
    );

    @GET("Membership/MobileGetRankUpPointHistory")
    Call<HistoryPointResponse> getPoint(
            @Query("SkipCount") int skipCount,
            @Query("MaxResultCount") int maxResultCount,
            @Query("TransactionCode") String transactionCode,
            @Query("CardNumber") String cardNumber,
            @Query("FromDate") String fromDate,
            @Query("ToDate") String toDate
    );

    @GET("Agency/MobileGetListByCity")
    Call<AgenciesResponse> getAgenciesById(@Query("cityId") int cityId, @Query("name") String name, @Query("type") int type, @Query("_lat") double _lat, @Query("_long") double _long);

    @POST("Maintenance/MobileCarVersionNameList")
    Call<CarVersionNameResponse> postCarVersion();

    @GET("Agency/MobileGetListNearby")
    Call<AgenciesResponse> getAgenciesNearBy(@Query("_lat") double _lat, @Query("_long") double _long, @Query("type") int type);

    @GET("Car/MobileGetlist")
    Call<CarsResponse> getListCar(@Query("isFull") boolean isFull);

    @GET("FacebookGroups/MobileGetList")
    Call<FacebookGroupResponse> getListFacebookGroup();

    @GET("Car/MobileGetDetail")
    Call<DetailCarResponse> getCar(@Query("Id") int carId);

    @GET("Membership/MobileGetList")
    Call<CardListResponse> getCardList();

    @GET("News/MobileGetPromotionList")
    Call<PromotionListResponse> getPromotionList();

    @GET("PriceAdvice/MobileGetListCarVersion")
    Call<CarVersionResponse> getListCarVersion(@Query("carId") int carId);

    @GET("Area/MobileGetList")
    Call<AreaResponse> getListArea();

    @GET("PriceAdvice/MobileGetDetailPrice")
    Call<PriceAdviceResponse> getListPrice(@Query("versionId") int versionId, @Query("areaId") int areaId);

    @GET("News/MobileGetNewsHTC")
    Call<NewsListResponse> getHTCList(@Query("SkipCount") int skipCount, @Query("MaxResultCount") int maxResultCount);

    @GET("News/MobileGeNewsMarketAndProduct")
    Call<NewsListResponse> getProductList(@Query("SkipCount") int skipCount, @Query("MaxResultCount") int maxResultCount);

    @GET("News/MobileGetNewsGroupsList")
    Call<GroupNewsResponse> getGroupNews();

    @GET("News/MobileGetListNewsHome")
    Call<NewsListResponse> getListNewsHome(@Query("Id") int id, @Query("SkipCount") int skipCount, @Query("MaxResultCount") int maxResultCount);

    @GET("News/MobileGetDetail")
    Call<NewsResponse> getNewsDetail(@Query("Id") int id);

    @GET("Survey/MobileGetSurvey")
    Call<SurveyResponse> getSurvey(@Query("_phone") String phoneNumber);

    @GET("WarrantyPolicy/MobileGetList")
    Call<WarrantyPolicyResponse> getListWarrantyPolicy();

    @GET("Maintenance/MobileGetMaintenanceLevel")
    Call<MaintenanceLevelResponse> getListMaintenanceLevel();

    @POST("Maintenance/MobileMaintenanceCarGetDetail")
    Call<MaintenanceDetailResponse> postMaintenanceCarGetDetail(@Body Map<String, Object> params);

    @GET("Term/MobileGetDetail")
    Call<TermResponse> getTerm();

    @GET("Appointment/MobileGetList")
    Call<AppointmentResponse> getListAppointment();

    @GET("TechnicalGuide/MobileGetList")
    Call<TechnicalGuideResponse> getListTechnicalGuide(@Query("SkipCount") int skipCount, @Query("MaxResultCount") int maxResultCount);

    @GET("GuideBook/MobileGetList")
    Call<GuideBookResponse> getListGuideBook(
            @Query("SkipCount") int skipCount,
            @Query("MaxResultCount") int maxResultCount,
            @Query("KeySearch") String keySearch,
            @Query("CarId") int carId
    );

    @GET("TechnicalGuide/MobileGetList")
    Call<TechnicalGuideResponse> searchTechnicalGuide(@Query("Name") String keySearch, @Query("SkipCount") int skipCount, @Query("MaxResultCount") int maxResultCount);

    @POST("Appointment/MobilePost")
    Call<PostAppointmentResponse> makeAppointment(@Body Map<String, Object> params);

    @GET("GuideBook/MobileContentGetList")
    Call<GuildBookItemDetailResponse> getDetailGuideBook(@Query("Id") int id);

    @POST("Survey/MobilePostSurveyAnswer")
    Call<SimpleResponse> postSurvey(@Body Map<String, Object> params);

    @GET("Survey/MobileCheckSurvey")
    Call<SimpleResponse> checkSurveyInValid(@Query("_token") String token);

    @GET
    Call<NewsFeedResponse> getFacebookApi(@Url String url);

    @GET
    Call<YoutubeResponse> getYoutubeApi(@Url String url);

    @GET("Car/MobileGetDetail")
    Call<DetailCarResponse> getDetailCar(@Query("Id") int id);


    @GET("QuestionRecommendCar/MobileGetListQuestion")
    Call<SelectCarResponse> getListRecommendQuestion();

    @GET("Car/MobileGetMyRightCars")
    Call<CarsResponse> getListSelectCar(@Query("multipleChoiceAnswers") String question);

    @GET("Car/MobileGetCategoryList")
    Call<CarCategoryResponse> getListCategory();

    @POST("Car/MobileGetListAndFilter")
    Call<CarFilterResponse> filterCars(@Body Map<String, Object> params);

    @POST("TokenAuth/LogOut")
    Call<SimpleResponse> logout();
}
