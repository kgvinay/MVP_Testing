package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
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
 * @author David Wu (david10608@gmail.com)
 * Created on 09.10.17.
 */

public class ReposActivity extends BaseActivity implements ReposView {
    @BindView(R.id.reposList)
    RecyclerView reposListRecyclerView;

    @BindView(R.id.reposTitleTextView)
    TextView mReposTitleTextView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mRepoSwipeRefreshLayout;

    ProgressDialog mProgressDialog;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private int counter = 0;

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
        mAdapter = new RepoDataAdapter(mRepoList);
        reposListRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.repos_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh clicked!", Toast.LENGTH_SHORT).show();
                refresh();
            case R.id.action_settings:
                Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSwipeRefresh() {
        mRepoSwipeRefreshLayout.setOnRefreshListener(() -> {
            Toast.makeText(this, "Refresh swiped!", Toast.LENGTH_SHORT).show();
            refresh();
        });
    }

    private void refresh() {

        Toast.makeText(this, "Refresh invoked!", Toast.LENGTH_SHORT).show();
    }

    private void setUpProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading . . .");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgress(0);
        mProgressDialog.setMax(100);
    }

    private void startProgressDialog() {
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

    @Override
    public void displayLoadingScreen() {
        if(mProgressDialog!=null)
            startProgressDialog();
    }

    @Override
    public void hideLoadingScreen() {
        if(mProgressDialog!=null)
            mProgressDialog.dismiss();
    }

    @Override
    public void showTitleByUsername(String username) {
        mReposTitleTextView.setText(String.format("Found %s repositories of user '%s'", mAdapter.getItemCount(), username));
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
