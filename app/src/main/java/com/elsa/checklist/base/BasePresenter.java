package com.elsa.checklist.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends BaseContract.View, D extends BaseContract.DataSource> implements BaseContract.Presenter<V, D> {

    V view;
    D dataSource;
    CompositeDisposable compositeDisposable;

    @Inject
    public BasePresenter(D dataSource, CompositeDisposable compositeDisposable) {
        this.dataSource = dataSource;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        compositeDisposable.clear();
        view = null;
    }

    @Override
    public V view() {
        return view;
    }

    @Override
    public D dataSource() {
        return dataSource;
    }

    @Override
    public CompositeDisposable compositeDisposable() {
        return compositeDisposable;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void checkViewAttached() throws ViewNotAttachedException {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    @Override
    public void setUserAsLoggedOut() {
        dataSource.setAccessToken(null);
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call Presenter.onAttach(View) before" +
                    " requesting data to the Presenter");
        }
    }
}
