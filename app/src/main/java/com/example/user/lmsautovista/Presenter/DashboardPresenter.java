package com.example.user.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Model.LocationDashboardBean;
import com.example.user.lmsautovista.Model.LoginBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/*
  Created by Shalu Dhochak on 2/19/2018.
*/

public class DashboardPresenter implements IPresenter.IDashboardPresenter{

    String new_lead,unassigend_leads,call_today,pending_newLeads,pending_followup, location;
    IView.LoginView view;

    public DashboardPresenter(IView.LoginView dashboardView) {
        this.view = dashboardView;
    }

    @Override
    public void getDashboardList(Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("location_select", SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));

            String url = Constants.BASE_URL + Constants.DASHBOARD_COUNT;
            GSONRequest<DashboardCountBean> dashboardGsonRequest = new GSONRequest<DashboardCountBean>(
                    Request.Method.POST,
                    url,
                    DashboardCountBean.class, map,
                    new com.android.volley.Response.Listener<DashboardCountBean>() {
                        @Override
                        public void onResponse(DashboardCountBean res) {
                            view.dismissProgressDialog();
                          //  if (!(res.dashboard_count.equals("")))
                            if (res.dashboard_count.size()>0)
                            {
                                new_lead = res.dashboard_count.get(0).getNew_leads();
                                unassigend_leads = res.dashboard_count.get(0).getUnassigned_leads();
                                call_today = res.dashboard_count.get(0).getCall_today();
                                pending_newLeads = res.dashboard_count.get(0).getPending_new_leads();
                                pending_followup =res.dashboard_count.get(0).getPending_followup();
                                try {
                             //       view.showDailyAppointmentsCount(new_lead,unassigend_leads,call_today,pending_newLeads,pending_followup);
                                    view.ShowDashboardCount(res);
                                } catch (Exception e) {
                                  //  Toast.makeText(context, "njf", Toast.LENGTH_SHORT).show();
                                }
                                view.dismissProgressDialog();
                            } else {
                                view.dismissProgressDialog();
                                //view.("Unable to ");
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
    public void getLocationList(Context context) {

        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("username", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_EMAIL, ""));
            map.put("password", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_PASSWORD, ""));

            String url = Constants.BASE_URL + Constants.LOGIN_URL;
            GSONRequest<LoginBean> dashboardGsonRequest = new GSONRequest<LoginBean>(
                    Request.Method.POST,
                    url,
                    LoginBean.class, map,
                    new com.android.volley.Response.Listener<LoginBean>() {
                        @Override
                        public void onResponse(LoginBean res) {
                            view.dismissProgressDialog();
                            if (!(res.getSession_data().equals("")))
                            {
                               view.showLocationList(res);

                                view.dismissProgressDialog();
                            } else {
                                view.dismissProgressDialog();
                                //view.("Unable to ");
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
    public void getLocationSpinnerList(Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
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
                                view.showLocationDashboard(res);

                                view.dismissProgressDialog();
                            } else {
                                view.dismissProgressDialog();
                                //view.("Unable to ");
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
    public void getDashboardLocationList(String location_id, Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("location_select", location_id);
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));

            String url = Constants.BASE_URL + Constants.DASHBOARD_COUNT;
            GSONRequest<DashboardCountBean> dashboardGsonRequest = new GSONRequest<DashboardCountBean>(
                    Request.Method.POST,
                    url,
                    DashboardCountBean.class, map,
                    new com.android.volley.Response.Listener<DashboardCountBean>() {
                        @Override
                        public void onResponse(DashboardCountBean res) {
                            view.dismissProgressDialog();
                            //  if (!(res.dashboard_count.equals("")))
                            if (res.dashboard_count.size()>0)
                            {
                                new_lead = res.dashboard_count.get(0).getNew_leads();
                                unassigend_leads = res.dashboard_count.get(0).getUnassigned_leads();
                                call_today = res.dashboard_count.get(0).getCall_today();
                                pending_newLeads = res.dashboard_count.get(0).getPending_new_leads();
                                pending_followup =res.dashboard_count.get(0).getPending_followup();
                                try {
                                    //       view.showDailyAppointmentsCount(new_lead,unassigend_leads,call_today,pending_newLeads,pending_followup);
                                    view.ShowDashboardCount(res);
                                } catch (Exception e) {
                                    //  Toast.makeText(context, "njf", Toast.LENGTH_SHORT).show();
                                }
                                view.dismissProgressDialog();
                            } else {
                                view.ShowDashboardCount(res);
                                view.dismissProgressDialog();
                                //view.("Unable to ");
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
