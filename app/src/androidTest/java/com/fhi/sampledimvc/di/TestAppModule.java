package com.fhi.sampledimvc.di;

import android.content.Context;

import com.fhi.sampledimvc.SampleTestApplication;
import com.fhi.sampledimvc.TestSampleTestApplication;
import com.fhi.sampledimvc.data.net.RestAPIImpl;
import com.fhi.sampledimvc.data.net.RestApi;
import com.fhi.sampledimvc.data.repository.SampleRepository;
import com.fhi.sampledimvc.rest.MockRestAPI;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vinay on 12/5/2016.
 */
@Module
public class TestAppModule {

    private final TestSampleTestApplication mApplication;

    public TestAppModule(TestSampleTestApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    SampleTestApplication provideAndroidApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }


    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @Singleton
    RestApi provideRestApi() {
        return new MockRestAPI();
    }

    @Provides
    @Singleton
    SampleRepository provideDataRepository(RestAPIImpl restDataSource) {
        return restDataSource;
    }
}
