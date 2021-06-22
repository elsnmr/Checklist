package com.elsa.checklist.ui.login;

import com.elsa.checklist.base.BaseDataSourceImpl;
import com.elsa.checklist.data.network.api.APIHeader;
import com.elsa.checklist.data.network.api.APIService;
import com.elsa.checklist.data.prefs.PreferencesHelper;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LoginDataSourceImpl extends BaseDataSourceImpl implements LoginContract.DataSource {
    @Inject
    public LoginDataSourceImpl(APIService helper, PreferencesHelper preferencesHelper, APIHeader apiHeader) {
        super(helper, preferencesHelper, apiHeader);
    }

    @Override
    public Observable<ResponseBody> login(String username, String password) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        return apiHelper().login(data);
    }
}
