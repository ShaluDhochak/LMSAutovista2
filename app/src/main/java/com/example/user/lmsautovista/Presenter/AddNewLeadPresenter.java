package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/2/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.AssignToBean;
import com.example.user.lmsautovista.Model.LeadSourceBean;
import com.example.user.lmsautovista.Model.LocationDashboardBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class AddNewLeadPresenter implements IPresenter.IAddNewLeadOresenter{

    IView.AddNewLeadView view;
    public AddNewLeadPresenter(IView.AddNewLeadView iview){
        this.view = iview;
    }

    @Override
    public void getAssignTo(String process, String location, Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", process);
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location_id", location);

            String url = Constants.BASE_URL + Constants.ASSIGN_TO_SPINNER;
            GSONRequest<AssignToBean> dashboardGsonRequest = new GSONRequest<AssignToBean>(
                    Request.Method.POST,
                    url,
                    AssignToBean.class, map,
                    new com.android.volley.Response.Listener<AssignToBean>() {
                        @Override
                        public void onResponse(AssignToBean res) {
                            view.dismissProgressDialog();
                            if (res.getSelect_user().size()>0)
                            {
                                view.showAssignTo(res);
                                view.dismissProgressDialog();
                            } else {
                                view.showAssignTo(res);
                                view.dismissProgressDialog();
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            view.dismissProgressDialog();
                        }
                    });
            view.dismissProgressDialog();
            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void getLocation(String process, Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", process);
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_ID, ""));

            String url = Constants.BASE_URL + Constants.DASHBOARD_LOCATION_SPINNER;
            GSONRequest<LocationDashboardBean> dashboardGsonRequest = new GSONRequest<LocationDashboardBean>(
                    Request.Method.POST,
                    url,
                    LocationDashboardBean.class, map,
                    new com.android.volley.Response.Listener<LocationDashboardBean>() {
                        @Override
                        public void onResponse(LocationDashboardBean res) {
                            view.dismissProgressDialog();
                            if (res.getSelect_location().size()>0)
                            {
                                view.showLocation(res);
                                view.dismissProgressDialog();
                            } else {
                                view.showLocation(res);
                                view.dismissProgressDialog();
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            view.dismissProgressDialog();
                        }
                    });
            view.dismissProgressDialog();
            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void getLeadSource(String process, Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", process);

            String url = Constants.BASE_URL + Constants.SELECT_LEAD_SOURCE_SPINNER;
            GSONRequest<LeadSourceBean> dashboardGsonRequest = new GSONRequest<LeadSourceBean>(
                    Request.Method.POST,
                    url,
                    LeadSourceBean.class, map,
                    new com.android.volley.Response.Listener<LeadSourceBean>() {
                        @Override
                        public void onResponse(LeadSourceBean res) {
                            view.dismissProgressDialog();
                            if (res.getSelect_lead_source().size()>0)
                            {
                                view.showLeadSource(res);
                                view.dismissProgressDialog();
                            } else {
                                view.showLeadSource(res);
                                view.dismissProgressDialog();
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            view.dismissProgressDialog();
                        }
                    });
            view.dismissProgressDialog();
            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }


}
