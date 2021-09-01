package com.mamits.apnaonlines.data.remote;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.mamits.apnaonlines.ui.utils.constants.AppConstant;
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
    public void fetchPayments(Activity mActivity, String accessToken, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        call.fetchPayments("Bearer " + accessToken).enqueue(new Callback<JsonObject>() {
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
    public void fetchTransactions(Activity mActivity, String accessToken, String pType, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("payment_type", pType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        call.fetchTransactions("Bearer " + accessToken, jsonObject.toString()).enqueue(new Callback<JsonObject>() {
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
    public void fetchHelp(Activity mActivity, String accessToken, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        call.fetchHelp("Bearer " + accessToken).enqueue(new Callback<JsonObject>() {
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
    public void sendOtp(Activity mActivity, String number, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("api_key", AppConstant.API_KEY);
            jsonObject.put("mobile", number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        call.sendOtp(jsonObject.toString()).enqueue(new Callback<JsonObject>() {
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
    public void verifyOtp(Activity mActivity, String number, String otp, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("api_key", AppConstant.API_KEY);
            jsonObject.put("mobile", number);
            jsonObject.put("otp", otp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        call.verifyOtp(jsonObject.toString()).enqueue(new Callback<JsonObject>() {
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
    public void updatePin(Activity mActivity, String number, String newPin, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("api_key", AppConstant.API_KEY);
            jsonObject.put("mobile", number);
            jsonObject.put("password", newPin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        call.updatePin(jsonObject.toString()).enqueue(new Callback<JsonObject>() {
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
    public void updateOrderStatus(Activity mActivity, String accessToken, String status, int order_id, String time, String type, String order_amount, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid", order_id);
            jsonObject.put("status", status);
            if (status.equalsIgnoreCase("accept") && time != null && type != null && order_amount != null) {
                jsonObject.put("time", time);
                jsonObject.put("type", type);
                jsonObject.put("order_amount", order_amount);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        call.updateOrderStatus("Bearer " + accessToken, jsonObject.toString()).enqueue(new Callback<JsonObject>() {
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
    public void fetchMessage(Activity mActivity, String accessToken, int user_id, int order_id, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid", order_id);
            jsonObject.put("userid", user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        call.fetchMessages("Bearer " + accessToken, jsonObject.toString()).enqueue(new Callback<JsonObject>() {
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
    public void sendMessage(Activity mActivity, String accessToken, int user_id, int order_id, String message, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderid", order_id);
            jsonObject.put("userid", user_id);
            jsonObject.put("message", message);
//            jsonObject.put("chatfile", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        call.sendMessages("Bearer " + accessToken, jsonObject.toString()).enqueue(new Callback<JsonObject>() {
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
    public void fetchCoupons(Activity mActivity, String accessToken, ResponseListener responseListener) {
        RetrofitInterface call = new RetrofitBase(mActivity, true).retrofit.create(RetrofitInterface.class);

        call.fetchCoupons("Bearer " + accessToken).enqueue(new Callback<JsonObject>() {
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
