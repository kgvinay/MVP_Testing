
package com.fhi.sampledimvc.navigation;

import android.content.Context;
import android.content.Intent;
import com.fhi.sampledimvc.mvp.view.activity.StarredActivity;
import com.fhi.sampledimvc.mvp.view.activity.UserActivity;

import javax.inject.Inject;

/**
 * Class used to navigate through the application.
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

    public void navigateToUserPage(Context context){
        if(context != null ){
            Intent intentToLaunch = UserActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}

