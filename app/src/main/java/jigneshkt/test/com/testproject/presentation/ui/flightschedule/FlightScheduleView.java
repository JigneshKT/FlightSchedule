package jigneshkt.test.com.testproject.presentation.ui.flightschedule;

import java.util.ArrayList;

import jigneshkt.test.com.testproject.base.BaseActivityView;
import jigneshkt.test.com.testproject.domain.model.FlightSchedule;

public interface FlightScheduleView extends BaseActivityView {

    void setArrivalAirportName(String name);
    void setDepartureAirportName(String name);

    void updateFlightSchedule();

    void onFlightScheduleSuccess(ArrayList<FlightSchedule> flightScheduleArrayList);
    void onFlightScheduleFailure();
    void onFlightNotFound();


}
