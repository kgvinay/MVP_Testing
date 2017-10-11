package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.adapters.StarredDataAdapter;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.mvp.view.StarredView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StarredActivity extends BaseActivity implements StarredView {
    @BindView(R.id.starredList)
    RecyclerView starredListRecyclerView;

    ProgressDialog mProgressDialog;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private int counter = 0;

    StarredDataAdapter mAdapter;
    private List<GitHubRepoStarred> mStarredDataList = new ArrayList<>();

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, StarredActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred);
        ButterKnife.bind(this);
        setUpProgressDialog();
        mStarredPresenter.attachView(this);
        mStarredPresenter.initialize();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        starredListRecyclerView.setLayoutManager(mLayoutManager);
        starredListRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new StarredDataAdapter(mStarredDataList);
        starredListRecyclerView.setAdapter(mAdapter);

    }

    private void setUpProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading . . .");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgress(0);
        mProgressDialog.setMax(100);
    }

    @Override
    public void displayLoadingScreen() {
        if(mProgressDialog!=null)
            startProgressDialog();
    }

    @Override
    public void hideLoadingScreen() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    @Override
    public void updateStarredResult(List<GitHubRepoStarred> starredList) {
        mStarredDataList = starredList;
        mAdapter = new StarredDataAdapter(mStarredDataList);
        starredListRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void fetchDataError() {
    }

    public void startProgressDialog() {
        mProgressDialog.show();
        progressBarStatus = 0;
        counter = 0;
        new Thread(() -> {
            while (progressBarStatus < 100) {
                progressBarStatus = counter;
                counter += 2;
                try {
                    Thread.sleep(50);
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
