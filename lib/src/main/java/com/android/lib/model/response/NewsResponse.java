package com.android.lib.model.response;

import com.android.lib.model.News;

public class NewsResponse extends BaseResponse {
    /**
     * data : {"id":11,"image":null,"title":"Tin từ HTC thông báo","shortContent":"Tin từ HTC thông báo","fullContent":"Thông báo tặng mỗi khách hàng 2 tỷ cho vui","newsgroupName":"Tin HTC","newsgroupName2":null,"source":"CMS","creationTime":"2018-11-09T00:00:00","lastModificationTime":null}
     * message : null
     */

    private News data;

    public News getNews() {
        return data;
    }

    public void setData(News news) {
        this.data = news;
    }
}
