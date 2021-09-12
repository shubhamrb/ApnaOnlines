package com.mamits.apnaonlines.viewmodel.fragment;


import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.navigator.fragment.ProfileNavigator;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.base.BaseViewModel;

public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {

    public ProfileViewModel(IDataManager dataManager, ISchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
