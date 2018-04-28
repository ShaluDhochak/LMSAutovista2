package com.example.user.lmsautovista.View.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
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
import com.example.user.lmsautovista.Presenter.AddFollowUpPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.JSONParser;
import com.example.user.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static android.view.View.GONE;

public class AddFollowUpActivity extends AppCompatActivity implements IView.AddFollowUpView{

    //Floating action Button
    @BindView(R.id.customerDetails_fab)
    FloatingActionButton customerDetails_fab;
    @BindView(R.id.followUpDetail_fab)
    FloatingActionButton followUpDetail_fab;

    @BindView(R.id.clickHereToTransferLocationDetails_RelativeLayout)
    RelativeLayout clickHereToTransferLocationDetails_RelativeLayout;
    @BindView(R.id.sendQuotation_RelativeLayout)
    RelativeLayout sendQuotation_RelativeLayout;
    @BindView(R.id.transferLocationDetails_RelativeLayout)
    RelativeLayout transferLocationDetails_RelativeLayout;
    @BindView(R.id.clickHereToSendQuotationDetails_RelativeLayout)
    RelativeLayout clickHereToSendQuotationDetails_RelativeLayout;

    //details
    @BindView(R.id.nextActionAddFollowUpDetails_Spinner)
    Spinner nextActionAddFollowUpDetails_Spinner;
    @BindView(R.id.feedbackStatusAddFollowUpDetails_Spinner)
    Spinner feedbackStatusAddFollowUpDetails_Spinner;
    @BindView(R.id.booking60DaysAddFollowUpDetails_Spinner)
    Spinner booking60DaysAddFollowUpDetails_Spinner;

    //submit and modify Button
    @BindView(R.id.submitAddFollowUpDetails_Button)
    Button submitAddFollowUpDetails_Button;
    @BindView(R.id.modifyAddFollowUpDetails_Button)
    Button modifyAddFollowUpDetails_Button;

    //quotation CardView
    @BindView(R.id.sendQuotation_cardView)
    CardView sendQuotation_cardView;

    //Quotation spinner
    @BindView(R.id.locationClickHereToSendQuotation_Spinner)
    Spinner locationClickHereToSendQuotation_Spinner;
    @BindView(R.id.descriptionClickHereToSendQuotation_Spinner)
    Spinner descriptionClickHereToSendQuotation_Spinner;
    @BindView(R.id.carModelClickHereToSendQuotation_Spinner)
    Spinner carModelClickHereToSendQuotation_Spinner;

    //Transfer Spinner
    @BindView(R.id.transferReasonAddFollowUpDetails_Spinner)
    Spinner transferReasonAddFollowUpDetails_Spinner;
    @BindView(R.id.transferProcessAddFollowUpDetails_Spinner)
    Spinner transferProcessAddFollowUpDetails_Spinner;
    @BindView(R.id.transferlocationAddFollowUpDetails_Spinner)
    Spinner transferlocationAddFollowUpDetails_Spinner;
    @BindView(R.id.assignToAddFollowUpDetails_Spinner)
    Spinner assignToAddFollowUpDetails_Spinner;

    //Buyer Type Spinner
    @BindView(R.id.buyerTypeAddFollowUp_Spinner)
    Spinner buyerTypeAddFollowUp_Spinner;
    @BindView(R.id.buyerType_cardView)
    CardView buyerType_cardView;

    //Buyer type cardView
    @BindView(R.id.oldCarDetails_cardView)
    CardView oldCarDetails_cardView;
    @BindView(R.id.buyUsedCarDetails_cardView)
    CardView buyUsedCarDetails_cardView;
    @BindView(R.id.newCarDetails_cardView)
    CardView newCarDetails_cardView;

    //NEW cAR Details Spinner
    @BindView(R.id.newCarModelDetails_Spinner)
    Spinner newCarModelDetails_Spinner;
    @BindView(R.id.newCarVariantDetails_Spinner)
    Spinner newCarVariantDetails_Spinner;

    //spinner for buy used car
    @BindView(R.id.budgetToBuyUsedCarDetails_Spinner)
    Spinner budgetToBuyUsedCarDetails_Spinner;
    @BindView(R.id.budgetFromBuyUsedCarDetails_Spinner)
    Spinner budgetFromBuyUsedCarDetails_Spinner;
    @BindView(R.id.carMakeBuyUsedCarDetails_Spinner)
    Spinner carMakeBuyUsedCarDetails_Spinner;
    @BindView(R.id.carModelBuyUsedCarDetails_Spinner)
    Spinner carModelBuyUsedCarDetails_Spinner;

    //spinner for Old car Details
    @BindView(R.id.carModelOldCarDetails_Spinner)
    Spinner carModelOldCarDetails_Spinner;
    @BindView(R.id.ownershipOldCarDetails_Spinner)
    Spinner ownershipOldCarDetails_Spinner;
    @BindView(R.id.carMakeOldCarDetails_Spinner)
    Spinner carMakeOldCarDetails_Spinner;
    @BindView(R.id.anyAssidentailClaimOldCarDetails_Spinner)
    Spinner anyAssidentailClaimOldCarDetails_Spinner;
    @BindView(R.id.mfgOldCarDetails_Spinner)
    Spinner mfgOldCarDetails_Spinner;

    @BindView(R.id.groupAddFollowUpDetails_Spinner)
    Spinner groupAddFollowUpDetails_Spinner;
    @BindView(R.id.carDeliveredBuyUsedCarDetails_Spinner)
    Spinner carDeliveredBuyUsedCarDetails_Spinner;

    @BindView(R.id.saleStatusBuyUsedCarDetails_Spinner)
    Spinner saleStatusBuyUsedCarDetails_Spinner;
    @BindView(R.id.visitLocationBuyUsedCarDetails_Spinner)
    Spinner visitLocationBuyUsedCarDetails_Spinner;

    @BindView(R.id.visitBookedBuyUsedCarDetails_Spinner)
    Spinner visitBookedBuyUsedCarDetails_Spinner;
    @BindView(R.id.visitStatusBuyUsedCarDetails_Spinner)
    Spinner visitStatusBuyUsedCarDetails_Spinner;

    @BindView(R.id.bookedNewCarDetails_Spinner)
    Spinner bookedNewCarDetails_Spinner;

    //textview for details
    @BindView(R.id.tdHvDateAddFollowUpDetails_TextView)
    TextView tdHvDateAddFollowUpDetails_TextView;

    //TextView for TD/HV date
    @BindView(R.id.addFollowUpName_TextView)
    TextView addFollowUpName_TextView;
    @BindView(R.id.addFollowUpContactNo_TextView)
    TextView addFollowUpContactNo_TextView;
    @BindView(R.id.addFollowUpBookingId_TextView)
    TextView addFollowUpBookingId_TextView;
    @BindView(R.id.nfdAddFollowUpDetails_TextView)
    TextView nfdAddFollowUpDetails_TextView;
    @BindView(R.id.nftAddFollowUpDetails_TextView)
    TextView nftAddFollowUpDetails_TextView;

    //textview for buy used car
    @BindView(R.id.visitBookedDateBuyUsedCarDetails_TextView)
    TextView visitBookedDateBuyUsedCarDetails_TextView;

    //textview for transfer location
    @BindView(R.id.clickHereToTransferLocationsDetails_TextView)
    TextView clickHereToTransferLocationsDetails_TextView;

    //textview for send quotation
    @BindView(R.id.clickHereToSendQuotation_TextView)
    TextView clickHereToSendQuotation_TextView;

    @BindView(R.id.leadFollowUpgroup_TextView)
    TextView leadFollowUpgroup_TextView;

    //edittext for details
    @BindView(R.id.remarksAddFollowUpDetails_EditText)
    TextView remarksAddFollowUpDetails_EditText;
    @BindView(R.id.emailAddFollowUpDetails_TextView)
    TextView emailAddFollowUpDetails_TextView;
    @BindView(R.id.addressAddFollowUpDetails_TextView)
    TextView addressAddFollowUpDetails_TextView;

    //edittext for old car details
    @BindView(R.id.kmsOldCarDetails_EditText)
    TextView kmsOldCarDetails_EditText;

    //Presenter
    AddFollowUpPresenter addFollowUpPresenter;

    Boolean TransferLeadValid, QuotationLeadValid;
   // ImageView Prev_ImageView, Next_ImageView, backButton_ImageView;
   // ImageButton dialContact_Button;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    DatePickerDialog datePickerDialog;
    TimePickerDialog mTimePicker;

    //shared PREFERENCE
    SharedPreferenceManager preferenceManager;
    String process_shedPref;
    JSONParser jsonParser = new JSONParser();;
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    CallingTaskNewLeadBean.Lead_Details beanList;

    //Spinner
    ArrayList<String> nextActionArrayList = new ArrayList<>();
    ArrayList<String> newModelArrayList = new ArrayList<>();
    ArrayList<String> newVariantArrayList = new ArrayList<>();

    ArrayList<String> quotationDescriptionArraylist = new ArrayList<>();
    ArrayList<String> quotationLocArrayList = new ArrayList<>();
    ArrayList<String> quotationModelArrayList = new ArrayList<>();

    ArrayList<String> transferProcessArrayList = new ArrayList<>();
    ArrayList<String> transferLocArrayList = new ArrayList<>();
    ArrayList<String> transferAssignToArrayList = new ArrayList<>();

    //Arraylist for buy Used Car
    ArrayList<String> carMakeArrayList = new ArrayList<>();
    ArrayList<String> carModelArrayList = new ArrayList<>();

    //ArrayList for Old Car
    ArrayList<String> oldCarMakeArrayList = new ArrayList<>();
    ArrayList<String> oldCarModelArrayList = new ArrayList<>();

    //spinner Map
    Map<String, String> newModelMap = new HashMap<>();
    Map<String, String> newVariantMap = new HashMap<>();

    Map<String, String> transferProcessMap = new HashMap<>();
    Map<String, String> transferLocationMap = new HashMap<>();
    Map<String, String> transferAssignToMap = new HashMap<>();

    //Map for Buy Used Car
    Map<String, String> carMakeMap = new HashMap<>();
    Map<String, String> carModelMap = new HashMap<>();

    //Map for Old car
    Map<String, String> oldCarMakeMap = new HashMap<>();
    Map<String, String> oldCarModelMap = new HashMap<>();

    //String for selected Spinner
    String selectedFeedback, selectedFeedbackId, selectedNextAction, selectedNextActionId;
    String selectedBookingDaysId, selectedBookingDays, selectedNewVariant, selectedNewVariantID;
    String selectedNewModel, selectedNewModelId;

    String selectedProcess, selectedProcessId,selectedLocation, selectedLocationId,selectedTransferTo, selectedTransferToId;

    //String for buyer type
    String selectedBuyerType, selectedBuyerTypeId;

    //String for Budget from
    String budgetFromString, budgetFromStringId, budgetToString, budgetToStringId;
    String carMakeString, carMakeStringId, carModelString, carModelStringId;

    //string for Old car Details
    String oldCarMakeString, oldCarMakeStringId, oldCarModelString, oldCarModelStringId;
    String ownershipString, ownershipStringId, manfYearString, manfStringId;
    String accClaimString, accClaimStringId;

    //quotation location
    String locQuotationSpinner, locQuotationSpinnerId, modelQuotationSpinner, modelQuotationSpinnerId, descQuotationSpinner, descQuotationSpinnerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_followup_with_header);
        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
        addFollowUpPresenter = new AddFollowUpPresenter(this);
        process_shedPref = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "");

        beanList = getIntent().getParcelableExtra("bean");
        String enquiryId = beanList.getEnq_id().toString();

        //setBooking id here
        addFollowUpBookingId_TextView.setText(enquiryId);
        addFollowUpName_TextView.setText(beanList.getName().toString());
        addFollowUpContactNo_TextView.setText(beanList.getContact_no().toString());

        //td/hv date visibility
        tdHvDateAddFollowUpDetails_TextView.setVisibility(GONE);

        quotationDescriptionArraylist.add("Description");
        transferProcessArrayList.add("Transfer Process");
        transferLocArrayList.add("Transfer Location To");
        transferAssignToArrayList.add("Transfer To");
        quotationLocArrayList.add("Location");
        quotationModelArrayList.add("Model");


        addFollowUpPresenter.getFeedbackList(this);
        addFollowUpPresenter.getNextActionList(this,"");
        addFollowUpPresenter.getQuotationLocation(this);

        addFollowUpPresenter.getQuotataionModel("0", this);

        addFollowUpPresenter.getQuotationDescription("0", "0", this);

        addFollowUpPresenter.getTransferProcess(this);

        addFollowUpPresenter.getTransferLocation("", this);

        addFollowUpPresenter.getTransferAssignTo("","", this);

        getBookingWithInDatsDetails();

        if (process_shedPref.equals("6")){
            newCarDetails_cardView.setVisibility(View.VISIBLE);
            buyerType_cardView.setVisibility(GONE);
            oldCarDetails_cardView.setVisibility(GONE);
            buyUsedCarDetails_cardView.setVisibility(GONE);
            buyerTypeAddFollowUp_Spinner.setVisibility(GONE);
            sendQuotation_cardView.setVisibility(View.VISIBLE);
            addFollowUpPresenter.getNewCarModel("1", this);
            addFollowUpPresenter.getNewVariant("", this);

        }else if (process_shedPref.equals("7")){
            getBuyerTypeDetails();
            sendQuotation_cardView.setVisibility(GONE);
            oldCarDetails_cardView.setVisibility(GONE);
            buyUsedCarDetails_cardView.setVisibility(GONE);
            buyerType_cardView.setVisibility(View.VISIBLE);
            buyerTypeAddFollowUp_Spinner.setVisibility(View.VISIBLE);
            newCarDetails_cardView.setVisibility(View.GONE);
        }else{

            newCarDetails_cardView.setVisibility(GONE);
            oldCarDetails_cardView.setVisibility(GONE);
            buyUsedCarDetails_cardView.setVisibility(GONE);
            buyerTypeAddFollowUp_Spinner.setVisibility(GONE);
            buyerType_cardView.setVisibility(GONE);
        }
        //if process_id is 6(new  car) then newCarDetails_cardView.visibility visible
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.submitAddFollowUpDetails_Button)
    public void submitAddFollowUp() {

        if (process_shedPref.equals("6")) {
            new CreateNewCarFollowUp().execute();
        } else if (process_shedPref.equals("7")) {
            new CreateUsedCarFollowUp().execute();
        }
    }

    @OnClick(R.id.customerDetails_fab)
    public void setCustomerDetails(){
        Intent customerDetailsIntent = new Intent(this, CustomerDetailsActivity.class);
        customerDetailsIntent.putExtra("bean",beanList);
        customerDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(customerDetailsIntent);
    }

    @OnClick(R.id.followUpDetail_fab)
    public void setFollowUpDetails(){
        Intent followuPdetailIntent = new Intent(this, FollowUpDetailsActivity.class);
        followuPdetailIntent.putExtra("bean",beanList);
        followuPdetailIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(followuPdetailIntent);
    }

    @OnClick(R.id.backButton_ImageView)
    public void backButtonFn(){
        super.onBackPressed();
    }

    private class CreateNewCarFollowUp extends AsyncTask<String, JSONObject, JSONObject> {

        String booking_id, email, contact, address, feedback,  nextAction;
        String comment, followUpdate, followUpTime,  td_hv, days60,new_model, new_variant, transpro;
        String tlocation,tansferTo, qlocation, qmodelname, qdescription;

        String user_id_session,process_id,role_session;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            user_id_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.USER_ID, "");
            process_id = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.PROCESS_ID, "");
            role_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.ROLE_ID, "");

            booking_id = addFollowUpBookingId_TextView.getText().toString();
            email = emailAddFollowUpDetails_TextView.getText().toString();
            contact = addFollowUpContactNo_TextView.getText().toString();
            address = addressAddFollowUpDetails_TextView.getText().toString();

            feedback = selectedFeedbackId;
            nextAction = selectedNextActionId;
            comment = remarksAddFollowUpDetails_EditText.getText().toString();
            followUpdate = nfdAddFollowUpDetails_TextView.getText().toString();
            followUpTime = nftAddFollowUpDetails_TextView.getText().toString();
            td_hv = tdHvDateAddFollowUpDetails_TextView.getText().toString();
            days60 = selectedBookingDaysId;

            new_model = selectedNewModelId;
            new_variant =selectedNewVariantID;

            transpro = selectedProcessId;
            tlocation = selectedLocationId;
            tansferTo = selectedTransferToId;

            qlocation = locQuotationSpinnerId;
            qmodelname = modelQuotationSpinnerId;
            qdescription = descQuotationSpinnerId;

            pDialog = new ProgressDialog(AddFollowUpActivity.this);
            pDialog.setMessage("Adding FollowUp...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.dismiss();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();

            params.add(new BasicNameValuePair("user_id", user_id_session));
            params.add(new BasicNameValuePair("process_id", process_id));
            params.add(new BasicNameValuePair("role", role_session));

            params.add(new BasicNameValuePair("booking_id", booking_id));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("alternate_contact", contact));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("feedback", feedback));
            params.add(new BasicNameValuePair("nextaction", nextAction));
            params.add(new BasicNameValuePair("comment", comment));
            params.add(new BasicNameValuePair("followupdate", followUpdate));
            params.add(new BasicNameValuePair("followuptime", followUpTime));
            params.add(new BasicNameValuePair("td_hv_date", td_hv));

            params.add(new BasicNameValuePair("days_booking", days60));
            params.add(new BasicNameValuePair("new_model", new_model));
            params.add(new BasicNameValuePair("new_variant", new_variant));

            params.add(new BasicNameValuePair("tprocess", transpro));
            params.add(new BasicNameValuePair("tlocation", tlocation));
            params.add(new BasicNameValuePair("transfer_assign_id", tansferTo));

            params.add(new BasicNameValuePair("qlocation", qlocation));
            params.add(new BasicNameValuePair("model_name", qmodelname));
            params.add(new BasicNameValuePair("description", qdescription));

            String url_add_followup= Constants.BASE_URL + Constants.ADD_FOLLOWUP_NEW_CAR;
            JSONObject json = jsonParser.makeHttpRequest(url_add_followup, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                if (success == 1 && message.equals("Data successfully Inserted.")) {
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
                    Toast.makeText(AddFollowUpActivity.this, "Data successfully Inserted.", Toast.LENGTH_SHORT).show();
                    //Toast(getActivity(),"Data successfully Inserted.", Toast.LENGTH_SHORT).show();
                    ClearAllData();
                }
                else {
                    Toast.makeText(AddFollowUpActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private class CreateUsedCarFollowUp extends AsyncTask<String, JSONObject, JSONObject> {

        String booking_id, email, contact, address, feedback,  nextAction;
        String comment, followUpdate, followUpTime,  td_hv, days60,new_model, new_variant, transpro;
        String tlocation,tansferTo;

        String buyerType, buy_make, buy_model, budget_from, budget_to,old_make, old_model,ownership, mfg, km, claim;

        String user_id_session,process_id,role_session;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            user_id_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.USER_ID, "");
            process_id = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.PROCESS_ID, "");
            role_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.ROLE_ID, "");

            booking_id = addFollowUpBookingId_TextView.getText().toString();
            email = emailAddFollowUpDetails_TextView.getText().toString();
            contact = addFollowUpContactNo_TextView.getText().toString();
            address = addressAddFollowUpDetails_TextView.getText().toString();

            feedback = selectedFeedbackId;
            nextAction = selectedNextActionId;
            comment = remarksAddFollowUpDetails_EditText.getText().toString();
            followUpdate = nfdAddFollowUpDetails_TextView.getText().toString();
            followUpTime = nftAddFollowUpDetails_TextView.getText().toString();
            td_hv = tdHvDateAddFollowUpDetails_TextView.getText().toString();
            days60 = selectedBookingDaysId;

            new_model = selectedNewModelId;
            new_variant =selectedNewVariantID;

            transpro = selectedProcessId;
            tlocation = selectedLocationId;
            tansferTo = selectedTransferToId;

            buyerType = selectedBuyerTypeId;
            buy_make = carMakeStringId;
            buy_model = carModelStringId;
            budget_from = budgetFromStringId;
            budget_to= budgetToStringId;
            old_make= oldCarMakeStringId;
            old_model= oldCarModelStringId;
            ownership= ownershipStringId;
            mfg= manfStringId;
            km= kmsOldCarDetails_EditText.getText().toString();
            claim = accClaimStringId;

            pDialog = new ProgressDialog(AddFollowUpActivity.this);
            pDialog.setMessage("Adding FollowUp...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.dismiss();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();

            params.add(new BasicNameValuePair("user_id", user_id_session));
            params.add(new BasicNameValuePair("process_id", process_id));
            params.add(new BasicNameValuePair("role", role_session));

            params.add(new BasicNameValuePair("booking_id", booking_id));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("alternate_contact", contact));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("feedback", feedback));
            params.add(new BasicNameValuePair("nextaction", nextAction));
            params.add(new BasicNameValuePair("comment", comment));
            params.add(new BasicNameValuePair("followupdate", followUpdate));
            params.add(new BasicNameValuePair("followuptime", followUpTime));
            params.add(new BasicNameValuePair("td_hv_date", td_hv));

            params.add(new BasicNameValuePair("days_booking", days60));
            params.add(new BasicNameValuePair("new_model", new_model));
            params.add(new BasicNameValuePair("new_variant", new_variant));

            params.add(new BasicNameValuePair("tprocess", transpro));
            params.add(new BasicNameValuePair("tlocation", tlocation));
            params.add(new BasicNameValuePair("transfer_assign_id", tansferTo));

            params.add(new BasicNameValuePair("buyer_type", buyerType));
            params.add(new BasicNameValuePair("buy_make", buy_make));
            params.add(new BasicNameValuePair("buy_model", buy_model));

            params.add(new BasicNameValuePair("budget_from", budget_from));
            params.add(new BasicNameValuePair("budget_to", budget_to));
            params.add(new BasicNameValuePair("old_make", old_make));

            params.add(new BasicNameValuePair("old_model", old_model));
            params.add(new BasicNameValuePair("ownership", ownership));
            params.add(new BasicNameValuePair("mfg", mfg));

            params.add(new BasicNameValuePair("km", km));
            params.add(new BasicNameValuePair("claim", claim));


            String url_add_followup= Constants.BASE_URL + Constants.ADD_FOLLOWUP_USED_CAR;
            JSONObject json = jsonParser.makeHttpRequest(url_add_followup, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                if (success == 1 && message.equals("Data successfully Inserted.")) {
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
                    Toast.makeText(AddFollowUpActivity.this, "Data successfully Inserted.", Toast.LENGTH_SHORT).show();
                    //Toast(getActivity(),"Data successfully Inserted.", Toast.LENGTH_SHORT).show();
                    ClearAllData();
                }
                else {
                    Toast.makeText(AddFollowUpActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignored) {
            }
        }
    }

    //Spinner for Booking within days
    public void getBuyerTypeDetails(){
        ArrayList<String> buyerTypeArrayList = new ArrayList<>();
        buyerTypeArrayList.add("Buyer Type");
        buyerTypeArrayList.add("Exchange with New Car");
        buyerTypeArrayList.add("Exchange with Old Car");
        buyerTypeArrayList.add("Buy used Car");
        buyerTypeArrayList.add("Sell Used Car");

        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, buyerTypeArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buyerTypeAddFollowUp_Spinner.setAdapter(contactArrayAdapter);

        buyerTypeAddFollowUp_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBuyerType = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedBuyerType.equals("Buyer Type")){
                    selectedBuyerTypeId = "";
                }else if (selectedBuyerType.equals("Exchange with New Car")){
                    selectedBuyerTypeId = "Exchange with New Car";

                    newCarDetails_cardView.setVisibility(View.VISIBLE);
                    oldCarDetails_cardView.setVisibility(View.VISIBLE);

                    newCarVisibility();
                    oldCarDetailsVisibility();
                    buyUsedCarDetails_cardView.setVisibility(GONE);
                }else if (selectedBuyerType.equals("Exchange with Old Car")){
                    selectedBuyerTypeId = "Exchange with Old Car";
                    newCarDetails_cardView.setVisibility(View.GONE);
                    oldCarDetails_cardView.setVisibility(View.VISIBLE);
                    buyUsedCarDetails_cardView.setVisibility(View.VISIBLE);

                    buyUsedCarVisibility();
                    oldCarDetailsVisibility();
                }else if (selectedBuyerType.equals("Buy used Car")){
                    selectedBuyerTypeId = "Buy used Car";
                    newCarDetails_cardView.setVisibility(View.GONE);
                    oldCarDetails_cardView.setVisibility(View.GONE);
                    buyUsedCarDetails_cardView.setVisibility(View.VISIBLE);
                    buyUsedCarVisibility();
                }else if (selectedBuyerType.equals("Sell Used Car")){
                    selectedBuyerTypeId = "Sell Used Car";
                    newCarDetails_cardView.setVisibility(View.GONE);
                    oldCarDetails_cardView.setVisibility(View.VISIBLE);
                    buyUsedCarDetails_cardView.setVisibility(View.GONE);
                    oldCarDetailsVisibility();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void newCarVisibility(){
        addFollowUpPresenter.getNewCarModel("1", this);
        addFollowUpPresenter.getNewVariant("", this);
    }

    private void buyUsedCarVisibility(){
        getBudgetFromDetails();
        getBudgetToDetails();
        addFollowUpPresenter.getUsedcarMake(this);
        addFollowUpPresenter.getUsedCarModel("", this);
    }

    private void oldCarDetailsVisibility(){
        getOwnershipDetails();
        getAnyAccClaimDetails();
        getManufacturingDetails();
        addFollowUpPresenter.getOldCarMake(this);
        addFollowUpPresenter.getOldCarModel("",this);
    }

    public void getBudgetFromDetails(){
        ArrayList<String> budgetFromArrayList = new ArrayList<>();
        budgetFromArrayList.add("Budget From");
        budgetFromArrayList.add("100000");
        budgetFromArrayList.add("200000");
        budgetFromArrayList.add("300000");
        budgetFromArrayList.add("400000");
        budgetFromArrayList.add("500000");
        budgetFromArrayList.add("600000");
        budgetFromArrayList.add("700000");
        budgetFromArrayList.add("800000");
        budgetFromArrayList.add("900000");
        budgetFromArrayList.add("1000000");

        ArrayAdapter<String> budgetFromArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, budgetFromArrayList);
        budgetFromArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        budgetFromBuyUsedCarDetails_Spinner.setAdapter(budgetFromArrayAdapter);

        budgetFromBuyUsedCarDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                budgetFromString = (String) parent.getItemAtPosition(position);

                if (budgetFromString.equals("Budget From")) {
                    budgetFromStringId = "";
                } else
                    budgetFromStringId = budgetFromString;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getBudgetToDetails(){
        ArrayList<String> budgetToArrayList = new ArrayList<>();
        budgetToArrayList.add("Budget To");
        budgetToArrayList.add("100000");
        budgetToArrayList.add("200000");
        budgetToArrayList.add("300000");
        budgetToArrayList.add("400000");
        budgetToArrayList.add("500000");
        budgetToArrayList.add("600000");
        budgetToArrayList.add("700000");
        budgetToArrayList.add("800000");
        budgetToArrayList.add("900000");
        budgetToArrayList.add("1000000");

        ArrayAdapter<String> budgetToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, budgetToArrayList);
        budgetToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        budgetToBuyUsedCarDetails_Spinner.setAdapter(budgetToArrayAdapter);

        budgetToBuyUsedCarDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                budgetToString = (String) parent.getItemAtPosition(position);

                if (budgetToString.equals("Budget To")) {
                    budgetToStringId = "";
                } else
                    budgetToStringId = budgetToString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getOwnershipDetails(){
        ArrayList<String> ownershipArrayList = new ArrayList<>();
        ownershipArrayList.add("Ownership");
        ownershipArrayList.add("First");
        ownershipArrayList.add("Second");
        ownershipArrayList.add("Third");
        ownershipArrayList.add("More than Three");

        ArrayAdapter<String> budgetToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, ownershipArrayList);
        budgetToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ownershipOldCarDetails_Spinner.setAdapter(budgetToArrayAdapter);

        ownershipOldCarDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ownershipString = (String) parent.getItemAtPosition(position);

                if (ownershipString.equals("Ownership")) {
                    ownershipStringId = "";
                } else
                    ownershipStringId = ownershipString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getManufacturingDetails(){
        ArrayList<String> manfArrayList = new ArrayList<>();
        manfArrayList.add("Manufacturing Year");
        manfArrayList.add("2018");
        manfArrayList.add("2017");
        manfArrayList.add("2016");
        manfArrayList.add("2015");
        manfArrayList.add("2014");
        manfArrayList.add("2013");
        manfArrayList.add("2012");
        manfArrayList.add("2011");
        manfArrayList.add("2010");
        manfArrayList.add("2009");
        manfArrayList.add("2008");
        manfArrayList.add("2007");
        manfArrayList.add("2006");
        manfArrayList.add("2005");
        manfArrayList.add("2004");
        manfArrayList.add("2003");
        manfArrayList.add("2002");
        manfArrayList.add("2001");
        manfArrayList.add("2000");
        manfArrayList.add("1999");
        manfArrayList.add("1998");
        manfArrayList.add("1997");
        manfArrayList.add("1996");
        manfArrayList.add("1995");
        manfArrayList.add("1994");
        manfArrayList.add("1993");
        manfArrayList.add("1992");
        manfArrayList.add("1991");
        manfArrayList.add("1990");
        manfArrayList.add("1989");
        manfArrayList.add("1988");
        manfArrayList.add("1987");
        manfArrayList.add("1986");
        manfArrayList.add("1985");
        manfArrayList.add("1984");
        manfArrayList.add("1983");
        manfArrayList.add("1982");
        manfArrayList.add("1981");

        ArrayAdapter<String> budgetToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, manfArrayList);
        budgetToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mfgOldCarDetails_Spinner.setAdapter(budgetToArrayAdapter);

        mfgOldCarDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                manfYearString = (String) parent.getItemAtPosition(position);

                if (manfYearString.equals("Manufacturing Year")) {
                    manfStringId = "";
                } else
                    manfStringId = manfYearString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getAnyAccClaimDetails(){
        ArrayList<String> accClaimArrayList = new ArrayList<>();
        accClaimArrayList.add("Any Accidental Claim");
        accClaimArrayList.add("Yes");
        accClaimArrayList.add("No");

        ArrayAdapter<String> budgetToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, accClaimArrayList);
        budgetToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anyAssidentailClaimOldCarDetails_Spinner.setAdapter(budgetToArrayAdapter);

        anyAssidentailClaimOldCarDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accClaimString = (String) parent.getItemAtPosition(position);

                if (accClaimString.equals("Any Accidental Claim")) {
                    accClaimStringId = "";
                } else
                    accClaimStringId = accClaimString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //details calender
    @OnClick(R.id.tdHvDateAddFollowUpDetails_TextView)
    public void getTdHvDate(){
        final Calendar tdHvcalenderObject = Calendar.getInstance();
        int BookedYear = tdHvcalenderObject.get(Calendar.YEAR);
        int BookedMonth = tdHvcalenderObject.get(Calendar.MONTH);
        int BookedDay = tdHvcalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(AddFollowUpActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tdHvDateAddFollowUpDetails_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.nfdAddFollowUpDetails_TextView)
    public void nfdDate(){
        final Calendar fromdatecalenderObject = Calendar.getInstance();
        int BookedYear = fromdatecalenderObject.get(Calendar.YEAR);
        int BookedMonth = fromdatecalenderObject.get(Calendar.MONTH);
        int BookedDay = fromdatecalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(AddFollowUpActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        nfdAddFollowUpDetails_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.nftAddFollowUpDetails_TextView)
    public void nftTime(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        mTimePicker = new TimePickerDialog(AddFollowUpActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                nftAddFollowUpDetails_TextView.setText(selectedHour + ":" + selectedMinute + ":00");
            }
        }, hour, minute, true);//Yes 24 hour time
        // mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    //details spinner
    @Override
    public void showFeedbackSpinnerList(FeedbackListBean jsonObject) {
        ArrayList<String> feedbackArrayList = new ArrayList<>();
        feedbackArrayList.add("Select Feedback");
        for(int i=0;i<jsonObject.getSelect_feedback().size();i++)
        {
            try {
                feedbackArrayList.add(jsonObject.getSelect_feedback().get(i).getFeedbackStatusName());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview, feedbackArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feedbackStatusAddFollowUpDetails_Spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void shownextActionAddFollowUpList(NextActionListBean jsonObject) {
        nextActionArrayList.clear();
        nextActionArrayList.add("Select Next Action");
        for(int i=0;i<jsonObject.getSelect_nextaction().size();i++)
        {
            try {
                nextActionArrayList.add(jsonObject.getSelect_nextaction().get(i).getNextActionName());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,nextActionArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nextActionAddFollowUpDetails_Spinner.setAdapter(companiesArrayAdapter);
    }

    //new car Details
    @Override
    public void shownewCarModelSpinner(ModelBean jsonObject) {
        newModelArrayList.clear();
        newModelArrayList.add("New Car Model");
        for(int i=0;i<jsonObject.getSelect_car_model().size();i++)
        {
            try {
                newModelArrayList.add(jsonObject.getSelect_car_model().get(i).getModel_name());
                newModelMap.put(jsonObject.getSelect_car_model().get(i).getModel_id(), jsonObject.getSelect_car_model().get(i).getModel_name());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,newModelArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newCarModelDetails_Spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void showNewCarVariantSpinner(CarVariantBean jsonObject) {
        newVariantArrayList.clear();
        newVariantArrayList.add("New Car Variant");
        for(int i=0;i<jsonObject.getSelect_car_variant().size();i++)
        {
            try {
                newVariantArrayList.add(jsonObject.getSelect_car_variant().get(i).getVariant_name());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,newVariantArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newCarVariantDetails_Spinner.setAdapter(companiesArrayAdapter);
    }

    //Send Quotation
    @Override
    public void showQuoatationLocSpinner(QuotationLocationBean jsonObject) {

        quotationLocArrayList.clear();
        quotationLocArrayList.add("Location");
        for(int i=0;i<jsonObject.getQuotation_location().size();i++)
        {
            try {
                quotationLocArrayList.add(jsonObject.getQuotation_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,quotationLocArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationClickHereToSendQuotation_Spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void showQuoatationDescriptionSpinner(QuotationDescriptionBean jsonObject) {
        quotationDescriptionArraylist.clear();
        quotationDescriptionArraylist.add("Description");
        for(int i=0;i<jsonObject.getQuotation_description().size();i++)
        {
            try {
                quotationDescriptionArraylist.add(jsonObject.getQuotation_description().get(i).getVariant());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,quotationDescriptionArraylist);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        descriptionClickHereToSendQuotation_Spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void showQuotationModelSpinner(QuotationModelBean jsonObject) {

        quotationModelArrayList.clear();
        quotationModelArrayList.add("Model");
        for(int i=0;i<jsonObject.getQuotation_model_name().size();i++)
        {
            try {
                quotationModelArrayList.add(jsonObject.getQuotation_model_name().get(i).getModel());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,quotationModelArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelClickHereToSendQuotation_Spinner.setAdapter(companiesArrayAdapter);
    }

    //transfer location
    @Override
    public void showTransferProcess(ProcessBean jsonObject) {
        transferProcessArrayList.clear();
        transferProcessArrayList.add("Transfer Process");
        for(int i=0;i<jsonObject.getAll_process().size();i++)
        {
            try {
                transferProcessArrayList.add(jsonObject.getAll_process().get(i).getProcess_name());
                transferProcessMap.put(jsonObject.getAll_process().get(i).getProcess_id(), jsonObject.getAll_process().get(i).getProcess_name());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> transferProcessArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,transferProcessArrayList);
        transferProcessArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transferProcessAddFollowUpDetails_Spinner.setAdapter(transferProcessArrayAdapter);
    }

    @Override
    public void showTransferLocation(TransferLocationBean jsonObject) {
        transferLocArrayList.clear();
        transferLocArrayList.add("Transfer Location To");
        for(int i=0;i<jsonObject.getSelect_transfer_location().size();i++)
        {
            try {
                transferLocArrayList.add(jsonObject.getSelect_transfer_location().get(i).getLocation());
                transferLocationMap.put(jsonObject.getSelect_transfer_location().get(i).getLocation_id(), jsonObject.getSelect_transfer_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> transferLocArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,transferLocArrayList);
        transferLocArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transferlocationAddFollowUpDetails_Spinner.setAdapter(transferLocArrayAdapter);
    }

    @Override
    public void showTransferAssignTo(TransferAssignToBean jsonObject) {
        transferAssignToArrayList.clear();
        transferAssignToArrayList.add("Transfer To");
        for(int i=0;i<jsonObject.getSelect_transfer_to_user().size();i++)
        {
            try{
                transferAssignToArrayList.add(jsonObject.getSelect_transfer_to_user().get(i).getFname() + " "+ jsonObject.getSelect_transfer_to_user().get(i).getLname());
                transferAssignToMap.put(jsonObject.getSelect_transfer_to_user().get(i).getId(), jsonObject.getSelect_transfer_to_user().get(i).getFname() + " "+ jsonObject.getSelect_transfer_to_user().get(i).getLname());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,transferAssignToArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignToAddFollowUpDetails_Spinner.setAdapter(transferToArrayAdapter);
    }

    //buy with used car
    @Override
    public void showCarMake(MakeBean jsonObject) {
        carMakeArrayList.clear();
        carMakeArrayList.add("Car Make");
        for(int i=0;i<jsonObject.getSelect_car_make().size();i++)
        {
            try{
                carMakeArrayList.add(jsonObject.getSelect_car_make().get(i).getMake_name());
                carMakeMap.put(jsonObject.getSelect_car_make().get(i).getMake_id(), jsonObject.getSelect_car_make().get(i).getMake_name());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,carMakeArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMakeBuyUsedCarDetails_Spinner.setAdapter(transferToArrayAdapter);
    }

    @Override
    public void showCarModel(POCStockModel jsonObject) {
        carModelArrayList.clear();
        carModelArrayList.add("Car Model");
        for(int i=0;i<jsonObject.getPoc_stock_color().size();i++)
        {
            try{
                carModelArrayList.add(jsonObject.getPoc_stock_color().get(i).getModel());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,carModelArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelBuyUsedCarDetails_Spinner.setAdapter(transferToArrayAdapter);
    }

    //old car Details
    @Override
    public void showOldCarMake(MakeBean jsonObject) {
        oldCarMakeArrayList.clear();
        oldCarMakeArrayList.add("Car Make");
        for(int i=0;i<jsonObject.getSelect_car_make().size();i++)
        {
            try{
                oldCarMakeArrayList.add(jsonObject.getSelect_car_make().get(i).getMake_name());
                oldCarMakeMap.put(jsonObject.getSelect_car_make().get(i).getMake_id(), jsonObject.getSelect_car_make().get(i).getMake_name());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    //    oldCarMakeString, oldCarMakeStringId, oldCarModelString, oldCarModelStringId;
        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,oldCarMakeArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMakeOldCarDetails_Spinner.setAdapter(transferToArrayAdapter);
    }

    @Override
    public void showOldCarModel(POCStockModel jsonObject) {
        oldCarModelArrayList.clear();
        oldCarModelArrayList.add("Car Model");
        for(int i=0;i<jsonObject.getPoc_stock_color().size();i++)
        {
            try{
                oldCarModelArrayList.add(jsonObject.getPoc_stock_color().get(i).getModel());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,oldCarModelArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelOldCarDetails_Spinner.setAdapter(transferToArrayAdapter);
    }

    //Spinner for Booking within days
    public void getBookingWithInDatsDetails(){
        ArrayList<String> bookingDaysArrayList = new ArrayList<>();
        bookingDaysArrayList.add("Booking WithIn Days");
        bookingDaysArrayList.add("30 days");
        bookingDaysArrayList.add("60 days");
        bookingDaysArrayList.add("90 days");
        bookingDaysArrayList.add("Undecided");
        bookingDaysArrayList.add("Just Researching");
        bookingDaysArrayList.add("Immediate");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, bookingDaysArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        booking60DaysAddFollowUpDetails_Spinner.setAdapter(contactArrayAdapter);

        booking60DaysAddFollowUpDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBookingDays = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedBookingDays.equals("Booking WithIn Days")){
                    selectedBookingDaysId = "";
                }else if (selectedBookingDays.equals("30 days")){
                    selectedBookingDaysId = "30";
                }else if (selectedBookingDays.equals("60 days")){
                    selectedBookingDaysId = "60";
                }else if (selectedBookingDays.equals("90 days")){
                    selectedBookingDaysId = "90";
                }else if (selectedBookingDays.equals("Undecided")){
                    selectedBookingDaysId = "Undecided";
                }else if (selectedBookingDays.equals("Just Researching")){
                    selectedBookingDaysId = "Just Researching";
                }else if (selectedBookingDays.equals("Immediate")){
                    selectedBookingDaysId = "Immediate";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //details
    @OnItemSelected(R.id.feedbackStatusAddFollowUpDetails_Spinner)
    public void companyNameSelected(Spinner spinner, int position)
    {
        selectedFeedback = spinner.getSelectedItem().toString();
        if (selectedFeedback.equals("Select Feedback")){
            selectedFeedbackId = "";
        }else if (!(selectedFeedback.equals("Select Feedback"))){
            selectedFeedbackId = selectedFeedback;
        }else {
            selectedFeedbackId = "";
        }

        addFollowUpPresenter.getNextActionList(AddFollowUpActivity.this, selectedFeedback);

    }

    @OnItemSelected(R.id.nextActionAddFollowUpDetails_Spinner)
    public void nextActionSelected(Spinner spinner, int position)
    {
        selectedNextAction = spinner.getSelectedItem().toString();

        if (selectedNextAction.equals("Select Next Action")){
            selectedNextActionId = "";
        }else if (!(selectedNextAction.equals("Select Next Action"))){
            selectedNextActionId = selectedNextAction;
        }else {
            selectedNextActionId = "";
        }

        if (selectedNextAction.equals("Booked From Autovista") || selectedNextAction.equals("Close")){
            nfdAddFollowUpDetails_TextView.setVisibility(View.GONE);
            nftAddFollowUpDetails_TextView.setVisibility(View.GONE);
        }else{
            nfdAddFollowUpDetails_TextView.setText("Next FollowUp Date");
            nftAddFollowUpDetails_TextView.setText("Next FollowUp Time");
            nfdAddFollowUpDetails_TextView.setVisibility(View.VISIBLE);
            nftAddFollowUpDetails_TextView.setVisibility(View.VISIBLE);
        }

        if (selectedNextAction.equals("Home Visit") || selectedNextAction.equals("Test Drive")){
            tdHvDateAddFollowUpDetails_TextView.setText("TD/HV Date");
            tdHvDateAddFollowUpDetails_TextView.setVisibility(View.VISIBLE);
        }else{
            tdHvDateAddFollowUpDetails_TextView.setText("TD/HV Date");
            tdHvDateAddFollowUpDetails_TextView.setVisibility(View.GONE);
        }

    }

    //new car details
    @OnItemSelected(R.id.newCarVariantDetails_Spinner)
    public void newCarVariantSelected(Spinner spinner, int position)
    {
        selectedNewVariant = spinner.getSelectedItem().toString();

       /* if (selectedNewVariant.equals("New Car Variant")){
            selectedNewVariantID = "";
        }else if (!(selectedNewVariant.equals("New Car Variant"))){
            selectedNewVariantID = selectedNewVariant;
        }else {
            selectedNewVariantID = "";
        }
        */

        for (Map.Entry<String, String> e : newVariantMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedNewVariant.equals("New Car Variant")) {
                selectedNewVariantID = "";
            } else {
                if (value.equals(selectedNewVariant)) {
                    selectedNewVariantID = (String) key;
                    Log.i("Selected Model : ", selectedNewVariantID);
                }
            }
        }

    }

    @OnItemSelected(R.id.newCarModelDetails_Spinner)
    public void newCarModelSelected(Spinner spinner, int position)
    {
        selectedNewModel = spinner.getSelectedItem().toString();

      /*  if (selectedNewModel.equals("New Car Model")){
            selectedNewModelId = "";
        }else if (!(selectedNewModel.equals("New Car Model"))){
            selectedNewModelId = selectedNewModel;
        }else {
            selectedNewModelId = "";
        }
        */
        for (Map.Entry<String, String> e : newModelMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedNewModel.equals("New Car Model")) {
                selectedNewModelId = "";
            } else {
                if (value.equals(selectedNewModel)) {
                    selectedNewModelId = (String) key;
                    Log.i("Selected Model : ", selectedNewModelId);

                    addFollowUpPresenter.getNewVariant(selectedNewModelId, this);
                }
            }
        }

    }

    //send quotation
    @OnItemSelected(R.id.carModelClickHereToSendQuotation_Spinner)
    public void quotationModelSelected(Spinner spinner, int position)
    {
        modelQuotationSpinner = spinner.getSelectedItem().toString();

        if(modelQuotationSpinner.equals("Model")){
            modelQuotationSpinnerId = "";
        }else if(!(modelQuotationSpinner.equals("Model"))){
            modelQuotationSpinnerId = modelQuotationSpinner;
        }else {
            modelQuotationSpinnerId = "";
        }
        addFollowUpPresenter.getQuotationDescription(locQuotationSpinnerId,modelQuotationSpinnerId, this);
    }

    @OnItemSelected(R.id.locationClickHereToSendQuotation_Spinner)
    public void quotationLocSelected(Spinner spinner, int position)
    {
        locQuotationSpinner = spinner.getSelectedItem().toString();

        if(locQuotationSpinner.equals("Location")){
           locQuotationSpinnerId = "";
        }else if(!(locQuotationSpinner.equals("Location"))){
           locQuotationSpinnerId = locQuotationSpinner;
        }else {
           locQuotationSpinnerId = "";
        }
        addFollowUpPresenter.getQuotataionModel(locQuotationSpinnerId, this);

    }

    @OnItemSelected(R.id.descriptionClickHereToSendQuotation_Spinner)
    public void quotationDescriptionSelected(Spinner spinner, int position)
    {
        descQuotationSpinner = spinner.getSelectedItem().toString();

        if(descQuotationSpinner.equals("Description")){
            descQuotationSpinnerId = "";
        }else if(!(descQuotationSpinner.equals("Description"))){
            descQuotationSpinnerId = descQuotationSpinner;
        }else {
            descQuotationSpinnerId = "";
        }
    }

    //transfer to
    @OnItemSelected(R.id.transferProcessAddFollowUpDetails_Spinner)
    public void transferProcesslSelected(Spinner spinner, int position)
    {
        selectedProcess = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : transferProcessMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedProcess.equals("Transfer Process")) {
                selectedProcessId = "";
                addFollowUpPresenter.getTransferLocation(selectedProcessId, this);
            } else {
                if (value.equals(selectedProcess)) {
                    selectedProcessId = (String) key;
                    Log.i("Selected Model : ", selectedProcessId);

                    addFollowUpPresenter.getTransferLocation(selectedProcessId, this);
                }
            }
        }
    }

    @OnItemSelected(R.id.transferlocationAddFollowUpDetails_Spinner)
    public void transferLocationSelected(Spinner spinner, int position)
    {
        selectedLocation = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : transferLocationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedLocation.equals("Transfer Location To")) {
                selectedLocationId = "";
                addFollowUpPresenter.getTransferAssignTo(selectedProcessId,selectedLocationId, this);
                //addFollowUpPresenter.getTransferLocation(selectedProcessId, this);
            } else {
                if (value.equals(selectedLocation)) {
                    selectedLocationId = (String) key;
                    Log.i("Selected Model : ", selectedLocationId);
                    addFollowUpPresenter.getTransferAssignTo(selectedProcessId,selectedLocationId, this);
                  //  addFollowUpPresenter.getTransferLocation(selectedProcessId, this);
                }
            }
        }
    }

    @OnItemSelected(R.id.assignToAddFollowUpDetails_Spinner)
    public void transferToSelected(Spinner spinner, int position)
    {
        selectedTransferTo = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : transferAssignToMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedTransferTo.equals("Transfer Location To")) {
                selectedTransferToId = "";
                //addFollowUpPresenter.getTransferLocation(selectedProcessId, this);
            } else {
                if (value.equals(selectedTransferTo)) {
                    selectedTransferToId = (String) key;
                    Log.i("Selected Model : ", selectedTransferToId);

                    //  addFollowUpPresenter.getTransferLocation(selectedProcessId, this);
                }
            }
        }
    }

    //buy used car
    @OnItemSelected(R.id.carMakeBuyUsedCarDetails_Spinner)
    public void carMakeSelected(Spinner spinner, int position)
    {
        carMakeString = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : carMakeMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (carMakeString.equals("Car Make")) {
                carMakeStringId = "";
            }else{
                if (value.equals(carMakeString)) {
                    carMakeStringId = (String) key;
                    Log.i("Selected Make: ", carMakeStringId);
                }
            }
            addFollowUpPresenter.getUsedCarModel(carMakeStringId,this);
        }
    }

    @OnItemSelected(R.id.carModelBuyUsedCarDetails_Spinner)
    public void carModelSelected(Spinner spinner, int position)
    {
        carModelString = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : carModelMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (carModelString.equals("Car Model")) {
                carModelStringId = "";
            }else{
                if (value.equals(carModelString)) {
                    carModelStringId = (String) key;
                    Log.i("Selected Make: ", carModelStringId);
                }
            }
        }
    }

    //old car Details
    @OnItemSelected(R.id.carMakeOldCarDetails_Spinner)
    public void olsCarMakeSelected(Spinner spinner, int position)
    {
        oldCarMakeString = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : oldCarMakeMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (oldCarMakeString.equals("Car Make")) {
                oldCarMakeStringId = "";
            }else{
                if (value.equals(oldCarMakeString)) {
                    oldCarMakeStringId = (String) key;
                    Log.i("Selected Make: ", oldCarMakeStringId);
                }
            }
            addFollowUpPresenter.getOldCarModel(oldCarMakeStringId,this);
        }

    }

    @OnItemSelected(R.id.carModelOldCarDetails_Spinner)
    public void oldCarModelSelected(Spinner spinner, int position)
    {
        oldCarModelString = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : oldCarModelMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (oldCarModelString.equals("Car Model")) {
                oldCarModelStringId = "";
            }else{
                if (value.equals(oldCarModelString)) {
                    oldCarModelStringId = (String) key;
                    Log.i("Selected Model: ", oldCarModelStringId);
                }
            }

        }

    }

    private void ClearAllData(){
        locationClickHereToSendQuotation_Spinner.setSelection(0);
        descriptionClickHereToSendQuotation_Spinner.setSelection(0);
        newCarModelDetails_Spinner.setSelection(0);
        carDeliveredBuyUsedCarDetails_Spinner.setSelection(0);
        saleStatusBuyUsedCarDetails_Spinner.setSelection(0);
        visitLocationBuyUsedCarDetails_Spinner.setSelection(0);
        visitBookedBuyUsedCarDetails_Spinner.setSelection(0);
        visitStatusBuyUsedCarDetails_Spinner.setSelection(0);
        budgetToBuyUsedCarDetails_Spinner.setSelection(0);
        budgetFromBuyUsedCarDetails_Spinner.setSelection(0);
        carMakeBuyUsedCarDetails_Spinner.setSelection(0);
        carModelBuyUsedCarDetails_Spinner.setSelection(0);
        carModelClickHereToSendQuotation_Spinner.setSelection(0);
        carMakeOldCarDetails_Spinner.setSelection(0);
        feedbackStatusAddFollowUpDetails_Spinner.setSelection(0);
        nextActionAddFollowUpDetails_Spinner.setSelection(0);
        assignToAddFollowUpDetails_Spinner.setSelection(0);
        groupAddFollowUpDetails_Spinner.setSelection(0);
        anyAssidentailClaimOldCarDetails_Spinner.setSelection(0);
        bookedNewCarDetails_Spinner.setSelection(0);
        booking60DaysAddFollowUpDetails_Spinner.setSelection(0);
        newCarVariantDetails_Spinner.setSelection(0);
        transferlocationAddFollowUpDetails_Spinner.setSelection(0);
        carModelOldCarDetails_Spinner.setSelection(0);
        ownershipOldCarDetails_Spinner.setSelection(0);
        buyerTypeAddFollowUp_Spinner.setSelection(0);
        tdHvDateAddFollowUpDetails_TextView.setText("");
        tdHvDateAddFollowUpDetails_TextView.setVisibility(View.GONE);
        visitBookedDateBuyUsedCarDetails_TextView.setText("");
        nfdAddFollowUpDetails_TextView.setText("");
        mfgOldCarDetails_Spinner.setSelection(0);
        addFollowUpBookingId_TextView.setText("");
        remarksAddFollowUpDetails_EditText.setText("");
        kmsOldCarDetails_EditText.setText("");
    }

}
