package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.adapters.RepoDataAdapter;
import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.mvp.view.DividerItemDecoration;
import com.fhi.sampledimvc.mvp.view.ReposView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author David Wu (david10608@gmail.com)
 * Created on 09.10.17.
 */

public class ReposActivity extends BaseActivity implements ReposView {
    @BindView(R.id.reposList)
    RecyclerView reposListRecyclerView;

    @BindView(R.id.reposTitleTextView)
    TextView mReposTitleTextView;

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
        reposListRecyclerView.addItemDecoration(dividerItemDecoration);
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
    public void showTitleByUsername(String username) {
        mReposTitleTextView.setText("Found "+mAdapter.getItemCount()+" repositories of user "+username);
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
