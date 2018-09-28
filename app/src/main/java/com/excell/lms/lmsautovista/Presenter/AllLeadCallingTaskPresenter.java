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

public class AllLeadCallingTaskPresenter implements IPresenter.IAllLeadCallingTaskPresenter{

    IView.AllLeadCallingTaskView iview;

    public AllLeadCallingTaskPresenter(IView.AllLeadCallingTaskView view){
        this.iview = view;
    }

    @Override
    public void getAllLeadCallingTaskList(String page_no,final Context context) {
        try {

           /* Map<String, String> map = new HashMap<>();
            map.put("contact_no", "");
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("page", page_no);
            map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
*/
            String processComplain = SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, "");
            Map<String, String> map = new HashMap<>();
            String url;
            if (processComplain.equals("9")){
                map.put("contact_no", "");
                map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
                map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
                map.put("page", "-1");
                map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
                map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
                map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));
                url = Constants.BASE_URL + Constants.ALL_LEAD_COMPLAINT;
            }else{
                map.put("contact_no", "");
                map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
                map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
                map.put("page", page_no);
                map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));
                map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
                map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
                url = Constants.BASE_URL + Constants.ALL_LAED_CALLING_TASK;
            }

          //  String url = Constants.BASE_URL + Constants.ALL_LAED_CALLING_TASK;
            GSONRequest<CallingTaskNewLeadBean> dashboardGsonRequest = new GSONRequest<CallingTaskNewLeadBean>(
                    Request.Method.POST,
                    url,
                    CallingTaskNewLeadBean.class, map,
                    new com.android.volley.Response.Listener<CallingTaskNewLeadBean>() {
                        @Override
                        public void onResponse(CallingTaskNewLeadBean res) {
                            if (res.getLead_details().size()>0) {
                                    iview.showAllLeadDetails(res);
                            }else{
                                    Toast.makeText(context, "No record found!", Toast.LENGTH_SHORT).show();
                                    iview.showAllLeadDetails(res);
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener(){
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
    public void getAllSearchedLeadCallingTask(String contact_n, String page_no,final Context context) {
        try {
            String processComplain = SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, "");
            Map<String, String> map = new HashMap<>();
            String url;
            if (processComplain.equals("9")){
                map.put("contact_no", contact_n);
                map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
                map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
                map.put("page", "-1");
                map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
                map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
                map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));
                url = Constants.BASE_URL + Constants.ALL_LEAD_COMPLAINT;
            }else{
               map.put("contact_no", contact_n);
                map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
                map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
                map.put("page", page_no);
                map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
                map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
                url = Constants.BASE_URL + Constants.ALL_LAED_CALLING_TASK;
            }

            GSONRequest<CallingTaskNewLeadBean> dashboardGsonRequest = new GSONRequest<CallingTaskNewLeadBean>(
                    Request.Method.POST,
                    url,
                    CallingTaskNewLeadBean.class, map,
                    new com.android.volley.Response.Listener<CallingTaskNewLeadBean>() {
                        @Override
                        public void onResponse(CallingTaskNewLeadBean res) {
                            try{
                                if (res.getLead_details_count().size()>0) {
                                    iview.showAllLeadDetails(res);
                                }else{
                                    try{
                                       // Toast.makeText(context, "No record found for All Lead!", Toast.LENGTH_SHORT).show();
                                        iview.showAllLeadDetails(res);
                                    } catch (Exception e) {
                                    }
                                }
                            } catch (Exception e) {
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
