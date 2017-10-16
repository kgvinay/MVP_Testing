package com.fhi.sampledimvc;

import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.repository.Github;
import com.fhi.sampledimvc.domain.GetStarredDataUseCase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;


/**
 * Created by Vinay on 12/7/2016.
 */
public class TestStarredUseCase {
    private static final String STARRED_USER_NAME = "JakeWharton";

    @Mock
    private Github mRepository;

    @Mock
    Scheduler mockScheduler;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testThatDetailUsecaseIsCalledOnce() throws Exception {
        GetStarredDataUseCase starredDataUseCase = givenACharacterUsecase();

        Mockito.when(mRepository.getStarredRepositories(STARRED_USER_NAME)).thenReturn(getStarredRepositories());
        starredDataUseCase.setUsername(STARRED_USER_NAME);
        starredDataUseCase.execute();

        Mockito.verify(mRepository, Mockito.only()).getStarredRepositories(STARRED_USER_NAME);
    }

    private GetStarredDataUseCase givenACharacterUsecase() {
        return new GetStarredDataUseCase(mRepository, null, null);
    }

    public Observable<List<GitHubRepoStarred>> getStarredRepositories() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<GitHubRepoStarred>>() {}.getType();
        List<GitHubRepoStarred> json =  gson.fromJson(TestData.MOCK_STARRED_DATA_LIST, type);
        return Observable.just(json).delay(1, TimeUnit.SECONDS);
    }
}
