package com.fhi.sampledimvc.mvp.presenter;

import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.domain.GetStarredDataUseCase;
import com.fhi.sampledimvc.mvp.view.StarredView;
import com.fhi.sampledimvc.mvp.view.View;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vinay on 11/28/2016.
 */
public class StarredPresenter implements Presenter {

    private StarredView mView;
    private final GetStarredDataUseCase mStarredListCase;

    @Inject
    public StarredPresenter(GetStarredDataUseCase starredDataUseCase) {
        mStarredListCase = starredDataUseCase;
    }

    @Override
    public void attachView(View v) {
        mView = (StarredView) v;
    }

    public void initialize() {
        mView.displayLoadingScreen();
        mStarredListCase.setUsername("pl4gue");
        mStarredListCase.execute()
                .subscribe(this::onStarredResultReceived, this::onStarredResultsError);
    }

    public void onStarredResultsError(Throwable throwable) {
        mView.hideLoadingScreen();
        mView.fetchDataError();
        throwable.printStackTrace();
    }

    public void onStarredResultReceived(List<GitHubRepoStarred> starredList) {
        mView.hideLoadingScreen();
        mView.updateStarredResult(starredList);
    }
}
