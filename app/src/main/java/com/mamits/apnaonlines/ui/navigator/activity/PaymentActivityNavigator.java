package com.mamits.apnaonlines.ui.navigator.activity;

import com.google.gson.JsonObject;
import com.mamits.apnaonlines.ui.navigator.base.BaseNavigator;

public interface PaymentActivityNavigator extends BaseNavigator {
    void showLoader();

    void hideLoader();


    void checkValidation(int type, String message);

    void throwable(Throwable it);

    void checkInternetConnection(String message);

    void onSuccessCfsToken(JsonObject jsonObject);

    void onSuccessSavePaymentResponse(JsonObject jsonObject);
}
