package com.elsa.checklist.di;

import com.elsa.checklist.ui.checklist.ChecklistContract;
import com.elsa.checklist.ui.checklist.ChecklistDataSourceImpl;
import com.elsa.checklist.ui.checklist.ChecklistFragment;
import com.elsa.checklist.ui.checklist.ChecklistPresenter;
import com.elsa.checklist.ui.login.LoginContract;
import com.elsa.checklist.ui.login.LoginDataSourceImpl;
import com.elsa.checklist.ui.login.LoginFragment;
import com.elsa.checklist.ui.login.LoginPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;

@InstallIn(FragmentComponent.class)
@Module
abstract class FragmentContractModule {
    @Binds
    abstract LoginContract.DataSource bindLoginDataSource(LoginDataSourceImpl impl);

    @Binds
    abstract LoginContract.View bindLoginFragment(LoginFragment fragment);

    @Binds
    abstract LoginContract.Presenter bindLoginPresenter(LoginPresenter impl);

    @Binds
    abstract ChecklistContract.DataSource bindChecklistDataSource(ChecklistDataSourceImpl impl);

    @Binds
    abstract ChecklistContract.View bindChecklistFragment(ChecklistFragment fragment);

    @Binds
    abstract ChecklistContract.Presenter bindChecklistPresenter(ChecklistPresenter impl);
}
