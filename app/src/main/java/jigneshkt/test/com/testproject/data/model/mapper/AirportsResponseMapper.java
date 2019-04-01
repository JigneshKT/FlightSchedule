package jigneshkt.test.com.testproject.data.model.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;

import jigneshkt.test.com.testproject.data.model.response.airports.AirportInResponse;
import jigneshkt.test.com.testproject.data.model.response.airports.AirportsResponse;
import jigneshkt.test.com.testproject.domain.model.Airport;


public class AirportsResponseMapper {

    @Inject
    public AirportsResponseMapper(){

    }

    @NonNull
    public ArrayList<Airport> mapIn(@NonNull AirportsResponse airportsResponse) {

        ArrayList<Airport>airportArrayList = new ArrayList<>();
        for (AirportInResponse airportInResponse:airportsResponse.getAirportResource().getAirports().getAirPortList()) {
            Airport airport = new Airport();
            airport.setAirportCode(airportInResponse.getAirportCode());
            airport.setCityCode(airportInResponse.getCityCode());
            airport.setCountryCode(airportInResponse.getCountryCode());
            airport.setLatitude(airportInResponse.getPosition().getCoordinate().getLatitude());
            airport.setLongitude(airportInResponse.getPosition().getCoordinate().getLongitude());
            airport.setName(airportInResponse.getNames().getName().getName());
            airport.setTimeZoneId(airportInResponse.getTimeZoneId());
            airport.setUtcOffset(airportInResponse.getUtcOffset());
            airport.setLocationType(airportInResponse.getLocationType());
            airportArrayList.add(airport);
        }

        return airportArrayList;

    }


}
