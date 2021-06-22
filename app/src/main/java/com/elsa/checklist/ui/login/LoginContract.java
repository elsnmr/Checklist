package com.elsa.checklist.ui.login;

import com.elsa.checklist.base.BaseContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface LoginContract {
    interface DataSource extends BaseContract.DataSource {
        Observable<ResponseBody> login(String username, String password);
    }

    interface View extends BaseContract.View {
        void openMainActivity();
    }

    interface Presenter extends BaseContract.Presenter<LoginContract.View, LoginContract.DataSource> {
        void login(String username, String password);
    }
}
