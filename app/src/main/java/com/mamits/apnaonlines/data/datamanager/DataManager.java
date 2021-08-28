package com.mamits.apnaonlines.data.datamanager;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.mamits.apnaonlines.data.local.pref.IPreferenceHelper;
import com.mamits.apnaonlines.data.local.pref.PreferenceHelper;
import com.mamits.apnaonlines.data.remote.ApiHelper;
import com.mamits.apnaonlines.data.remote.IApiHelper;
import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DataManager implements IDataManager {
    private static final String TAG = "DataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final Gson mGson;

    PreferenceHelper mPreferenceHelper;

    @Inject
    public DataManager(Context context, IApiHelper apiHelper, IPreferenceHelper preferenceHelper, Gson gson) {
        this.mContext = context;
        this.mApiHelper = (ApiHelper) apiHelper;
        this.mPreferenceHelper = (PreferenceHelper) preferenceHelper;
        this.mGson = gson;
    }

    @Override
    public void userLogin(Activity activity, String jsonObject, ResponseListener responseListener) {
        mApiHelper.userLogin(activity, jsonObject, responseListener);
    }

    @Override
    public void fetchHomeData(Activity mActivity, String accessToken, ResponseListener responseListener) {
        mApiHelper.fetchHomeData(mActivity, accessToken, responseListener);
    }

    @Override
    public void fetchOrders(Activity mActivity, String accessToken, int status, ResponseListener responseListener) {
        mApiHelper.fetchOrders(mActivity, accessToken,status, responseListener);
    }

    @Override
    public void fetchPayments(Activity mActivity, String accessToken, String pType, ResponseListener responseListener) {
        mApiHelper.fetchPayments(mActivity, accessToken,pType, responseListener);
    }

    @Override
    public String getAccessToken() {
        return mPreferenceHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferenceHelper.setAccessToken(accessToken);
    }

    @Override
    public void setCurrentUserId(int userId) {
        mPreferenceHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserId() {
        return mPreferenceHelper.getCurrentUserId();
    }

    @Override
    public String getUsername() {
        return mPreferenceHelper.getUsername();
    }

    @Override
    public void setUsername(String username) {
        mPreferenceHelper.setUsername(username);
    }

    @Override
    public String getUserNumber() {
        return mPreferenceHelper.getUserNumber();
    }

    @Override
    public void settUserNumber(String number) {
        mPreferenceHelper.settUserNumber(number);
    }

    @Override
    public void clearAllPreference() {
        mPreferenceHelper.clearAllPreference();
    }
}
