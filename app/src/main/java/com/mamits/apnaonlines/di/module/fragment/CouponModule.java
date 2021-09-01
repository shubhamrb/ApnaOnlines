package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.CouponViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.InboxViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class CouponModule {

    @Provides
    public CouponViewModel providesCoupons(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new CouponViewModel(iDataManager, iSchedulerProvider);
    }
}
