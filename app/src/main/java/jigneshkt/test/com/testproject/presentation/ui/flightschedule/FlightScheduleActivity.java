package jigneshkt.test.com.testproject.presentation.ui.flightschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.base.BaseActivity;
import jigneshkt.test.com.testproject.domain.model.Airport;
import jigneshkt.test.com.testproject.domain.model.FlightSchedule;
import jigneshkt.test.com.testproject.presentation.ui.airportlist.AirportListActivity;
import jigneshkt.test.com.testproject.presentation.ui.airportlist.PaginationScrollListener;
import jigneshkt.test.com.testproject.presentation.ui.map.MapActivity;

import static jigneshkt.test.com.testproject.presentation.ui.airportlist.AirportListActivity.AIRPORT_DATA_STRING;
import static jigneshkt.test.com.testproject.presentation.ui.map.MapActivity.ARRIVAL_AIRPORT;
import static jigneshkt.test.com.testproject.presentation.ui.map.MapActivity.DEPARTURE_AIRPORT;

public class FlightScheduleActivity extends BaseActivity<FlightSchedulePresenter> implements FlightScheduleView, SwipeRefreshLayout.OnRefreshListener {

    private final int REQUEST_CODE_ARRIVAL = 103;
    private final int REQUEST_CODE_DEPARTURE = 140;

    @BindView(R.id.rv_flight_schedule)
    RecyclerView mRecyclerView;

    @BindView(R.id.sr_flight_schedule)
    SwipeRefreshLayout swipeRefresh;


    @BindView(R.id.tv_arrival)
    TextView tv_arrival;

    @BindView(R.id.tv_departure)
    TextView tv_departure;


    private FlightScheduleRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    @OnClick(R.id.tv_arrival)
    public void selectArrival(){
        startAirportListingActivity(REQUEST_CODE_ARRIVAL);
    }

    @OnClick(R.id.tv_departure)
    public void selectDeparture(){
        startAirportListingActivity(REQUEST_CODE_DEPARTURE);
    }

    @Inject
    FlightSchedulePresenter flightSchedulePresenter;

    @OnClick(R.id.tv_start_map)
    public void startMapActivity(){
        if(getPresenter().getArrivalAirport()!=null && getPresenter().getDepartureAirport()!=null){
            Intent intent = new Intent(this,MapActivity.class);
            intent.putExtra(ARRIVAL_AIRPORT,new Gson().toJson(getPresenter().getArrivalAirport()));
            intent.putExtra(DEPARTURE_AIRPORT,new Gson().toJson(getPresenter().getDepartureAirport()));
            startActivity(intent);
        }else{
            Toast.makeText(this,R.string.select_airport_error,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void inject() {
        getAppComponent().inject(this);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_flight_schedule;
    }

    @NonNull
    @Override
    protected FlightSchedulePresenter getPresenter() {
        return flightSchedulePresenter;
    }

    @Override
    protected void configureViews() {
        super.configureViews();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        swipeRefresh.setOnRefreshListener(this);

//        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FlightScheduleRecyclerAdapter.FlightScheduleSelectionListener listener = (view, position) -> {
            // any action on flight schedule click
        };


        mAdapter = new FlightScheduleRecyclerAdapter(new ArrayList<FlightSchedule>(),listener);
        mRecyclerView.setAdapter(mAdapter);

        /**
         * add scroll listener while user reach in bottom load more will call
         */
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                preparedListItem();

            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }


    private void startAirportListingActivity(int requestCode){
        startActivityForResult(new Intent(this, AirportListActivity.class),requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data!=null && data.hasExtra(AIRPORT_DATA_STRING)){
            String airportStr = data.getStringExtra(AIRPORT_DATA_STRING);
            if (airportStr!=null && !airportStr.isEmpty()) {
                Airport airport = new Gson().fromJson(airportStr, Airport.class);
                if(requestCode == REQUEST_CODE_ARRIVAL){
                    getPresenter().setArrivalAirport(airport);
                }else if(requestCode == REQUEST_CODE_DEPARTURE){
                    getPresenter().setDepartureAirport(airport);
                }else{
                    //error
                }
            }
        }
    }


    @Override
    public void setArrivalAirportName(String name) {
        tv_arrival.setText(name);
    }

    @Override
    public void setDepartureAirportName(String name) {
        tv_departure.setText(name);
    }


    @Override
    public void onFlightScheduleSuccess(ArrayList<FlightSchedule> flightScheduleArrayList){
        if (currentPage != PAGE_START) mAdapter.removeLoading();
        mAdapter.addAll(flightScheduleArrayList);
        swipeRefresh.setRefreshing(false);
        if (currentPage < totalPage) mAdapter.addLoading();
        else isLastPage = true;
        isLoading = false;
    }


    @Override
    public void updateFlightSchedule() {
        resetAndUpdateList();
    }

    @Override
    public void onRefresh() {
        resetAndUpdateList();
    }

    private void resetAndUpdateList(){
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        mAdapter.clear();
        getPresenter().clearAll();
        preparedListItem();
    }


    private void preparedListItem() {
        getPresenter().loadMore(currentPage);
    }

    @Override
    public void onFlightScheduleFailure() {

    }

    @Override
    public void onFlightNotFound() {

    }
}
