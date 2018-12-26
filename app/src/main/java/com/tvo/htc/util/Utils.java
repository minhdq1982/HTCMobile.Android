package com.tvo.htc.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Patterns;
import android.widget.TextView;

import com.android.lib.RESTManager;
import com.android.lib.dialog.SimpleDialog;
import com.android.lib.model.Car;
import com.android.lib.model.response.DetailCarResponse;
import com.android.lib.model.response.InstallmentResponse;
import com.android.lib.model.response.LoginResponse;
import com.android.lib.model.response.SettingResponse;
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.tvo.htc.BuildConfig;
import com.tvo.htc.R;
import com.tvo.htc.model.LocalDataManager;
import com.tvo.htc.model.Question;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.view.main.survey.question.BaseQuestion;

import java.io.File;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.tvo.htc.util.AppConstants.HOT_LINE;

/**
 * Created by ThinhNH on 10/08/2016.
 */
public class Utils {
    public static final long CLICK_DELAY_MSEC_300 = 300;
    public static final long CLICK_DELAY_MSEC_500 = 500;
    public static final long CLICK_DELAY_MSEC_1000 = 1000;

    private static long mOldClickTime = 0;

    public static boolean isEnabledClick(long clickDelayMillis) {
        long time = System.currentTimeMillis();
        long diffTime = time - mOldClickTime;
        if (diffTime > 0 && diffTime < clickDelayMillis) {
            return false;
        }
        mOldClickTime = time;
        return true;
    }


    /***********************************************************************************************
     * Validate
     ***********************************************************************************************/
    /**
     * Checking the email is valid or not base on pre-defined pattern
     *
     * @param email : email need to be checked
     * @return : true if email is valid (not null, empty and contain valid character & pattern:xxx@yyy.zzz (x, y, z in : _A-Za-z0-9-.))
     * false otherwise
     */
    public static boolean isValidEmail(@NonNull String email) {
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(pattern).matcher(email).matches();
    }

    public static boolean isAppRunningForeground(final Context context) {
        String packageName = context.getPackageName();
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null) {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)
                        && processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getAppVersion() {
//        try {
//            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
//            String version = pInfo.versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

        return BuildConfig.VERSION_NAME;
    }

    public static String getImagePath(String image) {
        if (image != null) {
            return RESTManager.BASE_URL + image.replace(" ", "%20");
        } else {
            return RESTManager.BASE_URL + image;
        }
    }

    public static String getMoney(String money) {
        String result = null;

        try {
//            for (int i = money.length(); i >= 0; i --) {
//                if ()
//            }
//            DecimalFormat format = new DecimalFormat("#,###.00");
            DecimalFormat format = new DecimalFormat("#,###");

//            NumberFormat format =NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//            double dMoney = Double.parseDouble(money);
//            result = format.format(dMoney);

            result = format.format(Double.parseDouble(money)).replace(".", ",") + " VNĐ";
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean hasLogin(Context context) {
        LoginResponse loginResponse = LocalDataManager.getInstance(context).getLoginResponse();
        return hasLogin(context, loginResponse);
    }

    public static boolean hasLogin(Context context, LoginResponse loginResponse) {
        boolean hasLogin = false;
        if (context == null || loginResponse == null) {
            return hasLogin;
        }
        if (loginResponse != null && loginResponse.getData() != null && LocalDataManager.getInstance(context).getAccessToken() != null) {
            hasLogin = true;
        }

        return hasLogin;
    }

    /***********************************************************************************************
     * SHOW DIALOG
     ***********************************************************************************************/
    public static void showMessage(Context context, String message) {
        showMessage(context, message, null);
    }

    public static void showMessage(Context context, String message, SimpleDialog.Callbacks callbacks) {
        if (message == null || context == null) {
            return;
        }
        SimpleDialog dialog = new SimpleDialog();
        dialog.init(context, message, callbacks);
        dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), null);
    }
    /***********************************************************************************************
     * SHOW DIALOG
     ***********************************************************************************************/

    /**
     * Trim and Replace space in string
     */
    public static String normalizeString(String text, boolean isReplaceSpace) {
        text = text.trim();
        if (isReplaceSpace) text = text.replace(" ", "");
        return text;
    }

    /**
     * Converter date to pair
     *
     * @param s: ex: 2018-10-11 14:16:27.216448
     *           return: first: date, second: time
     */

    //TODO: refactor this when time api receiver
    public static Pair<String, String> splitDateTimeToPair(String s) {
        if (s.contains(" ")) {
            String[] pair = s.split(" ");
            return new Pair<>(pair[0], pair[1]);
        }
        return new Pair<>("", "");
    }

    public static void callHotLine(Context context, String hotline) {
        if (context == null || hotline == null || hotline.isEmpty()) {
            Utils.showMessage(context, context.getString(R.string.installment_error));
            return;
        }
        if (!Utils.callPhone(context, hotline)) {
            Utils.showMessage(context, context.getString(R.string.support_not_validate_phone_number));
        }
    }

    /**
     * call phone number
     *
     * @param context:     context
     * @param phoneNumber: phone number: ex: 0919191919
     * @return: true if phone is correct, false if phone not correct
     */
    @SuppressLint("MissingPermission")
    public static boolean callPhone(Context context, String phoneNumber) {
        boolean isValidate = Patterns.PHONE.matcher(phoneNumber).matches();
        if (isValidate) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            context.startActivity(intent);
        }
        return isValidate;
    }

    /**
     * go to web url
     *
     * @param context: context
     * @param url:     String
     */
    public static void goToUrl(Context context, String url) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get string use name res
     *
     * @param context: Context
     * @param nameRes: name of string item
     * @return string
     */
    public static String getStringResId(Context context, String nameRes) {
        return context.getString(context.getResources().getIdentifier(nameRes, "string", context.getPackageName()));
    }

    /**
     * @param context
     * @param idRes
     * @return list string
     */
    public static List<String> getArrayStringResId(Context context, @ArrayRes int idRes) {
        return Arrays.asList(context.getResources().getStringArray(idRes));
    }

    /**
     * get dimen in resource
     *
     * @param context  : context
     * @param dimensId : dimensions id in value
     * @return : px of dimensions
     */
    public static int getDimensions(Context context, int dimensId) {
        if (context != null)
            return context.getResources().getDimensionPixelOffset(dimensId);
        else return 0;
    }

    /**
     * Validate indentify ID
     *
     * @param identifyId
     * @return
     */

    public static boolean isValidIdentifyId(String identifyId) {
        String regex = "[0-9]*";
        String regex2 = "^[A-Za-z]{1}[0-9]{7}";

        return identifyId.matches(regex2) || (identifyId.matches(regex) && (identifyId.length() == 9 || identifyId.length() == 12));
    }

    public static String changeDateToDefault(String date) {
        String year = date.substring(4, 8);
        try {
            return "31/12/" + Integer.parseInt(year);
        } catch (Exception e) {
        }
        return AppConstants.DATE_DEFAULT;
    }

    public static boolean isValidName(String name) {
        name = name.toLowerCase();
        boolean isValid = true;
        String regexNumber = "[0-9@#$%^&*()-_+=!~,/?'\":;{}\\[\\]]";
        for (int i = 0; i < name.length(); i++) {
            if (String.valueOf(name.charAt(i)).matches(regexNumber)) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public static String validateMoney(String string) {
        String[] s = string.replace(".", "").split("");
        StringBuilder newString = new StringBuilder();
        int j = 0;
        for (int i = s.length - 1; i >= 0; i--) {
            newString.append(s[j]);
            if (i % 3 == 0 && i != 0) {
                newString.append(".");
            }
            j++;
        }
        if (newString.charAt(0) == '.') {
            return newString.substring(1);
        }
        return newString.toString();
    }

    public static int getDrawableResId(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    /**
     * Check data after current day
     *
     * @param dateInput : dd/mm/yyyy
     * @return true - date input is after current day.
     */
    public static boolean checkDateInputValidate(String dateInput) {
        try {
            if (LibUtils.isValidTime(dateInput)) {
                Date date = LibUtils.convertStringToDate(dateInput, "dd/MM/yyyy", false);
                if (date != null) {
                    Calendar currentCal = Calendar.getInstance();
                    Calendar inputCal = Calendar.getInstance();
                    inputCal.setTime(date);
                    setDefaultTime(inputCal);
                    setDefaultTime(currentCal);
                    return inputCal.getTimeInMillis() >= currentCal.getTimeInMillis();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void setDefaultTime(Calendar cal) {
        cal.set(Calendar.AM_PM, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    /**
     * @param time : format 09:00
     * @return
     */
    public static boolean checkTimeInputValidate(String dateInput, String time) {
        try {
            Date date = LibUtils.convertStringToDate(dateInput, "dd/MM/yyyy", false);
            if (date != null) {
                Calendar current = Calendar.getInstance();
                current.setTime(date);
                String[] ss = time.split(":");
                current.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ss[0]));
                current.set(Calendar.MINUTE, Integer.parseInt(ss[1]));
                return current.getTimeInMillis() > System.currentTimeMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (phone.length() < 10) {
            return false;
        } else {
            if (phone.length() == 10 && phone.charAt(0) == '0') {
                return true;
            }
            return phone.length() == 11 && phone.charAt(0) == '8' && phone.charAt(1) == '4';
        }
    }

    public static boolean isValidLicensePlates(String licensePlates) {
        licensePlates = licensePlates.toUpperCase();
        String regex = "[a-zA-Z0-9]*";
        return licensePlates.matches(regex);
    }

    public static String validateTimeInput(int d) {
        if (d < 10) {
            return "0" + d;
        } else {
            return d + "";
        }
    }

    public static String getHotline(String hotline) {
        if (hotline.charAt(0) == '+' && hotline.charAt(1) == '8' && hotline.charAt(2) == '4') {
            return hotline.replaceFirst("\\+84", "0");
        } else if (hotline.charAt(0) == '8' && hotline.charAt(1) == '4') {
            return hotline.replaceFirst("84", "0");
        }
        return hotline;
    }

    public static Drawable getDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }

    public static <T> String listToString(List<T> list) {
        if (list != null) {
            return new Gson().toJson(list);
        } else {
            return "";
        }
    }

    public static <T extends Type> Object stringToList(String string, T type) {
        if (string != null && !string.isEmpty()) {
            return new Gson().fromJson(string, type);
        }
        return null;
    }

    public static String questionToString(Pair<BaseQuestion.HeaderType, List<Question>> question) {
        return new Gson().toJson(question);
    }

    public static Pair<BaseQuestion.HeaderType, List<Question>> stringToQuestion(String s) {
        Type type = new TypeToken<Pair<BaseQuestion.HeaderType, List<Question>>>() {
        }.getType();
        return new Gson().fromJson(s, type);
    }

    public static List<InstallmentResponse> installmentCalculation(String total, String loan, String time, String firstInterest, String nextInterest) {
        List<InstallmentResponse> arr = new ArrayList<>();

        float tot = Float.parseFloat(total.replace(".", ""));
        float loa = Float.parseFloat(loan.replace(".", ""));
        float tim = Float.parseFloat(time);
        float first = Float.parseFloat(firstInterest) / 100;
        float next = Float.parseFloat(nextInterest) / 100;

        int root = (int) loa;
        int originalInMonth = 0;
        int interest;
        int originalAndInterest;

        int totalInterest = 0;
        int totalPay = 0;

        for (int i = 0; i < Integer.parseInt(time); i++) {
            if (i != 0)
                root = root - originalInMonth;

            originalInMonth = Math.round(loa / tim);

            if (i < 12)
                interest = Math.round(root * (first / 12));
            else
                interest = Math.round(root * (next / 12));
            Logger.e("Rete: " + interest);
            originalAndInterest = originalInMonth + interest;

            totalInterest += interest;
            totalPay += originalInMonth;
            arr.add(new InstallmentResponse(i + 1, root, originalInMonth, interest, originalAndInterest, totalInterest, totalPay));
        }

        return arr;
    }

    public static Pair<Double, Double> installmentCalculation(double borrow, int numberMonth, double interestRate1, double interestRate2) {
        double rate1 = interestRate1 / 100.0;
        double rate2 = interestRate2 / 100.0;
        Logger.e("Rate: " + rate1 + "/" + rate2);
        return Pair.create(rate1, rate2);
    }

    public static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

    public static DetailCarResponse removeOtherVersion(DetailCarResponse detailCarResponse, int verId) {
        if (detailCarResponse.getData() != null && detailCarResponse.getData().getVersion() != null
                && !detailCarResponse.getData().getVersion().isEmpty()) {
            for (int i = detailCarResponse.getData().getVersion().size() - 1; i >= 0; i--) {
                if (detailCarResponse.getData().getVersion().get(i).getId() != verId) {
                    detailCarResponse.getData().getVersion().remove(i);
                }
            }
        }

        return detailCarResponse;
    }

    public static <T extends Object> T cloneObject(T obj) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(obj), (Type) obj.getClass());
    }

    public static void setFontStyle(TextView textView, boolean isBold) {
        int type = Typeface.NORMAL;
        if (isBold) {
            type = Typeface.BOLD;
        }
        textView.setTypeface(Typeface.create(textView.getTypeface(), type));
    }

    public static DisplayMetrics getDisplayMetric(Context context) {
        return context.getResources().getDisplayMetrics();
    }


    public static boolean hasCarVersion(Car car) {
        return car != null && car.getVersion() != null && !car.getVersion().isEmpty();
    }

    public static boolean hasMultiCarVersion(Car car) {
        return hasCarVersion(car) && car.getVersion().size() > 1;
    }

    public static void openGoogleMap(Context context, Location myLocation, LatLng otLocation) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/?api=1&travelmode=driving&dir_action=navigate&destination=" + otLocation.latitude + "," + otLocation.longitude));
        context.startActivity(intent);
    }


    public static void openYoutubeChanel(Context context) {
        SettingResponse.Setting setting = SessionDataManager.getInstance().getSetting();
        if (setting == null
                || setting.getYouTubeChannelId() == null) {
            return;
        }
        String url = "http://www.youtube.com/channel/" + setting.getYouTubeChannelId();
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void openFacebookPage(Context context) {
        SettingResponse.Setting setting = SessionDataManager.getInstance().getSetting();
        if (setting == null
                || setting.getFacebookPageId() == null) {
            return;
        }
        String url;
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            url = "fb://page/" + setting.getFacebookPageId();
        } catch (Exception e) {
            url = "https://www.facebook.com/" + setting.getFacebookPageId();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void clearMemory(){
        System.gc();
    }
}
