package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

public class AirportResource {

    @SerializedName("Airports")
    private Airports airports;

    public Airports getAirports() {
        return airports;
    }

}
