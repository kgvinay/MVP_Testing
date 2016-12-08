package com.fhi.sampledimvc.mvp.view;


import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;

import java.util.List;

/**
 * Created by Vinay on 11/28/2016.
 */
public interface StarredView extends View {

    void displayLoadingScreen();

    void hideLoadingScreen();

    void updateStarredResult(List<GitHubRepoStarred> starredList);

    void fetchDataError();
}
