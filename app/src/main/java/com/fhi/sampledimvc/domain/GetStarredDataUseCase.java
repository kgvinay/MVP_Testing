package com.fhi.sampledimvc.domain;

import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.repository.Github;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Vinay on 11/28/2016.
 */
public class GetStarredDataUseCase extends Usecase<List<GitHubRepoStarred>> {

    private final Github mRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private String mUsername;

    @Inject
    public GetStarredDataUseCase(
            Github repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread) {

        mRepository = repository;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
    }

    public void setUsername(String username){
        mUsername = username;
    }

    @Override
    public Observable<List<GitHubRepoStarred>> buildObservable() {
        return mRepository.getStarredRepositories(mUsername)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }
}
