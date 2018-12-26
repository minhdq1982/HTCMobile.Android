package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.model.response.LoginResponse;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.event.EventNumberNotification;
import com.tvo.htc.util.EventBusUtils;
import com.tvo.htc.util.Utils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ThinhNH on 10/08/2016
 * display and control action on navigation bar
 */
public class CpnNavigationBar extends RelativeLayout implements View.OnClickListener {

    //Bind view
    @BindView(R.id.vBackground)
    View vBackground;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.flNavigation)
    View flNavigation;
    @BindView(R.id.ivNavigation)
    ImageView mIvNavigation;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.viewPadding)
    View mViewPadding;
    @BindView(R.id.llNavigationBarAction)
    LinearLayout llNavigationBarAction;
    @BindView(R.id.ivProfile)
    ImageView ivProfile;
    @BindView(R.id.flNotification)
    View flNotification;
    @BindView(R.id.tvNumNotify)
    TextView tvNumNotify;
    @BindView(R.id.ivFilter)
    ImageView ivFilter;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.ivSetting)
    ImageView ivSetting;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvCurrentCard)
    TextView tvCurrentCard;
    @BindView(R.id.tvAdd)
    TextView tvAdd;
    @BindView(R.id.tvIgnore)
    TextView tvIgnore;

    @BindView(R.id.rlSearch)
    RelativeLayout rlSearch;
    @BindView(R.id.edtSearch)
    EditText edtSearch;

    private NavigationListener mListener;
    private String mTitle;
    private int mTextColor = Color.WHITE;
    private int mTintBackColor = -1;
    private int mType = 1;
    private boolean mBackgroundTransparent = false;
    private int mBtFilterVisibility;
    private int mBtSearchVisibility;
    private int mBtCloseVisibility;
    private int mBtCancelVisibility;
    private int mBtCurrentCardVisibility;
    private int mBtSaveVisibility;
    private int mBtAddVisibility;
    private int mBtNotificationVisibility;
    private int mBtSettingVisibility;
    private LoginResponse mLoginResponse;


    public interface NavigationListener {
        void onNavigationBtClick(NavigationType type);
    }

    public enum NavigationType {
        BT_BACK, BT_PROFILE, BT_NOTIFICATION, BT_FILTER, BT_CANCEL, BT_SAVE, BT_SEARCH, BT_CLOSE, BT_ADD, BT_IGNORE, BT_SEARCH_EDT, BT_SETTING, NONE
    }

    public CpnNavigationBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnNavigationBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        EventBusUtils.register(this);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnNavigationBar, defStyle, 0);

        mLoginResponse = LocalDataManager.getInstance(getContext()).getLoginResponse();

        mTitle = a.getString(R.styleable.CpnNavigationBar_title);
        mTintBackColor = a.getColor(R.styleable.CpnNavigationBar_tintBack, -1);
        mTextColor = a.getColor(R.styleable.CpnNavigationBar_textColor, mTextColor);
        mType = a.getInt(R.styleable.CpnNavigationBar_navigation_type, mType);
        mBackgroundTransparent = a.getBoolean(R.styleable.CpnNavigationBar_backgroundTransparent, false);
        mBtFilterVisibility = a.getInt(R.styleable.CpnNavigationBar_btFilterVisibility, View.GONE);
        mBtSearchVisibility = a.getInt(R.styleable.CpnNavigationBar_btSearchVisibility, View.GONE);
        mBtCloseVisibility = a.getInt(R.styleable.CpnNavigationBar_btCloseVisibility, View.GONE);
        mBtCancelVisibility = a.getInt(R.styleable.CpnNavigationBar_btCancelVisibility, View.GONE);
        mBtCurrentCardVisibility = a.getInt(R.styleable.CpnNavigationBar_btCurrentCardVisibility, View.GONE);
        mBtSaveVisibility = a.getInt(R.styleable.CpnNavigationBar_btSaveVisibility, View.GONE);
        mBtAddVisibility = a.getInt(R.styleable.CpnNavigationBar_btAddVisibility, View.GONE);
        mBtNotificationVisibility = a.getInt(R.styleable.CpnNavigationBar_btNotificationVisibility, View.GONE);
        mBtSettingVisibility = a.getInt(R.styleable.CpnNavigationBar_btSettingVisibility, View.GONE);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_navigation_bar, this, true);
        ButterKnife.bind(this);

        updateView();
        getSaveNumberNotifications();
    }

    private void getSaveNumberNotifications() {
        updateNumNotifications(LocalDataManager.getInstance(getContext()).getUnreadNotification());
    }

    public void setNavigationListener(NavigationListener listener) {
        mListener = listener;
    }

    private void updateView() {
        if (mTitle != null) {
            mTvTitle.setText(mTitle);
        }
        mTvTitle.setTextColor(mTextColor);
        setTintImage(mIvNavigation, mTintBackColor);
        switch (mType) {
            case 0:
                // HOME
                flNavigation.setVisibility(GONE);
                ivLogo.setVisibility(VISIBLE);
                ivProfile.setVisibility(VISIBLE);
                vBackground.setAlpha(0f);
                flNotification.setVisibility(VISIBLE);
                break;
            case 1:
                // BACK
                ivLogo.setVisibility(GONE);
                mIvNavigation.setVisibility(VISIBLE);
                mIvNavigation.setImageResource(R.drawable.ic_back);
                flNotification.setVisibility(mBtNotificationVisibility);
                break;
            case 2:
                // IGNORE
                tvIgnore.setVisibility(VISIBLE);
                mIvNavigation.setVisibility(GONE);
                flNotification.setVisibility(GONE);
                break;
            case 3:
                // SEARCH
                mTvTitle.setVisibility(GONE);
                rlSearch.setVisibility(VISIBLE);
                flNavigation.setVisibility(GONE);
                break;
            default:
                // NONE
                tvIgnore.setVisibility(GONE);
                flNavigation.setVisibility(INVISIBLE);
                mViewPadding.setVisibility(VISIBLE);
                flNotification.setVisibility(mBtNotificationVisibility);
        }

        if (mBackgroundTransparent) {
            vBackground.setAlpha(0f);
        }

        if (!hasLogin()) {
            flNotification.setVisibility(GONE);

            // SEARCH
            if (mType == 3) {
                llNavigationBarAction.setVisibility(GONE);
            }
        }

        ivSetting.setVisibility(mBtSettingVisibility);
        tvAdd.setVisibility(mBtAddVisibility);
        tvSave.setVisibility(mBtSaveVisibility);
        ivFilter.setVisibility(mBtFilterVisibility);
        ivSearch.setVisibility(mBtSearchVisibility);
        ivClose.setVisibility(mBtCloseVisibility);
        tvCancel.setVisibility(mBtCancelVisibility);
        tvCurrentCard.setVisibility(mBtCurrentCardVisibility);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param title The example string attribute value to use.
     */
    public void setTitle(String title) {
        mTitle = title;
        if (mTvTitle != null) {
            mTvTitle.setText(mTitle);
        }
    }

    /**
     * Set text visible in edittext search
     *
     * @param key
     */
    public void setEditSearchText(String key) {
        edtSearch.setText(key);
    }


    public void setEditSearchHint(String hint) {
        edtSearch.setHint(hint);
    }

    public String getSearchKey() {
        return edtSearch.getText().toString();
    }

    public void addSearchListener(SearchListener listener) {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onQuery(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getTextColor() {
        return mTextColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param color The example color attribute value to use.
     */
    public void setTextColor(int color) {
        mTextColor = color;
        if (mTvTitle != null) {
            mTvTitle.setTextColor(mTextColor);
        }
    }

    public void setBtSaveVisibility(int btSaveVisibility) {
        this.mBtSaveVisibility = btSaveVisibility;
        tvSave.setVisibility(mBtSaveVisibility);

    }

    public void setBtFilterVisibility(int mBtFilterVisibility) {
        this.mBtFilterVisibility = mBtFilterVisibility;
        ivFilter.setVisibility(mBtFilterVisibility);

    }

    public void setBtSearchVisibility(int mBtSearchVisibility) {
        this.mBtSearchVisibility = mBtSearchVisibility;
        ivSearch.setVisibility(mBtSearchVisibility);

    }

    public void setBtCloseVisibility(int mBtCloseVisibility) {
        this.mBtCloseVisibility = mBtCloseVisibility;
        ivClose.setVisibility(mBtCloseVisibility);

    }

    public void setBtCancelVisibility(int mBtCancelVisibility) {
        this.mBtCancelVisibility = mBtCancelVisibility;
        tvCancel.setVisibility(mBtCancelVisibility);
    }

    public void setBtAddVisibility(int btAddVisibility) {
        this.mBtAddVisibility = btAddVisibility;
        tvAdd.setVisibility(mBtAddVisibility);
    }

    public void setBtCancelText(String text) {
        tvCancel.setText(text);
    }


    public void setBackVisibility(int visibility) {
        flNavigation.setVisibility(visibility);
    }

    /**
     * Set visible textview current card
     *
     * @param mBtCurrentCardVisibility
     */
    public void setBtCurrentCardVisibility(int mBtCurrentCardVisibility) {
        this.mBtCurrentCardVisibility = mBtCurrentCardVisibility;
        tvCurrentCard.setVisibility(mBtCurrentCardVisibility);
    }

    public void setBtSettingVisibility(int mBtSettingVisibility) {
        this.mBtSettingVisibility = mBtSettingVisibility;
        ivSetting.setVisibility(mBtSettingVisibility);
    }

    public void setType(int type) {
        this.mType = type;
        updateView();
    }

    public boolean hasLogin() {
        return Utils.hasLogin(getContext(), mLoginResponse);
    }

    /**
     * Set text current card
     *
     * @param text
     */

    public void setTextCurrentCard(String text) {
        tvCurrentCard.setText(text);
    }

    @OnClick({R.id.flNavigation, R.id.ivProfile, R.id.flNotification, R.id.ivFilter, R.id.tvCancel,
            R.id.tvSave, R.id.ivSearch, R.id.tvAdd, R.id.ivClose, R.id.imSearch, R.id.ivSetting})
    public void onClick(View v) {
        if (!Utils.isEnabledClick(Utils.CLICK_DELAY_MSEC_300)) {
            return;
        }
        NavigationType type = null;
        switch (v.getId()) {
            case R.id.ivSetting:
                type = NavigationType.BT_SETTING;
                break;
            case R.id.flNavigation:
                if (mType == 1) {
                    type = NavigationType.BT_BACK;
                } else if (mType == 2) {
                    type = NavigationType.BT_IGNORE;
                } else {
                    type = NavigationType.NONE;
                }
                break;
            case R.id.ivProfile:
                type = NavigationType.BT_PROFILE;
                break;
            case R.id.flNotification:
                type = NavigationType.BT_NOTIFICATION;
                break;
            case R.id.ivFilter:
                type = NavigationType.BT_FILTER;
                break;
            case R.id.ivSearch:
            case R.id.imSearch:
                type = NavigationType.BT_SEARCH;
                break;
            case R.id.ivClose:
                type = NavigationType.BT_CLOSE;
                break;
            case R.id.tvCancel:
                type = NavigationType.BT_CANCEL;
                break;
            case R.id.tvSave:
                type = NavigationType.BT_SAVE;
                break;
            case R.id.tvAdd:
                type = NavigationType.BT_ADD;
                break;
            default:
                break;

        }

        if (mListener != null) {
            mListener.onNavigationBtClick(type);
        }
    }

    public interface SearchListener {
        void onQuery(String key);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNumberNotificationEvent(EventNumberNotification event) {
        updateNumNotifications(event.newNumber);
        LocalDataManager.getInstance(getContext()).saveUnreadNotification(event.newNumber);
    }

    private void updateNumNotifications(int num) {
        if (flNotification.getVisibility() == View.VISIBLE) {
            if (!(num < 1)) {
                tvNumNotify.setText(String.valueOf(num));
                tvNumNotify.setVisibility(View.VISIBLE);
            } else {
                tvNumNotify.setVisibility(GONE);
            }
        }
    }

    private void setTintImage(ImageView im, int color) {
        if (color == -1) return;
        im.setColorFilter(color);
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBusUtils.unregister(this);
        super.onDetachedFromWindow();
    }
}
