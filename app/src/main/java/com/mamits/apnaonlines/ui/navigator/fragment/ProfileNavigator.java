package com.mamits.apnaonlines.ui.navigator.fragment;


import com.mamits.apnaonlines.ui.navigator.base.BaseNavigator;

public interface ProfileNavigator extends BaseNavigator {


    void showProgressBars();

    void checkInternetConnection(String message);

    void hideProgressBars();

    void checkValidation(int errorCode, String message);

    void throwable(Throwable throwable);

}
