package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.DashboardCountBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

import static com.excell.lms.lmsautovista.Utils.NetworkUtilities.MY_SOCKET_TIMEOUT_MS;

/*
  Created by Shalu Dhochak on 2/19/2018.
*/

public class DashboardPresenter implements IPresenter.IDashboardPresenter, IPresenter.ILocationWiseDashboardPresenter{

    String new_lead,unassigend_leads,call_today,pending_newLeads,pending_followup, location;
    IView.LoginView view;

    public DashboardPresenter(IView.LoginView dashboardView) {
        this.view = dashboardView;
    }

    @Override
    public void getDashboardHeaderLocationList(Context context) {

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
    public void getDashboardProcessList(Context context) {
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
                                view.showProcessDashboard(res);
                                view.dismissProgressDialog();
                            } else {
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
                            if (res.dashboard_count.size()>0)
                            {
                                new_lead = res.dashboard_count.get(0).getNew_leads();
                                unassigend_leads = res.dashboard_count.get(0).getUnassigned_leads();
                                call_today = res.dashboard_count.get(0).getCall_today();
                                pending_newLeads = res.dashboard_count.get(0).getPending_new_leads();
                                pending_followup =res.dashboard_count.get(0).getPending_followup();
                                try {
                                    view.ShowDashboardCount(res);
                                } catch (Exception e) {
                                 }
                                view.dismissProgressDialog();
                            } else {
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
    public void getLocationSpinnerList(String location, String process,final Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location_id",location);
            map.put("user_id",SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));

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
                                view.showLocationDashboard(res);
                            //    Toast.makeText(context, "No record Found", Toast.LENGTH_SHORT).show();
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
                            if (res.dashboard_count.size()>0)
                            {
                                new_lead = res.dashboard_count.get(0).getNew_leads();
                                unassigend_leads = res.dashboard_count.get(0).getUnassigned_leads();
                                call_today = res.dashboard_count.get(0).getCall_today();
                                pending_newLeads = res.dashboard_count.get(0).getPending_new_leads();
                                pending_followup =res.dashboard_count.get(0).getPending_followup();
                                try {
                                    view.ShowDashboardCount(res);
                                } catch (Exception e) {
                                }
                                view.dismissProgressDialog();
                            } else {
                                view.ShowDashboardCount(res);
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
            dashboardGsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void getDashboardCountLocationList(String user_type, String location_id,final Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("location_name", location_id);
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));
            map.put("user_type",user_type);

            String url = Constants.BASE_URL + Constants.LOCATION_DASHBOARD_COUNT;
            GSONRequest<LocationWiseDashboardCountBean> dashboardGsonRequest = new GSONRequest<LocationWiseDashboardCountBean>(
                    Request.Method.POST,
                    url,
                    LocationWiseDashboardCountBean.class, map,
                    new com.android.volley.Response.Listener<LocationWiseDashboardCountBean>() {
                        @Override
                        public void onResponse(LocationWiseDashboardCountBean res) {
                            view.dismissProgressDialog();
                            if (res.getNew_dashboard().size()>0)
                            {
                                try {
                                    view.showLocationDashboardCount(res);
                                } catch (Exception e) {
                                }
                                view.dismissProgressDialog();
                            } else {
                                view.showLocationDashboardCount(res);
                                view.dismissProgressDialog();
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            view.dismissProgressDialog();
                         //   Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                        }
                    });
            view.dismissProgressDialog();
            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);

            dashboardGsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void getDashboardWithoutSelectionDseList(String location_id,final Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("location_name", location_id);
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));
            map.put("user_type","");

            String url = Constants.BASE_URL + Constants.LOCATION_DASHBOARD_COUNT;
            GSONRequest<LocationWiseDashboardCountBean> dashboardGsonRequest = new GSONRequest<LocationWiseDashboardCountBean>(
                    Request.Method.POST,
                    url,
                    LocationWiseDashboardCountBean.class, map,
                    new com.android.volley.Response.Listener<LocationWiseDashboardCountBean>() {
                        @Override
                        public void onResponse(LocationWiseDashboardCountBean res) {
                            view.dismissProgressDialog();
                            if (res.getNew_dashboard().size()>0)
                            {
                                try {
                                    view.showLocationwithoutSeelctionCount(res);
                                } catch (Exception e) {
                                }
                                view.dismissProgressDialog();
                            } else {
                                view.showLocationwithoutSeelctionCount(res);
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
            dashboardGsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }
}
