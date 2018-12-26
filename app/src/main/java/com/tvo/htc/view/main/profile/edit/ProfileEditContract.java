package com.tvo.htc.view.main.profile.edit;

import com.android.lib.model.response.CityResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface ProfileEditContract {
    interface View extends BaseContract.View {
        void showConfirmSaveDialog();

        void displayInfo(String imagePath,
                         String name,
                         String s,
                         String formatBirthday,
                         String identityCard,
                         String emailAddress,
                         List<CityResponse.City> cities,
                         int positionOfCity);

        void finish();

        void showWaitDialog(boolean isShowing);

        void showErrorEmailValid();

        void showErrorLogin();

        void showMessageError(String message);

        void showErrorEmptyName();

        void showErrorIdentifyIdValid();

        void showErrorBirthdayEmpty();

        void showErrorNameValid();

        void goToHome();

        void showErrorBirthdayValidate();

        void showError(String message);

        void showErrorAddress();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void changePathAvatar(String path);

        void handleSaveProfile(String name, String phone, String dayOfBirth, String identifyId, String email, int positionCity, boolean isBackPress);

        void loadData(int type);

        void handleFinish();
    }
}
