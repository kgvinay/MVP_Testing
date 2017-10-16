package com.fhi.sampledimvc.injector.di.components;

/**
 * Created by Vinay on 9/16/2016.
 */


import android.content.Context;

import com.fhi.sampledimvc.SampleTestApplication;
import com.fhi.sampledimvc.data.repository.Github;
import com.fhi.sampledimvc.injector.di.modules.ApplicationModule;
import com.fhi.sampledimvc.mvp.view.Util.DividerItemDecoration;
import com.fhi.sampledimvc.mvp.view.activity.BaseActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    SampleTestApplication application();

    Context applicationContext();

    Github sampleRepository();

    DividerItemDecoration dividerItem();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();
}
