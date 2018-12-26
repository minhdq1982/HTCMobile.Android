package com.tvo.htc.view.main.support.findlocation;

import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.maps.android.PolyUtil;
import com.tvo.htc.model.Directions;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/20/2016.
 */

public class GetDirectionsTask {
    private String mRequest;

    public GetDirectionsTask(String _mRequest) {
        this.mRequest = _mRequest;
    }

    public ArrayList<LatLng> testDirection() {
        ArrayList<LatLng> ret = new ArrayList<>();
        try {
            URL url;
            url = new URL(mRequest);

            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");

            Directions results = new Gson().fromJson(reader, Directions.class);
            List<Directions.Routes> routes = results.getRoutes();
            if (routes.isEmpty()) {
                return ret;
            }
//            List<Directions.Routes.Legs> leg = routes.get(0).getLegs();
//            List<Directions.Routes.Legs.Steps> steps = leg.get(0).getSteps();
//            for (Directions.Routes.Legs.Steps step : steps) {
//                LatLng latlngStart = new LatLng(step.getStart_location().getLat(),
//                        step.getStart_location().getLng());
//
//                LatLng latlngEnd = new LatLng(step.getEnd_location().getLat(),
//                        step.getEnd_location().getLng());
//
//                ret.add(latlngStart);
//                ret.add(latlngEnd);
//            }
            ret.addAll(PolyUtil.decode(routes.get(0).getOverview_polyline().getPoints()));
            return ret;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }
}
