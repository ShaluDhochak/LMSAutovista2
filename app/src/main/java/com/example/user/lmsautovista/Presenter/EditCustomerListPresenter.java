package com.example.user.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.SearchCustomerBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shalu Dhochak on 4/28/2018.
 */

public class EditCustomerListPresenter  implements IPresenter.IEditCustomerOperationPresenter {

    IView.EditCustomerListView iView;

    public EditCustomerListPresenter(IView.EditCustomerListView view){
        this.iView = view;
    }

    @Override
    public void getEditCustomerList(String contact_no, Context context) {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("customer_name", contact_no);

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
                                    iView.showEditCustomerList(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iView.showEditCustomerList(res);
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
