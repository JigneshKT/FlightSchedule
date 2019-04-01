package jigneshkt.test.com.testproject.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import jigneshkt.test.com.testproject.base.BaseActivityView;
import jigneshkt.test.com.testproject.base.BasePresenter;


public abstract class FragmentPresenter<View extends BaseActivityView> extends BasePresenter<View> {

    void onCreate(@Nullable Bundle arguments, View view) {
        setView(view);
        setup(arguments);
    }

    void onRecreate(@NonNull Bundle savedInstanceState, View view) {
        setView(view);
        setup(savedInstanceState);
    }



    void onDestroy(boolean isFinishing) {

    }
}
