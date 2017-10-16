package com.fhi.sampledimvc.mvp.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.Util;

import java.util.Arrays;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fhi.sampledimvc.R.string.languagePreferenceKey;

/**
 * Created by Vinay on 11/28/2016.
 * Edited by David Wu (david10608@gmail.com)
 */

public class MainActivity extends BaseActivity {

    Locale locale;

    //TODO(0) Edittext for username
    //TODO(1) parallel RxJava Data getting by scrolling
    //TODO(2) change font styles (make "Created on" etc big)
    //TODO(3) Localization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_language:
                showChangeLangDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.language_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle(getResources().getString(R.string.lang_dialog_title));
        dialogBuilder.setMessage(getResources().getString(R.string.lang_dialog_message));
        dialogBuilder.setPositiveButton("Change", (dialog, whichButton) -> {
            final Spinner mLanguageSpinner = (Spinner) dialogView.findViewById(R.id.languageSpinner);
            int langpos = mLanguageSpinner.getSelectedItemPosition();
            switch(langpos) {
                case 0: //English
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getString(languagePreferenceKey), Util.UtilConstants.Languages.EN).apply();
                    setLangRecreate(Util.UtilConstants.Languages.EN);
                    return;
                case 1: //German
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getString(languagePreferenceKey), Util.UtilConstants.Languages.DE).apply();
                    setLangRecreate(Util.UtilConstants.Languages.DE);
                    return;
                default: //By default set to english
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getString(languagePreferenceKey), Util.UtilConstants.Languages.EN).apply();
                    setLangRecreate(Util.UtilConstants.Languages.EN);
                    return;
            }
        });
        dialogBuilder.setNegativeButton("Cancel", (dialog, whichButton) -> {
            //pass
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }


    @OnClick(R.id.reposButton)
    public void reposButtonClick() {
        navigator.navigateToReposPage(this);
    }

    @OnClick(R.id.starredButton)
    public void starredButtonClick() {
        navigator.navigateToStarredPage(this);
    }

    @OnClick(R.id.userButton)
    public void userButtonClick() {
        navigator.navigateToUserPage(this);
    }
}
