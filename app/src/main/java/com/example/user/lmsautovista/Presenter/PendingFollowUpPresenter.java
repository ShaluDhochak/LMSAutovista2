package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/19/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class PendingFollowUpPresenter implements IPresenter.IPendingLiveCallingTaskPresenter{

    IView.PendingLiveCallingTaskView iview;

    public PendingFollowUpPresenter(IView.PendingLiveCallingTaskView view){
        this.iview = view;
    }

    @Override
    public void getPendingCallingTaskList(Context context) {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("process_id_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("user_id_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("page", "-1");
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));

            String url = Constants.BASE_URL + Constants.PENDING_FOLLOW_UP_LEAD_DASHBOARD;
            GSONRequest<CallingTaskNewLeadBean> dashboardGsonRequest = new GSONRequest<CallingTaskNewLeadBean>(
                    Request.Method.POST,
                    url,
                    CallingTaskNewLeadBean.class, map,
                    new com.android.volley.Response.Listener<CallingTaskNewLeadBean>() {
                        @Override
                        public void onResponse(CallingTaskNewLeadBean res) {
                            if (!(res.getLead_details_count().equals("0"))) {
                                try{
                                    iview.showPendingLiveLeadDetails(res);
                                } catch (Exception e) {
                                }
                            }else{

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
