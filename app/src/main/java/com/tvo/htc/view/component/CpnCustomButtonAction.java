package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * TODO: document your custom view class.
 */
public class CpnCustomButtonAction extends LinearLayout {
    @BindView(R.id.tvText)
    TextView mTvText;
    @BindView(R.id.lineView)
    View lineView;

    private String mText;

    private TextView mOldText;

    public CpnCustomButtonAction(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnCustomButtonAction(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnCustomButtonAction(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnCustomButtonAction, defStyle, 0);

        mText = a.getString(R.styleable.CpnCustomButtonAction_text);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_custom_button_action, this, true);

        ButterKnife.bind(this);
        updateView();
    }

    private void updateView() {
        if (mText != null) {
            mTvText.setText(mText);
        }
        mOldText = mTvText;
    }

    public void setMargin(int left, int top, int right, int bottom) {
        LayoutParams params = (LayoutParams) mTvText.getLayoutParams();
        params.setMargins(left, top, right, bottom);
        mTvText.setLayoutParams(params);
    }

    public void setText(String text) {
        mText = text;
        if (mText != null) {
            mTvText.setText(mText);
        }
    }


    public String getText() {
        return mTvText.getText().toString();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            Utils.setFontStyle(mTvText, true);
            lineView.setVisibility(VISIBLE);
        } else {
            Utils.setFontStyle(mTvText, false);
            lineView.setVisibility(INVISIBLE);
        }
    }
}
