package com.example.user.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Model.LocationDashboardBean;
import com.example.user.lmsautovista.Model.LoginBean;
import com.example.user.lmsautovista.Presenter.LoginPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.NetworkUtilities;
import com.example.user.lmsautovista.Utils.SessionManagement;
import com.example.user.lmsautovista.Utils.StringUtils;
import com.example.user.lmsautovista.View.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IView.LoginView{

    SessionManagement session;

    @BindView(R.id.userId_et)
    EditText userId_et;

    @BindView(R.id.password_et)
    EditText password_et;

    @BindView(R.id.login_Btn)
    Button login_Btn;

    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        // Session Manager
        session = new SessionManagement(getApplicationContext());

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
                    session.createLoginSession(name, password);
                    loginPresenter.checkLogin(userId_et.getText().toString(), password_et.getText().toString(), this);
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
        /*  Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("process_id");
        intent.putExtra("bean", dashboardCount.get(position));
        startActivity(intent);
        */
        startActivity(new Intent(this, HomeActivity.class));
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


}
