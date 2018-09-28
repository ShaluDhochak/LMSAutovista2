package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.AuditorRemarkBean;
import com.excell.lms.lmsautovista.Model.FollowUpDetailsBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class AuditorDetailPresenter  implements IPresenter.IAuditorDetailsPresenter{

    IView.AuditorDetailsView iview;

    public AuditorDetailPresenter(IView.AuditorDetailsView view){
        this.iview = view;
    }

    @Override
    public void getAuditorDetailsList(String enq_id, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("process_id_session", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("booking_id", enq_id);

            String url = Constants.BASE_URL + Constants.AUDITOR_DETAIL;
            GSONRequest<AuditorRemarkBean> dashboardGsonRequest = new GSONRequest<AuditorRemarkBean>(
                    Request.Method.POST,
                    url,
                    AuditorRemarkBean.class, map,
                    new com.android.volley.Response.Listener<AuditorRemarkBean>() {
                        @Override
                        public void onResponse(AuditorRemarkBean res) {
                            if (res.getAuditor_remark_detail().size()>0)
                            {
                                try{
                                    iview.ShowAuditorDetailsList(res);
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
