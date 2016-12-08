package com.fhi.sampledimvc.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.fhi.sampledimvc.TestSampleTestApplication;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.net.RestApi;
import com.fhi.sampledimvc.data.repository.SampleRepository;
import com.fhi.sampledimvc.domain.GetStarredDataUseCase;
import com.fhi.sampledimvc.mvp.presenter.StarredPresenter;
import com.fhi.sampledimvc.mvp.view.activity.BaseActivity;
import com.fhi.sampledimvc.mvp.view.activity.MainActivity;
import com.fhi.sampledimvc.mvp.view.activity.StarredActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Vinay on 12/5/2016.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends BaseActivity {

    private static final String STARRED_USER_NAME = "JakeWharton";

    @Inject
    RestApi mockRestAPI;

    GetStarredDataUseCase mMockStarredUseCase;


    @Mock
    private SampleRepository mockUserRepository;
    @Mock private Scheduler mockThreadExecutor;
    @Mock private Scheduler mockPostExecutionThread;


//    @Rule
//    public ActivityTestRule<StarredActivity> activityTestRule = new ActivityTestRule<>(StarredActivity.class);

    @Before
    public void setUp() {
//        ((TestSampleTestApplication) activityTestRule.getActivity().getApplication()).getApplicationComponent().inject(this);
//        mMockStarredUseCase = mock(GetStarredDataUseCase.class);
//        mMockStarredUseCase.setRepositoryName(STARRED_USER_NAME);

        mMockStarredUseCase = new GetStarredDataUseCase(mockUserRepository, mockThreadExecutor, mockPostExecutionThread);



    }


    @Test
    public void correctStarredDataDisplayed() {
        mMockStarredUseCase.buildObservable();

        mMockStarredUseCase.setRepositoryName(STARRED_USER_NAME);
        verify(mockUserRepository).getStarredRepositories(STARRED_USER_NAME);

    }



}
