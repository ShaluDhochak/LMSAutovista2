package com.excell.lms.lmsautovista.View.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.Model.CustomerDetailsBean;
import com.excell.lms.lmsautovista.Presenter.CustomerDetailsPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerDetailsActivity extends AppCompatActivity implements IView.CustomerDetailsTaskView{

    CallingTaskNewLeadBean.Lead_Details beanList;

    @BindView(R.id.add_followUp_fab)
    FloatingActionButton add_followUp_fab;
    @BindView(R.id.followUpDetails_fab)
    FloatingActionButton followUpDetails_fab;
    @BindView(R.id.add_auditor_fab)
    FloatingActionButton add_auditor_fab;
    @BindView(R.id.auditor_details_fab)
    FloatingActionButton auditor_details_fab;


    @BindView(R.id.NavigationLeadname_TextView)
    TextView NavigationLeadname_TextView;
    @BindView(R.id.NavigationLeadeagernessDetails_TextView)
    TextView NavigationLeadeagernessDetails_TextView;
    @BindView(R.id.NavigationLeademail_TextView)
    TextView NavigationLeademail_TextView;
    @BindView(R.id.NavigationLeadContactno_TextView)
    TextView NavigationLeadContactno_TextView;
    @BindView(R.id.NavigationLeadAltContactno_TextView)
    TextView NavigationLeadAltContactno_TextView;

    @BindView(R.id.followupByNotificationCustomerDetails_TextView)
    TextView followupByNotificationCustomerDetails_TextView;
    @BindView(R.id.newVaraintCustomerDetails_TextView)
    TextView newVaraintCustomerDetails_TextView;
    @BindView(R.id.newModelCustomerDetails_TextView)
    TextView newModelCustomerDetails_TextView;

    @BindView(R.id.navigationLeadBookedWithIn_textView)
    TextView navigationLeadBookedWithIn_textView;
    @BindView(R.id.feedbackStatusCustomerDetailsString_TextView)
    TextView feedbackStatusCustomerDetailsString_TextView;
    @BindView(R.id.NavigationLeadAddressModel_TextView)
    TextView NavigationLeadAddressModel_TextView;

    @BindView(R.id.NavigationLeadEnqID_TextView)
    TextView NavigationLeadEnqID_TextView;
    @BindView(R.id.nextActionCustomerDetailsString_TextView)
    TextView nextActionCustomerDetailsString_TextView;
    @BindView(R.id.callDateNotificationCustomerDetailsString_TextView)
    TextView callDateNotificationCustomerDetailsString_TextView;
    @BindView(R.id.nfdNotificationCustomerDetailsString_TextView)
    TextView nfdNotificationCustomerDetailsString_TextView;

    @BindView(R.id.callTimeNotificationCustomerDetailsString_TextView)
    TextView callTimeNotificationCustomerDetailsString_TextView;
    @BindView(R.id.callStatusNotificationCustomerDetailsString_TextView)
    TextView callStatusNotificationCustomerDetailsString_TextView;
    @BindView(R.id.remarkNotificationCustomerDetailsString_TextView)
    TextView remarkNotificationCustomerDetailsString_TextView;

    //appointmnet text
    @BindView(R.id.appointmentTimeCustomerDetailsString_TextView)
    TextView appointmentTimeCustomerDetailsString_TextView;
    @BindView(R.id.appointmentRatingCustomerDetailsString_TextView)
    TextView appointmentRatingCustomerDetailsString_TextView;
    @BindView(R.id.appointmentDateCustomerDetailsString_TextView)
    TextView appointmentDateCustomerDetailsString_TextView;
    @BindView(R.id.appointmentStatusCustomerDetailsString_TextView)
    TextView appointmentStatusCustomerDetailsString_TextView;
    @BindView(R.id.appointmentTypeUsedCustomerDetails_TextView)
    TextView appointmentTypeUsedCustomerDetails_TextView;
    @BindView(R.id.appointmentFeedbackCustomerDetailsString_TextView)
    TextView appointmentFeedbackCustomerDetailsString_TextView;

    //Other details Text
    @BindView(R.id.interestedInEwCustomerDetailsString_TextView)
    TextView interestedInEwCustomerDetailsString_TextView;
    @BindView(R.id.interestedInAccessoriesCustomerDetailsString_TextView)
    TextView interestedInAccessoriesCustomerDetailsString_TextView;
    @BindView(R.id.interestedInInsuranceCustomerDetailsString_TextView)
    TextView interestedInInsuranceCustomerDetailsString_TextView;
    @BindView(R.id.interestedInFinanceHeadingCustomerDetails_TextView)
    TextView interestedInFinanceHeadingCustomerDetails_TextView;

    //Finance Details
    @BindView(R.id.financeDetail_cardView)
    CardView financeDetail_cardView;
    @BindView(R.id.customerTypeFinanceHeadingCustomerDetails_TextView)
    TextView customerTypeFinanceHeadingCustomerDetails_TextView;
    @BindView(R.id.corporateNameFinanceCustomerDetailsString_TextView)
    TextView corporateNameFinanceCustomerDetailsString_TextView;
    @BindView(R.id.customerDesignationFinanceCustomerDetailsString_TextView)
    TextView customerDesignationFinanceCustomerDetailsString_TextView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.dialContactNotification_Button)
    ImageView dialContactNotification_Button;

    //layout for buyer type
    @BindView(R.id.newCar_cardView)
    CardView newCar_cardView;
    @BindView(R.id.usedCar_cardView)
    CardView usedCar_cardView;
    @BindView(R.id.newCarDetails_cardView)
    CardView newCarDetails_cardView;
    @BindView(R.id.oldCarDetails_cardView)
    CardView oldCarDetails_cardView;
    @BindView(R.id.buyUsedCarDetails_cardView)
    CardView buyUsedCarDetails_cardView;

    //Buyer Type
    @BindView(R.id.buyerTypeCarDetails_txtView)
    TextView buyerTypeCarDetails_txtView;
    @BindView(R.id.buyerTypeCarDetails_cardView)
    CardView buyerTypeCarDetails_cardView;

    //Old car details
    @BindView(R.id.oldModelUsedCustomerDetails_TextView)
    TextView oldModelUsedCustomerDetails_TextView;
    @BindView(R.id.oldVaraintUsedCustomerDetails_TextView)
    TextView oldVaraintUsedCustomerDetails_TextView;
    @BindView(R.id.ownershipUsedCustomerDetails_TextView)
    TextView ownershipUsedCustomerDetails_TextView;
    @BindView(R.id.mfgYrUsedCustomerDetails_TextView)
    TextView mfgYrUsedCustomerDetails_TextView;
    @BindView(R.id.anyAccClaimUsedCustomerDetails_TextView)
    TextView anyAccClaimUsedCustomerDetails_TextView;
    @BindView(R.id.kmsUsedCustomerDetails_TextView)
    TextView kmsUsedCustomerDetails_TextView;
    @BindView(R.id.nftNotificationCustomerDetailsString_TextView)
    TextView nftNotificationCustomerDetailsString_TextView;

    @BindView(R.id.expectedPriceUsedCustomerDetails_TextView)
    TextView expectedPriceUsedCustomerDetails_TextView;
    @BindView(R.id.quotePriceUsedCustomerDetails_TextView)
    TextView quotePriceUsedCustomerDetails_TextView;
    @BindView(R.id.regNoUsedCustomerDetails_TextView)
    TextView regNoUsedCustomerDetails_TextView;

    @BindView(R.id.regNoUsedCustomerDeatils_LinearLayout)
    LinearLayout regNoUsedCustomerDeatils_LinearLayout;
    @BindView(R.id.quotePriceUsedCustomerDeatils_LinearLayout)
    LinearLayout quotePriceUsedCustomerDeatils_LinearLayout;
    @BindView(R.id.expectedPriceUsedCustomerDeatils_LinearLayout)
    LinearLayout expectedPriceUsedCustomerDeatils_LinearLayout;

    //Buy used car Derails
    @BindView(R.id.buyModelUsedCustomerDetails_TextView)
    TextView buyModelUsedCustomerDetails_TextView;
    @BindView(R.id.buyVaraintUsedCustomerDetails_TextView)
    TextView buyVaraintUsedCustomerDetails_TextView;
    @BindView(R.id.budgetFromUsedCustomerDetails_TextView)
    TextView budgetFromUsedCustomerDetails_TextView ;
    @BindView(R.id.budgetToUsedCustomerDetails_TextView)
    TextView budgetToUsedCustomerDetails_TextView;

    //card view
    @BindView(R.id.appointmentDetail_cardView)
    CardView appointmentDetail_cardView;
    @BindView(R.id.otherDetail_cardView)
    CardView otherDetail_cardView;

    //CardView for Customer details
    @BindView(R.id.complainCustomerLeadDetails_cardView)
    CardView complainCustomerLeadDetails_cardView;
    @BindView(R.id.complainDetails_cardView)
    CardView complainDetails_cardView;

    @BindView(R.id.navigationLeadDetails_cardView)
    CardView navigationLeadDetails_cardView;

    @BindView(R.id.nameCustomerComplainLeadname_TextView)
    TextView nameCustomerComplainLeadname_TextView;
    @BindView(R.id.emailCustomerComplainDetails_TextView)
    TextView emailCustomerComplainDetails_TextView;

    @BindView(R.id.contactNoCustomerComplainDetails_TextView)
    TextView contactNoCustomerComplainDetails_TextView;
    @BindView(R.id.altContactCustomerComplainDetails_TextView)
    TextView altContactCustomerComplainDetails_TextView;
    @BindView(R.id.addressCustomerComplainDetails_TextView)
    TextView addressCustomerComplainDetails_TextView;

    @BindView(R.id.enqIdCustomerComplainDetails_TextView)
    TextView enqIdCustomerComplainDetails_TextView;
    @BindView(R.id.leadSourceComplainDetails_TextView)
    TextView leadSourceComplainDetails_TextView;

    @BindView(R.id.complaintDateComplainDetailsString_TextView)
    TextView complaintDateComplainDetailsString_TextView;
    @BindView(R.id.serviceCenterComplainDetailsString_TextView)
    TextView serviceCenterComplainDetailsString_TextView;

    @BindView(R.id.customerRemarkComplainDetailsString_TextView)
    TextView customerRemarkComplainDetailsString_TextView;
    @BindView(R.id.feedbackStatusComplainDetailsString_TextView)
    TextView feedbackStatusComplainDetailsString_TextView;

    @BindView(R.id.nextActionComplainDetailsString_TextView)
    TextView nextActionComplainDetailsString_TextView;
    @BindView(R.id.currentUserComplainDetailsString_TextView)
    TextView currentUserComplainDetailsString_TextView;

    @BindView(R.id.callDateComplainDetailsString_TextView)
    TextView callDateComplainDetailsString_TextView;
    @BindView(R.id.nfdComplainDetailsString_TextView)
    TextView nfdComplainDetailsString_TextView;

    @BindView(R.id.nftComplainDetailsString_TextView)
    TextView nftComplainDetailsString_TextView;
    @BindView(R.id.remarkComplainDetailsString_TextView)
    TextView remarkComplainDetailsString_TextView;

    //presenter class
    CustomerDetailsPresenter customerDetailsPresenter;
    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        ButterKnife.bind(this);

        newCar_cardView.setVisibility(View.GONE);
        usedCar_cardView.setVisibility(View.GONE);
        oldCarDetails_cardView.setVisibility(View.GONE);
        buyUsedCarDetails_cardView.setVisibility(View.GONE);

        buyerTypeCarDetails_cardView.setVisibility(View.GONE);
        oldCarDetails_cardView.setVisibility(View.GONE);

        newCarDetails_cardView.setVisibility(View.GONE);
        appointmentDetail_cardView.setVisibility(View.GONE);
        otherDetail_cardView.setVisibility(View.GONE);
        financeDetail_cardView.setVisibility(View.GONE);

        //LAYOUT VISIBILITY FOR cOMPLAIN
        complainCustomerLeadDetails_cardView.setVisibility(View.GONE);
        complainDetails_cardView.setVisibility(View.GONE);
    }

    private void initialiseUI(){
        customerDetailsPresenter = new CustomerDetailsPresenter(this);
        beanList = getIntent().getParcelableExtra("bean");

        if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            String enquiryId = beanList.getComplaint_id().toString();
            customerDetailsPresenter.getCustomerDetailsList(enquiryId,CustomerDetailsActivity.this);
        }else {
            String enquiryId = beanList.getEnq_id().toString();
            customerDetailsPresenter.getCustomerDetailsList(enquiryId,CustomerDetailsActivity.this);
        }
      }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtilities.isInternet(this)) {
            initialiseUI();
        } else {
            Toast.makeText(CustomerDetailsActivity.this, "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void onBackBtnCustomer(){
        onBackPressed();
    }

    @Override
    public void ShowCustomerDetailsList(CustomerDetailsBean jsonObject) {
       NavigationLeadeagernessDetails_TextView.setVisibility(View.GONE);
       if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("6")){
           newCar_cardView.setVisibility(View.VISIBLE);
           usedCar_cardView.setVisibility(View.GONE);
           buyerTypeCarDetails_cardView.setVisibility(View.VISIBLE);
           buyerTypeCarDetails_txtView.setText("Buyer Type : " + jsonObject.getCustomer_details().get(0).getBuyer_type());
           if (jsonObject.getCustomer_details().get(0).getBuyer_type().equals("Exchange")){

               newCarDetails_cardView.setVisibility(View.VISIBLE);
               oldCarDetails_cardView.setVisibility(View.VISIBLE);
               regNoUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               quotePriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               expectedPriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               buyUsedCarDetails_cardView.setVisibility(View.GONE);
           }else if (jsonObject.getCustomer_details().get(0).getBuyer_type().equals("First")) {
               newCarDetails_cardView.setVisibility(View.VISIBLE);
               oldCarDetails_cardView.setVisibility(View.GONE);
               buyUsedCarDetails_cardView.setVisibility(View.GONE);
           }

       }else if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("7")){
           buyerTypeCarDetails_cardView.setVisibility(View.VISIBLE);
           newCar_cardView.setVisibility(View.VISIBLE);
           usedCar_cardView.setVisibility(View.GONE);
           buyerTypeCarDetails_txtView.setText("Buyer Type : " + jsonObject.getCustomer_details().get(0).getBuyer_type());
           if (jsonObject.getCustomer_details().get(0).getBuyer_type().equals("Exchange")){
               newCarDetails_cardView.setVisibility(View.VISIBLE);
               oldCarDetails_cardView.setVisibility(View.VISIBLE);
               buyUsedCarDetails_cardView.setVisibility(View.GONE);
               regNoUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               quotePriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               expectedPriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
           }else if (jsonObject.getCustomer_details().get(0).getBuyer_type().equals("Exchange with Old Car")){
               newCarDetails_cardView.setVisibility(View.GONE);
               oldCarDetails_cardView.setVisibility(View.VISIBLE);
               regNoUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               quotePriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               expectedPriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               buyUsedCarDetails_cardView.setVisibility(View.VISIBLE);
           }else if (jsonObject.getCustomer_details().get(0).getBuyer_type().equals("Buy Used Car")){
               newCarDetails_cardView.setVisibility(View.GONE);
               oldCarDetails_cardView.setVisibility(View.GONE);
               buyUsedCarDetails_cardView.setVisibility(View.VISIBLE);
           }else if (jsonObject.getCustomer_details().get(0).getBuyer_type().equals("Sell Used Car")){
               newCarDetails_cardView.setVisibility(View.GONE);
               oldCarDetails_cardView.setVisibility(View.VISIBLE);
               regNoUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               quotePriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               expectedPriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
               buyUsedCarDetails_cardView.setVisibility(View.GONE);
           }
        } if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("8")) {
            newCar_cardView.setVisibility(View.VISIBLE);
            usedCar_cardView.setVisibility(View.GONE);
            oldCarDetails_cardView.setVisibility(View.GONE);
            buyUsedCarDetails_cardView.setVisibility(View.GONE);

            buyerTypeCarDetails_cardView.setVisibility(View.VISIBLE);
            buyerTypeCarDetails_txtView.setText("Buyer Type : " + jsonObject.getCustomer_details().get(0).getBuyer_type());
            oldCarDetails_cardView.setVisibility(View.VISIBLE);

            newCarDetails_cardView.setVisibility(View.GONE);
            appointmentDetail_cardView.setVisibility(View.VISIBLE);
            otherDetail_cardView.setVisibility(View.GONE);
            financeDetail_cardView.setVisibility(View.GONE);

            regNoUsedCustomerDeatils_LinearLayout.setVisibility(View.VISIBLE);
            quotePriceUsedCustomerDeatils_LinearLayout.setVisibility(View.VISIBLE);
            expectedPriceUsedCustomerDeatils_LinearLayout.setVisibility(View.VISIBLE);
        } if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {

            complainCustomerLeadDetails_cardView.setVisibility(View.VISIBLE);
            complainDetails_cardView.setVisibility(View.VISIBLE);
            navigationLeadDetails_cardView.setVisibility(View.GONE);

            newCar_cardView.setVisibility(View.GONE);
            usedCar_cardView.setVisibility(View.GONE);
            oldCarDetails_cardView.setVisibility(View.GONE);
            buyUsedCarDetails_cardView.setVisibility(View.GONE);

            buyerTypeCarDetails_cardView.setVisibility(View.GONE);
            buyerTypeCarDetails_txtView.setText("Buyer Type : " + jsonObject.getCustomer_details().get(0).getBuyer_type());
            oldCarDetails_cardView.setVisibility(View.GONE);

            newCarDetails_cardView.setVisibility(View.GONE);
            appointmentDetail_cardView.setVisibility(View.GONE);
            otherDetail_cardView.setVisibility(View.GONE);
            financeDetail_cardView.setVisibility(View.GONE);

            regNoUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
            quotePriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);
            expectedPriceUsedCustomerDeatils_LinearLayout.setVisibility(View.GONE);

            nameCustomerComplainLeadname_TextView.setText(jsonObject.getCustomer_details().get(0).getName());
            emailCustomerComplainDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getEmail());

            contactNoCustomerComplainDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getContact_no());
            altContactCustomerComplainDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getAlternate_contact_no());
            addressCustomerComplainDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getAddress());

            enqIdCustomerComplainDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getEnq_id());
            leadSourceComplainDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getLead_source());

            complaintDateComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCreated_date());
            serviceCenterComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getService_center());

            customerRemarkComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getComment());
            feedbackStatusComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getFeedbackStatus());

            nextActionComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getNextAction());
            currentUserComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_name());

            callDateComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_call_date());
            nfdComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_nfd());

            nftComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_nft());
            remarkComplainDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_comment());

        }

        if(!(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            NavigationLeadname_TextView.setText(jsonObject.getCustomer_details().get(0).getName());
            NavigationLeademail_TextView.setText(jsonObject.getCustomer_details().get(0).getEmail());
            NavigationLeadContactno_TextView.setText(jsonObject.getCustomer_details().get(0).getContact_no());
            NavigationLeadAltContactno_TextView.setText("Alternate No : " + jsonObject.getCustomer_details().get(0).getAlternate_contact_no());

            feedbackStatusCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getFeedbackStatus());
            NavigationLeadAddressModel_TextView.setText(jsonObject.getCustomer_details().get(0).getAddress());
            NavigationLeadEnqID_TextView.setText(jsonObject.getCustomer_details().get(0).getEnq_id());
            nextActionCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getNextAction());

            appointmentTimeCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getAppointment_time());
            appointmentRatingCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getAppointment_rating());
            appointmentDateCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getAppointment_date());
            appointmentStatusCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getAppointment_status());
            appointmentTypeUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getAppointment_type());
            appointmentFeedbackCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getAppointment_feedback());

            interestedInEwCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getInterested_in_ew());
            interestedInAccessoriesCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getInterested_in_accessories());
            interestedInInsuranceCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getInterested_in_insurance());
            interestedInFinanceHeadingCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getInterested_in_finance());

            //finance Details
            if (jsonObject.getCustomer_details().get(0).getInterested_in_finance().equals("Yes")) {
                financeDetail_cardView.setVisibility(View.VISIBLE);
            } else if (jsonObject.getCustomer_details().get(0).getInterested_in_finance().equals("No")) {
                financeDetail_cardView.setVisibility(View.GONE);
            }

            customerTypeFinanceHeadingCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getCustomer_occupation());
            corporateNameFinanceCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCustomer_corporate_name());
            customerDesignationFinanceCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCustomer_designation());

            newVaraintCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getVariant_name());
            newModelCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getModel_name());

            oldModelUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getOld_make());
            oldVaraintUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getOld_model());
            ownershipUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getOwnership());
            mfgYrUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getManf_year());
            anyAccClaimUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getAccidental_claim());
            kmsUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getKm());
            expectedPriceUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getExpected_price());
            quotePriceUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getQuotated_price());
            regNoUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getReg_no());

            buyModelUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getBuy_make());
            buyVaraintUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getBuy_model());
            budgetFromUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getBudget_from());
            budgetToUsedCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getBudget_to());

            if (!(jsonObject.getCustomer_details().get(0).getAssign_to_dse().equals("0"))) {
                remarkNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_remark());
                callDateNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_call_date());
                nfdNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_nfd());
                nftNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_nft());
                callTimeNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_call_time());
                callStatusNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_contactibility());
                followupByNotificationCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_fname() + " " + jsonObject.getCustomer_details().get(0).getDse_lname());
            } else if (!(jsonObject.getCustomer_details().get(0).getAssign_to_dse_tl().equals("0"))) {
                remarkNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_remark());
                callDateNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_call_date());
                nfdNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_nfd());
                nftNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_nft());
                callTimeNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_call_time());
                callStatusNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getDse_contactibility());
                followupByNotificationCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getDsetl_fname() + " " + jsonObject.getCustomer_details().get(0).getDsetl_lname());
            } else if (!(jsonObject.getCustomer_details().get(0).getAssign_to_cse().equals("0"))) {
                remarkNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_remark());
                callDateNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_call_date());
                nfdNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_nfd());
                nftNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_nft());
                callTimeNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_call_time());
                callStatusNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_contactibility());
                followupByNotificationCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_fname() + " " + jsonObject.getCustomer_details().get(0).getCse_lname());
            } else if (!(jsonObject.getCustomer_details().get(0).getAssign_by_cse_tl().equals("0"))) {
                remarkNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_remark());
                callDateNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_call_date());
                nfdNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_nfd());
                nftNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_nft());
                callTimeNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_call_time());
                callStatusNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getCse_contactibility());
                followupByNotificationCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getCsetl_fname() + " " + jsonObject.getCustomer_details().get(0).getCsetl_lname());
            }

            //code for booking within days
            if ((jsonObject.getCustomer_details().get(0).getDays60_booking().toString().equals("90")) || (jsonObject.getCustomer_details().get(0).getDays60_booking().toString().equals(">60"))) {
                NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
                NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getDays60_booking() + " Days");
                NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.green));
                NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.white));
            } else if (jsonObject.getCustomer_details().get(0).getDays60_booking().toString().equals("30")) {
                NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
                NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getDays60_booking() + " Days");
                NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.red));
                NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.white));
            } else if (jsonObject.getCustomer_details().get(0).getDays60_booking().toString().equals("60")) {
                NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
                NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getDays60_booking() + " Days");
                NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.yellow));
                NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.black));
            } else if ((jsonObject.getCustomer_details().get(0).getDays60_booking().toString().equals("Just Researching")) || (jsonObject.getCustomer_details().get(0).getDays60_booking().equals("Undecided"))) {
                NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
                NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getDays60_booking());
                NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.green));
                NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.white));
            } else if (jsonObject.getCustomer_details().get(0).getDays60_booking().toString().equals("")) {
                NavigationLeadeagernessDetails_TextView.setVisibility(View.GONE);
            }
        }
     }

    @OnClick(R.id.followUpDetails_fab)
    public void followUpDetails(){
        Intent followuPdetailIntent = new Intent(this, FollowUpDetailsActivity.class);
        followuPdetailIntent.putExtra("bean",beanList);
        followuPdetailIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(followuPdetailIntent);
    }

    @OnClick(R.id.add_followUp_fab)
    public void addFollowUp(){
        Intent addFollowUpIntent = new Intent(this, AddFollowUpActivity.class);
        addFollowUpIntent.putExtra("bean",beanList);
        addFollowUpIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(addFollowUpIntent);
    }

    @OnClick(R.id.add_auditor_fab)
    public void addAuditor(){
        Intent addFollowUpIntent = new Intent(this, AddAuditorRemarkDetailActivity.class);
        addFollowUpIntent.putExtra("bean",beanList);
        addFollowUpIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(addFollowUpIntent);
    }

    @OnClick(R.id.auditor_details_fab)
    public void auditorDetailCustomer(){
        Intent auditorDetailsIntent = new Intent(this, AuditorDetailsActivity.class);
        auditorDetailsIntent.putExtra("bean",beanList);
        auditorDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(auditorDetailsIntent);
    }

    @OnClick(R.id.dialContactNotification_Button)
    public void callCustomer(){
        String number = NavigationLeadContactno_TextView.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(CustomerDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CustomerDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            } else {
                startActivity(callIntent);
            }
        }else {
            startActivity(callIntent);
        }
    }
}
