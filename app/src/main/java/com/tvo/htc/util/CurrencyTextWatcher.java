package com.tvo.htc.util;

import android.text.Editable;
import android.text.TextWatcher;

public class CurrencyTextWatcher implements TextWatcher {

    private boolean mEditing;

    public CurrencyTextWatcher() {
        mEditing = false;
    }

    public synchronized void afterTextChanged(Editable editable) {
        if(editable.length() == 0){
            return;
        }
        if (!mEditing) {
            mEditing = true;

            String string = editable.toString();

            String[] s = string.replace(".", "").split("");
            StringBuilder stringBuilder = new StringBuilder();
            String newString;
            int j = 0;
            for (int i = s.length - 1; i >= 0; i--) {
                stringBuilder.append(s[j]);
                if (i % 3 == 0 && i != 0) {
                    stringBuilder.append(".");
                }
                j++;
            }
            if (stringBuilder.charAt(0) == '.') {
                newString = stringBuilder.substring(1);
            } else {
                newString = stringBuilder.toString();
            }
            editable.replace(0, editable.length(), newString);

            mEditing = false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
