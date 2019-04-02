package jigneshkt.test.com.testproject.presentation.ui.airportlist;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.base.BaseActivity;
import jigneshkt.test.com.testproject.domain.model.Airport;

import static jigneshkt.test.com.testproject.presentation.ui.flightschedule.FlightScheduleActivity.ACTION_BAR_TITLE;

public class AirportListActivity  extends BaseActivity<AirportListPresenter> implements AirportListView, SwipeRefreshLayout.OnRefreshListener {


    public static String AIRPORT_DATA_STRING = "AIRPORT_STRING";


    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @Inject
    AirportListPresenter airportListPresenter;


    private AirportsRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    @Override
    protected void inject() {
        getAppComponent().inject(this);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_airport_list;
    }

    @NonNull
    @Override
    protected AirportListPresenter getPresenter() {
        return airportListPresenter;
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
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        swipeRefresh.setOnRefreshListener(this);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        /**
         * add click listener
         */
        AirportsRecyclerAdapter.AirportSelectionListener listener = (view, position) -> {
            String airportStr = new Gson().toJson(getPresenter().provideSelectedAirport(position));
            Intent intent = new Intent();
            intent.putExtra(AIRPORT_DATA_STRING, airportStr);
            setResult(RESULT_OK, intent);
            finish();
        };

        mAdapter = new AirportsRecyclerAdapter(new ArrayList<Airport>(),listener);
        mRecyclerView.setAdapter(mAdapter);
        preparedListItem();
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

    @Override
    public void onAirportsSuccess(ArrayList<Airport> airportArrayList) {

        if (currentPage != PAGE_START) mAdapter.removeLoading();
        mAdapter.addAll(airportArrayList);
        swipeRefresh.setRefreshing(false);
        if (currentPage < totalPage) mAdapter.addLoading();
        else isLastPage = true;
        isLoading = false;
    }

    @Override
    public void onAirportsFailure() {

    }




    @Override
    public void onRefresh() {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
