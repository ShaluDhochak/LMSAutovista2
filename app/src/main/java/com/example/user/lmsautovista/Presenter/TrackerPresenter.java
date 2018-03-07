package com.example.user.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.FeedbackListBean;
import com.example.user.lmsautovista.Model.NextActionListBean;
import com.example.user.lmsautovista.Model.SearchTrackerListBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 3/3/2018.
 */

public class TrackerPresenter implements IPresenter.ITrackerPresenter{

    IView.TrackerView view;

    public TrackerPresenter(IView.TrackerView iview){
        this.view = iview;
    }

    @Override
    public void getCompaniesList(Context context) {
        try {

            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));

            String url = Constants.BASE_URL + Constants.SELECT_FEEDBACK;
            GSONRequest<FeedbackListBean> dashboardGsonRequest = new GSONRequest<FeedbackListBean>(
                    Request.Method.POST,
                    url,
                    FeedbackListBean.class, map,
                    new com.android.volley.Response.Listener<FeedbackListBean>() {
                        @Override
                        public void onResponse(FeedbackListBean res) {
                            view.dismissProgressDialog();
                            if (!(res.getSelect_feedback().equals("")))
                            {
                                  view.showFeedbackView(res);
                            } else {
                                view.dismissProgressDialog();
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
            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getNextActionList(Context context, String feedback) {
        try {

            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("feedback", feedback );

            String url = Constants.BASE_URL + Constants.SELECT_NEXTACTION;
            GSONRequest<NextActionListBean> dashboardGsonRequest = new GSONRequest<NextActionListBean>(
                    Request.Method.POST,
                    url,
                    NextActionListBean.class, map,
                    new com.android.volley.Response.Listener<NextActionListBean>() {
                        @Override
                        public void onResponse(NextActionListBean res) {
                            view.dismissProgressDialog();
                            if (!(res.getSelect_nextaction().equals("")))
                            {
                                view.showNextActionView(res);
                            } else {
                                view.dismissProgressDialog();
                                //view.("Unable to ");
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
            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getSearchTRackerList(Context context) {
        try {
            view.showProgressDialog();

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("process_name", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_NAME, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, ""));
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("page", "-1");

            map.put("campaignName", "All");
            map.put("feedback", "");
            map.put("nextaction", "");
            map.put("date_type", "Lead");
            map.put("fromdate", "2017-11-01");
            map.put("todate", "2018-03-02");

            String url = Constants.BASE_URL + Constants.SEARCH_TRACKER;
            GSONRequest<SearchTrackerListBean> dashboardGsonRequest = new GSONRequest<SearchTrackerListBean>(
                    Request.Method.POST,
                    url,
                    SearchTrackerListBean.class, map,
                    new com.android.volley.Response.Listener<SearchTrackerListBean>() {
                        @Override
                        public void onResponse(SearchTrackerListBean res) {
                            view.dismissProgressDialog();
                            if (!(res.getUser_details_count().equals("0")))
                            {
                                try {
                                    view.showTrackerListView(res);
                                } catch (Exception e) {
                                }
                                view.dismissProgressDialog();
                            } else {
                                view.dismissProgressDialog();
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
            dashboardGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(dashboardGsonRequest);
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }

}
