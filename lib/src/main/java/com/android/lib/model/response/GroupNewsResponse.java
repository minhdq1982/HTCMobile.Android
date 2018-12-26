package com.android.lib.model.response;

import java.util.List;

public class GroupNewsResponse extends BaseResponse {

    private List<GroupNews> data;

    public List<GroupNews> getData() {
        return data;
    }

    public void setData(List<GroupNews> data) {
        this.data = data;
    }
}
