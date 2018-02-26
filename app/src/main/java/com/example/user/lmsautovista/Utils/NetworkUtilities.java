package com.example.user.lmsautovista.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by User on 2/17/2018.
 */

public class NetworkUtilities {
    public static final int MY_SOCKET_TIMEOUT_MS = 1 * 60 * 1000; // m1.30min

    /**
     * Check Internet connection.
     */
    public static boolean isInternet(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
