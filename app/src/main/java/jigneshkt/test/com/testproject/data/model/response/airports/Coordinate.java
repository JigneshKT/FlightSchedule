package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

public class Coordinate {

    @SerializedName("Latitude")
    private Double Latitude;

    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    @SerializedName("Longitude")
    private Double Longitude;
}
