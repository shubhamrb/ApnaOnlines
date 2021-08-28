package com.mamits.apnaonlines.di.module;


import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mamits.apnaonlines.Application;
import com.mamits.apnaonlines.data.datamanager.DataManager;
import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.data.local.pref.IPreferenceHelper;
import com.mamits.apnaonlines.data.local.pref.PreferenceHelper;
import com.mamits.apnaonlines.data.remote.ApiHeader;
import com.mamits.apnaonlines.data.remote.ApiHelper;
import com.mamits.apnaonlines.data.remote.IApiHelper;
import com.mamits.apnaonlines.di.scope.PreferenceInfo;
import com.mamits.apnaonlines.ui.utils.constants.AppConstant;
import com.mamits.apnaonlines.ui.utils.rx.AppSchedulerProvider;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application Myapplication;


    @Provides
    @Singleton
    IApiHelper provideApiHelper(ApiHelper mApiHelper) {
        return mApiHelper;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        this.Myapplication = application;
        return application;
    }

    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstant.PREF_NAME;
    }

    @Provides
    @Singleton
    IPreferenceHelper providePreferencesHelper(PreferenceHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPrefs(Context context) {
           return context.getSharedPreferences(providePreferenceName(),Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(
            IPreferenceHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getAccessToken());
    }

    @Provides
    ISchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
