package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.util.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ThinhNH on 3/20/2018.
 */

public class CpnTab extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.ivHome)
    ImageView ivHome;
    @BindView(R.id.tvHome)
    TextView tvHome;
    @BindView(R.id.ivCar)
    ImageView ivCar;
    @BindView(R.id.tvCar)
    TextView tvCar;
    @BindView(R.id.ivService)
    ImageView ivService;
    @BindView(R.id.tvService)
    TextView tvService;
    @BindView(R.id.ivNews)
    ImageView ivNews;
    @BindView(R.id.tvNews)
    TextView tvNews;
    @BindView(R.id.ivSupport)
    ImageView ivSupport;
    @BindView(R.id.tvSupport)
    TextView tvSupport;
    private TabListener mListener;

    private boolean mIsSelected = false;
    private TabType mTypeSelected = null;
    private Map<TabType, Boolean> mMapTabMenu = new HashMap<>();
    private int mNumNotify = 0;

    public enum TabType {
        HOME, CAR, SERVICE, NEWS, SUPPORT
    }

    public interface TabListener {
        void onTabMenuSelected(TabType type);
    }

    public CpnTab(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnTab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CpnTab, defStyle, 0);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_tab, this, true);
        ButterKnife.bind(this);

        setTabMenuSelected(TabType.HOME, true);
    }

    public void setMenuListener(TabListener listener) {
        mListener = listener;
    }

    public void setTabMenuSelected(TabType type) {
        setTabMenuSelected(type, false);
    }

    public void setTabMenuSelected(TabType type, boolean onlyChangeTab) {
        setTabMenuSelectedOnlyOne(mTypeSelected, false);
        setTabMenuSelectedOnlyOne(type, true);

        if (mListener != null && !onlyChangeTab) {
            mListener.onTabMenuSelected(type);
        }
    }

    private void setTabMenuSelectedOnlyOne(TabType type, boolean isSelected) {
        if (type == mTypeSelected && isSelected == mIsSelected) {
            return;
        }
        if (type == null || isSelected) {
            mTypeSelected = type;
        }
        mIsSelected = isSelected;
        // set again
        switch (type) {
            case HOME:
                ivHome.setSelected(isSelected);
                tvHome.setSelected(isSelected);
                break;
            case CAR:
                ivCar.setSelected(isSelected);
                tvCar.setSelected(isSelected);
                break;
            case SERVICE:
                ivService.setSelected(isSelected);
                tvService.setSelected(isSelected);
                break;
            case NEWS:
                ivNews.setSelected(isSelected);
                tvNews.setSelected(isSelected);
                break;
            case SUPPORT:
                ivSupport.setSelected(isSelected);
                tvSupport.setSelected(isSelected);
                break;
            default:
                break;
        }
    }

//    public void setTabMenuEnable(TabType type, boolean isEnable) {
//        mMapTabMenu.put(type, isEnable);
//        ImageView imageView = null;
//        switch (type) {
//            case UPLOAD_FILE:
//                imageView = mIvHome;
//                if (mMode == ViewMode.LOCAL) {
//                    if (!isEnable) {
//                        mIvHome.setImageResource(R.drawable.btn_upload_2_device_disable);
//                    } else {
//                        mIvHome.setImageResource(R.drawable.btn_upload_2_device);
//                    }
//                } else {
//                    if (!isEnable) {
//                        mIvHome.setImageResource(R.drawable.btn_upload_file_disable);
//                    } else {
//                        mIvHome.setImageResource(R.drawable.btn_upload_file);
//                    }
//                }
//                break;
//            case FILTER:
//                imageView = mFlCart;
//                break;
//            case SORT:
//                imageView = mIvTodayStatistics;
//                break;
//            case MORE:
//                imageView = mIvStatistics;
//                if (!isEnable) {
//                    mIvStatistics.setImageResource(R.drawable.btn_more_disable);
//                } else {
//                    mIvStatistics.setImageResource(R.drawable.btn_more);
//                }
//                break;
//            default:
//                break;
//        }
//
//        if (imageView != null) {
//            imageView.setEnabled(isEnable);
//            if (isEnable) {
//                imageView.setOnTouchListener(this);
//            } else {
//                imageView.setOnTouchListener(null);
//            }
//        }
//    }

    @OnClick({R.id.llHome, R.id.llCar, R.id.llService, R.id.llNews, R.id.llSupport})
    public void onClick(View v) {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_500)) {
            return;
        }
        TabType type = null;
        switch (v.getId()) {
            case R.id.llHome:
                type = TabType.HOME;
                break;
            case R.id.llCar:
                type = TabType.CAR;
                break;
            case R.id.llService:
                type = TabType.SERVICE;
                break;
            case R.id.llNews:
                type = TabType.NEWS;
                break;
            case R.id.llSupport:
                type = TabType.SUPPORT;
                break;
            default:
                break;
        }

//        if (type == mTypeSelected) {
//            return;
//        }

        setTabMenuSelected(type);
    }
}
