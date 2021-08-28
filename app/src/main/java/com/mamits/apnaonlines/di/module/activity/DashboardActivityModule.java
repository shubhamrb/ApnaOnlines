package com.mamits.apnaonlines.di.module.activity;


import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.activity.DashboardActivityViewModel;
import com.mamits.apnaonlines.viewmodel.activity.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardActivityModule {

    @Provides
    public DashboardActivityViewModel providesDashboardViewModel(IDataManager mDataManger, ISchedulerProvider mSchedulerProvider){
    return  new DashboardActivityViewModel(mDataManger,mSchedulerProvider);
    }

}
