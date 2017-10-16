/*
 * Copyright 2015 - 2016 Egor Andreevici
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.fhi.sampledimvc.di;

import com.fhi.sampledimvc.TestApplication;
import com.fhi.sampledimvc.data.repository.Github;
import com.fhi.sampledimvc.injector.di.components.ApplicationComponent;
import com.fhi.sampledimvc.mvp.view.Util.DividerItemDecoration;
import com.fhi.sampledimvc.tests.MainActivityTests;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends ApplicationComponent {

    void inject(MainActivityTests test);

    Github sampleRepository();

    TestApplication application();

    DividerItemDecoration dividerItem();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();
}
