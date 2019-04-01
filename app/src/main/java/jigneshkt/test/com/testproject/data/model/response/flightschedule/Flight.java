package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("Departure")
    private Departure departure;

    @SerializedName("Arrival")
    private Arrival arrival;

    public Departure getDeparture() {
        return departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public MarketingCarrier getMarketingCarrier() {
        return marketingCarrier;
    }

    @SerializedName("MarketingCarrier")
    private MarketingCarrier marketingCarrier;

}
