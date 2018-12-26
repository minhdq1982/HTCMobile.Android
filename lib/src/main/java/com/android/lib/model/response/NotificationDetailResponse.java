package com.android.lib.model.response;

import com.android.lib.model.response.NotificationResponse.Notification;

public class NotificationDetailResponse extends BaseResponse {


    /**
     * data : {"userId":0,"title":"string","shortContent":"string","content":"string","hasRead":true,"notificationType":"string","notifiedTime":"2018-12-05T10:05:38.973Z","id":0}
     * unreadNotification : 0
     */

    private Notification data;
    private int unreadNotification;

    public Notification getData() {
        return data;
    }

    public void setData(Notification data) {
        this.data = data;
    }

    public int getUnreadNotification() {
        return unreadNotification;
    }

    public void setUnreadNotification(int unreadNotification) {
        this.unreadNotification = unreadNotification;
    }

}
