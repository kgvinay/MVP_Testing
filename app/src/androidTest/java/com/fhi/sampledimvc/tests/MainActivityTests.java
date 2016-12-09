package com.fhi.sampledimvc.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.TestApplication;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;
import com.fhi.sampledimvc.data.net.RestApi;
import com.fhi.sampledimvc.mvp.view.activity.StarredActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Vinay on 12/9/2016.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public ActivityTestRule<StarredActivity> activityTestRule = new ActivityTestRule<>(StarredActivity.class);

    @Inject
    RestApi mockRestAPI;

    private static final String STARRED_USER_NAME = "JakeWharton";

    @Before
    public void setUp() {
        ((TestApplication) activityTestRule.getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    @Test
    public void correctStarredDataDisplayed() {
        List<GitHubRepoStarred> testStarredData = mockRestAPI.getStarredRepositories(STARRED_USER_NAME).toBlocking().first();

        onView(withId(R.id.titleTextView)).check(matches(withText(testStarredData.get(0).getName())));
    }
}
