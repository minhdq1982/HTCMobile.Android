package com.tvo.htc.util;

import android.content.Context;

import com.tvo.htc.R;

public class ValidateFieldUtils {
    Context context;

    public ValidateFieldUtils(Context context) {
        this.context = context;
    }

    public boolean hasValidate(String field, Type type) {
        switch (type) {
            case NAME:
                return validName(field);
            case MAIL:
                return validMail(field);
            case PHONE:
                return validPhone(field);
            case LICENSE_PLATES:
                return validLicensePlates(field);
        }


        return true;
    }

    public boolean hasValidate(int positionSpinner, Type type) {
        switch (type) {
            case SPINNER_CITY:
                return validSpCity(positionSpinner);
            case SPINNER_AGENCY:
                return validSpAgency(positionSpinner);
        }
        return true;
    }

    public boolean hasValidate(String s1, String s2, Type type) {
        switch (type) {
            case DATE_TIME_APPOINTMENT:
                return validDateTime(s1, s2);
        }
        return true;
    }

    /***************** Private funtion ********************/
    private boolean validName(String name) {
        if (name.isEmpty()) {
            showMessage(context.getString(R.string.registration_empty_name));
            return false;
        }
        if (!Utils.isValidName(name)) {
            showMessage(context.getString(R.string.profile_error_invalid_name));
            return false;
        }
        return true;
    }

    private boolean validMail(String mail) {
        if (mail.isEmpty()) {
            showMessage(context.getString(R.string.registration_empty_email));
            return false;
        }
        if (!Utils.isValidEmail(mail)) {
            showMessage(context.getString(R.string.profile_info_error_email));
            return false;
        }
        return true;
    }

    private boolean validPhone(String phone) {
        if (phone.isEmpty()) {
            showMessage(context.getString(R.string.registration_empty_phone));
            return false;
        }
        if (!Utils.isValidPhoneNumber(phone)) {
            showMessage(context.getString(R.string.make_appointment_error_phone));
            return false;
        }
        return true;
    }

    private boolean validSpCity(int position) {
        if (position == 0) {
            showMessage(context.getString(R.string.registration_empty_city));
            return false;
        }
        return true;
    }

    private boolean validSpAgency(int position) {
        if (position == 0) {
            showMessage(context.getString(R.string.registration_empty_agency));
            return false;
        }
        return true;
    }

    private boolean validDateTime(String date, String time) {
        if (date.isEmpty()) {
            showMessage(context.getString(R.string.make_appointment_error_date_empty));
            return false;
        }
        if (!Utils.checkDateInputValidate(date)) {
            showMessage(context.getString(R.string.make_appointment_error_date_format));
            return false;
        }
        if (!Utils.checkTimeInputValidate(date, time)) {
            showMessage(context.getString(R.string.make_appointment_error_time_format));
            return false;
        }
        return true;
    }

    private boolean validLicensePlates(String licensePlates){
        if (licensePlates.isEmpty()){
            showMessage(context.getString(R.string.make_appointment_error_empty_licenseplates));
            return false;
        }
        if (!Utils.isValidLicensePlates(licensePlates)){
            showMessage(context.getString(R.string.make_appointment_error_license_plates));
            return false;
        }
        return true;
    }



    private void showMessage(String message) {
        Utils.showMessage(context, message);
    }


    public enum Type {
        NAME,
        MAIL,
        PHONE,
        SPINNER_AGENCY,
        SPINNER_CITY,
        DATE_TIME_APPOINTMENT,
        LICENSE_PLATES
    }
}
