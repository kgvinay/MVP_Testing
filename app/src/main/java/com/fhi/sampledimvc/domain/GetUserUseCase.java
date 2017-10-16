package com.fhi.sampledimvc.domain;

import com.fhi.sampledimvc.data.entity.users.User;
import com.fhi.sampledimvc.data.repository.Github;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Vinay on 11/28/2016.
 */

public class GetUserUseCase extends Usecase<List<User>> {

    private final Github mRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private String mRepoName;

    @Inject
    public GetUserUseCase(
            Github repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread) {

        mRepository = repository;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
    }

    @Override
    public Observable<List<User>> buildObservable() {
        return mRepository.getGithubUsers()
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }
}
