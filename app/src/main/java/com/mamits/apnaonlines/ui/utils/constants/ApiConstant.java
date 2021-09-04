package com.mamits.apnaonlines.ui.utils.constants;

import com.mamits.apnaonlines.BuildConfig;

public class ApiConstant {
    public static final String LOGIN_END_POINT = BuildConfig.BASE_URL + "api/vendorsignin";
    public static final String HOME_DATA_END_POINT = BuildConfig.BASE_URL + "api/auth/vendordashboard";
    public static final String ORDERS_END_POINT = BuildConfig.BASE_URL + "api/auth/vendorOrder";
    public static final String PAYMENTS_END_POINT = BuildConfig.BASE_URL + "api/auth/paymenthistory";
    public static final String TRANSACTIONS_END_POINT = BuildConfig.BASE_URL + "api/auth/transactionHistory";
    public static final String HELP_END_POINT = BuildConfig.BASE_URL + "api/auth/helpandsupport";
    public static final String SEND_OTP_END_POINT = BuildConfig.BASE_URL + "api/sendOtp";
    public static final String VERIFY_OTP_END_POINT = BuildConfig.BASE_URL + "api/forgotpassword";
    public static final String UPDATE_PIN_END_POINT = BuildConfig.BASE_URL + "api/updatepassword";
    public static final String UPDATE_ORDER_STATUS_END_POINT = BuildConfig.BASE_URL + "api/auth/updateOrderStatus";
    public static final String FETCH_MESSAGES_END_POINT = BuildConfig.BASE_URL + "api/auth/getLatestMessage";
    public static final String SEND_MESSAGE_END_POINT = BuildConfig.BASE_URL + "api/auth/saveChatMessage";
    public static final String FETCH_COUPONS_END_POINT = BuildConfig.BASE_URL + "api/auth/getCoupon";
    public static final String CREATE_COUPON_END_POINT = BuildConfig.BASE_URL + "api/auth/addCoupon";
    public static final String FETCH_SERVICES_END_POINT = BuildConfig.BASE_URL + "api/auth/getInventoryList";
    public static final String DELETE_COUPON_END_POINT = BuildConfig.BASE_URL + "api/auth/deleteCoupon";
    public static final String DELETE_SERVICE_END_POINT = BuildConfig.BASE_URL + "api/auth/deleteStock";
    public static final String FETCH_CAT_SUB_CATEGORY_END_POINT = BuildConfig.BASE_URL + "api/auth/getVendorCategory";
}
