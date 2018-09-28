package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/21/2018.
*/

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.MakeBean;
import com.excell.lms.lmsautovista.Model.POCCarStockCountBean;
import com.excell.lms.lmsautovista.Model.POCSpinnerBean;
import com.excell.lms.lmsautovista.Model.POCStockModel;
import com.excell.lms.lmsautovista.Model.POCarStockBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

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
    public void getPocCarStockCount(final String make,final String model,final String stock_location, final String mfg_year,final String owner,final String ageing,final String price,final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            if (make.equals("Make")){
                map.put("make", "");
            }else
                map.put("make", make);

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

            String url = Constants.BASE_URL + Constants.POC_CAR_STOCK_COUNT;
            GSONRequest<POCCarStockCountBean> dashboardGsonRequest = new GSONRequest<POCCarStockCountBean>(
                    Request.Method.POST,
                    url,
                    POCCarStockCountBean.class, map,
                    new com.android.volley.Response.Listener<POCCarStockCountBean>() {
                        @Override
                        public void onResponse(POCCarStockCountBean res) {
                            try {
                                if (res.getPoc_stock_count().size() > 0) {
                                    try {
                                        iview.showPocCarStockCount(res);
                                        saveProcessInfo(make,model,stock_location,mfg_year,owner,ageing,price,res, SharedPreferenceManager.getInstance(context));
                                    } catch (Exception e) {
                                    }
                                } else {
                                    iview.showPocCarStockCount(res);
                                    Toast.makeText(context, "No Record Found", Toast.LENGTH_SHORT).show();
                                }
                            }catch(Exception e){

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
    public void getPocCarStockFirstCount(final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.POC_CAR_STOCK_COUNT;
            GSONRequest<POCCarStockCountBean> dashboardGsonRequest = new GSONRequest<POCCarStockCountBean>(
                    Request.Method.POST,
                    url,
                    POCCarStockCountBean.class, map,
                    new com.android.volley.Response.Listener<POCCarStockCountBean>() {
                        @Override
                        public void onResponse(POCCarStockCountBean res) {
                            try {
                                if (res.getPoc_stock_count().size() > 0) {
                                    try {
                                        iview.showPocCarStockCount(res);
                                    } catch (Exception e) {
                                    }
                                } else {
                                    iview.showPocCarStockCount(res);
                                    Toast.makeText(context, "No Record Found", Toast.LENGTH_SHORT).show();
                                }
                            }catch(Exception e){

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
                            try {
                                if (res.getPoc_stock().size() > 0) {
                                    try {
                                        iview.showPOCCarStockList(res);
                                    } catch (Exception e) {
                                    }
                                } else {
                                    iview.showPOCCarStockList(res);
                                }
                            }catch(Exception e){

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
    public void getLocation(final Context context) {
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
                            try {
                                if (res.getPoc_stock_location().size() > 0) {
                                    iview.showPOCCarLocationSpinner(res);
                                } else {
                                    iview.showPOCCarLocationSpinner(res);
                                    //view.("Unable to ");
                                }
                            }catch(Exception e){

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
    public void getFuelType(final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.POC_STOCK_SPINNER;
            GSONRequest<POCSpinnerBean> dashboardGsonRequest = new GSONRequest<POCSpinnerBean>(
                    Request.Method.POST, url, POCSpinnerBean.class, map,
                    new com.android.volley.Response.Listener<POCSpinnerBean>() {
                        @Override
                        public void onResponse(POCSpinnerBean res) {
                            try {
                                if (res.getPoc_stock_fuel_type().size() > 0) {
                                    iview.showPOCCarFuelTypeSpinner(res);
                                } else {
                                    iview.showPOCCarFuelTypeSpinner(res);
                                    //view.("Unable to ");
                                }
                            }catch(Exception e){

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
    public void getColor(final Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.POC_STOCK_SPINNER;
            GSONRequest<POCSpinnerBean> dashboardGsonRequest = new GSONRequest<POCSpinnerBean>(
                    Request.Method.POST, url, POCSpinnerBean.class, map,
                    new com.android.volley.Response.Listener<POCSpinnerBean>() {
                        @Override
                        public void onResponse(POCSpinnerBean res) {
                            try {
                                if (res.getPoc_stock_color().size() > 0) {
                                    iview.showPOCCarColorSpinner(res);
                                } else {
                                    iview.showPOCCarColorSpinner(res);
                                    //view.("Unable to ");
                                }
                            }catch(Exception e){

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
                            try {
                                if (res.getPoc_stock_color().size() > 0) {
                                    iview.showPOCCarModelSpinner(res);
                                } else {
                                    iview.showPOCCarModelSpinner(res);
                                    //view.("Unable to ");
                                }
                            }catch(Exception e){

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
                            try {
                                if (res.getSelect_car_make().size() > 0) {
                                    iview.showPocMakeSpinner(res);
                                } else {
                                    iview.showPocMakeSpinner(res);
                                    //view.("Unable to ");
                                }
                            }catch(Exception e){

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
    public void saveProcessInfo(String make, String model, String stock_location, String mfg_year, String owner, String ageing, String price,POCCarStockCountBean jsonObject, SharedPreferenceManager sharedPreferenceManager) {
        try {
            sharedPreferenceManager.savePreference("MAKE_POC", make);
            sharedPreferenceManager.savePreference("MODEL_POC", model);
            sharedPreferenceManager.savePreference("MFGYR_POC", mfg_year);
            sharedPreferenceManager.savePreference("OWNER_POC", owner);
            sharedPreferenceManager.savePreference("AGEING_POC", ageing);
            sharedPreferenceManager.savePreference("PRICE_POC", price);
            sharedPreferenceManager.savePreference("STOCK_LOCATION_POC", stock_location);
        } catch (Exception e) {
        }
    }
}
