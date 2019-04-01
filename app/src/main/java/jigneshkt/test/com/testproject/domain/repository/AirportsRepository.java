package jigneshkt.test.com.testproject.domain.repository;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.body.airports.AirportsBody;
import jigneshkt.test.com.testproject.data.model.response.airports.AirportsResponse;

public interface AirportsRepository {

    Observable<AirportsResponse> getAirports(AirportsBody airportsBody);
}
