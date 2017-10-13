package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.fhi.sampledimvc.R;
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

import static com.fhi.sampledimvc.R.string.languagePreferenceKey;

public class BaseActivity extends AppCompatActivity {

    Locale locale;

    private ProgressDialog mProgressDialog;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private int counter = 0;

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
        loadLanguage();
        DaggerUseCaseComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .useCaseModule(new UseCaseModule())
                .build().inject(this);
    }

    private void loadLanguage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = preferences.getString(getString(languagePreferenceKey), "");
        if (!lang.equals("") && !config.locale.equals(lang)) {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    private ApplicationComponent getApplicationComponent() {
        return ((SampleTestApplication) getApplication()).getApplicationComponent();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    protected void setUpProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgress(0);
        mProgressDialog.setMax(100);
    }

    protected void startProgressDialog(String title) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(title);
            mProgressDialog.show();
            progressBarStatus = 0;
            counter = 0;
            new Thread(() -> {
                while (progressBarStatus < 100) {
                    progressBarStatus = counter;
                    counter += 1;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBarHandler.post(() -> mProgressDialog.setProgress(progressBarStatus));
                }
                if (progressBarStatus >= 100)
                    return;
            }).start();
        }
    }

    protected void stopProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

}
