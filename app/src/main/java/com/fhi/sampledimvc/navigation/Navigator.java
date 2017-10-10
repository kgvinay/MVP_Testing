
package com.fhi.sampledimvc.navigation;

import android.content.Context;
import android.content.Intent;

import com.fhi.sampledimvc.mvp.view.activity.ReposActivity;
import com.fhi.sampledimvc.mvp.view.activity.StarredActivity;
import com.fhi.sampledimvc.mvp.view.activity.UserActivity;

import javax.inject.Inject;

/**
 * Class used to navigate through the application.
 * @author Edited by David Wu (david10608@gmail.com)
 */

public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToStarredPage(Context context) {
        if (context != null) {
            Intent intentToLaunch = StarredActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToReposPage(Context context) {
        if (context != null) {
            Intent newPage = ReposActivity.getCallingIntent(context);
            context.startActivity(newPage);
        }
    }

    public void navigateToUserPage(Context context) {
        if (context != null) {
            Intent intentToLaunch = UserActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}

