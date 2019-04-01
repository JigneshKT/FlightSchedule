package jigneshkt.test.com.testproject.data.model.response.flightschedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResource {

    public List<Schedule> getSchedules() {
        return schedules;
    }

    @SerializedName("Schedule")
    private List<Schedule> schedules;
}
