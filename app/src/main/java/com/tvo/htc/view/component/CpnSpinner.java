package com.tvo.htc.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tvo.htc.R;
import com.tvo.htc.util.FragmentUtil;
import com.tvo.htc.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

import static android.graphics.Typeface.NORMAL;

/**
 * Create by Ngocji on 11/6/2018
 **/


public class CpnSpinner extends FrameLayout {
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.imArrowDown)
    ImageView imArrowDown;
    @BindView(R.id.rlRootView)
    RelativeLayout rlRootView;

    private int mGravity;
    private int mTextStyle;
    private Drawable mBackgroundDrawable;
    private Drawable mIconArrowDown;
    private int mPaddingLeft;

    public CpnSpinner(Context context) {
        super(context);
        initView(null);
    }

    public CpnSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public CpnSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.cpn_spinner, this, true);
        ButterKnife.bind(this);

        if (attrs != null) {
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
                    .CpnSpinner, 0, 0);
            mGravity = a.getInt(R.styleable.CpnSpinner_android_gravity, Gravity.NO_GRAVITY);
            mTextStyle = a.getInt(R.styleable.CpnSpinner_android_textStyle, NORMAL);
            mBackgroundDrawable = a.getDrawable(R.styleable.CpnSpinner_sp_background);
            mIconArrowDown = a.getDrawable(R.styleable.CpnSpinner_sp_icon_drop_down);
            mPaddingLeft = a.getDimensionPixelOffset(R.styleable.CpnSpinner_sp_paddingLeft, 0);
        }
        updateView();
    }

    private void updateView() {
        if (mBackgroundDrawable != null) {
            rlRootView.setBackgroundDrawable(mBackgroundDrawable);
        }
        if (mIconArrowDown != null) {
            imArrowDown.setImageDrawable(mIconArrowDown);
        }
    }


    public <T> void setData(List<T> listData, boolean hasHint) {
        if (listData == null) return;
        CustomSpinnerAdapter<T> adapter = new CustomSpinnerAdapter<>(getContext(), listData, mGravity, mTextStyle, mPaddingLeft, hasHint);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public <T> void setData(List<T> listData) {
        setData(listData, false);
    }

    public <T> T getSelectedData() {
        return (T) spinner.getSelectedItem();
    }

    public int getSelectedPosition() {
        return spinner.getSelectedItemPosition();
    }

    public void setSelection(int position) {
        spinner.setSelection(position);
    }

    public void setOnItemSelectedListener(Spinner.OnItemSelectedListener listener) {
        spinner.setOnItemSelectedListener(listener);
    }

    @OnTouch(R.id.flTouchFocusable)
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                FragmentUtil.hideKeyboard(view.getContext());
                break;
        }
        return false;
    }
}

class CustomSpinnerAdapter<T> extends ArrayAdapter<T> {
    private int mLayoutRes;
    private int mGravity;
    private int mTextStyle;
    private List<T> mListItem;
    private LayoutInflater mInflate;
    private int mPadding, mPaddingLeft;
    private boolean mHasHint;

    public CustomSpinnerAdapter(Context context, List<T> list, int gravity, int textStyle, int paddingLeft, boolean hasHint) {
        super(context, R.layout.item_cpn_spinner, list);
        this.mLayoutRes = R.layout.item_cpn_spinner;
        this.mListItem = list;
        this.mGravity = gravity;
        this.mTextStyle = textStyle;
        mInflate = LayoutInflater.from(context);
        mPadding = Utils.getDimensions(context, R.dimen.margin_padding_1_5x);
        mPaddingLeft = paddingLeft;
        this.mHasHint = hasHint;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent, true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent, false);
    }

    private View createItemView(int position, View convertView, ViewGroup parent, boolean isSetPadding) {
        final View view = mInflate.inflate(mLayoutRes, parent, false);
        if (isSetPadding) {
            view.setPadding(mPadding, mPadding, 0, mPadding);
        } else {
            view.setPadding(mPaddingLeft, 0, 0, 0);
        }
        final TextView textView = view.findViewById(R.id.tvValue);
        if (mGravity != -1) {
            textView.setGravity(mGravity);
        }
        if (mTextStyle != -1) {
            textView.setTypeface(null, mTextStyle);
        }
        textView.setText(mListItem.get(position).toString());
        if (mHasHint && position == 0) {
            textView.setTextColor(view.getContext().getResources().getColor(R.color.colorTextSub));
        } else {
            textView.setTextColor(view.getContext().getResources().getColor(R.color.colorNearBlack));
        }
        return view;
    }
}
