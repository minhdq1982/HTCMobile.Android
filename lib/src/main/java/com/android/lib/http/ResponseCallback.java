package com.android.lib.http;

import com.android.lib.model.response.BaseResponse;
import com.android.lib.util.Logger;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ThinhNH on 3/22/2018.
 */

public class ResponseCallback<T> implements Callback<T> {

    private final IRequestListener mListener;

    public ResponseCallback(IRequestListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            if (response.isSuccessful()) {
                boolean isError = false;
                if (response.body() instanceof BaseResponse) {
                    BaseResponse baseResponse = (BaseResponse) response.body();

                    if (!baseResponse.isSuccess()) {
                        ErrorException errorException = new ErrorException(baseResponse.getMessage());
                        errorException.setResponseCode(response.code());
                        ErrorException.ErrorBody errorBody = new ErrorException.ErrorBody();
                        errorBody.setCode(baseResponse.getCode());
                        errorBody.setMessage(baseResponse.getMessage());
                        errorException.setErrorBody(errorBody);

                        onFailure(call, errorException);
                        isError = true;
                    }
                }

                if (!isError && mListener != null) {
                    mListener.onCompleted(response.body());
                }
            } else {
                Gson gson = new Gson();
                ErrorException errorException = new ErrorException(response.message());
                errorException.setResponseCode(response.code());
                try {
                    ErrorException.ErrorBody errorBody = gson.fromJson(response.errorBody().charStream(),
                            ErrorException.ErrorBody.class);
                    errorException.setErrorBody(errorBody);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                onFailure(call, errorException);
            }
        } catch (Exception e) {
            Logger.e("ERROR CALLBACK: "+e);
            onFailure(null, null);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        try {
            if (mListener != null) {
                mListener.onFailed(t);
            }
        } catch (Exception e) {
            Logger.e("ERROR CALLBACK: "+e);
        }
    }
}
