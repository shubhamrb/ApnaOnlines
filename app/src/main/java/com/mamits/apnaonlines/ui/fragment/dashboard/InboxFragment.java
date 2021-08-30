package com.mamits.apnaonlines.ui.fragment.dashboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.databinding.FragmentInboxBinding;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.InboxNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.InboxViewModel;

import javax.inject.Inject;

public class InboxFragment extends BaseFragment<FragmentInboxBinding, InboxViewModel> implements InboxNavigator, View.OnClickListener {
    private String TAG = "InboxFragment";
    private FragmentInboxBinding binding;

    @Inject
    InboxViewModel mViewModel;
    private Context mContext;
    private Gson mGson;

    @Override
    public void onClick(View v) {

    }

    @Override
    public InboxViewModel getMyViewModel() {
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
            mViewModel.fetchData((Activity) mContext);
        }
    }

    @Override
    public int getBindingVariable() {
        return BR.inboxView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inbox;
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

    @Override
    public void onSuccessHomeData(JsonObject jsonObject) {

    }
}