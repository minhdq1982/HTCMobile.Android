package com.android.lib.model.response;

import com.android.lib.model.News;

import java.util.List;

public class PromotionListResponse extends BaseResponse {
    /**
     * data : {"agencyPromotion":[{"image":"string","title":"string","shortContent":"string","fullContent":"string","type":0,"createDate":"2018-10-19T07:31:00.558Z","modifiedDate":"2018-10-19T07:31:00.558Z","id":0}],"htcPromotion":[{"image":"string","title":"string","shortContent":"string","fullContent":"string","type":0,"createDate":"2018-10-19T07:31:00.558Z","modifiedDate":"2018-10-19T07:31:00.558Z","id":0}]}
     */

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<News> agencyPromotion;
        private List<News> htcPromotion;

        public List<News> getAgencyPromotion() {
            return agencyPromotion;
        }

        public void setAgencyPromotion(List<News> agencyPromotion) {
            this.agencyPromotion = agencyPromotion;
        }

        public List<News> getHtcPromotion() {
            return htcPromotion;
        }

        public void setHtcPromotion(List<News> htcPromotion) {
            this.htcPromotion = htcPromotion;
        }
    }
}
