package com.example.user.lmsautovista.Presenter;

/*
 Created by Shalu Dhochak on 4/30/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.DSEDailyReportLocationBean;
import com.example.user.lmsautovista.Model.DSEDailyReportViewBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class DSEDailyReportViewPresenter implements IPresenter.IDSEDailyReportViewPresenter{

    IView.DSEDailyView iview;

    public DSEDailyReportViewPresenter(IView.DSEDailyView view){
        this.iview = view;
    }

    @Override
    public void getLocation(String process,Context context) {
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

    @Override
    public void getSearchViaContactNoList(String status, String location_id, String fromdate, final Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("location_id", location_id);
            map.put("status", status);
            map.put("fromdate", fromdate);
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location", SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_SESSION, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));

            String url = Constants.BASE_URL + Constants.DSE_DAILY_REPORT_VIEW;
            GSONRequest<DSEDailyReportViewBean> dashboardGsonRequest = new GSONRequest<DSEDailyReportViewBean>(
                    Request.Method.POST,
                    url,
                    DSEDailyReportViewBean.class, map,
                    new com.android.volley.Response.Listener<DSEDailyReportViewBean>() {
                        @Override
                        public void onResponse(DSEDailyReportViewBean res) {
                            if (res.getDaily_tracker_show_data().size()>0)
                            {
                                iview.ShowDseDailyReportView(res);
                            } else {
                                Toast.makeText(context, "No Record Found", Toast.LENGTH_SHORT).show();
                                iview.ShowDseDailyReportView(res);
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
