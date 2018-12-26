package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tvo.htc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ThinhNH on 10/08/2016
 * display and control action on navigation bar
 */
public class CpnEditText extends LinearLayout {

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.etText)
    EditText etText;
    @BindView(R.id.vLine)
    View vLine;

    private Drawable mIcon;
    private CharSequence mText;
    private boolean enable;
    private CharSequence mHint;
    private int mImeOptions;
    private int mInputType;
    private int mLine;
    private int mLineColor = getResources().getColor(R.color.colorLine);
    private float mTextSize;
    private int mTextStyle;
    private int mMaxLength;

    public CpnEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CpnEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CpnEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                .CpnEditText, defStyle, 0);

        mIcon = a.getDrawable(R.styleable.CpnEditText_icon);
        mText = a.getText(R.styleable.CpnEditText_text);
        mTextSize = a.getDimension(R.styleable.CpnEditText_textSize, -1);
        enable = a.getBoolean(R.styleable.CpnEditText_enable, true);
        mHint = a.getText(R.styleable.CpnEditText_hint);
        mImeOptions = a.getInt(R.styleable.CpnEditText_android_imeOptions,
                EditorInfo.IME_ACTION_NEXT);
        mLineColor = a.getColor(R.styleable.CpnEditText_lineColor, mLineColor);

        mInputType = a.getInt(R.styleable.CpnEditText_android_inputType, EditorInfo
                .TYPE_CLASS_TEXT);
        mLine = a.getInt(R.styleable.CpnEditText_android_lines, -1);
        mTextStyle = a.getInt(R.styleable.CpnEditText_android_textStyle, -1);
        mMaxLength = a.getInt(R.styleable.CpnEditText_android_maxLength, -1);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cpn_edit_text, this, true);
        ButterKnife.bind(this);

        updateView();
    }

    private void updateView() {
        if (ivIcon != null) {
            ivIcon.setImageDrawable(mIcon);
        }
        etText.setText(mText);
        etText.setHint(mHint);
        if (mTextSize != -1) {
            etText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        setEnabled(enable);
        etText.setImeOptions(mImeOptions);
        etText.setInputType(mInputType);
        if (mLine != -1) {
            etText.setSingleLine(true);
        } else {
            etText.setSingleLine(false);
            etText.setMaxLines(20);
        }
        if (mTextStyle != -1) {
            etText.setTypeface(etText.getTypeface(), mTextStyle);
        }
        if (mMaxLength != -1) {
            etText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
        }

        vLine.setBackgroundColor(mLineColor);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        etText.setEnabled(enabled);
        etText.setFocusable(enabled);
        etText.setFocusableInTouchMode(enabled);
    }

    public void setText(String text) {
        this.mText = text;
        etText.setText(mText);
    }

    public String getText() {
        return etText.getText().toString();
    }

    public void addTextWatcher(TextWatcher textWatcher) {
        etText.addTextChangedListener(textWatcher);
    }

    public EditText getEditText() {
        return etText;
    }

    public void setEditTextInputType(int type) {
        etText.setInputType(type);
    }

    public String getLine() {
        return etText.getMinLines()+"";
    }
}