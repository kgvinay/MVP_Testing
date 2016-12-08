package com.fhi.sampledimvc.mvp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.SampleTestApplication;
import com.fhi.sampledimvc.injector.di.components.ApplicationComponent;
import com.fhi.sampledimvc.injector.di.components.DaggerUseCaseComponent;
import com.fhi.sampledimvc.injector.di.modules.ActivityModule;
import com.fhi.sampledimvc.injector.di.modules.UseCaseModule;
import com.fhi.sampledimvc.mvp.presenter.StarredPresenter;
import com.fhi.sampledimvc.mvp.presenter.UserPresenter;
import com.fhi.sampledimvc.navigation.Navigator;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Inject
    StarredPresenter mStarredPresenter;

    @Inject
    UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerUseCaseComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .useCaseModule(new UseCaseModule())
                .build().inject(this);
    }

    private ApplicationComponent getApplicationComponent() {
        return ((SampleTestApplication) getApplication()).getApplicationComponent();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
