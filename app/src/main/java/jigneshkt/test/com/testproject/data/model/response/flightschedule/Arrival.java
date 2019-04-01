package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

public class Arrival {

    public ScheduledTimeLocal getScheduledTimeLocal() {
        return scheduledTimeLocal;
    }

    public String getAirportCode() {
        return airportCode;
    }

    @SerializedName("ScheduledTimeLocal")
    private ScheduledTimeLocal scheduledTimeLocal;

    @SerializedName("AirportCode")
    private String airportCode;
}
