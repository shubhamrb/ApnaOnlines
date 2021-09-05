package com.mamits.apnaonlines.ui.fragment.dashboard.services;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mamits.apnaonlines.BR;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.services.CategoryDataModel;
import com.mamits.apnaonlines.data.model.services.ProductDataModel;
import com.mamits.apnaonlines.data.model.services.ServiceDataModel;
import com.mamits.apnaonlines.data.model.services.SubCategoryDataModel;
import com.mamits.apnaonlines.databinding.FragmentAddServiceBinding;
import com.mamits.apnaonlines.ui.activity.DashboardActivity;
import com.mamits.apnaonlines.ui.base.BaseFragment;
import com.mamits.apnaonlines.ui.navigator.fragment.AddServiceNavigator;
import com.mamits.apnaonlines.viewmodel.fragment.AddServiceViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class AddServiceFragment extends BaseFragment<FragmentAddServiceBinding, AddServiceViewModel>
        implements AddServiceNavigator, View.OnClickListener {

    private String TAG = "AddServiceFragment";
    private FragmentAddServiceBinding binding;

    @Inject
    AddServiceViewModel mViewModel;
    private Context mContext;
    private List<CategoryDataModel> catSubCategoryList;
    private List<ProductDataModel> productsList;
    private Gson mGson;
    private ArrayAdapter catAdapter, subAdapter, servicesAdapter;
    private String selected_cat = "Select Category", selected_sub_cat = "Select Subcategory", selected_service = "Select Services";
    private JSONObject finalObject = null;
    private ServiceDataModel model;
    private boolean isUpdate = false;
    private String inventoryId = "";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                String price = binding.etPrice.getText().toString();

                if (selected_cat.equals("Select Category")
                        || selected_sub_cat.equals("Select Subcategory")
                        || selected_service.equals("Select Services")) {
                    Toast.makeText(mContext, "Please enter all required details (*).", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (price.trim().length() == 0) {
                    price = "";
                }
                try {
                    finalObject.put("price", price);

                    if (isUpdate) {
                        finalObject.put("inventoryid", inventoryId);
                        updateService(finalObject);
                    } else {
                        addService(finalObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void updateService(JSONObject finalObject) {
        mViewModel.updateService((Activity) mContext, finalObject);
    }

    private void addService(JSONObject productDataModel) {
        mViewModel.addService((Activity) mContext, productDataModel);
    }

    @Override
    public AddServiceViewModel getMyViewModel() {
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
                model = (ServiceDataModel) bundle.getSerializable("model");
                inventoryId = String.valueOf(model.getId());
                isUpdate = true;
                binding.btnSubmit.setText("Update");
            } else {
                binding.btnSubmit.setText("Submit");
            }
            loadCategorySubCategory();
            binding.btnSubmit.setOnClickListener(this);
        }
    }

    private void loadCategorySubCategory() {
        catSubCategoryList = new ArrayList<>();
        mViewModel.fetchCategorySubCategory((Activity) mContext);
    }

    @Override
    public int getBindingVariable() {
        return BR.addServiceView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_service;
    }

    @Override
    public void showProgressBars() {
        showsLoading();
    }

    @Override
    public void checkInternetConnection(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBars() {
        hidesLoading();
    }

    @Override
    public void checkValidation(int errorCode, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void throwable(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onSuccessServiceAdded(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("success").getAsBoolean()) {
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                Navigation.findNavController(((DashboardActivity) mContext).findViewById(R.id.nav_host_fragment)).navigateUp();
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccessCatSubCat(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("success").getAsBoolean()) {
                mGson = new Gson();
                Type category = new TypeToken<List<CategoryDataModel>>() {
                }.getType();
                catSubCategoryList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), category);
                fillSpinners();
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccessProducts(JsonObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject.get("success").getAsBoolean()) {
                mGson = new Gson();
                Type products = new TypeToken<List<ProductDataModel>>() {
                }.getType();
                productsList = mGson.fromJson(jsonObject.get("data").getAsJsonArray().toString(), products);

                fillServicesSpinner();
            } else {
                int messageId = jsonObject.get("messageId").getAsInt();
                String message = jsonObject.get("message").getAsString();
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fillSpinners() {
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Select Category");
        List<String> subCategoryList = new ArrayList<>();
        subCategoryList.add("Select Subcategory");

        for (CategoryDataModel model : catSubCategoryList) {
            categoryList.add(model.getName());
        }

        /*category*/
        catAdapter = new ArrayAdapter(mContext, R.layout.spinner_layout, categoryList);
        binding.spinnerCategory.setAdapter(catAdapter);

        /*sub category*/
        subAdapter = new ArrayAdapter(mContext, R.layout.spinner_layout, subCategoryList);
        binding.spinnerSubcategory.setAdapter(subAdapter);

        if (model != null) {
            binding.spinnerCategory.setSelection(categoryList.indexOf(model.getCategory().getName()));

            String catname = model.getCategory().getName();
            int index = categoryList.indexOf(catname);
            for (SubCategoryDataModel model : catSubCategoryList.get(index - 1).getSubcategory()) {
                subCategoryList.add(model.getName());
            }

            binding.etPrice.setText(model.getPrice());
            binding.rlPrice.setVisibility(View.VISIBLE);
        }


        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    subCategoryList.clear();
                    subCategoryList.add("Select Subcategory");
                    if (i > 0) {
                        selected_cat = String.valueOf(catSubCategoryList.get(i - 1).getId());

                        for (SubCategoryDataModel model : catSubCategoryList.get(i - 1).getSubcategory()) {
                            subCategoryList.add(model.getName());
                        }

                    } else {
                        selected_cat = "Select Category";
                    }
                    if (model != null) {
                        binding.spinnerSubcategory.setSelection(subCategoryList.indexOf(model.getSubcategory().getName()));
                    } else {
                        binding.spinnerSubcategory.setSelection(0);
                    }

                    if (!selected_cat.equals("Select Category") && !selected_sub_cat.equals("Select Subcategory")) {
                        fetchProducts();
                        binding.rlPrice.setVisibility(View.VISIBLE);
                    } else {
                        if (servicesAdapter != null) {
                            servicesAdapter.clear();
                            servicesAdapter.notifyDataSetChanged();
                            finalObject = null;
                        }
                        binding.rlPrice.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        binding.spinnerSubcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i > 0) {
                        selected_sub_cat = String.valueOf(catSubCategoryList.get(binding.spinnerCategory.getSelectedItemPosition() - 1).getSubcategory().get(i - 1).getId());
                    } else {
                        selected_sub_cat = "Select Subcategory";
                    }

                    if (!selected_cat.equals("Select Category") && !selected_sub_cat.equals("Select Subcategory")) {
                        fetchProducts();
                        binding.rlPrice.setVisibility(View.VISIBLE);
                    } else {
                        if (servicesAdapter != null) {
                            servicesAdapter.clear();
                            servicesAdapter.notifyDataSetChanged();
                            finalObject = null;
                        }
                        binding.rlPrice.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void fetchProducts() {
        mViewModel.fetchProducts((Activity) mContext, selected_cat, selected_sub_cat);
    }

    private void fillServicesSpinner() {
        List<String> servicesList = new ArrayList<>();
        /*services*/
        for (ProductDataModel model : productsList) {
            servicesList.add(model.getName());
        }
        servicesAdapter = new ArrayAdapter(mContext, R.layout.spinner_layout, servicesList);
        binding.spinnerServices.setAdapter(servicesAdapter);
        if (model != null) {
            binding.spinnerServices.setSelection(servicesList.indexOf(model.getName()));
        }
        binding.spinnerServices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    selected_service = String.valueOf(productsList.get(i).getId());

                    finalObject = new JSONObject();
                    finalObject.put("category", productsList.get(i).getCategory_id());
                    finalObject.put("subcategory", String.valueOf(productsList.get(i).getSub_category_id()));
                    finalObject.put("product", String.valueOf(productsList.get(i).getId()));
                    finalObject.put("product_type", String.valueOf(productsList.get(i).getProduct_type()));

                    List<ProductDataModel.VariationDataModel> list = productsList.get(i).getVariation();

                    Gson gson = new GsonBuilder().serializeNulls().create();
                    String listString = gson.toJson(
                            list,
                            new TypeToken<List<ProductDataModel>>() {
                            }.getType());

                    JSONArray jsonArray = new JSONArray(listString);
                    finalObject.put("variation", jsonArray);
                    model = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}