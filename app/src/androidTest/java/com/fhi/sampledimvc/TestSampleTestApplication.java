package com.fhi.sampledimvc;

import com.fhi.sampledimvc.di.DaggerTestAppComponent;
import com.fhi.sampledimvc.di.TestAppComponent;
import com.fhi.sampledimvc.di.TestAppModule;
import com.fhi.sampledimvc.injector.di.components.ApplicationComponent;

/**
 * Created by Vinay on 12/5/2016.
 */
public class TestSampleTestApplication extends SampleTestApplication {

    private TestAppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
    }

    @Override
    public ApplicationComponent getApplicationComponent() {
        return this.testAppComponent;
    }
}
