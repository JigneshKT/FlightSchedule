package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

public class ScheduledTimeLocal {

    public String getDateTime() {
        return dateTime;
    }

    @SerializedName("DateTime")
    private String dateTime;
}
