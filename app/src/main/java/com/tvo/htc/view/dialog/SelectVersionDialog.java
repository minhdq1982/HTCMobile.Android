package com.tvo.htc.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lib.model.Car;
import com.android.lib.model.response.DetailCarResponse;
import com.tvo.htc.R;
import com.tvo.htc.util.ImageLoader;
import com.tvo.htc.util.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 4230026 on 8/28/2015.
 */
public class SelectVersionDialog extends BaseDialogFragment implements OnClickListener {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.ivCar)
    ImageView ivCar;
    @BindView(R.id.rcVersion)
    RecyclerView rcVersion;

    private Context mContext;
    private DetailCarResponse mDetailCarResponse;
    private Callbacks mCallbacks;
    private VersionAdapter mVersionAdapter;

    public interface Callbacks {
        void clickOKButton(DetailCarResponse detailCarResponse);

        void errorVersion();
    }

    public static SelectVersionDialog newInstance(Context context,
                                                  DetailCarResponse detailCarResponse,
                                                  Callbacks callbacks) {
        Bundle args = new Bundle();

        SelectVersionDialog fragment = new SelectVersionDialog();
        fragment.setArguments(args);
        fragment.mContext = context;
        fragment.mDetailCarResponse = Utils.cloneObject(detailCarResponse);
        fragment.mCallbacks = callbacks;
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.dialog_select_version;
    }

    @Override
    public void init(View view) {

        if (mDetailCarResponse != null && mDetailCarResponse.getData() != null) {
            String image = null;
            if (mDetailCarResponse.getData().getImages() != null && mDetailCarResponse.getData().getImages().size() > 0) {
                image = mDetailCarResponse.getData().getImages().get(0);
            }
            ImageLoader.loadImage(getActivity(), ivCar, image);
            tvName.setText(mDetailCarResponse.getData().getName());
        }

        mVersionAdapter = new VersionAdapter(getActivity(),
                mDetailCarResponse.getData().getVersion());
        rcVersion.setAdapter(mVersionAdapter);
    }

    @OnClick({R.id.cpnbCancel, R.id.cpnbDone})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cpnbDone:
                dismiss();
                if (mCallbacks != null) {
                    Car.Version version = mVersionAdapter.getSelectionItem();
                    if (version != null) {
                        mDetailCarResponse = Utils.removeOtherVersion(mDetailCarResponse,
                                version.getId());
                        mCallbacks.clickOKButton(mDetailCarResponse);
                    } else {
                        mCallbacks.errorVersion();
                    }
                }
                break;
            case R.id.cpnbCancel:
                dismiss();
                break;
            default:
                break;
        }
    }
}