package jigneshkt.test.com.testproject.data.implementation;

import javax.inject.Inject;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.api.FlightScheduleAPI;
import jigneshkt.test.com.testproject.data.model.response.flightschedule.FlightScheduleResponse;
import jigneshkt.test.com.testproject.domain.repository.FlightScheduleRepository;

public class FlightScheduleDataRepository implements FlightScheduleRepository {

    private FlightScheduleAPI flightScheduleAPI;

    @Inject
    public FlightScheduleDataRepository(FlightScheduleAPI flightScheduleAPI){
        this.flightScheduleAPI = flightScheduleAPI;
    }


    @Override
    public Observable<FlightScheduleResponse> getFlightScheduled(String origin, String destination,
                                                          String fromDateTime,
                                                          Integer limit,
                                                          Integer offset,
                                                          String directFlights){

        return flightScheduleAPI.getFlightScheduled(origin,destination,fromDateTime,limit,offset,directFlights);
    }


}
