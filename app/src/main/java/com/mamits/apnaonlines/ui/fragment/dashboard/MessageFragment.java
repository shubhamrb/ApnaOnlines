package com.mamits.apnaonlines.ui.fragment.dashboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.chat.MessageDataModel;
import com.mamits.apnaonlines.databinding.FragmentMessageBinding;
import com.mamits.apnaonlines.ui.adapter.MessengerAdapter;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.MessageNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.MessageViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessageFragment extends BaseFragment<FragmentMessageBinding, MessageViewModel> implements MessageNavigator, View.OnClickListener {
    private String TAG = "MessageFragment";
    private FragmentMessageBinding binding;

    @Inject
    MessageViewModel mViewModel;
    private Context mContext;
    private Gson mGson;
    private int user_id, order_id;
    private List<MessageDataModel> messagesList;
    private MessengerAdapter messageAdapter;
    private boolean isScrollToBottom = true;
    private CountDownTimer timer;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String message = binding.etMessage.getText().toString();
                if (message.trim().length() == 0) {
                    Toast.makeText(mContext, "Can't send empty message.", Toast.LENGTH_SHORT).show();
                }
                binding.etMessage.setText("");
                sendMessage(message);
                break;
        }
    }

    private void sendMessage(String message) {
        mViewModel.sendMessage((Activity) mContext, user_id, order_id, message);
    }

    @Override
    public MessageViewModel getMyViewModel() {
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
            Bundle bundle = getArguments();
            if (bundle != null) {
                user_id = bundle.getInt("userid", -1);
                order_id = bundle.getInt("orderid", -1);
            }
            setUpMessages();

            binding.btnSend.setOnClickListener(this);
        }
    }

    private void setUpMessages() {
        messagesList = new ArrayList<>();
        mGson = new Gson();
        binding.recyclerMessages.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageAdapter = new MessengerAdapter(getActivity(), mViewModel);
        binding.recyclerMessages.setAdapter(messageAdapter);

        loadMessages();
    }

    private void loadMessages() {
        mViewModel.fetchMessages((Activity) mContext, user_id, order_id);
    }

    @Override
    public int getBindingVariable() {
        return BR.messageView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
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
    public void onSuccessMessages(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                mGson = new Gson();
                Type messages = new TypeToken<List<MessageDataModel>>() {
                }.getType();
                messagesList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), messages);

                if (messagesList != null && messagesList.size() > 0) {
                    messageAdapter.setList(messagesList);
                    if (isScrollToBottom) {
                        binding.recyclerMessages.scrollToPosition(messagesList.size() - 1);
                        isScrollToBottom = false;
                    }
                    if (timer != null) {
                        timer.cancel();
                    }
                    /*start timer*/
                    startTimer();
                } else {
                    binding.recyclerMessages.setVisibility(View.GONE);
                }

            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccessMessageSend(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {

                if (timer != null) {
                    timer.cancel();
                }
                isScrollToBottom = true;
                loadMessages();
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startTimer() {
        timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                loadMessages();
            }

        }.start();
    }

    @Override
    public void onPause() {
        if (timer != null) {
            timer.cancel();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (timer != null) {
            timer.start();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }
}