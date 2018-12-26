package com.tvo.htc.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import static com.tvo.htc.util.AppConstants.REQUEST_PERMISSION;

/**
 * Created by ThinhNH on 11/10/2016.
 */
public class PermissionUtil {
    public static String[] arrPermission = new String[]{
            Manifest.permission.INTERNET
    };

    public static boolean checkAndRequestPermissions(Activity activity) {
        return checkAndRequestPermissions(activity, arrPermission);
    }

    public static boolean checkAndRequestPermissions(Activity activity, String[] arrPermission) {
        boolean isRequestPermissions = false;
        if (activity == null) {
            return isRequestPermissions;
        }
        // Here, thisActivity is the current activity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return isRequestPermissions;
        }
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String s : arrPermission) {
            if (ContextCompat.checkSelfPermission(activity, s) != PackageManager
                    .PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, s)) {
                }

                listPermissionsNeeded.add(s);
            }
        }

        if (listPermissionsNeeded != null && listPermissionsNeeded.size() != 0) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String
                    [listPermissionsNeeded.size()]), REQUEST_PERMISSION);
            isRequestPermissions = true;
        }
        return isRequestPermissions;
    }

    public static boolean checkAndRequestPermission(Activity activity, String permission) {
        boolean isRequestPermission = false;
        if (activity == null || permission == null) {
            return isRequestPermission;
        }

        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager
                .PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            }

            ActivityCompat.requestPermissions(activity, new String[]{permission},
                    REQUEST_PERMISSION);
            isRequestPermission = true;
        }

        return isRequestPermission;
    }

    public static boolean checkAndRequestPermissions(Fragment fragment, String[] arrPermission, int requestCode) {
        boolean isRequestPermissions = false;
        if (fragment == null) {
            return isRequestPermissions;
        }
        // Here, thisActivity is the current activity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return isRequestPermissions;
        }
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : arrPermission) {
            if (ContextCompat.checkSelfPermission(fragment.getContext(), permission) != PackageManager
                    .PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(fragment.getActivity(), permission)) {
                }

                listPermissionsNeeded.add(permission);
            }
        }

        if (listPermissionsNeeded != null && listPermissionsNeeded.size() != 0) {
            fragment.requestPermissions(listPermissionsNeeded.toArray(new String
                    [listPermissionsNeeded.size()]), requestCode);
            isRequestPermissions = true;
        }
        return isRequestPermissions;
    }

    public static boolean checkAndRequestPermission(Fragment fragment, String permission, int requestCode) {
        return checkAndRequestPermissions(fragment, new String[]{permission}, requestCode);
    }

    public static boolean checkReadWriteExternal(Fragment fragment, int requestCode) {
        String[] per = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        return checkAndRequestPermissions(fragment, per, requestCode);
    }
}
