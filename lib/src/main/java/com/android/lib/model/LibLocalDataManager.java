package com.android.lib.model;

import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SettingResponse;

/**
 * The class used for managing local data
 * Created by ThinhNH on 25/08/2016.
 */
public class LibLocalDataManager {
    private static LibLocalDataManager sInstance;

    private LoginResponse loginResponse;

    private SettingResponse.Setting settingResponse;

    private String accessToken;

    private LibLocalDataManager() {
    }

    public static LibLocalDataManager getInstance() {
        if (sInstance == null) {
            sInstance = new LibLocalDataManager();
        }
        return sInstance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    /***********************************************************************************************
     * API
     ***********************************************************************************************/
    public String setAccessToken(String accessToken) {
        return this.accessToken = accessToken;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public SettingResponse.Setting getSettingResponse() {
        return settingResponse;
    }

    public void setSettingResponse(SettingResponse.Setting settingResponse) {
        this.settingResponse = settingResponse;
    }
}
