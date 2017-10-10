package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.adapters.RepoDataAdapter;
import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.mvp.view.ReposView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pl4gue on 09.10.17.
 */

public class ReposActivity extends BaseActivity implements ReposView {
    @BindView(R.id.reposList)
    RecyclerView reposListRecyclerView;

    ProgressDialog mProgressDialog;

    RepoDataAdapter mAdapter;
    private List<GitHubUserRepos> mRepoList = new ArrayList<>();

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ReposActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        ButterKnife.bind(this);
        setUpProgressDialog();
        mRepoPresenter.attachView(this);
        mRepoPresenter.initialize();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        reposListRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RepoDataAdapter(mRepoList);
        reposListRecyclerView.setAdapter(mAdapter);
    }

    private void setUpProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading . . . ");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void displayLoadingScreen() {
        if(mProgressDialog!=null)
            mProgressDialog.show();
    }

    @Override
    public void hideLoadingScreen() {
        if(mProgressDialog!=null)
            mProgressDialog.dismiss();
    }

    @Override
    public void updateReposResult(List<GitHubUserRepos> reposList) {
        mRepoList = reposList;
        mAdapter = new RepoDataAdapter(mRepoList);
        reposListRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void fetchDataError() {

    }
}
