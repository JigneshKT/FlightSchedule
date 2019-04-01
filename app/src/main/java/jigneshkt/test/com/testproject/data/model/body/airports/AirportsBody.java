package jigneshkt.test.com.testproject.data.model.body.airports;

public class AirportsBody {
    private Integer limit;
    private Integer offset;
    private String lhOperated;
    private String lang;

    public AirportsBody(Integer limit, Integer offset,String lhOperated, String lang){
        this.limit = limit;
        this.offset = offset;
        this.lhOperated = lhOperated;
        this.lang = lang;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }


    public String getLhOperated() {
        return lhOperated;
    }

    public String getLang() {
        return lang;
    }


}
