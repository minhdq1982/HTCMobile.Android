/**
 *
 */
package com.android.lib.http;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.android.lib.dialog.SimpleDialog;
import com.android.lib.dialog.WaitDialogFragment;
import com.android.lib.util.LibConstants;
import com.android.lib.util.Logger;
import com.steam.sshop.lib.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @author ThinhNH
 */
public class DialogHandler implements SimpleDialog.Callbacks {
    public final static int ID_ERR_DIALOG_ = 1;
    public final static int ID_WAIT_DIALOG = 2;
    private static DialogHandler instance;
    private boolean isShow = false;
    private SimpleDialog mErrorDialog;
    private WaitDialogFragment mWaitDialog;
    private static Activity mAct = null;

    private Integer mReason;

    public static synchronized Activity getCurrentActivity() {
        return mAct;
    }

    public static synchronized void setCurrentAct(Activity act) {
        mAct = act;
    }

    public static DialogHandler getInstance() {
        if (instance == null) {
            instance = new DialogHandler();
            return instance;
        } else {
            return instance;
        }


    }

    private DialogHandler() {
        super();
    }

    /**
     * Display error dialog with given message
     */
    public void displayMessage(Throwable throwable, final int featureID) {
        if (mAct == null) {
            return;
        }
        mAct.runOnUiThread(() -> {
            if (mAct == null || mErrorDialog != null) {
                return;
            }

            try {
                // handle 401
                if (throwable != null && throwable instanceof ErrorException) {
                    ErrorException errorException = (ErrorException) throwable;

                    if (errorException.getResponseCode() == 401) {
                        String message = getCurrentActivity().getString(R.string.st_fail_refresh_token);
                        mErrorDialog = new SimpleDialog();
                        mErrorDialog.init(mAct, message, getCurrentActivity().getString(R.string.dlg_btn_yes), () -> {
                            clickOKButton();
                            sendBroadcastRequireLogin();
                        });
                        mErrorDialog.setNotError(false);
                        mErrorDialog.show(((AppCompatActivity) mAct).getSupportFragmentManager(), null);
                        return;
                    }
                }

                String message = createDisplayMsg(throwable, featureID);
                if (message != null && !message.isEmpty()) {
                    mErrorDialog = new SimpleDialog();
                    mErrorDialog.init(mAct, message, DialogHandler.this);
                    mErrorDialog.setNotError(false);
                    mErrorDialog.show(((AppCompatActivity) mAct).getSupportFragmentManager(), null);
                    Logger.d("message: " + message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Display error dialog with given message
     */
    public void displayWaitDialog() {
        if (mAct == null) {
            return;
        }
        mAct.runOnUiThread(() -> {
            if (mAct == null || mWaitDialog != null)
                return;
            try {
                mWaitDialog = WaitDialogFragment.newInstance(false);
                mWaitDialog.show(mAct.getFragmentManager(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public String createDisplayMsg(Throwable throwable, int featureID) {
        mReason = -1;
        String message = "";
        if (throwable != null) {
//            message = throwable.getMessage();
            message = getCurrentActivity().getString(R.string.st_abnormal_err_msg);

            if (throwable instanceof ConnectException) {
                message = getCurrentActivity().getString(R.string.st_no_network_connection);
            } else if (throwable instanceof SocketTimeoutException) {
                message = getCurrentActivity().getString(R.string.err_http_timeout);
            } else if (throwable instanceof ErrorException) {
                ErrorException errorException = (ErrorException) throwable;

                if (errorException.getResponseCode() == 401) {
                    message = getCurrentActivity().getString(R.string.st_fail_refresh_token);
                } else if (errorException.getResponseCode() == 200) {
                    if (errorException.getMessage() != null && !errorException.getMessage().isEmpty()) {
                        message = errorException.getMessage();
                    }

                    if (errorException.getErrorBody() != null) {
                        String errorMessage = errorException.getErrorBody().getMessage();

                        if (errorMessage != null && !errorMessage.isEmpty()) {
                            message = errorMessage;
                        }
                    }
                }
            } else {

            }
        }

        return message;
    }

    @Override
    public void clickOKButton() {
        dismissDialog(ID_ERR_DIALOG_);
    }


    public void dismissDialog(int which) {
        switch (which) {
            case ID_ERR_DIALOG_:
                if (mErrorDialog != null) {
                    mErrorDialog.dismiss();
                    mErrorDialog = null;
                }
                break;
            case ID_WAIT_DIALOG:
                if (mWaitDialog != null) {
                    mWaitDialog.dismiss();
                    mWaitDialog = null;
                }
                break;
            default:
                break;
        }
    }

    private void sendBroadcastRequireLogin() {
        Intent intent = new Intent();
        intent.setAction(LibConstants.ACTION_UNAUTHORIZED);
        getCurrentActivity().sendBroadcast(intent);
    }
}
