package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.OrdersViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.ServicesViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {

    @Provides
    public ServicesViewModel providesServices(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new ServicesViewModel(iDataManager, iSchedulerProvider);
    }
}
