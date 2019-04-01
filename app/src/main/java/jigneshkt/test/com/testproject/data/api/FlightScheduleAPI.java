package jigneshkt.test.com.testproject.data.api;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.response.flightschedule.FlightScheduleResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlightScheduleAPI {

    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}")
    Observable<FlightScheduleResponse> getFlightScheduled(@Path("origin")String origin, @Path("destination")String destination,
                                                          @Path("fromDateTime")String fromDateTime,
                                                          @Query("limit")Integer limit,
                                                          @Query("offset")Integer offset,
                                                          @Query("directFlights")String directFlights);

}
