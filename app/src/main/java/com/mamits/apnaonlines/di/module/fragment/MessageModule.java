package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.InboxViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.MessageViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MessageModule {

    @Provides
    public MessageViewModel providesMessage(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new MessageViewModel(iDataManager, iSchedulerProvider);
    }
}
