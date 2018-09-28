package com.excell.lms.lmsautovista.Presenter;

/*
Created by Shalu Dhochak on 5/2/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.SearchTrackerListBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MonthlyReportPresenter implements IPresenter.IMonthlyReportPresenter{
    IView.IMonthlyReportView view;

    public MonthlyReportPresenter(IView.IMonthlyReportView view){
        this.view = view;
    }

    @Override
    public void getSearchTRackerList(final Context context, String currentDate) {
        try {
            Calendar c = Calendar.getInstance();
            int day = 1;
            int month = c.get(Calendar.MONTH) + 1;
            int year = c.get(Calendar.YEAR);
            String fromdate = year + "-" + month + "-" + day;
            String todate = year + "-" +month +"-" +day;
            if(month==1 ||month==3||month==5 ||month==7||month==8||month==10 ||month==12){
                todate = year + "-" +month + "-" + "31";
            }else if (month==4 ||month==6 ||month==9|| month==11){
                todate = year + "-" + month + "-" + "30";
            }else if (month ==2){
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
                    todate = year + "-" + month + "-" + "29";
                }else{
                    todate = year + "-" + month + "-" + "28";
                }
            }

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("page", "-1");
            map.put("campaign_name", "All");
            map.put("date_type", "Lead");
            map.put("fromdate", fromdate);
            map.put("todate", todate);
            map.put("role_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_NAME, ""));

            String url = Constants.BASE_URL + Constants.SEARCH_TRACKER;
            GSONRequest<SearchTrackerListBean> dashboardGsonRequest = new GSONRequest<SearchTrackerListBean>(
                    Request.Method.POST,
                    url,
                    SearchTrackerListBean.class, map,
                    new com.android.volley.Response.Listener<SearchTrackerListBean>() {
                        @Override
                        public void onResponse(SearchTrackerListBean res) {
                            try {
                                if (res.getUser_details_count().size()>0) {
                                    view.showTrackerListView(res);
                                } else {
                                    view.showTrackerListView(res);
                                    Toast.makeText(context, "No Record Found", Toast.LENGTH_SHORT).show();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
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
