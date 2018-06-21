package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.POCStockCountDetailListBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 5/25/2018.
 */

public class PocStockCountPresenter implements IPresenter.IPOCStockCarCountPresenter{

    IView.PocCarStockCountDetailView iview;

    public PocStockCountPresenter( IView.PocCarStockCountDetailView view){
        this.iview = view;
    }

    @Override
    public void getPocCarStockCount(String model, String stock_location, String mfg_year, String owner, String ageing, String price,final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            if (model.equals("Model")){
                map.put("model", "");
            }else{
                map.put("model", model);
            }

            if (stock_location.equals("Location")){
                map.put("stock_location", "");
            }else{
                map.put("stock_location", stock_location);
            }

            if (mfg_year.equals("Mfg Year")){
                map.put("mfg_year", "");
            }else{
                map.put("mfg_year", mfg_year);
            }

            if (owner.equals("Owner")){
                map.put("owner", "");
            }else{
                map.put("owner", owner);
            }

            if (ageing.equals("Ageing")){
                map.put("ageing", "");
            }else{
                map.put("ageing", ageing);
            }

            if (price.equals("Price")){
                map.put("price", "");
            }else{
                map.put("price", price);
            }

            String url = Constants.BASE_URL + Constants.POC_STOCK_LIST;
            GSONRequest<POCStockCountDetailListBean> dashboardGsonRequest = new GSONRequest<POCStockCountDetailListBean>(
                    Request.Method.POST,
                    url,
                    POCStockCountDetailListBean.class, map,
                    new com.android.volley.Response.Listener<POCStockCountDetailListBean>() {
                        @Override
                        public void onResponse(POCStockCountDetailListBean res) {
                            if (res.getPoc_stock_list().size()>0) {
                                try{
                                    iview.showPOCCarStockCountList(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iview.showPOCCarStockCountList(res);
                           //     Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
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
    public void getPocCarStockCount1(final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            map.put("model", SharedPreferenceManager.getInstance(context).getPreference("MODEL_POC", ""));
            map.put("stock_location", SharedPreferenceManager.getInstance(context).getPreference("STOCK_LOCATION_POC", ""));
            map.put("mfg_year", SharedPreferenceManager.getInstance(context).getPreference("MFGYR_POC", ""));
            map.put("owner", SharedPreferenceManager.getInstance(context).getPreference("OWNER_POC", ""));
            map.put("ageing", SharedPreferenceManager.getInstance(context).getPreference("AGEING_POC", ""));
            map.put("price", SharedPreferenceManager.getInstance(context).getPreference("PRICE_POC", ""));

            String url = Constants.BASE_URL + Constants.POC_STOCK_LIST;
            GSONRequest<POCStockCountDetailListBean> dashboardGsonRequest = new GSONRequest<POCStockCountDetailListBean>(
                    Request.Method.POST,
                    url,
                    POCStockCountDetailListBean.class, map,
                    new com.android.volley.Response.Listener<POCStockCountDetailListBean>() {
                        @Override
                        public void onResponse(POCStockCountDetailListBean res) {
                            if (res.getPoc_stock_list().size()>0) {
                                try{
                                    iview.showPOCCarStockCountList(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iview.showPOCCarStockCountList(res);
                            //    Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
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
    public void getPocCarStockMakeCount1(String model,final Context context) {
        try {
            String location, mfg_yr, owner, ageing, price;
            location = SharedPreferenceManager.getInstance(context).getPreference("STOCK_LOCATION_POC", "");
            mfg_yr =SharedPreferenceManager.getInstance(context).getPreference("MFGYR_POC", "");
            owner =SharedPreferenceManager.getInstance(context).getPreference("OWNER_POC", "");
            ageing =SharedPreferenceManager.getInstance(context).getPreference("AGEING_POC", "");
            price = SharedPreferenceManager.getInstance(context).getPreference("PRICE_POC", "");

            Map<String, String> map = new HashMap<>();

            map.put("model", model);

            if (!(location.equals(""))){
                map.put("stock_location",location);
            }else{
                map.put("stock_location","");
            }

            if (!(mfg_yr.equals(""))){
                map.put("mfg_year",mfg_yr);
            }else{
                map.put("mfg_year","");
            }

            if (!(owner.equals(""))){
                map.put("owner",owner);
            }else{
                map.put("owner","");
            }

            if (!(ageing.equals(""))){
                map.put("ageing",ageing);
            }else{
                map.put("ageing","");
            }

            if (!(price.equals(""))){
                map.put("price",price);
            }else{
                map.put("price","");
            }

            String url = Constants.BASE_URL + Constants.POC_STOCK_LIST;
            GSONRequest<POCStockCountDetailListBean> dashboardGsonRequest = new GSONRequest<POCStockCountDetailListBean>(
                    Request.Method.POST,
                    url,
                    POCStockCountDetailListBean.class, map,
                    new com.android.volley.Response.Listener<POCStockCountDetailListBean>() {
                        @Override
                        public void onResponse(POCStockCountDetailListBean res) {
                            if (res.getPoc_stock_list().size()>0) {
                                try{
                                    iview.showPOCCarStockCountList(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iview.showPOCCarStockCountList(res);
                           //     Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
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
