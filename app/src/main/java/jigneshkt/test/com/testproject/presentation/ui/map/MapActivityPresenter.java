package jigneshkt.test.com.testproject.presentation.ui.map;

import android.content.Intent;

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

    public void setUpAirports(Intent intent){
        if (intent != null && intent.hasExtra(ARRIVAL_AIRPORT)) {
            String arrivalAirportStr = intent.getStringExtra(ARRIVAL_AIRPORT);
            if (arrivalAirportStr!=null && !arrivalAirportStr.isEmpty()) {
                arrival = new Gson().fromJson(arrivalAirportStr, Airport.class);
            }
        }


        if (intent != null && intent.hasExtra(DEPARTURE_AIRPORT)) {
            String departureAirportStr = intent.getStringExtra(DEPARTURE_AIRPORT);
            if (departureAirportStr != null && !departureAirportStr.isEmpty()) {
                departure = new Gson().fromJson(departureAirportStr, Airport.class);
            }
        }
    }




    @Inject
    public MapActivityPresenter( ) {

    }
}
