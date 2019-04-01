package jigneshkt.test.com.testproject.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import jigneshkt.test.com.testproject.TestProjectApplication;
import jigneshkt.test.com.testproject.base.BaseActivityView;
import jigneshkt.test.com.testproject.presentation.dagger.AppComponent;


public abstract class BaseFragment<Presenter extends FragmentPresenter> extends Fragment
        implements BaseActivityView, View.OnClickListener {


    private static final int NO_MENU_ID = -1;
    protected CompositeDisposable subscriptions = new CompositeDisposable();


    protected AppComponent getComponent() {
        return ((TestProjectApplication) getActivity().getApplication()).getAppComponent();
    }


    @NonNull
    public abstract String getFragmentTag();

    protected abstract void inject();

    @NonNull
    protected abstract Presenter getPresenter();

    @LayoutRes
    protected abstract int getFragmentLayoutId();



    protected int getMenuId() {
        return NO_MENU_ID;
    }

    protected void configureViews() {
    }

    protected void configureSubscriptions() {
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (getMenuId() != NO_MENU_ID) {
            inflater.inflate(getMenuId(), menu);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);

        Presenter presenter = getPresenter();
        if (savedInstanceState == null) {
            presenter.onCreate(getArguments(), this);
        } else {
            presenter.onRecreate(savedInstanceState, this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureViews();
        setHasOptionsMenu(getMenuId() != NO_MENU_ID);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume(this);
        configureSubscriptions();
    }

    @Override
    public void onPause() {
        super.onPause();
        subscriptions.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        boolean isFinishing = getActivity().isFinishing() || isRemoving();
        getPresenter().onDestroy(isFinishing);
    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }


}
