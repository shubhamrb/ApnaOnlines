package com.mamits.apnaonlines.ui.utils.listeners;


import com.mamits.apnaonlines.data.model.ErrorObject;

public interface RetrofitListener {
    void onResponseSuccess(String responseBodyString, int apiFlag);

    void onResponseError(ErrorObject errorObject, Throwable throwable, int apiFlag);
}
