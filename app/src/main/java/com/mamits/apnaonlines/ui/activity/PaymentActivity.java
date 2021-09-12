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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentActivityViewModel>
        implements PaymentActivityNavigator, View.OnClickListener, PaytmPaymentTransactionCallback {

    String TAG = "PaymentActivity";
    @Inject
    PaymentActivityViewModel mViewModel;
    ActivityPaymentBinding binding;
    private String orderId, customerName, customerPhone, customerEmail, amount, appid, m_id;
    private static final String STAGE_CALLBACK_URL_PAYTM = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";
    private static final String PROD_CALLBACK_URL_PAYTM = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=";

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

        amount = getIntent().getStringExtra("amount");
        customerName = mViewModel.getmDataManger().getUsername();
        customerPhone = mViewModel.getmDataManger().getUserNumber();
        customerEmail = mViewModel.getmDataManger().getUserEmail();
        if (getIntent().hasExtra("appid")) {
            appid = getIntent().getStringExtra("appid");
        } else if (getIntent().hasExtra("m_id")) {
            m_id = getIntent().getStringExtra("m_id");
        }
        startPayment();
    }

    private void startPayment() {
        int n = 10000 + new Random().nextInt(90000);
        int m = (int) Math.pow(10, n - 1);
        orderId = String.valueOf(m + new Random().nextInt(9 * m)).replace("-", "");
        if (appid != null) {
            getCfsToken();
        } else if (m_id != null) {
            getPaytmToken();
        }

    }

    private void getPaytmToken() {
        mViewModel.fetchPaytmToken(this, orderId, amount,customerPhone,customerEmail);
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
    }

    @Override
    public void throwable(Throwable it) {
        it.printStackTrace();
    }

    @Override
    public void checkInternetConnection(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
        if (requestCode == CFPaymentService.REQ_CODE && data != null) {

            Bundle bundle = data.getExtras();
            StringBuilder builder = new StringBuilder();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        builder.append(key).append(" : ").append(bundle.getString(key)).append("\n");
                    }
                }
                binding.txtMessage.setText(builder.toString());
            }
        } else if (requestCode == 101 && data != null) {
            binding.txtMessage.setText(data.getStringExtra("nativeSdkForMerchantMessage") + "\n" + data.getStringExtra("response"));
        }
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

        }
    }

    @Override
    public void onTransactionResponse(@Nullable Bundle bundle) {
        if (bundle != null) {
            binding.txtMessage.setText(bundle.toString());
        }
    }

    @Override
    public void networkNotAvailable() {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtMessage.setText("PAYMENT ERROR : network not available");
    }

    @Override
    public void onErrorProceed(String s) {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s);
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s);
    }

    @Override
    public void someUIErrorOccurred(String s) {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s);
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s + " " + s1);
    }

    @Override
    public void onBackPressedCancelTransaction() {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtMessage.setText("PAYMENT ERROR : User cancelled");
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        binding.progressBar.setVisibility(View.GONE);
        binding.txtMessage.setText("PAYMENT ERROR : " + s + " " + bundle.toString());
    }
}