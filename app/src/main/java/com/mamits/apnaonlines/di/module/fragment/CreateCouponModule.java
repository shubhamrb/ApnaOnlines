package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.CreateCouponViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.OrderDetailViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateCouponModule {

    @Provides
    public CreateCouponViewModel providesCreateCoupon(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new CreateCouponViewModel(iDataManager, iSchedulerProvider);
    }
}
