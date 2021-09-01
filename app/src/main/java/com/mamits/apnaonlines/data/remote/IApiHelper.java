package com.mamits.apnaonlines.data.remote;

import android.app.Activity;

import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;

public interface IApiHelper {

    void userLogin(Activity activity, String jsonObject, ResponseListener responseListener);

    void fetchHomeData(Activity mActivity, String accessToken, ResponseListener responseListener);

    void fetchOrders(Activity mActivity, String accessToken, int status, ResponseListener responseListener);

    void fetchPayments(Activity mActivity, String accessToken, ResponseListener responseListener);

    void fetchTransactions(Activity mActivity, String accessToken, String pType, ResponseListener responseListener);

    void fetchHelp(Activity mActivity, String accessToken, ResponseListener responseListener);

    void sendOtp(Activity mActivity, String number, ResponseListener responseListener);

    void verifyOtp(Activity mActivity, String number, String otp, ResponseListener responseListener);

    void updatePin(Activity mActivity, String number, String newPin, ResponseListener responseListener);

    void updateOrderStatus(Activity mActivity, String accessToken, String status, int order_id, String time, String type, String order_amount, ResponseListener responseListener);

    void fetchMessage(Activity mActivity, String accessToken, int user_id, int order_id, ResponseListener responseListener);

    void sendMessage(Activity mActivity, String accessToken, int user_id, int order_id, String message, ResponseListener responseListener);

    void fetchCoupons(Activity mActivity, String accessToken, ResponseListener responseListener);
}
