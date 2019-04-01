package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Airports {

    @SerializedName("Airport")
    private List<AirportInResponse> airPortList;

    public List<AirportInResponse> getAirPortList() {
        return airPortList;
    }


}
