package com.elsa.checklist.di;

import com.elsa.checklist.BuildConfig;
import com.elsa.checklist.data.network.NetworkHelper;
import com.elsa.checklist.data.network.api.APIHeader;
import com.elsa.checklist.data.network.api.APIService;
import com.elsa.checklist.data.prefs.AppPreferencesHelper;
import com.elsa.checklist.data.prefs.PreferencesHelper;
import com.elsa.checklist.utilities.AppConstants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * provide singleton
 * */
@Module
@InstallIn(SingletonComponent.class)
public final class NetworkModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        }
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().client(okHttpClient).baseUrl("http://18.139.50.74:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build();
    }

    @Provides
    @Singleton
    APIService provideAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @TokenInfo
    String provideToken() {
        return NetworkHelper.token;
    }

    @Provides
    @Singleton
    APIHeader.ProtectedApiHeader provideProtectedApiHeader(
            @TokenInfo String token) {
        return new APIHeader.ProtectedApiHeader(
                token);
    }
}
