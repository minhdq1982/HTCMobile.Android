package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tvo.htc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThinhNH on 10/08/2016
 * display and control action on navigation bar
 */
public class CpnButton extends LinearLayout {

    @BindView(R.id.tvText)
    TextView tvText;
    @BindView(R.id.vOverlay)
    View vOverlay;

    @BindView(R.id.imLeft)
    ImageView imLeft;
    @BindView(R.id.imRight)
    ImageView imRight;

    @BindView(R.id.flRootView)
    FrameLayout flRootView;

    private int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    private CharSequence mText;
    private int mType;
    private Drawable mIconLeft;
    private Drawable mIconRight;
    private boolean isCenterIcon;


    public CpnButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnButton, defStyle, 0);

        mText = a.getText(R.styleable.CpnButton_text);
        mType = a.getInt(R.styleable.CpnButton_type, 0);
        mIconLeft = a.getDrawable(R.styleable.CpnButton_btn_icon_left);
        mIconRight = a.getDrawable(R.styleable.CpnButton_btn_icon_right);
        isCenterIcon = a.getBoolean(R.styleable.CpnButton_isCenterIcon, false);

        //init text padding
        int paddingAll = a.getDimensionPixelOffset(R.styleable.CpnButton_text_padding, -1);
        int paddingLeft = a.getDimensionPixelOffset(R.styleable.CpnButton_text_paddingLeft, 0);
        int paddingTop = a.getDimensionPixelOffset(R.styleable.CpnButton_text_paddingTop, 0);
        int paddingRight = a.getDimensionPixelOffset(R.styleable.CpnButton_text_paddingRight, 0);
        int paddingBottom = a.getDimensionPixelOffset(R.styleable.CpnButton_text_paddingBottom, 0);

        int[] systemAttrs = {android.R.attr.layout_height};
        TypedArray aDefault = context.obtainStyledAttributes(attrs, systemAttrs);
        if (aDefault.getIndexCount() > 0) {
            if (aDefault.peekValue(0).type == TypedValue.TYPE_DIMENSION) {
                mHeight = (int) aDefault.getDimension(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (aDefault.peekValue(0).type == TypedValue.TYPE_INT_DEC) {
                mHeight = (int) aDefault.getInt(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_button, this, true);
        ButterKnife.bind(this);
        updatePaddingText(paddingAll, paddingLeft, paddingTop, paddingRight, paddingBottom);
        updateView();
    }

    private void updatePaddingText(int paddingAll, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        if (paddingAll != -1) {
            tvText.setPadding(paddingAll, paddingAll, paddingAll, paddingAll);
        } else {
            tvText.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    private void updateView() {
        tvText.setText(mText);
        if (mIconLeft != null) {
            imLeft.setVisibility(VISIBLE);
            imLeft.setImageDrawable(mIconLeft);
        }
        if (mIconRight != null) {
            imRight.setVisibility(VISIBLE);
            imRight.setImageDrawable(mIconRight);
        }

        if (isCenterIcon) {
            LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            tvText.setLayoutParams(layoutParams);
        }

        switch (mType) {
            case 0:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                flRootView.setBackgroundResource(R.drawable.bg_button_blue);
                break;
            case 1:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                flRootView.setBackgroundResource(R.drawable.bg_button_white);
                break;
            case 2:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                flRootView.setBackgroundResource(R.drawable.bg_button_white_border);
                break;
            case 3:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                flRootView.setBackgroundResource(R.drawable.bg_button_blue2);
                break;
            case 4:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextTile));
                flRootView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorBackButton));
                break;
            case 5:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                flRootView.setBackgroundResource(R.drawable.bg_button_trans_white_border);
                break;
            case 6:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorNearBlack));
                flRootView.setBackgroundResource(R.drawable.bg_button_gray2);
                break;
            default:
                tvText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                flRootView.setBackgroundResource(R.drawable.bg_button_blue);
                break;
        }

        if (mHeight > 0) {
            flRootView.getLayoutParams().height = mHeight;
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        vOverlay.setEnabled(enabled);
        if (enabled) {
            vOverlay.setAlpha(0.05f);
        } else {
            vOverlay.setAlpha(0.1f);
        }
    }

    public void setText(CharSequence mText) {
        this.mText = mText;
        tvText.setText(mText);
    }
}
