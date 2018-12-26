package com.android.lib.model.response;

import java.util.List;

/**
 * Create by Ngocji on 11/1/2018
 **/


public class GuildBookItemDetailResponse extends BaseResponse {

    private List<Item> data;

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    public static class Item {
        /**
         * title : Tiêu đề 2
         * content : Test api riêng
         * orderNumber : 1
         * parentId : null or int
         * guideBookId : 2
         * isActive : true
         * id : 17
         */

        private String title;
        private String content;
        private int orderNumber;
        private int parentId;
        private int guideBookId;
        private boolean isActive;
        private int id;
        private boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getGuideBookId() {
            return guideBookId;
        }

        public void setGuideBookId(int guideBookId) {
            this.guideBookId = guideBookId;
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
    }
}
