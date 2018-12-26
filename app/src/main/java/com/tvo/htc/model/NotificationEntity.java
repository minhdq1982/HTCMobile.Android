package com.tvo.htc.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Create by Ngocji on 12/5/2018
 **/


public class NotificationEntity implements Parcelable {
    private Long Id;
    private String NotificationType;
    private String SurveyType;
    private String SurveyToken;
    private String title;
    private String content;

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNotificationType() {
        return NotificationType;
    }

    public void setNotificationType(String notificationType) {
        NotificationType = notificationType;
    }

    public String getSurveyType() {
        return SurveyType;
    }

    public void setSurveyType(String surveyType) {
        SurveyType = surveyType;
    }

    public String getSurveyToken() {
        return SurveyToken;
    }

    public void setSurveyToken(String surveyToken) {
        SurveyToken = surveyToken;
    }


    public NotificationEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.Id);
        dest.writeString(this.NotificationType);
        dest.writeString(this.SurveyType);
        dest.writeString(this.SurveyToken);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    protected NotificationEntity(Parcel in) {
        this.Id = (Long) in.readValue(Long.class.getClassLoader());
        this.NotificationType = in.readString();
        this.SurveyType = in.readString();
        this.SurveyToken = in.readString();
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Creator<NotificationEntity> CREATOR = new Creator<NotificationEntity>() {
        @Override
        public NotificationEntity createFromParcel(Parcel source) {
            return new NotificationEntity(source);
        }

        @Override
        public NotificationEntity[] newArray(int size) {
            return new NotificationEntity[size];
        }
    };
}
