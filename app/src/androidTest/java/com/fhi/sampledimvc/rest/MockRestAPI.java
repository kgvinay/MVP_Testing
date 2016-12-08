package com.fhi.sampledimvc.rest;

import com.fhi.sampledimvc.data.TestData;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.entity.users.User;
import com.fhi.sampledimvc.data.net.RestApi;
import com.fhi.sampledimvc.data.repository.SampleRepository;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Vinay on 12/5/2016.
 */
public class MockRestAPI implements RestApi {

    @Override
    public Observable<List<GitHubRepoStarred>> getStarredRepositories(String userName) {
        List<GitHubRepoStarred> starredList = new Gson().fromJson(TestData.MOCK_STARRED_DATA_LIST, (Type) GitHubRepoStarred.class);
        return Observable.just(starredList).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<List<User>> getGithubUsers() {
        return null;
    }
}
