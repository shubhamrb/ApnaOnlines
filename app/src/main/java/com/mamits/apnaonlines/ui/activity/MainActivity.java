package com.mamits.apnaonlines.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.login.LoginDataModel;
import com.mamits.apnaonlines.databinding.ActivityMainBinding;
import com.mamits.apnaonlines.ui.base.BaseActivity;
import com.mamits.apnaonlines.ui.navigator.activity.MainActivityNavigator;
import com.mamits.apnaonlines.ui.utils.constants.AppConstant;
import com.mamits.apnaonlines.viewmodel.activity.MainActivityViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel>
        implements MainActivityNavigator, View.OnClickListener {

    String TAG = "MainActivity";
    @Inject
    MainActivityViewModel mViewModel;
    ActivityMainBinding binding;
    private BottomSheetDialog changePinDialog, forgotPinDialog, loginOtpDialog;
    private Gson mGson;

    @Override
    public int getBindingVariable() {
        return BR.mainView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding = getViewDataBinding();
        mViewModel = getMyViewModel();
        mViewModel.setNavigator(this);

        if (mViewModel.getmDataManger().getCurrentUserId()!=-1){
            Intent dashboardIntent = new Intent(this, DashboardActivity.class);
            startActivity(dashboardIntent);
            finishAffinity();
        }

        binding.btnLogin.setOnClickListener(this);
        binding.btnReg.setOnClickListener(this);
        binding.btnForgot.setOnClickListener(this);
    }

    @Override
    protected MainActivityViewModel getMyViewModel() {
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

    }

    @Override
    public void throwable(Throwable it) {
        it.printStackTrace();
    }

    @Override
    public void checkInternetConnection(String message) {

    }

    @Override
    public void onSuccessUserLogin(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                mGson = new Gson();
                LoginDataModel model = mGson.fromJson(jsonObject.get("data").getAsJsonObject().toString(), LoginDataModel.class);
                mViewModel.getmDataManger().setAccessToken(model.getToken());
                mViewModel.getmDataManger().setCurrentUserId(model.getUser().getId());
                mViewModel.getmDataManger().setUsername(model.getUser().getName());
                mViewModel.getmDataManger().settUserNumber(model.getUser().getPhone());

                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
                finishAffinity();
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                String number = binding.etNumber.getText().toString();
                String pin = binding.etPin.getText().toString();
                if (number.length() == 0 || pin.length() == 0) {
                    Toast.makeText(this, "Invalid id or password", Toast.LENGTH_SHORT).show();
                    return;
                }

                doLogin(number, pin);
                break;
            case R.id.btn_forgot:
                showForgotOtpBottomDialog();
                break;
            case R.id.btn_verify:
                if (forgotPinDialog != null && forgotPinDialog.isShowing()) {
                    forgotPinDialog.dismiss();
                }
                showChangePinBottomDialog();
                break;
            case R.id.btn_login_verify:
                if (loginOtpDialog != null && loginOtpDialog.isShowing()) {
                    loginOtpDialog.dismiss();
                }
                Intent dashboardIntent = new Intent(this, DashboardActivity.class);
                startActivity(dashboardIntent);
                finishAffinity();
                break;
        }
    }

    private void doLogin(String number, String pin) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                return;
            }
            // Get new FCM registration token
            if (task.isSuccessful()) {
                String device_id = task.getResult();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("mobile", number);
                    jsonObject.put("pin", pin);
                    jsonObject.put("api_key", AppConstant.API_KEY);
                    jsonObject.put("device_type", AppConstant.DEVICE_TYPE);
                    jsonObject.put("device_token", device_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mViewModel.userLogin(this, jsonObject.toString());

            }
        });
    }

    private void showChangePinBottomDialog() {
        changePinDialog = new BottomSheetDialog(this);
        changePinDialog.setContentView(R.layout.change_pin_bottomsheet);
        changePinDialog.show();
    }

    private void showForgotOtpBottomDialog() {
        forgotPinDialog = new BottomSheetDialog(this);
        forgotPinDialog.setContentView(R.layout.forgot_otp_bottomsheet);
        RelativeLayout btn_verify = forgotPinDialog.findViewById(R.id.btn_verify);

        if (btn_verify != null) {
            btn_verify.setOnClickListener(this);
        }
        forgotPinDialog.show();
    }

    private void showLoginOtpBottomDialog() {
        loginOtpDialog = new BottomSheetDialog(this);
        loginOtpDialog.setContentView(R.layout.login_otp_bottomsheet);

        RelativeLayout btn_login_verify = loginOtpDialog.findViewById(R.id.btn_login_verify);
        if (btn_login_verify != null) {
            btn_login_verify.setOnClickListener(this);
        }
        loginOtpDialog.show();
    }
}