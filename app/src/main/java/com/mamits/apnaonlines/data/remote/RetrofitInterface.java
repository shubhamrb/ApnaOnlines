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
    Call<JsonObject> fetchPayments(@Header("Authorization") String accessToken);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.TRANSACTIONS_END_POINT)
    Call<JsonObject> fetchTransactions(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.HELP_END_POINT)
    Call<JsonObject> fetchHelp(@Header("Authorization") String accessToken);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.SEND_OTP_END_POINT)
    Call<JsonObject> sendOtp(@Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.VERIFY_OTP_END_POINT)
    Call<JsonObject> verifyOtp(@Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.UPDATE_PIN_END_POINT)
    Call<JsonObject> updatePin(@Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.UPDATE_ORDER_STATUS_END_POINT)
    Call<JsonObject> updateOrderStatus(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_MESSAGES_END_POINT)
    Call<JsonObject> fetchMessages(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.SEND_MESSAGE_END_POINT)
    Call<JsonObject> sendMessages(@Header("Authorization") String accessToken, @Body String jsonObject);
}