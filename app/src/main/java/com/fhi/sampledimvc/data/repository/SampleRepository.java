package com.fhi.sampledimvc.data.repository;

import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.entity.users.User;

import java.util.List;

import rx.Observable;

/**
 * Created by Vinay on 11/28/2016.
 */
public interface SampleRepository {

    Observable<List<GitHubRepoStarred>> getStarredRepositories(String userName);

    Observable<List<User>> getGithubUsers();
}
