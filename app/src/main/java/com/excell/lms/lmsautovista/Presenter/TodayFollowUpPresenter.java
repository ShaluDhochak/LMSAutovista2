package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/19/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class TodayFollowUpPresenter implements IPresenter.ITodayCallingTaskPresenter{

    IView.TodayLeadCallingTaskView view;

    public TodayFollowUpPresenter(IView.TodayLeadCallingTaskView view){
        this.view = view;
    }

    @Override
    public void getTodayCallingTaskList(final Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("user_id_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("page", "-1");
            map.put("contact_no", "");
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
        //    map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));

            String url = Constants.BASE_URL + Constants.CALL_TODAY_DASHBOARD;
            GSONRequest<CallingTaskNewLeadBean> dashboardGsonRequest = new GSONRequest<CallingTaskNewLeadBean>(
                    Request.Method.POST,
                    url,
                    CallingTaskNewLeadBean.class, map,
                    new com.android.volley.Response.Listener<CallingTaskNewLeadBean>() {
                        @Override
                        public void onResponse(CallingTaskNewLeadBean res) {
                           if (res.getLead_details().size()>0)
                            {
                                try{
                                    view.ShowTodayLeadDetailCount(res);
                                } catch (Exception e) {
                                }
                            }else{
                               view.ShowTodayLeadDetailCount(res);
                               Toast.makeText(context, "No record Found for Today FollowUp", Toast.LENGTH_SHORT).show();
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
    public void getCurrentTodayTaskList(final Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("user_id_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("page", "-1");
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("name", "current");

            String url = Constants.BASE_URL + Constants.CALL_TODAY_DASHBOARD;
            GSONRequest<CallingTaskNewLeadBean> dashboardGsonRequest = new GSONRequest<CallingTaskNewLeadBean>(
                    Request.Method.POST,
                    url,
                    CallingTaskNewLeadBean.class, map,
                    new com.android.volley.Response.Listener<CallingTaskNewLeadBean>() {
                        @Override
                        public void onResponse(CallingTaskNewLeadBean res) {
                            if (res.getLead_details().size()>0)
                            {
                                try{
                                    view.ShowTodayLeadDetailCount(res);
                                } catch (Exception e) {
                                }
                            }else{
                                view.ShowTodayLeadDetailCount(res);
                                Toast.makeText(context, "No Record Found for Current FollowUp", Toast.LENGTH_SHORT).show();
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
