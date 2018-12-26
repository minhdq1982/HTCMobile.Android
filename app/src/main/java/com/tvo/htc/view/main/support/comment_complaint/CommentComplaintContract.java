package com.tvo.htc.view.main.support.comment_complaint;

import com.android.lib.model.Car;
import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

/**
 * Created by ThinhNH on 3/21/2018.
 */

public interface CommentComplaintContract {
    interface View extends BaseContract.View {
        void displayProblems(List<String> problems, int selectionPosition);

        void displayErrorEmpty(String message);

        void displaySuccessSent(String message);

        void displayUserInfo(LoginResponse.Data mUserData);

        void disableUseCar();

        void displayListUseCar(List<LoginResponse.Data.Car> cars);

        void displaySelectCar(List<Car> cars);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData();

        void sentCommentComplaint(String fullName,
                                  String email,
                                  String phone,
                                  String licensePlates,
                                  int positionProblems,
                                  String problemsTitle,
                                  String problemsContent, boolean isUpdateProfile, int useCarPosition, int selectCarPosition, boolean isUseCar, boolean isAddNewCar);
    }
}
