package com.mamits.apnaonlines.viewmodel.fragment;


import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.navigator.fragment.DashboardFragmentNavigator;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.base.BaseViewModel;

public class DashboardFragmentViewModel extends BaseViewModel<DashboardFragmentNavigator> {

    public DashboardFragmentViewModel(IDataManager dataManager, ISchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


}
