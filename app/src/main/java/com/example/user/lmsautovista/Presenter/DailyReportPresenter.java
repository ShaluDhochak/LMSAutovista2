package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 5/2/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.SearchTrackerListBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class DailyReportPresenter implements IPresenter.IDailyReportPresenter{
    IView.IDailyReportView view;

    public DailyReportPresenter(IView.IDailyReportView view){
        this.view = view;
    }

    @Override
     public void getSearchTRackerList(final Context context, String currentDate) {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("page", "-1");

            map.put("campaign_name", "All");
            map.put("date_type", "Lead");
            map.put("fromdate", currentDate);
            map.put("todate", currentDate);

        String url = Constants.BASE_URL + Constants.SEARCH_TRACKER;
        GSONRequest<SearchTrackerListBean> dashboardGsonRequest = new GSONRequest<SearchTrackerListBean>(
                Request.Method.POST,
                url,
                SearchTrackerListBean.class, map,
                new com.android.volley.Response.Listener<SearchTrackerListBean>() {
                    @Override
                    public void onResponse(SearchTrackerListBean res) {
                        try {
                            if (res.getUser_details_count().size() > 0) {
                                view.showTrackerListView(res);
                            } else {
                                view.showTrackerListView(res);
                                Toast.makeText(context, "No Record Found", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
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
