package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.NewCarStockCountDetailsBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 5/26/2018.
 */

public class NewCarStockCountDetailPresenter implements IPresenter.INewCarStockCountPresenter {

    IView.NewCarStockCountDetailView iview;

    public NewCarStockCountDetailPresenter(IView.NewCarStockCountDetailView view){
        this.iview = view;
    }

    @Override
    public void getNewCarStockCountDetail(final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            map.put("model", SharedPreferenceManager.getInstance(context).getPreference("MODEL_NewStock", ""));
            map.put("assigned_location", SharedPreferenceManager.getInstance(context).getPreference("STOCK_LOCATION_NewStock", ""));
            map.put("vehicle_status", SharedPreferenceManager.getInstance(context).getPreference("VEHICLE_STATUS_NewStock", ""));
            map.put("ageing", SharedPreferenceManager.getInstance(context).getPreference("AGEING_NewStock", ""));
            map.put("price", SharedPreferenceManager.getInstance(context).getPreference("PRICE_NewStock", ""));

            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK_LIST;
            GSONRequest<NewCarStockCountDetailsBean> dashboardGsonRequest = new GSONRequest<NewCarStockCountDetailsBean>(
                    Request.Method.POST,
                    url,
                    NewCarStockCountDetailsBean.class, map,
                    new com.android.volley.Response.Listener<NewCarStockCountDetailsBean>() {
                        @Override
                        public void onResponse(NewCarStockCountDetailsBean res) {
                            if (res.getNew_stock_list().size()>0) {
                                try{
                                    iview.showNewCarStockCountList(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iview.showNewCarStockCountList(res);
                                Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
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

    @Override
    public void getNewCarStockCountModelDetail(String model,final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            map.put("model", model);
            map.put("assigned_location", SharedPreferenceManager.getInstance(context).getPreference("STOCK_LOCATION_NewStock", ""));
            map.put("vehicle_status", SharedPreferenceManager.getInstance(context).getPreference("VEHICLE_STATUS_NewStock", ""));
            map.put("ageing", SharedPreferenceManager.getInstance(context).getPreference("AGEING_NewStock", ""));
            map.put("price", SharedPreferenceManager.getInstance(context).getPreference("PRICE_NewStock", ""));

            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK_LIST;
            GSONRequest<NewCarStockCountDetailsBean> dashboardGsonRequest = new GSONRequest<NewCarStockCountDetailsBean>(
                    Request.Method.POST,
                    url,
                    NewCarStockCountDetailsBean.class, map,
                    new com.android.volley.Response.Listener<NewCarStockCountDetailsBean>() {
                        @Override
                        public void onResponse(NewCarStockCountDetailsBean res) {
                            if (res.getNew_stock_list().size()>0) {
                                try{
                                    iview.showNewCarStockCountList(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iview.showNewCarStockCountList(res);
                                Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
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
