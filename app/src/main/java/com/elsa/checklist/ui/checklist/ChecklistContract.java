package com.elsa.checklist.ui.checklist;

import com.elsa.checklist.base.BaseContract;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface ChecklistContract {
    interface DataSource extends BaseContract.DataSource {
        Observable<ResponseBody> getAll();
    }

    interface View extends BaseContract.View {
        void result(ArrayList<Map<String, Object>> list);
    }

    interface Presenter extends BaseContract.Presenter<ChecklistContract.View, ChecklistContract.DataSource> {
        void getAll();
    }
}
