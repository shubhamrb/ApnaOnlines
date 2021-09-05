package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.ChangePasswordViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.CreateCouponViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ChangePasswordModule {

    @Provides
    public ChangePasswordViewModel providesChangePassword(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new ChangePasswordViewModel(iDataManager, iSchedulerProvider);
    }
}
