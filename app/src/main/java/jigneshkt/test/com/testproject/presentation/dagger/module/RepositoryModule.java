package jigneshkt.test.com.testproject.presentation.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jigneshkt.test.com.testproject.data.api.AirportsAPI;
import jigneshkt.test.com.testproject.data.api.AuthenticationAPI;
import jigneshkt.test.com.testproject.data.api.FlightScheduleAPI;
import jigneshkt.test.com.testproject.data.implementation.AirportsDataRepository;
import jigneshkt.test.com.testproject.data.implementation.AppContextDataRepository;
import jigneshkt.test.com.testproject.data.implementation.AuthenticationDataRepository;
import jigneshkt.test.com.testproject.data.implementation.FlightScheduleDataRepository;
import jigneshkt.test.com.testproject.domain.repository.AirportsRepository;
import jigneshkt.test.com.testproject.domain.repository.AppContextRepository;
import jigneshkt.test.com.testproject.domain.repository.AuthenticationRepository;
import jigneshkt.test.com.testproject.domain.repository.FlightScheduleRepository;

@Module
public class RepositoryModule {

    Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    AuthenticationRepository provideAuthenticationRepository(AuthenticationAPI authenticationAPI, AppContextRepository appContextRepository) {
        return new AuthenticationDataRepository(authenticationAPI, appContextRepository);
    }


    @Provides
    @Singleton
    AirportsRepository provideAirportsRepository(AirportsAPI airportsAPI ) {
        return new AirportsDataRepository(airportsAPI);
    }

    @Provides
    @Singleton
    FlightScheduleRepository provideFlightScheduleRepository(FlightScheduleAPI flightScheduleAPI ) {
        return new FlightScheduleDataRepository(flightScheduleAPI);
    }

    @Provides
    @Singleton
    AppContextRepository provideAppContextRepository() {
        return new AppContextDataRepository();
    }

}
