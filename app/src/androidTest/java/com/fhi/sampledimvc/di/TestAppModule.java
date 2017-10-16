package com.fhi.sampledimvc.di;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.TestApplication;
import com.fhi.sampledimvc.data.MockAPIClient;
import com.fhi.sampledimvc.data.net.RestAPIImpl;
import com.fhi.sampledimvc.data.net.RestApi;
import com.fhi.sampledimvc.data.repository.Github;
import com.fhi.sampledimvc.mvp.view.Util.DividerItemDecoration;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vinay on 12/9/2016.
 */
@Module
public class TestAppModule {

    private final TestApplication mApplication;

    public TestAppModule(TestApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    TestApplication provideAndroidApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    Drawable provideDividerDrawable() {
        return ContextCompat.getDrawable(mApplication.getApplicationContext(), R.drawable.divider);
    }

    @Provides
    @Singleton
    DividerItemDecoration provideDividerItem(Drawable drawable) {
        return new DividerItemDecoration(drawable);
    }

    @Provides
    public RestApi provideWeatherApiClient() {
        return new MockAPIClient();
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
    Github provideDataRepository(RestAPIImpl restDataSource) {
        return restDataSource;
    }

}
