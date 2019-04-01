package jigneshkt.test.com.testproject.data.model.body.authentication;

public class AuthenticationBody {

    private String client_id;
    private String client_secret;
    private String grant_type;

    public AuthenticationBody(String client_id, String client_secret, String grant_type ) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }
}
