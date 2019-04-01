package jigneshkt.test.com.testproject.domain.intercepter.airports;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.body.airports.AirportsBody;
import jigneshkt.test.com.testproject.data.model.mapper.AirportsResponseMapper;
import jigneshkt.test.com.testproject.domain.intercepter.UseCase;
import jigneshkt.test.com.testproject.domain.model.Airport;
import jigneshkt.test.com.testproject.domain.repository.AirportsRepository;

public class GetAirportsUseCase extends UseCase<ArrayList<Airport>> {

    private AirportsRepository airportsRepository;
    private AirportsResponseMapper airportsResponseMapper;
    private Integer limit;
    private Integer offset;
    private String lhOperated;
    private String lang;

    @Inject
    public GetAirportsUseCase(AirportsRepository airportsRepository, AirportsResponseMapper airportsResponseMapper){
        this.airportsResponseMapper = airportsResponseMapper;
        this.airportsRepository = airportsRepository;
    }

    public GetAirportsUseCase withLimit(Integer limit){
        this.limit = limit;
        return this;
    }

    public GetAirportsUseCase withOffset(Integer offset){
        this.offset = offset;
        return this;
    }

    public GetAirportsUseCase withlHOperated(String lhOperated){
        this.lhOperated = lhOperated;
        return this;
    }

    public GetAirportsUseCase withLang(String lang){
        this.lang = lang;
        return this;
    }


    @Override
    protected Observable<ArrayList<Airport>> buildUseCaseObservable() {
        return airportsRepository.getAirports(new AirportsBody(limit,offset,lhOperated,lang)).map(airportsResponse ->
            airportsResponseMapper.mapIn(airportsResponse));
    }
}
