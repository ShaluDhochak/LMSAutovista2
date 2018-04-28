package com.example.user.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.CustomerDetailsBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

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
    public void getCustomerDetailsList(String enquiry_id,Context context) {
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
                            if (res.getCustomer_details().size()>0)
                            {
                                try{
                                    view.ShowCustomerDetailsList(res);
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
