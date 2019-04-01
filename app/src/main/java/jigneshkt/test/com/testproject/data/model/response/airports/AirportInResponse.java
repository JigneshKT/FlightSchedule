package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

public class AirportInResponse {

    @SerializedName("AirportCode")
    private String airportCode;

    @SerializedName("CityCode")
    private String cityCode;

    @SerializedName("CountryCode")
    private String countryCode;

    @SerializedName("LocationType")
    private String locationType;

    @SerializedName("UtcOffset")
    private String utcOffset;

    @SerializedName("TimeZoneId")
    private String timeZoneId;

    @SerializedName("Position")
    private Position position;

    @SerializedName("Names")
    private Names names;

    public String getAirportCode() {
        return airportCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public Position getPosition() {
        return position;
    }

    public Names getNames() {
        return names;
    }











}
