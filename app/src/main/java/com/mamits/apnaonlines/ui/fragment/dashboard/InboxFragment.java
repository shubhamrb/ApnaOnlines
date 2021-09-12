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
import com.mamits.apnaonlines.data.model.orders.OrdersDataModel;
import com.mamits.apnaonlines.databinding.FragmentInboxBinding;
import com.mamits.apnaonlines.ui.adapter.InboxAdapter;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.OrdersNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.OrdersViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class InboxFragment extends BaseFragment<FragmentInboxBinding, OrdersViewModel> implements OrdersNavigator, View.OnClickListener {
    private String TAG = "InboxFragment";
    private FragmentInboxBinding binding;

    @Inject
    OrdersViewModel mViewModel;
    private Context mContext;
    private Gson mGson;
    private List<OrdersDataModel> inboxList;
    private InboxAdapter inboxAdapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    public OrdersViewModel getMyViewModel() {
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
            binding.progressBar.setVisibility(View.VISIBLE);
            setUpInbox();
        }
    }

    private void setUpInbox() {
        inboxList = new ArrayList<>();
        mGson = new Gson();
        binding.recyclerChats.setLayoutManager(new LinearLayoutManager(getActivity()));
        inboxAdapter = new InboxAdapter(getActivity(), mViewModel);
        binding.recyclerChats.setAdapter(inboxAdapter);

        loadInbox();
    }

    private void loadInbox() {
        mViewModel.fetchOrders((Activity) mContext, 0);
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
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBars() {

    }

    @Override
    public void checkValidation(int errorCode, String message) {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void throwable(Throwable throwable) {
        binding.progressBar.setVisibility(View.GONE);
        throwable.printStackTrace();
    }

    @Override
    public void onSuccessOrders(JsonObject jsonObject) {
        binding.progressBar.setVisibility(View.GONE);
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                mGson = new Gson();
                Type orderDataList = new TypeToken<List<OrdersDataModel>>() {
                }.getType();
                inboxList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), orderDataList);

                if (inboxList != null && inboxList.size() > 0) {
                    inboxAdapter.setList(inboxList);
                    binding.recyclerChats.setVisibility(View.VISIBLE);
                } else {
                    binding.recyclerChats.setVisibility(View.GONE);
                }

            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

}