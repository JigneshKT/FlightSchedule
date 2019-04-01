package jigneshkt.test.com.testproject;

import android.app.Application;

import jigneshkt.test.com.testproject.presentation.dagger.AppComponent;
import jigneshkt.test.com.testproject.presentation.dagger.DaggerAppComponent;
import jigneshkt.test.com.testproject.presentation.dagger.module.ApplicationModule;
import jigneshkt.test.com.testproject.presentation.dagger.module.NetworkModule;
import jigneshkt.test.com.testproject.presentation.dagger.module.RepositoryModule;

public class TestProjectApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        getAppComponent().inject(this);
    }

    private void initAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .repositoryModule(new RepositoryModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
