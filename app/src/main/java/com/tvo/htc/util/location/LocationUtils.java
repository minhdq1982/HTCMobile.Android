package com.tvo.htc.util.location;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import com.android.lib.util.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ThinhNH on 1/3/2017.
 */

public class LocationUtils {
    public static boolean isGPSEnable(Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService
                (Context.LOCATION_SERVICE);

        boolean isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return isGPSEnable;
    }

    public static String getAddress(Activity activity, Location location) {

        if (activity == null || location == null) {
            Logger.d("NO ACTIVITY AND NO LOCATION. CANNOT GET ADDRESS");
            return null;
        }

        String streetAddress = "";
        Logger.v("Longitude: " + location.getLongitude() + "Latitude: " + location.getLatitude());

        /*----------to get City-Name from coordinates ------------- */
        Geocoder gcd = new Geocoder(activity, Locale.getDefault());

        try {
            List<Address> addresses = gcd.getFromLocation(location.getLatitude(), location
                    .getLongitude(), 1);

            if (addresses.isEmpty()) {
                // EMPTY STRING
                return streetAddress;
            }

            Address address = addresses.get(0);

            StringBuilder streetAddressBuilder = new StringBuilder();

            if (address != null) {
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    streetAddressBuilder.append(address.getAddressLine(i));
                    if (i + 1 < address.getMaxAddressLineIndex()) {
                        streetAddressBuilder.append(", ");
                    }
                }
                streetAddress = streetAddressBuilder.toString();
                Logger.d("GEOCODE SUCCESS. RESULTS: " + streetAddress);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return streetAddress;
    }

    public static String getAddress(Context context, double lat, double lng) {

        if (context == null || lat == 0.0 || lng == 0.0) {
            Logger.d("NO ACTIVITY AND NO LOCATION. CANNOT GET ADDRESS");
            return null;
        }

        String streetAddress = "";
        Logger.v("Longitude: " + lng + "Latitude: " + lat);

        /*----------to get City-Name from coordinates ------------- */
        Geocoder gcd = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            Address address = addresses.get(0);

            StringBuilder streetAddressBuilder = new StringBuilder();

            if (address != null) {
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    streetAddressBuilder.append(address.getAddressLine(i));
                    if (i + 1 < address.getMaxAddressLineIndex()) {
                        streetAddressBuilder.append(", ");
                    }
                }
                streetAddress = streetAddressBuilder.toString();
                Logger.d("GEOCODE SUCCESS. RESULT: " + streetAddress);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return streetAddress;

    }
}
