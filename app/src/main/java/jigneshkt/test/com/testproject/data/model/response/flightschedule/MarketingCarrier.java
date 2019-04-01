package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

public class MarketingCarrier {

    @SerializedName("AirlineID")
    private String airlineID;

    public String getAirlineID() {
        return airlineID;
    }

    public String getFlightNUmber() {
        return flightNUmber;
    }

    @SerializedName("FlightNumber")
    private String flightNUmber;
}
