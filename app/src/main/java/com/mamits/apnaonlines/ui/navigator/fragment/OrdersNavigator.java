package com.mamits.apnaonlines.ui.navigator.fragment;


import com.google.gson.JsonObject;
import com.mamits.apnaonlines.ui.navigator.base.BaseNavigator;

public interface OrdersNavigator extends BaseNavigator {


    void showProgressBars();

    void checkInternetConnection(String message);

    void hideProgressBars();

    void checkValidation(int errorCode, String message);

    void throwable(Throwable throwable);

    void onSuccessOrders(JsonObject jsonObject);
}
