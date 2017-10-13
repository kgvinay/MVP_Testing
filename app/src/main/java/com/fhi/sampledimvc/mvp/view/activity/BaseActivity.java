package com.fhi.sampledimvc.mvp.view.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.fhi.sampledimvc.SampleTestApplication;
import com.fhi.sampledimvc.injector.di.components.ApplicationComponent;
import com.fhi.sampledimvc.injector.di.components.DaggerUseCaseComponent;
import com.fhi.sampledimvc.injector.di.modules.ActivityModule;
import com.fhi.sampledimvc.injector.di.modules.UseCaseModule;
import com.fhi.sampledimvc.mvp.presenter.RepoPresenter;
import com.fhi.sampledimvc.mvp.presenter.StarredPresenter;
import com.fhi.sampledimvc.mvp.presenter.UserPresenter;
import com.fhi.sampledimvc.mvp.view.Util.DividerItemDecoration;
import com.fhi.sampledimvc.navigation.Navigator;

import java.util.Locale;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    Locale locale;

    @Inject
    Navigator navigator;

    @Inject
    StarredPresenter mStarredPresenter;

    @Inject
    RepoPresenter mRepoPresenter;

    @Inject
    UserPresenter mUserPresenter;

    @Inject
    DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = preferences.getString("LANG","");
        if (!lang.equals("") && !config.locale.equals(lang)) {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
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
