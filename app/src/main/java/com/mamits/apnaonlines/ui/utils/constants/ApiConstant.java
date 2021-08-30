package com.mamits.apnaonlines.ui.utils.constants;

import com.mamits.apnaonlines.BuildConfig;

public class ApiConstant {
    public static final String LOGIN_END_POINT = BuildConfig.BASE_URL+ "api/vendorsignin";
    public static final String HOME_DATA_END_POINT = BuildConfig.BASE_URL+ "api/auth/vendordashboard";
    public static final String ORDERS_END_POINT = BuildConfig.BASE_URL+ "api/auth/vendorOrder";
    public static final String PAYMENTS_END_POINT = BuildConfig.BASE_URL+ "api/auth/paymenthistory";
    public static final String TRANSACTIONS_END_POINT = BuildConfig.BASE_URL+ "api/auth/transactionHistory";
    public static final String HELP_END_POINT = BuildConfig.BASE_URL+ "api/auth/helpandsupport";
    public static final String SEND_OTP_END_POINT = BuildConfig.BASE_URL+ "api/sendOtp";
    public static final String VERIFY_OTP_END_POINT = BuildConfig.BASE_URL+ "api/forgotpassword";
    public static final String UPDATE_PIN_END_POINT = BuildConfig.BASE_URL+ "api/updatepassword";
    public static final String UPDATE_ORDER_STATUS_END_POINT = BuildConfig.BASE_URL+ "api/auth/updateOrderStatus";
}
