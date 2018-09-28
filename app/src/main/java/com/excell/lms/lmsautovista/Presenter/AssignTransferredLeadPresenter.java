package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.AssignToUserBean;
import com.excell.lms.lmsautovista.Model.AssignTransferLocationBean;
import com.excell.lms.lmsautovista.Model.AssignTransferredCampignListBean;
import com.excell.lms.lmsautovista.Model.FromUserAssignTransferBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/*
  Created by Shalu Dhochak on 5/7/2018.
*/

public class AssignTransferredLeadPresenter implements IPresenter.IAssignTransferLeadPresenter{

    IView.IAssignTransferredLeadView iview;

    public AssignTransferredLeadPresenter(IView.IAssignTransferredLeadView view){
        this.iview = view;
    }

    @Override
    public void getAssignLocation(Context context) {
        Map<String, String> map = new HashMap<>();
        map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
        map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));

        String Url = Constants.BASE_URL + Constants.ASSIGN_TRANSFER_LOCATION;

        GSONRequest<AssignTransferLocationBean> locationSpinnerGsonRequest = new GSONRequest<AssignTransferLocationBean>(
                Request.Method.POST, Url, AssignTransferLocationBean.class, map, new Response.Listener<AssignTransferLocationBean>() {
                    @Override
                    public void onResponse(AssignTransferLocationBean response) {
                        try{
                        if (response.getFrom_location().size()>0){
                                iview.showAssignTransferredLocation(response);
                           }else{
                            iview.showAssignTransferredLocation(response);
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
        locationSpinnerGsonRequest.setShouldCache(false);
        Utilities.getRequestQueue(context).add(locationSpinnerGsonRequest);
    }

    @Override
    public void getAssignFromList(String location_id, Context context) {
        String Url = Constants.BASE_URL + Constants.ASSIGN_TRANSFER_FROM_USER;

        Map<String, String> map = new HashMap<>();
        map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
        map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
        map.put("location_id", location_id);
        map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));

        GSONRequest<FromUserAssignTransferBean> fromUserSpinnerGsonRequest = new GSONRequest<FromUserAssignTransferBean>(Request.Method.POST,
                Url, FromUserAssignTransferBean.class, map, new Response.Listener<FromUserAssignTransferBean>() {
                    @Override
                    public void onResponse(FromUserAssignTransferBean response) {
                        try {
                            if (response.getAssign_transferred_lead_from_user().size() > 0) {
                                iview.showAssignFromSpinnerView(response);
                            } else {
                                iview.showAssignFromSpinnerView(response);
                           }
                        }catch(Exception e)
                        {
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        fromUserSpinnerGsonRequest.setShouldCache(false);
        Utilities.getRequestQueue(context).add(fromUserSpinnerGsonRequest);
    }

    @Override
    public void getAssignToList(String location_id,String fromUser,final Context context) {

        String Url = Constants.BASE_URL + Constants.ASSIGN_TRANSFER_TO_USER;
        Map<String, String> map = new HashMap<>();
        map.put("fromUser", fromUser);
        map.put("process_id",  SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
        map.put("toLocation_id", location_id);

        GSONRequest<AssignToUserBean> assignToSpinnerGsonRequest = new GSONRequest<AssignToUserBean>(
                Request.Method.POST, Url, AssignToUserBean.class, map, new Response.Listener<AssignToUserBean>() {
                    @Override
                    public void onResponse(AssignToUserBean response) {
                        try {
                            if (response.getAssign_transferred_lead_to_user().size() > 0) {
                                iview.showAssignToView(response);
                            } else {
                                iview.showAssignToView(response);
                            }
                        }catch(Exception e)
                        {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        assignToSpinnerGsonRequest.setShouldCache(false);
        Utilities.getRequestQueue(context).add(assignToSpinnerGsonRequest);

    }

    @Override
    public void getAssignToLocation(Context context) {
        String Url = Constants.BASE_URL + Constants.ASSIGN_TRANSFER_LOCATION;

        Map<String, String> map = new HashMap<>();
        map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
        map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));

        GSONRequest<AssignTransferLocationBean> locationToSpinnerGsonRequest = new GSONRequest<AssignTransferLocationBean>(
                Request.Method.POST, Url, AssignTransferLocationBean.class, map,
                new Response.Listener<AssignTransferLocationBean>() {
                    @Override
                    public void onResponse(AssignTransferLocationBean response) {
                        try {
                            if (response.getTo_location().size() > 0) {
                                iview.showAssignToLocationView(response);
                            } else {
                                iview.showAssignToLocationView(response);
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
    public void getAssignCampaignList(String fromUser, Context context) {
        String Url = Constants.BASE_URL + Constants.ASSIGN_TRANSFER_CAMPAIGN_LIST;

        Map<String, String> map = new HashMap<>();
        map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
        map.put("fromUser",fromUser);
        map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
        GSONRequest<AssignTransferredCampignListBean> assignSpinnerGsonRequest = new GSONRequest<AssignTransferredCampignListBean>(
                Request.Method.POST,
                Url,
                AssignTransferredCampignListBean.class, map,
                new Response.Listener<AssignTransferredCampignListBean>() {
                    @Override
                    public void onResponse(AssignTransferredCampignListBean response) {
                     try {
                          if (response.getAssign_transferred_leads_source().size() > 0) {
                              iview.showAssignCampaignlist(response);
                          } else {
                             iview.showAssignCampaignlist(response);
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
        assignSpinnerGsonRequest.setShouldCache(false);
        Utilities.getRequestQueue(context).add(assignSpinnerGsonRequest);
    }


}
