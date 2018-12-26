package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lib.model.response.SurveyResponse;
import com.tvo.htc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThinhNH on 10/08/2016
 * display and control action on navigation bar
 */
public class CpnBannerSurvey extends LinearLayout {

    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;

    private SurveyResponse.SurveyType mType;

    public CpnBannerSurvey(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnBannerSurvey(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnBannerSurvey(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnBannerSurvey, defStyle, 0);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_banner_survey, this, true);
        ButterKnife.bind(this);
        updateView();
    }

    private void updateView() {
    }

    public void setType(SurveyResponse.SurveyType type) {
        this.mType = type;
        if (type == SurveyResponse.SurveyType.REPAIR_PROTECTION) {
            tvName.setText(getResources().getString(R.string.banner_survey_name_service));
            cardView.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSurveyBackgroundService));
            tvName.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSurveyTextSell));
            tvDescription.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSurveyTextSell));
        }
    }
}
