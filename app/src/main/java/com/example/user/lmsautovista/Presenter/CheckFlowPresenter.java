package com.example.user.lmsautovista.Presenter;

/*
 Created by Shalu Dhochak on 5/12/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.CheckFlowTransferLeadDetailBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class CheckFlowPresenter implements IPresenter.ICheckFlowCustomer{

    IView.ICheckFlowView iview;

    public CheckFlowPresenter(IView.ICheckFlowView view){
        this.iview = view;
    }

    @Override
    public void getCheckFlow(String enq_id,final Context context) {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("enq_id", enq_id);

            String url = Constants.BASE_URL + Constants.CHECK_FLOW;
            GSONRequest<CheckFlowTransferLeadDetailBean> dashboardGsonRequest = new GSONRequest<CheckFlowTransferLeadDetailBean>(
                    Request.Method.POST,
                    url,
                    CheckFlowTransferLeadDetailBean.class, map,
                    new com.android.volley.Response.Listener<CheckFlowTransferLeadDetailBean>() {
                        @Override
                        public void onResponse(CheckFlowTransferLeadDetailBean res) {
                            if (res.getLead_data().size()>0)
                            {
                                try{
                                    iview.showCheckFlow(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iview.showCheckFlow(res);
                                Toast.makeText(context, "No Resord Found", Toast.LENGTH_SHORT).show();
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
