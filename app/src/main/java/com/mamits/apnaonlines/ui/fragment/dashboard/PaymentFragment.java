package com.mamits.apnaonlines.ui.fragment.dashboard;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
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
    private List<TransactionsDataModel> paymentsList;
    private Gson mGson;
    private PaymentsAdapter paymentsAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_filter:
                openFilterDialog(v);
                break;
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
            binding.txtFilter.setOnClickListener(this);
            setUpPayments();
        }
    }

    private void setUpPayments() {
        paymentsList = new ArrayList<>();
        mGson = new Gson();
        binding.recyclerPayments.setLayoutManager(new LinearLayoutManager(getActivity()));
        paymentsAdapter = new PaymentsAdapter(getActivity(), mViewModel);
        binding.recyclerPayments.setAdapter(paymentsAdapter);

        loadPayments("all", null);
    }

    private void loadPayments(String pType, PopupWindow popupWindow) {
        try {
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
                mViewModel.getmNavigator().get().showProgressBars();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (pType) {
            case "all":
                binding.txtH1.setText("All Payments");
                binding.txtFilter.setText("All");
                break;
            case "vendor":
                binding.txtH1.setText("Pay To Vendor");
                binding.txtFilter.setText("Pay To Vendor");
                break;
            case "offer":
                binding.txtH1.setText("Offer");
                binding.txtFilter.setText("Offer");
                break;
            case "extra":
                binding.txtH1.setText("Extra Amount");
                binding.txtFilter.setText("Extra Amount");
                break;

        }

        mViewModel.fetchPayments((Activity) mContext, pType);
        binding.recyclerPayments.setVisibility(View.VISIBLE);
    }

    private void openFilterDialog(View v) {
        LayoutInflater layoutInflater
                = (LayoutInflater) mContext
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.payments_filter, null);

        RelativeLayout rl_all = popupView.findViewById(R.id.rl_all);
        RelativeLayout rl_vendor = popupView.findViewById(R.id.rl_vendor);
        RelativeLayout rl_offer = popupView.findViewById(R.id.rl_offer);
        RelativeLayout rl_extra = popupView.findViewById(R.id.rl_extra);


        PopupWindow popupWindow = new PopupWindow(
                popupView,
                mContext.getResources().getDimensionPixelOffset(R.dimen._200sdp),
                ViewGroup.LayoutParams.WRAP_CONTENT);

        rl_all.setOnClickListener(v1 -> loadPayments("all", popupWindow));
        rl_vendor.setOnClickListener(v1 -> loadPayments("vendor", popupWindow));
        rl_offer.setOnClickListener(v1 -> loadPayments("offer", popupWindow));
        rl_extra.setOnClickListener(v1 -> loadPayments("extra", popupWindow));

        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAsDropDown(v, 0, 0);
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
                Type orderDataList = new TypeToken<List<TransactionsDataModel>>() {
                }.getType();
                paymentsList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), orderDataList);

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