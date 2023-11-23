package com.mamits.apnaonlines.ui.activity;

import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.cashfree.pg.CFPaymentService.PARAM_NOTIFY_URL;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cashfree.pg.CFPaymentService;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.databinding.ActivityPaymentBinding;
import com.mamits.apnaonlines.ui.base.BaseActivity;
import com.mamits.apnaonlines.ui.navigator.activity.PaymentActivityNavigator;
import com.mamits.apnaonlines.viewmodel.activity.PaymentActivityViewModel;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;
import com.phonepe.intent.sdk.api.B2BPGRequest;
import com.phonepe.intent.sdk.api.B2BPGRequestBuilder;
import com.phonepe.intent.sdk.api.PhonePe;
import com.phonepe.intent.sdk.api.PhonePeInitException;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentActivityViewModel> implements PaymentActivityNavigator, View.OnClickListener, PaytmPaymentTransactionCallback {
    String TAG = "PaymentActivity";
    @Inject
    PaymentActivityViewModel mViewModel;
    ActivityPaymentBinding binding;
    private String orderId, customerName, customerPhone, customerEmail, amount, appid, m_id;
    private static final String STAGE_CALLBACK_URL_PAYTM = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";
    private static final String PROD_CALLBACK_URL_PAYTM = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=";
    private String PHONEPE_MERCHANT_ID = "APNAONLINES";
    private String PHONEPE_SALT = "331a8273-c2d5-4590-9369-2b12f1bb1e1e";
    private String apiEndPoint = "/pg/v1/pay";

    @Override
    public int getBindingVariable() {
        return BR.paymentView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding = getViewDataBinding();
        mViewModel = getMyViewModel();
        mViewModel.setNavigator(this);

        /*phonepe sdk initialized*/
        PhonePe.init(this);

        binding.btnHome.setOnClickListener(this);
        if (!mViewModel.getmDataManger().isPaymentOpen()) {
            mViewModel.getmDataManger().setPaymentOpen(true);

            amount = getIntent().getStringExtra("amount");
            customerName = mViewModel.getmDataManger().getUsername();
            customerPhone = mViewModel.getmDataManger().getUserNumber();
            customerEmail = mViewModel.getmDataManger().getUserEmail();
            if (getIntent().hasExtra("appid")) {
                appid = getIntent().getStringExtra("appid");
            } else if (getIntent().hasExtra("m_id")) {
                m_id = getIntent().getStringExtra("m_id");
            } else if (getIntent().hasExtra("phonepe_m_id")) {
                PHONEPE_MERCHANT_ID = getIntent().getStringExtra("phonepe_m_id");
            }
            startPayment();
        }

    }

    private void startPayment() {
        int n = 10000 + new Random().nextInt(90000);
        int m = (int) Math.pow(10, n - 1);
        orderId = String.valueOf(m + new Random().nextInt(9 * m)).replace("-", "");
        if (appid != null) {
            getCfsToken();
        } else if (m_id != null) {
            getPaytmToken();
        } else if (PHONEPE_MERCHANT_ID != null) {
            JSONObject data = new JSONObject();
            try {
                data.put("merchantTransactionId", orderId);
                data.put("merchantId", PHONEPE_MERCHANT_ID);
                data.put("amount", 100);
                data.put("mobileNumber", customerPhone);
                data.put("callbackUrl", "https://webhook.site/callback-url");

                JSONObject paymentInstrument = new JSONObject();
                paymentInstrument.put("type", "UPI_INTENT");
                paymentInstrument.put("targetApp", "com.phonepe.app");
                data.put("paymentInstrument", paymentInstrument);

                JSONObject deviceContext = new JSONObject();
                deviceContext.put("deviceOS", "ANDROID");

                data.put("deviceContext", deviceContext);
                String payloadBase64;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    payloadBase64 = Base64.getEncoder().encodeToString(data.toString().getBytes(Charset.defaultCharset()));
                } else {
                    payloadBase64 = android.util.Base64.encodeToString(data.toString().getBytes(), android.util.Base64.DEFAULT);
                }
                String checksum = sha256(payloadBase64 + apiEndPoint + PHONEPE_SALT) + "###1";
                String checksum1 = sha256("/pg/v1/status/" + PHONEPE_MERCHANT_ID + "/" + orderId + PHONEPE_SALT) + "###1";

                Log.d(TAG, "merchantTransactionId : " + orderId);
                Log.d(TAG, "payloadBase64 : " + payloadBase64);
                Log.d(TAG, "checksum : " + checksum);
                Log.d(TAG, "checksum1 : " + checksum1);

                B2BPGRequest b2BPGRequest = new B2BPGRequestBuilder()
                        .setData(payloadBase64)
                        .setChecksum(checksum)
                        .setUrl(apiEndPoint)
                        .build();

                try {
                    Intent intent = PhonePe.getImplicitIntent(this, b2BPGRequest, "com.phonepe.app");
                    startActivityForResult(intent, 1);
                } catch (PhonePeInitException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private String sha256(String base64) {
        try {
            byte[] bytes = base64.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(bytes);
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void getPaytmToken() {
        mViewModel.fetchPaytmToken(this, orderId, amount, customerPhone, customerEmail);
    }

    private void getCfsToken() {
        mViewModel.fetchCfsToken(this, orderId, amount);
    }

    @Override
    protected PaymentActivityViewModel getMyViewModel() {
        return mViewModel;
    }

    @Override
    public void showLoader() {
        showLoading();
    }

    @Override
    public void hideLoader() {
        hideLoading();
    }


    @Override
    public void checkValidation(int type, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void throwable(Throwable it) {
        it.printStackTrace();
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void checkInternetConnection(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessCfsToken(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                String token = jsonObject.get("data").getAsString();

                if (token != null && !token.equals("")) {
                    doPayment(token);
                }
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                binding.btnHome.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSuccessSavePaymentResponse(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {

            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Log.e(TAG, message);
            }
        }
    }

    private void doPayment(String token) {
        if (appid != null) {
            /*cashfree*/
            Map<String, String> params = getInputParams();
            if (params != null) {
                Log.e(TAG, "params : " + params);
                CFPaymentService.getCFPaymentServiceInstance().doPayment(this, params, token, "PROD");
            } else {
                Log.e(TAG, "PAYMENT : " + "keys not found");
                finish();
            }
        } else {
            /*paytm*/
            PaytmOrder paytmOrder = new PaytmOrder(orderId, m_id, token, amount, PROD_CALLBACK_URL_PAYTM + orderId);
            TransactionManager transactionManager = new TransactionManager(paytmOrder, this);
            transactionManager.setAppInvokeEnabled(false);
            transactionManager.startTransaction(this, 101);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        binding.progressBar.setVisibility(View.GONE);
        binding.btnHome.setVisibility(View.VISIBLE);
        if (requestCode == CFPaymentService.REQ_CODE && data != null) {
            try {
                Bundle bundle = data.getExtras();
                StringBuilder builder = new StringBuilder();
                if (bundle != null) {
                    String orderId = bundle.getString("orderId");
                    builder.append("ORDER ID").append(" : #").append(orderId).append("\n");

                    String status = bundle.getString("txStatus");

                    if (status.toLowerCase().contains("success")) {
                        JSONObject jsonObject = new JSONObject();

                        String orderAmount = bundle.getString("orderAmount");
                        String referenceId = bundle.getString("referenceId");
                        String paymentMode = bundle.getString("paymentMode");
                        String txMsg = bundle.getString("txMsg");
                        String txTime = bundle.getString("txTime");
                        String paymentMethod = bundle.getString("paymentMethod");

                        jsonObject.put("orderId", orderId);
                        jsonObject.put("orderAmount", orderAmount);
                        jsonObject.put("referenceId", referenceId);
                        jsonObject.put("txStatus", status);
                        jsonObject.put("paymentMode", paymentMode);
                        jsonObject.put("txMsg", txMsg);
                        jsonObject.put("txTime", txTime);
                        jsonObject.put("paymentMethod", paymentMethod != null ? paymentMethod : "");

                        savePaymentResponse(jsonObject);

                        binding.imgStatus.setImageResource(R.drawable.checked);
                        builder.append("PAYMENT STATUS").append(" : ").append("SUCCESS");
                    } else {
                        binding.imgStatus.setImageResource(R.drawable.cancel);
                        builder.append("PAYMENT STATUS").append(" : ").append("FAILED");
                    }

                    binding.imgStatus.setVisibility(View.VISIBLE);


                    binding.txtMessage.setText(builder.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == 101 && data != null) {
            binding.txtMessage.setText(data.getStringExtra("nativeSdkForMerchantMessage") + "\n" + data.getStringExtra("response"));
        } else if (requestCode == 1 && data != null) {
            if (resultCode == RESULT_OK) {
                // The transaction was successful
                String response = data.getStringExtra("response");
                Log.d("response : ", "PhonePe Transaction Success. Response: " + response);

                // Parse the response and handle it as needed
                // Example: You might want to check the status and other details
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String status = jsonResponse.optString("status");
                    // Handle the status and other details accordingly
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // The transaction was not successful
                Log.d("response : ", "PhonePe Transaction Failed.");
            }
        }
    }

    private void savePaymentResponse(JSONObject jsonObject) {
        mViewModel.savePaymentResponse(this, jsonObject);
    }

    private Map<String, String> getInputParams() {

        if (amount != null && appid != null) {
            Map<String, String> params = new HashMap<>();

            params.put(PARAM_APP_ID, appid);
            params.put(PARAM_ORDER_ID, orderId);
            params.put(PARAM_ORDER_AMOUNT, amount);
            params.put(PARAM_CUSTOMER_NAME, customerName);
            params.put(PARAM_CUSTOMER_PHONE, customerPhone);
            params.put(PARAM_CUSTOMER_EMAIL, customerEmail);
            params.put(PARAM_ORDER_CURRENCY, "INR");
            params.put(PARAM_NOTIFY_URL, "https://test.gocashfree.com/notify");
            return params;
        } else {
            return null;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                mViewModel.getmDataManger().setPaymentOpen(false);
                startActivity(new Intent(this, DashboardActivity.class));
                finishAffinity();
                break;
        }
    }

    @Override
    public void onTransactionResponse(@Nullable Bundle bundle) {
        if (bundle != null) {
            binding.txtMessage.setText(bundle.toString());
        }
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void networkNotAvailable() {
        binding.progressBar.setVisibility(View.GONE);
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.txtMessage.setText("PAYMENT ERROR : network not available");
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorProceed(String s) {
        binding.progressBar.setVisibility(View.GONE);
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s);
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        binding.progressBar.setVisibility(View.GONE);
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s);
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void someUIErrorOccurred(String s) {
        binding.progressBar.setVisibility(View.GONE);
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s);
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        binding.progressBar.setVisibility(View.GONE);
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s + " " + s1);
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressedCancelTransaction() {
        binding.progressBar.setVisibility(View.GONE);
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.txtMessage.setText("PAYMENT ERROR : User cancelled");
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        binding.progressBar.setVisibility(View.GONE);
        binding.imgStatus.setImageResource(R.drawable.cancel);
        binding.imgStatus.setVisibility(View.VISIBLE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s + " " + bundle.toString());
        binding.btnHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
    }
}