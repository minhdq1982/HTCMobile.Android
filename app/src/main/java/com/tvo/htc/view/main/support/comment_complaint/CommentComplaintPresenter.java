package com.tvo.htc.view.main.support.comment_complaint;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.Car;
import com.android.lib.model.response.CarsResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SimpleResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.AppConstants;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.BasePresenter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class CommentComplaintPresenter extends BasePresenter<CommentComplaintContract.View> implements CommentComplaintContract.Presenter {

    private LoginResponse.Data mUserData;

    private List<Car> mListSelectCar;
    private List<LoginResponse.Data.Car> mProfileCar;

    public CommentComplaintPresenter(Context context) {
        super(context);
        mUserData = LocalDataManager.getInstance(getContext()).getLoginResponse().getData();
    }

    @Override
    public void loadData() {
        loadInfoProfile();
        loadListCar();
    }

    @Override
    public void sentCommentComplaint(String fullName,
                                     String phone,
                                     String email,
                                     String licensePlate,
                                     int feedbackType,
                                     String title,
                                     String content,
                                     boolean isUpdateProfile,
                                     int useCarPosition,
                                     int selectCarPosition,
                                     boolean isUseCar,
                                     boolean isAddNewCar) {
        //Check empty
        if (fullName.isEmpty() ||
                phone.isEmpty() ||
                email.isEmpty() ||
                title.isEmpty() ||
                content.isEmpty()) {
            getView().displayErrorEmpty(getContext().getString(R.string.support_comment_complaint_tips));
        } else if (feedbackType == 0) {
            getView().displayErrorEmpty(getContext().getString(R.string.support_comment_complaint_tips_choose_problems));
        } else {
            if (!Utils.isValidName(fullName)) {
                getView().displayErrorEmpty(getContext().getString(R.string.make_appointment_error_name));
                return;
            }
            if (!Utils.isValidPhoneNumber(phone)) {
                getView().displayErrorEmpty(getContext().getString(R.string.make_appointment_error_phone));
                return;
            }
            if (!Utils.isValidEmail(email)) {
                getView().displayErrorEmpty(getContext().getString(R.string.make_appointment_error_email));
                return;
            }
            if (!isUseCar && !Utils.isValidLicensePlates(licensePlate)) {
                getView().displayErrorEmpty(getContext().getString(R.string.add_car_error_licensePlate));
                return;
            }

            int carId = -1;
            String carName = "";
            if (isUseCar && mProfileCar != null && useCarPosition != 0 && useCarPosition < mProfileCar.size()) {
                carId = mProfileCar.get(useCarPosition).getCarID();
                licensePlate = mProfileCar.get(useCarPosition).getLicensePlate();
                isAddNewCar = false;
            } else if (mListSelectCar != null && selectCarPosition != 0 && selectCarPosition < mListSelectCar.size()) {
                carId = mListSelectCar.get(selectCarPosition).getId();
                carName = mListSelectCar.get(selectCarPosition).getName();
            } else {
                isAddNewCar = false;
            }

            //copy field to update
            boolean finalIsAddNewCar = isAddNewCar;
            int finalCarId = carId;
            String finalCarName = carName;
            String finalLicensePlate = licensePlate;

            RESTManager.getInstance().feedback(
                    fullName,
                    phone,
                    email,
                    licensePlate,
                    String.valueOf(feedbackType),
                    title,
                    content,
                    carId,
                    isUpdateProfile,
                    isAddNewCar,
                    new IRequestListener<SimpleResponse>() {
                        @Override
                        public void onCompleted(SimpleResponse data) {
                            super.onCompleted(data);
                            if (getView() != null) {
                                getView().displaySuccessSent(data.getMessage());
                                if (isUpdateProfile)
                                    LocalDataManager.getInstance(getContext()).updateProfile(fullName, email);
                                if (finalIsAddNewCar)
                                    LocalDataManager.getInstance(getContext()).updateCar(finalCarId, finalCarName, finalLicensePlate);
                            }
                        }
                    });
        }
    }

    private void loadInfoProfile() {
        getView().displayProblems(Arrays.asList(getContext().getResources().getStringArray(R.array.support_commnet_complaint_arr_porblems)), AppConstants.SELECTION_PROBLEMS);
        if (mUserData != null) {
            getView().displayUserInfo(mUserData);
            if (mUserData.getCars() != null && !mUserData.getCars().isEmpty()) {
                getView().displayListUseCar(mUserData.getCars());
                mProfileCar = mUserData.getCars();
            } else {
                getView().disableUseCar();
            }
        }
    }

    private void loadListCar() {
        RESTManager.getInstance().getCars(new IRequestListener<CarsResponse>() {
            @Override
            public void onCompleted(CarsResponse data) {
                super.onCompleted(data);
                if (data != null && data.getData() != null) {
                    mListSelectCar = data.getData();
                    SessionDataManager.getInstance().setCars(data.getData());
                    getView().displaySelectCar(data.getData());
                }
            }
        });
    }
}
