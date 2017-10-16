package com.fhi.sampledimvc.domain;

import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.data.repository.Github;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * @author David Wu (david10608@gmail.com)
 * Created by pl4gue on 09.10.17.
 */

public class GetRepoUseCase extends Usecase<List<GitHubUserRepos>> {

    private final Github mGithub;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private String mUsername;

    @Inject
    public GetRepoUseCase(Github github,
                          @Named("ui_thread") Scheduler uiThread,
                          @Named("executor_thread") Scheduler executorThread) {
        mGithub = github;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    @Override
    public Observable<List<GitHubUserRepos>> buildObservable() {
        return mGithub.getGithubRepos(mUsername)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }
}
