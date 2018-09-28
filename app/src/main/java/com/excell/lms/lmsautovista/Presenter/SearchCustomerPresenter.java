package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/26/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.SearchCustomerBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class SearchCustomerPresenter implements IPresenter.ISearchCustomerPresenter {

    IView.CustomerSearchView iView;

    public SearchCustomerPresenter(IView.CustomerSearchView view){
        this.iView = view;
    }

    @Override
    public void getSearchViaContactNoList(String customer_no,final Context context) {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("customer_name", customer_no);

            String url = Constants.BASE_URL + Constants.CUSTOMER_SEARCH;
            GSONRequest<SearchCustomerBean> dashboardGsonRequest = new GSONRequest<SearchCustomerBean>(
                    Request.Method.POST,
                    url,
                    SearchCustomerBean.class, map,
                    new com.android.volley.Response.Listener<SearchCustomerBean>() {
                        @Override
                        public void onResponse(SearchCustomerBean res) {
                            if (res.getLead_data().size()>0) {
                                try{
                                    iView.showSearchCustomerDetails(res);
                                } catch (Exception e) {
                                }
                            }else{
                                Toast.makeText(context, "No Record found", Toast.LENGTH_SHORT).show();
                                iView.showSearchCustomerDetails(res);
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
