package com.elsa.checklist.ui.checklist;

import com.elsa.checklist.base.BaseDataSourceImpl;
import com.elsa.checklist.data.network.api.APIHeader;
import com.elsa.checklist.data.network.api.APIService;
import com.elsa.checklist.data.prefs.PreferencesHelper;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ChecklistDataSourceImpl extends BaseDataSourceImpl implements ChecklistContract.DataSource {
    @Inject
    public ChecklistDataSourceImpl(APIService apiHelper, PreferencesHelper preferencesHelper, APIHeader apiHeader) {
        super(apiHelper, preferencesHelper, apiHeader);
    }

    @Override
    public Observable<ResponseBody> getAll() {
        return apiHelper().checklist(apiHeader().getProtectedApiHeader().getToken());
    }
}
