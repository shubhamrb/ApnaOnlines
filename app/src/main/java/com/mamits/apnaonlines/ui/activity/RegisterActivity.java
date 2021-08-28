package com.mamits.apnaonlines.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.databinding.ActivityRegisterBinding;
import com.mamits.apnaonlines.ui.base.BaseActivity;
import com.mamits.apnaonlines.ui.navigator.activity.RegisterActivityNavigator;
import com.mamits.apnaonlines.viewmodel.activity.RegisterActivityViewModel;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterActivityViewModel>
        implements RegisterActivityNavigator , View.OnClickListener {

    String TAG = "RegisterActivity";
    @Inject
    RegisterActivityViewModel mViewModel;
    ActivityRegisterBinding binding;

    @Override
    public int getBindingVariable() {
        return BR.registerView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding = getViewDataBinding();
        mViewModel = getMyViewModel();
        mViewModel.setNavigator(this);

        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    protected RegisterActivityViewModel getMyViewModel() {
        return mViewModel;
    }


    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }


    @Override
    public void checkValidation(int type, String message) {

    }

    @Override
    public void throwable(Throwable it) {

    }

    @Override
    public void checkInternetConnection(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}