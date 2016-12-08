package com.fhi.sampledimvc.mvp.presenter;

import com.fhi.sampledimvc.data.entity.users.User;
import com.fhi.sampledimvc.domain.GetUserUseCase;
import com.fhi.sampledimvc.mvp.view.StarredView;
import com.fhi.sampledimvc.mvp.view.UserView;
import com.fhi.sampledimvc.mvp.view.View;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vinay on 11/29/2016.
 */
public class UserPresenter implements Presenter {

    private UserView mView;
    private final GetUserUseCase mUserListCase;

    @Inject
    public UserPresenter(GetUserUseCase userDataUseCase) {
        mUserListCase = userDataUseCase;
    }

    @Override
    public void attachView(View v) {
        mView = (UserView) v;
    }

    public void initialize() {
        mView.displayLoadingScreen();
        mUserListCase.execute()
                .subscribe(this::onUserResultsReceived, this::onUserResultsError);
    }

    private void onUserResultsError(Throwable throwable) {
        mView.hideLoadingScreen();
        mView.fetchDataError();
        throwable.printStackTrace();
    }

    private void onUserResultsReceived(List<User> users) {
        mView.hideLoadingScreen();
        mView.updateUSerResult(users);
    }
}
