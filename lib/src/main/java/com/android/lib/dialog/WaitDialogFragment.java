
package com.android.lib.dialog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.steam.sshop.lib.R;

/**
 * Created by ThinhNH on 20/08/2016.
 */
public class WaitDialogFragment extends DialogFragment {
    private boolean isShowing;

    public static WaitDialogFragment newInstance(boolean cancelable) {
        WaitDialogFragment dialogFragment = new WaitDialogFragment();
        dialogFragment.setCancelable(cancelable);
        Bundle bundle = new Bundle();
        dialogFragment.setArguments(bundle);
//        dialogFragment.setTargetFragment(fragment, 0);
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_wait, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // remove title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // no cancel
        setCancelable(false);
        // set color transpartent
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        Log.d("", "show dialog");
        if (isShowing) {
            return;
        }
        isShowing = true;
        FragmentTransaction ft = manager.beginTransaction();
        /**************** Edit by ThinhNH ************************/
//        ft.add(this, tag);
        if (tag == null) {
        	ft.add(this, getClass().getSimpleName());
        } else {
        	ft.add(this, tag);
        }
        /**************** End Edit by ThinhNH ************************/
        ft.commitAllowingStateLoss();
    }
    
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setTargetFragment(null, -1);
	}

    @Override
    public void dismiss() {
        isShowing = false;
        dismissAllowingStateLoss();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
