package jigneshkt.test.com.testproject.domain.repository;


import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.body.authentication.AuthenticationBody;
import jigneshkt.test.com.testproject.data.model.response.authentication.AuthenticationResponse;

public interface AuthenticationRepository {

    Observable<AuthenticationResponse> authenticate(AuthenticationBody authenticationBody);

}
