package com.mamits.apnaonlines.viewmodel.activity;

import android.app.Activity;

import com.androidnetworking.error.ANError;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.navigator.activity.DashboardActivityNavigator;
import com.mamits.apnaonlines.ui.utils.commonClasses.NetworkUtils;
import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.base.BaseViewModel;

import org.json.JSONObject;

public class DashboardActivityViewModel extends BaseViewModel<DashboardActivityNavigator> {


    public DashboardActivityViewModel(IDataManager mDataManager, ISchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }

    public void openStore(Activity mActivity, JSONObject jsonObject) {
        if (NetworkUtils.isNetworkConnected(mActivity)) {
            getmNavigator().get().showLoader();
            getmDataManger().openStore(mActivity, getmDataManger().getAccessToken(), jsonObject, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    try {
                        getmNavigator().get().hideLoader();
                        getmNavigator().get().onSuccessStoreStatus(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    try {
                        getmNavigator().get().hideLoader();
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            if (anError.getErrorBody() != null) {
                                JSONObject object = new JSONObject(anError.getErrorBody());
                                try {
                                    getmNavigator().get().checkValidation(anError.getErrorCode(), object.optString("message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                            throwable.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } else {
            getmNavigator().get().checkInternetConnection(mActivity.getResources().getString(R.string.check_internet_connection));

        }
    }
}
