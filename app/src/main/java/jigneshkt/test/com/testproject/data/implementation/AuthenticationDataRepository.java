package jigneshkt.test.com.testproject.data.implementation;

import javax.inject.Inject;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.api.AuthenticationAPI;
import jigneshkt.test.com.testproject.data.model.body.authentication.AuthenticationBody;
import jigneshkt.test.com.testproject.data.model.response.authentication.AuthenticationResponse;
import jigneshkt.test.com.testproject.domain.repository.AppContextRepository;
import jigneshkt.test.com.testproject.domain.repository.AuthenticationRepository;

public class AuthenticationDataRepository implements AuthenticationRepository {

    private AuthenticationAPI authenticationAPI;
    private AppContextRepository appContextRepository;

    @Inject
    public AuthenticationDataRepository(AuthenticationAPI authenticationAPI, AppContextRepository appContextRepository){
        this.authenticationAPI = authenticationAPI;
        this.appContextRepository = appContextRepository;
    }


    @Override
    public  Observable<AuthenticationResponse> authenticate(AuthenticationBody authenticationBody) {
            return authenticationAPI.authenticate(authenticationBody.getClient_id(),authenticationBody.getClient_secret(),authenticationBody.getGrant_type());
    }
}
