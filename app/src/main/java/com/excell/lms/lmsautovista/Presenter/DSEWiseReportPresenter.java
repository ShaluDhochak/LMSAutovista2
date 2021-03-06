package com.excell.lms.lmsautovista.Presenter;

/*
Created by Shalu Dhochak on 5/4/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.DSEReportBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class DSEWiseReportPresenter  implements IPresenter.IDsewiseReportPresenter{

    IView.IDsewiseReportView iview;

    public DSEWiseReportPresenter(IView.IDsewiseReportView view){
        this.iview = view;
    }

    @Override
    public void getLocation( Context context) {
        try {
          //  view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_ID, ""));
            map.put("user_id",SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));


            String url = Constants.BASE_URL + Constants.DASHBOARD_LOCATION_SPINNER;
            GSONRequest<LocationDashboardBean> dashboardGsonRequest = new GSONRequest<LocationDashboardBean>(
                    Request.Method.POST,
                    url,
                    LocationDashboardBean.class, map,
                    new com.android.volley.Response.Listener<LocationDashboardBean>() {
                        @Override
                        public void onResponse(LocationDashboardBean res) {

                            if (res.getSelect_location().size()>0)
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
       /* try {
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
        */
    }

    @Override
    public void getSearchViaContactNoList(String location_id, String fromdate, String todate, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("fromdate", fromdate);
            map.put("todate", todate);
            map.put("location_id", location_id);

            String url = Constants.BASE_URL + Constants.DSEWISE_REPORT;
            GSONRequest<DSEReportBean> dashboardGsonRequest = new GSONRequest<DSEReportBean>(
                    Request.Method.POST, url,
                    DSEReportBean.class, map,
                    new com.android.volley.Response.Listener<DSEReportBean>() {
                        @Override
                        public void onResponse(DSEReportBean res) {
                            try {
                                if (res.getDsewise_count().size() > 0) {
                                    iview.showDseWiseListView(res);
                                } else {
                                    iview.showDseWiseListView(res);
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


