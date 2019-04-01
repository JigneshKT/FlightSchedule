package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

public class FlightScheduleResponse {

    public ScheduleResource getScheduleResource() {
        return scheduleResource;
    }

    @SerializedName("ScheduleResource")
    private ScheduleResource scheduleResource;
}
