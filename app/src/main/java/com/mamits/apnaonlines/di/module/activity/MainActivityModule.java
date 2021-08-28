package com.mamits.apnaonlines.di.module.activity;


import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.activity.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    public MainActivityViewModel providesMainActivityViewModel(IDataManager mDataManger, ISchedulerProvider mSchedulerProvider){
    return  new MainActivityViewModel(mDataManger,mSchedulerProvider);
    }

}
