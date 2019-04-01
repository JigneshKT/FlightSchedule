package jigneshkt.test.com.testproject.domain.intercepter.flightschedule;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.mapper.FlightScheduleResponseMapper;
import jigneshkt.test.com.testproject.domain.intercepter.UseCase;
import jigneshkt.test.com.testproject.domain.model.FlightSchedule;
import jigneshkt.test.com.testproject.domain.repository.FlightScheduleRepository;


public class GetFlightScheduleUseCase  extends UseCase<ArrayList<FlightSchedule>> {

    private FlightScheduleRepository flightScheduleRepository;
    private FlightScheduleResponseMapper flightScheduleResponseMapper;
    private String origin;
    private String destination;
    private String fromDateTime;
    private Integer limit;
    private Integer offset;
    private String directFlights;

    @Inject
    public GetFlightScheduleUseCase(FlightScheduleRepository flightScheduleRepository, FlightScheduleResponseMapper flightScheduleResponseMapper){
        this.flightScheduleRepository = flightScheduleRepository;
        this.flightScheduleResponseMapper = flightScheduleResponseMapper;
    }

    public GetFlightScheduleUseCase withOrigin(String origin){
        this.origin = origin;
        return this;
    }
    public GetFlightScheduleUseCase withDestination(String destination){
        this.destination = destination;
        return this;
    }

    public GetFlightScheduleUseCase withFromDateTime(String fromDateTime){
        this.fromDateTime = fromDateTime;
        return this;
    }
    public GetFlightScheduleUseCase withLimit(int limit){
        this.limit = limit;
        return this;
    }
    public GetFlightScheduleUseCase withOffset(int offset){
        this.offset = offset;
        return this;
    }
    public GetFlightScheduleUseCase withDirectFlights(String directFlights){
        this.directFlights = directFlights;
        return this;
    }

    @Override
    protected Observable<ArrayList<FlightSchedule>> buildUseCaseObservable() {
        return flightScheduleRepository.getFlightScheduled(origin,destination,fromDateTime,limit,offset,directFlights).
                map(flightScheduleResponse -> flightScheduleResponseMapper.mapIn(flightScheduleResponse));
    }
}

