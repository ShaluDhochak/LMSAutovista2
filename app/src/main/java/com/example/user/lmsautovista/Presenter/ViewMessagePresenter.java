package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/30/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.ViewMessageListBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class ViewMessagePresenter  implements IPresenter.IViewMessagePresenter{

    IView.IViewMessageView iview;

    public ViewMessagePresenter(IView.IViewMessageView view){
        this.iview = view;
    }

    @Override
    public void getViewMessageList(Context context) {
        try {
             String location_id = SharedPreferenceManager.getInstance(context).getPreference(Constants.LOCATION_SESSION, "");;

            Map<String, String> countLeadHashMap = new HashMap<>();
            countLeadHashMap.put("location", location_id);
            String URL = Constants.BASE_URL + Constants.VIEW_MESSAGE;

            GSONRequest<ViewMessageListBean> gsonRequest = new GSONRequest<>(
                    Request.Method.POST, URL, ViewMessageListBean.class, countLeadHashMap,
                    new Response.Listener<ViewMessageListBean>() {
                        @Override
                        public void onResponse(ViewMessageListBean response) {
                            if (response.getMessage_home().size() > 0) {
                                iview.showViewMessageList(response);
                            } else {
                                iview.showViewMessageList(response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
            );
            Utilities.getRequestQueue(context).add(gsonRequest);
        } catch (Exception e) {
            Toast.makeText(context, " Null POinter Exception", Toast.LENGTH_SHORT).show();
        }
    }
}
