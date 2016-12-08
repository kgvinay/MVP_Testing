package com.fhi.sampledimvc.di;

import android.content.Context;

import com.fhi.sampledimvc.SampleTestApplication;
import com.fhi.sampledimvc.data.repository.SampleRepository;
import com.fhi.sampledimvc.injector.di.components.ApplicationComponent;
import com.fhi.sampledimvc.tests.MainActivityTest;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

/**
 * Created by Vinay on 12/5/2016.
 */

@Singleton
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends ApplicationComponent{

    Context applicationContext();

    SampleTestApplication application();


    SampleRepository sampleRepository();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();

    void inject(MainActivityTest test);
}
