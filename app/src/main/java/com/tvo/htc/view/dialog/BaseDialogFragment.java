package com.tvo.htc.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

import static android.view.Gravity.CENTER;

/**
 * Created by ThinhNH on 11/14/2015.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private FragmentManager mFragmentManager;

    protected abstract void init(View view);

    protected abstract int getLayoutID();

    @Override
    public void show(FragmentManager manager, String tag) {
        mFragmentManager = manager;
        FragmentTransaction ft = manager.beginTransaction();
//		ft.add(this, tag);
        String tagName = this.getClass().getSimpleName();
        Fragment fragment = manager.findFragmentByTag(tagName);
        if (fragment != null && fragment.getClass().getSimpleName().equals(this.getClass()
                .getSimpleName()) && fragment.isVisible()) {
            return;
        }
        ft.add(this, tagName);
//		ft.addToBackStack(tagName);
        ft.commitAllowingStateLoss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        ButterKnife.bind(this, view);
        FrameLayout rootView = new FrameLayout(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        rootView.setLayoutParams(params);
        if (!isFullScreen()) {
            int padding = getPaddingFromAttr();
            rootView.setPadding(padding, padding, padding, padding);
        }
        FrameLayout.LayoutParams paramsView = new FrameLayout.LayoutParams(-1, -2, CENTER);
        view.setLayoutParams(paramsView);
        view.setOnTouchListener((v, event) -> true);
        rootView.setOnClickListener(v -> {
            if (isCancel()) {
                dismiss();
            }
        });
        rootView.addView(view);
        ButterKnife.bind(rootView);
        init(view);
        return rootView;
    }

    protected boolean isFullScreen() {
        return false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(isCancel());
        getDialog().setCancelable(isCancel());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void dismiss() {
        hideKeyboard();
        dismissAllowingStateLoss();
        if (mFragmentManager != null) {
            String tagName = this.getClass().getSimpleName();
            mFragmentManager.popBackStack(tagName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public boolean isCancel() {
        return false;
    }


    /**
     * Hidden Keyboard
     */
    public void showKeyboard() {
        if (getActivity() == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Hidden Keyboard
     */
    public void hideKeyboard() {
        if (getActivity() == null) {
            return;
        }
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService
                (Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


    private int getPaddingFromAttr() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{android.R.attr.dialogPreferredPadding};
        int indexOfAttrTextSize = 0;
        TypedArray a = getContext().obtainStyledAttributes(typedValue.data, textSizeAttr);
        int padding = a.getDimensionPixelOffset(indexOfAttrTextSize, -1);
        a.recycle();
        return padding;
    }
}
