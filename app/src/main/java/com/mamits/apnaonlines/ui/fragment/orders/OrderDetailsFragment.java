package com.mamits.apnaonlines.ui.fragment.orders;

import android.content.Context;
import android.view.View;

import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.databinding.FragmentOrderDetailsBinding;
import com.mamits.apnaonlines.databinding.FragmentOrdersBinding;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.OrderDetailNavigator;
import com.mamits.apnaonlines.ui.navigator.fragment.OrdersNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.OrderDetailViewModel;
import com.mamits.apnaonlines.viewmodel.fragment.OrdersViewModel;

import javax.inject.Inject;


public class OrderDetailsFragment extends BaseFragment<FragmentOrderDetailsBinding, OrderDetailViewModel> implements OrderDetailNavigator, View.OnClickListener {

    private String TAG = "OrderDetailsFragment";
    private FragmentOrderDetailsBinding binding;

    @Inject
    OrderDetailViewModel mViewModel;
    private Context mContext;
    @Override
    public void onClick(View v) {

    }

    @Override
    public OrderDetailViewModel getMyViewModel() {
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


        }
    }

    @Override
    public int getBindingVariable() {
        return BR.orderDetailView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_details;
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

    }
}