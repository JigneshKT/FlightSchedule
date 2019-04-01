package jigneshkt.test.com.testproject.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends BaseActivityView> {

    protected CompositeDisposable subscriptions = new CompositeDisposable();

    protected T view;

    public void onResume(T v) {
        setView(v);
        updateViewState();
    }

    protected void setView(@NonNull T view) {
        this.view = view;
    }

    protected void updateViewState(){};

    protected void setup(@Nullable Bundle arguments) {}

}
