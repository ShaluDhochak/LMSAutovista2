package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 5/30/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.DailyProductivityReportBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class DailyProductivityReportPresenter implements IPresenter.IDailyProductivityReportPresnter{

    IView.DailyProductivityReportPresnter view;

    public DailyProductivityReportPresenter(IView.DailyProductivityReportPresnter view){
        this.view = view;
    }

    @Override
    public void getDailyProductivityReportList(String user_type, String location_id, Context context) {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("location_name", location_id);
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));
            map.put("user_type",user_type);

            String url = Constants.BASE_URL + Constants.DAILY_PRODUCTIVITY_REPORT;
            GSONRequest<DailyProductivityReportBean> dashboardGsonRequest = new GSONRequest<DailyProductivityReportBean>(
                    Request.Method.POST,
                    url,
                    DailyProductivityReportBean.class, map,
                    new com.android.volley.Response.Listener<DailyProductivityReportBean>() {
                        @Override
                        public void onResponse(DailyProductivityReportBean res) {
                            if (res.getDaily_productivity_report().size()>0)
                            {
                                try {
                                    view.showDashboardCount(res);
                                } catch (Exception e) {
                                }
                            } else {
                                view.showDashboardCount(res);
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
    public void getLocationSpinnerList(final Context context) {
        try {

            Map<String, String> map = new HashMap<>();
          /*  map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
              map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
              map.put("location_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_ID, ""));
          */

            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location_id",SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_ID, ""));
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
                                view.showLocationDashboard(res);
                            } else {
                                view.showLocationDashboard(res);
                                Toast.makeText(context, "No record Found", Toast.LENGTH_SHORT).show();
                                 //view.("Unable to ");
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
