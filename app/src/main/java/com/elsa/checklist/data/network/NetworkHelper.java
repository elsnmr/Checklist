package com.elsa.checklist.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import dagger.hilt.android.qualifiers.ApplicationContext;

/*
 * kelas yang menyimpan fungsi yang diperlukan untuk keperluan network
 * */
public class NetworkHelper {

    public static String token = "";

    public static boolean isNetworkConnected(@ApplicationContext Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                return false;
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                return false;
            }
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true;
            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true;
            } else return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            } else return networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET;
        }
    }
}
