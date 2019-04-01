package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule {

    public TotalJourney getTotalJourney() {
        return totalJourney;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    @SerializedName("TotalJourney")
    private TotalJourney totalJourney;

    @SerializedName("Flight")
    private List<Flight> flights;

}
