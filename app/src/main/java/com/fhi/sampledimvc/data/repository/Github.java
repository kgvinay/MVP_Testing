package com.fhi.sampledimvc.data.repository;

import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.entity.users.User;

import java.util.List;

import rx.Observable;

/**
 * Created by Vinay on 11/28/2016.
 * Edited by David Wu (david10608@gmail.com)
 */
public interface Github {

    Observable<List<GitHubRepoStarred>> getStarredRepositories(String userName);

    Observable<List<GitHubUserRepos>> getGithubRepos(String username);

    Observable<List<User>> getGithubUsers();
}
