package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

public class TotalJourney {

    public String getDuration() {
        return duration;
    }

    @SerializedName("Duration")
    private String duration;
}
