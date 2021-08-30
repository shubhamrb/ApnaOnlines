package com.mamits.apnaonlines.ui.fragment.dashboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.payments.PaymentsDataModel;
import com.mamits.apnaonlines.data.model.payments.TransactionsDataModel;
import com.mamits.apnaonlines.databinding.FragmentPaymentBinding;
import com.mamits.apnaonlines.ui.adapter.PaymentsAdapter;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.PaymentsNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.PaymentsViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PaymentFragment extends BaseFragment<FragmentPaymentBinding, PaymentsViewModel> implements PaymentsNavigator, View.OnClickListener {
    private String TAG = "PaymentFragment";
    private FragmentPaymentBinding binding;

    @Inject
    PaymentsViewModel mViewModel;
    private Context mContext;
    private List<PaymentsDataModel> paymentsList;
    private Gson mGson;
    private PaymentsAdapter paymentsAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public PaymentsViewModel getMyViewModel() {
        return mViewModel;
    }

    @Override
    protected void initView(View view, boolean isRefresh) {
        binding = getViewDataBinding();
        mViewModel = getMyViewModel();
        mViewModel.setNavigator(this);
        if (getActivity() != null) {
            mContext = getActivity();
        } else if (getBaseActivity() != null) {
            mContext = getBaseActivity();
        } else if (view.getContext() != null) {
            mContext = view.getContext();
        }
        if (isRefresh) {
            setUpPayments();
        }
    }

    private void setUpPayments() {
        paymentsList = new ArrayList<>();
        mGson = new Gson();
        binding.recyclerPayments.setLayoutManager(new LinearLayoutManager(getActivity()));
        paymentsAdapter = new PaymentsAdapter(getActivity(), mViewModel);
        binding.recyclerPayments.setAdapter(paymentsAdapter);

        loadPayments();
    }

    private void loadPayments() {

        mViewModel.fetchPayments((Activity) mContext);
        binding.recyclerPayments.setVisibility(View.VISIBLE);
    }

    @Override
    public int getBindingVariable() {
        return BR.paymentsView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_payment;
    }

    @Override
    public void showProgressBars() {
        showsLoading();
    }

    @Override
    public void checkInternetConnection(String message) {

    }

    @Override
    public void hideProgressBars() {
        hidesLoading();
    }

    @Override
    public void checkValidation(int errorCode, String message) {

    }

    @Override
    public void throwable(Throwable throwable) {

    }

    @Override
    public void onSuccessPayments(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                mGson = new Gson();
                Type payments = new TypeToken<List<PaymentsDataModel>>() {
                }.getType();
                paymentsList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), payments);

                if (paymentsList != null && paymentsList.size() > 0) {
                    paymentsAdapter.setList(paymentsList);
                } else {
                    binding.recyclerPayments.setVisibility(View.GONE);
                }
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

            }
        }
    }
}