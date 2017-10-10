package com.fhi.sampledimvc.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.adapters.StarredDataAdapter;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.mvp.view.DividerItemDecoration;
import com.fhi.sampledimvc.mvp.view.StarredView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StarredActivity extends BaseActivity implements StarredView {
    @BindView(R.id.starredList)
    RecyclerView starredListRecyclerView;

    ProgressDialog mProgressDialog;

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
        setupProgressDialog();
        mStarredPresenter.attachView(this);
        mStarredPresenter.initialize();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        starredListRecyclerView.setLayoutManager(mLayoutManager);
        starredListRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new StarredDataAdapter(mStarredDataList);
        starredListRecyclerView.setAdapter(mAdapter);

    }

    private void setupProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading . . . ");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void displayLoadingScreen() {
        if (mProgressDialog != null)
            mProgressDialog.show();
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
}
