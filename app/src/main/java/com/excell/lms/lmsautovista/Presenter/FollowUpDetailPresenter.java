package com.excell.lms.lmsautovista.Presenter;

/*
 Created by Shalu Dhochak on 4/22/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.FollowUpDetailsBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class FollowUpDetailPresenter implements IPresenter.IFollowUpDetailsPresenter{

    IView.FollowUpDetailsView iview;

    public FollowUpDetailPresenter(IView.FollowUpDetailsView view){
        this.iview = view;
    }

    @Override
    public void getFollowUpDetailsList(String enq_id, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("enq_id", enq_id);

            String url = Constants.BASE_URL + Constants.FOLLOWUP_DETAILS;
            GSONRequest<FollowUpDetailsBean> dashboardGsonRequest = new GSONRequest<FollowUpDetailsBean>(
                    Request.Method.POST,
                    url,
                    FollowUpDetailsBean.class, map,
                    new com.android.volley.Response.Listener<FollowUpDetailsBean>() {
                        @Override
                        public void onResponse(FollowUpDetailsBean res) {
                            if (res.getFollowup_details().size()>0)
                            {
                                try{
                                    iview.ShowFollowUpDetailsList(res);
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
