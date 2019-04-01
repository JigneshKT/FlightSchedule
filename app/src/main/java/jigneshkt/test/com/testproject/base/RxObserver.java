package jigneshkt.test.com.testproject.base;

import io.reactivex.observers.DisposableObserver;

public class RxObserver<T> extends DisposableObserver<T> {

    private DataView dataView;
    private boolean hideContent = true;
    private boolean showLoading = true;

    public RxObserver() { }

    public RxObserver(DataView dataView) {
        this.dataView = dataView;
    }

    public RxObserver(DataView dataView, boolean showLoading, boolean hideContent) {
        this.dataView = dataView;
        this.showLoading = showLoading;
        this.hideContent = hideContent;
    }

    public RxObserver(DataView dataView, boolean hideContent) {
        this.dataView = dataView;
        this.hideContent = hideContent;
    }

    @Override
    public void onStart() {
        if(dataView!=null && showLoading){
            dataView.showLoading(hideContent);
        }
        super.onStart();
    }

    @Override public void onError(Throwable e) {
        if(dataView!=null){
            dataView.hideLoading();
        }
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        if(dataView!=null){
            dataView.hideLoading();
        }
    }

    @Override public void onNext(T t) {
        if(dataView!=null){
            dataView.hideLoading();
        }
    }
}