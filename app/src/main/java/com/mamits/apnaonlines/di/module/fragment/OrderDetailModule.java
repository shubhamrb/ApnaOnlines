package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.OrderDetailViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.OrdersViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class OrderDetailModule {

    @Provides
    public OrderDetailViewModel providesOrderDetail(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new OrderDetailViewModel(iDataManager, iSchedulerProvider);
    }
}
