package jigneshkt.test.com.testproject.presentation.ui.airportlist;

import java.util.ArrayList;

import jigneshkt.test.com.testproject.base.BaseActivityView;
import jigneshkt.test.com.testproject.domain.model.Airport;

public interface AirportListView extends BaseActivityView{

    void onAirportsSuccess(ArrayList<Airport> airportArrayList);
    void onAirportsFailure();

}
