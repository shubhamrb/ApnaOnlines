package com.mamits.apnaonlines.data.remote;

import com.google.gson.JsonObject;
import com.mamits.apnaonlines.ui.utils.constants.ApiConstant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.LOGIN_END_POINT)
    Call<JsonObject> userLogin(@Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.HOME_DATA_END_POINT)
    Call<JsonObject> fetchHomeData(@Header("Authorization") String accessToken);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.ORDERS_END_POINT)
    Call<JsonObject> fetchOrders(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.PAYMENTS_END_POINT)
    Call<JsonObject> fetchPayments(@Header("Authorization") String accessToken, @Body String jsonObject);
}