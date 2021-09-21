package com.mamits.apnaonlines.viewmodel.fragment;


import android.app.Activity;

import com.androidnetworking.error.ANError;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.datamanager.IDataManager;
import com.mamits.apnaonlines.ui.navigator.fragment.OrderDetailNavigator;
import com.mamits.apnaonlines.ui.utils.commonClasses.NetworkUtils;
import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;
import com.mamits.apnaonlines.ui.utils.rx.ISchedulerProvider;
import com.mamits.apnaonlines.viewmodel.base.BaseViewModel;

import org.json.JSONObject;

import java.io.File;

public class OrderDetailViewModel extends BaseViewModel<OrderDetailNavigator> {

    public OrderDetailViewModel(IDataManager dataManager, ISchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void updateOrderStatus(Activity mActivity, String status, int order_id, String time, String type, String order_amount) {

        if (NetworkUtils.isNetworkConnected(mActivity)) {
            getmNavigator().get().showProgressBars();
            getmDataManger().updateOrderStatus(mActivity, getmDataManger().getAccessToken(), status, order_id, time, type, order_amount, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    try {
                        getmNavigator().get().hideProgressBars();

                        getmNavigator().get().onSuccessOrderStatusUpdated(jsonObject, status);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    try {
                        getmNavigator().get().hideProgressBars();

                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            if (anError.getErrorBody() != null) {
                                JSONObject object = new JSONObject(anError.getErrorBody());
                                try {
                                    getmNavigator().get().checkValidation(anError.getErrorCode(), object.optString("message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                            throwable.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } else {
            getmNavigator().get().checkInternetConnection(mActivity.getResources().getString(R.string.check_internet_connection));

        }
    }

    public void completeOrder(Activity mActivity, String des, int order_id, String pType, File uploadedFile) {

        if (NetworkUtils.isNetworkConnected(mActivity)) {
            getmNavigator().get().showProgressBars();
            getmDataManger().completeOrder(mActivity, getmDataManger().getAccessToken(), des, order_id, pType, uploadedFile, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    try {
                        getmNavigator().get().hideProgressBars();

                        getmNavigator().get().onSuccessOrderCompleted(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    try {
                        getmNavigator().get().hideProgressBars();

                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            if (anError.getErrorBody() != null) {
                                JSONObject object = new JSONObject(anError.getErrorBody());
                                try {
                                    getmNavigator().get().checkValidation(anError.getErrorCode(), object.optString("message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                            throwable.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } else {
            getmNavigator().get().checkInternetConnection(mActivity.getResources().getString(R.string.check_internet_connection));

        }
    }

    public void checkPaymentStatus(Activity mActivity, String order_id, String des, String pType) {

        if (NetworkUtils.isNetworkConnected(mActivity)) {
            getmNavigator().get().showProgressBars();
            getmDataManger().checkPaymentStatus(mActivity, getmDataManger().getAccessToken(), order_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    try {
                        getmNavigator().get().hideProgressBars();

                        getmNavigator().get().onSuccessPaymentStatus(jsonObject, des, pType);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    try {
                        getmNavigator().get().hideProgressBars();

                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            if (anError.getErrorBody() != null) {
                                JSONObject object = new JSONObject(anError.getErrorBody());
                                try {
                                    getmNavigator().get().checkValidation(anError.getErrorCode(), object.optString("message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                            throwable.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } else {
            getmNavigator().get().checkInternetConnection(mActivity.getResources().getString(R.string.check_internet_connection));

        }
    }
}
