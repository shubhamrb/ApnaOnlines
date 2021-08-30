package com.mamits.apnaonlines.ui.fragment.orders;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.orders.OrderDetailDataModel;
import com.mamits.apnaonlines.data.model.orders.OrdersDataModel;
import com.mamits.apnaonlines.databinding.FragmentOrderDetailsBinding;
import com.mamits.apnaonlines.ui.adapter.FormDataAdapter;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.customviews.CustomInputEditText;
import com.mamits.apnaonlines.ui.customviews.CustomTextView;
import com.mamits.apnaonlines.ui.navigator.fragment.OrderDetailNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.OrderDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class OrderDetailsFragment extends BaseFragment<FragmentOrderDetailsBinding, OrderDetailViewModel>
        implements OrderDetailNavigator, View.OnClickListener, FormDataAdapter.downloadListener {

    private String TAG = "OrderDetailsFragment";
    private FragmentOrderDetailsBinding binding;

    @Inject
    OrderDetailViewModel mViewModel;
    private Context mContext;
    private OrdersDataModel model;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private BottomSheetDialog acceptOrderDialog;
    private String tType = "";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accept:
                openAcceptBottomDialog();
                break;
            case R.id.btn_reject:
                update("Reject", null, null, null);
                break;
        }
    }

    private void openAcceptBottomDialog() {
        if (acceptOrderDialog == null) {

            acceptOrderDialog = new BottomSheetDialog(mContext);
            acceptOrderDialog.setContentView(R.layout.accept_order_bottomsheet);
            acceptOrderDialog.setCanceledOnTouchOutside(false);

            CustomTextView txt_label_time = acceptOrderDialog.findViewById(R.id.txt_label_time);
            CustomInputEditText et_time = acceptOrderDialog.findViewById(R.id.et_time);
            CustomInputEditText et_amount = acceptOrderDialog.findViewById(R.id.et_amount);
            RelativeLayout btn_submit = acceptOrderDialog.findViewById(R.id.btn_submit);
            AppCompatSpinner spin = acceptOrderDialog.findViewById(R.id.spinner);
            et_amount.setText(model.getOrder_amount());
            ArrayList<String> list = new ArrayList<>();
            list.add("Minutes");
            list.add("Hour");

            ArrayAdapter adapter = new ArrayAdapter(mContext, R.layout.spinner_layout, list);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    tType = adapterView.getItemAtPosition(i).toString();
                    txt_label_time.setText("Time in " + tType.toLowerCase());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    tType = "";
                }
            });

            btn_submit.setOnClickListener(v -> {
                String time = et_time.getText().toString();
                if (time.trim().length() == 0) {
                    Toast.makeText(mContext, "Please enter the time.", Toast.LENGTH_SHORT).show();
                    return;
                }
                update("Accept", time, tType.toLowerCase(), model.getOrder_amount());
            });
            acceptOrderDialog.setOnDismissListener(dialog -> {
                acceptOrderDialog = null;
            });
            acceptOrderDialog.show();
        }
    }

    private void update(String status, String time, String type, String order_amount) {
        mViewModel.updateOrderStatus((Activity) mContext, status, model.getId(), time, type, order_amount);
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
            model = (OrdersDataModel) getArguments().getSerializable("order");
            setData();
            permission();

            binding.btnAccept.setOnClickListener(this);
            binding.btnReject.setOnClickListener(this);
        }
    }

    private void permission() {
        requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        Toast.makeText(mContext, "Permission granted, Please download", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(mContext, "This permission is required to download the file.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setData() {
        binding.txtDate.setText(model.getOrderdatetime());
        binding.txtOrderId.setText(String.format("#%s", model.getOrder_id()));
        binding.txtUsername.setText(model.getUsers().getName());
        binding.txtServiceCategory.setText(model.getProducts().getName());
        switch (model.getStatus()) {
            case 1:
                binding.txtStatus.setText("Pending");
                binding.txtH1.setText("Pending Order");
                binding.txtStatus.setTextColor(mContext.getResources().getColor(R.color.yellow_ffb302));
                binding.bottom.setVisibility(View.VISIBLE);
                break;
            case 2:
                binding.txtStatus.setText("Accept");
                binding.txtH1.setText("Accepted Order");
                binding.txtStatus.setTextColor(mContext.getResources().getColor(R.color.green_39ae00));
                binding.bottom.setVisibility(View.GONE);
                binding.chatBottom.setVisibility(View.VISIBLE);
                break;
            case 3:
                binding.txtStatus.setText("Reject");
                binding.txtH1.setText("Rejected Order");
                binding.txtStatus.setTextColor(mContext.getResources().getColor(R.color.red_ff2502));
                binding.bottom.setVisibility(View.GONE);
                break;
            case 4:
                binding.txtStatus.setText("Cancel");
                binding.txtH1.setText("Canceled Order");
                binding.txtStatus.setTextColor(mContext.getResources().getColor(R.color.red_ff2502));
                binding.bottom.setVisibility(View.GONE);
                break;
            case 5:
                binding.txtStatus.setText("Complete");
                binding.txtH1.setText("Completed Order");
                binding.txtStatus.setTextColor(mContext.getResources().getColor(R.color.green_39ae00));
                binding.bottom.setVisibility(View.GONE);
                binding.chatBottom.setVisibility(View.VISIBLE);
                break;
        }
        binding.txtPrice.setText("₹ " + model.getOrder_amount());

        Glide.with(mContext).load(model.getProducts().getProduct_image()).into(binding.img);

        /*user details*/
        binding.txtName.setText(model.getUsers().getName());
        if (model.getUsers().getPhone() != null)
            binding.txtMobile.setText(model.getUsers().getPhone());
        if (model.getUsers().getEmail() != null)
            binding.txtEmail.setText(model.getUsers().getEmail());

        setFormData();
    }

    private void setFormData() {
        List<OrderDetailDataModel> formList = model.getOrder_detail();
        binding.recyclerForm.setLayoutManager(new LinearLayoutManager(getActivity()));
        FormDataAdapter formDataAdapter = new FormDataAdapter(getActivity(), formList, mViewModel, this);
        binding.recyclerForm.setAdapter(formDataAdapter);
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
    public void onSuccessOrderStatusUpdated(JsonObject jsonObject, String status) {
        if (jsonObject != null) {
            if (jsonObject.get("status").getAsBoolean()) {
                String message = jsonObject.get("message").getAsString();
                if (status.equalsIgnoreCase("accept")) {
                    if (acceptOrderDialog != null && acceptOrderDialog.isShowing()) {
                        acceptOrderDialog.dismiss();
                    }
                    binding.chatBottom.setVisibility(View.VISIBLE);
                }
                binding.bottom.setVisibility(View.GONE);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void downloadFile(String url) {
        if (ContextCompat.checkSelfPermission(
                mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            String title = URLUtil.guessFileName(url, null, null);
            request.setTitle(title);
            request.setDescription("Downloading...");
            String cookie = CookieManager.getInstance().getCookie(url);
            request.addRequestHeader("cookie", cookie);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);

            DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            Toast.makeText(mContext, "Downloading...", Toast.LENGTH_LONG).show();
        } else {
            requestPermissionLauncher.launch(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }
}