package com.example.user.lmsautovista.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.LoginBean;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2/17/2018.
 */

public class LoginPresenter implements IPresenter.ILoginPresenter{

    IView.LoginView view;
    String role, status;

    public LoginPresenter(IView.LoginView loginView) {
        this.view = loginView;
    }

    @Override
    public void checkLogin(final String emailId, final String password, final Context context) {
        try {
            view.showProgressDialog();

           /* final JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", "admin@autovista.in");
            jsonObject.put("password", "admin@1323");
           */

            Map<String, String> map = new HashMap<>();
            map.put("username", emailId);
            map.put("password", password);

            String url = Constants.BASE_URL + Constants.LOGIN_URL;
            GSONRequest<LoginBean> loginGsonRequest = new GSONRequest<LoginBean>(
                    Request.Method.POST,
                    url,
                    LoginBean.class, map,
                    new com.android.volley.Response.Listener<LoginBean>() {
                        @Override
                        public void onResponse(LoginBean res) {
                            view.dismissProgressDialog();
                            if (res.isSuccess()==1)
                            {
                                try {
                                    saveUserInfo(emailId,password,res, SharedPreferenceManager.getInstance(context));
                                } catch (Exception e) {
                                    Toast.makeText(context, "njf", Toast.LENGTH_SHORT).show();
                                }
                            view.dismissProgressDialog();
                            view.loginSuccess();
                            } else {
                                view.dismissProgressDialog();
                                view.loginFailure("Unable to ");
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
            loginGsonRequest.setShouldCache(false);
            Utilities.getRequestQueue(context).add(loginGsonRequest);
        } catch (Exception e) {
            view.dismissProgressDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUserInfo(String email, String password, LoginBean jsonObject, SharedPreferenceManager sharedPreferenceManager) {
        try {
            sharedPreferenceManager.savePreference(Constants.USER_EMAIL, email);
            sharedPreferenceManager.savePreference(Constants.USER_PASSWORD, password);

            sharedPreferenceManager.savePreference(Constants.LOCATION_SESSION, jsonObject.getUser_detail().get(0).getLocation_id());
            sharedPreferenceManager.savePreference(Constants.USER_NAME, jsonObject.getSession_data().get(0).getUsername());
            sharedPreferenceManager.savePreference(Constants.PROCESS_ID_initial, jsonObject.getUser_detail().get(0).getProcess_id());
            sharedPreferenceManager.savePreference(Constants.PROCESS_NAME_INITIAL, jsonObject.getUser_detail().get(0).getProcess_name());
            sharedPreferenceManager.savePreference(Constants.USER_ID, jsonObject.getUser_detail().get(0).getId());
            sharedPreferenceManager.savePreference(Constants.ROLE_ID, jsonObject.getUser_detail().get(0).getRole());

            //sharedPreference for Rights

            for(int i = 0; i< jsonObject.getRights().size();i++){
                if (jsonObject.getRights().get(i).getProcess_id().equals("0")){
                    if (jsonObject.getRights().get(i).getForm_name().equals("Add Location")){
                        sharedPreferenceManager.savePreference("add_location_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Calling Notification")){
                        sharedPreferenceManager.savePreference("calling_notification_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Dashboard")){
                        sharedPreferenceManager.savePreference("dashboard_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Search Customer")){
                        sharedPreferenceManager.savePreference("search_customer_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Edit Customer")){
                        sharedPreferenceManager.savePreference("edit_customer_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("daily_report")){
                        sharedPreferenceManager.savePreference("daily_report_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("monthly_report")){
                        sharedPreferenceManager.savePreference("monthly_report_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("dse_report")){
                        sharedPreferenceManager.savePreference("dse_report_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("lead_report")){
                        sharedPreferenceManager.savePreference("lead_report_view", jsonObject.getRights().get(i).getView());
                    }

                }else if (jsonObject.getRights().get(i).getProcess_id().equals("1")){

                    if (jsonObject.getRights().get(i).getForm_name().equals("Download Lead Tracker")){
                        sharedPreferenceManager.savePreference("download_lead_tracker_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Tracker")){
                        sharedPreferenceManager.savePreference("tracker_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Manager Remark")){
                        sharedPreferenceManager.savePreference("add_manager_remark_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Follow Up")){
                        sharedPreferenceManager.savePreference("add_followUp_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Transfer Lead")){
                        sharedPreferenceManager.savePreference("transfer_lead_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign transferred leads")){
                        sharedPreferenceManager.savePreference("assign_transferred_lead_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign Lead ")){
                        sharedPreferenceManager.savePreference("assign_lead_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add New Lead")){
                        sharedPreferenceManager.savePreference("add_new_lead_finance_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign transferred leads")){
                        sharedPreferenceManager.savePreference("assign_transferred_lead_finance_view", jsonObject.getRights().get(i).getView());
                    }


                }else if (jsonObject.getRights().get(i).getProcess_id().equals("4")){

                    if (jsonObject.getRights().get(i).getForm_name().equals("Download Lead Tracker")){
                        sharedPreferenceManager.savePreference("download_lead_tracker_service_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Tracker")){
                        sharedPreferenceManager.savePreference("tracker_service_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Manager Remark")){
                        sharedPreferenceManager.savePreference("add_manager_remark_service_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Follow Up")){
                        sharedPreferenceManager.savePreference("add_followup_service_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Transfer Lead")){
                        sharedPreferenceManager.savePreference("transfer_lead_service_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign transferred leads")){
                        sharedPreferenceManager.savePreference("assign_transfer_lead_service_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign Lead ")){
                        sharedPreferenceManager.savePreference("assign_lead_service_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add New Lead")){
                        sharedPreferenceManager.savePreference("add_new_lead_service_view", jsonObject.getRights().get(i).getView());
                    }

                }else if(jsonObject.getRights().get(i).getProcess_id().equals("5")){

                    if (jsonObject.getRights().get(i).getForm_name().equals("Download Lead Tracker")){
                        sharedPreferenceManager.savePreference("download_lead_tracker_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Tracker")){
                        sharedPreferenceManager.savePreference("tracker_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Manager Remark")){
                        sharedPreferenceManager.savePreference("add_manager_remark_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Follow Up")){
                        sharedPreferenceManager.savePreference("add_followup_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Follow Up")){
                        sharedPreferenceManager.savePreference("add_followup_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Transfer Lead")){
                        sharedPreferenceManager.savePreference("transfer_lead_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign transferred leads")){
                        sharedPreferenceManager.savePreference("assign_transferres_lead_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign Lead ")){
                        sharedPreferenceManager.savePreference("assign_lead_acc_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add New Lead")){
                        sharedPreferenceManager.savePreference("add_new_lead_acc_view", jsonObject.getRights().get(i).getView());
                    }

                }else if (jsonObject.getRights().get(i).getProcess_id().equals("6")){

                    if (jsonObject.getRights().get(i).getForm_name().equals("Add New Lead")){
                        sharedPreferenceManager.savePreference("add_new_lead_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign Lead ")){
                        sharedPreferenceManager.savePreference("assign_lead_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Follow Up")){
                        sharedPreferenceManager.savePreference("add_followup_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Manager Remark")){
                        sharedPreferenceManager.savePreference("add_manager_remark_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Transfer Lead")){
                        sharedPreferenceManager.savePreference("transfer_lead_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Tracker")){
                        sharedPreferenceManager.savePreference("tracker_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign transferred leads")){
                        sharedPreferenceManager.savePreference("assign_transfer_lead_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Download Lead Tracker")){
                        sharedPreferenceManager.savePreference("download_lead_tracker_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Create Quotation Format")){
                        sharedPreferenceManager.savePreference("create_quotation_format_newcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Upload Quotaion")){
                        sharedPreferenceManager.savePreference("upload_quotation_newcar_view", jsonObject.getRights().get(i).getView());
                    }

                }else if (jsonObject.getRights().get(i).getProcess_id().equals("7")){
                    if (jsonObject.getRights().get(i).getForm_name().equals("Check Stock")){
                        sharedPreferenceManager.savePreference("check_stock_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Upload Stock")){
                        sharedPreferenceManager.savePreference("upload_stock_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Download Lead Tracker")){
                        sharedPreferenceManager.savePreference("download_lead_tracker_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Tracker")){
                        sharedPreferenceManager.savePreference("tracker_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Manager Remark")){
                        sharedPreferenceManager.savePreference("add_manger_remark_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add Follow Up")){
                        sharedPreferenceManager.savePreference("add_followup_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Transfer Lead")){
                        sharedPreferenceManager.savePreference("transfer_lead_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign transferred leads")){
                        sharedPreferenceManager.savePreference("assign_tracferred_lead_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Assign Lead ")){
                        sharedPreferenceManager.savePreference("assign_lead_usedcar_view", jsonObject.getRights().get(i).getView());
                    }else if (jsonObject.getRights().get(i).getForm_name().equals("Add New Lead")){
                        sharedPreferenceManager.savePreference("add_new_lead_usedcar_view", jsonObject.getRights().get(i).getView());
                    }

                }
            }


        } catch (Exception e) {
        }
    }

    @Override
    public void saveProcessInfo(String process_id, String process_name, SharedPreferenceManager sharedPreferenceManager) {
        try {
            sharedPreferenceManager.savePreference(Constants.PROCESS_ID, process_id);
            sharedPreferenceManager.savePreference(Constants.PROCESS_NAME, process_name);

        } catch (Exception e) {
        }
    }

    @Override
    public void saveLocationInfo(String location_id, String location_name, SharedPreferenceManager sharedPreferenceManager) {
        try {
            sharedPreferenceManager.savePreference(Constants.LOCATION_ID, location_id);
            sharedPreferenceManager.savePreference(Constants.LOCATION_NAME, location_name);

        } catch (Exception e) {
        }
    }


}
