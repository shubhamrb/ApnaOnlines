package com.mamits.apnaonlines.data.datamanager;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.mamits.apnaonlines.data.local.pref.IPreferenceHelper;
import com.mamits.apnaonlines.data.local.pref.PreferenceHelper;
import com.mamits.apnaonlines.data.model.login.LoginDataModel;
import com.mamits.apnaonlines.data.remote.ApiHelper;
import com.mamits.apnaonlines.data.remote.IApiHelper;
import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;

import org.json.JSONObject;

import java.io.File;

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
    public void fetchPaymentKeys(Activity mActivity, String accessToken, ResponseListener responseListener) {
        mApiHelper.fetchPaymentKeys(mActivity, accessToken, responseListener);
    }

    @Override
    public void fetchCfsToken(Activity mActivity, String accessToken, String orderId, String amount, ResponseListener responseListener) {
        mApiHelper.fetchCfsToken(mActivity, accessToken, orderId, amount, responseListener);
    }

    @Override
    public void fetchPaytmToken(Activity mActivity, String accessToken, String orderId, String amount, String customerPhone, String customerEmail, ResponseListener responseListener) {
        mApiHelper.fetchPaytmToken(mActivity, accessToken, orderId, amount, customerPhone, customerEmail, responseListener);
    }

    @Override
    public void fetchOrders(Activity mActivity, String accessToken, int status, ResponseListener responseListener) {
        mApiHelper.fetchOrders(mActivity, accessToken, status, responseListener);
    }

    @Override
    public void fetchPayments(Activity mActivity, String accessToken, ResponseListener responseListener) {
        mApiHelper.fetchPayments(mActivity, accessToken, responseListener);
    }

    @Override
    public void fetchTransactions(Activity mActivity, String accessToken, String pType, ResponseListener responseListener) {
        mApiHelper.fetchTransactions(mActivity, accessToken, pType, responseListener);
    }

    @Override
    public void fetchHelp(Activity mActivity, String accessToken, ResponseListener responseListener) {
        mApiHelper.fetchHelp(mActivity, accessToken, responseListener);
    }

    @Override
    public void sendOtp(Activity mActivity, String number, ResponseListener responseListener) {
        mApiHelper.sendOtp(mActivity, number, responseListener);
    }

    @Override
    public void signUp(Activity mActivity, String number, ResponseListener responseListener) {
        mApiHelper.signUp(mActivity, number, responseListener);
    }

    @Override
    public void doRegistration(Activity mActivity, JSONObject jsonObject, ResponseListener responseListener) {
        mApiHelper.doRegistration(mActivity, jsonObject, responseListener);
    }

    @Override
    public void verifyOtp(Activity mActivity, String number, String otp, ResponseListener responseListener) {
        mApiHelper.verifyOtp(mActivity, number, otp, responseListener);
    }

    @Override
    public void updatePin(Activity mActivity, String number, String newPin, ResponseListener responseListener) {
        mApiHelper.updatePin(mActivity, number, newPin, responseListener);
    }

    @Override
    public void updateOrderStatus(Activity mActivity, String accessToken, String status, int order_id, String time, String type, String order_amount, ResponseListener responseListener) {
        mApiHelper.updateOrderStatus(mActivity, accessToken, status, order_id, time, type, order_amount, responseListener);
    }

    @Override
    public void completeOrder(Activity mActivity, String accessToken, String des, int order_id, String pType, File uploadedFile, ResponseListener responseListener) {
        mApiHelper.completeOrder(mActivity, accessToken, des, order_id, pType, uploadedFile, responseListener);
    }

    @Override
    public void fetchMessage(Activity mActivity, String accessToken, int user_id, int order_id, ResponseListener responseListener) {
        mApiHelper.fetchMessage(mActivity, accessToken, user_id, order_id, responseListener);
    }

    @Override
    public void sendMessage(Activity mActivity, String accessToken, int user_id, int order_id, String message, File uploadedFile, ResponseListener responseListener) {
        mApiHelper.sendMessage(mActivity, accessToken, user_id, order_id, message, uploadedFile, responseListener);
    }

    @Override
    public void fetchCoupons(Activity mActivity, String accessToken, ResponseListener responseListener) {
        mApiHelper.fetchCoupons(mActivity, accessToken, responseListener);
    }

    @Override
    public void createCoupon(Activity mActivity, String accessToken, JSONObject couponObject, ResponseListener responseListener) {
        mApiHelper.createCoupon(mActivity, accessToken, couponObject, responseListener);
    }

    @Override
    public void fetchServices(Activity mActivity, String accessToken, String category, String subCategory, ResponseListener responseListener) {
        mApiHelper.fetchServices(mActivity, accessToken, category, subCategory, responseListener);
    }

    @Override
    public void fetchCategorySubcategory(Activity mActivity, String accessToken, ResponseListener responseListener) {
        mApiHelper.fetchCategorySubcategory(mActivity, accessToken, responseListener);
    }

    @Override
    public void updateCategory(Activity mActivity, String accessToken, JSONObject jsonObject, ResponseListener responseListener) {
        mApiHelper.updateCategory(mActivity, accessToken, jsonObject, responseListener);
    }

    @Override
    public void deleteCoupon(Activity mActivity, String accessToken, String couponid, ResponseListener responseListener) {
        mApiHelper.deleteCoupon(mActivity, accessToken, couponid, responseListener);
    }

    @Override
    public void deleteService(Activity mActivity, String accessToken, String inventoryId, ResponseListener responseListener) {
        mApiHelper.deleteService(mActivity, accessToken, inventoryId, responseListener);
    }

    @Override
    public void fetchProducts(Activity mActivity, String accessToken, String cat, String sub_cat, ResponseListener responseListener) {
        mApiHelper.fetchProducts(mActivity, accessToken, cat, sub_cat, responseListener);
    }

    @Override
    public void addService(Activity mActivity, String accessToken, JSONObject productDataModel, ResponseListener responseListener) {
        mApiHelper.addService(mActivity, accessToken, productDataModel, responseListener);
    }

    @Override
    public void updateService(Activity mActivity, String accessToken, JSONObject productDataModel, ResponseListener responseListener) {
        mApiHelper.updateService(mActivity, accessToken, productDataModel, responseListener);
    }

    @Override
    public void changePassword(Activity mActivity, String accessToken, JSONObject object, ResponseListener responseListener) {
        mApiHelper.changePassword(mActivity, accessToken, object, responseListener);
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
    public String getUserData() {
        return mPreferenceHelper.getUserData();
    }

    @Override
    public void setUserData(LoginDataModel loginDataModel) {
        mPreferenceHelper.setUserData(loginDataModel);
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
    public String getUserEmail() {
        return mPreferenceHelper.getUserEmail();
    }

    @Override
    public void settUserEmail(String email) {
        mPreferenceHelper.settUserEmail(email);
    }

    @Override
    public void clearAllPreference() {
        mPreferenceHelper.clearAllPreference();
    }
}
