package com.mamits.apnaonlines.data.remote;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.mamits.apnaonlines.ui.utils.listeners.ResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ApiHelper implements IApiHelper {

    @Inject
    public ApiHelper() {
    }


    @Override
    public void userLogin(Activity mActivity, String jsonObject, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        call.userLogin(jsonObject).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                responseListener.onFailed(t);
            }
        });


    }

    @Override
    public void fetchHomeData(Activity mActivity, String accessToken, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        call.fetchHomeData("Bearer " + accessToken).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                responseListener.onFailed(t);
            }
        });
    }

    @Override
    public void fetchOrders(Activity mActivity, String accessToken, int status, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        call.fetchOrders("Bearer " + accessToken, jsonObject.toString()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                responseListener.onFailed(t);
            }
        });
    }

    @Override
    public void fetchPayments(Activity mActivity, String accessToken, String pType, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("payment_type", pType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        call.fetchPayments("Bearer " + accessToken, jsonObject.toString()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                responseListener.onFailed(t);
            }
        });
    }
}
