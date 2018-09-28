package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/2/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.AssignToBean;
import com.excell.lms.lmsautovista.Model.AssignTransferLocationBean;
import com.excell.lms.lmsautovista.Model.LeadSourceBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;
public class AddNewLeadPresenter implements IPresenter.IAddNewLeadOresenter{

    IView.AddNewLeadView view;
    public AddNewLeadPresenter(IView.AddNewLeadView iview){
        this.view = iview;
    }

    @Override
    public void getProcess(Context context) {
        try {
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
                            if (!(res.getSession_data().equals("")))
                            {
                                view.showProcess(res);
                             } else {
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
    public void getAssignTo(String process, String location, Context context) {
        try {
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
                            try {
                                if (res.getSelect_user().size() > 0) {
                                    view.showAssignTo(res);
                                } else {
                                    view.showAssignTo(res);
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

    @Override
    public void getLocation(String process, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id", process);
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
                             } else {
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
    public void getAssignToLocation(String process_id,Context context) {
        String Url = Constants.BASE_URL + Constants.ASSIGN_TRANSFER_LOCATION;
        Map<String, String> map = new HashMap<>();
        map.put("process_id", process_id);
        map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));

        GSONRequest<AssignTransferLocationBean> locationToSpinnerGsonRequest = new GSONRequest<AssignTransferLocationBean>(
                Request.Method.POST,
                Url,
                AssignTransferLocationBean.class, map,
                new Response.Listener<AssignTransferLocationBean>() {
                    @Override
                    public void onResponse(AssignTransferLocationBean response) {
                        try {
                            if (response.getTo_location().size() > 0) {
                                view.showLocation(response);
                            } else {
                                view.showLocation(response);
                            }
                        }catch(Exception e){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        locationToSpinnerGsonRequest.setShouldCache(false);
        Utilities.getRequestQueue(context).add(locationToSpinnerGsonRequest);
    }
    @Override
    public void getLeadSource(String process, Context context) {
        try {
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
                            if (res.getSelect_lead_source().size()>0)
                            {
                                view.showLeadSource(res);
                            } else {
                                view.showLeadSource(res);
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
