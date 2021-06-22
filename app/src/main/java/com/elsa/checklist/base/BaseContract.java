package com.elsa.checklist.base;


import androidx.annotation.StringRes;

import com.elsa.checklist.data.network.api.APIHeader;
import com.elsa.checklist.data.network.api.APIService;
import com.elsa.checklist.data.prefs.PreferencesHelper;

import io.reactivex.disposables.CompositeDisposable;

/*
 * interface untuk base
 * */
public interface BaseContract {
    /*
     * interface untuk base dataSource
     * */
    interface DataSource {
        APIService apiHelper();

        PreferencesHelper preferencesHelper();

        APIHeader apiHeader();

        void setUserAsLoggedOut();

        void setAccessToken(String accessToken);

        void updateUserInfo(String accessToken);

        void updateApiHeader(String accessToken);
    }

    /*
     * interface untuk base view
     * */
    interface View {
        void showLoading();

        void hideLoading();

        void openActivityOnTokenExpire();

        void onError(@StringRes int resId);

        void onError(String message);

        void showMessage(String message);

        void showMessage(@StringRes int resId);

        boolean isNetworkConnected();

        void hideKeyboard();
    }

    /*
     * interface untuk base presenter
     * */
    interface Presenter<V extends View, D extends DataSource> {
        void onAttach(V view);

        void onDetach();

        V view();

        D dataSource();

        CompositeDisposable compositeDisposable();

        boolean isViewAttached();

        void checkViewAttached() throws BasePresenter.ViewNotAttachedException;

        void setUserAsLoggedOut();
    }
}
