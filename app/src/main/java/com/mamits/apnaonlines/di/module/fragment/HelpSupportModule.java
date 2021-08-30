package com.mamits.apnaonlines.di.module.fragment;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.fragment.HelpSupportViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.TransactionsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class HelpSupportModule {

    @Provides
    public HelpSupportViewModel providesHelpView(IDataManager iDataManager, ISchedulerProvider iSchedulerProvider) {
        return new HelpSupportViewModel(iDataManager, iSchedulerProvider);
    }
}
