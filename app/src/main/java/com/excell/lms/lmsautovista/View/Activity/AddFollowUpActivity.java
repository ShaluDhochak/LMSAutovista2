package com.excell.lms.lmsautovista.View.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
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
import com.excell.lms.lmsautovista.Presenter.AddFollowUpPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.makeText;

public class AddFollowUpActivity extends AppCompatActivity implements IView.AddFollowUpView {

    //Floating action Button
    @BindView(R.id.customerDetails_fab)
    FloatingActionButton customerDetails_fab;
    @BindView(R.id.followUpDetail_fab)
    FloatingActionButton followUpDetail_fab;
    @BindView(R.id.addAuditorFollowUp_fab)
    FloatingActionButton addAuditorFollowUp_fab;

    @BindView(R.id.auditorDetailFollowUp_fab)
    FloatingActionButton auditorDetailFollowUp_fab;


    @BindView(R.id.sendQuotation_RelativeLayout)
    RelativeLayout sendQuotation_RelativeLayout;
    @BindView(R.id.transferLocationDetails_RelativeLayout)
    RelativeLayout transferLocationDetails_RelativeLayout;

    //details
    @BindView(R.id.nextActionAddFollowUpDetails_Spinner)
    Spinner nextActionAddFollowUpDetails_Spinner;
    @BindView(R.id.feedbackStatusAddFollowUpDetails_Spinner)
    Spinner feedbackStatusAddFollowUpDetails_Spinner;
    @BindView(R.id.booking60DaysAddFollowUpDetails_Spinner)
    Spinner booking60DaysAddFollowUpDetails_Spinner;
    @BindView(R.id.callStatusAddFollowUpDetails_Spinner)
    Spinner callStatusAddFollowUpDetails_Spinner;

    @BindView(R.id.callContactNo_TextView)
    TextView callContactNo_TextView;

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
    @BindView(R.id.accessoriesPackageClickHereToSendQuotation_Spinner)
    Spinner accessoriesPackageClickHereToSendQuotation_Spinner;

    //Transfer Spinner
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
    @BindView(R.id.clickhereToSendBroucher_cv)
    CardView clickhereToSendBroucher_cv;
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

    //TextView for TD/HV date
    @BindView(R.id.addFollowUpName_TextView)
    TextView addFollowUpName_TextView;
    @BindView(R.id.addFollowUpContactNo_TextView)
    TextView addFollowUpContactNo_TextView;
    @BindView(R.id.addFollowUpAlternateContactNo_TextView)
    EditText addFollowUpAlternateContactNo_TextView;
    @BindView(R.id.addFollowUpBookingId_TextView)
    TextView addFollowUpBookingId_TextView;
    @BindView(R.id.nfdAddFollowUpDetails_TextView)
    TextView nfdAddFollowUpDetails_TextView;
    @BindView(R.id.nftAddFollowUpDetails_TextView)
    TextView nftAddFollowUpDetails_TextView;

    //textview for transfer location
    @BindView(R.id.clickHereToTransferLocationsDetails_TextView)
    TextView clickHereToTransferLocationsDetails_TextView;
    @BindView(R.id.transferLocatinDetails_cardView)
    CardView transferLocatinDetails_cardView;

    //textview for send quotation
    @BindView(R.id.clickHereToSendQuotation_TextView)
    TextView clickHereToSendQuotation_TextView;

    //edittext for details
    @BindView(R.id.remarksAddFollowUpDetails_EditText)
    TextView remarksAddFollowUpDetails_EditText;
    @BindView(R.id.emailAddFollowUpDetails_TextView)
    TextView emailAddFollowUpDetails_TextView;
    @BindView(R.id.addressAddFollowUpDetails_TextView)
    TextView addressAddFollowUpDetails_TextView;

    //appointment Details
    @BindView(R.id.appointmentTypeAddFollowUp_Spinner)
    Spinner appointmentTypeAddFollowUp_Spinner;
    @BindView(R.id.appointmentStatusAddFollowUp_Spinner)
    Spinner appointmentStatusAddFollowUp_Spinner;
    @BindView(R.id.appointmentDateAddFollowUp_txtView)
    TextView appointmentDateAddFollowUp_txtView;
    @BindView(R.id.appointmentTimeAddFollowUp_txtView)
    TextView appointmentTimeAddFollowUp_txtView;

    //Escalaltion Detail
    @BindView(R.id.escalationType_Spinner)
    Spinner escalationType_Spinner;
    @BindView(R.id.escalationRemark_editText)
    EditText escalationRemark_editText;

    //Other Details
    @BindView(R.id.interestedInFinanceOtherDetails_Spinner)
    Spinner interestedInFinanceOtherDetails_Spinner;
    @BindView(R.id.interestedInInsuranceOtherDetails_Spinner)
    Spinner interestedInInsuranceOtherDetails_Spinner;
    @BindView(R.id.interestedInAccessoriesOtherDetails_Spinner)
    Spinner interestedInAccessoriesOtherDetails_Spinner;
    @BindView(R.id.interestedInEWOtherDetails_Spinner)
    Spinner interestedInEWOtherDetails_Spinner;

    //Finance Details
    @BindView(R.id.financeDetails_cardView)
    CardView financeDetails_cardView;
    @BindView(R.id.customerTypeFinanceOtherDetails_Spinner)
    Spinner customerTypeFinanceOtherDetails_Spinner;
    @BindView(R.id.corporateNameFinanceOtherDetails_Spinner)
    Spinner corporateNameFinanceOtherDetails_Spinner;
    @BindView(R.id.customerDesignation_editText)
    EditText customerDesignation_editText;

    //edittext for old car details
    @BindView(R.id.kmsOldCarDetails_EditText)
    TextView kmsOldCarDetails_EditText;
    @BindView(R.id.fuelTypeOldCarDetails_Spinner)
    Spinner fuelTypeOldCarDetails_Spinner;
    @BindView(R.id.evaluationWithinDaysOldCarDetails_rl)
    RelativeLayout evaluationWithinDaysOldCarDetails_rl;
    @BindView(R.id.evaluationWithinDaysOldCarDetails_Spinner)
    Spinner evaluationWithinDaysOldCarDetails_Spinner;
    @BindView(R.id.fuelTypeOldCarDetails_rl)
    RelativeLayout fuelTypeOldCarDetails_rl;
    @BindView(R.id.colorOldCarDetails_EditText)
    EditText colorOldCarDetails_EditText;

    @BindView(R.id.registrationNoOldCarDetails_EditText)
    EditText registrationNoOldCarDetails_EditText;
    @BindView(R.id.quotePriceOldCarDetails_EditText)
    EditText quotePriceOldCarDetails_EditText;
    @BindView(R.id.expectedPriceOldCarDetails_EditText)
    EditText expectedPriceOldCarDetails_EditText;

    @BindView(R.id.colorOldCarDetails_rl)
    RelativeLayout colorOldCarDetails_rl;
    @BindView(R.id.clickhereSendBroucher_checkBox)
    CheckBox clickhereSendBroucher_checkBox;

    //Add Escalation
    @BindView(R.id.addEscalation_btn)
    TextView addEscalation_btn;
    //add Escalation CardvIew
    @BindView(R.id.escalationDetail_cardView)
    CardView escalationDetail_cardView;

    AlertDialog altDialog;
    TextView cancelEscalationFilter_txtView, applyEscalationFilter_txtView;
    EditText escalationRemark_et;
    Spinner escalationTypeFilter_spinner;
    TextView alreadySeleactedEscalationLevel1_txtView, escalationValueLevel1_tv, alreadySeleactedEscalationLevel2_txtView, escalationValueLevel2_tv;
    TextView alreadySeleactedEscalationLevel3_txtView, escalationValueLevel3_tv;
    LinearLayout escalationLevel3_ll, escalationLevel2_ll, escalationLevel1_ll;
    String escalationRemark_value;

    //Presenter
    AddFollowUpPresenter addFollowUpPresenter;
    Boolean TransferLeadValid, QuotationLeadValid;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.buyerType_RelativeLayout)
    LinearLayout buyerType_RelativeLayout;
    @BindView(R.id.otherDetails_cardView)
    CardView otherDetails_cardView;

    //evaltaion Transfer
    @BindView(R.id.assignToEvaluationAddFollowUpDetails_Spinner)
    Spinner assignToEvaluationAddFollowUpDetails_Spinner;
    @BindView(R.id.evaluationlocationAddFollowUpDetails_Spinner)
    Spinner evaluationlocationAddFollowUpDetails_Spinner;
    @BindView(R.id.evaluationProcessAddFollowUpDetails_Spinner)
    Spinner evaluationProcessAddFollowUpDetails_Spinner;
    @BindView(R.id.evaluationLocationDetails_cardView)
    CardView evaluationLocationDetails_cardView;

    //CheckBox for Visibility
    @BindView(R.id.clickhereAppointmentDetails_checkBox)
    CheckBox clickhereAppointmentDetails_checkBox;
    @BindView(R.id.clickhereOtherDetails_checkBox)
    CheckBox clickhereOtherDetails_checkBox;
    @BindView(R.id.clickhereQuotationDetails_checkBox)
    CheckBox clickhereQuotationDetails_checkBox;
    @BindView(R.id.clickhereTransferLocation_checkBox)
    CheckBox clickhereTransferLocation_checkBox;
    @BindView(R.id.clickhereEvaluationTransferDetails_checkBox)
    CheckBox clickhereEvaluationTransferDetails_checkBox;

    @BindView(R.id.appointmentDetailsAddFollowUpSpinner_RelativeLayout)
    RelativeLayout appointmentDetailsAddFollowUpSpinner_RelativeLayout;
    @BindView(R.id.otherDetails_RelativeLayout)
    RelativeLayout otherDetails_RelativeLayout;
    @BindView(R.id.clickHereToSendQuotationDetails_RelativeLayout)
    RelativeLayout clickHereToSendQuotationDetails_RelativeLayout;
    @BindView(R.id.clickHereToTransferLocationDetails_RelativeLayout)
    RelativeLayout clickHereToTransferLocationDetails_RelativeLayout;
    @BindView(R.id.clickHereToEvaluationTransferDetails_RelativeLayout)
    RelativeLayout clickHereToEvaluationTransferDetails_RelativeLayout;

    @BindView(R.id.evaluationTransferDetails_RelativeLayout)
    RelativeLayout evaluationTransferDetails_RelativeLayout;
    @BindView(R.id.clickHereToEvaluationTransferDetails_TextView)
    TextView clickHereToEvaluationTransferDetails_TextView;

    @BindView(R.id.appointmentDetailsAddFollowUp_cardView)
    CardView appointmentDetailsAddFollowUp_cardView;
    @BindView(R.id.followUpDetails_cardView)
    CardView followUpDetails_cardView;

    @BindView(R.id.customerCompaintFollowUpDetails_cardView)
    CardView customerCompaintFollowUpDetails_cardView;

    @BindView(R.id.complaintLocationCompaintAddFollowUpDetails_Spinner)
    Spinner complaintLocationCompaintAddFollowUpDetails_Spinner;
    @BindView(R.id.complaintTypeCompaintAddFollowUpDetails_Spinner)
    Spinner complaintTypeCompaintAddFollowUpDetails_Spinner;
    @BindView(R.id.regNoCompaintAddFollowUpDetails_EditText)
    EditText regNoCompaintAddFollowUpDetails_EditText;

    @BindView(R.id.regNoCompaintAddFollowUpDetails_rl)
    RelativeLayout regNoCompaintAddFollowUpDetails_rl;
    @BindView(R.id.complaintTypeCompaintAddFollowUpDetails_rl)
    RelativeLayout complaintTypeCompaintAddFollowUpDetails_rl;
    @BindView(R.id.complaintLocationCompaintAddFollowUpDetails_rl)
    RelativeLayout complaintLocationCompaintAddFollowUpDetails_rl;

    DatePickerDialog datePickerDialog;
    TimePickerDialog mTimePicker;

    //shared PREFERENCE
    SharedPreferenceManager preferenceManager;
    String process_shedPref;
    JSONParser jsonParser = new JSONParser();

    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final int REQUEST_PHONE_CALL = 1;

    CallingTaskNewLeadBean.Lead_Details beanList;

    //Spinner
    ArrayList<String> nextActionArrayList = new ArrayList<>();
    ArrayList<String> newModelArrayList = new ArrayList<>();
    ArrayList<String> newVariantArrayList = new ArrayList<>();
    ArrayList<String> quotationDescriptionArraylist = new ArrayList<>();
    ArrayList<String> quotationLocArrayList = new ArrayList<>();
    ArrayList<String> quotationModelArrayList = new ArrayList<>();
    ArrayList<String> quotationAccessoriesPackageArrayList = new ArrayList<>();
    ArrayList<String> transferProcessArrayList = new ArrayList<>();
    ArrayList<String> transferLocArrayList = new ArrayList<>();
    ArrayList<String> transferAssignToArrayList = new ArrayList<>();
    ArrayList<String> evaluationProcessArrayList = new ArrayList<>();
    ArrayList<String> evaluationLocArrayList = new ArrayList<>();
    ArrayList<String> evaluationAssignToArrayList = new ArrayList<>();

    //Arraylist for buy Used Car
    ArrayList<String> carMakeArrayList = new ArrayList<>();
    ArrayList<String> carModelArrayList = new ArrayList<>();

    //ArrayList for Old Car
    ArrayList<String> oldCarMakeArrayList = new ArrayList<>();
    ArrayList<String> oldCarModelArrayList = new ArrayList<>();

    //Arraylist for Customer Corporate Name
    ArrayList<String> customerCorporateArrayList = new ArrayList<>();

    //spinner Map
    Map<String, String> newModelMap = new HashMap<>();
    Map<String, String> newVariantMap = new HashMap<>();
    Map<String, String> transferProcessMap = new HashMap<>();
    Map<String, String> transferLocationMap = new HashMap<>();
    Map<String, String> transferAssignToMap = new HashMap<>();
    Map<String, String> evaluationLocationMap = new HashMap<>();
    Map<String, String> evaluationAssignToMap = new HashMap<>();

    //spinner Quotation
    Map<String, String> qModelIdMap = new HashMap<>();

    //Map for Buy Used Car
    Map<String, String> carMakeMap = new HashMap<>();
    Map<String, String> carModelMap = new HashMap<>();

    //Map for Old car
    Map<String, String> oldCarMakeMap = new HashMap<>();
    Map<String, String> oldCarModelMap = new HashMap<>();

    //String for selected Spinner
    String selectedFeedback, selectedFeedbackId, selectedNextAction, selectedNextActionId;
    String selectedBookingDaysId, selectedBookingDays, selectedNewVariant, selectedNewVariantID;
    String selectedNewModel, selectedNewModelId, callStatusString, callStatusStringId;
    String selectedProcess, selectedProcessId, selectedLocation, selectedLocationId, selectedTransferTo, selectedTransferToId;
    String selectedEvaluationProcess, selectedEvaluationProcessId, selectedEvaluationLocation, selectedEvaluationLocationId,selectedEvaluationAssign, selectedEvaluationAssignId;

    //String for buyer type
    String selectedBuyerType, selectedBuyerTypeId;
    String selectCompaintLocation, selectComplainLocationId, selectComplaintType, selectComplaintTypeId;

    //String for appointment type
    String selectAppointmentType, selectAppointmentTypeId, selectedAppointmentStatus, selectedAppointmentStatusId, selectedAppointmentRating, selectedAppointmentRatingId;
    String selectEscalationType, selectEscalationTypeId;
    String selectedInterestedInInsurance, selectedInterestedInInsuranceId, selectedInterestedInFinance, selectedInterestedInFinanceId, selectedInterestedInAccessories, selectedInterestedInAccessoriesId;
    String selectedInterestedInEw, selectedInterestedInEwId;
    //string for Financial Details
    String selectedCustomerType, selectedCustomerTypeId, selectedCorporateName, selectedCorporateNameId, selectedCustomerDesignation, selectedCustomerDesignationId;

    //String for Budget from
    String budgetFromString, budgetFromStringId, budgetToString, budgetToStringId;
    String carMakeString, carMakeStringId, carModelString, carModelStringId;

    //string for Old car Details
    String oldCarMakeString, oldCarMakeStringId, oldCarModelString, oldCarModelStringId;
    String evaluationWithInDaysString, evaluationWithInDaysStringId, fuelEvaluationString, fuelEvaluationStringId;
    String ownershipString, ownershipStringId, manfYearString, manfStringId;
    String accClaimString, accClaimStringId;

    //quotation location
    String locQuotationSpinner, locQuotationSpinnerId, modelQuotationSpinner, modelQuotationSpinnerId, descQuotationSpinner, descQuotationSpinnerId, quotationAccessoriesPackageSpinner, quotationAccessoriesPackageSpinnerId;
    String sendBroucher = "0";

    String enquiryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_followup_with_header);
        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialiseUI();
    }

    private void initialiseUI(){
        addFollowUpPresenter = new AddFollowUpPresenter(this);
        process_shedPref = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "");
        beanList = getIntent().getParcelableExtra("bean");

        if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            enquiryId = beanList.getComplaint_id().toString();
        }else {
            enquiryId = beanList.getEnq_id().toString();
        }

        //setBooking id here
        try {
            addFollowUpBookingId_TextView.setText(enquiryId);
            addFollowUpName_TextView.setText(beanList.getName().toString());
            addFollowUpContactNo_TextView.setText(beanList.getContact_no().toString());
            emailAddFollowUpDetails_TextView.setText(beanList.getEmail().toString());
            addressAddFollowUpDetails_TextView.setText(beanList.getAddress().toString());
            addFollowUpAlternateContactNo_TextView.setText(beanList.getAlternate_contact_no().toString());
        }catch(Exception e){
        }

        //td/hv date visibility
       // tdHvDateAddFollowUpDetails_TextView.setVisibility(GONE);
        //add Escalation card View
        escalationDetail_cardView.setVisibility(GONE);
        //finanace detail Card
        financeDetails_cardView.setVisibility(View.GONE);

        quotationDescriptionArraylist.add("Description");
        transferProcessArrayList.add("Transfer Process");
        transferLocArrayList.add("Transfer Location");
        transferAssignToArrayList.add("Transfer To");

        evaluationProcessArrayList.add("Evaluation Proces");
        evaluationLocArrayList.add("Evaluation Location");
        evaluationAssignToArrayList.add("Evaluation Assign To");

        quotationLocArrayList.add("Location");
        quotationModelArrayList.add("Model");
        quotationAccessoriesPackageArrayList.add("Accessories Package Name");

        addFollowUpPresenter.getFeedbackList(this);
        addFollowUpPresenter.getNextActionList(this,"");
        callStatus();
        addFollowUpPresenter.getQuotationLocation(this);
        addFollowUpPresenter.getQuotataionModel("0", this);
        addFollowUpPresenter.getQuotationDescription("0", "0", this);
        addFollowUpPresenter.getQuotationAccessoriesPackage("0", this);

        addFollowUpPresenter.getTransferProcess(this);
        addFollowUpPresenter.getTransferLocation("", this);
        addFollowUpPresenter.getTransferAssignTo("","", this);

        addFollowUpPresenter.getEvaluationLocation("", this);
        addFollowUpPresenter.getEvaluationAssignTo("","", this);

        addFollowUpPresenter.getCorporateName(this);

        appointmentDetailsAddFollowUpSpinner_RelativeLayout.setVisibility(GONE);
        otherDetails_RelativeLayout.setVisibility(GONE);
        clickHereToSendQuotationDetails_RelativeLayout.setVisibility(GONE);
        clickHereToTransferLocationDetails_RelativeLayout.setVisibility(GONE);
        clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(GONE);
        customerCompaintFollowUpDetails_cardView.setVisibility(GONE);

        getBookingWithInDatsDetails();
        getAppointmentType();
        getAppointmentStatus();
        getEscalaltionDetails();
        getInterestedInEw();
        getInterestedInAccessories();
        getInterestedInInsurance();
        getInterestedInFinance();
        getCustomerType();
        getEvaluationProcessDetails();
        setVisibility();
        newCarVisibility();
        oldCarDetailsVisibility();
        buyUsedCarVisibility();

        getComplaintLocationDetails();
        getComplaintTypeDetails();
    }

    private void setVisibility(){
        if (process_shedPref.equals("6")){
            getBuyerTypeDetails();
            clickhereToSendBroucher_cv.setVisibility(View.GONE);
            newCarDetails_cardView.setVisibility(View.GONE);
            buyerType_cardView.setVisibility(VISIBLE);
            oldCarDetails_cardView.setVisibility(GONE);
            buyUsedCarDetails_cardView.setVisibility(GONE);
            buyerTypeAddFollowUp_Spinner.setVisibility(VISIBLE);
            sendQuotation_cardView.setVisibility(VISIBLE);
            addFollowUpPresenter.getNewCarModel("1", this);
            addFollowUpPresenter.getNewVariant("", this);
            escalationDetail_cardView.setVisibility(GONE);

            clickHereToTransferLocationDetails_RelativeLayout.setVisibility(View.GONE);
            evaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
            clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(GONE);
            customerCompaintFollowUpDetails_cardView.setVisibility(GONE);

            registrationNoOldCarDetails_EditText.setVisibility(GONE);
            quotePriceOldCarDetails_EditText.setVisibility(GONE);
            expectedPriceOldCarDetails_EditText.setVisibility(GONE);

            String transfer_lead = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference("transfer_lead_newcar_view", "");
            if (transfer_lead.equals("1")){
                transferLocatinDetails_cardView.setVisibility(VISIBLE);
            }else{
                transferLocatinDetails_cardView.setVisibility(View.GONE);
            }
        }else if (process_shedPref.equals("7")){
            getBuyerTypeDetails();
            clickhereToSendBroucher_cv.setVisibility(View.GONE);
            newCarDetails_cardView.setVisibility(View.GONE);
            sendQuotation_cardView.setVisibility(GONE);
            oldCarDetails_cardView.setVisibility(GONE);
            buyUsedCarDetails_cardView.setVisibility(GONE);
            buyerType_cardView.setVisibility(VISIBLE);
            buyerTypeAddFollowUp_Spinner.setVisibility(VISIBLE);
            newCarDetails_cardView.setVisibility(View.GONE);
            escalationDetail_cardView.setVisibility(GONE);

            clickHereToTransferLocationDetails_RelativeLayout.setVisibility(View.GONE);
            evaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
            clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(GONE);
            customerCompaintFollowUpDetails_cardView.setVisibility(GONE);

            registrationNoOldCarDetails_EditText.setVisibility(GONE);
            quotePriceOldCarDetails_EditText.setVisibility(GONE);
            expectedPriceOldCarDetails_EditText.setVisibility(GONE);

            String transfer_lead = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference("transfer_lead_usedcar_view", "");
            if (transfer_lead.equals("1")){
                transferLocatinDetails_cardView.setVisibility(VISIBLE);
            }else{
                transferLocatinDetails_cardView.setVisibility(View.GONE);
            }
        }else if(process_shedPref.equals("8")){
        //    getBuyerTypeDetails();
            escalationDetail_cardView.setVisibility(GONE);
            clickhereToSendBroucher_cv.setVisibility(View.GONE);

            sendQuotation_cardView.setVisibility(GONE);
            buyUsedCarDetails_cardView.setVisibility(GONE);
            buyerType_cardView.setVisibility(View.GONE);
            buyerTypeAddFollowUp_Spinner.setVisibility(View.GONE);
            newCarDetails_cardView.setVisibility(View.GONE);
            otherDetails_cardView.setVisibility(View.GONE);
            financeDetails_cardView.setVisibility(GONE);
            transferProcessAddFollowUpDetails_Spinner.setVisibility(GONE);

            clickHereToEvaluationTransferDetails_TextView.setVisibility(GONE);
            clickHereToTransferLocationDetails_RelativeLayout.setVisibility(GONE);
            customerCompaintFollowUpDetails_cardView.setVisibility(GONE);

            transferlocationAddFollowUpDetails_Spinner.setVisibility(GONE);
            assignToAddFollowUpDetails_Spinner.setVisibility(GONE);
            evaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
            clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(GONE);

            registrationNoOldCarDetails_EditText.setVisibility(VISIBLE);
            quotePriceOldCarDetails_EditText.setVisibility(VISIBLE);
            expectedPriceOldCarDetails_EditText.setVisibility(VISIBLE);
            oldCarDetails_cardView.setVisibility(VISIBLE);
            addEscalation_btn.setVisibility(VISIBLE);
            buyerType_RelativeLayout.setVisibility(VISIBLE);

            String transfer_lead = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference("transfer_lead_evaluationcar_view", "");
            if (transfer_lead.equals("1")){
                transferLocatinDetails_cardView.setVisibility(VISIBLE);
                evaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
                clickHereToEvaluationTransferDetails_TextView.setVisibility(GONE);
                clickHereToTransferLocationDetails_RelativeLayout.setVisibility(GONE);
                clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
                transferlocationAddFollowUpDetails_Spinner.setVisibility(GONE);
                assignToAddFollowUpDetails_Spinner.setVisibility(GONE);
            }else{
                transferLocatinDetails_cardView.setVisibility(View.GONE);
            }
        }else if(process_shedPref.equals("9")){

            //    getBuyerTypeDetails();
            clickhereToSendBroucher_cv.setVisibility(View.GONE);
            sendQuotation_cardView.setVisibility(GONE);
            addEscalation_btn.setVisibility(GONE);

            transferProcessAddFollowUpDetails_Spinner.setVisibility(GONE);
            clickHereToEvaluationTransferDetails_TextView.setVisibility(GONE);

            registrationNoOldCarDetails_EditText.setVisibility(GONE);
            quotePriceOldCarDetails_EditText.setVisibility(GONE);
            expectedPriceOldCarDetails_EditText.setVisibility(GONE);

            regNoCompaintAddFollowUpDetails_rl.setVisibility(VISIBLE);
            complaintTypeCompaintAddFollowUpDetails_rl.setVisibility(VISIBLE);
            complaintLocationCompaintAddFollowUpDetails_rl.setVisibility(VISIBLE);

            //evaluationLocationDetails_cardView
           // transferLocatinDetails_cardView
            transferLocatinDetails_cardView.setVisibility(GONE);
            evaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
            clickHereToTransferLocationDetails_RelativeLayout.setVisibility(GONE);
            clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
            transferlocationAddFollowUpDetails_Spinner.setVisibility(GONE);
            assignToAddFollowUpDetails_Spinner.setVisibility(GONE);
            sendQuotation_cardView.setVisibility(GONE);
            financeDetails_cardView.setVisibility(GONE);
            otherDetails_cardView.setVisibility(GONE);
            escalationDetail_cardView.setVisibility(GONE);

            buyerType_RelativeLayout.setVisibility(GONE);
            buyerType_cardView.setVisibility(GONE);

            appointmentDetailsAddFollowUp_cardView.setVisibility(GONE);
            followUpDetails_cardView.setVisibility(GONE);
            customerCompaintFollowUpDetails_cardView.setVisibility(VISIBLE);
            followUpDetails_cardView.setVisibility(VISIBLE);

        }
        else{
            newCarDetails_cardView.setVisibility(GONE);
            oldCarDetails_cardView.setVisibility(GONE);
            buyUsedCarDetails_cardView.setVisibility(GONE);
            buyerTypeAddFollowUp_Spinner.setVisibility(GONE);
            buyerType_cardView.setVisibility(GONE);

            customerCompaintFollowUpDetails_cardView.setVisibility(GONE);

        }
    }

    @OnClick(R.id.addEscalation_btn)
    public void addEscalationnBtn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddFollowUpActivity.this);
        LayoutInflater li = (LayoutInflater) AddFollowUpActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.escalation_layout_followup_alert, null);

        escalationLevel3_ll = (LinearLayout) view.findViewById(R.id.escalationLevel3_ll);
        escalationLevel2_ll = (LinearLayout) view.findViewById(R.id.escalationLevel2_ll);
        escalationLevel1_ll = (LinearLayout) view.findViewById(R.id.escalationLevel1_ll);

        alreadySeleactedEscalationLevel1_txtView = (TextView) view.findViewById(R.id.alreadySeleactedEscalationLevel1_txtView);
        escalationValueLevel1_tv = (TextView) view.findViewById(R.id.escalationValueLevel1_tv);

        alreadySeleactedEscalationLevel2_txtView = (TextView) view.findViewById(R.id.alreadySeleactedEscalationLevel2_txtView);
        escalationValueLevel2_tv = (TextView) view.findViewById(R.id.escalationValueLevel2_tv);

        alreadySeleactedEscalationLevel3_txtView = (TextView) view.findViewById(R.id.alreadySeleactedEscalationLevel3_txtView);
        escalationValueLevel3_tv = (TextView) view.findViewById(R.id.escalationValueLevel3_tv);

        escalationTypeFilter_spinner = (Spinner) view.findViewById(R.id.escalationTypeFilter_spinner);
        cancelEscalationFilter_txtView = (TextView) view.findViewById(R.id.cancelEscalationFilter_txtView);
        applyEscalationFilter_txtView = (TextView) view.findViewById(R.id.applyEscalationFilter_txtView);
        escalationRemark_et = (EditText) view.findViewById(R.id.escalationRemark_et);
        escalationLevel1_ll.setVisibility(View.GONE);
        escalationLevel2_ll.setVisibility(View.GONE);
        escalationLevel3_ll.setVisibility(View.GONE);

        try {
            if (beanList.getEsc_level1().toString().equals("Yes")) {
                escalationLevel1_ll.setVisibility(VISIBLE);
                alreadySeleactedEscalationLevel1_txtView.setText("Escalation level 1 ");
                escalationValueLevel1_tv.setText(beanList.getEsc_level1_remark().toString());
            }
            if (beanList.getEsc_level2().toString().equals("Yes")) {
                escalationLevel2_ll.setVisibility(VISIBLE);
                alreadySeleactedEscalationLevel2_txtView.setText("Escalation level 2 ");
                escalationValueLevel2_tv.setText(beanList.getEsc_level2_remark().toString());
            }

                if (beanList.getEsc_level3().toString().equals("Yes")) {
                    escalationLevel3_ll.setVisibility(VISIBLE);
                    alreadySeleactedEscalationLevel3_txtView.setText("Escalation level 3 ");
                    escalationValueLevel3_tv.setText(beanList.getEsc_level3_remark().toString());
                }
            }catch (Exception e){
            }

        // final EditText etName = (EditText) view.findViewById(R.id.etName);
        ArrayList<String> escalationTypeArrayList = new ArrayList<>();
        escalationTypeArrayList.add("Escalation Type");
        escalationTypeArrayList.add("Escalation Level 1");
        escalationTypeArrayList.add("Escalation Level 2");
        escalationTypeArrayList.add("Escalation Level 3");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, escalationTypeArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        escalationTypeFilter_spinner.setAdapter(contactArrayAdapter);

        escalationTypeFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectEscalationType = (String) parent.getItemAtPosition(position);
                if (selectEscalationType.equals("Escalation Type")) {
                    selectEscalationTypeId = "";
                } else {
                    selectEscalationTypeId = selectEscalationType;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        escalationRemark_value = escalationRemark_et.getText().toString();
        cancelEscalationFilter_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altDialog.dismiss();
            }
        });
        applyEscalationFilter_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new addEscalationPopUp().execute();
                altDialog.dismiss();
            }
        });
        builder.setView(view);
        altDialog = builder.create();
        altDialog.show();
    }

    @OnClick(R.id.callContactNo_TextView)
    public void callCustomerAddFollowUp(){
        String number = addFollowUpContactNo_TextView.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(AddFollowUpActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AddFollowUpActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            } else {
                startActivity(callIntent);
            }
        }else {
            startActivity(callIntent);
        }
    }

    @OnCheckedChanged(R.id.clickhereSendBroucher_checkBox)
    public void getCheckBoxEvent(CompoundButton buttonView, boolean isChecked){
        if (isChecked) {
            sendBroucher = "1";
        } else {
            sendBroucher = "0";
         }
    }
    @OnCheckedChanged(R.id.clickhereAppointmentDetails_checkBox)
    public void getAppointmentCheckboxEvent(CompoundButton buttonView, boolean isChecked){
        if (isChecked) {
            appointmentDetailsAddFollowUpSpinner_RelativeLayout.setVisibility(VISIBLE);
        } else {
            appointmentDetailsAddFollowUpSpinner_RelativeLayout.setVisibility(GONE);
        }
    }
    @OnCheckedChanged(R.id.clickhereOtherDetails_checkBox)
    public void getOtherDetailsCheckBoxEvent(CompoundButton buttonView, boolean isChecked){
        if (isChecked) {
            otherDetails_RelativeLayout.setVisibility(VISIBLE);
        } else {
            otherDetails_RelativeLayout.setVisibility(View.GONE);
        }
    }
    @OnCheckedChanged(R.id.clickhereQuotationDetails_checkBox)
    public void getQuotationDetailsCheckBoxEvent(CompoundButton buttonView, boolean isChecked){
        if (isChecked) {
            clickHereToSendQuotationDetails_RelativeLayout.setVisibility(VISIBLE);
        } else {
            clickHereToSendQuotationDetails_RelativeLayout.setVisibility(View.GONE);
        }
    }
    @OnCheckedChanged(R.id.clickhereTransferLocation_checkBox)
    public void getTransferLocationCheckBoxEvent(CompoundButton buttonView, boolean isChecked){
        if (isChecked) {
        if (process_shedPref.equals("8")) {
            transferLocatinDetails_cardView.setVisibility(VISIBLE);
            evaluationTransferDetails_RelativeLayout.setVisibility(VISIBLE);
            clickHereToEvaluationTransferDetails_TextView.setVisibility(GONE);
            clickHereToTransferLocationDetails_RelativeLayout.setVisibility(GONE);
            clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(VISIBLE);
            transferlocationAddFollowUpDetails_Spinner.setVisibility(GONE);
            assignToAddFollowUpDetails_Spinner.setVisibility(GONE);
        }else{
            clickHereToTransferLocationDetails_RelativeLayout.setVisibility(VISIBLE);
            evaluationTransferDetails_RelativeLayout.setVisibility(VISIBLE);
            clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(VISIBLE);
            clickHereToEvaluationTransferDetails_TextView.setVisibility(VISIBLE);
            transferlocationAddFollowUpDetails_Spinner.setVisibility(VISIBLE);
            assignToAddFollowUpDetails_Spinner.setVisibility(VISIBLE);
        }
        } else {
            clickHereToTransferLocationDetails_RelativeLayout.setVisibility(View.GONE);
            evaluationTransferDetails_RelativeLayout.setVisibility(View.GONE);
            clickHereToEvaluationTransferDetails_RelativeLayout.setVisibility(GONE);
            clickHereToEvaluationTransferDetails_TextView.setVisibility(GONE);
            transferlocationAddFollowUpDetails_Spinner.setVisibility(GONE);
            assignToAddFollowUpDetails_Spinner.setVisibility(GONE);
        }
    }

    private boolean ValidateSendBroucher() {
        if (SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
            if ((selectedBuyerTypeId.equals("First")) || (selectedBuyerTypeId.equals("Exchange"))) {
                if ((selectedNewModelId.equals("")) && (sendBroucher.equals("1"))) {
                    makeText(AddFollowUpActivity.this, "Please Select New Car Details", Toast.LENGTH_SHORT).show();
                    return false;
                }else {
                    return true;
                }
            }else {
                makeText(AddFollowUpActivity.this, "Please Select New Car Details", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else
            return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.submitAddFollowUpDetails_Button)
    public void submitAddFollowUp() {
        if (process_shedPref.equals("6")) {
            if (ValidateFeedback()) {
                if (ValidateNextAction()) {
                    if (ValidateCallStatus()) {
                            if ((nfdAddFollowUpDetails_TextView.getText().toString().equals("")) &&((!(selectedNextAction.equals("Close"))) && (!(selectedNextAction.equals("Lead Transfer"))) &&  !(selectedNextAction.equals("Booked From Autovista")) && ! (selectedNextAction.equals("Purchase Done") ))) {
                                Toast.makeText(this, "Please Enter Next FollowUp Date", Toast.LENGTH_SHORT).show();
                        }else{
                                if (remarksAddFollowUpDetails_EditText.getText().length() > 0) {
                                    if (ValidateSendBroucher()){
                                        new CreateNewCarFollowUp().execute();
                                    }
                                } else {
                                    Toast.makeText(this, "Please Enter remark.", Toast.LENGTH_SHORT).show();
                                }
                         }
                    }
                }
            }
        } else if (process_shedPref.equals("7")) {
            if (ValidateFeedback()) {
                if (ValidateNextAction()) {
                    if (ValidateCallStatus()) {
                        if ((nfdAddFollowUpDetails_TextView.getText().toString().equals(""))&& ((!(selectedNextAction.equals("Close")))&& (!(selectedNextAction.equals("Lead Transfer"))) && !(selectedNextAction.equals("Booked From Autovista"))&& ! (selectedNextAction.equals("Purchase Done") ))) {
                            Toast.makeText(this, "Please Enter Next FollowUp Date", Toast.LENGTH_SHORT).show();
                        }else {
                            if (remarksAddFollowUpDetails_EditText.getText().length() > 0) {   //
                                new CreateUsedCarFollowUp().execute();
                             } else {
                                Toast.makeText(this, "Please Enter remark.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        } else if (process_shedPref.equals("8")) {
            if (ValidateFeedback()) {
                if (ValidateNextAction()) {
                    if (ValidateCallStatus()) {
                        if ((nfdAddFollowUpDetails_TextView.getText().toString().equals("")) && ((!(selectedNextAction.equals("Close"))) && (!(selectedNextAction.equals("Lead Transfer"))) && !(selectedNextAction.equals("Booked From Autovista")) && !(selectedNextAction.equals("Purchase Done")))) {
                            Toast.makeText(this, "Please Enter Next FollowUp Date", Toast.LENGTH_SHORT).show();
                        } else {
                            if (remarksAddFollowUpDetails_EditText.getText().length() > 0) {
                                new CreateEvaluationCarFollowUp().execute();
                            } else {
                                Toast.makeText(this, "Please Enter remark.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }else if (process_shedPref.equals("9")) {
            if (ValidateFeedback()) {
                if (ValidateNextAction()) {
                    if (ValidateCallStatus()) {
                        if ((nfdAddFollowUpDetails_TextView.getText().toString().equals("")) && ((!(selectedNextAction.equals("Close"))) && (!(selectedNextAction.equals("Lead Transfer"))) && !(selectedNextAction.equals("Booked From Autovista")) && !(selectedNextAction.equals("Purchase Done")))) {
                            Toast.makeText(this, "Please Enter Next FollowUp Date", Toast.LENGTH_SHORT).show();
                        } else {

                            new CreateComplaintFollowUp().execute();
                        }
                    }
                }
            }

        }
    }

    //validation field
    private boolean ValidateFeedback(){
        if (selectedFeedback.equals("Feedback")) {
            Utilities.showToast(AddFollowUpActivity.this,"Please Select Feedback");
            return false;
        }else {
            return true;
        }
    }
    private boolean ValidateNextAction(){
        if (selectedNextAction.equals("Next Action")) {
            Utilities.showToast(AddFollowUpActivity.this,"Please Select Next Action");
            return false;
            }else {
            return true;
        }
    }
  /*  private boolean ValidateTDHVDate(String nextAction){
        if ((tdHvDateAddFollowUpDetails_TextView.getText().toString().equals("TD/HV Date"))&& (!(nextAction.equals("TestDrive"))) && (!(nextAction.equals("HomeVisit")))){
            return true;
        }else if ((tdHvDateAddFollowUpDetails_TextView.getText().toString().equals("TD/HV Date"))&& nextAction.equals("TestDrive")){
            Utilities.showToast(AddFollowUpActivity.this,"Please Select TD/HV Date");
            return false;
        }else if ((tdHvDateAddFollowUpDetails_TextView.getText().toString().equals("TD/HV Date"))&& nextAction.equals("HomeVisit")) {
            Utilities.showToast(AddFollowUpActivity.this,"Please Select TD/HV Date");
            return false;
        }else{
            return true;
        }

    }*/
    private boolean ValidateNextFollowUpDate(String nextAction){
        if ((nfdAddFollowUpDetails_TextView.getText().toString().equals("Next FollowUp Date"))&& (!(nextAction.equals("Close"))) && (!(nextAction.equals("Booked From Autovista")))){
            Utilities.showToast(AddFollowUpActivity.this,"Please Select Next FollowUp Date");
            return false;
        }else if ((nfdAddFollowUpDetails_TextView.getText().toString().equals("Next FollowUp Date"))&& nextAction.equals("Next FollowUp Date")){
            Utilities.showToast(AddFollowUpActivity.this,"Please Select Next FollowUp Date");
            return true;
        }else if ((nfdAddFollowUpDetails_TextView.getText().toString().equals("Next FollowUp Date"))&& (nextAction.equals("Close"))){
            ValidateCallStatus();
            return true;
        }else{
            ValidateCallStatus();
            return true;
        }
    }
    private boolean ValidateCallStatus(){
        if (callStatusStringId.equals("")) {
            Utilities.showToast(AddFollowUpActivity.this,"Please Select Call Status");
            return false;
        }else {
             return true;
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

    @OnClick(R.id.addAuditorFollowUp_fab)
    public void addAuditor(){
        Intent addFollowUpIntent = new Intent(this, AddAuditorRemarkDetailActivity.class);
        addFollowUpIntent.putExtra("bean",beanList);
        addFollowUpIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(addFollowUpIntent);
    }

    @OnClick(R.id.auditorDetailFollowUp_fab)
    public void auditorDetails(){
        Intent addDetailsIntent = new Intent(this, AuditorDetailsActivity.class);
        addDetailsIntent.putExtra("bean",beanList);
        addDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(addDetailsIntent);
    }

    @OnClick(R.id.backButton_ImageView)
    public void backButtonFn(){
        super.onBackPressed();
    }
    @OnClick(R.id.modifyAddFollowUpDetails_Button)
    public void resetBtn(){
        ClearAllData();
        setVisibility();
    }

    private class addEscalationPopUp extends AsyncTask<String, JSONObject, JSONObject> {
        String escalation_type, escalation_remark, booking_id, process_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            booking_id = addFollowUpBookingId_TextView.getText().toString();
            escalation_type = selectEscalationTypeId;
            escalation_remark = escalationRemark_et.getText().toString();

            process_id = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.PROCESS_ID, "");

            pDialog = new ProgressDialog(AddFollowUpActivity.this);
            pDialog.setMessage("Adding Escalation...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {

                List<NameValuePair> params;
                params = new ArrayList<>();
                params.add(new BasicNameValuePair("booking_id", booking_id));
                params.add(new BasicNameValuePair("escalation_type", escalation_type));
                params.add(new BasicNameValuePair("escalation_remark", escalation_remark));
                params.add(new BasicNameValuePair("process_id", process_id));

                String url_add_followup= Constants.BASE_URL + Constants.ADD_ESCALATION_TYPE;
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
                }
                else {
                    Toast.makeText(AddFollowUpActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private class CreateEvaluationCarFollowUp extends AsyncTask<String, JSONObject, JSONObject>{
        String booking_id,email,contact,alternate_contact_no,address,feedback,nextAction;
        String comment,followUpdate,followUpTime,td_hv,days60,call_status;
        String appointment_type,appointment_date,appointment_time,appointment_address,appointment_status,appointment_rating,appointment_feedback;
        String interested_in_insurance,interested_in_finance,interested_in_accessories,interested_in_ew,old_make,old_model,ownership,mfg,km,claim;;
        String evaluation_within_days, fuel_type, color;
        String customer_occupation,customer_designation,customer_corporate_name;
        String user_id_session,process_id,role_session;
        String evaluation_process,evaluation_location,evaluation_assign_to;
        String registration_no,expected_price,quoted_price;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.USER_ID, "");
            process_id = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.PROCESS_ID, "");
            role_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.ROLE_ID, "");
            booking_id = addFollowUpBookingId_TextView.getText().toString();
            email = emailAddFollowUpDetails_TextView.getText().toString();
            contact = addFollowUpContactNo_TextView.getText().toString();
            alternate_contact_no =addFollowUpAlternateContactNo_TextView.getText().toString();
            address = addressAddFollowUpDetails_TextView.getText().toString();
            feedback = selectedFeedbackId;
            nextAction = selectedNextActionId;
            call_status = callStatusStringId;
            comment = remarksAddFollowUpDetails_EditText.getText().toString();
            followUpdate = nfdAddFollowUpDetails_TextView.getText().toString();
            followUpTime = nftAddFollowUpDetails_TextView.getText().toString();
            days60 = selectedBookingDaysId;

            appointment_type = selectAppointmentTypeId;
            appointment_date =appointmentDateAddFollowUp_txtView.getText().toString();
            appointment_time = appointmentTimeAddFollowUp_txtView.getText().toString();
            appointment_status = selectedAppointmentStatusId;
            appointment_rating = selectedAppointmentRatingId;

            customer_occupation =selectedCustomerTypeId;
            customer_designation = customerDesignation_editText.getText().toString();
            customer_corporate_name = selectedCorporateNameId;

            interested_in_insurance = selectedInterestedInInsuranceId;
            interested_in_finance = selectedInterestedInFinanceId;
            interested_in_accessories = selectedInterestedInAccessoriesId;
            interested_in_ew = selectedInterestedInEwId;

            old_make= oldCarMakeStringId;
            old_model= oldCarModelStringId;
            ownership= ownershipStringId;
            mfg= manfStringId;
            km= kmsOldCarDetails_EditText.getText().toString();
            claim = accClaimStringId;
            registration_no = registrationNoOldCarDetails_EditText.getText().toString();
            expected_price = expectedPriceOldCarDetails_EditText.getText().toString();
            quoted_price = quotePriceOldCarDetails_EditText.getText().toString();

            evaluation_within_days = evaluationWithInDaysStringId;
            fuel_type= fuelEvaluationStringId;
            color = colorOldCarDetails_EditText.getText().toString();

            evaluation_process = selectedEvaluationProcessId;
            evaluation_location = selectedEvaluationLocationId;
            evaluation_assign_to = selectedEvaluationAssignId;

            pDialog = new ProgressDialog(AddFollowUpActivity.this);
            pDialog.setMessage("Adding FollowUp...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            List<NameValuePair> params;
            params = new ArrayList<>();
            params.add(new BasicNameValuePair("user_id", user_id_session));
            params.add(new BasicNameValuePair("process_id", process_id));
            params.add(new BasicNameValuePair("role", role_session));

            params.add(new BasicNameValuePair("booking_id", booking_id));
            params.add(new BasicNameValuePair("email", email));

            params.add(new BasicNameValuePair("alternate_contact", alternate_contact_no));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("feedback", feedback));
            params.add(new BasicNameValuePair("nextaction", nextAction));
            params.add(new BasicNameValuePair("contactibility", call_status));
            params.add(new BasicNameValuePair("comment", comment));
            params.add(new BasicNameValuePair("followupdate", followUpdate));
            params.add(new BasicNameValuePair("followuptime", followUpTime));

            params.add(new BasicNameValuePair("appointment_type", appointment_type));
            params.add(new BasicNameValuePair("appointment_date", appointment_date));
            params.add(new BasicNameValuePair("appointment_time", appointment_time));
            params.add(new BasicNameValuePair("appointment_address", appointment_address));
            params.add(new BasicNameValuePair("appointment_status", appointment_status));
            params.add(new BasicNameValuePair("appointment_rating", appointment_rating));
            params.add(new BasicNameValuePair("appointment_feedback", appointment_feedback));

            params.add(new BasicNameValuePair("interested_in_insurance", interested_in_insurance));
            params.add(new BasicNameValuePair("interested_in_finance", interested_in_finance));
            params.add(new BasicNameValuePair("interested_in_accessories", interested_in_accessories));
            params.add(new BasicNameValuePair("interested_in_ew", interested_in_ew));

            params.add(new BasicNameValuePair("customer_occupation", customer_occupation));
            params.add(new BasicNameValuePair("customer_designation", customer_designation));
            params.add(new BasicNameValuePair("customer_corporate_name", customer_corporate_name));

            params.add(new BasicNameValuePair("days_booking", days60));

            params.add(new BasicNameValuePair("evaluation_location", evaluation_location));
            params.add(new BasicNameValuePair("evaluation_process", evaluation_process));
            params.add(new BasicNameValuePair("evaluation_assign_to", evaluation_assign_to));

            params.add(new BasicNameValuePair("old_make", old_make));
            params.add(new BasicNameValuePair("old_model", old_model));
            params.add(new BasicNameValuePair("ownership", ownership));
            params.add(new BasicNameValuePair("mfg", mfg));
            params.add(new BasicNameValuePair("km", km));
            params.add(new BasicNameValuePair("claim", claim));
            params.add(new BasicNameValuePair("registration_no", registration_no));
            params.add(new BasicNameValuePair("expected_price", expected_price));
            params.add(new BasicNameValuePair("quoted_price", quoted_price));

            params.add(new BasicNameValuePair("evaluation_within_days", evaluation_within_days));
            params.add(new BasicNameValuePair("color", color));
            params.add(new BasicNameValuePair("fuel_type", fuel_type));

            String url_add_followup= Constants.BASE_URL + Constants.ADD_FOLLOWUP_EVALUATION_CAR;
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
                    ClearAllData();
                    setVisibility();
                }
                else {
                    Toast.makeText(AddFollowUpActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                    setVisibility();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private class CreateNewCarFollowUp extends AsyncTask<String, JSONObject, JSONObject> {
        String booking_id, email, contact,alternate_contact_no, address, feedback,  nextAction, customer_name;
        String comment, followUpdate, followUpTime,  td_hv, days60,new_model, new_variant, transpro;
        String tlocation,tansferTo, qlocation, qmodelname, qdescription,accessories_package_name, call_status;
        String appointment_type, appointment_date, appointment_time, appointment_address, appointment_status, appointment_rating, appointment_feedback, buyerType;
        String interested_in_insurance, interested_in_finance, interested_in_accessories, interested_in_ew, old_make, old_model,ownership, mfg, km, claim;;
        String customer_occupation,customer_designation,customer_corporate_name;
        String user_id_session,process_id,role_session;
        String evaluation_process,evaluation_location,evaluation_assign_to, brochure;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.USER_ID, "");
            process_id = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.PROCESS_ID, "");
            role_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.ROLE_ID, "");

            booking_id = addFollowUpBookingId_TextView.getText().toString();
            email = emailAddFollowUpDetails_TextView.getText().toString();
            contact = addFollowUpContactNo_TextView.getText().toString();
            alternate_contact_no =addFollowUpAlternateContactNo_TextView.getText().toString();
            address = addressAddFollowUpDetails_TextView.getText().toString();

            customer_name = addFollowUpName_TextView.getText().toString();
            feedback = selectedFeedbackId;
            nextAction = selectedNextActionId;
            call_status = callStatusStringId;
            comment = remarksAddFollowUpDetails_EditText.getText().toString();
            followUpdate = nfdAddFollowUpDetails_TextView.getText().toString();
            followUpTime = nftAddFollowUpDetails_TextView.getText().toString();
            days60 = selectedBookingDaysId;

            appointment_type = selectAppointmentTypeId;
            appointment_date =appointmentDateAddFollowUp_txtView.getText().toString();
            appointment_time = appointmentTimeAddFollowUp_txtView.getText().toString();
            appointment_status = selectedAppointmentStatusId;
            appointment_rating = selectedAppointmentRatingId;

            customer_occupation =selectedCustomerTypeId;
            customer_designation = customerDesignation_editText.getText().toString();
            customer_corporate_name = selectedCorporateNameId;

            interested_in_insurance = selectedInterestedInInsuranceId;
            interested_in_finance = selectedInterestedInFinanceId;
            interested_in_accessories = selectedInterestedInAccessoriesId;
            interested_in_ew = selectedInterestedInEwId;

            if(sendBroucher.equals("0")){
                brochure = "Not Checked";
            }else if (sendBroucher.equals("1")){
                brochure = "Checked";
            }
            new_model = selectedNewModelId;
            new_variant =selectedNewVariantID;

            buyerType = selectedBuyerTypeId;

            old_make= oldCarMakeStringId;
            old_model= oldCarModelStringId;
            ownership= ownershipStringId;
            mfg= manfStringId;
            km= kmsOldCarDetails_EditText.getText().toString();
            claim = accClaimStringId;

            transpro = selectedProcessId;
            tlocation = selectedLocationId;
            tansferTo = selectedTransferToId;
            evaluation_process = selectedEvaluationProcessId;
            evaluation_location = selectedEvaluationLocationId;
            evaluation_assign_to = selectedEvaluationAssignId;

            qlocation = locQuotationSpinnerId;
            qmodelname = modelQuotationSpinnerId;
            qdescription = descQuotationSpinnerId;
            accessories_package_name = quotationAccessoriesPackageSpinnerId;

            pDialog = new ProgressDialog(AddFollowUpActivity.this);
            pDialog.setMessage("Adding FollowUp...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
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
            params.add(new BasicNameValuePair("phone", contact));
            params.add(new BasicNameValuePair("alternate_contact", alternate_contact_no));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("feedback", feedback));
            params.add(new BasicNameValuePair("nextaction", nextAction));
            params.add(new BasicNameValuePair("contactibility", call_status));
            params.add(new BasicNameValuePair("comment", comment));
            params.add(new BasicNameValuePair("followupdate", followUpdate));
            params.add(new BasicNameValuePair("followuptime", followUpTime));

            params.add(new BasicNameValuePair("appointment_type", appointment_type));
            params.add(new BasicNameValuePair("appointment_date", appointment_date));
            params.add(new BasicNameValuePair("appointment_time", appointment_time));
            params.add(new BasicNameValuePair("appointment_address", appointment_address));
            params.add(new BasicNameValuePair("appointment_status", appointment_status));
            params.add(new BasicNameValuePair("appointment_rating", appointment_rating));
            params.add(new BasicNameValuePair("appointment_feedback", appointment_feedback));

            params.add(new BasicNameValuePair("interested_in_insurance", interested_in_insurance));
            params.add(new BasicNameValuePair("interested_in_finance", interested_in_finance));
            params.add(new BasicNameValuePair("interested_in_accessories", interested_in_accessories));
            params.add(new BasicNameValuePair("interested_in_ew", interested_in_ew));

            params.add(new BasicNameValuePair("customer_occupation", customer_occupation));
            params.add(new BasicNameValuePair("customer_designation", customer_designation));
            params.add(new BasicNameValuePair("customer_corporate_name", customer_corporate_name));
            params.add(new BasicNameValuePair("days_booking", days60));

            params.add(new BasicNameValuePair("brochure", brochure));
            params.add(new BasicNameValuePair("new_model", new_model));
            params.add(new BasicNameValuePair("new_variant", new_variant));

            params.add(new BasicNameValuePair("tprocess", transpro));
            params.add(new BasicNameValuePair("tlocation", tlocation));
            params.add(new BasicNameValuePair("transfer_assign", tansferTo));

            params.add(new BasicNameValuePair("evaluation_location", evaluation_location));
            params.add(new BasicNameValuePair("evaluation_assign_to", evaluation_assign_to));

            params.add(new BasicNameValuePair("buyer_type", buyerType));
            params.add(new BasicNameValuePair("old_make", old_make));
            params.add(new BasicNameValuePair("old_model", old_model));
            params.add(new BasicNameValuePair("ownership", ownership));
            params.add(new BasicNameValuePair("mfg", mfg));
            params.add(new BasicNameValuePair("km", km));
            params.add(new BasicNameValuePair("claim", claim));

            params.add(new BasicNameValuePair("customer_name", customer_name));
            params.add(new BasicNameValuePair("qlocation", qlocation));
            params.add(new BasicNameValuePair("model_id", qmodelname));
            params.add(new BasicNameValuePair("description", qdescription));
            params.add(new BasicNameValuePair("accessories_package_name", accessories_package_name));

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
                    ClearAllData();
                    setVisibility();
                }
                else {
                    Toast.makeText(AddFollowUpActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                    setVisibility();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private class CreateComplaintFollowUp extends AsyncTask<String, JSONObject, JSONObject> {
        String user_id, process_id, role, complaint_id, email, alternate_contact, address, feedback, nextaction;
        String comment, followupupdate,followuptime, reg_no, complaint_type, location, contactibility;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            user_id= SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.USER_ID, "");
            process_id = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.PROCESS_ID, "");
            role = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.ROLE_ID, "");

            complaint_id = addFollowUpBookingId_TextView.getText().toString();
            email = emailAddFollowUpDetails_TextView.getText().toString();
            alternate_contact = addFollowUpAlternateContactNo_TextView.getText().toString();
            address = addressAddFollowUpDetails_TextView.getText().toString();
            feedback= selectedFeedbackId;
            nextaction= selectedNextActionId;
            comment = remarksAddFollowUpDetails_EditText.getText().toString();
            followupupdate = nfdAddFollowUpDetails_TextView.getText().toString();
            followuptime = nftAddFollowUpDetails_TextView.getText().toString();
            reg_no = regNoCompaintAddFollowUpDetails_EditText.getText().toString();
            complaint_type = selectComplaintTypeId;
            location= selectComplainLocationId;
            contactibility = callStatusString;

            pDialog = new ProgressDialog(AddFollowUpActivity.this);
            pDialog.setMessage("Adding FollowUp...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();
            params.add(new BasicNameValuePair("user_id", user_id));
            params.add(new BasicNameValuePair("process_id", process_id));
            params.add(new BasicNameValuePair("role", role));

            params.add(new BasicNameValuePair("complaint_id", complaint_id));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("alternate_contact", alternate_contact));
            params.add(new BasicNameValuePair("nextaction", nextaction));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("feedback", feedback));
            params.add(new BasicNameValuePair("followupupdate", followupupdate));
            params.add(new BasicNameValuePair("followuptime", followuptime));
            params.add(new BasicNameValuePair("comment", comment));
            params.add(new BasicNameValuePair("reg_no", reg_no));
            params.add(new BasicNameValuePair("complaint_type", complaint_type));

            params.add(new BasicNameValuePair("location", location));
            params.add(new BasicNameValuePair("contactibility", contactibility));

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
                    ClearAllData();
                    setVisibility();
                }
                else {
                    Toast.makeText(AddFollowUpActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                    setVisibility();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private class CreateUsedCarFollowUp extends AsyncTask<String, JSONObject, JSONObject> {
        String booking_id, email, contact, address, feedback,  nextAction;
        String comment, followUpdate, followUpTime,  td_hv, days60,new_model, new_variant, transpro;
        String tlocation,tansferTo, call_status;
        String appointment_type, appointment_date, appointment_time, appointment_address, appointment_status, appointment_rating, appointment_feedback;
        String interested_in_insurance, interested_in_finance, interested_in_accessories, interested_in_ew;
        String customer_occupation,customer_designation,customer_corporate_name;
        String buyerType, buy_make, buy_model, budget_from, budget_to,old_make, old_model,ownership, mfg, km, claim;
        String evaluation_process,evaluation_location,evaluation_assign_to;
        String user_id_session,process_id,role_session;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.USER_ID, "");
            process_id = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.PROCESS_ID, "");
            role_session = SharedPreferenceManager.getInstance(AddFollowUpActivity.this).getPreference(Constants.ROLE_ID, "");

            booking_id = addFollowUpBookingId_TextView.getText().toString();
            email = emailAddFollowUpDetails_TextView.getText().toString();
            contact =addFollowUpAlternateContactNo_TextView.getText().toString();
            address = addressAddFollowUpDetails_TextView.getText().toString();

            feedback = selectedFeedbackId;
            nextAction = selectedNextActionId;
            call_status = callStatusStringId;
            comment = remarksAddFollowUpDetails_EditText.getText().toString();
            followUpdate = nfdAddFollowUpDetails_TextView.getText().toString();
            followUpTime = nftAddFollowUpDetails_TextView.getText().toString();
            days60 = selectedBookingDaysId;

            appointment_type = selectAppointmentTypeId;
            appointment_date =appointmentDateAddFollowUp_txtView.getText().toString();
            appointment_time = appointmentTimeAddFollowUp_txtView.getText().toString();
            appointment_status = selectedAppointmentStatusId;
            appointment_rating = selectedAppointmentRatingId;

            interested_in_insurance = selectedInterestedInInsuranceId;
            interested_in_finance = selectedInterestedInFinanceId;
            interested_in_accessories = selectedInterestedInAccessoriesId;
            interested_in_ew = selectedInterestedInEwId;

            customer_occupation =selectedCustomerTypeId;
            customer_designation = customerDesignation_editText.getText().toString();
            customer_corporate_name = selectedCorporateNameId;

            new_model = selectedNewModelId;
            new_variant = selectedNewVariantID;

            transpro = selectedProcessId;
            tlocation = selectedLocationId;
            tansferTo = selectedTransferToId;

            evaluation_process = selectedEvaluationProcessId;
            evaluation_location = selectedEvaluationLocationId;
            evaluation_assign_to = selectedEvaluationAssignId;

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

            params.add(new BasicNameValuePair("contactibility", callStatusStringId));
            params.add(new BasicNameValuePair("comment", comment));
            params.add(new BasicNameValuePair("followupdate", followUpdate));
            params.add(new BasicNameValuePair("followuptime", followUpTime));

            params.add(new BasicNameValuePair("appointment_type", appointment_type));
            params.add(new BasicNameValuePair("appointment_date", appointment_date));
            params.add(new BasicNameValuePair("appointment_time", appointment_time));
            params.add(new BasicNameValuePair("appointment_address", appointment_address));
            params.add(new BasicNameValuePair("appointment_status", appointment_status));
            params.add(new BasicNameValuePair("appointment_rating", appointment_rating));
            params.add(new BasicNameValuePair("appointment_feedback", appointment_feedback));

            params.add(new BasicNameValuePair("interested_in_insurance", interested_in_insurance));
            params.add(new BasicNameValuePair("interested_in_finance", interested_in_finance));
            params.add(new BasicNameValuePair("interested_in_accessories", interested_in_accessories));
            params.add(new BasicNameValuePair("interested_in_ew", interested_in_ew));

            params.add(new BasicNameValuePair("customer_occupation", customer_occupation));
            params.add(new BasicNameValuePair("customer_designation", customer_designation));
            params.add(new BasicNameValuePair("customer_corporate_name", customer_corporate_name));

            params.add(new BasicNameValuePair("days_booking", days60));
            params.add(new BasicNameValuePair("new_model", new_model));
            params.add(new BasicNameValuePair("new_variant", new_variant));

            params.add(new BasicNameValuePair("tprocess", transpro));
            params.add(new BasicNameValuePair("tlocation", tlocation));
            params.add(new BasicNameValuePair("transfer_assign", tansferTo));
            params.add(new BasicNameValuePair("evaluation_location", evaluation_location));
            params.add(new BasicNameValuePair("evaluation_process", evaluation_process));
            params.add(new BasicNameValuePair("evaluation_assign_to", evaluation_assign_to));

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
                    ClearAllData();
                    setVisibility();
                }
                else {
                    Toast.makeText(AddFollowUpActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                    setVisibility();
                }
            } catch (Exception ignored) {
            }
        }
    }

    //spinner for other deatils
    public void getInterestedInFinance() {
        ArrayList<String> interestedInFinanceArrayList = new ArrayList<>();
        interestedInFinanceArrayList.add("Interested in Finance");
        interestedInFinanceArrayList.add("Yes");
        interestedInFinanceArrayList.add("No");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, interestedInFinanceArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedInFinanceOtherDetails_Spinner.setAdapter(contactArrayAdapter);

        interestedInFinanceOtherDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedInterestedInFinance = (String) parent.getItemAtPosition(position);
                if (selectedInterestedInFinance.equals("Interested in Finance")){
                    selectedInterestedInFinanceId = "";
                    financeDetails_cardView.setVisibility(GONE);
                }else {
                    selectedInterestedInFinanceId = selectedInterestedInFinance;
                    financeDetails_cardView.setVisibility(GONE);
                }

                if (selectedInterestedInFinance.equals("Yes")){
                    financeDetails_cardView.setVisibility(VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void getInterestedInInsurance() {
        ArrayList<String> interestedInInsuranceArrayList = new ArrayList<>();
        interestedInInsuranceArrayList.add("Interested in Insurance");
        interestedInInsuranceArrayList.add("Yes");
        interestedInInsuranceArrayList.add("No");

        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, interestedInInsuranceArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedInInsuranceOtherDetails_Spinner.setAdapter(contactArrayAdapter);

        interestedInInsuranceOtherDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedInterestedInInsurance = (String) parent.getItemAtPosition(position);
                if (selectedInterestedInInsurance.equals("Interested in Insurance")){
                    selectedInterestedInInsuranceId = "";
                }else{
                    selectedInterestedInInsuranceId = selectedInterestedInInsurance;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void getInterestedInAccessories() {
        ArrayList<String> interestedInAccessoriesArrayList = new ArrayList<>();
        interestedInAccessoriesArrayList.add("Interested in Accessories");
        interestedInAccessoriesArrayList.add("Yes");
        interestedInAccessoriesArrayList.add("No");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, interestedInAccessoriesArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedInAccessoriesOtherDetails_Spinner.setAdapter(contactArrayAdapter);

        interestedInAccessoriesOtherDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedInterestedInAccessories = (String) parent.getItemAtPosition(position);
                if (selectedInterestedInAccessories.equals("Interested in Accessories")){
                    selectedInterestedInAccessoriesId = "";
                }else{
                    selectedInterestedInAccessoriesId = selectedInterestedInAccessories;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void getInterestedInEw() {
        ArrayList<String> interestedInEwArrayList = new ArrayList<>();
        interestedInEwArrayList.add("Interested in Ew");
        interestedInEwArrayList.add("Yes");
        interestedInEwArrayList.add("No");

        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, interestedInEwArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedInEWOtherDetails_Spinner.setAdapter(contactArrayAdapter);

        interestedInEWOtherDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedInterestedInEw = (String) parent.getItemAtPosition(position);
                if (selectedInterestedInEw.equals("Interested in Ew")){
                    selectedInterestedInEwId = "";
                }else{
                    selectedInterestedInEwId = selectedInterestedInEw;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void getCustomerType() {
        ArrayList<String> customerTypeArrayList = new ArrayList<>();
        customerTypeArrayList.add("Customer Type");
        customerTypeArrayList.add("Salaried");
        customerTypeArrayList.add("Self Employed");

        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, customerTypeArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customerTypeFinanceOtherDetails_Spinner.setAdapter(contactArrayAdapter);
    }

    @OnItemSelected(R.id.customerTypeFinanceOtherDetails_Spinner)
    public void customerType(Spinner spinner, int position){
        selectedCustomerType = spinner.getSelectedItem().toString();
        if (selectedCustomerType.equals("Customer Type")){
            selectedCustomerTypeId = "";
        }else{
            selectedCustomerTypeId = selectedCustomerType;
        }
    }

    //spinner for appointment Type
    public void getAppointmentType() {
        ArrayList<String> appointmentTypeArrayList = new ArrayList<>();
        appointmentTypeArrayList.add("Appointment Type");
        appointmentTypeArrayList.add("Home Visit");
        appointmentTypeArrayList.add("Showroom Visit");
        appointmentTypeArrayList.add("Test Drive");
        appointmentTypeArrayList.add("Evaluation Allotted");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, appointmentTypeArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointmentTypeAddFollowUp_Spinner.setAdapter(contactArrayAdapter);

        appointmentTypeAddFollowUp_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectAppointmentType = (String) parent.getItemAtPosition(position);
                if (selectAppointmentType.equals("Appointment Type")){
                    selectAppointmentTypeId = "";
                }else{
                    selectAppointmentTypeId = selectAppointmentType;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void getEscalaltionDetails(){
    }
    //spinner for appointment Type
    public void getAppointmentStatus() {
        ArrayList<String> appointmentStatusArrayList = new ArrayList<>();
        appointmentStatusArrayList.add("Appointment Status");
        appointmentStatusArrayList.add("Conducted");
        appointmentStatusArrayList.add("Not Conducted");

        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, appointmentStatusArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointmentStatusAddFollowUp_Spinner.setAdapter(contactArrayAdapter);
        appointmentStatusAddFollowUp_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAppointmentStatus = (String) parent.getItemAtPosition(position);
                if (selectedAppointmentStatus.equals("Appointment Status"))
                {
                    selectedAppointmentStatusId = "";
                }else{
                    selectedAppointmentStatusId = selectedAppointmentStatus;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //Spinner for Booking within days
    public void getBuyerTypeDetails(){
        ArrayList<String> buyerTypeArrayList = new ArrayList<>();
        if (process_shedPref.equals("6")) {
            buyerTypeArrayList.add("Buyer Type");
            buyerTypeArrayList.add("First");
            buyerTypeArrayList.add("Exchange with New Car");
        }else if (process_shedPref.equals("7")){
            buyerTypeArrayList.add("Buyer Type");
            buyerTypeArrayList.add("Exchange with Old Car");
            buyerTypeArrayList.add("Buy used Car");
            buyerTypeArrayList.add("Sell Used Car");
        }
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, buyerTypeArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buyerTypeAddFollowUp_Spinner.setAdapter(contactArrayAdapter);
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
        if(process_shedPref.equals("8")) {
            getOwnershipDetails();
            getAnyAccClaimDetails();
            getManufacturingDetails();
            getFuelEvaluation();
            getEvaluationWithinDays();
            evaluationWithinDaysOldCarDetails_rl.setVisibility(VISIBLE);
            colorOldCarDetails_rl.setVisibility(VISIBLE);
            fuelTypeOldCarDetails_rl.setVisibility(VISIBLE);
            addFollowUpPresenter.getOldCarMake(this);
            addFollowUpPresenter.getOldCarModel("",this);
        }else {
            getOwnershipDetails();
            getAnyAccClaimDetails();
            getManufacturingDetails();
            evaluationWithinDaysOldCarDetails_rl.setVisibility(GONE);
            colorOldCarDetails_rl.setVisibility(GONE);
            fuelTypeOldCarDetails_rl.setVisibility(GONE);
            addFollowUpPresenter.getOldCarMake(this);
            addFollowUpPresenter.getOldCarModel("",this);
        }

    }
    public void callStatus(){
        ArrayList<String> callStatusArrayList = new ArrayList<>();
        callStatusArrayList.add("Call Status");
        callStatusArrayList.add("Connected");
        callStatusArrayList.add("Not Connected");
        ArrayAdapter<String> callStatusArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, callStatusArrayList);
        callStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        callStatusAddFollowUpDetails_Spinner.setAdapter(callStatusArrayAdapter);
        callStatusAddFollowUpDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                callStatusString = (String) parent.getItemAtPosition(position);
                if (callStatusString.equals("Call Status")) {
                    callStatusStringId = "";
                } else
                    callStatusStringId = callStatusString;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //buy car
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

    public void getEvaluationWithinDays(){
        ArrayList<String> evaluationWithinDaysArrayList = new ArrayList<>();
        evaluationWithinDaysArrayList.add("Evaluation WithIn Days");
        evaluationWithinDaysArrayList.add("30 Days");
        evaluationWithinDaysArrayList.add("60 Days");
        evaluationWithinDaysArrayList.add("90 Days");
        evaluationWithinDaysArrayList.add("Undecided");
        evaluationWithinDaysArrayList.add("Immediate");
        ArrayAdapter<String> budgetToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, evaluationWithinDaysArrayList);
        budgetToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        evaluationWithinDaysOldCarDetails_Spinner.setAdapter(budgetToArrayAdapter);
        evaluationWithinDaysOldCarDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                evaluationWithInDaysString = (String) parent.getItemAtPosition(position);
                if (evaluationWithInDaysString.equals("Evaluation WithIn Days")) {
                    evaluationWithInDaysStringId = "";
                } else if (evaluationWithInDaysString.equals("30 Days")) {
                    evaluationWithInDaysStringId = "30";
                } else if (evaluationWithInDaysString.equals("60 Days")) {
                    evaluationWithInDaysStringId = "60";
                } else if (evaluationWithInDaysString.equals("90 Days")) {
                    evaluationWithInDaysStringId = "90";
                } else if (evaluationWithInDaysString.equals("Undecided")) {
                    evaluationWithInDaysStringId = "Undecided";
                } else if (evaluationWithInDaysString.equals("Immediate")) {
                    evaluationWithInDaysStringId = "Immediate";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void getFuelEvaluation(){
        ArrayList<String> fuelEvaluationArrayList = new ArrayList<>();
        fuelEvaluationArrayList.add("Fuel Type");
        fuelEvaluationArrayList.add("Petrol");
        fuelEvaluationArrayList.add("Diesel");
        fuelEvaluationArrayList.add("CNG");
        ArrayAdapter<String> budgetToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, fuelEvaluationArrayList);
        budgetToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelTypeOldCarDetails_Spinner.setAdapter(budgetToArrayAdapter);
        fuelTypeOldCarDetails_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fuelEvaluationString = (String) parent.getItemAtPosition(position);
                if (fuelEvaluationString.equals("Fuel Type")) {
                    fuelEvaluationStringId = "";
                } else
                    fuelEvaluationStringId = fuelEvaluationString;
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
    @OnClick(R.id.appointmentDateAddFollowUp_txtView)
    public void appointmnetDate(){
        final Calendar fromdatecalenderObject = Calendar.getInstance();
        int BookedYear = fromdatecalenderObject.get(Calendar.YEAR);
        int BookedMonth = fromdatecalenderObject.get(Calendar.MONTH);
        int BookedDay = fromdatecalenderObject.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(AddFollowUpActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        appointmentDateAddFollowUp_txtView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }
    @OnClick(R.id.appointmentTimeAddFollowUp_txtView)
    public void appointmnetTime(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        mTimePicker = new TimePickerDialog(AddFollowUpActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                appointmentTimeAddFollowUp_txtView.setText(selectedHour%12 + ":" + selectedMinute + ":00" + ((selectedHour>=12) ? " PM" : " AM"));
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.show();
    }
    @OnClick(R.id.nftAddFollowUpDetails_TextView)
    public void nftTime(){
        final String time;
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        time = simpleDateFormat.format(mcurrentTime.getTime());
        mTimePicker = new TimePickerDialog(AddFollowUpActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                 nftAddFollowUpDetails_TextView.setText(selectedHour%12 + ":" + selectedMinute + ":00" + ((selectedHour>=12) ? " PM" : " AM"));
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.show();
    }
    //details spinner
    @Override
    public void showFeedbackSpinnerList(FeedbackListBean jsonObject) {
        ArrayList<String> feedbackArrayList = new ArrayList<>();
        feedbackArrayList.add("Feedback");
        for(int i=0;i<jsonObject.getSelect_feedback().size();i++)
        {
            try {
                feedbackArrayList.add(jsonObject.getSelect_feedback().get(i).getFeedbackStatusName());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview, feedbackArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feedbackStatusAddFollowUpDetails_Spinner.setAdapter(companiesArrayAdapter);
        if (jsonObject.getSelect_feedback().size()==1){
            feedbackStatusAddFollowUpDetails_Spinner.setSelection(1);
        }
    }
    @Override
    public void shownextActionAddFollowUpList(NextActionListBean jsonObject) {
        nextActionArrayList.clear();
        nextActionArrayList.add("Next Action");
        for(int i=0;i<jsonObject.getSelect_nextaction().size();i++)
        {
            try {
                nextActionArrayList.add(jsonObject.getSelect_nextaction().get(i).getNextActionName());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,nextActionArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nextActionAddFollowUpDetails_Spinner.setAdapter(companiesArrayAdapter);

        if (jsonObject.getSelect_nextaction().size()==1){
            nextActionAddFollowUpDetails_Spinner.setSelection(1);
        }
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
            }catch (Exception e) {
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
                newVariantMap.put(jsonObject.getSelect_car_variant().get(i).getVariant_id(), jsonObject.getSelect_car_variant().get(i).getVariant_name());
            }catch (Exception e) {
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
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,quotationLocArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationClickHereToSendQuotation_Spinner.setAdapter(companiesArrayAdapter);
        if (jsonObject.getQuotation_location().size()==1){
            locationClickHereToSendQuotation_Spinner.setSelection(1);
        }
    }
    @Override
    public void showQuoatationDescriptionSpinner(QuotationDescriptionBean jsonObject) {
        quotationDescriptionArraylist.clear();
        quotationDescriptionArraylist.add("Description");
        for(int i=0;i<jsonObject.getQuotation_description().size();i++)
        {
            try {
                quotationDescriptionArraylist.add(jsonObject.getQuotation_description().get(i).getVariant());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,quotationDescriptionArraylist);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        descriptionClickHereToSendQuotation_Spinner.setAdapter(companiesArrayAdapter);
        if (jsonObject.getQuotation_description().size()==1){
            descriptionClickHereToSendQuotation_Spinner.setSelection(1);
        }
    }
    @Override
    public void showQuotationModelSpinner(QuotationModelBean jsonObject) {
        quotationModelArrayList.clear();
        quotationModelArrayList.add("Model");
        for(int i=0;i<jsonObject.getQuotation_model_name().size();i++)
        {
            try {
                quotationModelArrayList.add(jsonObject.getQuotation_model_name().get(i).getModel());
                qModelIdMap.put(jsonObject.getQuotation_model_name().get(i).getModel_id(), jsonObject.getQuotation_model_name().get(i).getModel());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,quotationModelArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelClickHereToSendQuotation_Spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void showQuotationAccessoriesPackageSpinner(QuotationPackageBean jsonObject) {
        quotationAccessoriesPackageArrayList.clear();
        quotationAccessoriesPackageArrayList.add("Accessories Package Name");
        for(int i=0;i<jsonObject.getAccessories_package().size();i++)
        {
            try {
                quotationAccessoriesPackageArrayList.add(jsonObject.getAccessories_package().get(i).getPackage_name());
             }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,quotationAccessoriesPackageArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accessoriesPackageClickHereToSendQuotation_Spinner.setAdapter(companiesArrayAdapter);
        if (jsonObject.getAccessories_package().size()==1){
            accessoriesPackageClickHereToSendQuotation_Spinner.setSelection(1);
        }
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
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferProcessArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,transferProcessArrayList);
        transferProcessArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transferProcessAddFollowUpDetails_Spinner.setAdapter(transferProcessArrayAdapter);
        if (jsonObject.getAll_process().size()==1){
            transferProcessAddFollowUpDetails_Spinner.setSelection(1);
        }
    }
    @Override
    public void showTransferLocation(TransferLocationBean jsonObject) {
        transferLocArrayList.clear();
        transferLocArrayList.add("Transfer Location");
        for(int i=0;i<jsonObject.getSelect_transfer_location().size();i++)
        {
            try {
                transferLocArrayList.add(jsonObject.getSelect_transfer_location().get(i).getLocation());
                transferLocationMap.put(jsonObject.getSelect_transfer_location().get(i).getLocation_id(), jsonObject.getSelect_transfer_location().get(i).getLocation());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferLocArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,transferLocArrayList);
        transferLocArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transferlocationAddFollowUpDetails_Spinner.setAdapter(transferLocArrayAdapter);
        if (jsonObject.getSelect_transfer_location().size()==1){
            transferlocationAddFollowUpDetails_Spinner.setSelection(1);
        }
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
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,transferAssignToArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignToAddFollowUpDetails_Spinner.setAdapter(transferToArrayAdapter);
        if (jsonObject.getSelect_transfer_to_user().size()==1){
            assignToAddFollowUpDetails_Spinner.setSelection(1);
        }
    }
    @Override
    public void showEvaluationLocation(TransferLocationBean jsonObject) {
        evaluationLocArrayList.clear();
        evaluationLocArrayList.add("Evaluation Location");
        for(int i=0;i<jsonObject.getSelect_transfer_location().size();i++)
        {
            try {
                evaluationLocArrayList.add(jsonObject.getSelect_transfer_location().get(i).getLocation());
                evaluationLocationMap.put(jsonObject.getSelect_transfer_location().get(i).getLocation_id(), jsonObject.getSelect_transfer_location().get(i).getLocation());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferLocArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,evaluationLocArrayList);
        transferLocArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        evaluationlocationAddFollowUpDetails_Spinner.setAdapter(transferLocArrayAdapter);
        if (jsonObject.getSelect_transfer_location().size()==1){
            evaluationlocationAddFollowUpDetails_Spinner.setSelection(1);
        }
    }
    @Override
    public void showEvaluationAssignTo(TransferAssignToBean jsonObject) {
        evaluationAssignToArrayList.clear();
        evaluationAssignToArrayList.add("Evaluation Transfer To");
        for(int i=0;i<jsonObject.getSelect_transfer_to_user().size();i++)
        {
            try{
                evaluationAssignToArrayList.add(jsonObject.getSelect_transfer_to_user().get(i).getFname() + " "+ jsonObject.getSelect_transfer_to_user().get(i).getLname());
                evaluationAssignToMap.put(jsonObject.getSelect_transfer_to_user().get(i).getId(), jsonObject.getSelect_transfer_to_user().get(i).getFname() + " "+ jsonObject.getSelect_transfer_to_user().get(i).getLname());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,evaluationAssignToArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignToEvaluationAddFollowUpDetails_Spinner.setAdapter(transferToArrayAdapter);
        if (jsonObject.getSelect_transfer_to_user().size()==1){
            assignToEvaluationAddFollowUpDetails_Spinner.setSelection(1);
        }
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
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,carMakeArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMakeBuyUsedCarDetails_Spinner.setAdapter(transferToArrayAdapter);
    }
    @Override
    public void showCarModel(ModelBean jsonObject) {
        carModelArrayList.clear();
        carModelArrayList.add("Car Model");
        for(int i=0;i<jsonObject.getSelect_car_model().size();i++)
        {
            try{
                carModelArrayList.add(jsonObject.getSelect_car_model().get(i).getModel_name());
                carModelMap.put(jsonObject.getSelect_car_model().get(i).getModel_id(), jsonObject.getSelect_car_model().get(i).getModel_name());
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
        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,oldCarMakeArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMakeOldCarDetails_Spinner.setAdapter(transferToArrayAdapter);
    }
    @Override
    public void showOldCarModel(ModelBean jsonObject) {
        oldCarModelArrayList.clear();
        oldCarModelArrayList.add("Car Model");
        for(int i=0;i<jsonObject.getSelect_car_model().size();i++)
        {
            try{
                oldCarModelArrayList.add(jsonObject.getSelect_car_model().get(i).getModel_name());
                oldCarModelMap.put(jsonObject.getSelect_car_model().get(i).getModel_id(), jsonObject.getSelect_car_model().get(i).getModel_name());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,oldCarModelArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelOldCarDetails_Spinner.setAdapter(transferToArrayAdapter);
    }
    @Override
    public void showCustomerCorporateName(CorporateFinancialBean jsonObject) {
        customerCorporateArrayList.clear();
        customerCorporateArrayList.add("Corporate Name");
        for(int i=0;i<jsonObject.getCustomer_corporate_name().size();i++)
        {
            try{
                customerCorporateArrayList.add(jsonObject.getCustomer_corporate_name().get(i).getCorporate_name());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> transferToArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this,R.layout.spinner_textview,customerCorporateArrayList);
        transferToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        corporateNameFinanceOtherDetails_Spinner.setAdapter(transferToArrayAdapter);

    }

    @OnItemSelected(R.id.corporateNameFinanceOtherDetails_Spinner)
    public void corporateName(Spinner spinner, int position){
        selectedCorporateName = spinner.getSelectedItem().toString();
        if (selectedCorporateName.equals("Corporate Name")){
            selectedCorporateNameId = "";
        }else {
            selectedCorporateNameId = selectedCorporateName;
        }
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
    }

    public void getComplaintLocationDetails(){
        ArrayList<String> cLocArrayList = new ArrayList<>();
        cLocArrayList.add("Complaint Location");
        cLocArrayList.add("Kharghar Showroom");
        cLocArrayList.add("Bandra Showroom");
        cLocArrayList.add("Malad Showroom");
        cLocArrayList.add("Thane Nexa");
        cLocArrayList.add("Nexa Baner Showroom");
        cLocArrayList.add("Pune Magarpatta");
        cLocArrayList.add("Kharghar Workshop");
        cLocArrayList.add("Kalina WorkShop");
        cLocArrayList.add("Taloja WorkShop");
        cLocArrayList.add("Baner WorkShop");
        cLocArrayList.add("Pune Wadki");
        cLocArrayList.add("POC Mumbai");
        cLocArrayList.add("POC Pune");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, cLocArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complaintLocationCompaintAddFollowUpDetails_Spinner.setAdapter(contactArrayAdapter);
    }

    public void getComplaintTypeDetails(){
        ArrayList<String> cTypeArrayList = new ArrayList<>();
        cTypeArrayList.add("Complaint Type");
        cTypeArrayList.add("Sales");
        cTypeArrayList.add("Used Car");
        cTypeArrayList.add("Service");

        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, cTypeArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complaintTypeCompaintAddFollowUpDetails_Spinner.setAdapter(contactArrayAdapter);
    }
    //Spinner for Evaluation PROCESS
    public void getEvaluationProcessDetails(){
        ArrayList<String> evaluationArrayList = new ArrayList<>();
        evaluationArrayList.add("Evaluation");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(AddFollowUpActivity.this, R.layout.spinner_textview, evaluationArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        evaluationProcessAddFollowUpDetails_Spinner.setAdapter(contactArrayAdapter);
    }

    @OnItemSelected(R.id.evaluationProcessAddFollowUpDetails_Spinner)
    public void buyerTypeSpinner(Spinner spinner, int position){
        selectedEvaluationProcess = spinner.getSelectedItem().toString();
        if (selectedEvaluationProcess.equals("Evaluation")){
            selectedEvaluationProcessId = "8";
        }
    }
    @OnItemSelected(R.id.buyerTypeAddFollowUp_Spinner)
    public void evaluationProcess(Spinner spinner, int position){
        selectedBuyerType = spinner.getSelectedItem().toString();
        //     selectedDateTypeId = String.valueOf(position);
                if (selectedBuyerType.equals("Buyer Type")){
                    selectedBuyerTypeId = "";
                }else if(selectedBuyerType.equals("First")){
                    selectedBuyerTypeId = "First";
                    clickhereToSendBroucher_cv.setVisibility(VISIBLE);
                    newCarDetails_cardView.setVisibility(VISIBLE);
                }else if (selectedBuyerType.equals("Exchange with New Car")){
                    selectedBuyerTypeId = "Exchange";
                    clickhereToSendBroucher_cv.setVisibility(VISIBLE);
                    newCarDetails_cardView.setVisibility(VISIBLE);
                    oldCarDetails_cardView.setVisibility(VISIBLE);
                    // sendBroucherUsedCar = "1";
                    newCarVisibility();
                    oldCarDetailsVisibility();
                    buyUsedCarDetails_cardView.setVisibility(GONE);
                }else if (selectedBuyerType.equals("Exchange with Old Car")){
                    selectedBuyerTypeId = "Exchange with Old Car";
                    clickhereToSendBroucher_cv.setVisibility(GONE);
                    newCarDetails_cardView.setVisibility(View.GONE);
                    oldCarDetails_cardView.setVisibility(VISIBLE);
                    buyUsedCarDetails_cardView.setVisibility(VISIBLE);
                    //   sendBroucherUsedCar = "0";
                    buyUsedCarVisibility();
                    oldCarDetailsVisibility();
                }else if (selectedBuyerType.equals("Buy used Car")){
                    selectedBuyerTypeId = "Buy used Car";
                    clickhereToSendBroucher_cv.setVisibility(GONE);
                    newCarDetails_cardView.setVisibility(View.GONE);
                    oldCarDetails_cardView.setVisibility(View.GONE);
                    buyUsedCarDetails_cardView.setVisibility(VISIBLE);
                    //    sendBroucherUsedCar = "0";
                    buyUsedCarVisibility();
                }else if (selectedBuyerType.equals("Sell Used Car")) {
                    selectedBuyerTypeId = "Sell Used Car";
                    clickhereToSendBroucher_cv.setVisibility(GONE);
                    newCarDetails_cardView.setVisibility(View.GONE);
                    oldCarDetails_cardView.setVisibility(VISIBLE);
                    buyUsedCarDetails_cardView.setVisibility(View.GONE);
                    //   sendBroucherUsedCar = "0";
                    oldCarDetailsVisibility();
                }
    }
    @OnItemSelected(R.id.booking60DaysAddFollowUpDetails_Spinner)
    public void bookingDays(Spinner spinner, int position){
        selectedBookingDays = spinner.getSelectedItem().toString();
        if (selectedBookingDays.equals("Booking WithIn Days")){
            selectedBookingDaysId = "";
        }else if (selectedBookingDays.equals("30 days")){
            selectedBookingDaysId = "30";
        }else if(selectedBookingDays.equals("60 days")){
            selectedBookingDaysId = "60";
        }else if(selectedBookingDays.equals("90 days")){
            selectedBookingDaysId = "90";
        }else if(selectedBookingDays.equals("Undecided")){
            selectedBookingDaysId = "Undecided";
        }else if(selectedBookingDays.equals("Just Researching")){
            selectedBookingDaysId = "Just Researching";
        }else if(selectedBookingDays.equals("Immediate")){
            selectedBookingDaysId = "Immediate";
        }
    }

    //Complaint Location
    @OnItemSelected(R.id.complaintLocationCompaintAddFollowUpDetails_Spinner)
    public void complaintLocation(Spinner spinner, int position){
        selectCompaintLocation = spinner.getSelectedItem().toString();
        if (selectCompaintLocation.equals("Complaint Location")){
            selectComplainLocationId = "";
        }else{
            selectComplainLocationId = selectCompaintLocation;
        }
    }
    //Complaint Type
    @OnItemSelected(R.id.complaintTypeCompaintAddFollowUpDetails_Spinner)
    public void complaintType(Spinner spinner, int position){
        selectComplaintType = spinner.getSelectedItem().toString();
        if (selectComplaintType.equals("Complaint Type")){
            selectComplaintTypeId = "";
        }else{
            selectComplaintTypeId = selectComplaintType;
        }
    }

    //details
    @OnItemSelected(R.id.feedbackStatusAddFollowUpDetails_Spinner)
    public void companyNameSelected(Spinner spinner, int position)
    {
        selectedFeedback = spinner.getSelectedItem().toString();
        if (selectedFeedback.equals("Feedback")){
            selectedFeedbackId = "";
        }else if (!(selectedFeedback.equals("Feedback"))){
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
        if (selectedNextAction.equals("Next Action")){
            selectedNextActionId = "";
        }else if (!(selectedNextAction.equals("Next Action"))){
            selectedNextActionId = selectedNextAction;
        }
        if (selectedNextAction.equals("Booked From Autovista") || selectedNextAction.equals("Purchase Done") || selectedNextAction.equals("Close") || selectedNextAction.equals("Lead Transfer")){
            nfdAddFollowUpDetails_TextView.setVisibility(View.GONE);
            nftAddFollowUpDetails_TextView.setVisibility(View.GONE);
        }else{
            nfdAddFollowUpDetails_TextView.setVisibility(VISIBLE);
            nftAddFollowUpDetails_TextView.setVisibility(VISIBLE);
    }
    }

    //new car details
    @OnItemSelected(R.id.newCarVariantDetails_Spinner)
    public void newCarVariantSelected(Spinner spinner, int position)
    {
        selectedNewVariant = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : newVariantMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedNewVariant.equals("New Car Variant")) {
                selectedNewVariantID = "";
            } else if (value.equals(selectedNewVariant)) {
                    selectedNewVariantID = (String) key;
            }
        }
    }
    @OnItemSelected(R.id.newCarModelDetails_Spinner)
    public void newCarModelSelected(Spinner spinner, int position)
    {
        selectedNewModel = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : newModelMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedNewModel.equals("New Car Model")) {
                selectedNewModelId = "";
            } else {
                if (value.equals(selectedNewModel)) {
                    selectedNewModelId = (String) key;

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
        for (Map.Entry<String, String> e : qModelIdMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (modelQuotationSpinner.equals("Model")) {
                modelQuotationSpinnerId = "";
            } else {
                if (value.equals(modelQuotationSpinner)) {
                    modelQuotationSpinnerId = (String) key;
                }
            }
        }
        addFollowUpPresenter.getQuotationDescription(locQuotationSpinnerId,modelQuotationSpinnerId, this);
        addFollowUpPresenter.getQuotationAccessoriesPackage(modelQuotationSpinnerId, this);
    }
    @OnItemSelected(R.id.locationClickHereToSendQuotation_Spinner)
    public void quotationLocSelected(Spinner spinner, int position)
    {
        locQuotationSpinner = spinner.getSelectedItem().toString();
        if(locQuotationSpinner.equals("Location")){
           locQuotationSpinnerId = "";
        }else if(!(locQuotationSpinner.equals("Location"))){
           locQuotationSpinnerId = locQuotationSpinner;
           addFollowUpPresenter.getQuotataionModel(locQuotationSpinnerId, this);
        }else {
           locQuotationSpinnerId = "";
           addFollowUpPresenter.getQuotataionModel(locQuotationSpinnerId, this);
        }
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
    @OnItemSelected(R.id.accessoriesPackageClickHereToSendQuotation_Spinner)
    public void quotationAccessoriesSelected(Spinner spinner, int position)
    {
        quotationAccessoriesPackageSpinner = spinner.getSelectedItem().toString();
        if(quotationAccessoriesPackageSpinner.equals("Accessories Package Name")){
            quotationAccessoriesPackageSpinnerId = "";
        }else if(!(quotationAccessoriesPackageSpinner.equals("Accessories Package Name"))){
            quotationAccessoriesPackageSpinnerId = quotationAccessoriesPackageSpinner;
        }else {
            quotationAccessoriesPackageSpinnerId = "";
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
            if (selectedLocation.equals("Transfer Location")) {
                selectedLocationId = "";
                addFollowUpPresenter.getTransferAssignTo(selectedProcessId,selectedLocationId, this);
            } else {
                if (value.equals(selectedLocation)) {
                    selectedLocationId = (String) key;
                    Log.i("Selected Model : ", selectedLocationId);
                    addFollowUpPresenter.getTransferAssignTo(selectedProcessId,selectedLocationId, this);
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
            if (selectedTransferTo.equals("Transfer To")) {
                selectedTransferToId = "";
            } else {
                if (value.equals(selectedTransferTo)) {
                    selectedTransferToId = (String) key;
                }
            }
        }
    }
    @OnItemSelected(R.id.evaluationlocationAddFollowUpDetails_Spinner)
    public void evaluationLocationSelected(Spinner spinner, int position)
    {
        selectedEvaluationLocation = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : evaluationLocationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedEvaluationLocation.equals("Evaluation Location")) {
                selectedEvaluationLocationId = "";
                addFollowUpPresenter.getEvaluationAssignTo("8",selectedEvaluationLocationId, this);
            } else {
                if (value.equals(selectedEvaluationLocation)) {
                    selectedEvaluationLocationId = (String) key;
                    addFollowUpPresenter.getEvaluationAssignTo("8",selectedEvaluationLocationId, this);
                }
            }
        }
    }
    @OnItemSelected(R.id.assignToEvaluationAddFollowUpDetails_Spinner)
    public void evaluationAssignToSelected(Spinner spinner, int position)
    {
        selectedEvaluationAssign = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : evaluationAssignToMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (selectedEvaluationAssign.equals("Evaluation Transfer To")) {
                selectedEvaluationAssignId = "";
            } else {
                if (value.equals(selectedEvaluationAssign)) {
                    selectedEvaluationAssignId = (String) key;
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
                addFollowUpPresenter.getOldCarModel(oldCarMakeStringId,this);
            }else
                if (value.equals(oldCarMakeString)) {
                    oldCarMakeStringId = (String) key;
                    addFollowUpPresenter.getOldCarModel(oldCarMakeStringId,this);
            }
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
            }else if (value.equals(oldCarModelString)) {
                    oldCarModelStringId = (String) key;
                }
        }
    }

    private void ClearAllData(){
        locationClickHereToSendQuotation_Spinner.setSelection(0);
        descriptionClickHereToSendQuotation_Spinner.setSelection(0);
        newCarModelDetails_Spinner.setSelection(0);
        budgetToBuyUsedCarDetails_Spinner.setSelection(0);
        budgetFromBuyUsedCarDetails_Spinner.setSelection(0);
        carMakeBuyUsedCarDetails_Spinner.setSelection(0);
        carModelBuyUsedCarDetails_Spinner.setSelection(0);
        carModelClickHereToSendQuotation_Spinner.setSelection(0);
        carMakeOldCarDetails_Spinner.setSelection(0);
        feedbackStatusAddFollowUpDetails_Spinner.setSelection(0);
        nextActionAddFollowUpDetails_Spinner.setSelection(0);
        assignToAddFollowUpDetails_Spinner.setSelection(0);
        anyAssidentailClaimOldCarDetails_Spinner.setSelection(0);

        booking60DaysAddFollowUpDetails_Spinner.setSelection(0);
        newCarVariantDetails_Spinner.setSelection(0);
        transferlocationAddFollowUpDetails_Spinner.setSelection(0);
        carModelOldCarDetails_Spinner.setSelection(0);
        ownershipOldCarDetails_Spinner.setSelection(0);
        buyerTypeAddFollowUp_Spinner.setSelection(0);
        colorOldCarDetails_EditText.setText("");
        evaluationWithinDaysOldCarDetails_Spinner.setSelection(0);
        fuelTypeOldCarDetails_Spinner.setSelection(0);
        nfdAddFollowUpDetails_TextView.setText("");
        nftAddFollowUpDetails_TextView.setText("");
        mfgOldCarDetails_Spinner.setSelection(0);
        addFollowUpBookingId_TextView.setText("");
        remarksAddFollowUpDetails_EditText.setText("");
        kmsOldCarDetails_EditText.setText("");
        clickhereSendBroucher_checkBox.setChecked(false);

        oldCarDetails_cardView.setVisibility(GONE);
        buyUsedCarDetails_cardView.setVisibility(GONE);
        buyerType_cardView.setVisibility(VISIBLE);
        buyerTypeAddFollowUp_Spinner.setVisibility(VISIBLE);
        newCarDetails_cardView.setVisibility(View.GONE);
        expectedPriceOldCarDetails_EditText.setText("");
        quotePriceOldCarDetails_EditText.setText("");
        registrationNoOldCarDetails_EditText.setText("");

       appointmentTypeAddFollowUp_Spinner.setSelection(0);
       appointmentStatusAddFollowUp_Spinner.setSelection(0);
       appointmentDateAddFollowUp_txtView.setText("");
       appointmentTimeAddFollowUp_txtView.setText("");

       escalationRemark_editText.setText("");
       escalationType_Spinner.setSelection(0);

       interestedInFinanceOtherDetails_Spinner.setSelection(0);
       interestedInInsuranceOtherDetails_Spinner.setSelection(0);
       interestedInAccessoriesOtherDetails_Spinner.setSelection(0);
       interestedInEWOtherDetails_Spinner.setSelection(0);

       evaluationProcessAddFollowUpDetails_Spinner.setSelection(0);
       evaluationlocationAddFollowUpDetails_Spinner.setSelection(0);
       assignToEvaluationAddFollowUpDetails_Spinner.setSelection(0);
       callStatusAddFollowUpDetails_Spinner.setSelection(0);
    }
}
