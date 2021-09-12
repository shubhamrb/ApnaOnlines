package com.mamits.apnaonlines.di.module.activity;


import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.activity.MainActivityViewModel;
import com.mamits.apnaonlines.viewmodel.activity.PaymentActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PaymentActivityModule {

    @Provides
    public PaymentActivityViewModel providesPaymentActivityViewModel(IDataManager mDataManger, ISchedulerProvider mSchedulerProvider){
    return  new PaymentActivityViewModel(mDataManger,mSchedulerProvider);
    }

}
