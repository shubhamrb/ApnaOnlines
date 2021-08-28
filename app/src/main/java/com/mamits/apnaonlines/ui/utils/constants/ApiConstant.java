package com.mamits.apnaonlines.ui.utils.constants;

import com.mamits.apnaonlines.BuildConfig;

public class ApiConstant {
    public static final String LOGIN_END_POINT = BuildConfig.BASE_URL+ "api/vendorsignin";
    public static final String HOME_DATA_END_POINT = BuildConfig.BASE_URL+ "api/auth/vendordashboard";
    public static final String ORDERS_END_POINT = BuildConfig.BASE_URL+ "api/auth/vendorOrder";
    public static final String PAYMENTS_END_POINT = BuildConfig.BASE_URL+ "api/auth/transactionHistory";
}
