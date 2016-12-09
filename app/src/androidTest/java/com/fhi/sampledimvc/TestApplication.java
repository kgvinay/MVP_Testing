package com.fhi.sampledimvc;

import com.fhi.sampledimvc.di.DaggerTestAppComponent;
import com.fhi.sampledimvc.di.TestAppComponent;
import com.fhi.sampledimvc.di.TestAppModule;
import com.fhi.sampledimvc.injector.di.components.ApplicationComponent;
import com.fhi.sampledimvc.injector.di.components.DaggerApplicationComponent;
import com.fhi.sampledimvc.injector.di.modules.ApplicationModule;

/**
 * Created by Vinay on 12/9/2016.
 */
public class TestApplication extends SampleTestApplication {

    private TestAppComponent testAppComponent;


    @Override
    public void onCreate() {
        initializeInjector();
        super.onCreate();
    }

    private void initializeInjector() {
        this.testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
    }

    public TestAppComponent getApplicationComponent() {
        return this.testAppComponent;
    }

}
