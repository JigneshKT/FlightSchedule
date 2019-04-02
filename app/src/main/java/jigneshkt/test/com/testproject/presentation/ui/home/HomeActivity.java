package jigneshkt.test.com.testproject.presentation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.base.BaseActivity;
import jigneshkt.test.com.testproject.presentation.ui.flightschedule.FlightScheduleActivity;


public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityView {

    @BindView(R.id.tv_authentication)
    TextView tv_authentication;


    @Inject
    HomeActivityPresenter homeActivityPresenter;

    @Override
    protected void inject() {
        getAppComponent().inject(this);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_home;
    }

    @NonNull
    @Override
    protected HomeActivityPresenter getPresenter() {
        return homeActivityPresenter;
    }

    @Override
    protected void configureViews() {
        super.configureViews();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAuthenticationSuccess() {
            startActivity(new Intent(this,FlightScheduleActivity.class));
            finish();
    }

    @Override
    public void onAuthenticationFailure() {
        tv_authentication.setText(getString(R.string.credentials_fail_label));
    }
}
