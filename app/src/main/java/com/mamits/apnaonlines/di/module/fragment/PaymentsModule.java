package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.OrdersViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.PaymentsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PaymentsModule {

    @Provides
    public PaymentsViewModel providesPayments(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new PaymentsViewModel(iDataManager, iSchedulerProvider);
    }
}
