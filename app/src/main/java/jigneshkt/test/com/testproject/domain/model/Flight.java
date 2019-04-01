package jigneshkt.test.com.testproject.domain.model;

public class Flight {
    private String departureAirportCode;
    private String departureTime;
    private String arrivalAirportCode;
    private String arrivalTime;
    private String marketingCarrierAirlineID;

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getMarketingCarrierAirlineID() {
        return marketingCarrierAirlineID;
    }

    public void setMarketingCarrierAirlineID(String marketingCarrierAirlineID) {
        this.marketingCarrierAirlineID = marketingCarrierAirlineID;
    }

    public String getMarketingCarrierFlightNumber() {
        return marketingCarrierFlightNumber;
    }

    public void setMarketingCarrierFlightNumber(String marketingCarrierFlightNumber) {
        this.marketingCarrierFlightNumber = marketingCarrierFlightNumber;
    }

    private String marketingCarrierFlightNumber;



}
