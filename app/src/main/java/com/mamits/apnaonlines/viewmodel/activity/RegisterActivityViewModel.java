package com.mamits.apnaonlines.viewmodel.activity;

import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.navigator.activity.MainActivityNavigator;
import com.mamits.apnaonlines.ui.navigator.activity.RegisterActivityNavigator;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.base.BaseViewModel;


public class RegisterActivityViewModel extends BaseViewModel<RegisterActivityNavigator> {


    public RegisterActivityViewModel(IDataManager mDataManager, ISchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }

}
