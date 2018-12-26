package com.android.lib;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.android.lib.http.ApiClient;
import com.android.lib.http.ApiDownloadInterface;
import com.android.lib.http.ApiInterface;
import com.android.lib.http.DialogHandler;
import com.android.lib.http.IRequestListener;
import com.android.lib.http.ResponseCallback;
import com.android.lib.model.ProgressListener;
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
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;

import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Provides APIs for working with personal storage
 * Created by ThinhNH on 20/08/2016.
 */
public class RESTManager {

    //            public static final String BASE_URL = "http://14.160.64.50:21021/";
    public static final String BASE_URL = "http://192.168.50.132:8989/";
    public static final String API_URL = BASE_URL + "api/v1/";

    private static RESTManager mInstance;

    private Context mContext;
    private ApiInterface mApiService;

    /**
     * Private constructor
     */
    private RESTManager() {

    }

    /**
     * Getting a instance of {@link RESTManager} class
     * *
     */
    public static RESTManager getInstance() {
        if (mInstance == null) {
            mInstance = new RESTManager();
        }
        return mInstance;
    }

    /**
     * Function is used to initialzed necessary attribute for this class
     * Only need to call when first time create instance or need to update information
     *
     * @param context context that is used for create HttpExecutor
     */
    public void init(@NonNull Context context) {
        this.mContext = context;

        mApiService =
                ApiClient.getClient().create(ApiInterface.class);

        DialogHandler.setCurrentAct((Activity) context);

//        trustEveryone();
    }

    public Context getContext() {
        return mContext;
    }

    //================================APIS====================================================

    public void login(String phone, String deviceId, String terminal, IRequestListener<SimpleResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("Phone", phone);
        params.put("DeviceToken", deviceId);
        params.put("Terminal", terminal);

        Call<SimpleResponse> call = mApiService.login(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void verifyLogin(String phone, String code, IRequestListener<LoginResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("phone", phone);
        params.put("code", code);

        Call<LoginResponse> call = mApiService.verifyLogin(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void addCard(String cardNumber, IRequestListener<CardResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("cardNumber", cardNumber);

        Call<CardResponse> call = mApiService.addCard(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void feedback(String fullName, String phone, String email, String licensePlate,
                         String feedbackType, String title, String content,
                         int carId, boolean isUpdateProfile, boolean isAddNewCar, IRequestListener<SimpleResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("fullName", fullName);
        params.put("phone", phone);
        params.put("email", email);
        params.put("licensePlate", licensePlate);
        params.put("feedbackType", feedbackType);
        params.put("title", title);
        params.put("content", content);
        params.put("carId", carId);
        params.put("isUpdate", isUpdateProfile);
        params.put("isUpdateCar", isAddNewCar);

        Call<SimpleResponse> call = mApiService.feedback(params);
        call.enqueue(new ResponseCallback<>(listener));
    }


    public void getProfile(int id, IRequestListener<LoginResponse> listener) {
        Call<LoginResponse> call = mApiService.getProfile(id);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void updateSetting(boolean notification, boolean newsfeed, IRequestListener<UpdateResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("isNewsfeed", notification);
        params.put("isNotification", newsfeed);
        Call<UpdateResponse> call = mApiService.updateSetting(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getWarrantyPolicy(IRequestListener<WarrantyPolicyResponse> listener) {
        Call<WarrantyPolicyResponse> call = mApiService.getListWarrantyPolicy();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getTerm(IRequestListener<TermResponse> listener) {
        Call<TermResponse> call = mApiService.getTerm();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getEndow(
            int skipCount,
            int maxResultCount,
            String transactionCode,
            String cardNumber,
            String programCode,
            String programName,
            String fromDate,
            String toDate,
            IRequestListener<HistoryEndowResponse> listener
    ) {
        Call<HistoryEndowResponse> call = mApiService.getEndow(skipCount, maxResultCount, transactionCode, cardNumber, programCode, programName, fromDate, toDate);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getUsePoint(
            int skipCount,
            int maxResultCount,
            String transactionCode,
            String cardNumber,
            String fromDate,
            String toDate,
            IRequestListener<HistoryUsePointResponse> listener
    ) {
        Call<HistoryUsePointResponse> call = mApiService.getUsePoint(skipCount, maxResultCount, transactionCode, cardNumber, fromDate, toDate);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getGradePoint(
            int skipCount,
            int maxResultCount,
            String transactionCode,
            String cardNumber,
            String fromDate,
            String toDate,
            IRequestListener<HistoryPointResponse> listener
    ) {
        Call<HistoryPointResponse> call = mApiService.getPoint(skipCount, maxResultCount, transactionCode, cardNumber, fromDate, toDate);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void updateProfile(String name, String birthday, String identityCard, String email, String address, IRequestListener<SimpleResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("name", name);
        params.put("birthday", birthday);
        params.put("email", email);
        params.put("identityId", identityCard);
        params.put("address", address);
        Call<SimpleResponse> call = mApiService.updateProfile(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getNotification(int skipCount, int maxResultCount, IRequestListener<NotificationResponse> listener) {
        Call<NotificationResponse> call = mApiService.getNotification(skipCount, maxResultCount);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void setRead(long id, IRequestListener<NotificationDetailResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("id", id);
        Call<NotificationDetailResponse> call = mApiService.setRead(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getNotificationDetail(long id, IRequestListener<NotificationDetailResponse> listener) {
        Call<NotificationDetailResponse> call = mApiService.getNotificationDetail(id);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getSetting(IRequestListener<SettingResponse> listener) {
        Call<SettingResponse> call = mApiService.getSetting();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void updateAvatar(String pathAvatar, IRequestListener<SimpleResponse> listener) {
        File file = new File(pathAvatar);
        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part multipart = MultipartBody.Part.createFormData("File", file.getName(), requestBody);
        Call<SimpleResponse> call = mApiService.updateAvatar(multipart);
        call.enqueue(new ResponseCallback<>(listener));
    }


    public void addCar(int customerId, int id, int carId, String vinno, String licensePlate, IRequestListener<ActionCarResponse> listener) {
        Map<String, Object> params = createParams();
        if (id != -1) {
            params.put("id", id);
        }
        params.put("customerId", customerId);
        params.put("carId", carId);
        params.put("vinno", vinno);
        params.put("licensePlate", licensePlate);
        params.put("agencyId", null);
        params.put("purchaseDate", null);
        Call<ActionCarResponse> call = mApiService.addCar(params);
        call.enqueue(new ResponseCallback<>(listener));
    }


    public void registerTestDrive(int carId, int cityId, int agencyId, String name, String phone, String email, String note, boolean isUpdateProfile, IRequestListener<RegisterTestDriveResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("carId", carId);
        params.put("cityId", cityId);
        params.put("agencyId", agencyId);
        params.put("name", name);
        params.put("phone", phone);
        params.put("email", email);
        params.put("note", note);
        params.put("isUpdate", isUpdateProfile);
        Call<RegisterTestDriveResponse> call = mApiService.registerTestDrive(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void deleteCard(int id, IRequestListener<SimpleResponse> listener) {
        Call<SimpleResponse> call = mApiService.deleteCard(id);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void deleteCar(int id, IRequestListener<ActionCarResponse> listener) {
        Call<ActionCarResponse> call = mApiService.deleteCar(id);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getAgencies(IRequestListener<AgenciesResponse> listener) {
        Call<AgenciesResponse> call = mApiService.getAgencies();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getAgenciesById(int cityId, String name, int type, double _lat, double _long, IRequestListener<AgenciesResponse> listener) {
        Call<AgenciesResponse> call = mApiService.getAgenciesById(cityId, name, type, _lat, _long);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void postCarVersion(IRequestListener<CarVersionNameResponse> listener) {
        Call<CarVersionNameResponse> call = mApiService.postCarVersion();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getAgenciesNearBy(double _lat, double _long, int type, IRequestListener<AgenciesResponse> listener) {
        Call<AgenciesResponse> call = mApiService.getAgenciesNearBy(_lat, _long, type);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getCities(IRequestListener<CityResponse> listener) {
        Call<CityResponse> call = mApiService.getCities();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getCars(IRequestListener<CarsResponse> listener) {
        Call<CarsResponse> call = mApiService.getListCar(false);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListFacebookGroup(IRequestListener<FacebookGroupResponse> listener) {
        Call<FacebookGroupResponse> call = mApiService.getListFacebookGroup();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getCars(boolean isFull, IRequestListener<CarsResponse> listener) {
        Call<CarsResponse> call = mApiService.getListCar(isFull);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getCar(int carId, IRequestListener<DetailCarResponse> listener) {
        Call<DetailCarResponse> call = mApiService.getCar(carId);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListCarVersion(int carId, IRequestListener<CarVersionResponse> listener) {
        Call<CarVersionResponse> call = mApiService.getListCarVersion(carId);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListArea(IRequestListener<AreaResponse> listener) {
        Call<AreaResponse> call = mApiService.getListArea();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListPrice(int versionId, int areaId, IRequestListener<PriceAdviceResponse> listener) {
        Call<PriceAdviceResponse> call = mApiService.getListPrice(versionId, areaId);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListMaintenanceLevel(IRequestListener<MaintenanceLevelResponse> listener) {
        Call<MaintenanceLevelResponse> call = mApiService.getListMaintenanceLevel();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void postMaintenanceCarGetDetail(String carVersionName, int agencyId, int maintenanceLevelId, IRequestListener<MaintenanceDetailResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("carVersionName", carVersionName);
        params.put("agencyId", agencyId);
        params.put("maintenanceLevelId", maintenanceLevelId);
        Call<MaintenanceDetailResponse> call = mApiService.postMaintenanceCarGetDetail(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getCardList(IRequestListener<CardListResponse> listener) {
        Call<CardListResponse> call = mApiService.getCardList();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getPromotionList(IRequestListener<PromotionListResponse> listener) {
        Call<PromotionListResponse> call = mApiService.getPromotionList();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListAppointment(IRequestListener<AppointmentResponse> listener) {
        Call<AppointmentResponse> call = mApiService.getListAppointment();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getHTCList(int skipCount, int maxResultCount, IRequestListener<NewsListResponse> listener) {
        Call<NewsListResponse> call = mApiService.getHTCList(skipCount, maxResultCount);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getProductList(int skipCount, int maxResultCount, IRequestListener<NewsListResponse> listener) {
        Call<NewsListResponse> call = mApiService.getProductList(skipCount, maxResultCount);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getGroupNews(IRequestListener<GroupNewsResponse> listener) {
        Call<GroupNewsResponse> call = mApiService.getGroupNews();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListNewsHome(int id, int skipCount, int maxResultCount,
                                IRequestListener<NewsListResponse> listener) {
        Call<NewsListResponse> call = mApiService.getListNewsHome(id, skipCount, maxResultCount);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getNewsDetail(int id, IRequestListener<NewsResponse> listener) {
        Call<NewsResponse> call = mApiService.getNewsDetail(id);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getSurvey(String phoneNumber, IRequestListener<SurveyResponse> listener) {
        Call<SurveyResponse> call = mApiService.getSurvey(phoneNumber);
        call.enqueue(new ResponseCallback<>(listener));
    }


    public void getListTechnicalGuide(int skipCount, int maxResultCount, IRequestListener<TechnicalGuideResponse> listener) {
        Call<TechnicalGuideResponse> call = mApiService.getListTechnicalGuide(skipCount, maxResultCount);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListGuideBook(int skipCount, int maxResultCount, String keySearch, int carId, IRequestListener<GuideBookResponse> listener) {
        Call<GuideBookResponse> call = mApiService.getListGuideBook(skipCount, maxResultCount, keySearch, carId);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void searchTechnicalGuide(String keySearch, int skipCount, int maxResultCount, IRequestListener<TechnicalGuideResponse> listener) {
        Call<TechnicalGuideResponse> call = mApiService.searchTechnicalGuide(keySearch, skipCount, maxResultCount);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void makeAppointment(String wrapDataAppointment, IRequestListener<PostAppointmentResponse> listener) {
        try {
            JSONObject obj = new JSONObject(wrapDataAppointment);
            Map<String, Object> params = createParams();
            params.put("customerName", obj.getString("customerName"));
            params.put("customerEmail", obj.getString("customerEmail"));
            params.put("customerPhone", obj.getString("customerPhone"));
            params.put("customerNote", obj.getString("customerNote"));
            params.put("dateAppointment", LibUtils.revertFormatDateMakeAppointment(obj.getString("dateAppointment")));
            params.put("staffName", obj.getString("staffName"));
            params.put("carId", obj.getInt("carId"));
            params.put("cityId", obj.getInt("cityId"));
            params.put("agencyId", obj.getInt("agencyId"));
            params.put("honorifics", obj.getString("honorifics"));
            params.put("serviceName", obj.getString("serviceName"));
            params.put("licensePlates", obj.getString("licensePlates"));
            params.put("versionName", obj.getString("versionName"));
            params.put("isUpdate", obj.getBoolean("isUpdateProfile"));
            params.put("isUpdateCar", obj.getBoolean("isAddNewCar"));

            Call<PostAppointmentResponse> call = mApiService.makeAppointment(params);
            call.enqueue(new ResponseCallback<>(listener));

        } catch (Exception e) {
            listener.onFailed(e);
        }
    }


    public void getDetailGuideBook(int id, IRequestListener<GuildBookItemDetailResponse> listener) {
        Call<GuildBookItemDetailResponse> call = mApiService.getDetailGuideBook(id);
        call.enqueue(new ResponseCallback<>(listener));
    }


    public void postSurvey(String token, List<String> answers, IRequestListener<SimpleResponse> listener) {
        Map<String, Object> params = createParams();
        params.put("token", token);
        params.put("device", "App");
        params.put("answers", answers);
        Call<SimpleResponse> call = mApiService.postSurvey(params);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void checkSurveyInvalid(String token, IRequestListener<SimpleResponse> listener) {
        Call<SimpleResponse> call = mApiService.checkSurveyInValid(token);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getFacebookApi(String url, IRequestListener<NewsFeedResponse> listener) {
        Call<NewsFeedResponse> call = mApiService.getFacebookApi(url);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getYoutubeApi(String url, IRequestListener<YoutubeResponse> listener) {
        Call<YoutubeResponse> call = mApiService.getYoutubeApi(url);
        call.enqueue(new ResponseCallback<>(listener));
    }


    public void getDetailCar(int id, IRequestListener<DetailCarResponse> listener) {
        Call<DetailCarResponse> call = mApiService.getDetailCar(id);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void downloadCatalog(String url, ProgressListener listener, Callback<ResponseBody> callback) {
        Call<ResponseBody> call = ApiClient.getClientDownload(listener).create(ApiDownloadInterface.class).downloadCatalog(url);
        call.enqueue(callback);
    }

    public void downloadCalculateInterestRate(double borrow,
                                              int numberMonth,
                                              double interestRate1,
                                              double interestRate2,
                                              ProgressListener listener,
                                              Callback<ResponseBody> callback) {
        Call<ResponseBody> call = ApiClient.getClientDownload(listener).create(ApiDownloadInterface.class).downloadCalculateInterest(borrow, numberMonth, interestRate1, interestRate2);
        call.enqueue(callback);
    }


    public void getListRecommendQuestion(IRequestListener<SelectCarResponse> listener) {
        Call<SelectCarResponse> call = mApiService.getListRecommendQuestion();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListCarSelect(String question, IRequestListener<CarsResponse> listener) {
        Call<CarsResponse> call = mApiService.getListSelectCar(question);
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void getListCategory(IRequestListener<CarCategoryResponse> listener) {
        Call<CarCategoryResponse> call = mApiService.getListCategory();
        call.enqueue(new ResponseCallback<>(listener));
    }

    public void filterCars(Boolean moreThan500M, Boolean moreThan1500ml, Integer gear, IRequestListener<CarFilterResponse> listener) {
        Map<String, Object> params = createParams();
        if (moreThan500M != null) params.put("priceMoreThan500M", moreThan500M);
        if (moreThan1500ml != null) params.put("capacityMoreThan1500ml", moreThan1500ml);
        if (gear != null) params.put("gear", gear);
        Call<CarFilterResponse> call = mApiService.filterCars(params);
        call.enqueue(new ResponseCallback<>(listener));
    }


    public void logout() {
        Call<SimpleResponse> call = mApiService.logout();
        call.enqueue(new ResponseCallback<>(null));
    }


    //===========================================PRIVATE FUNCTION
    // ============================================

    private Map<String, Object> createParams() {
        Map<String, Object> params = new HashMap<>();

//        LoginResponse loginResponse = LibLocalDataManager.getInstance().getLoginResponse();
//        if (loginResponse != null) {
//            params.put(PARAM_TOKEN, "");
//            if (loginResponse.isIsKhachHang()) {
//                params.put(PARAM_USER_CODE, loginResponse.getUserID());
//            } else {
//                params.put(PARAM_USER_CODE, "");
//            }
//        }

        return params;
    }

//    private final boolean isCustomer() {
//        boolean isCustomer = false;
//
//        LoginResponse loginResponse = LibLocalDataManager.getInstance().getLoginResponse();
//        if (loginResponse != null) {
//            isCustomer = loginResponse.isIsKhachHang();
//        }
//
//        return isCustomer;
//    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    Logger.d("hostname: " + hostname);
                    Logger.d("session: " + session.isValid());
                    if (hostname.equalsIgnoreCase(getDomainName())
                            || hostname.equalsIgnoreCase("api.crashlytics.com")
                            || hostname.equalsIgnoreCase("settings.crashlytics.com")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    public static String getDomainName() {
        String domain = "";
        try {
            URI uri = new URI(BASE_URL);
            domain = uri.getHost();
            domain = domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return domain;
    }

    public static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }
}
