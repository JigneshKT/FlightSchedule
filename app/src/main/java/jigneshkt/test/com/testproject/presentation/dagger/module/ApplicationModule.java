package jigneshkt.test.com.testproject.presentation.dagger.module;

import android.app.Application;

import dagger.Module;

@Module
public class ApplicationModule {

    private Application mContext;

    public ApplicationModule(Application context) {
        this.mContext = context;
    }



}
