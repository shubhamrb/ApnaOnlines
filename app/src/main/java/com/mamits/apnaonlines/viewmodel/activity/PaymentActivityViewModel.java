package com.mamits.apnaonlines.viewmodel.activity;

import android.app.Activity;

import com.androidnetworking.error.ANError;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.navigator.activity.PaymentActivityNavigator;
import com.mamits.apnaonlines.ui.utils.commonClasses.NetworkUtils;
import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.base.BaseViewModel;

import org.json.JSONObject;


public class PaymentActivityViewModel extends BaseViewModel<PaymentActivityNavigator> {


    public PaymentActivityViewModel(IDataManager mDataManager, ISchedulerProvider mSchedulerProvider) {
        super(mDataManager, mSchedulerProvider);
    }

    public void fetchCfsToken(Activity mActivity, String orderId, String amount) {
        if (NetworkUtils.isNetworkConnected(mActivity)) {
            getmNavigator().get().showLoader();
            getmDataManger().fetchCfsToken(mActivity, getmDataManger().getAccessToken(), orderId, amount, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    try {
                        getmNavigator().get().hideLoader();
                        getmNavigator().get().onSuccessCfsToken(jsonObject);
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
