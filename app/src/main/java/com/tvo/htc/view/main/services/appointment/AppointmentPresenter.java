package com.tvo.htc.view.main.services.appointment;

import android.content.Context;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.AppointmentResponse;
import com.android.lib.util.Logger;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.BasePresenter;

import java.util.List;

import static com.tvo.htc.util.AppConstants.START_OFFSET;

/**
 * Created by Ngocji on 10/16/2018.
 */

public class AppointmentPresenter extends BasePresenter<AppointmentContract.View> implements AppointmentContract.Presenter {

    private int mOffSet = START_OFFSET;
    private List<AppointmentResponse.Data> mListAppointment;

    public AppointmentPresenter(Context context) {
        super(context);
        mOffSet = SessionDataManager.getInstance().getAppointmentOffSet();
    }

    @Override
    public void loadListAppointment() {
            RESTManager.getInstance().getListAppointment(new IRequestListener<AppointmentResponse>() {
                @Override
                public void onCompleted(AppointmentResponse data) {
                    super.onCompleted(data);
                    if (data != null) {
                        if (mOffSet == START_OFFSET) {
                            mListAppointment = data.getData();
                            getView().displayListAppointment(mListAppointment);
                            Logger.d(data.getData().toString());
                        } else {
                            getView().updateListAppointment(data.getData());
                        }
                        saveSession();
                    }
                }
            });
    }

    @Override
    public void handleAppointmentClicked(int position) {
        if (mListAppointment != null) {
            getView().displayDetailAppointment(mListAppointment.get(position));
        }
    }


    private void saveSession() {
        SessionDataManager.getInstance().setAppointmentOffSet(mOffSet);
    }
}
