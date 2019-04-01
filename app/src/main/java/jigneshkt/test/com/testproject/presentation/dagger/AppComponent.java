package jigneshkt.test.com.testproject.presentation.dagger;

import javax.inject.Singleton;

import dagger.Component;
import jigneshkt.test.com.testproject.presentation.dagger.module.ApplicationModule;
import jigneshkt.test.com.testproject.presentation.dagger.module.NetworkModule;
import jigneshkt.test.com.testproject.presentation.dagger.module.RepositoryModule;
import jigneshkt.test.com.testproject.presentation.ui.airportlist.AirportListActivity;
import jigneshkt.test.com.testproject.presentation.ui.flightschedule.FlightScheduleActivity;
import jigneshkt.test.com.testproject.presentation.ui.home.HomeActivity;
import jigneshkt.test.com.testproject.presentation.ui.map.MapActivity;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject (jigneshkt.test.com.testproject.TestProjectApplication testProject);
    void inject (HomeActivity homeActivity);
    void inject (FlightScheduleActivity flightScheduleActivity);
    void inject (AirportListActivity airportListActivity);
    void inject (MapActivity mapActivity);

}
