package com.mamits.apnaonlines.data.remote;

import com.google.gson.JsonObject;
import com.mamits.apnaonlines.ui.utils.constants.ApiConstant;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.LOGIN_END_POINT)
    Call<JsonObject> userLogin(@Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.HOME_DATA_END_POINT)
    Call<JsonObject> fetchHomeData(@Header("Authorization") String accessToken);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.PAYMENT_KEYS_END_POINT)
    Call<JsonObject> fetchPaymentKeys(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_CFSTOKEN_END_POINT)
    Call<JsonObject> fetchCfsToken(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_PAYTM_TOKEN_END_POINT)
    Call<JsonObject> fetchPaytmToken(@Header("Authorization") String accessToken, @Body String jsonObject);

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
    @POST(ApiConstant.SIGNUP_END_POINT)
    Call<JsonObject> signUp(@Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.REGISTRATION_END_POINT)
    Call<JsonObject> doRegistration(@Body String jsonObject);

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
    @POST(ApiConstant.CHECK_PAYMENT_STATUS_END_POINT)
    Call<JsonObject> checkPaymentStatus(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_MESSAGES_END_POINT)
    Call<JsonObject> fetchMessages(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Multipart
    @POST(ApiConstant.SEND_MESSAGE_END_POINT)
    Call<JsonObject> sendMessages(@Header("Authorization") String accessToken, @Part("orderid")RequestBody orderid, @Part("userid")RequestBody userid, @Part("message")RequestBody message, @Part MultipartBody.Part chatfile);

    @POST(ApiConstant.FETCH_COUPONS_END_POINT)
    Call<JsonObject> fetchCoupons(@Header("Authorization") String accessToken);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.CREATE_COUPON_END_POINT)
    Call<JsonObject> createCoupon(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_SERVICES_END_POINT)
    Call<JsonObject> fetchServices(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.DELETE_COUPON_END_POINT)
    Call<JsonObject> deleteCoupon(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_CAT_SUB_CATEGORY_END_POINT)
    Call<JsonObject> fetchCatSubCategory(@Header("Authorization") String accessToken);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.ALL_CATEGORY_END_POINT)
    Call<JsonObject> fetchAllCategory(@Header("Authorization") String accessToken);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.UPDATE_CATEGORY_END_POINT)
    Call<JsonObject> updateCategory(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.DELETE_SERVICE_END_POINT)
    Call<JsonObject> deleteService(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_PRODUCTS_END_POINT)
    Call<JsonObject> fetchProducts(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.ADD_SERVICE_END_POINT)
    Call<JsonObject> addService(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.UPDATE_SERVICE_END_POINT)
    Call<JsonObject> updateService(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.CHANGE_PASSWORD_END_POINT)
    Call<JsonObject> changePassword(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.STORE_OPEN_END_POINT)
    Call<JsonObject> storeOpen(@Header("Authorization") String accessToken, @Body String jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.FETCH_VENDOR_PROFILE_END_POINT)
    Call<JsonObject> fetchStoreStatus(@Header("Authorization") String accessToken);

    @Multipart
    @POST(ApiConstant.COMPLETE_ORDER_END_POINT)
    Call<JsonObject> completeOrder(@Header("Authorization") String accessToken, @Part("orderid")RequestBody orderid, @Part("description")RequestBody description, @Part("payment_accept_mode")RequestBody payment_accept_mode, @Part MultipartBody.Part file);

    @Headers("Content-Type: application/json")
    @POST(ApiConstant.SAVE_PAYMENT_RESPONSE_END_POINT)
    Call<JsonObject> savePaymentResponse(@Header("Authorization") String accessToken, @Body String jsonObject);
}