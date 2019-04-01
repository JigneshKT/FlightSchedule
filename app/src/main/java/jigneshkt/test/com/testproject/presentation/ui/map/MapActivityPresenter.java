package jigneshkt.test.com.testproject.presentation.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import javax.inject.Inject;

import jigneshkt.test.com.testproject.base.BaseActivityPresenter;
import jigneshkt.test.com.testproject.domain.model.Airport;

import static jigneshkt.test.com.testproject.presentation.ui.map.MapActivity.ARRIVAL_AIRPORT;
import static jigneshkt.test.com.testproject.presentation.ui.map.MapActivity.DEPARTURE_AIRPORT;

public class MapActivityPresenter extends BaseActivityPresenter<MapActivityView> {

    public Airport getArrival() {
        return arrival;
    }

    public Airport getDeparture() {
        return departure;
    }

    private Airport arrival;
    private Airport departure;

    @Override
    protected void updateViewState() {
        super.updateViewState();
    }


    @Override
    protected void setup(@Nullable Bundle arguments) {
        super.setup(arguments);

        if (arguments != null && arguments.containsKey(ARRIVAL_AIRPORT)) {
            String arrivalAirportStr = arguments.getString(ARRIVAL_AIRPORT);
            if (arrivalAirportStr!=null && !arrivalAirportStr.isEmpty()) {
                arrival = new Gson().fromJson(arrivalAirportStr, Airport.class);
            }
        }


        if (arguments != null && arguments.containsKey(DEPARTURE_AIRPORT)) {
            String departureAirportStr = arguments.getString(DEPARTURE_AIRPORT);
            if (departureAirportStr != null && !departureAirportStr.isEmpty()) {
                departure = new Gson().fromJson(departureAirportStr, Airport.class);
            }
        }
    }




    @Inject
    public MapActivityPresenter( ) {

    }
}
