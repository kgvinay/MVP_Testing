package com.fhi.sampledimvc.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    public void displayLoadingScreen() {
        startProgressDialog(getString(R.string.loading));
    }

    @Override
    public void hideLoadingScreen() {
        stopProgressDialog();
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


}
