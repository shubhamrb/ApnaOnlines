package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.AddServiceViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.CreateCouponViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddServiceModule {

    @Provides
    public AddServiceViewModel providesAddService(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new AddServiceViewModel(iDataManager, iSchedulerProvider);
    }
}
