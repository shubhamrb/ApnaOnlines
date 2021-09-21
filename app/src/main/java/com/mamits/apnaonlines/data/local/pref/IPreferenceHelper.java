package com.mamits.apnaonlines.data.local.pref;


import com.mamits.apnaonlines.data.model.login.LoginDataModel;

public interface IPreferenceHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getUserData();

    void setUserData(LoginDataModel loginDataModel);

    void setCurrentUserId(int userId);

    int getCurrentUserId();

    String getUsername();

    void setUsername(String username);

    String getUserNumber();

    void settUserNumber(String number);

    String getUserEmail();

    void settUserEmail(String email);

    String getNotificationType();

    void setNotificationType(String type);

    boolean isPaymentOpen();

    void setPaymentOpen(boolean isOpen);

    void clearAllPreference();
}
