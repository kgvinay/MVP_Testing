package com.fhi.sampledimvc.mvp.presenter;

import android.util.Log;

import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.domain.GetRepoUseCase;
import com.fhi.sampledimvc.mvp.view.ReposView;
import com.fhi.sampledimvc.mvp.view.View;

import java.util.List;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * @author David Wu (david10608@gmail.com)
 * Created by pl4gue on 09.10.17.
 */

public class RepoPresenter implements Presenter {

    private ReposView mView;
    private final GetRepoUseCase mRepoListCase;

    @Inject
    public RepoPresenter(GetRepoUseCase repoUseCase) {
        mRepoListCase = repoUseCase;
    }

    public void initialize() {
        mView.displayLoadingScreen();
        mRepoListCase.setUsername("pl4gue");
        mRepoListCase
                .execute()
                .subscribe(this::onNextRepoList, this::onError);
    }

    public void onNextRepoList(List<GitHubUserRepos> reposList) {
        Log.d(TAG, "onNextRepoList: "+reposList.size());
        mView.hideLoadingScreen();
        mView.updateReposResult(reposList);
    }

    public void onError(Throwable throwable) {
        mView.hideLoadingScreen();
        mView.fetchDataError();
        throwable.printStackTrace();
    }

    @Override
    public void attachView(View v) {
        mView = (ReposView) v;
    }
}
