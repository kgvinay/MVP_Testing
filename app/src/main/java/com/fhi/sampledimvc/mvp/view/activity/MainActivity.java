package com.fhi.sampledimvc.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import com.fhi.sampledimvc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.starredButton)
    AppCompatButton starredButton;

    @BindView(R.id.userButton)
    AppCompatButton userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
