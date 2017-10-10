package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.adapters.UserDataAdapter;
import com.fhi.sampledimvc.data.entity.users.User;
import com.fhi.sampledimvc.mvp.view.DividerItemDecoration;
import com.fhi.sampledimvc.mvp.view.UserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends BaseActivity implements UserView {

    @BindView(R.id.userList)
    RecyclerView userListRecyclerView;

    ProgressDialog mProgressDialog;

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
        setupProgressDialog();
        mUserPresenter.attachView(this);
        mUserPresenter.initialize();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        userListRecyclerView.setLayoutManager(mLayoutManager);
        userListRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new UserDataAdapter(mUserDataList);
        userListRecyclerView.setAdapter(mAdapter);
    }

    private void setupProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading . . . ");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void displayLoadingScreen() {
         if(mProgressDialog != null )
             mProgressDialog.show();
    }

    @Override
    public void hideLoadingScreen() {
        if(mProgressDialog != null )
            mProgressDialog.dismiss();
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
