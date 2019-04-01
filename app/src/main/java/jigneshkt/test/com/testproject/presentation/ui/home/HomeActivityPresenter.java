package jigneshkt.test.com.testproject.presentation.ui.home;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import jigneshkt.test.com.testproject.base.BaseActivityPresenter;
import jigneshkt.test.com.testproject.domain.intercepter.authentication.AuthenticationUseCase;


public class HomeActivityPresenter extends BaseActivityPresenter<HomeActivityView> {

    private static final String TAG = HomeActivityPresenter.class.getSimpleName();
    private final AuthenticationUseCase authenticationUseCase;



    @Override
    protected void updateViewState() {
        super.updateViewState();
        authenticate();
    }

    @Inject
    public HomeActivityPresenter(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase= authenticationUseCase;
    }


    private void authenticate(){

        authenticationUseCase.execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean result) {
                if(result){
                    view.onAuthenticationSuccess();
                }else{
                    view.onAuthenticationFailure();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onAuthenticationFailure();
            }

            @Override
            public void onComplete() {

            }
        });

    }





}
