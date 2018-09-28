package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;
import com.excell.lms.lmsautovista.Utils.Utilities;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.currentPassword_EditText)
    EditText currentPassword_EditText;

    @BindView(R.id.newPassword_editText)
    EditText newPassword_editText;

    @BindView(R.id.confirmPassword_editText)
    EditText confirmPassword_editText;

    @BindView(R.id.changePassword_Button)
    Button changePassword_Button;

    @BindView(R.id.loginhere_TextView)
    TextView loginhere_TextView;

    private static final String TAG_SUCCESS = "success";

    String current_password, user_id_pref;
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
     //   SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ChangePasswordActivity.this);
        current_password= SharedPreferenceManager.getInstance(ChangePasswordActivity.this).getPreference(Constants.USER_PASSWORD, "");
        user_id_pref = SharedPreferenceManager.getInstance(ChangePasswordActivity.this).getPreference(Constants.USER_ID, "");
    }

    @Override
    public void onBackPressed()
    {
        Intent changePasswordLogin = new Intent(ChangePasswordActivity.this, LoginActivity.class);
        startActivity(changePasswordLogin);
        Toast.makeText(ChangePasswordActivity.this, "First Login with New User ID and Password", Toast.LENGTH_SHORT).show();
        //  super.onBackPressed();
    }

    @OnClick(R.id.loginhere_TextView)
    public void loginHereMethod(){
        startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.changePassword_Button)
    public void changePasswordMethod(){
        if (checkValidation())
        {
            if (checkCorrectValidation())
                new CreateChangePassword().execute();
        }
    }

    public class CreateChangePassword extends AsyncTask<String, JSONObject, JSONObject> {
        String user_id, newPassword, oldPassword;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id =user_id_pref ;
            newPassword = newPassword_editText.getText().toString();
            oldPassword = currentPassword_EditText.getText().toString();

            pDialog = new ProgressDialog(ChangePasswordActivity.this);
            pDialog.setMessage("Changing Password...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("user_id", user_id));
            params.add(new BasicNameValuePair("new_password", newPassword));
            params.add(new BasicNameValuePair("old_password", oldPassword));

            String url = Constants.BASE_URL + Constants.CHANGE_PASSWORD;
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    return json;
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Password is not Changed", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject response) {
            try {
                pDialog.dismiss();
                if (!(response == null)) {
                    Toast.makeText(ChangePasswordActivity.this, "Password Change Successfully", Toast.LENGTH_SHORT).show();
                    ClearAll();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "error ", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
            }
        }
    }

    private boolean checkCorrectValidation(){
        if (currentPassword_EditText.getText().toString().equals(current_password)) {
            return true;
        }else{
            Utilities.showToast(ChangePasswordActivity.this, "InCorrect Current Password");
            return false;
        }
    }

    private boolean checkValidation() {
        if (currentPassword_EditText.getText().toString().trim().length() > 0) {
            if (newPassword_editText.getText().toString().trim().length() > 0) {
                if (confirmPassword_editText.toString().trim().length() > 0) {
                    String newPassword = newPassword_editText.getText().toString();
                    String confirmPassword = confirmPassword_editText.getText().toString();
                    if ((newPassword.equals(confirmPassword))) {
                        return true;
                    }else {
                        Utilities.showToast(ChangePasswordActivity.this, "New Password and Confirm Password doesn't match");
                        return false;
                    }
                } else {
                    Utilities.showToast(ChangePasswordActivity.this, "Enter Confirm Password");
                    return false;
                }
            } else {
                Utilities.showToast(ChangePasswordActivity.this, "Enter New Password");
                return false;
            }
        } else {
            Utilities.showToast(ChangePasswordActivity.this, "Enter Current Password");
            return false;
        }
    }

    private void ClearAll(){
        newPassword_editText.setText("");
        currentPassword_EditText.setText("");
        confirmPassword_editText.setText("");
    }

}
