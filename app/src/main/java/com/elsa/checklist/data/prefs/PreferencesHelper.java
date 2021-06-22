package com.elsa.checklist.data.prefs;


public interface PreferencesHelper {

    int getCurrentUserId();

    void setCurrentUserId(Integer userId);

    String getAccessToken();

    void setAccessToken(String accessToken);

}
