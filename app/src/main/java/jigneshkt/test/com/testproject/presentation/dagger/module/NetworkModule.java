package jigneshkt.test.com.testproject.presentation.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import jigneshkt.test.com.testproject.data.api.AirportsAPI;
import jigneshkt.test.com.testproject.data.api.AuthenticationAPI;
import jigneshkt.test.com.testproject.data.api.FlightScheduleAPI;
import jigneshkt.test.com.testproject.data.model.body.authentication.AuthenticationBody;
import jigneshkt.test.com.testproject.data.model.response.authentication.AuthenticationResponse;
import jigneshkt.test.com.testproject.domain.repository.AppContextRepository;
import jigneshkt.test.com.testproject.presentation.dagger.qualifier.ApiOkHttpClient;
import jigneshkt.test.com.testproject.presentation.dagger.qualifier.AuthOkHttpClient;
import jigneshkt.test.com.testproject.presentation.dagger.qualifier.Refresh410Interceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static jigneshkt.test.com.testproject.BuildConfig.BASE_URL;
import static jigneshkt.test.com.testproject.BuildConfig.CLIENT_ID;
import static jigneshkt.test.com.testproject.BuildConfig.CLIENT_SECRET;
import static jigneshkt.test.com.testproject.BuildConfig.DEBUG;
import static jigneshkt.test.com.testproject.BuildConfig.GRANT_TYPE;

@Module
public class NetworkModule {


    private static final String HEADER_KEY_AUTHORIZATION = "Authorization";

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        Gson gson = new GsonBuilder().setLenient().create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @ApiOkHttpClient
    OkHttpClient provideApiOkHttpClient(AppContextRepository appContextRepository,
                                        @Refresh410Interceptor Interceptor refresh410Interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(generateHttpLoggingInterceptor());
        builder.addInterceptor(generateClientHeaderInterceptor(appContextRepository));
        builder.addInterceptor(refresh410Interceptor);
        return builder.build();
    }


    private Interceptor generateClientHeaderInterceptor(final AppContextRepository appContextRepository) {
        return chain -> {
            Request.Builder builder = chain.request().newBuilder();
            if(appContextRepository.getAccessToken()!=null){
                builder.header(HEADER_KEY_AUTHORIZATION,"Bearer "+appContextRepository.getAccessToken());
            }
            return chain.proceed(builder.build());
        };
    }

    private HttpLoggingInterceptor generateHttpLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);
        return logger;
    }


    @Provides
    @Refresh410Interceptor
    Interceptor provideRefresh410Intercepter(AuthenticationAPI authenticationAPI) {
        return chain -> {
            Request originalRequest = chain.request();
            okhttp3.Response response = chain.proceed(originalRequest);
            if (response.code() == 410) {

                Response<AuthenticationResponse> refreshTokenResponse = authenticationAPI.refreshToken(new AuthenticationBody(CLIENT_ID,CLIENT_SECRET,GRANT_TYPE)).execute();
                if(refreshTokenResponse.isSuccessful()){
                    chain.proceed(originalRequest.newBuilder().header(HEADER_KEY_AUTHORIZATION,"Bearer "+refreshTokenResponse.body().getAccess_token()).build());
                }
            }
            return response;
        };
    }


    //////// Get Airports API     ////////////////////////////////
    @Provides
    @Singleton
    AirportsAPI provideAirportsAPI(Converter.Factory factory, @ApiOkHttpClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(factory)
                .build().create(AirportsAPI.class);
    }

    //////// Get Flight schedule API     ////////////////////////////////
    @Provides
    @Singleton
    FlightScheduleAPI provideFlightScheduleAPI(Converter.Factory factory, @ApiOkHttpClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(factory)
                .build().create(FlightScheduleAPI.class);
    }


    //////// Get authentication API     ///////////////////////////
    @Provides
    @Singleton
    AuthenticationAPI provideAuthenticationAPI(Converter.Factory factory, @AuthOkHttpClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(factory)
                .build().create(AuthenticationAPI.class);
    }


    @Provides @AuthOkHttpClient
    OkHttpClient provideAuthOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(generateHttpLoggingInterceptor());
        return builder.build();
    }


}
