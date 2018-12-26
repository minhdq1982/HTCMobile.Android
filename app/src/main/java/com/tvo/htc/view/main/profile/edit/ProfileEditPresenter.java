package com.tvo.htc.view.main.profile.edit;

import android.content.Context;
import android.util.Pair;

import com.android.lib.RESTManager;
import com.android.lib.http.ErrorException;
import com.android.lib.http.HTTPRequestOption;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.CityResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SimpleResponse;
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.model.event.EventSaveProfileSuccess;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.RealFilePath;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import static com.tvo.htc.view.main.profile.edit.ProfileEditFragment.TYPE_CONFIRM_LOGIN;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class ProfileEditPresenter extends BasePresenter<ProfileEditContract.View> implements ProfileEditContract.Presenter {
    public ProfileEditPresenter(Context context) {
        super(context);
        mLoginResponse = LocalDataManager.getInstance(getContext()).getLoginResponse();
    }

    private String mPathAvatar;
    private LoginResponse mLoginResponse;
    private List<CityResponse.City> mListCity;
    private int countUpdateProfile = 0;
    private int mType;

    @Override
    public void loadData(int type) {
        mType = type;
        if (mLoginResponse != null && mLoginResponse.getData() != null) {
            loadCity();
        }
    }

    @Override
    public void changePathAvatar(String path) {
        mPathAvatar = RealFilePath.revertFormatPath(path);
    }

    @Override
    public void handleSaveProfile(String name, String phone, String dayOfBirth, String identifyId, String email, int positionCity, boolean isBackPress) {
        name = Utils.normalizeString(name, false);
        dayOfBirth = Utils.normalizeString(dayOfBirth, false);
        identifyId = Utils.normalizeString(identifyId, false);
        email = Utils.normalizeString(email, false);

        Pair<Boolean, Boolean> pairChange = hasChangeInfo(mPathAvatar, name, dayOfBirth, identifyId, email, positionCity);
        if (pairChange.first || pairChange.second) {
            if (isBackPress) {
                getView().showConfirmSaveDialog();
            } else {
                getView().showWaitDialog(true);
                if (pairChange.second) {
                    saveInfo(name, dayOfBirth, identifyId, email, positionCity);
                }
                if (pairChange.first) {
                    saveAvatar(mPathAvatar);
                }
            }
        } else {
            if (isBackPress) {
                handleFinish();
            } else {
                // Not input info and tap save info
                getView().showWaitDialog(true);
                saveInfo(name, dayOfBirth, identifyId, email, positionCity);
            }
        }
    }

    @Override
    public void handleFinish() {
        switch (mType) {
            case TYPE_CONFIRM_LOGIN:
                getView().goToHome();
                break;
            default:
                getView().finish();
        }
    }

    private void saveAvatar(String pathOfAvatar) {
        countUpdateProfile++;
        RESTManager.getInstance().updateAvatar(
                pathOfAvatar,
                new IRequestListener<SimpleResponse>(new HTTPRequestOption(true, false)) {
                    @Override
                    public void onCompleted(SimpleResponse data) {
                        super.onCompleted(data);
                        handleSuccess();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        super.onFailed(throwable);
                        Logger.e("Error upload avatar");
                        handleError();
                    }
                });
    }

    private void saveInfo(String name, String dayOfBirth, String identifyId, String
            email, int positionCity) {
        //todo check invalid field
//        if (name.isEmpty()) {
//            handleError();
//            getView().showErrorEmptyName();
//            return;
//        }

        if (!name.isEmpty() && !Utils.isValidName(name)) {
            handleError();
            getView().showErrorNameValid();
            return;
        }

        if (!email.isEmpty() && !Utils.isValidEmail(email)) {
            handleError();
            getView().showErrorEmailValid();
            return;
        }

        if (!identifyId.isEmpty() && !Utils.isValidIdentifyId(identifyId)) {
            handleError();
            getView().showErrorIdentifyIdValid();
            return;
        }

        if (!dayOfBirth.isEmpty() && !dayOfBirth.equals("dd/mm/yyyy") && !LibUtils.isValidTime(dayOfBirth)) {
            handleError();
            getView().showErrorBirthdayValidate();
            return;
        }

//        if (positionCity == 0) {
//            handleError();
//            getView().showErrorAddress();
//            return;
//        }
        String address = "";
        if (positionCity != 0) {
            address = mListCity.get(positionCity).getName();
        }

        countUpdateProfile++;
        RESTManager.getInstance().updateProfile(name, LibUtils.revertFormatTime(dayOfBirth), identifyId, email, address, new IRequestListener<SimpleResponse>(new HTTPRequestOption(true, false)) {
            @Override
            public void onCompleted(SimpleResponse data) {
                super.onCompleted(data);
                handleSuccess();
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
                Logger.e("Error update profile: " + throwable.toString());
                handleError();
            }
        });
    }

    private void handleSuccess() {
        countUpdateProfile--;
        Logger.e("Count update-->" + countUpdateProfile);
        if (countUpdateProfile == 0) {
            RESTManager.getInstance().getProfile(mLoginResponse.getData().getId(), new IRequestListener<LoginResponse>(new HTTPRequestOption(true, false)) {
                @Override
                public void onCompleted(LoginResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.isSuccess()) {
                        LocalDataManager.getInstance(getContext()).saveLoginResponse(data);
                        getView().showWaitDialog(false);
                        handleFinish();
                        EventBusUtils.postEvent(new EventSaveProfileSuccess());
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                    handleFinish();
                }
            });
        }
    }

    private void handleError() {
        countUpdateProfile = 0;
        getView().showWaitDialog(false);
    }

    private Pair<Boolean, Boolean> hasChangeInfo(String pathAvatar,
                                                 String name,
                                                 String birthday,
                                                 String identifyId,
                                                 String email,
                                                 int positionCity) {
        if (getContext() == null || mLoginResponse == null || mLoginResponse.getData() == null)
            return Pair.create(false, false);
        LoginResponse.Data data = mLoginResponse.getData();
        boolean isChangeAvatar = pathAvatar != null;
        boolean isChangeInfo = hasModifier(data.getName(), name) ||
                hasModifier(LibUtils.getFormatBirthday(data.getBirthday()), birthday) ||
                hasModifier(data.getIdentityCard(), identifyId) ||
                hasModifier(data.getEmailAddress(), email) ||
                (positionCity != 0 && hasModifier(data.getAddress(), mListCity.get(positionCity).getName()));

        return Pair.create(isChangeAvatar, isChangeInfo);
    }


    private boolean hasModifier(String s1, String s2) {
        if (s1 == null && (s2 == null || s2.isEmpty())) return false;
        if (s1 != null && s2 != null) return !s1.equals(s2);
        return true;
    }

    private void loadCity() {
        if (SessionDataManager.getInstance().getCities() != null && !SessionDataManager.getInstance().getCities().isEmpty()) {
            mListCity = new ArrayList<>(SessionDataManager.getInstance().getCities());
            updateDisplayProfile();
        } else {
            RESTManager.getInstance().getCities(new IRequestListener<CityResponse>(
                    new HTTPRequestOption(false, true)) {
                @Override
                public void onCompleted(CityResponse data) {
                    super.onCompleted(data);
                    if (data != null && data.isSuccess() && data.getData() != null) {
                        mListCity = new ArrayList<>(data.getData());
                        SessionDataManager.getInstance().setCities(data.getData());
                        updateDisplayProfile();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    if (throwable instanceof ErrorException) {
                        ErrorException errorException = (ErrorException) throwable;
                        getView().showError(errorException.getMessage());
                        return;
                    }
                    super.onFailed(throwable);
                }
            });
        }
    }

    private void updateDisplayProfile() {
        mListCity.add(0, new CityResponse.City(getContext().getString(R.string.profile_edit_hint_address)));
        LoginResponse.Data dataProfile = mLoginResponse.getData();
        getView().displayInfo(
                Utils.getImagePath(dataProfile.getAvatar()),
                dataProfile.getName(),
                dataProfile.getPhoneNumber(),
                LibUtils.getFormatBirthday(dataProfile.getBirthday()),
                dataProfile.getIdentityCard(),
                dataProfile.getEmailAddress(),
                mListCity,
                filterPositionCity(dataProfile.getAddress()));
    }

    private int filterPositionCity(String address) {
        for (int i = 0; i < mListCity.size(); i++) {
            if (mListCity.get(i).getName().equalsIgnoreCase(address)) {
                return i;
            }
        }
        return -1;
    }

}
