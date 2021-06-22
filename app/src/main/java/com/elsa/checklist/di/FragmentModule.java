package com.elsa.checklist.di;

import androidx.fragment.app.Fragment;

import com.elsa.checklist.ui.checklist.ChecklistFragment;
import com.elsa.checklist.ui.login.LoginFragment;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import io.reactivex.disposables.CompositeDisposable;

@InstallIn(FragmentComponent.class)
@Module
public class FragmentModule {
    @Provides
    LoginFragment provideLoginFragment(Fragment fragment) {
        return (LoginFragment) fragment;
    }

    @Provides
    ChecklistFragment provideChecklistFragment(Fragment fragment) {
        return (ChecklistFragment) fragment;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
