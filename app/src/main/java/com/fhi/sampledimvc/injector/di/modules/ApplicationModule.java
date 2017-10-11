package com.fhi.sampledimvc.injector.di.modules;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.SampleTestApplication;
import com.fhi.sampledimvc.Util;
import com.fhi.sampledimvc.data.net.RestAPIImpl;
import com.fhi.sampledimvc.data.net.RestApi;
import com.fhi.sampledimvc.data.repository.Github;
import com.fhi.sampledimvc.mvp.view.Util.DividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vinay on 9/16/2016.
 */
@Module
public class ApplicationModule {
    private final SampleTestApplication mApplication;
    String mBaseUrl = "https://api.github.com/";

    public ApplicationModule(SampleTestApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    SampleTestApplication provideAndroidApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    Drawable provideDividerDrawable() {
        return ContextCompat.getDrawable(mApplication.getApplicationContext(), R.drawable.divider);
    }

    @Provides
    @Singleton
    DividerItemDecoration provideDividerItem(Drawable drawable) {
        return new DividerItemDecoration(drawable);
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    Cache provideHttpCache(SampleTestApplication application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    String provideBaseURL() {
        return mBaseUrl;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, Context context) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request request = chain.request();
            if (!Util.isInternetConnection(context)) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();
                request = request.newBuilder()
                        .header("Accept", "application/json;odata=verbose")
                        .cacheControl(cacheControl)
                        .method(request.method(), request.body())
                        .build();
            } else {
                request = request.newBuilder()
                        .header("Accept", "application/json;odata=verbose")
                        .method(request.method(), request.body())
                        .build();
            }
            Log.d("API Call", "URL : " + request.url());
            return chain.proceed(request);
        });

        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    RestApi provideRestApi(Gson gson, OkHttpClient okHttpClient, String baseURL) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseURL)
                .client(okHttpClient)
                .build()
                .create(RestApi.class);
    }

    @Provides
    @Singleton
    Github provideDataRepository(RestAPIImpl restDataSource) {
        return restDataSource;
    }






}
