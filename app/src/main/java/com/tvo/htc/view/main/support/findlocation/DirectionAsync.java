package com.tvo.htc.view.main.support.findlocation;

import android.content.Context;
import android.os.AsyncTask;

import com.android.lib.util.Logger;
import com.google.android.gms.maps.model.LatLng;
import com.tvo.htc.R;

import java.util.ArrayList;

public class DirectionAsync extends AsyncTask<LatLng, Void, ArrayList<LatLng>> {

    private final Context mContext;
    private ArrayList<LatLng> listStep;
    private DirectionCallback setListener;

    public DirectionAsync(Context context) {
        mContext = context;
        listStep = new ArrayList<>();
    }

    public void setSetListener(DirectionCallback setListener) {
        this.setListener = setListener;
    }

    @Override
    protected ArrayList<LatLng> doInBackground(LatLng... latLngs) {

        String request = makeURL(latLngs[0], latLngs[1]);
        GetDirectionsTask task = new GetDirectionsTask(request);
        ArrayList<LatLng> list = task.testDirection();
        listStep.addAll(list);
        return listStep;
    }

    public String makeURL(LatLng latLngStart, LatLng latLngEnd) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(latLngStart.latitude);
        urlString.append(",");
        urlString.append(latLngStart.longitude);
        urlString.append("&destination=");// to
        urlString.append(latLngEnd.latitude);
        urlString.append(",");
        urlString.append(latLngEnd.longitude);
        urlString.append("&mode=driving");
        urlString.append("&key=").append(mContext.getString(R.string.map_api));
        Logger.d("makeURL: " + urlString);
        return urlString.toString();
    }

    @Override
    protected void onPostExecute(ArrayList<LatLng> latLngs) {
        super.onPostExecute(latLngs);
        setListener.directionCallback(latLngs);
    }

    interface DirectionCallback {
        void directionCallback(ArrayList<LatLng> latLngs);
    }
}
