package jigneshkt.test.com.testproject.domain.intercepter.authentication;

import javax.inject.Inject;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.body.authentication.AuthenticationBody;
import jigneshkt.test.com.testproject.domain.intercepter.UseCase;
import jigneshkt.test.com.testproject.domain.repository.AppContextRepository;
import jigneshkt.test.com.testproject.domain.repository.AuthenticationRepository;

import static jigneshkt.test.com.testproject.BuildConfig.CLIENT_ID;
import static jigneshkt.test.com.testproject.BuildConfig.CLIENT_SECRET;
import static jigneshkt.test.com.testproject.BuildConfig.GRANT_TYPE;

public class AuthenticationUseCase extends UseCase<Boolean> {

    private AuthenticationRepository authenticationRepository;
    private AppContextRepository appContextRepository;

    @Inject
    public AuthenticationUseCase(AuthenticationRepository authenticationRepository, AppContextRepository appContextRepository){
        this.authenticationRepository = authenticationRepository;
        this.appContextRepository = appContextRepository;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        return authenticationRepository.authenticate(new AuthenticationBody(CLIENT_ID,CLIENT_SECRET,GRANT_TYPE))
                .flatMap(authenticationResponse -> {
                    appContextRepository.setAccessToken(authenticationResponse.getAccess_token());
                    if(authenticationResponse.getAccess_token()!=null) {
                        return Observable.just(true);
                    }else{
                        return Observable.just(false);
                    }
                });
    }
}
