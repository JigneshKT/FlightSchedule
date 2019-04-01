package jigneshkt.test.com.testproject.data.model.response.airports;

import com.google.gson.annotations.SerializedName;

public class Names {
    public Name getName() {
        return name;
    }

    @SerializedName("Name")
    private Name name;
}
