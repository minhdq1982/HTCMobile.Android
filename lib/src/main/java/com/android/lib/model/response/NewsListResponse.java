package com.android.lib.model.response;

import com.android.lib.model.News;

import java.util.List;

public class NewsListResponse extends BaseResponse {

    /**
     * data : {"totalCount":0,"items":[{"image":"string","title":"string","shortContent":"string","fullContent":"string","source":"string","creationTime":"2018-11-16T08:19:19.547Z","lastModificationTime":"2018-11-16T08:19:19.547Z","id":0}]}
     */

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * totalCount : 0
         * items : [{"image":"string","title":"string","shortContent":"string","fullContent":"string","source":"string","creationTime":"2018-11-16T08:19:19.547Z","lastModificationTime":"2018-11-16T08:19:19.547Z","id":0}]
         */

        private int totalCount;
        private List<News> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<News> getItems() {
            return items;
        }

        public void setItems(List<News> items) {
            this.items = items;
        }
    }

    //0 : Tin HTC
    //1 : Tin Thị trường
    //2 : Khuyến mãi từ Đại lý
    //3 : Khuyến mãi từ HTC
    //4 : Tin Facebook
    //5 : Tin Youtube
    public static class TYPE {
        public static final int NEWS_HTC= 0;
        public static final int NEWS_MARKET= 1;
        public static final int PROMOTION_AGENCY= 2;
        public static final int PROMOTION_HTC= 3;
        public static final int NEWS_FACEBOOK= 4;
        public static final int NEWS_YOUTUBE= 5;
    }
}
