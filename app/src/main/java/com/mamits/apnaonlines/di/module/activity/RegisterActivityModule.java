package com.mamits.apnaonlines.di.module.activity;


import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.activity.MainActivityViewModel;
import com.mamits.apnaonlines.viewmodel.activity.RegisterActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterActivityModule {

    @Provides
    public RegisterActivityViewModel providesRegisterActivityViewModel(IDataManager mDataManger, ISchedulerProvider mSchedulerProvider){
    return  new RegisterActivityViewModel(mDataManger,mSchedulerProvider);
    }

}
