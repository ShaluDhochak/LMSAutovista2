package com.example.user.lmsautovista.Utils;

import android.content.Context;

import com.android.volley.RequestQueue;

/**
 * Created by User on 2/20/2018.
 */

public class Utilities {

    public static RequestQueue getRequestQueue(Context context) {
        return VolleySingleton.getInstance(context).getRequestQueue();
    }
}
