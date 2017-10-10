package com.fhi.sampledimvc.mvp.view;

import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;

import java.util.List;

/**
 * @author David Wu (david10608@gmail.com)
 * Created by pl4gue on 09.10.17.
 */

public interface ReposView extends View {
    void displayLoadingScreen();

    void hideLoadingScreen();

    void updateReposResult(List<GitHubUserRepos> reposList);

    void fetchDataError();
}
