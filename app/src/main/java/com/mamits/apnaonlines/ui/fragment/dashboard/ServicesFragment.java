package com.mamits.apnaonlines.ui.fragment.dashboard;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.services.CategoryDataModel;
import com.mamits.apnaonlines.data.model.services.ServiceDataModel;
import com.mamits.apnaonlines.data.model.services.SubCategoryDataModel;
import com.mamits.apnaonlines.databinding.FragmentServicesBinding;
import com.mamits.apnaonlines.ui.adapter.ServicesAdapter;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.ServicesNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.ServicesViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ServicesFragment extends BaseFragment<FragmentServicesBinding, ServicesViewModel> implements ServicesNavigator, View.OnClickListener, ServicesAdapter.deleteListener {

    private String TAG = "ServicesFragment";
    private FragmentServicesBinding binding;

    @Inject
    ServicesViewModel mViewModel;
    private Context mContext;
    private Gson mGson;
    private List<ServiceDataModel> servicesList;
    private ServicesAdapter servicesAdapter;
    private BottomSheetDialog filterDialog;
    private List<CategoryDataModel> catSubCategoryList;
    private ArrayAdapter catAdapter, subAdapter;
    private String selected_cat = "all", selected_sub_cat = "all";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_filter:
                openFilterBottomDialog();
                break;
        }
    }

    @Override
    public ServicesViewModel getMyViewModel() {
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
            binding.btnFilter.setOnClickListener(this);
            loadCategorySubCategory();
            setUpServices();
        }
    }

    private void loadCategorySubCategory() {
        catSubCategoryList = new ArrayList<>();
        mViewModel.fetchCategorySubCategory((Activity) mContext);
    }

    private void openFilterBottomDialog() {
        if (filterDialog == null) {
            filterDialog = new BottomSheetDialog(mContext);
            filterDialog.setContentView(R.layout.service_filter_bottomsheet);
            filterDialog.setCanceledOnTouchOutside(false);

            RelativeLayout btn_submit = filterDialog.findViewById(R.id.btn_submit);
            AppCompatSpinner spinner_category = filterDialog.findViewById(R.id.spinner_category);
            AppCompatSpinner spinner_subcategory = filterDialog.findViewById(R.id.spinner_subcategory);

            List<String> categoryList = new ArrayList<>();
            categoryList.add("All");
            List<String> subCategoryList = new ArrayList<>();
            subCategoryList.add("All");

            for (CategoryDataModel model : catSubCategoryList) {
                categoryList.add(model.getName());
            }


            catAdapter = new ArrayAdapter(mContext, R.layout.spinner_layout, categoryList);
            spinner_category.setAdapter(catAdapter);
            spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        subCategoryList.clear();
                        subCategoryList.add("All");
                        if (i > 0) {
                            selected_cat = String.valueOf(catSubCategoryList.get(i - 1).getId());

                            for (SubCategoryDataModel model : catSubCategoryList.get(i - 1).getSubcategory()) {
                                subCategoryList.add(model.getName());
                            }
                        } else {
                            selected_cat = "all";
                        }
                        spinner_subcategory.setSelection(0);
                        subAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            subAdapter = new ArrayAdapter(mContext, R.layout.spinner_layout, subCategoryList);
            spinner_subcategory.setAdapter(subAdapter);
            spinner_subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        if (i > 0) {
                            selected_sub_cat = String.valueOf(catSubCategoryList.get(spinner_category.getSelectedItemPosition() - 1).getSubcategory().get(i - 1).getId());

                        } else {
                            selected_sub_cat = "all";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            btn_submit.setOnClickListener(v -> {
                Log.e("Cat ", selected_cat);
                Log.e("Sub Cat ", selected_sub_cat);
                if (filterDialog != null) {
                    filterDialog.dismiss();
                }
                loadServices(selected_cat, selected_sub_cat);
            });
            filterDialog.setOnDismissListener(dialog -> {
                filterDialog = null;
            });
            filterDialog.show();
        }
    }

    private void setUpServices() {
        servicesList = new ArrayList<>();
        mGson = new Gson();
        binding.recyclerServices.setLayoutManager(new LinearLayoutManager(getActivity()));
        servicesAdapter = new ServicesAdapter(getActivity(), mViewModel, this);
        binding.recyclerServices.setAdapter(servicesAdapter);

        loadServices(selected_cat, selected_sub_cat);
    }

    private void loadServices(String cat_id, String subcat_id) {
        mViewModel.fetchServices((Activity) mContext, cat_id, subcat_id);
        binding.recyclerServices.setVisibility(View.VISIBLE);
    }

    @Override
    public int getBindingVariable() {
        return BR.servicesView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_services;
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
    public void onSuccessCatSubCat(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("success").getAsBoolean()) {
                mGson = new Gson();
                Type category = new TypeToken<List<CategoryDataModel>>() {
                }.getType();
                catSubCategoryList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), category);

            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccessServices(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("success").getAsBoolean()) {
                mGson = new Gson();
                Type services = new TypeToken<List<ServiceDataModel>>() {
                }.getType();
                try {
                    JsonArray dataArray = jsonObject.get("data").getAsJsonArray();
                    servicesList = mGson.fromJson(dataArray.toString(), services);

                    if (servicesList != null && servicesList.size() > 0) {
                        servicesAdapter.setList(servicesList);
                    } else {
                        binding.recyclerServices.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    binding.recyclerServices.setVisibility(View.GONE);
                }


            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccessDeleteService(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("success").getAsBoolean()) {
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                loadServices(selected_cat, selected_sub_cat);
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDeleteService(String inventoryId) {
        new AlertDialog.Builder(mContext)
                .setTitle("Delete Service")
                .setMessage("Are you sure you want to delete this service ?")

                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // Continue with delete operation
                    mViewModel.deleteService((Activity) mContext, inventoryId);
                })

                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}