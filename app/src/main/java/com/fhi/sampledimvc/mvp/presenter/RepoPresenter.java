package com.fhi.sampledimvc.mvp.presenter;

import android.util.Log;
import android.widget.EditText;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;
import com.fhi.sampledimvc.domain.GetRepoUseCase;
import com.fhi.sampledimvc.mvp.view.ReposView;
import com.fhi.sampledimvc.mvp.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

/**
 * @author David Wu (david10608@gmail.com)
 * Created by pl4gue on 09.10.17.
 */

public class RepoPresenter implements Presenter {

    private ReposView mView;
    private final GetRepoUseCase mRepoListCase;
    private List<GitHubUserRepos> currentList;
    private int maxPerRefresh=5;
    private int current=0, currentPage = 1, totalPages;

    @Inject
    public RepoPresenter(GetRepoUseCase repoUseCase) {
        mRepoListCase = repoUseCase;
    }

    public void initialize() {
        mView.displayLoadingScreen();
        mRepoListCase.setUsername("pl4gue");
        next();
    }

    public void next() {
        mView.displayLoadingScreen();
        mRepoListCase
                .execute()
                .subscribe(this::onNextRepoList, this::onError);
        raiseCounter();
    }

    public void refresh() {
        mView.displayLoadingScreen();
        resetCounter();
        mRepoListCase
                .execute()
                .subscribe(this::onNextRepoList, this::onError);
    }

    public void onNextRepoList(List<GitHubUserRepos> reposList) {
        totalPages = (reposList.size() % maxPerRefresh)!=0?(reposList.size() / maxPerRefresh)+1: (reposList.size() / maxPerRefresh);
        try {
            currentList = reposList.subList(current, current + maxPerRefresh);
        } catch (IndexOutOfBoundsException iobe) {
            currentList = reposList.subList(current, reposList.size());
        }
        Log.d(TAG, "onNextRepoList: "+currentList.size());
        mView.hideLoadingScreen();
        mView.updateReposResult(currentList);
        mView.showTitleByUsername(reposList,"pl4gue",currentPage,totalPages);
    }

    private void raiseCounter() {
        current += maxPerRefresh;
        currentPage++;
        if (currentPage > totalPages) {
            resetCounter();
            refresh();
        }
    }

    private void resetCounter() {
        current = 0;
        currentPage = 1;
    }

    public void onError(Throwable throwable) {
        mView.hideLoadingScreen();
        mView.fetchDataError();
        throwable.printStackTrace();
    }

    @Override
    public void attachView(View v) {
        mView = (ReposView) v;
    }
}
