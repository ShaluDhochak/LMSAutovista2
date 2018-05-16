package com.example.user.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.DSEDailyReportLocationBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shalu Dhochak on 4/30/2018.
 */

public class SubmitMessagePresenter implements IPresenter.ISubmitMessagepresenter{

    IView.ISubmitMessageView iview;

    public SubmitMessagePresenter(IView.ISubmitMessageView view){
        this.iview = view;

    }

    @Override
    public void getLocation( Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location", SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_SESSION, ""));

            String url = Constants.BASE_URL + Constants.DSE_DAILY_REPORT_LOCATION;
            GSONRequest<DSEDailyReportLocationBean> dashboardGsonRequest = new GSONRequest<DSEDailyReportLocationBean>(
                    Request.Method.POST,
                    url,
                    DSEDailyReportLocationBean.class, map,
                    new com.android.volley.Response.Listener<DSEDailyReportLocationBean>() {
                        @Override
                        public void onResponse(DSEDailyReportLocationBean res) {

                            if (res.getDaliy_dse_tracker_location().size()>0)
                            {
                                iview.showLocation(res);

                            } else {
                                iview.showLocation(res);

                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
