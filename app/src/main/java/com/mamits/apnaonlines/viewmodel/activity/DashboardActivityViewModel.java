package com.mamits.apnaonlines.viewmodel.activity;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.navigator.activity.DashboardActivityNavigator;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.base.BaseViewModel;


public class DashboardActivityViewModel extends BaseViewModel<DashboardActivityNavigator> {


    public DashboardActivityViewModel(IDataManager mDataManager, ISchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }

}
