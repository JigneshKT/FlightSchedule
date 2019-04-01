package jigneshkt.test.com.testproject.presentation.ui.airportlist;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import jigneshkt.test.com.testproject.base.BaseActivityPresenter;
import jigneshkt.test.com.testproject.domain.intercepter.airports.GetAirportsUseCase;
import jigneshkt.test.com.testproject.domain.model.Airport;

public class AirportListPresenter  extends BaseActivityPresenter<AirportListView> {

    private final GetAirportsUseCase getAirportsUseCase;
    private int LIMIT = 20;
    private ArrayList<Airport> airports = new ArrayList<>();

    @Override
    protected void updateViewState() {
        super.updateViewState();
    }

    @Inject
    public AirportListPresenter(GetAirportsUseCase getAirportsUseCase) {
        this.getAirportsUseCase = getAirportsUseCase;
    }

    public Airport provideSelectedAirport(int position){
        return airports.get(position);
    }


    public void loadMore(int currentPage){
        getAirports(currentPage);
    }


    public void getAirports(int currentPage){

        getAirportsUseCase
                .withLang("en")
                .withlHOperated("0")
                .withOffset(currentPage*LIMIT-LIMIT)
                .withLimit(LIMIT)
                .execute(new DisposableObserver<ArrayList<Airport>>() {
                    @Override
                    public void onNext(ArrayList<Airport> airPorts) {
                        addAll(airPorts);
                        view.onAirportsSuccess(airPorts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onAirportsFailure();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void addAll(List<Airport> airportList) {
        for (Airport airport : airportList) {
            airports.add(airport);
        }
    }

    public void clearAll(){
        airports.clear();
    }


}
