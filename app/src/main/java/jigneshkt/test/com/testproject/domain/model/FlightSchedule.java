package jigneshkt.test.com.testproject.domain.model;

import java.util.List;

public class FlightSchedule {
    private List<Flight> flightList;

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public String getTotalJourney() {
        return totalJourney;
    }

    public void setTotalJourney(String totalJourney) {
        this.totalJourney = totalJourney;
    }

    private String totalJourney;




}
