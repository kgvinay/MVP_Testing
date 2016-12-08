package com.fhi.sampledimvc.domain;

import android.util.Log;

import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.repository.SampleRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Vinay on 11/28/2016.
 */
public class GetStarredDataUseCase extends Usecase<List<GitHubRepoStarred>> {

    private final SampleRepository mRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private String mRepoName;

    @Inject
    public GetStarredDataUseCase(
            SampleRepository repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread) {

        mRepository = repository;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
    }

    public void setRepositoryName(String repositoryName){
        mRepoName = repositoryName;
    }

    @Override
    public Observable<List<GitHubRepoStarred>> buildObservable() {
        return mRepository.getStarredRepositories(mRepoName)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }
}
