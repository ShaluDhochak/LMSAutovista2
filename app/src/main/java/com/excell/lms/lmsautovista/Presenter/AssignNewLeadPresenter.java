package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 5/4/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.AssignLocationBean;
import com.excell.lms.lmsautovista.Model.AssignNewLeadAssignUserBean;
import com.excell.lms.lmsautovista.Model.AssignNewLeadCampaignBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class AssignNewLeadPresenter implements IPresenter.IAssignNewLeadPresenter{

    IView.IAssignNewLeadView iview;

    public AssignNewLeadPresenter(IView.IAssignNewLeadView view){
        this.iview = view;
    }

    @Override
    public void getAssignLocation(Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));

            String url = Constants.BASE_URL + Constants.ASSIGN_LOCATION;
            GSONRequest<AssignLocationBean> dashboardGsonRequest = new GSONRequest<AssignLocationBean>(
                    Request.Method.POST, url,
                    AssignLocationBean.class, map,
                    new com.android.volley.Response.Listener<AssignLocationBean>() {
                        @Override
                        public void onResponse(AssignLocationBean res) {
                            try {
                                if (res.getProcess_all_location().size() > 0) {
                                    iview.showAssignToLocation(res);
                                } else {
                                    iview.showAssignToLocation(res);
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
    public void getAssignToList(String location_id, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("location", location_id);

            String url = Constants.BASE_URL + Constants.ASSIGN_ASSIGN_TO;
            GSONRequest<AssignNewLeadAssignUserBean> dashboardGsonRequest = new GSONRequest<AssignNewLeadAssignUserBean>(
                    Request.Method.POST, url,
                    AssignNewLeadAssignUserBean.class, map,
                    new com.android.volley.Response.Listener<AssignNewLeadAssignUserBean>() {
                        @Override
                        public void onResponse(AssignNewLeadAssignUserBean res) {

                            try {
                                if (res.getAssign_new_lead_assign_user().size() > 0) {
                                    iview.showAssignToView(res);
                                } else {
                                    iview.showAssignToView(res);
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
    public void getCamapignList(Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));

            String url = Constants.BASE_URL + Constants.ASSIGN_LOCATION_CAMPAIGN;
            GSONRequest<AssignNewLeadCampaignBean> dashboardGsonRequest = new GSONRequest<AssignNewLeadCampaignBean>(
                    Request.Method.POST, url,
                    AssignNewLeadCampaignBean.class, map,
                    new com.android.volley.Response.Listener<AssignNewLeadCampaignBean>() {
                        @Override
                        public void onResponse(AssignNewLeadCampaignBean res) {

                            try {
                                if (res.getAssign_new_leads_source().size() > 0) {
                                    iview.showCampaignListView(res);
                                } else {
                                    iview.showCampaignListView(res);
                                }
                            }
                            catch(Exception e){

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
