package jigneshkt.test.com.testproject.presentation.ui.map;

import android.support.annotation.NonNull;

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
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.supported_map);
        mapFragment.getMapAsync(this);
    }


    @Override
    @SuppressWarnings("MissingPermission")
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        showAirports();
    }


    private void showAirports(){
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

        int zoomPadding = 20;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, zoomPadding));
    }
}
