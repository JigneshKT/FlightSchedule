package jigneshkt.test.com.testproject.data.implementation;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.api.AirportsAPI;
import jigneshkt.test.com.testproject.data.model.body.airports.AirportsBody;
import jigneshkt.test.com.testproject.data.model.response.airports.AirportsResponse;
import jigneshkt.test.com.testproject.domain.repository.AirportsRepository;

public class AirportsDataRepository implements AirportsRepository {


    private AirportsAPI airportsAPI;
    public AirportsDataRepository(AirportsAPI airportsAPI){
        this.airportsAPI = airportsAPI;
    }

    @Override
    public Observable<AirportsResponse> getAirports(AirportsBody airportsBody) {
        return airportsAPI.getAirports(airportsBody.getLimit(),airportsBody.getOffset(),airportsBody.getLhOperated(),airportsBody.getLang());
    }
}
