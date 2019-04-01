package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("@LanguageCode")
    private String languageCode;

    public String getLanguageCode() {
        return languageCode;
    }

    public String getName() {
        return name;
    }

    @SerializedName("$")
    private String name;
}
