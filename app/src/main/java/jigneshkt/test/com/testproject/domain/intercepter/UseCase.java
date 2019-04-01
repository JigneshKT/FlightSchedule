package jigneshkt.test.com.testproject.domain.intercepter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<ReturnType> {


    protected abstract Observable<ReturnType> buildUseCaseObservable();

    public Disposable execute(DisposableObserver<ReturnType> UseCaseSubscriber) {
        return this.buildUseCaseObservable()
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribeWith(UseCaseSubscriber);
    }

}
