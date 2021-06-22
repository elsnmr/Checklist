package com.elsa.checklist.base;

import com.elsa.checklist.data.network.api.APIHeader;
import com.elsa.checklist.data.network.api.APIService;
import com.elsa.checklist.data.prefs.PreferencesHelper;

import javax.inject.Inject;

public class BaseDataSourceImpl implements BaseContract.DataSource {
    APIService apiHelper;
    PreferencesHelper preferencesHelper;
    APIHeader apiHeader;

    @Inject
    public BaseDataSourceImpl(APIService apiHelper, PreferencesHelper preferencesHelper, APIHeader apiHeader) {
        this.apiHelper = apiHelper;
        this.preferencesHelper = preferencesHelper;
        this.apiHeader = apiHeader;
    }

    @Override
    public APIService apiHelper() {
        return apiHelper;
    }

    @Override
    public PreferencesHelper preferencesHelper() {
        return preferencesHelper;
    }

    @Override
    public APIHeader apiHeader() {
        return apiHeader;
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        preferencesHelper().setAccessToken(accessToken);

        // Setting Fixed Header
        apiHeader().getProtectedApiHeader()
                .setToken(accessToken);
    }

    @Override
    public void updateUserInfo(String accessToken) {
        preferencesHelper().setAccessToken(accessToken);

        updateApiHeader(accessToken);
    }

    @Override
    public void updateApiHeader(String accessToken) {
        apiHeader().getProtectedApiHeader().setToken(accessToken);
    }
}
