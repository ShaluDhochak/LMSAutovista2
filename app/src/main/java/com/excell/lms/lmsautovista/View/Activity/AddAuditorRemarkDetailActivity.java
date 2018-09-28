package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;
import com.github.clans.fab.FloatingActionButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddAuditorRemarkDetailActivity extends AppCompatActivity {

    CallingTaskNewLeadBean.Lead_Details beanList;

    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final int REQUEST_PHONE_CALL = 1;

    //shared PREFERENCE
    SharedPreferenceManager preferenceManager;
    String process_shedPref;
    JSONParser jsonParser = new JSONParser();

    //Floating action buttons
    @BindView(R.id.customerAuditorDetails_fab)
    FloatingActionButton customerAuditorDetails_fab;

    @BindView(R.id.addFollowUpAuditorDetail_fab)
    FloatingActionButton addFollowUpAuditorDetail_fab;

    @BindView(R.id.followUpDetailAuditor_fab)
    FloatingActionButton followUpDetailAuditor_fab;

    @BindView(R.id.auditorDetailAuditorDetail_fab)
    FloatingActionButton auditorDetailAuditorDetail_fab;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    @BindView(R.id.serviceFeedbackAuditorDetails_Spinner)
    Spinner serviceFeedbackAuditorDetails_Spinner;

    @BindView(R.id.remarksAuditorDetails_EditText)
    EditText remarksAuditorDetails_EditText;

    @BindView(R.id.fakeUpdationAuditorDetails_Spinner)
    Spinner fakeUpdationAuditorDetails_Spinner;

    @BindView(R.id.followupPendingAuditorDetails_Spinner)
    Spinner followupPendingAuditorDetails_Spinner;

    @BindView(R.id.callReceivedShowroomAuditorDetails_Spinner)
    Spinner callReceivedShowroomAuditorDetails_Spinner;

    @BindView(R.id.addAuditorName_TextView)
    TextView addAuditorName_TextView;

    @BindView(R.id.addAuditorContactNo_TextView)
    TextView addAuditorContactNo_TextView;

    @BindView(R.id.addAuditorBookingId_TextView)
    TextView addAuditorBookingId_TextView;

    @BindView(R.id.submitAuditorDetails_Button)
    Button submitAuditorDetails_Button;
    
    String followUpPendingString, followUpPendingStringId, callReceivedString, callReceivedStringId, fakeString, fakeStringId;
    String serviceFeedbackString, serviceFeedbackStringId;
    String enquiryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor_remark_detail);

        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialiseUI();
    }

    @OnClick(R.id.followUpDetailAuditor_fab)
    public void followUpDetailAuditor(){
        Intent followuPdetailIntent = new Intent(this, FollowUpDetailsActivity.class);
        followuPdetailIntent.putExtra("bean",beanList);
        followuPdetailIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(followuPdetailIntent);
    }

    @OnClick(R.id.addFollowUpAuditorDetail_fab)
    public void addFollowUpAuditor(){
        Intent addFollowUpIntent = new Intent(this, AddFollowUpActivity.class);
        addFollowUpIntent.putExtra("bean",beanList);
        addFollowUpIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(addFollowUpIntent);
    }

    @OnClick(R.id.customerAuditorDetails_fab)
    public void customerDetails(){
        Intent addFollowUpIntent = new Intent(this, CustomerDetailsActivity.class);
        addFollowUpIntent.putExtra("bean",beanList);
        addFollowUpIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(addFollowUpIntent);
    }

    @OnClick(R.id.auditorDetailAuditorDetail_fab)
    public void auditorDetailAuditor(){
        Intent auditorDetailsIntent = new Intent(this, AuditorDetailsActivity.class);
        auditorDetailsIntent.putExtra("bean",beanList);
        auditorDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(auditorDetailsIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void onBackBtnCustomer(){
        onBackPressed();
    }

    private void initialiseUI() {
        process_shedPref = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "");

        beanList = getIntent().getParcelableExtra("bean");

        if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            enquiryId = beanList.getComplaint_id().toString();
            addAuditorName_TextView.setText(beanList.getName());
            addAuditorContactNo_TextView.setText(beanList.getContact_no());
            addAuditorBookingId_TextView.setText(beanList.getComplaint_id());
        }else {
            enquiryId = beanList.getEnq_id().toString();
            addAuditorName_TextView.setText(beanList.getName());
            addAuditorContactNo_TextView.setText(beanList.getContact_no());
            addAuditorBookingId_TextView.setText(beanList.getEnq_id());
        }

        serviceFeedbackAuditor();
        followUpPending();
        callReceivedPending();
        fakeUpdationPending();

    }

    @OnClick(R.id.submitAuditorDetails_Button)
    public void submitAuditor(){
        if (!followUpPendingStringId.equals("")){
            if (!callReceivedStringId.equals("")){
                if (!fakeStringId.equals("")){
                    if(!serviceFeedbackStringId.equals("")){
                        if (remarksAuditorDetails_EditText.getText().toString().length()>0){
                            new CreateCarAuditor().execute();
                        }else{
                            Toast.makeText(AddAuditorRemarkDetailActivity.this, "Please Enter Comment", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AddAuditorRemarkDetailActivity.this, "Please Select Service Feedback", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AddAuditorRemarkDetailActivity.this, "Please Select Fake Updation", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(AddAuditorRemarkDetailActivity.this, "Please Select Call Received From Showroom", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(AddAuditorRemarkDetailActivity.this, "Please Select Follow-Up Pending", Toast.LENGTH_SHORT).show();
        }

    }

    private class CreateCarAuditor extends AsyncTask<String, JSONObject, JSONObject> {
        String user_id,process_id_session,booking_id,followup_pending,call_resceived,fake_updation,service_feedback;
        String comment;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id =  SharedPreferenceManager.getInstance(AddAuditorRemarkDetailActivity.this).getPreference(Constants.USER_ID, "");
            process_id_session = SharedPreferenceManager.getInstance(AddAuditorRemarkDetailActivity.this).getPreference(Constants.PROCESS_ID, "");
            booking_id = enquiryId;
            followup_pending = followUpPendingStringId;
            call_resceived = callReceivedStringId;
            fake_updation = fakeStringId;
            service_feedback =serviceFeedbackStringId;
            comment =remarksAuditorDetails_EditText.getText().toString();

            pDialog = new ProgressDialog(AddAuditorRemarkDetailActivity.this);
            pDialog.setMessage("Adding Auditor...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            List<NameValuePair> params;
            params = new ArrayList<>();
            params.add(new BasicNameValuePair("user_id", user_id));
            params.add(new BasicNameValuePair("process_id_session", process_id_session));
            params.add(new BasicNameValuePair("booking_id", booking_id));
            params.add(new BasicNameValuePair("followup_pending", followup_pending));
            params.add(new BasicNameValuePair("call_received", call_resceived));
            params.add(new BasicNameValuePair("fake_updation", fake_updation));
            params.add(new BasicNameValuePair("service_feedback", service_feedback));
            params.add(new BasicNameValuePair("comment", comment));

            String url_add_followup= Constants.BASE_URL + Constants.AUDITOR_INSERT;
            JSONObject json = jsonParser.makeHttpRequest(url_add_followup, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                if (success == 1 && message.equals("Successfully Updated.")) {
                    return json;
                }
                else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject response) {
            try {
                pDialog.dismiss();
                if (!(response == null)) {
                    Toast.makeText(AddAuditorRemarkDetailActivity.this, "Successfully Updated.", Toast.LENGTH_SHORT).show();
                    ClearAllData();
                }
                else {
                    Toast.makeText(AddAuditorRemarkDetailActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception ignored) {
            }
        }
    }

    private void ClearAllData() {
        followupPendingAuditorDetails_Spinner.setSelection(0);
        callReceivedShowroomAuditorDetails_Spinner.setSelection(0);
        fakeUpdationAuditorDetails_Spinner.setSelection(0);
        serviceFeedbackAuditorDetails_Spinner.setSelection(0);
        remarksAuditorDetails_EditText.setText("");
    }

    public void followUpPending(){
        ArrayList<String> followUpArrayList = new ArrayList<>();
        followUpArrayList.add("FollowUp Pending");
        followUpArrayList.add("Yes");
        followUpArrayList.add("No");
        ArrayAdapter<String> callStatusArrayAdapter = new ArrayAdapter<String>(AddAuditorRemarkDetailActivity.this, R.layout.spinner_textview, followUpArrayList);
        callStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        followupPendingAuditorDetails_Spinner.setAdapter(callStatusArrayAdapter);
        followupPendingAuditorDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                followUpPendingString = (String) parent.getItemAtPosition(position);
                if (followUpPendingString.equals("FollowUp Pending")) {
                    followUpPendingStringId = "";
                } else
                    followUpPendingStringId = followUpPendingString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void callReceivedPending(){
        ArrayList<String> callReceivedArrayList = new ArrayList<>();
        callReceivedArrayList.add("Call Received");
        callReceivedArrayList.add("Yes");
        callReceivedArrayList.add("No");
        ArrayAdapter<String> callStatusArrayAdapter = new ArrayAdapter<String>(AddAuditorRemarkDetailActivity.this, R.layout.spinner_textview, callReceivedArrayList);
        callStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        callReceivedShowroomAuditorDetails_Spinner.setAdapter(callStatusArrayAdapter);
        callReceivedShowroomAuditorDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                callReceivedString = (String) parent.getItemAtPosition(position);
                if (callReceivedString.equals("Call Received")) {
                    callReceivedStringId = "";
                } else
                    callReceivedStringId = callReceivedString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void fakeUpdationPending(){
        ArrayList<String> fakeUpdationArrayList = new ArrayList<>();
        fakeUpdationArrayList.add("Fake Updation");
        fakeUpdationArrayList.add("Yes");
        fakeUpdationArrayList.add("No");
        ArrayAdapter<String> callStatusArrayAdapter = new ArrayAdapter<String>(AddAuditorRemarkDetailActivity.this, R.layout.spinner_textview, fakeUpdationArrayList);
        callStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fakeUpdationAuditorDetails_Spinner.setAdapter(callStatusArrayAdapter);
        fakeUpdationAuditorDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fakeString = (String) parent.getItemAtPosition(position);
                if (fakeString.equals("Fake Updation")) {
                    fakeStringId = "";
                } else
                    fakeStringId = fakeString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void serviceFeedbackAuditor(){
        ArrayList<String> serviceFeedbackArrayList = new ArrayList<>();
        serviceFeedbackArrayList.add("Service Feedback");
        serviceFeedbackArrayList.add("Excellent");
        serviceFeedbackArrayList.add("Good");
        serviceFeedbackArrayList.add("Bad");
        ArrayAdapter<String> callStatusArrayAdapter = new ArrayAdapter<String>(AddAuditorRemarkDetailActivity.this, R.layout.spinner_textview, serviceFeedbackArrayList);
        callStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceFeedbackAuditorDetails_Spinner.setAdapter(callStatusArrayAdapter);
        serviceFeedbackAuditorDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                serviceFeedbackString = (String) parent.getItemAtPosition(position);
                if (serviceFeedbackString.equals("Fake Updation")) {
                    serviceFeedbackStringId = "";
                } else
                    serviceFeedbackStringId = serviceFeedbackString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}
