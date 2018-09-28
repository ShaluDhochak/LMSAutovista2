package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.MessageListBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shalu Dhochak on 4/30/2018.
 */

public class AddMessagePresenter implements IPresenter.IAddMessagePresenter{

    IView.IAddMessageView iview;
    public AddMessagePresenter(IView.IAddMessageView view){
        this.iview = view;
    }

    @Override
    public void getAddViewMessageList(final Context context) {
        String userPref = SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, "");

        Map<String,String> appLeadHashMap = new HashMap<>();
        appLeadHashMap.put("user_id", userPref);

        String url =  Constants.BASE_URL + Constants.MESSAGE_LIST;
        GSONRequest<MessageListBean> gsonRequest = new GSONRequest<MessageListBean>(
                Request.Method.POST, url , MessageListBean.class,appLeadHashMap,
                new Response.Listener<MessageListBean>() {
                    @Override
                    public void onResponse(MessageListBean response) {
                        if (response.getMessage_list().size() > 0) {
                            iview.showAddViewMessagelist(response);
                        }else {
                            Toast.makeText(context, "No Record found", Toast.LENGTH_SHORT).show();
                            iview.showAddViewMessagelist(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        Utilities.getRequestQueue(context).add(gsonRequest);

    }
}
