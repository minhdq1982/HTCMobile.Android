
package com.android.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.lib.util.Logger;
import com.steam.sshop.lib.R;

/**
 * Created by ThinhNH on 20/08/2016.
 */
public class SimpleDialog extends DialogFragment implements OnClickListener {
    private final String TAG = getClass().getSimpleName();
    private Button btnOk;

    private Context context;
    private Callbacks callbacks;
    private String message;
    private TextView txtMessage;
    private boolean isNotError = false;
    private String btText;

    public interface Callbacks {
        void clickOKButton();
    }

    public boolean isNotError() {
        return isNotError;
    }

    public void setNotError(boolean isNotError) {
        this.isNotError = isNotError;
    }

    public SimpleDialog() {

    }

    public void init(Context context, String message, Callbacks callbacks) {
        init(context, message, null, callbacks);
    }

    public void init(Context context, String message, String btText, Callbacks callbacks) {
        this.context = context;
        this.message = message;
        this.btText = btText;
        this.callbacks = callbacks;

        if (this.context != null) {
            Log.v(TAG, this.context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_simple, container, false);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

        // remove title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // no cancel
        setCancelable(false);
        // set color transpartent
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        txtMessage = (TextView) view.findViewById(R.id.tvMessage);
        txtMessage.setText(message);

        btnOk = (Button) view.findViewById(R.id.ok);
        if (btText != null && !btText.isEmpty()) {
            btnOk.setText(btText);
        }
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public void show(FragmentManager manager, String tag) {
        Logger.d("show dialog");
        if (tag == null) {
            tag = getClass().getSimpleName();
        }
        String tagName = this.getClass().getSimpleName();
        Fragment fragment = manager.findFragmentByTag(tagName);
        if (fragment != null && fragment.getClass().getSimpleName().equals(tag) && fragment.isAdded()) {
            return;
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        /**************** End Edit by ThinhNH ************************/
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int i = v.getId();
        if (i == R.id.ok) {
            dismiss();
            if (callbacks != null) {
                callbacks.clickOKButton();
            }
        } else {
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
