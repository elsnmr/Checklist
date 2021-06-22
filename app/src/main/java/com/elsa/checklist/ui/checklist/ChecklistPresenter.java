package com.elsa.checklist.ui.checklist;

import com.elsa.checklist.base.BasePresenter;
import com.elsa.checklist.utilities.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ChecklistPresenter extends BasePresenter<ChecklistContract.View, ChecklistContract.DataSource> implements ChecklistContract.Presenter {
    @Inject
    public ChecklistPresenter(ChecklistContract.DataSource dataSource, CompositeDisposable compositeDisposable) {
        super(dataSource, compositeDisposable);
    }

    @Override
    public void getAll() {
        view().showLoading();
        compositeDisposable().add(dataSource().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    view().hideLoading();
                    if (responseBody != null) {
                        String res = responseBody.string();
                        if (!res.isEmpty()) {
                            JSONObject jsonObject = new JSONObject(res);
                            JSONArray data = jsonObject.getJSONArray("data");
                            ArrayList<Map<String, Object>> list = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                list.add(util.jsonObjectToMapObj(data.getJSONObject(i)));
                            }
                            view().result(list);
                        }
                    }
                }, throwable -> {

                    if (!isViewAttached()) {
                        return;
                    }

                    view().hideLoading();
                    view().onError("Failure");
                })
        );
    }
}
