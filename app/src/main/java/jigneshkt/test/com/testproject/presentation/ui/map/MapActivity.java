package jigneshkt.test.com.testproject.presentation.ui.map;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import javax.inject.Inject;

import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.base.BaseActivity;

import static jigneshkt.test.com.testproject.presentation.ui.flightschedule.FlightScheduleActivity.ACTION_BAR_TITLE;

public class MapActivity extends BaseActivity<MapActivityPresenter> implements MapActivityView, OnMapReadyCallback {

    public static String ARRIVAL_AIRPORT = "ARRIVAL_AIRPORT";
    public static String DEPARTURE_AIRPORT = "DEPARTURE_AIRPORT";


    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    @Inject
    MapActivityPresenter mapActivityPresenter;

    @Override
    protected void inject() {
        getAppComponent().inject(this);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_map;
    }

    @NonNull
    @Override
    protected MapActivityPresenter getPresenter() {
        return mapActivityPresenter;
    }

    @Override
    protected void configureViews() {
        super.configureViews();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getIntent().getStringExtra(ACTION_BAR_TITLE));
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_background_theme)));
        }
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.supported_map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        showAirports();
    }


    private void showAirports(){

        getPresenter().setUpAirports(getIntent());

        LatLng departureLatLng = new LatLng(getPresenter().getDeparture().getLatitude(), getPresenter().getDeparture().getLongitude());
        googleMap.addMarker(new MarkerOptions().position(departureLatLng).title(getPresenter().getDeparture().getName()));

        LatLng arrivalLatLng =  new LatLng(getPresenter().getArrival().getLatitude(),getPresenter().getArrival().getLongitude());
        googleMap.addMarker(new MarkerOptions().position(arrivalLatLng).title(getPresenter().getArrival().getName()));

        googleMap.addPolyline(new PolylineOptions()
                .add(departureLatLng, arrivalLatLng)
                .width(5f)
                .color(R.color.colorPrimary));

        LatLngBounds latLngBounds = LatLngBounds.builder()
                .include(departureLatLng)
                .include(arrivalLatLng)
                .build();

        // begin new code:
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.25);

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(latLngBounds, width, height, padding);// end of new code

        googleMap.animateCamera(cu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
