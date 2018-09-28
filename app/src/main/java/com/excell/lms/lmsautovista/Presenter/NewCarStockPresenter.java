package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/20/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Model.ModelBean;
import com.excell.lms.lmsautovista.Model.NewCarStockBean;
import com.excell.lms.lmsautovista.Model.NewStockFilterBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class NewCarStockPresenter implements IPresenter.INewCarStockViewPresenter{

    IView.NewCarStockView iview;

    public NewCarStockPresenter(IView.NewCarStockView view){
        this.iview = view;
    }

    @Override
    public void getnewCarStockList(String model, String location, String color, String fuelType, final Context context) {
        try {

            Map<String, String> map = new HashMap<>();

            if (model.equals("Model")){
                map.put("model_id", "");
            }else{
                map.put("model_id", model);
            }

            if (location.equals("Location")){
                map.put("location", "");
            }else{
                map.put("location", location);
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

            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK;
            GSONRequest<NewCarStockBean> dashboardGsonRequest = new GSONRequest<NewCarStockBean>(
                    Request.Method.POST,
                    url,
                    NewCarStockBean.class, map,
                    new com.android.volley.Response.Listener<NewCarStockBean>() {
                        @Override
                        public void onResponse(NewCarStockBean res) {
                            if (res.getNew_car_stock().size()>0) {
                                try{
                                    iview.showNewCarStockList(res);
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
    public void getNewCarStockList(final Context context) {
        try {

            Map<String, String> map = new HashMap<>();
            String url = Constants.BASE_URL + Constants.NEW_CAR_STOCK;
            GSONRequest<NewCarStockBean> dashboardGsonRequest = new GSONRequest<NewCarStockBean>(
                    Request.Method.POST,
                    url,
                    NewCarStockBean.class, map,
                    new com.android.volley.Response.Listener<NewCarStockBean>() {
                        @Override
                        public void onResponse(NewCarStockBean res) {
                            if (res.getNew_car_stock().size()>0) {
                                try{
                                    iview.showNewCarStockList(res);
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

            String url = Constants.BASE_URL + Constants.STOCK_FILTER;
            GSONRequest<NewStockFilterBean> dashboardGsonRequest = new GSONRequest<NewStockFilterBean>(
                    Request.Method.POST,
                    url,
                    NewStockFilterBean.class, map,
                    new com.android.volley.Response.Listener<NewStockFilterBean>() {
                        @Override
                        public void onResponse(NewStockFilterBean res) {
                            if (res.getNew_stock_location().size()>0)
                            {
                                iview.showNewCarLocationSpinner(res);

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

            String url = Constants.BASE_URL + Constants.STOCK_FILTER;
            GSONRequest<NewStockFilterBean> dashboardGsonRequest = new GSONRequest<NewStockFilterBean>(
                    Request.Method.POST, url, NewStockFilterBean.class, map,
                    new com.android.volley.Response.Listener<NewStockFilterBean>() {
                        @Override
                        public void onResponse(NewStockFilterBean res) {
                            if (res.getNew_stock_fuel_type().size()>0)
                            {
                                iview.showNewCarFuelTypeSpinner(res);
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

            String url = Constants.BASE_URL + Constants.STOCK_FILTER;
            GSONRequest<NewStockFilterBean> dashboardGsonRequest = new GSONRequest<NewStockFilterBean>(
                    Request.Method.POST, url, NewStockFilterBean.class, map,
                    new com.android.volley.Response.Listener<NewStockFilterBean>() {
                        @Override
                        public void onResponse(NewStockFilterBean res) {
                            if (res.getNew_stock_color().size()>0)
                            {
                                iview.showNewCarColorSpinner(res);
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
    public void getModel(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.MODEL_SPINNER;
            GSONRequest<ModelBean> dashboardGsonRequest = new GSONRequest<ModelBean>(
                    Request.Method.POST, url, ModelBean.class, map,
                    new com.android.volley.Response.Listener<ModelBean>() {
                        @Override
                        public void onResponse(ModelBean res) {
                            if (res.getSelect_car_model().size()>0)
                            {
                                iview.shownewCarModelSpinner(res);
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
