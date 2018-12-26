package com.android.lib.model;

import android.util.Pair;

/**
 * Create by Ngocji on 10/25/2018
 **/


public interface History {
    String getPreviewTitle();

    String getPreviewDate();

    Type getTypeHistory();

    int getAmount();

    Pair<String, String> getPreviewDataFirst();

    Pair<String, String> getPreviewDataSecond();

    Pair<String, String> getPreviewDataThird();

    enum Type {
        GRADE_POINT,
        USE_POINT,
        ENDOW
    }

}
