package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.adapters.UserDataAdapter;
import com.fhi.sampledimvc.data.entity.users.User;
import com.fhi.sampledimvc.mvp.view.UserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends BaseActivity implements UserView {

    @BindView(R.id.userList)
    RecyclerView userListRecyclerView;

    UserDataAdapter mAdapter;
    private List<User> mUserDataList = new ArrayList<>();

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        setUpProgressDialog();
        mUserPresenter.attachView(this);
        mUserPresenter.initialize();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        userListRecyclerView.setLayoutManager(mLayoutManager);
        userListRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new UserDataAdapter(mUserDataList);
        userListRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void displayLoadingScreen() {
        startProgressDialog(getString(R.string.loading));
    }

    @Override
    public void hideLoadingScreen() {
        stopProgressDialog();
    }

    @Override
    public void updateUSerResult(List<User> userList) {
        mUserDataList = userList;
        mAdapter = new UserDataAdapter(mUserDataList);
        userListRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void fetchDataError() {

    }

}
