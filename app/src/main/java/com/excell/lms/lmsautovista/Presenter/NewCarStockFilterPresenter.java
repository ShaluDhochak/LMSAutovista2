package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.NewCarStock1Bean;
import com.excell.lms.lmsautovista.Model.NewCarStockLocationModel;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/*
  Created by User on 5/26/2018.
*/

public class NewCarStockFilterPresenter implements IPresenter.INewCarStock1Presenter{

    IView.NewCarStockFilter iview;
    public NewCarStockFilterPresenter(IView.NewCarStockFilter view){
        this.iview = view;
    }

    @Override
    public void getLocation(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK_LOCATION;
            GSONRequest<NewCarStockLocationModel> dashboardGsonRequest = new GSONRequest<NewCarStockLocationModel>(
                    Request.Method.POST,
                    url,
                    NewCarStockLocationModel.class, map,
                    new com.android.volley.Response.Listener<NewCarStockLocationModel>() {
                        @Override
                        public void onResponse(NewCarStockLocationModel res) {
                            if (res.getNew_stock_location().size()>0)
                            {
                                iview.showNewCarLocationSpinner(res);
                            } else {
                                iview.showNewCarLocationSpinner(res);
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
    public void getModel(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK_LOCATION;
            GSONRequest<NewCarStockLocationModel> dashboardGsonRequest = new GSONRequest<NewCarStockLocationModel>(
                    Request.Method.POST, url, NewCarStockLocationModel.class, map,
                    new com.android.volley.Response.Listener<NewCarStockLocationModel>() {
                        @Override
                        public void onResponse(NewCarStockLocationModel res) {
                            if (res.getNew_stock_model().size()>0)
                            {
                                iview.shownewCarModelSpinner(res);
                            } else {
                                iview.shownewCarModelSpinner(res);
                                //view.("Unable to ");
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
    public void getNewCarStockCount(final String model, final String assigned_location, final String vehicle_status, final String ageing, final String price, final Context context) {
        try {

            Map<String, String> map = new HashMap<>();

            if (model.equals("Model")){
                map.put("model", "");
            }else{
                map.put("model", model);
            }

            if (assigned_location.equals("Location")){
                map.put("assigned_location", "");
            }else{
                map.put("assigned_location", assigned_location);
            }

            if (vehicle_status.equals("Visit Status")){
                map.put("vehicle_status", "");
            }else{
                map.put("vehicle_status", vehicle_status);
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

            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK_FILTER;
            GSONRequest<NewCarStock1Bean> dashboardGsonRequest = new GSONRequest<NewCarStock1Bean>(
                    Request.Method.POST,
                    url,
                    NewCarStock1Bean.class, map,
                    new com.android.volley.Response.Listener<NewCarStock1Bean>() {
                        @Override
                        public void onResponse(NewCarStock1Bean res) {
                            if (res.getNew_stock_count().size()>0) {
                                try{
                                    iview.showNewCarStockCountList(res);
                                    showProcessData(model, assigned_location, vehicle_status, ageing, price,res, SharedPreferenceManager.getInstance(context) );
                                } catch (Exception e) {

                                }
                            }else{
                                iview.showNewCarStockCountList(res);
                          //      Toast.makeText(context, "No Record Found.", Toast.LENGTH_SHORT).show();
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
    public void getNewCarStockEmptyCount(final Context context) {
        try {

            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK_FILTER;
            GSONRequest<NewCarStock1Bean> dashboardGsonRequest = new GSONRequest<NewCarStock1Bean>(
                    Request.Method.POST,
                    url,
                    NewCarStock1Bean.class, map,
                    new com.android.volley.Response.Listener<NewCarStock1Bean>() {
                        @Override
                        public void onResponse(NewCarStock1Bean res) {
                            if (res.getNew_stock_count().size()>0) {
                                try{
                                    iview.showNewCarStockCountList(res);
                                } catch (Exception e) {

                                }
                            }else{
                                iview.showNewCarStockCountList(res);
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
    public void showProcessData(String model, String assigned_location, String vehicl_status, String ageing, String price, NewCarStock1Bean jsonObject, SharedPreferenceManager sharedPreferenceManager) {
        try {
            sharedPreferenceManager.savePreference("MODEL_NewStock", model);
            sharedPreferenceManager.savePreference("VEHICLE_STATUS_NewStock", vehicl_status);
            sharedPreferenceManager.savePreference("AGEING_NewStock", ageing);
            sharedPreferenceManager.savePreference("PRICE_NewStock", price);
            sharedPreferenceManager.savePreference("STOCK_LOCATION_NewStock", assigned_location);
        } catch (Exception e) {
        }
    }

}
