package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 5/4/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.DSEDailyReportLocationBean;
import com.example.user.lmsautovista.Model.LeadReportBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class LeadReportPresenter implements IPresenter.ILeadReportPresenter{

    IView.ILeadReportView iview;

    public LeadReportPresenter(IView.ILeadReportView view){
        this.iview = view;
    }

    @Override
    public void getLeadLocation(Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location", SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_SESSION, ""));

            String url = Constants.BASE_URL + Constants.DSE_DAILY_REPORT_LOCATION;
            GSONRequest<DSEDailyReportLocationBean> dashboardGsonRequest = new GSONRequest<DSEDailyReportLocationBean>(
                    Request.Method.POST, url,
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
    public void getSearchVialOcationList(String location_id, String fromdate, String todate, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("fromdate", fromdate);
            map.put("todate", todate);
            map.put("location_id", location_id);

            String url = Constants.BASE_URL + Constants.LEAD_REPORT;
            GSONRequest<LeadReportBean> dashboardGsonRequest = new GSONRequest<LeadReportBean>(
                    Request.Method.POST, url,
                    LeadReportBean.class, map,
                    new com.android.volley.Response.Listener<LeadReportBean>() {
                        @Override
                        public void onResponse(LeadReportBean res) {
                            try {
                                if (res.getTotal_count().size() > 0) {
                                    iview.showLeadReportView(res);
                                } else {
                                    iview.showLeadReportView(res);
                                }

                                if (res.getTotal_feedback().size() > 0) {
                                    iview.showLeadReportView(res);
                                } else {
                                    iview.showLeadReportView(res);
                                }

                            }catch(Exception e){
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

