package com.mamits.apnaonlines.ui.fragment.dashboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.home.HomeDataModel;
import com.mamits.apnaonlines.databinding.FragmentHomeBinding;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.HomeNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.HomeViewModel;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator, View.OnClickListener {
    private String TAG = "HomeFragment";
    private FragmentHomeBinding binding;

    @Inject
    HomeViewModel mViewModel;
    private Context mContext;
    private Gson mGson;

    @Override
    public void onClick(View v) {

    }

    @Override
    public HomeViewModel getMyViewModel() {
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
        return BR.HomeView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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
        throwable.printStackTrace();
    }

    @Override
    public void onSuccessHomeData(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                mGson = new Gson();
                HomeDataModel model = mGson.fromJson(jsonObject.get("data").getAsJsonObject().toString(), HomeDataModel.class);
                binding.txtTotalOrder.setText(model.getTotalorder() + "");
                binding.txtOrderAccept.setText(model.getTotalaccept() + "");
                binding.txtOrderReject.setText(model.getTotalreject() + "");
                binding.txtPendingOrder.setText(model.getTotalpending() + "");
                binding.txtOrderComplete.setText(model.getTotalcomplete() + "");

                binding.txtPayNow.setText(model.getTotalcancel() + "");
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

            }
        }
    }
}