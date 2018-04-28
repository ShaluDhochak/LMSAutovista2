package com.example.user.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/23/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.CarVariantBean;
import com.example.user.lmsautovista.Model.FeedbackListBean;
import com.example.user.lmsautovista.Model.MakeBean;
import com.example.user.lmsautovista.Model.ModelBean;
import com.example.user.lmsautovista.Model.NextActionListBean;
import com.example.user.lmsautovista.Model.POCStockModel;
import com.example.user.lmsautovista.Model.ProcessBean;
import com.example.user.lmsautovista.Model.QuotationDescriptionBean;
import com.example.user.lmsautovista.Model.QuotationLocationBean;
import com.example.user.lmsautovista.Model.QuotationModelBean;
import com.example.user.lmsautovista.Model.TransferAssignToBean;
import com.example.user.lmsautovista.Model.TransferLocationBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

public class AddFollowUpPresenter implements IPresenter.IAddFollowUpPresenter{

    IView.AddFollowUpView iview;

    public AddFollowUpPresenter(IView.AddFollowUpView view){
        this.iview = view;
    }

    @Override
    public void getFeedbackList(Context context) {
        try {

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
                            if (!(res.getSelect_feedback().equals("")))
                            {
                                iview.showFeedbackSpinnerList(res);
                            } else {

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
    public void getNextActionList(Context context, String selectedFeedback) {
        try {

            Map<String, String> map = new HashMap<>();
            map.put("process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, ""));
            map.put("feedback", selectedFeedback );

            String url = Constants.BASE_URL + Constants.SELECT_NEXTACTION;
            GSONRequest<NextActionListBean> dashboardGsonRequest = new GSONRequest<NextActionListBean>(
                    Request.Method.POST,
                    url,
                    NextActionListBean.class, map,
                    new com.android.volley.Response.Listener<NextActionListBean>() {
                        @Override
                        public void onResponse(NextActionListBean res) {

                            if (!(res.getSelect_nextaction().equals("")))
                            {
                                iview.shownextActionAddFollowUpList(res);
                            } else {

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
    public void getNewCarModel(String make, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("make_id", make);

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

    @Override
    public void getNewVariant(String model, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("model_id", model);

            String url = Constants.BASE_URL + Constants.VARAINT_SPINNER;
            GSONRequest<CarVariantBean> dashboardGsonRequest = new GSONRequest<CarVariantBean>(
                    Request.Method.POST, url, CarVariantBean.class, map,
                    new com.android.volley.Response.Listener<CarVariantBean>() {
                        @Override
                        public void onResponse(CarVariantBean res) {
                            if (res.getSelect_car_variant().size()>0)
                            {
                                iview.showNewCarVariantSpinner(res);
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
    public void getQuotationLocation(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.QUOTATION_LOCATION;
            GSONRequest<QuotationLocationBean> dashboardGsonRequest = new GSONRequest<QuotationLocationBean>(
                    Request.Method.POST, url, QuotationLocationBean.class, map,
                    new com.android.volley.Response.Listener<QuotationLocationBean>() {
                        @Override
                        public void onResponse(QuotationLocationBean res) {
                            if (res.getQuotation_location().size()>0)
                            {
                                iview.showQuoatationLocSpinner(res);
                            } else {
                                iview.showQuoatationLocSpinner(res);
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
    public void getQuotationDescription(String location, String model, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("quotation_location",location);
            map.put("quotation_model_name", model);

            String url = Constants.BASE_URL + Constants.QUOATATION_DESCRIPTION;
            GSONRequest<QuotationDescriptionBean> dashboardGsonRequest = new GSONRequest<QuotationDescriptionBean>(
                    Request.Method.POST, url, QuotationDescriptionBean.class, map,
                    new com.android.volley.Response.Listener<QuotationDescriptionBean>() {
                        @Override
                        public void onResponse(QuotationDescriptionBean res) {
                            if (res.getQuotation_description().size()>0)
                            {
                                iview.showQuoatationDescriptionSpinner(res);
                            } else {
                                iview.showQuoatationDescriptionSpinner(res);
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
    public void getQuotataionModel(String location, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("quotation_location", location);

            String url = Constants.BASE_URL + Constants.QUOTATION_MODEL;
            GSONRequest<QuotationModelBean> dashboardGsonRequest = new GSONRequest<QuotationModelBean>(
                    Request.Method.POST, url, QuotationModelBean.class, map,
                    new com.android.volley.Response.Listener<QuotationModelBean>() {
                        @Override
                        public void onResponse(QuotationModelBean res) {
                            if (res.getQuotation_model_name().size()>0)
                            {
                                iview.showQuotationModelSpinner(res);
                            } else {
                                iview.showQuotationModelSpinner(res);
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
    public void getTransferProcess(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.TRANSFER_PROCESS;
            GSONRequest<ProcessBean> dashboardGsonRequest = new GSONRequest<ProcessBean>(
                    Request.Method.POST, url, ProcessBean.class, map,
                    new com.android.volley.Response.Listener<ProcessBean>() {
                        @Override
                        public void onResponse(ProcessBean res) {
                            if (res.getAll_process().size()>0)
                            {
                                iview.showTransferProcess(res);
                            } else {
                                iview.showTransferProcess(res);
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
    public void getTransferLocation(String process, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("transfer_process_id", process);

            String url = Constants.BASE_URL + Constants.TRANSFER_LOCATION;
            GSONRequest<TransferLocationBean> dashboardGsonRequest = new GSONRequest<TransferLocationBean>(
                    Request.Method.POST, url, TransferLocationBean.class, map,
                    new com.android.volley.Response.Listener<TransferLocationBean>() {
                        @Override
                        public void onResponse(TransferLocationBean res) {
                            if (res.getSelect_transfer_location().size()>0)
                            {
                                iview.showTransferLocation(res);
                            } else {
                                iview.showTransferLocation(res);
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
    public void getTransferAssignTo(String process, String location, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("transfer_process_id", process);
            map.put("transfer_location_id", location);
            map.put("role", SharedPreferenceManager.getInstance(context).getPreference(Constants.ROLE_ID, ""));
            map.put("user_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.USER_ID, "") );
            map.put("session_process_id", SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, "") );

            String url = Constants.BASE_URL + Constants.TRANSFER_ASSIGN_TO;
            GSONRequest<TransferAssignToBean> dashboardGsonRequest = new GSONRequest<TransferAssignToBean>(
                    Request.Method.POST, url, TransferAssignToBean.class, map,
                    new com.android.volley.Response.Listener<TransferAssignToBean>() {
                        @Override
                        public void onResponse(TransferAssignToBean res) {
                            if (res.getSelect_transfer_to_user().size()>0)
                            {
                                iview.showTransferAssignTo(res);
                            } else {
                                iview.showTransferAssignTo(res);
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
    public void getUsedcarMake(Context context) {
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
                                iview.showCarMake(res);
                            } else {
                                iview.showCarMake(res);
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
    public void getUsedCarModel(String make_id,Context context) {
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
                                iview.showCarModel(res);
                            } else {
                                iview.showCarModel(res);
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
    public void getOldCarMake(Context context) {
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
                                iview.showOldCarMake(res);
                            } else {
                                iview.showOldCarMake(res);
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
    public void getOldCarModel(String make_id, Context context) {
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
                                iview.showOldCarModel(res);
                            } else {
                                iview.showOldCarModel(res);
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
