package com.tvo.htc.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.android.lib.model.LibLocalDataManager;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SettingResponse;
import com.android.lib.util.Logger;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * The class used for managing local data
 * Created by ThinhNH on 25/08/2016.
 */
public class LocalDataManager {

    public static final String PREF_LOGIN_RESPONSE = "PREF_LOGIN_RESPONSE";
    private static final String PREF_ACCEPT_TERM_CONDITIONAL = "PREF_ACCEPT_TERM_CONDITIONAL";

    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static final String PREF_IS_SHOW_HOME_LOGIN = "PREF_IS_SHOW_HOME_LOGIN";
    private static final String PREF_DEVICE_ID = "PREF_DEVICE_ID";

    private static final String PREF_SUFFIX_SURVEY = "PREFIX_SUFFIX_SURVEY_";

    private static final String PREF_UNREAD_NOTIFICATION = "PREF_UNREAD_NOTIFICATION";

    public static final String PREF_SETTING_RESPONSE = "PREF_SETTING_RESPONSE";


    private static LocalDataManager sInstance;

    private Gson gson = new Gson();
    private SharedPreferences mPreferences;

    private Context mContext;

    private LoginResponse mLoginResponse;
    private String accessToken;
    private String mDeviceID;
    private boolean isShowHomeLogin = true;

    private SettingResponse.Setting mSettingResponse;

    private LocalDataManager(Context context) {
        this.mContext = context.getApplicationContext();
        if (mContext == null) {
            throw new NullPointerException("context is null");
        }
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
    }

    public static LocalDataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LocalDataManager(context);
        }
        return sInstance;
    }

    /**
     * Register event--->
     */
    public void registerEvent(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Un register event--->
     */
    public void unregisterEvent(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Check term & conditional accepted
     */
    public boolean hasAcceptedTermConditional() {
        return mPreferences.getBoolean(PREF_ACCEPT_TERM_CONDITIONAL, false);
    }

    public void saveAcceptedTermConditional() {
        mPreferences.edit().putBoolean(PREF_ACCEPT_TERM_CONDITIONAL, true).apply();
    }
    /***********************************************************************************************
     * API
     ***********************************************************************************************/

    /**
     * Access token
     */
    public void saveAccessToken(String access) {
        this.accessToken = access;
        mPreferences.edit().putString(PREF_ACCESS_TOKEN, access).commit();
        LibLocalDataManager.getInstance().setAccessToken(accessToken);
    }

    public String getAccessToken() {
        if (accessToken == null) {
            accessToken = mPreferences.getString(PREF_ACCESS_TOKEN, null);
        }
        return accessToken;
    }

    public void saveIsShowHomeLogin(boolean isShowHomeLogin) {
        this.isShowHomeLogin = isShowHomeLogin;
        mPreferences.edit().putBoolean(PREF_IS_SHOW_HOME_LOGIN, isShowHomeLogin).commit();
    }

    public boolean isShowHomeLogin() {
        if (isShowHomeLogin) {
            isShowHomeLogin = mPreferences.getBoolean(PREF_IS_SHOW_HOME_LOGIN, true);
        }
        return isShowHomeLogin;
    }

    public int getUnreadNotification() {
        return mPreferences.getInt(PREF_UNREAD_NOTIFICATION, 0);
    }

    public void saveUnreadNotification(int number) {
        mPreferences.edit().putInt(PREF_UNREAD_NOTIFICATION, number).commit();
    }

    public int plusUnreadNotification() {
        return mPreferences.getInt(PREF_UNREAD_NOTIFICATION, 0) + 1;
    }

    /**
     * Login
     */

    public void saveLoginResponse(LoginResponse loginResponse) {
        mLoginResponse = loginResponse;
        String encodedData = gson.toJson(loginResponse);
        mPreferences.edit().putString(PREF_LOGIN_RESPONSE, encodedData).commit();
        LibLocalDataManager.getInstance().setLoginResponse(loginResponse);
    }

    public LoginResponse getLoginResponse() {
        if (mLoginResponse != null) {
            return mLoginResponse;
        }

        String encodedData = mPreferences.getString(PREF_LOGIN_RESPONSE, null);
        if (encodedData != null) {
            mLoginResponse = gson.fromJson(encodedData, LoginResponse.class);
        }

        return mLoginResponse;
    }

    public void updateProfile(String name, String email) {
        if (mLoginResponse == null) getLoginResponse();
        if (mLoginResponse != null && mLoginResponse.getData() != null) {
            mLoginResponse.getData().setName(name);
            mLoginResponse.getData().setEmailAddress(email);
            saveLoginResponse(mLoginResponse);
        }
    }

    public void updateCar(int carId, String carName, String licensePlates) {
        if (carId==-1 || carName.isEmpty() || licensePlates.isEmpty()) return;
        if (mLoginResponse == null) getLoginResponse();
        if (mLoginResponse != null && mLoginResponse.getData() != null) {
            List<LoginResponse.Data.Car> cars = new ArrayList<>();
            cars.add(new LoginResponse.Data.Car(carId, carName, licensePlates));

            if (mLoginResponse.getData().getCars() == null) {
                mLoginResponse.getData().setCars(cars);
            } else {
                mLoginResponse.getData().getCars().addAll(cars);
            }
        }
    }

    public void saveSetting(SettingResponse.Setting settingResponse) {
        mSettingResponse = settingResponse;
        String encodedData = gson.toJson(settingResponse);
        mPreferences.edit().putString(PREF_SETTING_RESPONSE, encodedData).apply();
        LibLocalDataManager.getInstance().setSettingResponse(settingResponse);
    }

    public SettingResponse.Setting getSetting() {
        if (mSettingResponse != null) {
            return mSettingResponse;
        }

        String encodedData = mPreferences.getString(PREF_SETTING_RESPONSE, null);
        if (encodedData != null) {
            mSettingResponse = gson.fromJson(encodedData, SettingResponse.Setting.class);
        }

        return mSettingResponse;
    }

    public void logout() {
        saveLoginResponse(null);
        saveAccessToken("");
    }

    public void saveDeviceID(String deviceID) {
        mDeviceID = deviceID;
        mPreferences.edit().putString(PREF_DEVICE_ID, deviceID).commit();
    }

    public String getDeviceID() {
        if (mDeviceID == null) {
            mDeviceID = mPreferences.getString(PREF_DEVICE_ID, null);
        }
        return mDeviceID;
    }

    // save pref survey
    public void saveSurvey(@NonNull SaveSurvey saveSurvey) {
        String encode = new Gson().toJson(saveSurvey);
        Logger.e("JSON: " + encode);
        mPreferences.edit().putString(PREF_SUFFIX_SURVEY + saveSurvey.ordinalType, encode).commit();
    }

    public SaveSurvey getSurvey(int ordinalType) {
        String encode = mPreferences.getString(PREF_SUFFIX_SURVEY + ordinalType, "");
        if (!encode.isEmpty()) {
            return new Gson().fromJson(encode, SaveSurvey.class);
        }
        return null;
    }

    public void deleteSurvey(int ordinalType) {
        mPreferences.edit().putString(PREF_SUFFIX_SURVEY + ordinalType, null).commit();
    }
}
