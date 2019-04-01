package jigneshkt.test.com.testproject.data.model.response.authentication;

import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse {

    @SerializedName("access_token")
    private String access_token;
    @SerializedName("token_type")
    private String token_type;
    @SerializedName("expires_in")
    private String expires_in;


    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

}
