package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.SearchCustomerBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.makeText;

public class EditCustomerDetailActivity extends AppCompatActivity {

    SearchCustomerBean.Lead_Data bean;
    String enquiry_id;

    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONParser jsonParser = new JSONParser();

    //Back Button ImageView
    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    @BindView(R.id.editLeadDetailname_EditText)
    EditText editLeadDetailname_EditText;
    @BindView(R.id.editLeadDetailemail_EditText)
    EditText editLeadDetailemail_EditText;
    @BindView(R.id.editLeadDetailContactno_EditText)
    EditText editLeadDetailContactno_EditText;
    @BindView(R.id.editLeadDetailaddress_EditText)
    EditText editLeadDetailaddress_EditText;
    @BindView(R.id.editDetailSubmit_Button)
    Button editDetailSubmit_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_detail);

        ButterKnife.bind(this);

        initialiseUI();
    }

    private void initialiseUI(){

    }

    @Override
    public void onResume(){
        super.onResume();
        bean = EditCustomerDetailActivity.this.getIntent().getParcelableExtra("bean");
        enquiry_id = bean.getEnq_id();

        editLeadDetailname_EditText.setText(bean.getName());
        editLeadDetailemail_EditText.setText(bean.getEmail());
        editLeadDetailContactno_EditText.setText(bean.getContact_no());
        editLeadDetailaddress_EditText.setText(bean.getAddress());
    }


    @OnClick(R.id.editDetailSubmit_Button)
    public void editDetailsBtn(){
        new SubmitEditCustomer().execute();
    }

    private class SubmitEditCustomer extends AsyncTask<String, JSONObject, JSONObject> {
        String Name, Email, ContactNo,Address,enq_id, user_id, process_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Name = editLeadDetailname_EditText.getText().toString();
            Email = editLeadDetailemail_EditText.getText().toString();
            ContactNo = editLeadDetailContactno_EditText.getText().toString();
            Address = editLeadDetailaddress_EditText.getText().toString();
            enq_id = enquiry_id ;
            user_id =  SharedPreferenceManager.getInstance(EditCustomerDetailActivity.this).getPreference(Constants.USER_ID, "");
            process_id = SharedPreferenceManager.getInstance(EditCustomerDetailActivity.this).getPreference(Constants.PROCESS_ID, "");
            pDialog = new ProgressDialog(EditCustomerDetailActivity.this);

            pDialog.setMessage("Submitting Details...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();
            params.add(new BasicNameValuePair("name", Name));
            params.add(new BasicNameValuePair("email", Email));
            params.add(new BasicNameValuePair("contact", ContactNo));
            params.add(new BasicNameValuePair("address", Address));
            params.add(new BasicNameValuePair("enq_id", enq_id));
            params.add(new BasicNameValuePair("user_id", user_id));
            params.add(new BasicNameValuePair("process_id", process_id));

            String url_add_lead = Constants.BASE_URL + Constants.EDIT_SEARCH_OPERATION;
            JSONObject json = jsonParser.makeHttpRequest(url_add_lead, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                if (success == 1 && message.equals("Data successfully Updated.")) {
                    return json;
                }
                else {
                    return null;
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
                    makeText(EditCustomerDetailActivity.this,"Data successfully Updated.", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                else {
                    makeText(EditCustomerDetailActivity.this, "Already Updated.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void backBtn(){
        super.onBackPressed();
    }
}
