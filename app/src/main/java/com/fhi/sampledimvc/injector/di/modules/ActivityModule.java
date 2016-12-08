
package com.fhi.sampledimvc.injector.di.modules;

import android.app.Activity;

import com.fhi.sampledimvc.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity providesActivity() {
        return this.activity;
    }


}
