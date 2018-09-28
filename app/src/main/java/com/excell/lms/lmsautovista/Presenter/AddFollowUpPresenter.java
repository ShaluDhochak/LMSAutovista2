package com.excell.lms.lmsautovista.Presenter;

/*
  Created by Shalu Dhochak on 4/23/2018.
*/

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.CarVariantBean;
import com.excell.lms.lmsautovista.Model.CorporateFinancialBean;
import com.excell.lms.lmsautovista.Model.FeedbackListBean;
import com.excell.lms.lmsautovista.Model.MakeBean;
import com.excell.lms.lmsautovista.Model.ModelBean;
import com.excell.lms.lmsautovista.Model.NextActionListBean;
import com.excell.lms.lmsautovista.Model.ProcessBean;
import com.excell.lms.lmsautovista.Model.QuotationDescriptionBean;
import com.excell.lms.lmsautovista.Model.QuotationLocationBean;
import com.excell.lms.lmsautovista.Model.QuotationModelBean;
import com.excell.lms.lmsautovista.Model.QuotationPackageBean;
import com.excell.lms.lmsautovista.Model.TransferAssignToBean;
import com.excell.lms.lmsautovista.Model.TransferLocationBean;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;

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
                                iview.shownextActionAddFollowUpList(res);
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
            map.put("qlocation",location);
            map.put("model_id", model);

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
    public void getQuotationAccessoriesPackage(String model, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("model_id", model);

            String url = Constants.BASE_URL + Constants.QUOTATION_ACCESSORIES_PACKAGE;
            GSONRequest<QuotationPackageBean> dashboardGsonRequest = new GSONRequest<QuotationPackageBean>(
                    Request.Method.POST, url, QuotationPackageBean.class, map,
                    new com.android.volley.Response.Listener<QuotationPackageBean>() {
                        @Override
                        public void onResponse(QuotationPackageBean res) {
                            if (res.getAccessories_package().size()>0)
                            {
                                iview.showQuotationAccessoriesPackageSpinner(res);
                            } else {
                                iview.showQuotationAccessoriesPackageSpinner(res);
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
            map.put("qlocation", location);

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

            String url = Constants.BASE_URL + Constants.MODEL_SPINNER;
            GSONRequest<ModelBean> dashboardGsonRequest = new GSONRequest<ModelBean>(
                    Request.Method.POST, url, ModelBean.class, map,
                    new com.android.volley.Response.Listener<ModelBean>() {
                        @Override
                        public void onResponse(ModelBean res) {
                            if (res.getSelect_car_model().size()>0)
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

            String url = Constants.BASE_URL + Constants.MODEL_SPINNER;
            GSONRequest<ModelBean> dashboardGsonRequest = new GSONRequest<ModelBean>(
                    Request.Method.POST, url, ModelBean.class, map,
                    new com.android.volley.Response.Listener<ModelBean>() {
                        @Override
                        public void onResponse(ModelBean res) {
                            if (res.getSelect_car_model().size()>0)
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

    @Override
    public void getCorporateName(Context context) {
        try {
            Map<String, String> map = new HashMap<>();

            String url = Constants.BASE_URL + Constants.CORPORATE_NAME;
            GSONRequest<CorporateFinancialBean> dashboardGsonRequest = new GSONRequest<CorporateFinancialBean>(
                    Request.Method.POST, url, CorporateFinancialBean.class, map,
                    new com.android.volley.Response.Listener<CorporateFinancialBean>() {
                        @Override
                        public void onResponse(CorporateFinancialBean res) {
                            if (res.getCustomer_corporate_name().size()>0)
                            {
                                iview.showCustomerCorporateName(res);
                            } else {
                                iview.showCustomerCorporateName(res);
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
    public void getEvaluationLocation(String process, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("transfer_process_id", "8");

            String url = Constants.BASE_URL + Constants.TRANSFER_LOCATION;
            GSONRequest<TransferLocationBean> dashboardGsonRequest = new GSONRequest<TransferLocationBean>(
                    Request.Method.POST, url, TransferLocationBean.class, map,
                    new com.android.volley.Response.Listener<TransferLocationBean>() {
                        @Override
                        public void onResponse(TransferLocationBean res) {
                            if (res.getSelect_transfer_location().size()>0)
                            {
                                iview.showEvaluationLocation(res);
                            } else {
                                iview.showEvaluationLocation(res);
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
    public void getEvaluationAssignTo(String process, String location, Context context) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("transfer_process_id", "8");
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
                                iview.showEvaluationAssignTo(res);
                            } else {
                                iview.showEvaluationAssignTo(res);
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
