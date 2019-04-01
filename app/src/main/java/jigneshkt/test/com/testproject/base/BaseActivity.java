package jigneshkt.test.com.testproject.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

import butterknife.ButterKnife;
import jigneshkt.test.com.testproject.TestProjectApplication;
import jigneshkt.test.com.testproject.presentation.dagger.AppComponent;


public abstract class BaseActivity <Presenter extends BaseActivityPresenter> extends AppCompatActivity implements BaseActivityView {

    private static final int NO_STUB_VIEW = -1;
    protected boolean isHighSpeedInternetAvailable=false;
    protected boolean isInternetAvailable=false;

    protected AppComponent getAppComponent() {
        return ((TestProjectApplication) getApplication()).getAppComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            getPresenter().onReCreate(savedInstanceState, this);
        } else {
            getPresenter().onCreate(getIntent().getExtras(), this);
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onResume(this);
        configureViews();
        configureSubscriptions();
        getInternetAvailabilityAndSpeed();

    }

    protected void configureSubscriptions() {
    }

    protected void configureViews() {

    }

    protected abstract void inject();

    @NonNull
    protected abstract Presenter getPresenter();

    @LayoutRes
    protected abstract int getActivityLayoutId();


    public void getInternetAvailabilityAndSpeed() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    isHighSpeedInternetAvailable = true;
                    isInternetAvailable=true;
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    isInternetAvailable=true;
                    isHighSpeedInternetAvailable = getMobileNetworkSpeed();
                }
            } else {
                isInternetAvailable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean getMobileNetworkSpeed() {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            // 2G
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false;
            // 3G
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return false;
            // 4G
            case TelephonyManager.NETWORK_TYPE_LTE:
                return false;
            default:
                return false;
        }
    }
}
