package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

public class Position {

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @SerializedName("Coordinate")
    private Coordinate coordinate;

}
