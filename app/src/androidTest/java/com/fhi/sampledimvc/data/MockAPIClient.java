package com.fhi.sampledimvc.data;

import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.entity.users.User;
import com.fhi.sampledimvc.data.net.RestApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Vinay on 12/9/2016.
 */
public class MockAPIClient implements RestApi {
    @Override
    public Observable<List<GitHubRepoStarred>> getStarredRepositories(@Path("user") String userName) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<GitHubRepoStarred>>() {
        }.getType();
        List<GitHubRepoStarred> json = gson.fromJson(TestData.MOCK_STARRED_DATA_LIST, type);
        return Observable.just(json).delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<List<User>> getGithubUsers() {
        return null;
    }

    @Override
    public Observable<List<GitHubUserRepos>> getUserRepos(@Path("user") String username) {
        return Observable.just(new Gson().fromJson(TestData.MOCK_STARRED_DATA_LIST, new TypeToken<List<GitHubUserRepos>>() {
        }.getType()));
    }
}
