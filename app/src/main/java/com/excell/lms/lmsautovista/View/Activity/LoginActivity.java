package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.DashboardCountBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Presenter.LoginPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.Utils.SessionManagement;
import com.excell.lms.lmsautovista.Utils.StringUtils;
import com.excell.lms.lmsautovista.View.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IView.LoginView{

    @BindView(R.id.userId_et)
    EditText userId_et;
    @BindView(R.id.password_et)
    EditText password_et;
    @BindView(R.id.login_Btn)
    Button login_Btn;

    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);

        session = new SessionManagement(getApplicationContext());   // Session Manager
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure want to Exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                    }
                }).setNegativeButton("no", null).show();
    }

    @OnClick(R.id.login_Btn)
    public void login(){
        String name = userId_et.getText().toString();
        String password = password_et.getText().toString();

        if(userId_et.getText().toString().trim().length() > 0 && password_et.getText().toString().trim().length() > 0)
        {
            if(StringUtils.isEmailValid(userId_et.getText().toString())) {
                if (NetworkUtilities.isInternet(this)) {
                    loginPresenter.checkLogin(userId_et.getText().toString(), password_et.getText().toString(), this);
                    session.createLoginSession(name, password);
                } else
                    StringUtils.internetError(this);
            }else{
                Toast.makeText(this,"Enter valid Email",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Email Id or Password cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, NavigationDrawer.class));
        finish();
    }

    @Override
    public void loginFailure(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLocationList(LoginBean jsonObject) {

    }

    @Override
    public void ShowDashboardCount(DashboardCountBean jsonObject) {

    }

    @Override
    public void showLocationDashboard(LocationDashboardBean jsonObject) {

    }

    @Override
    public void showProcessDashboard(LoginBean jsonObject) {

    }

    @Override
    public void showLocationDashboardCount(LocationWiseDashboardCountBean jsonObject) {

    }

    @Override
    public void showLocationwithoutSeelctionCount(LocationWiseDashboardCountBean jsonObject) {

    }
}
