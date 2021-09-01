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
import com.mamits.apnaonlines.data.model.coupons.CouponsDataModel;
import com.mamits.apnaonlines.databinding.FragmentCouponBinding;
import com.mamits.apnaonlines.ui.adapter.CouponsAdapter;
import com.mamits.apnaonlines.ui.adapter.OrdersAdapter;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.CouponNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.CouponViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class CouponFragment extends BaseFragment<FragmentCouponBinding, CouponViewModel> implements CouponNavigator, View.OnClickListener {
    private String TAG = "CouponFragment";
    private FragmentCouponBinding binding;

    @Inject
    CouponViewModel mViewModel;
    private Context mContext;
    private Gson mGson;
    private List<CouponsDataModel> couponsList;
    private CouponsAdapter couponsAdapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    public CouponViewModel getMyViewModel() {
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
            setUpCoupons();
        }
    }

    private void setUpCoupons() {
        couponsList = new ArrayList<>();
        mGson = new Gson();
        binding.recyclerCoupons.setLayoutManager(new LinearLayoutManager(getActivity()));
        couponsAdapter = new CouponsAdapter(getActivity(), mViewModel);
        binding.recyclerCoupons.setAdapter(couponsAdapter);

        loadCoupons();
    }

    private void loadCoupons() {
        mViewModel.fetchCoupons((Activity) mContext);
    }

    @Override
    public int getBindingVariable() {
        return BR.couponsView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    public void showProgressBars() {

    }

    @Override
    public void checkInternetConnection(String message) {

    }

    @Override
    public void hideProgressBars() {

    }

    @Override
    public void checkValidation(int errorCode, String message) {

    }

    @Override
    public void throwable(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onSuccessFetchCoupons(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                mGson = new Gson();
                Type couponsDataList = new TypeToken<List<CouponsDataModel>>() {
                }.getType();
                couponsList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), couponsDataList);

                if (couponsList != null && couponsList.size() > 0) {
                    couponsAdapter.setList(couponsList);
                } else {
                    binding.recyclerCoupons.setVisibility(View.GONE);
                }

            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}