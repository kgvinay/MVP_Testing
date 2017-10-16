package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.adapters.RepoDataAdapter;
import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.mvp.view.ReposView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author David Wu (david10608@gmail.com)
 *         Created on 09.10.17.
 */

public class ReposActivity extends BaseActivity implements ReposView {
    @BindView(R.id.reposList)
    RecyclerView reposListRecyclerView;

    @BindView(R.id.reposTitleTextView)
    TextView mReposTitleTextView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mRepoSwipeRefreshLayout;

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
        onSwipeRefresh();
        setUpProgressDialog();
        mRepoPresenter.attachView(this);
        mRepoPresenter.initialize();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        reposListRecyclerView.setLayoutManager(mLayoutManager);
        reposListRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new RepoDataAdapter(mRepoList, this);
        reposListRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.repos_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                //toast.makeText(this, "Refresh clicked!", //toast.LENGTH_SHORT).show();
                refresh();
                return true;
            case R.id.action_settings:
                //toast.makeText(this, "Settings clicked!", //toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_next:
                //toast.makeText(this, "Next clicked!", //toast.LENGTH_SHORT).show();
                nextPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSwipeRefresh() {
        mRepoSwipeRefreshLayout.setOnRefreshListener(() -> {
            //toast.makeText(this, "Refresh swiped!", //toast.LENGTH_SHORT).show();
            refresh();
        });
    }

    private void nextPage() {
        //toast.makeText(this, "Next Page invoked!", //toast.LENGTH_SHORT).show();
        mRepoPresenter.next();
    }

    private void refresh() {
        mRepoPresenter.refresh();
        if (mRepoSwipeRefreshLayout.isRefreshing())
            mRepoSwipeRefreshLayout.setRefreshing(false);
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
    public void showTitleByUsername(List<GitHubUserRepos> reposList, String username, int currentPage, int totalPages) {
        mReposTitleTextView.setText(getString(R.string.repo_titleAfterFound, mAdapter.getItemCount(), reposList.size(), username, currentPage, totalPages));
    }

    @Override
    public void updateReposResult(List<GitHubUserRepos> reposList) {
        mRepoList = reposList;
        mAdapter = new RepoDataAdapter(mRepoList, this);
        reposListRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void fetchDataError() {
    }
}