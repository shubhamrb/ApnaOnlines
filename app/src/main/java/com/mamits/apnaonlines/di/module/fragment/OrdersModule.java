package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.HomeViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.OrdersViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class OrdersModule {

    @Provides
    public OrdersViewModel providesOrders(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new OrdersViewModel(iDataManager, iSchedulerProvider);
    }
}
