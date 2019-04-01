package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

public class AirportsResponse {

    @SerializedName("AirportResource")
    private AirportResource airportResource;

    public AirportResource getAirportResource() {
        return airportResource;
    }
}
