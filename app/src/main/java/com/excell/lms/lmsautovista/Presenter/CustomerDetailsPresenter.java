package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.CustomerDetailsBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/*
  Created by Shalu Dhochak on 4/18/2018.
*/

public class CustomerDetailsPresenter implements IPresenter.ICustomerDetailsPresenter{

    IView.CustomerDetailsTaskView view;

    public CustomerDetailsPresenter(IView.CustomerDetailsTaskView customerView){
        this.view = customerView;
    }

    @Override
    public void getCustomerDetailsList(String enquiry_id,final Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("enq_id", enquiry_id);
            String url = Constants.BASE_URL + Constants.CUSTOMER_DETAILS;
            GSONRequest<CustomerDetailsBean> dashboardGsonRequest = new GSONRequest<CustomerDetailsBean>(
                    Request.Method.POST,
                    url,
                    CustomerDetailsBean.class, map,
                    new com.android.volley.Response.Listener<CustomerDetailsBean>() {
                        @Override
                        public void onResponse(CustomerDetailsBean res) {
                          //  try {
                                if (res.getCustomer_details().size() > 0) {
                                    view.ShowCustomerDetailsList(res);
                                } else {
                                    view.ShowCustomerDetailsList(res);
                                }
                           // }catch (Exception e){
                       //         Toast.makeText(context, "null pointer", Toast.LENGTH_SHORT).show();
                          //  }
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
