package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.HomeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    public HomeViewModel providesHome(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new HomeViewModel(iDataManager, iSchedulerProvider);
    }
}
