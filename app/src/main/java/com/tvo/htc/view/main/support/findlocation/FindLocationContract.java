package com.tvo.htc.view.main.support.findlocation;

import android.location.Location;
import android.support.v4.app.Fragment;

import com.android.lib.model.response.AgenciesResponse;
import com.android.lib.model.response.CityResponse;
import com.tvo.htc.view.BaseContract;

import java.util.List;

public interface FindLocationContract {

    interface View extends BaseContract.View {
        void displayMarker(List<AgenciesResponse.Agency> itemsBeans);

        void displayMyLocation(Location location);

        void displayNearbyLocation(Location location);

        void displayOnItemClick(List<AgenciesResponse.Agency> items, int position);

        void displayOnPageChange(List<AgenciesResponse.Agency> items, int i);

        void displayOnMarkerClick(int position);

        void getCityName(List<CityResponse.City> items);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadMarker();

        void loadMarkerNearBy(Location location, int type);

        void loadMyLocation(Fragment fragment);

        void loadOnItemClick(List<AgenciesResponse.Agency> items, int position);

        void loadOnPageChange(List<AgenciesResponse.Agency> items, int i);

        void stopRequestLocation();

        void loadCityName();

        void loadAgencyList(Location location, int cityId, String name, int type);
    }

}
