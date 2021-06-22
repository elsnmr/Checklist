package com.elsa.checklist.di;

import android.app.Activity;

import com.elsa.checklist.ui.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@InstallIn(ActivityComponent.class)
@Module
public class ActivityModule {
    @Provides
    MainActivity provideMainActivity(Activity activity) {
        return (MainActivity) activity;
    }
}
