package jigneshkt.test.com.testproject.presentation.dagger.qualifier;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Qualifier
@Retention(RUNTIME)
public @interface ApiOkHttpClient {}
