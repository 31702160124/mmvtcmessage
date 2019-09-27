package com.qq.a1843318972.mmvtcmessage.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class netWork {
    public static boolean isNewworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            for (NetworkInfo networkInfo1 : networkInfo) {
                int netid = networkInfo1.getType();
                if (netid == 1) {
                    if (networkInfo1.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    } else {
                        if (netid == 0) {
                            if (networkInfo1.getState() == NetworkInfo.State.CONNECTED) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
