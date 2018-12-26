package com.tvo.htc.view.dialog;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.lib.RESTManager;
import com.android.lib.http.DialogHandler;
import com.android.lib.model.ProgressListener;
import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.FileUtils;
import com.tvo.htc.view.component.CpnButton;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadDialog extends BaseDialogFragment implements ProgressListener, Callback<ResponseBody> {
    @BindView(R.id.cpnbCancel)
    CpnButton cpnButtonCancel;
    @BindView(R.id.cpnbOpen)
    CpnButton cpnButtonOpen;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.tvCurrentProgress)
    TextView tvCurrentProgress;
    @BindView(R.id.tvCurrentKb)
    TextView tvCurrentKb;

    String pathSave = "";
    String name = "";
    Handler uiThread = new Handler(Looper.getMainLooper());
    CallBack callBack;
    TypeDownload typeDownload;


    public enum TypeDownload {
        CATALOG,
        CALCULATE_INTERES
    }


    @Override
    protected void init(View view) {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_download;
    }

    @SuppressLint("SetTextI18n")
    public void startDownload(String url, String name, CallBack callBack) {
        this.callBack = callBack;
        this.typeDownload = TypeDownload.CATALOG;
        this.name = name;
        RESTManager.getInstance().downloadCatalog(url, this, this);
    }

    public void startDownload(double borrow, int numberMonth, double interestRate1, double interestRate2, String name, CallBack callBack) {
        this.callBack = callBack;
        this.typeDownload = TypeDownload.CALCULATE_INTERES;
        this.name = name;
        RESTManager.getInstance().downloadCalculateInterestRate(borrow, numberMonth, interestRate1, interestRate2, this, this);
    }

    @Override
    public void onProgress(long bytesRead, long contentLength, boolean done) {
        int progress = (int) (((bytesRead * 1f) / (contentLength * 1f)) * 100);
        String currentKb = FileUtils.convertCurrentProgress(bytesRead, contentLength);
        uiThread.post(() -> {
            if (done) {
                cpnButtonOpen.setVisibility(View.VISIBLE);
                cpnButtonCancel.setText(getString(R.string.dialog_download_close));
            } else {
                progressBar.setProgress(progress);
                tvCurrentProgress.setText(progress + "%");
                tvCurrentKb.setText(currentKb);
            }
        });
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        saveFile(response.body(), name);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        FileUtils.deleteFile(pathSave);
        uiThread.post(() -> {
            Logger.e("Error: " + t);
            dismiss();
            DialogHandler.getInstance().displayMessage(t, 0);
        });

    }

    @OnClick({R.id.cpnbCancel, R.id.cpnbOpen})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.cpnbCancel:
                dismiss();
                callBack.onDownloadSuccess(pathSave);
                break;
            case R.id.cpnbOpen:
                openFile();
                break;
        }
    }

    public interface CallBack {
        void onDownloadSuccess(String path);
    }

    private void saveFile(ResponseBody responseBody, String name) {
        new Thread(() -> {
            try {
                switch (typeDownload) {
                    case CALCULATE_INTERES:
                        pathSave = FileUtils.saveCalculateInterestRate(responseBody, name);
                        break;
                    case CATALOG:
                        pathSave = FileUtils.saveCatalog(responseBody, name);
                        break;
                }
            } catch (Exception e) {
                Logger.e("Error save file: " + e + "/path_save--->" + pathSave);
                FileUtils.deleteFile(pathSave);
                uiThread.post(() -> {
                    dismiss();
                    DialogHandler.getInstance().displayMessage(e, 0);
                });
            }
        }).start();
    }

    private void openFile() {
        switch (typeDownload) {
            case CATALOG:
                if (pathSave.isEmpty()) {
                    FileUtils.goToFolderCatalog(getContext());
                } else {
                    FileUtils.openFile(getContext(), pathSave);
                }
                break;
            case CALCULATE_INTERES:
                if (pathSave.isEmpty()) {
                    FileUtils.gotoFolderCalculateInterestRate(getContext());
                } else {
                    FileUtils.openFile(getContext(), pathSave);
                }
                break;
        }
    }
}
