package com.mamits.apnaonlines.data.local.pref;


public interface IPreferenceHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    void setCurrentUserId(int userId);

    int getCurrentUserId();

    String getUsername();

    void setUsername(String username);

    String getUserNumber();

    void settUserNumber(String number);

    void clearAllPreference();
}
