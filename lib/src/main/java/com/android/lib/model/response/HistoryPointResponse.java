package com.android.lib.model.response;

import android.util.Pair;

import com.android.lib.model.History;
import com.android.lib.util.LibUtils;

import java.util.List;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class HistoryPointResponse extends BaseResponse {

    /**
     * data : {"totalCount":0,"items":[{"transactionCode":"string","membershipId":0,"membershipCardNumber":"string","benefitTypeId":0,"benefitTypeName":"string","transactionDate":"2018-11-29T02:38:52.389Z","type":"string","points":0,"userChange":"string","isActive":true,"id":0}]}
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
         * items : [{"transactionCode":"string","membershipId":0,"membershipCardNumber":"string","benefitTypeId":0,"benefitTypeName":"string","transactionDate":"2018-11-29T02:38:52.389Z","type":"string","points":0,"userChange":"string","isActive":true,"id":0}]
         */

        private int totalCount;
        private List<Items> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public static class Items implements History {
            /**
             * transactionCode : string
             * membershipId : 0
             * membershipCardNumber : string
             * benefitTypeId : 0
             * benefitTypeName : string
             * transactionDate : 2018-11-29T02:38:52.389Z
             * type : string
             * points : 0
             * userChange : string
             * isActive : true
             * id : 0
             */

            private String transactionCode;
            private String dealPointType;
            private String membershipCode;
            private String cardNumber;
            private String transactionDate;
            private int pointChTotal;
            private int amountChTotal;
            private String rankId;
            private String rankName;
            private String creatorUserEmail;
            private String code;

            public String getTransactionCode() {
                return transactionCode;
            }

            public void setTransactionCode(String transactionCode) {
                this.transactionCode = transactionCode;
            }

            public String getDealPointType() {
                return dealPointType;
            }

            public void setDealPointType(String dealPointType) {
                this.dealPointType = dealPointType;
            }

            public String getMembershipCode() {
                return membershipCode;
            }

            public void setMembershipCode(String membershipCode) {
                this.membershipCode = membershipCode;
            }

            public String getCardNumber() {
                return cardNumber;
            }

            public void setCardNumber(String cardNumber) {
                this.cardNumber = cardNumber;
            }

            public String getTransactionDate() {
                return transactionDate;
            }

            public void setTransactionDate(String transactionDate) {
                this.transactionDate = transactionDate;
            }

            public int getPointChTotal() {
                return pointChTotal;
            }

            public void setPointChTotal(int pointChTotal) {
                this.pointChTotal = pointChTotal;
            }

            public int getAmountChTotal() {
                return amountChTotal;
            }

            public void setAmountChTotal(int amountChTotal) {
                this.amountChTotal = amountChTotal;
            }

            public String getRankId() {
                return rankId;
            }

            public void setRankId(String rankId) {
                this.rankId = rankId;
            }

            public String getRankName() {
                return rankName;
            }

            public void setRankName(String rankName) {
                this.rankName = rankName;
            }

            public String getCreatorUserEmail() {
                return creatorUserEmail;
            }

            public void setCreatorUserEmail(String creatorUserEmail) {
                this.creatorUserEmail = creatorUserEmail;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            @Override
            public String getPreviewTitle() {
                return getPointChTotal() + "";
            }

            @Override
            public String getPreviewDate() {
                return LibUtils.getDate(getTransactionDate());
            }

            @Override
            public History.Type getTypeHistory() {
                return Type.GRADE_POINT;
            }

            @Override
            public int getAmount() {
                return getAmountChTotal();
            }

            @Override
            public Pair<String, String> getPreviewDataFirst() {
                return new Pair<>("card_history_point_title_first", getTransactionCode());
            }

            @Override
            public Pair<String, String> getPreviewDataSecond() {
                return new Pair<>("card_history_point_title_second", getCardNumber());
            }

            @Override
            public Pair<String, String> getPreviewDataThird() {
                return new Pair<>("card_history_point_title_third", getRankName());
            }
        }
    }
}
