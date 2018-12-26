package com.tvo.htc.util.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.android.lib.util.Logger;
import com.tvo.htc.R;
import com.tvo.htc.util.PermissionUtil;
import com.tvo.htc.view.BaseActivity;
import com.tvo.htc.view.dialog.ConfirmDialog;


/**
 * Created by ThinhNH on 01/03/2017.
 */
public class GPSTracker implements LocationListener {

    public static final int LOCATION_REQUEST_CODE = 1002;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    private static final String[] arrLocationPermission = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private static Location location;
    private static boolean checkGPS = false;
    private static boolean checkNetwork = false;
    private static boolean canGetLocation = false;
    private final Fragment mFragment;
    private MyLocationListener mListener;
    private LocationManager mLocationManager;

    public interface MyLocationListener extends LocationListener {
        void onLocationChanged(Location location);

        void onShownSettingsAlert();
    }

    public static boolean checkAndRequestGPSPermission(Fragment fragment) {
        return PermissionUtil.checkAndRequestPermissions(fragment, arrLocationPermission, LOCATION_REQUEST_CODE);
    }

    public GPSTracker(Fragment fragment, MyLocationListener listener) {
        this.mFragment = fragment;
        this.mListener = listener;
    }

    public void requestUpdateLocation() {
        if (checkAndRequestGPSPermission(mFragment)) {
            return;
        }

        if (mListener != null) {
            if (!LocationUtils.isGPSEnable(mFragment.getActivity())) {
                showSettingsAlert(mFragment.getActivity());
                mListener.onShownSettingsAlert();
            }

            Location location = getLocation(mFragment.getActivity(), this);
            if (location != null) {
                mListener.onLocationChanged(location);
            }

            // Reset get location
            setCanGetLocation(false);
        }
    }

    private Location getLocation(Activity activity,
                                       GPSTracker locationListener) {
        if (activity == null) {
            return null;
        }

        try {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission
                    .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                PermissionUtil.checkAndRequestPermissions(activity, arrLocationPermission);
            } else {
                mLocationManager = (LocationManager) activity.getSystemService
                        (Context.LOCATION_SERVICE);

                // getting GPS status
                checkGPS = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                // getting network status
                checkNetwork = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!checkGPS && !checkNetwork) {
                    Logger.d("NO LOCATION SERVICE PROVIDER AVAILABLE");
                } else {
                    canGetLocation = true;
                    if (checkNetwork) {
                        Logger.d("NETWORK AVAILABLE. REQUESTING LOCATION UPDATE FROM NETWORK PROVIDER");
                        // First get location from Network Provider
                        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);

                        location = mLocationManager.getLastKnownLocation(LocationManager
                                .NETWORK_PROVIDER);
                    }

                    if (checkGPS) {
                        Logger.d("GPS AVAILABLE. REQUESTING LOCATION UPDATE FROM GPS");
                        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES,
                                locationListener);

                        location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.d("ERROR GET LAST KNOWN LOCATION: " + e.getMessage());
        }

        Logger.d("LAST KNOWN LOCATION: " + location);
        return location;
    }

    public void stopUsingGPS() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(GPSTracker.this);
        }
    }

    public static boolean canGetLocation() {
        return canGetLocation;
    }

    public static void setCanGetLocation(boolean canGetLoc) {
        canGetLocation = canGetLoc;
    }

    private void showSettingsAlert(final Activity activity) {
        ConfirmDialog dialog = new ConfirmDialog.Builder()
                .setTextMessage(activity.getString(R.string.mesage_turn_on_gps))
                .create();
        dialog.show(((BaseActivity) activity).getSupportFragmentManager(), null);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (mListener == null || mFragment == null) {
            return;
        }
        mListener.onLocationChanged(location);
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (mListener != null) {
            mListener.onProviderDisabled(provider);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        if (mListener != null) {
            mListener.onProviderEnabled(provider);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (mListener != null) {
            mListener.onStatusChanged(provider, status, extras);
        }
    }
}