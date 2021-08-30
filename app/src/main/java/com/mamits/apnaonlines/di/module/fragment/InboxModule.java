package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.HomeViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.InboxViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class InboxModule {

    @Provides
    public InboxViewModel providesInbox(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new InboxViewModel(iDataManager, iSchedulerProvider);
    }
}
