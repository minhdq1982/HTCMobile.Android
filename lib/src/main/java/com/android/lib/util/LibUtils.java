package com.android.lib.util;

import android.annotation.SuppressLint;
import android.os.Build;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ThinhNH on 22/08/2016.
 */
public class LibUtils {
    public static final String FORMAT_DATETIME = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_DATETIME_OUTPUT = "dd/MM/yyyy";
    public static final String FORMAT_DATETIME_PARAM = "dd-MM-yyyy";
    public static final String FORMAT_TITLE = "dd MMM";
    public static final String FORMAT_TIME = "dd/MM/yyyy - HH:mm";

    public static final String FORMAT_TIME_APPOINTMENT = "dd/MM/yyyy HH:mm";

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        Logger.d("Android SDK: " + sdkVersion + " (" + release + ")");
        return release;
    }

    public static String convertDateToString(Date date, String format) {
        return convertDateToString(date, format, TimeZone.getDefault());
    }

    public static String convertDateToStringUTCTime(Date date) {
        return convertDateToStringUTCTime(date, FORMAT_DATETIME);
    }

    public static String convertDateToStringUTCTime(Date date, String format) {
        return convertDateToString(date, format, TimeZone.getTimeZone("UTC"));
    }

    public static String convertDateToString(Date date, String format, TimeZone timeZone) {
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        String reportDate = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
//            SimpleDateFormat df = new SimpleDateFormat(format, new Locale("vi", "VN"));
            df.setTimeZone(timeZone);

            // Using DateFormat format method we can create a string
            // representation of a date with the defined format.
            reportDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return reportDate;
    }

    public static String converterDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATETIME_OUTPUT);
        return format.format(date);
    }

    public static Date converterStringToDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATETIME_OUTPUT);
            return format.parse(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static Date convertStringToDate(String dateString) {
        return convertStringToDate(dateString, FORMAT_DATETIME, true);
    }

    public static Date convertStringToDate(String dateString, String format) {
        return convertStringToDate(dateString, format, true);
    }

    public static Date convertStringToDate(String dateString, String format, boolean isChangeToUTC) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (isChangeToUTC) {
//        dateFormat.setTimeZone(TimeZone.getDefault());
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDate(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        Date date = convertStringToDate(dateTime);
        if (date == null) {
            return null;
        }
        return convertDateToString(date, FORMAT_DATETIME_OUTPUT);
    }

    public static String getTimeAgo(String stringDate) {
        TimeZone timeZone = TimeZone.getDefault();
        Date date = convertStringToDate(stringDate);
        if (date == null) return stringDate;
        Calendar current = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        current.setTimeZone(TimeZone.getDefault());
        target.setTimeZone(timeZone);
        target.setTime(date);

        if (target.get(Calendar.DATE) == current.get(Calendar.DATE)) {
            if (target.get(Calendar.HOUR_OF_DAY) == current.get(Calendar.HOUR_OF_DAY)) {
                return current.get(Calendar.MINUTE) - target.get(Calendar.MINUTE) + " phút trước";
            }
            return current.get(Calendar.HOUR_OF_DAY) - target.get(Calendar.HOUR_OF_DAY) + " giờ trước";
        }
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_TITLE);
        return convertDateToDateOfYear(stringDate);
    }

    public static String getTimeAgoFromTimeStamp(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "ngay bây giờ";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1 phút trước";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " phút trước";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "1 giờ trước";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " giờ trước";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "hôm qua";
        } else if ((diff / DAY_MILLIS) < 30) {
            return diff / DAY_MILLIS + " ngày trước";
        } else {
            return getDateFromTimeStamp(time);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateFromTimeStamp(long time) {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(time));
    }

    public static String getFormatTitleDate(String stringDate) {
        Calendar targetDate = getRawCalendar(stringDate);
        if ((targetDate.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                && (targetDate.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH))
                && (targetDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))) {
            return "Hôm nay";
        }
        if ((targetDate.get(Calendar.DAY_OF_MONTH) + 1 == Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                && (targetDate.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH))
                && (targetDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))) {
            return "Hôm qua";
        }
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_TITLE);
        return convertDateToDateOfYear(stringDate);
    }

    public static boolean compareDate(String previousDate, String currentDate) {
        Calendar prevCal = getRawCalendar(previousDate);
        Calendar currentCal = getRawCalendar(currentDate);
        if (prevCal != null && currentCal != null) {
            return currentCal.after(prevCal);
        } else {
            return false;
        }
    }

    private static Calendar getRawCalendar(String stringDate) {
        Date date = convertStringToDate(stringDate);
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar;
        } else {
            return null;
        }
    }

    public static String getFormatCardDate(String activeDate) {
        Date d = convertStringToDate(activeDate);
        if (d != null) {
            return convertDateToString(d, FORMAT_DATETIME_OUTPUT);
        }
        return activeDate;
    }

    public static boolean isValidTime(String time) {
        try {
            Date.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static String getFormatTechnicalGuideDate(String modifiedDate) {
        if (modifiedDate != null) {
            modifiedDate = modifiedDate.replace("Z", "");
            Date date = convertStringToDate(modifiedDate);
            if (date != null) {
                SimpleDateFormat format = new SimpleDateFormat(FORMAT_TIME);
                return format.format(date);
            }
        }
        return "";
    }

    public static String revertFormatTime(String stringDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATETIME_OUTPUT);
            Date date = format.parse(stringDate);
            if (date != null) {
                format = new SimpleDateFormat(FORMAT_DATETIME);
                return format.format(date);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String getFormatBirthday(String birthday) {
        return getDate(birthday);
    }

    public static boolean checkSpecialCharacters(String string) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }

    public static boolean checkToday(String string) {
        try {
            Calendar calendar = Calendar.getInstance();
            long currentYear = calendar.get(Calendar.YEAR);
            long currentMonth = calendar.get(Calendar.MONTH) + 1;
            long currentDay = calendar.get(Calendar.DAY_OF_MONTH);

            String[] date = string.split("/");
            long inputYear = Long.parseLong(date[2]);
            long inputmonth = Long.parseLong(date[1]);
            long inputday = Long.parseLong(date[0]);

            if (inputYear > currentYear) {
                return true;
            } else if (inputYear == currentYear) {
                if (inputmonth > currentMonth) {
                    return true;
                } else if (inputmonth == currentMonth) {
                    return inputday > currentDay;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            return true;
        }
    }

    public static double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));

        double last = Math.round((Radius * c) * 10);

        return last / 10;
    }

    public static String ReplaceDot(double last) {
        return (last + "").replace(".", ",");
    }

    public static String getTimeStamp(String string) {
        String[] dateTime, time;
        String newDate;
        Timestamp newTimestamp;
        dateTime = string.split("T");
        time = dateTime[1].split("\\+");

        newDate = dateTime[0] + " " + time[0];
        newTimestamp = Timestamp.valueOf(newDate);

        return newTimestamp.getTime() + "";
    }

    public static BitmapDescriptor ResourceToDescriptor(int resource) {
        return BitmapDescriptorFactory.fromResource(resource);
    }

    public static String convertDateToDayOfWeek(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(LibUtils.convertStringToDate(date));
        long dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return "Chủ nhật";
        }
        return "Thứ " + dayOfWeek;
    }

    public static boolean checkDayOut(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(LibUtils.convertStringToDate(date));
        long today = Calendar.getInstance().getTimeInMillis();
        long inputday = calendar.getTimeInMillis();

        return inputday < today;
    }

    public static String getDateTimeFromString(String dateTime) {
        return convertDateToDateOfYear(dateTime) + "\n" + convertDateToTimeOfDay(dateTime);
    }

    public static String getDateTimeAppointment(String dateTime) {
        return convertDateToDayOfWeek(dateTime) + ", " + convertDateToDateOfYear(dateTime) + ", " + convertDateToTimeOfDay(dateTime);
    }

    public static String convertDateToTimeOfDay(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(LibUtils.convertStringToDate(date));
        long hour = calendar.get(Calendar.HOUR);
        long minute = calendar.get(Calendar.MINUTE);
        long am_pm = calendar.get(Calendar.AM_PM);
        String MINUTE;
        if (minute < 10) {
            MINUTE = "0" + minute;
        } else {
            MINUTE = minute + "";
        }
        if (am_pm == 0) {
            return hour + ":" + MINUTE + " AM";
        } else {
            if (hour == 0) {
                return 12 + ":" + MINUTE + " PM";
            }
            return hour + ":" + MINUTE + " PM";
        }
    }

    public static String convertDateToDateOfYear(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(LibUtils.convertStringToDate(date));
        long day = calendar.get(Calendar.DAY_OF_MONTH);
        long month = calendar.get(Calendar.MONTH) + 1;
        long year = calendar.get(Calendar.YEAR);

        return day + "/" + month + "/" + year;
    }

    /**
     * Convert GMT+7 to UTC
     *
     * @param date
     * @return
     */
    public static String revertFormatDateMakeAppointment(String date) {
        Date dateResult = convertStringToDate(date, FORMAT_TIME_APPOINTMENT, false);
        if (dateResult != null) {
            return convertDateToStringUTCTime(dateResult, FORMAT_DATETIME);
        }
        return "";
    }

    public static Calendar getCalMakeAppointMent(String date) {
        Calendar cal = Calendar.getInstance();
        if (!date.isEmpty()) cal.setTime(convertStringToDate(date, "dd/MM/yyyy"));

        return cal;
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }

    public static String getLastMonthDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
    }
}
