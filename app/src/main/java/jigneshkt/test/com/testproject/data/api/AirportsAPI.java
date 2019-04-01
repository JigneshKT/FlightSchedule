package jigneshkt.test.com.testproject.data.api;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.response.airports.AirportsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirportsAPI {


    @GET("references/airports")
    Observable<AirportsResponse> getAirports(
            @Query("limit")Integer limit,
            @Query("offset")Integer offset,
            @Query("LHoperated")String lhOperated,
            @Query("lang")String lang
    );
}
