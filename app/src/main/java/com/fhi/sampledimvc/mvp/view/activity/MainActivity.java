package com.fhi.sampledimvc.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import com.fhi.sampledimvc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vinay on 11/28/2016.
 * Edited by David Wu (david10608@gmail.com)
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.starredButton)
    AppCompatButton starredButton;

    @BindView(R.id.userButton)
    AppCompatButton userButton;

    //TODO(0) Edittext for username
    //TODO(1) parallel RxJava Data getting by scrolling
    //TODO(2) change font styles (make "Created on" etc big)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
