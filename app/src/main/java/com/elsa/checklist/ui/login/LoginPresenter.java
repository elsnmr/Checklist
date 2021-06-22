package com.elsa.checklist.ui.login;

import com.elsa.checklist.R;
import com.elsa.checklist.base.BasePresenter;
import com.elsa.checklist.utilities.util;

import org.json.JSONObject;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.DataSource> implements LoginContract.Presenter {
    @Inject
    public LoginPresenter(LoginContract.DataSource dataSource, CompositeDisposable compositeDisposable) {
        super(dataSource, compositeDisposable);
    }

    @Override
    public void login(String username, String password) {
        if (username == null || username.isEmpty()) {
            view().onError(R.string.empty_username);
            return;
        }
        if (password == null || password.isEmpty()) {
            view().onError(R.string.empty_password);
            return;
        }
        view().showLoading();
        compositeDisposable().add(dataSource().login(username, password)
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
                            JSONObject data = jsonObject.getJSONObject("data");
                            Map<String, Object> map = util.jsonObjectToMapObj(data);
                            String token = util.string(map.get("token"));
                            if (token == null) {
                                view().onError(R.string.some_error);
                            } else {
                                dataSource().updateUserInfo(token);
                                view().openMainActivity();
                            }
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
