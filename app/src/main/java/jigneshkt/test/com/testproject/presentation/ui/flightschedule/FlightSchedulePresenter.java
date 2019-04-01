package jigneshkt.test.com.testproject.presentation.ui.flightschedule;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import jigneshkt.test.com.testproject.base.BaseActivityPresenter;
import jigneshkt.test.com.testproject.domain.intercepter.flightschedule.GetFlightScheduleUseCase;
import jigneshkt.test.com.testproject.domain.model.Airport;
import jigneshkt.test.com.testproject.domain.model.FlightSchedule;
import jigneshkt.test.com.testproject.util.DateUtil;
import retrofit2.HttpException;

public class FlightSchedulePresenter extends BaseActivityPresenter<FlightScheduleView> {

    private String TAG = FlightSchedulePresenter.class.getSimpleName();
    private Airport departureAirport;

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    private Airport arrivalAirport;
    private final GetFlightScheduleUseCase getFlightScheduleUseCase;

    private int LIMIT = 20;
    private ArrayList<FlightSchedule> flightSchedules = new ArrayList<>();


    @Override
    protected void updateViewState() {
        super.updateViewState();
    }

    @Inject
    public FlightSchedulePresenter(GetFlightScheduleUseCase getFlightScheduleUseCase) {
        this.getFlightScheduleUseCase = getFlightScheduleUseCase;
    }


    public void setDepartureAirport(Airport airport){
        this.departureAirport = airport;
        view.setDepartureAirportName(departureAirport.getName() + ", " + departureAirport.getCountryCode() );
        checkWhetherBothAirportPresent();
    }

    public void setArrivalAirport(Airport airport){
        this.arrivalAirport = airport;
        view.setArrivalAirportName(arrivalAirport.getName() + ", " + arrivalAirport.getCountryCode());
        checkWhetherBothAirportPresent();
    }


    void checkWhetherBothAirportPresent(){
        if(arrivalAirport!=null && departureAirport!=null){
            view.updateFlightSchedule();
        }
    }


    private void getFlightSchedule(int currentPage){
        getFlightScheduleUseCase
                .withOrigin(departureAirport.getAirportCode())
                .withDestination(arrivalAirport.getAirportCode())
                .withDirectFlights("0")
                .withOffset(currentPage*LIMIT-LIMIT)
                .withLimit(LIMIT)
                .withFromDateTime(DateUtil.dateForFlightSchedule())
                .execute(new DisposableObserver<ArrayList<FlightSchedule>>() {
                    @Override
                    public void onNext(ArrayList<FlightSchedule> flightSchedules) {
                        if(flightSchedules!=null && flightSchedules.size()>0) {
                            addAll(flightSchedules);
                            view.onFlightScheduleSuccess(flightSchedules);
                        }else
                            view.onFlightScheduleFailure();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"e: "+e.getLocalizedMessage());
                        if(e instanceof HttpException && ((HttpException) e).code() == 404){
                            view.onFlightNotFound();
                        }else{
                            view.onFlightScheduleFailure();
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addAll(List<FlightSchedule> newFlightSchedules) {
        for (FlightSchedule flightSchedule : newFlightSchedules) {
            flightSchedules.add(flightSchedule);
        }
    }

    public void clearAll(){
        flightSchedules.clear();
    }

    public void loadMore(int currentPage){
        getFlightSchedule(currentPage);
    }

}
