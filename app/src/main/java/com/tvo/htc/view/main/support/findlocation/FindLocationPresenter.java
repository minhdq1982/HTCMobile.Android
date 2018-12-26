package com.tvo.htc.view.main.support.findlocation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.RESTManager;
import com.android.lib.http.IRequestListener;
import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CityResponse;
import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.google.android.gms.maps.model.LatLng;
import com.tvo.htc.model.SessionDataManager;
import com.tvo.htc.util.location.GPSTracker;
import com.tvo.htc.view.BasePresenter;

import java.util.Collections;
import java.util.List;

public class FindLocationPresenter extends BasePresenter<FindLocationContract.View>
        implements FindLocationContract.Presenter {
    private GPSTracker mGPSTracker;
    private boolean hasDetectLocation;

    private List<AgenciesResponse.Agency> mList;
    private Location mLocation;

    private LatLng myLatLng, ot1LatLng, ot2LatLng;

    public FindLocationPresenter(Context context) {
        super(context);

        mList = SessionDataManager.getInstance().getAgencies();
    }

    @Override
    public void loadMarker() {
        if (mList == null) {
            RESTManager.getInstance().getAgencies(new IRequestListener<AgenciesResponse>() {
                @Override
                public void onCompleted(AgenciesResponse data) {
                    super.onCompleted(data);
                    SessionDataManager.getInstance().setAgencies(data.getData());
                    if (getView() != null) {
                        getView().displayMarker(data.getData());
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    super.onFailed(throwable);
                }
            });
        } else {
            if (getView() != null) {
                getView().displayMarker(mList);
            }
        }
    }

    @Override
    public void loadMarkerNearBy(Location location, int mType) {
        RESTManager.getInstance().getAgenciesNearBy(location.getLatitude(), location.getLongitude(), mType,
                new IRequestListener<AgenciesResponse>() {
                    @Override
                    public void onCompleted(AgenciesResponse data) {
                        super.onCompleted(data);
                        mLocation = location;
                        softing(data.getData(), mLocation.getLatitude(), mLocation.getLongitude());
                        if (getView() != null) {
                            getView().displayMarker(data.getData());
                        }
                    }
                });
    }

    private void softing(List<AgenciesResponse.Agency> data, double latitude, double longitude) {
        Collections.sort(data, (o1, o2) -> {
            myLatLng = new LatLng(latitude, longitude);
            ot1LatLng = new LatLng(o1.getLatitude(), o1.getLongitude());
            ot2LatLng = new LatLng(o2.getLatitude(), o2.getLongitude());
            double data1 = LibUtils.CalculationByDistance(myLatLng, ot1LatLng);
            double data2 = LibUtils.CalculationByDistance(myLatLng, ot2LatLng);
            return Double.compare(data1, data2);
        });
    }

    @Override
    public void loadMyLocation(Fragment fragment) {
        mGPSTracker = new GPSTracker(fragment, new GPSTracker.MyLocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Logger.d("" + location);
                if (getView() != null) {
                    getView().displayMyLocation(location);

                    if (!hasDetectLocation) {
                        getView().displayNearbyLocation(location);
                    }
                }
                hasDetectLocation = true;
            }

            @Override
            public void onShownSettingsAlert() {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
        mGPSTracker.requestUpdateLocation();
    }

    @Override
    public void loadOnItemClick(List<AgenciesResponse.Agency> items, int position) {
        if (getView() != null) {
            getView().displayOnItemClick(items, position);
        }
    }

    @Override
    public void loadOnPageChange(List<AgenciesResponse.Agency> items, int i) {
        if (getView() != null) {
            getView().displayOnPageChange(items, i);
        }
    }

    @Override
    public void stopRequestLocation() {
        mGPSTracker.stopUsingGPS();
    }

    @Override
    public void loadCityName() {
        RESTManager.getInstance().getCities(new IRequestListener<CityResponse>() {
            @Override
            public void onCompleted(CityResponse data) {
                super.onCompleted(data);
                if (getView() != null) {
                    getView().getCityName(data.getData());
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
            }
        });
    }

    @Override
    public void loadAgencyList(Location location, int cityId, String name, int type) {
        RESTManager.getInstance().getAgenciesById(cityId, name, type, location.getLatitude(), location.getLongitude(), new IRequestListener<AgenciesResponse>() {
            @Override
            public void onCompleted(AgenciesResponse data) {
                super.onCompleted(data);
                if (mLocation != null) {
                    softing(data.getData(), mLocation.getLatitude(), mLocation.getLongitude());
                }
                if (getView() != null) {
                    getView().displayMarker(data.getData());
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                super.onFailed(throwable);
            }
        });
    }
}
