package com.fhi.sampledimvc.data.net;

import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.entity.users.User;
import com.fhi.sampledimvc.data.repository.SampleRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vinay on 11/28/2016.
 */
public class RestAPIImpl implements SampleRepository {

    private static final long RETRY_COUNT = 5;
    RestApi mRestAPI;

    @Inject
    public RestAPIImpl(RestApi restAPI) {
        this.mRestAPI = restAPI;
    }

    @Override
    public Observable<List<GitHubRepoStarred>> getStarredRepositories(String userName) {
        return mRestAPI.getStarredRepositories(userName).retry(RETRY_COUNT);
    }

    @Override
    public Observable<List<User>> getGithubUsers() {
        return mRestAPI.getGithubUsers().retry(RETRY_COUNT);
    }
}
