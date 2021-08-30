package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.PaymentsViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.TransactionsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionsModule {

    @Provides
    public TransactionsViewModel providesTransactions(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new TransactionsViewModel(iDataManager, iSchedulerProvider);
    }
}
