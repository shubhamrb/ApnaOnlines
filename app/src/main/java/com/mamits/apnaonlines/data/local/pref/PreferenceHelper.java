package com.mamits.apnaonlines.data.local.pref;

import android.content.SharedPreferences;

import com.mamits.apnaonlines.ui.utils.constants.AppConstant;

import javax.inject.Inject;


public class PreferenceHelper implements IPreferenceHelper {

    private SharedPreferences mSharedPreferences;

    @Inject
    public PreferenceHelper(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    @Override
    public void setAccessToken(String accessToken) {
        mSharedPreferences.edit().putString(AppConstant.PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getAccessToken() {
        return mSharedPreferences.getString(AppConstant.PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setCurrentUserId(int userId) {
        mSharedPreferences.edit().putInt(AppConstant.PREF_KEY_USER_ID, userId).apply();
    }

    @Override
    public int getCurrentUserId() {
        return mSharedPreferences.getInt(AppConstant.PREF_KEY_USER_ID,-1);
    }

    @Override
    public String getUsername() {
        return mSharedPreferences.getString(AppConstant.PREF_KEY_USER_NAME, null);
    }

    @Override
    public void setUsername(String username) {
        mSharedPreferences.edit().putString(AppConstant.PREF_KEY_USER_NAME, username).apply();
    }

    @Override
    public String getUserNumber() {
        return mSharedPreferences.getString(AppConstant.PREF_KEY_USER_NUMBER, null);
    }

    @Override
    public void settUserNumber(String number) {
        mSharedPreferences.edit().putString(AppConstant.PREF_KEY_USER_NUMBER, number).apply();
    }

    @Override
    public void clearAllPreference() {
        mSharedPreferences.edit().clear().apply();
    }


}
