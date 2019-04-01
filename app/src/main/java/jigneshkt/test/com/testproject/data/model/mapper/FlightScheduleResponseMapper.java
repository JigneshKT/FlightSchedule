package jigneshkt.test.com.testproject.data.model.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;

import jigneshkt.test.com.testproject.data.model.response.flightschedule.FlightScheduleResponse;
import jigneshkt.test.com.testproject.data.model.response.flightschedule.Schedule;
import jigneshkt.test.com.testproject.domain.model.Flight;
import jigneshkt.test.com.testproject.domain.model.FlightSchedule;

public class FlightScheduleResponseMapper {


    @Inject
    public FlightScheduleResponseMapper(){

    }

    @NonNull
    public ArrayList<FlightSchedule> mapIn(@NonNull FlightScheduleResponse flightScheduleResponse) {
        ArrayList<FlightSchedule>scheduleFlights = new ArrayList<>();

        for (Schedule schedule:flightScheduleResponse.getScheduleResource().getSchedules()) {
            FlightSchedule flightSchedule = new FlightSchedule();
            flightSchedule.setTotalJourney(schedule.getTotalJourney().getDuration());
            ArrayList<Flight> flights = new ArrayList<>();
            for(jigneshkt.test.com.testproject.data.model.response.flightschedule.Flight flightInResponse: schedule.getFlights()){
                Flight flight = new Flight();
                flight.setArrivalAirportCode(flightInResponse.getArrival().getAirportCode());
                flight.setDepartureAirportCode(flightInResponse.getDeparture().getAirportCode());
                flight.setArrivalTime(flightInResponse.getArrival().getScheduledTimeLocal().getDateTime());
                flight.setDepartureTime(flightInResponse.getDeparture().getScheduledTimeLocal().getDateTime());
                flight.setMarketingCarrierAirlineID(flightInResponse.getMarketingCarrier().getAirlineID());
                flight.setMarketingCarrierFlightNumber(flightInResponse.getMarketingCarrier().getFlightNUmber());
                flights.add(flight);
            }
            flightSchedule.setFlightList(flights);
            scheduleFlights.add(flightSchedule);
        }
        return scheduleFlights;
    }


}
