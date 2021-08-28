package com.mamits.apnaonlines.ui.navigator.activity;

import com.mamits.apnaonlines.ui.navigator.base.BaseNavigator;

public interface RegisterActivityNavigator extends BaseNavigator {
    void showLoader();

    void hideLoader();


    void checkValidation(int type, String message);

    void throwable(Throwable it);

    void checkInternetConnection(String message);

}
