package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/2/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Model.AddNewLeadBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class AddNewLeadPresenter implements IPresenter.IAddNewLeadOresenter{

    IView.AddNewLeadView view;
    public AddNewLeadPresenter(IView.AddNewLeadView iview){
        this.view = iview;
    }

    @Override
    public void saveNewLeadInfo(String fname, String email, String address, String assign, String contact_no, String comment, String lead_source, String assign_by, String username, String process_name, final Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("fname", fname);
            map.put("email", email);
            map.put("address", address);
            map.put("assign", assign);
            map.put("contact_no", contact_no);
            map.put("comment", comment);
            map.put("lead_source", lead_source);
            map.put("assign_by", assign_by);
            map.put("username", username);
            map.put("process_name", process_name);

            String url = Constants.BASE_URL + Constants.ADD_NEW_LEAD;
            GSONRequest<AddNewLeadBean> loginGsonRequest = new GSONRequest<AddNewLeadBean>(
                    Request.Method.POST,
                    url,
                    AddNewLeadBean.class, map,
                    new com.android.volley.Response.Listener<AddNewLeadBean>() {
                        @Override
                        public void onResponse(AddNewLeadBean res) {
                            view.dismissProgressDialog();
                            if (res.getSuccess().equals("1"))
                            {

                                view.dismissProgressDialog();
                                view.addNewLeadSuccess();
                            } else {
                                view.dismissProgressDialog();
                                view.addNewLeadFailure("Unable to ");
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            view.dismissProgressDialog();
                        }
                    });
            view.dismissProgressDialog();
            loginGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(loginGsonRequest);
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }


}
