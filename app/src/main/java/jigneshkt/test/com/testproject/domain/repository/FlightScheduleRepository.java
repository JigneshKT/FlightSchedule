package jigneshkt.test.com.testproject.domain.repository;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.response.flightschedule.FlightScheduleResponse;

public interface FlightScheduleRepository {



    Observable<FlightScheduleResponse> getFlightScheduled(String origin, String destination,
                                                          String fromDateTime,
                                                          Integer limit,
                                                          Integer offset,
                                                          String directFlights);
}
