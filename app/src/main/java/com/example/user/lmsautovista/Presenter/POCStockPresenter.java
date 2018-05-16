package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/21/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Model.MakeBean;
import com.example.user.lmsautovista.Model.POCSpinnerBean;
import com.example.user.lmsautovista.Model.POCStockModel;
import com.example.user.lmsautovista.Model.POCarStockBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class POCStockPresenter implements IPresenter.IPOCCarStockViewPresenter{

    IView.PocCarStockView iview;

    public POCStockPresenter(IView.PocCarStockView view){
        this.iview = view;
    }

    @Override
    public void getPocCarStockList(String make,String model, String location, String color, String fuelType, final Context context) {
        try {

            Map<String, String> map = new HashMap<>();

            if (make.equals("Make")){
                map.put("make_id", "");
            }else
                map.put("make_id", make);

            if (model.equals("Model")){
                map.put("model", "");
            }else{
                map.put("model", model);
            }

            if (location.equals("Location")){
                map.put("stock_location", "");
            }else{
                map.put("stock_location", location);
            }

            if (fuelType.equals("Fuel Type")){
                map.put("fuel_type", "");
            }else{
                map.put("fuel_type", fuelType);
            }

            if (color.equals("Color")){
                map.put("color", "");
            }else{
                map.put("color", color);
            }

            map.put("ageing","");
            map.put("mfg_yr","" );

            String url = Constants.BASE_URL + Constants.POC_CAR_STOCK;
            GSONRequest<POCarStockBean> dashboardGsonRequest = new GSONRequest<POCarStockBean>(
                    Request.Method.POST,
                    url,
                    POCarStockBean.class, map,
                    new com.android.volley.Response.Listener<POCarStockBean>() {
                        @Override
                        public void onResponse(POCarStockBean res) {
                            if (res.getPoc_stock().size()>0) {
                                try{
                                    iview.showPOCCarStockList(res);
                                } catch (Exception e) {
                                }
                            }else{
                                iview.showPOCCarStockList(res);
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
    public void getPocCarStockList(final Context context) {
        try {

            Map<String, String> map = new HashMap<>();
            String url = Constants.BASE_URL + Constants.POC_CAR_STOCK;
            GSONRequest<POCarStockBean> dashboardGsonRequest = new GSONRequest<POCarStockBean>(
                    Request.Method.POST,
                    url,
                    POCarStockBean.class, map,
                    new com.android.volley.Response.Listener<POCarStockBean>() {
                        @Override
                        public void onResponse(POCarStockBean res) {
                            if (res.getPoc_stock().size()>0) {
                                try{
                                    iview.showPOCCarStockList(res);
                                } catch (Exception e) {
                                }
                            }else{
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
    public void getLocation(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.POC_STOCK_SPINNER;
            GSONRequest<POCSpinnerBean> dashboardGsonRequest = new GSONRequest<POCSpinnerBean>(
                    Request.Method.POST,
                    url,
                    POCSpinnerBean.class, map,
                    new com.android.volley.Response.Listener<POCSpinnerBean>() {
                        @Override
                        public void onResponse(POCSpinnerBean res) {
                            if (res.getPoc_stock_location().size()>0)
                            {
                                iview.showPOCCarLocationSpinner(res);
                            } else {
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
    public void getFuelType(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.POC_STOCK_SPINNER;
            GSONRequest<POCSpinnerBean> dashboardGsonRequest = new GSONRequest<POCSpinnerBean>(
                    Request.Method.POST, url, POCSpinnerBean.class, map,
                    new com.android.volley.Response.Listener<POCSpinnerBean>() {
                        @Override
                        public void onResponse(POCSpinnerBean res) {
                            if (res.getPoc_stock_fuel_type().size()>0)
                            {
                                iview.showPOCCarFuelTypeSpinner(res);
                            } else {
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
    public void getColor(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.POC_STOCK_SPINNER;
            GSONRequest<POCSpinnerBean> dashboardGsonRequest = new GSONRequest<POCSpinnerBean>(
                    Request.Method.POST, url, POCSpinnerBean.class, map,
                    new com.android.volley.Response.Listener<POCSpinnerBean>() {
                        @Override
                        public void onResponse(POCSpinnerBean res) {
                            if (res.getPoc_stock_color().size()>0)
                            {
                                iview.showPOCCarColorSpinner(res);
                            } else {
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
    public void getModel(String make_id,Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("make_id", make_id);

            String url = Constants.BASE_URL + Constants.POC_CAR_MODEL;
            GSONRequest<POCStockModel> dashboardGsonRequest = new GSONRequest<POCStockModel>(
                    Request.Method.POST, url, POCStockModel.class, map,
                    new com.android.volley.Response.Listener<POCStockModel>() {
                        @Override
                        public void onResponse(POCStockModel res) {
                            if (res.getPoc_stock_color().size()>0)
                            {
                                iview.showPOCCarModelSpinner(res);
                            } else {
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
    public void getMake(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.MAKE_SPINNER;
            GSONRequest<MakeBean> dashboardGsonRequest = new GSONRequest<MakeBean>(
                    Request.Method.POST, url, MakeBean.class, map,
                    new com.android.volley.Response.Listener<MakeBean>() {
                        @Override
                        public void onResponse(MakeBean res) {
                            if (res.getSelect_car_make().size()>0)
                            {
                                iview.showPocMakeSpinner(res);
                            } else {
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
}
