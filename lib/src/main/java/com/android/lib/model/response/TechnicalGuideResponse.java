package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Create by Ngocji on 10/29/2018
 **/


public class TechnicalGuideResponse extends BaseResponse {

    private List<Item> data;

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    public static class Item implements Parcelable {
        /**
         * title : string
         * image : string
         * shortContent : string
         * content : string
         * isActive : true
         * lastModificationTime : 2018-11-07T03:28:01.066Z
         * creationTime : 2018-11-07T03:28:01.066Z
         * id : 0
         */

        private String title;
        private String image;
        private String shortContent;
        private String content;
        private boolean isActive;
        private String lastModificationTime;
        private String creationTime;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getShortContent() {
            return shortContent;
        }

        public void setShortContent(String shortContent) {
            this.shortContent = shortContent;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getLastModificationTime() {
            return lastModificationTime;
        }

        public void setLastModificationTime(String lastModificationTime) {
            this.lastModificationTime = lastModificationTime;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.image);
            dest.writeString(this.shortContent);
            dest.writeString(this.content);
            dest.writeByte(this.isActive ? (byte) 1 : (byte) 0);
            dest.writeString(this.lastModificationTime);
            dest.writeString(this.creationTime);
            dest.writeInt(this.id);
        }

        public Item() {
        }

        protected Item(Parcel in) {
            this.title = in.readString();
            this.image = in.readString();
            this.shortContent = in.readString();
            this.content = in.readString();
            this.isActive = in.readByte() != 0;
            this.lastModificationTime = in.readString();
            this.creationTime = in.readString();
            this.id = in.readInt();
        }

        public static final Creator<Item> CREATOR = new Creator<Item>() {
            @Override
            public Item createFromParcel(Parcel source) {
                return new Item(source);
            }

            @Override
            public Item[] newArray(int size) {
                return new Item[size];
            }
        };
    }
}
