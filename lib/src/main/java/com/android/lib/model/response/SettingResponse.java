package com.android.lib.model.response;

public class SettingResponse extends BaseResponse {

    /**
     * data : {"isSendSurveyCarNew":true,"isSendSurveyCarMaintenace":true,"surveyCarNewTime":5,"surveyCarMaintenaceTime":6,"surveyDeadLine":2,"facebookPageId":"This is facebook ID","facebookAccessToken":"This is facebook token","youTubeChannelId":"This is youtube channel ID","sync_Membership":18,"sync_Survey":6,"sync_News":10,"numberDayRemindToMaintenance":5,"hotline":"19001234","hotline_Complain":"19004321","email_Complain":"complaint@huyndai.com","sendSurveyViaSMS":false,"sendSurveyViaInApp":true,"sendRemindAppointmentViaSMS":true,"sendRemindAppointmentViaInApp":true,"sendPromotionViaSMS":true,"sendPromotionViaInApp":true}
     * message : null
     */

    private Setting data;

    public Setting getData() {
        return data;
    }

    public void setData(Setting data) {
        this.data = data;
    }

    public static class Setting {
        /**
         * isSendSurveyCarNew : true
         * isSendSurveyCarMaintenace : true
         * surveyCarNewTime : 5
         * surveyCarMaintenaceTime : 6
         * surveyDeadLine : 2
         * facebookPageId : This is facebook ID
         * facebookAccessToken : This is facebook token
         * youTubeChannelId : This is youtube channel ID
         * sync_Membership : 18
         * sync_Survey : 6
         * sync_News : 10
         * numberDayRemindToMaintenance : 5
         * hotline : 19001234
         * hotline_Complain : 19004321
         * email_Complain : complaint@huyndai.com
         * sendSurveyViaSMS : false
         * sendSurveyViaInApp : true
         * sendRemindAppointmentViaSMS : true
         * sendRemindAppointmentViaInApp : true
         * sendPromotionViaSMS : true
         * sendPromotionViaInApp : true
         */

        private boolean isSendSurveyCarNew;
        private boolean isSendSurveyCarMaintenace;
        private int surveyCarNewTime;
        private int surveyCarMaintenaceTime;
        private int surveyDeadLine;
        private String facebookPageId;
        private String facebookAccessToken;
        private String youTubeChannelId;
        private int sync_Membership;
        private int sync_Survey;
        private int sync_News;
        private int numberDayRemindToMaintenance;
        private String hotline;
        private String hotline_Complain;
        private String email_Complain;
        private boolean sendSurveyViaSMS;
        private boolean sendSurveyViaInApp;
        private boolean sendRemindAppointmentViaSMS;
        private boolean sendRemindAppointmentViaInApp;
        private boolean sendPromotionViaSMS;
        private boolean sendPromotionViaInApp;

        public boolean isIsSendSurveyCarNew() {
            return isSendSurveyCarNew;
        }

        public void setIsSendSurveyCarNew(boolean isSendSurveyCarNew) {
            this.isSendSurveyCarNew = isSendSurveyCarNew;
        }

        public boolean isIsSendSurveyCarMaintenace() {
            return isSendSurveyCarMaintenace;
        }

        public void setIsSendSurveyCarMaintenace(boolean isSendSurveyCarMaintenace) {
            this.isSendSurveyCarMaintenace = isSendSurveyCarMaintenace;
        }

        public int getSurveyCarNewTime() {
            return surveyCarNewTime;
        }

        public void setSurveyCarNewTime(int surveyCarNewTime) {
            this.surveyCarNewTime = surveyCarNewTime;
        }

        public int getSurveyCarMaintenaceTime() {
            return surveyCarMaintenaceTime;
        }

        public void setSurveyCarMaintenaceTime(int surveyCarMaintenaceTime) {
            this.surveyCarMaintenaceTime = surveyCarMaintenaceTime;
        }

        public int getSurveyDeadLine() {
            return surveyDeadLine;
        }

        public void setSurveyDeadLine(int surveyDeadLine) {
            this.surveyDeadLine = surveyDeadLine;
        }

        public String getFacebookPageId() {
            return facebookPageId;
        }

        public void setFacebookPageId(String facebookPageId) {
            this.facebookPageId = facebookPageId;
        }

        public String getFacebookAccessToken() {
            return facebookAccessToken;
        }

        public void setFacebookAccessToken(String facebookAccessToken) {
            this.facebookAccessToken = facebookAccessToken;
        }

        public String getYouTubeChannelId() {
            return youTubeChannelId;
        }

        public void setYouTubeChannelId(String youTubeChannelId) {
            this.youTubeChannelId = youTubeChannelId;
        }

        public int getSync_Membership() {
            return sync_Membership;
        }

        public void setSync_Membership(int sync_Membership) {
            this.sync_Membership = sync_Membership;
        }

        public int getSync_Survey() {
            return sync_Survey;
        }

        public void setSync_Survey(int sync_Survey) {
            this.sync_Survey = sync_Survey;
        }

        public int getSync_News() {
            return sync_News;
        }

        public void setSync_News(int sync_News) {
            this.sync_News = sync_News;
        }

        public int getNumberDayRemindToMaintenance() {
            return numberDayRemindToMaintenance;
        }

        public void setNumberDayRemindToMaintenance(int numberDayRemindToMaintenance) {
            this.numberDayRemindToMaintenance = numberDayRemindToMaintenance;
        }

        public String getHotline() {
            return hotline;
        }

        public void setHotline(String hotline) {
            this.hotline = hotline;
        }

        public String getHotline_Complain() {
            return hotline_Complain;
        }

        public void setHotline_Complain(String hotline_Complain) {
            this.hotline_Complain = hotline_Complain;
        }

        public String getEmail_Complain() {
            return email_Complain;
        }

        public void setEmail_Complain(String email_Complain) {
            this.email_Complain = email_Complain;
        }

        public boolean isSendSurveyViaSMS() {
            return sendSurveyViaSMS;
        }

        public void setSendSurveyViaSMS(boolean sendSurveyViaSMS) {
            this.sendSurveyViaSMS = sendSurveyViaSMS;
        }

        public boolean isSendSurveyViaInApp() {
            return sendSurveyViaInApp;
        }

        public void setSendSurveyViaInApp(boolean sendSurveyViaInApp) {
            this.sendSurveyViaInApp = sendSurveyViaInApp;
        }

        public boolean isSendRemindAppointmentViaSMS() {
            return sendRemindAppointmentViaSMS;
        }

        public void setSendRemindAppointmentViaSMS(boolean sendRemindAppointmentViaSMS) {
            this.sendRemindAppointmentViaSMS = sendRemindAppointmentViaSMS;
        }

        public boolean isSendRemindAppointmentViaInApp() {
            return sendRemindAppointmentViaInApp;
        }

        public void setSendRemindAppointmentViaInApp(boolean sendRemindAppointmentViaInApp) {
            this.sendRemindAppointmentViaInApp = sendRemindAppointmentViaInApp;
        }

        public boolean isSendPromotionViaSMS() {
            return sendPromotionViaSMS;
        }

        public void setSendPromotionViaSMS(boolean sendPromotionViaSMS) {
            this.sendPromotionViaSMS = sendPromotionViaSMS;
        }

        public boolean isSendPromotionViaInApp() {
            return sendPromotionViaInApp;
        }

        public void setSendPromotionViaInApp(boolean sendPromotionViaInApp) {
            this.sendPromotionViaInApp = sendPromotionViaInApp;
        }
    }
}
