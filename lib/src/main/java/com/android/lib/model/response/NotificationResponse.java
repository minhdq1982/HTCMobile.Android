package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Create by Ngocji on 10/22/2018
 **/


public class NotificationResponse extends BaseResponse {

    /**
     * data : [{"userId":0,"title":"string","shortContent":"string","content":"string","hasRead":true,"notificationType":"string","notifiedTime":"2018-12-05T04:26:16.251Z","id":0}]
     * unreadNotification : 0
     */

    private int unreadNotification;
    private List<Notification> data;

    public int getUnreadNotification() {
        return unreadNotification;
    }

    public void setUnreadNotification(int unreadNotification) {
        this.unreadNotification = unreadNotification;
    }

    public List<Notification> getData() {
        return data;
    }

    public void setData(List<Notification> data) {
        this.data = data;
    }

    public enum NotificationType {
        Other("Other"),
        Survey("Survey"),
        Promotion("Promotion"),
        MaintenanceRemind("MaintenanceRemind"),
        Birthday("Birthday"),
        None("None");

        public String type;

        NotificationType(String notificationType) {
            this.type = notificationType;
        }
    }

    public static class Notification implements Parcelable {
        /**
         * userId : 0
         * title : string
         * shortContent : string
         * content : string
         * hasRead : true
         * notificationType : string
         * notifiedTime : 2018-12-05T04:26:16.251Z
         * id : 0
         */

        private int userId;
        private String title;
        private String shortContent;
        private String content;
        private boolean hasRead;
        private String notificationType;
        private String notifiedTime;
        private int id;
        private String surveyToken;
        private String surveyType;

        public String getSurveyToken() {
            return surveyToken;
        }

        public void setSurveyToken(String surveyToken) {
            this.surveyToken = surveyToken;
        }

        public String getSurveyType() {
            return surveyType;
        }

        public void setSurveyType(String surveyType) {
            this.surveyType = surveyType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public boolean isRead() {
            return hasRead;
        }

        public void setRead(boolean hasRead) {
            this.hasRead = hasRead;
        }

        public NotificationType getNotificationType() {
            for (NotificationType type: NotificationType.values()) {
                if(type.type.equalsIgnoreCase(notificationType)) {
                    return type;
                }
            }
            return NotificationType.None;
        }

        public void setNotificationType(String notificationType) {
            this.notificationType = notificationType;
        }

        public String getNotifiedTime() {
            return notifiedTime;
        }

        public void setNotifiedTime(String notifiedTime) {
            this.notifiedTime = notifiedTime;
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
            dest.writeInt(this.userId);
            dest.writeString(this.title);
            dest.writeString(this.shortContent);
            dest.writeString(this.content);
            dest.writeByte(this.hasRead ? (byte) 1 : (byte) 0);
            dest.writeString(this.notificationType);
            dest.writeString(this.notifiedTime);
            dest.writeInt(this.id);
        }

        public Notification() {
        }

        protected Notification(Parcel in) {
            this.userId = in.readInt();
            this.title = in.readString();
            this.shortContent = in.readString();
            this.content = in.readString();
            this.hasRead = in.readByte() != 0;
            this.notificationType = in.readString();
            this.notifiedTime = in.readString();
            this.id = in.readInt();
        }

        public static final Creator<Notification> CREATOR = new Creator<Notification>() {
            @Override
            public Notification createFromParcel(Parcel source) {
                return new Notification(source);
            }

            @Override
            public Notification[] newArray(int size) {
                return new Notification[size];
            }
        };
    }
}
