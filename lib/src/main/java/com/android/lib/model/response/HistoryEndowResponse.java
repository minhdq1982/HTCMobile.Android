package com.android.lib.model.response;

import android.util.Pair;

import com.android.lib.model.History;
import com.android.lib.util.LibUtils;

import java.util.List;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class HistoryEndowResponse extends BaseResponse {

    /**
     * data : {"totalCount":0,"items":[{"transactionCode":"string","membershipId":0,"membershipCardNumber":"string","benefitTypeId":0,"benefitTypeName":"string","transactionDate":"2018-11-28T07:07:47.733Z","typeIncentive":"string","numberUsing":0,"numberAccept":0,"programCode":"string","programName":"string","isActive":true,"id":0}]}
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
         * items : [{"transactionCode":"string","membershipId":0,"membershipCardNumber":"string","benefitTypeId":0,"benefitTypeName":"string","transactionDate":"2018-11-28T07:07:47.733Z","typeIncentive":"string","numberUsing":0,"numberAccept":0,"programCode":"string","programName":"string","isActive":true,"id":0}]
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
             * transactionDate : 2018-11-28T07:07:47.733Z
             * typeIncentive : string
             * numberUsing : 0
             * numberAccept : 0
             * programCode : string
             * programName : string
             * isActive : true
             * id : 0
             */

            private String transactionCode;
            private int membershipId;
            private String membershipCardNumber;
            private int benefitTypeId;
            private String benefitTypeName;
            private String transactionDate;
            private String typeIncentive;
            private int numberUsing;
            private int numberAccept;
            private String programCode;
            private String programName;
            private boolean isActive;
            private int id;

            public String getTransactionCode() {
                return transactionCode;
            }

            public void setTransactionCode(String transactionCode) {
                this.transactionCode = transactionCode;
            }

            public int getMembershipId() {
                return membershipId;
            }

            public void setMembershipId(int membershipId) {
                this.membershipId = membershipId;
            }

            public String getMembershipCardNumber() {
                return membershipCardNumber;
            }

            public void setMembershipCardNumber(String membershipCardNumber) {
                this.membershipCardNumber = membershipCardNumber;
            }

            public int getBenefitTypeId() {
                return benefitTypeId;
            }

            public void setBenefitTypeId(int benefitTypeId) {
                this.benefitTypeId = benefitTypeId;
            }

            public String getBenefitTypeName() {
                return benefitTypeName;
            }

            public void setBenefitTypeName(String benefitTypeName) {
                this.benefitTypeName = benefitTypeName;
            }

            public String getTransactionDate() {
                return transactionDate;
            }

            public void setTransactionDate(String transactionDate) {
                this.transactionDate = transactionDate;
            }

            public String getTypeIncentive() {
                return typeIncentive;
            }

            public void setTypeIncentive(String typeIncentive) {
                this.typeIncentive = typeIncentive;
            }

            public int getNumberUsing() {
                return numberUsing;
            }

            public void setNumberUsing(int numberUsing) {
                this.numberUsing = numberUsing;
            }

            public int getNumberAccept() {
                return numberAccept;
            }

            public void setNumberAccept(int numberAccept) {
                this.numberAccept = numberAccept;
            }

            public String getProgramCode() {
                return programCode;
            }

            public void setProgramCode(String programCode) {
                this.programCode = programCode;
            }

            public String getProgramName() {
                return programName;
            }

            public void setProgramName(String programName) {
                this.programName = programName;
            }

            public boolean isIsActive() {
                return isActive;
            }

            public void setIsActive(boolean isActive) {
                this.isActive = isActive;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }


            @Override
            public String getPreviewTitle() {
                return getTypeIncentive() + "";
            }

            @Override
            public String getPreviewDate() {
                return LibUtils.getDate(getTransactionDate());
            }

            @Override
            public Type getTypeHistory() {
                return Type.ENDOW;
            }

            @Override
            public int getAmount() {
                return 0;
            }

            @Override
            public Pair<String, String> getPreviewDataFirst() {
                return new Pair<>("card_history_endow_title_first", getTransactionCode());
            }

            @Override
            public Pair<String, String> getPreviewDataSecond() {
                return new Pair<>("card_history_endow_title_second", getProgramCode());
            }

            @Override
            public Pair<String, String> getPreviewDataThird() {
                return new Pair<>("card_history_endow_title_third", getProgramName());
            }
        }
    }
}
