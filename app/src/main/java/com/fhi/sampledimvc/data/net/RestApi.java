package com.fhi.sampledimvc.data.net;

import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.entity.users.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Vinay on 11/28/2016.
 * @author Edited by David Wu (david10608@gmail.com)
 */
public interface RestApi {

    @GET("users/{user}/starred")
    Observable<List<GitHubRepoStarred>> getStarredRepositories(@Path("user") String userName);

    @GET("users")
    Observable<List<User>> getGithubUsers();

    @GET("users/{user}/repos")
    Observable<List<GitHubUserRepos>> getUserRepos(@Path("user") String username);
}
