package com.mamits.apnaonlines.data.remote;

import android.app.Activity;

import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;

public interface IApiHelper {

    void userLogin(Activity activity, String jsonObject, ResponseListener responseListener);

    void fetchHomeData(Activity mActivity, String accessToken, ResponseListener responseListener);

    void fetchOrders(Activity mActivity, String accessToken, int status, ResponseListener responseListener);

    void fetchPayments(Activity mActivity, String accessToken, String pType, ResponseListener responseListener);
}
