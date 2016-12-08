package com.fhi.sampledimvc;

import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.domain.GetStarredDataUseCase;
import com.fhi.sampledimvc.mvp.presenter.StarredPresenter;
import com.fhi.sampledimvc.mvp.view.StarredView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Vinay on 12/1/2016.
 */
public class PresenterTest {

    @Mock
    GetStarredDataUseCase mockStarredDataUseCase;

    @Mock
    StarredView mockStarredView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testThatChannelsArePassedToTheView() throws Exception {
        StarredPresenter starredPresenter = getMockStarredPresenter();
        List<GitHubRepoStarred> mockGitHubStarredList = getMockGitHubStarredList();
        starredPresenter.onStarredResultReceived(mockGitHubStarredList);
        verify(mockStarredView, times(1))
                .updateStarredResult(mockGitHubStarredList);
    }

    private StarredPresenter getMockStarredPresenter() {
        StarredPresenter listPresenter = new StarredPresenter(mockStarredDataUseCase);
        listPresenter.attachView(mockStarredView);
        return listPresenter;
    }

    private List<GitHubRepoStarred> getMockGitHubStarredList() {
        List<GitHubRepoStarred> starredList = new ArrayList<>();

        GitHubRepoStarred gitHubRepoStarred1 = new GitHubRepoStarred();
        gitHubRepoStarred1.setArchiveUrl("archiveURL1");
        gitHubRepoStarred1.setAssigneesUrl("assigneesURL1");
        gitHubRepoStarred1.setBlobsUrl("blobsURL1");
        gitHubRepoStarred1.setCommentsUrl("commentsURL1");

        GitHubRepoStarred gitHubRepoStarred2 = new GitHubRepoStarred();
        gitHubRepoStarred2.setArchiveUrl("archiveURL2");
        gitHubRepoStarred2.setAssigneesUrl("assigneesURL2");
        gitHubRepoStarred2.setBlobsUrl("blobsURL2");
        gitHubRepoStarred2.setCommentsUrl("commentsURL2");

        starredList.add(gitHubRepoStarred1);
        starredList.add(gitHubRepoStarred2);
        return starredList;
    }
}
