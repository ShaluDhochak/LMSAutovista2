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

public class LoginPresenter  implements IPresenter.ILoginPresenter{

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

            sharedPreferenceManager.savePreference(Constants.LOCATION, jsonObject.getUser_detail().get(0).getLocation());
            sharedPreferenceManager.savePreference(Constants.USER_NAME, jsonObject.getSession_data().get(0).getUsername());
            sharedPreferenceManager.savePreference(Constants.PROCESS_ID_initial, jsonObject.getUser_detail().get(0).getProcess_id());
            sharedPreferenceManager.savePreference(Constants.PROCESS_NAME_INITIAL, jsonObject.getUser_detail().get(0).getProcess_name());
            sharedPreferenceManager.savePreference(Constants.USER_ID, jsonObject.getUser_detail().get(0).getId());
            sharedPreferenceManager.savePreference(Constants.ROLE_ID, jsonObject.getUser_detail().get(0).getRole());
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


}
