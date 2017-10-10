package com.fhi.sampledimvc.injector.di.modules;

import com.fhi.sampledimvc.data.repository.Github;
import com.fhi.sampledimvc.domain.GetStarredDataUseCase;
import com.fhi.sampledimvc.domain.GetUserUseCase;
import com.fhi.sampledimvc.injector.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by Vinay on 11/28/2016.
 */
@Module
public class UseCaseModule {

    public UseCaseModule() {
    }

    @Provides
    @PerActivity
    GetStarredDataUseCase provideChannelListUseCase(Github fhRepository, @Named("ui_thread") Scheduler uiThread,
                                                    @Named("executor_thread") Scheduler executorThread) {
        return new GetStarredDataUseCase(fhRepository, uiThread, executorThread);
    }

    @Provides
    @PerActivity
    GetUserUseCase provideUserCase(Github fhRepository, @Named("ui_thread") Scheduler uiThread,
                                   @Named("executor_thread") Scheduler executorThread) {
        return new GetUserUseCase(fhRepository, uiThread, executorThread);
    }

}
