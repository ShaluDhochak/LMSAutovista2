package com.excell.lms.lmsautovista.View.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.makeText;


public class DseDailyReportFragment extends Fragment {

    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONParser jsonParser = new JSONParser();
    DatePickerDialog datePickerDialog;

    @BindView(R.id.dateDseDailyReport_txtView)
    TextView dateDseDailyReport_txtView;
    @BindView(R.id.walkInCountDseDailyReport_et)
    EditText walkInCountDseDailyReport_et;
    @BindView(R.id.remarkWalkInDseDailyReport_et)
    EditText remarkWalkInDseDailyReport_et;
    @BindView(R.id.homeVisitCountDseDailyReport_et)
    EditText homeVisitCountDseDailyReport_et;
    @BindView(R.id.remarkhomeVisitDseDailyReport_et)
    EditText remarkhomeVisitDseDailyReport_et;
    @BindView(R.id.bookingCountDseDailyReport_et)
    EditText bookingCountDseDailyReport_et;
    @BindView(R.id.remarkBookingDseDailyReport_et)
    EditText remarkBookingDseDailyReport_et;
    @BindView(R.id.testDriveCountDseDailyReport_et)
    EditText testDriveCountDseDailyReport_et;
    @BindView(R.id.remarkTestDriveDseDailyReport_et)
    EditText remarkTestDriveDseDailyReport_et;
    @BindView(R.id.deliveryCountDseDailyReport_et)
    EditText deliveryCountDseDailyReport_et;
    @BindView(R.id.enquiryCountDseDailyReport_et)
    EditText enquiryCountDseDailyReport_et;
    @BindView(R.id.remarkEnquiryDseDailyReport_et)
    EditText remarkEnquiryDseDailyReport_et;
    @BindView(R.id.gatePassCountDseDailyReport_et)
    EditText gatePassCountDseDailyReport_et;
    @BindView(R.id.remarkGatePassDseDailyReport_et)
    EditText remarkGatePassDseDailyReport_et;
    @BindView(R.id.remarkEvaluationDseDailyReport_et)
    EditText remarkEvaluationDseDailyReport_et;
    @BindView(R.id.evaluationCountDseDailyReport_et)
    EditText evaluationCountDseDailyReport_et;

    //Submit Button for DSE Daily REPORT
    @BindView(R.id.addDSEDailyReportSubmit_Button)
    Button addDSEDailyReportSubmit_Button;

    SharedPreferences pref;
    String user_id, user_location;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_dse_daily_report, container, false);
        ButterKnife.bind(this, view);
        initialiseUI();
        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseUI(){

    }

    @OnClick(R.id.dateDseDailyReport_txtView)
    public void getDseDate(){
        final Calendar dseDailyReportcalenderObject = Calendar.getInstance();
        int BookedYear = dseDailyReportcalenderObject.get(Calendar.YEAR);
        int BookedMonth = dseDailyReportcalenderObject.get(Calendar.MONTH);
        int BookedDay = dseDailyReportcalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateDseDailyReport_txtView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.addDSEDailyReportSubmit_Button)
    public void addDseDailyReport(){
        if(walkInCountDseDailyReport_et.getText().toString().trim().length()>0){
            if (remarkWalkInDseDailyReport_et.getText().toString().trim().length()>0){
                if (homeVisitCountDseDailyReport_et.getText().toString().trim().length()>0){
                    if(remarkhomeVisitDseDailyReport_et.getText().toString().trim().length()>0){
                        if (bookingCountDseDailyReport_et.getText().toString().trim().length()>0){
                            if (remarkBookingDseDailyReport_et.getText().toString().trim().length()>0){
                                if (testDriveCountDseDailyReport_et.getText().toString().trim().length()>0){
                                    if (remarkTestDriveDseDailyReport_et.getText().toString().trim().length()>0){
                                        if (deliveryCountDseDailyReport_et.getText().toString().trim().length()>0){
                                            if (enquiryCountDseDailyReport_et.getText().toString().trim().length()>0){
                                                if (remarkEnquiryDseDailyReport_et.getText().toString().trim().length()>0){
                                                    if (gatePassCountDseDailyReport_et.getText().toString().trim().length()>0){
                                                        if (remarkGatePassDseDailyReport_et.getText().toString().trim().length()>0){
                                                            if (evaluationCountDseDailyReport_et.getText().toString().trim().length()>0){
                                                                if (remarkEvaluationDseDailyReport_et.getText().toString().trim().length()>0){
                                                                    new CreateDSEReport().execute();
                                                                }else{
                                                                    Toast.makeText(getActivity(), "Please enter Evaluation Remark", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }else{
                                                                Toast.makeText(getActivity(), "Please enter Evaluation Count", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }else {
                                                            Toast.makeText(getActivity(), "Please enter GatePass Remark", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else{
                                                        Toast.makeText(getActivity(), "Please enter GatePass", Toast.LENGTH_SHORT).show();
                                                    }
                                                }else{
                                                    Toast.makeText(getActivity(), "Please enter Enquiry Remark", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(getActivity(), "Please enter Enquiry Count", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(getActivity(), "Please enter Delivery Count", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(getActivity(), "Please enter Test Drive Remark", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getActivity(), "Please enter Test Drive Count", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getActivity(), "Please enter booking Remark", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Please enter Booking Count", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Please enter Home Visit remark", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Please enter Home Visit", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity(), "Please enter WalkIn Remark", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Please enter WalkIn Count", Toast.LENGTH_SHORT).show();
        }
    }

    private class CreateDSEReport extends AsyncTask<String, JSONObject, JSONObject> {
        String walkInCount,walkInRemark, homeVisitCount, homeVisitRemark, bookingCount,bookingRemark,testDriveCount, testDriveRemark;
        String deliveryCount, enquiryCount, enquiryRemark, gatepassCount, gatePassRemark,userId, userLocation, evaluationCount, evaluationRemark, process_id;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            walkInCount =walkInCountDseDailyReport_et.getText().toString();
            walkInRemark =remarkWalkInDseDailyReport_et.getText().toString();
            homeVisitCount =homeVisitCountDseDailyReport_et.getText().toString();
            homeVisitRemark =remarkhomeVisitDseDailyReport_et.getText().toString();
            bookingCount =bookingCountDseDailyReport_et.getText().toString();
            bookingRemark =remarkBookingDseDailyReport_et.getText().toString();
            testDriveCount =testDriveCountDseDailyReport_et.getText().toString();
            testDriveRemark =remarkTestDriveDseDailyReport_et.getText().toString();
            deliveryCount =deliveryCountDseDailyReport_et.getText().toString();
            enquiryCount =enquiryCountDseDailyReport_et.getText().toString();
            enquiryRemark =remarkEnquiryDseDailyReport_et.getText().toString();
            gatepassCount =gatePassCountDseDailyReport_et.getText().toString();
            gatePassRemark =remarkGatePassDseDailyReport_et.getText().toString();
            evaluationCount = evaluationCountDseDailyReport_et.getText().toString();
            evaluationRemark = remarkEvaluationDseDailyReport_et.getText().toString();

            userId = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_ID, "");
            userLocation = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.LOCATION_SESSION, "");
            process_id = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, "");

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Adding DSE Report...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();

            params.add(new BasicNameValuePair("walk_in_count", walkInCount));
            params.add(new BasicNameValuePair("walk_in_remark", walkInRemark));
            params.add(new BasicNameValuePair("home_visit_count", homeVisitCount));
            params.add(new BasicNameValuePair("home_visit_remark", homeVisitRemark));
            params.add(new BasicNameValuePair("booking_count", bookingCount));
            params.add(new BasicNameValuePair("booking_remark", bookingRemark));
            params.add(new BasicNameValuePair("test_drive_count", testDriveCount));
            params.add(new BasicNameValuePair("test_drive_remark", testDriveRemark));
            params.add(new BasicNameValuePair("delivery_count", deliveryCount));

            params.add(new BasicNameValuePair("enquiry_count", enquiryCount));
            params.add(new BasicNameValuePair("enquiry_remark", enquiryRemark));
            params.add(new BasicNameValuePair("gatepass_count", gatepassCount));
            params.add(new BasicNameValuePair("gatepass_remark", gatePassRemark));
            params.add(new BasicNameValuePair("evaluation_count", evaluationCount));
            params.add(new BasicNameValuePair("evaluation_remark", evaluationRemark));

            params.add(new BasicNameValuePair("user_id", userId));
            params.add(new BasicNameValuePair("location", userLocation));
            params.add(new BasicNameValuePair("process_id", process_id));

            String url_add_lead = Constants.BASE_URL + Constants.DSE_DAILY_REPORT_INSERT;
            JSONObject json = jsonParser.makeHttpRequest(url_add_lead, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                if (success == 1 && message.equals("Data successfully Inserted.")) {
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

                    makeText(getActivity(),"Data successfully Inserted.", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
                else {
                    makeText(getActivity(), "Already Inserted ", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private void clearAll(){
        dateDseDailyReport_txtView.setText("Date");

        walkInCountDseDailyReport_et.setText("");
        remarkWalkInDseDailyReport_et.setText("");
        homeVisitCountDseDailyReport_et.setText("");
        remarkhomeVisitDseDailyReport_et.setText("");
        bookingCountDseDailyReport_et.setText("");
        remarkBookingDseDailyReport_et.setText("");
        testDriveCountDseDailyReport_et.setText("");
        remarkTestDriveDseDailyReport_et.setText("");
        deliveryCountDseDailyReport_et.setText("");
        enquiryCountDseDailyReport_et.setText("");
        remarkEnquiryDseDailyReport_et.setText("");
        gatePassCountDseDailyReport_et.setText("");
        remarkGatePassDseDailyReport_et.setText("");
        evaluationCountDseDailyReport_et.setText("");
        remarkEvaluationDseDailyReport_et.setText("");
    }
}
