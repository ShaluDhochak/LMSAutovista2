package com.excell.lms.lmsautovista.View.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.SearchTrackerListBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

public class TrackerDetailActivity extends AppCompatActivity {
    //Header
    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    //Common Details
    @BindView(R.id.leadnameTrackerDetail_TextView)
    TextView leadnameTrackerDetail_TextView;

    @BindView(R.id.leadBookingDayTrackerDetail_TextView)
    TextView leadBookingDayTrackerDetail_TextView;
    @BindView(R.id.leadContactnoTrackerDetail_TextView)
    TextView leadContactnoTrackerDetail_TextView;
    @BindView(R.id.leadAltContactnoTrackerDetail_TextView)
    TextView leadAltContactnoTrackerDetail_TextView;

    @BindView(R.id.leadEnqIDTrackerDetail_TextView)
    TextView leadEnqIDTrackerDetail_TextView;
    @BindView(R.id.addressTrackerDetail_TextView)
    TextView addressTrackerDetail_TextView;
    @BindView(R.id.emailIdTrackerDetail_TextView)
    TextView emailIdTrackerDetail_TextView;
    @BindView(R.id.leadDateTrackerDetail_TextView)
    TextView leadDateTrackerDetail_TextView;
    @BindView(R.id.leadTimeTrackerDetail_TextView)
    TextView leadTimeTrackerDetail_TextView;
    @BindView(R.id.leadBookingTrackerDetail_TextView)
    TextView leadBookingTrackerDetail_TextView;

    //layout for Booking days
    @BindView(R.id.leadBookingDayTrackerDetail_LinearLayout)
    LinearLayout leadBookingDayTrackerDetail_LinearLayout;

    //detail TextView
    @BindView(R.id.leadSourceTrackerString_TextView)
    TextView leadSourceTrackerString_TextView;
    @BindView(R.id.currentUserTrackerDetails_TextView)
    TextView currentUserTrackerDetails_TextView;
    @BindView(R.id.feedbackStatusTrackerDetailsString_TextView)
    TextView feedbackStatusTrackerDetailsString_TextView;
    @BindView(R.id.nextActionTrackerDetailsString_TextView)
    TextView nextActionTrackerDetailsString_TextView;
    @BindView(R.id.eagernessTrackerDetailsString_TextView)
    TextView eagernessTrackerDetailsString_TextView;
    @BindView(R.id.nfdTrackerDetailsString_TextView)
    TextView nfdTrackerDetailsString_TextView;
    @BindView(R.id.remarkTrackerDetailsString_TextView)
    TextView remarkTrackerDetailsString_TextView;

    //Service TEXTvIEW
    @BindView(R.id.carModelServiceTrackerString_TextView)
    TextView carModelServiceTrackerString_TextView;
    @BindView(R.id.regNoServiceTrackerDetails_TextView)
    TextView regNoServiceTrackerDetails_TextView;
    @BindView(R.id.kmServiceTrackerDetails_TextView)
    TextView kmServiceTrackerDetails_TextView;
    @BindView(R.id.serviceTypeTrackerDetailsString_TextView)
    TextView serviceTypeTrackerDetailsString_TextView;
    @BindView(R.id.PickUpRequiredServiceTrackerDetailsString_TextView)
    TextView PickUpRequiredServiceTrackerDetailsString_TextView;
    @BindView(R.id.pickUpDateTrackerDetailsString_TextView)
    TextView pickUpDateTrackerDetailsString_TextView;

    //string Accessories textView
    @BindView(R.id.carModelAccessoriesTrackerString_TextView)
    TextView carModelAccessoriesTrackerString_TextView;
    @BindView(R.id.regNoAccessoriesTrackerDetails_TextView)
    TextView regNoAccessoriesTrackerDetails_TextView;
    @BindView(R.id.accessoriesListTrackerDetailsString_TextView)
    TextView accessoriesListTrackerDetailsString_TextView;
    @BindView(R.id.accessoriesPriceTrackerDetailsString_TextView)
    TextView accessoriesPriceTrackerDetailsString_TextView;

    //Finance Textview
    @BindView(R.id.carModelFinanceTrackerString_TextView)
    TextView carModelFinanceTrackerString_TextView;
    @BindView(R.id.regNoFinanceTrackerDetails_TextView)
    TextView regNoFinanceTrackerDetails_TextView;
    @BindView(R.id.bankNameFinanceTrackerDetailsString_TextView)
    TextView bankNameFinanceTrackerDetailsString_TextView;
    @BindView(R.id.loanTypeTrackerDetailsString_TextView)
    TextView loanTypeTrackerDetailsString_TextView;

    @BindView(R.id.roiFinanceTrackerDetailsString_TextView)
    TextView roiFinanceTrackerDetailsString_TextView;
    @BindView(R.id.losNoFinanceTrackerDetailsString_TextView)
    TextView losNoFinanceTrackerDetailsString_TextView;
    @BindView(R.id.tenureFinanceTrackerDetailsString_TextView)
    TextView tenureFinanceTrackerDetailsString_TextView;
    @BindView(R.id.amountFinanceTrackerDetailsString_TextView)
    TextView amountFinanceTrackerDetailsString_TextView;

    @BindView(R.id.dealerDsaFinanceTrackerDetailsString_TextView)
    TextView dealerDsaFinanceTrackerDetailsString_TextView;
    @BindView(R.id.cenFinanceTrackerDetailsString_TextView)
    TextView cenFinanceTrackerDetailsString_TextView;
    @BindView(R.id.pickUpDateFinanceTrackerDetailsString_TextView)
    TextView pickUpDateFinanceTrackerDetailsString_TextView;
    @BindView(R.id.loginDateFinanceTrackerDetailsString_TextView)
    TextView loginDateFinanceTrackerDetailsString_TextView;

    @BindView(R.id.loanStatusFinanceTrackerDetailsString_TextView)
    TextView loanStatusFinanceTrackerDetailsString_TextView;
    @BindView(R.id.approvedDateFinanceTrackerDetailsString_TextView)
    TextView approvedDateFinanceTrackerDetailsString_TextView;
    @BindView(R.id.disburseDateFinanceTrackerDetailsString_TextView)
    TextView disburseDateFinanceTrackerDetailsString_TextView;
    @BindView(R.id.disburseAmountFinanceTrackerDetailsString_TextView)
    TextView disburseAmountFinanceTrackerDetailsString_TextView;

    @BindView(R.id.processingFeeFinanceTrackerDetailsString_TextView)
    TextView processingFeeFinanceTrackerDetailsString_TextView;
    @BindView(R.id.emiFinanceTrackerDetailsString_TextView)
    TextView emiFinanceTrackerDetailsString_TextView;

    //textview for new car
    @BindView(R.id.newCarDetailTracker_TextView)
    TextView newCarDetailTracker_TextView;
    @BindView(R.id.leadSourceNewCarTrackerString_TextView)
    TextView leadSourceNewCarTrackerString_TextView;

    @BindView(R.id.assReqNewCarTrackerString_TextView)
    TextView assReqNewCarTrackerString_TextView;
    @BindView(R.id.customerLocNewCarTrackerString_TextView)
    TextView customerLocNewCarTrackerString_TextView;

    //New Entry
    @BindView(R.id.leadAssignedDateNewCarTrackerString_TextView)
    TextView leadAssignedDateNewCarTrackerString_TextView;
    @BindView(R.id.leadAssignedTimeNewCarTrackerString_TextView)
    TextView leadAssignedTimeNewCarTrackerString_TextView;
    @BindView(R.id.cseNameNewCarTrackerString_TextView)
    TextView cseNameNewCarTrackerString_TextView;
    @BindView(R.id.cseCallDateNewCarTrackerString_TextView)
    TextView cseCallDateNewCarTrackerString_TextView;
    @BindView(R.id.cseCallTimeNewCarTrackerString_TextView)
    TextView cseCallTimeNewCarTrackerString_TextView;
    @BindView(R.id.cseCallStatusNewCarTrackerString_TextView)
    TextView cseCallStatusNewCarTrackerString_TextView;

    @BindView(R.id.cseFeedbackNewCarTrackerString_TextView)
    TextView cseFeedbackNewCarTrackerString_TextView;

    @BindView(R.id.cseNextActionNewCarTrackerString_TextView)
    TextView cseNextActionNewCarTrackerString_TextView;
    @BindView(R.id.cseRemarkNewCarTrackerString_TextView)
    TextView cseRemarkNewCarTrackerString_TextView;

    @BindView(R.id.cseNFDNewCarTrackerString_TextView)
    TextView cseNFDNewCarTrackerString_TextView;
    @BindView(R.id.cseNFTNewCarTrackerString_TextView)
    TextView cseNFTNewCarTrackerString_TextView;

    @BindView(R.id.appTypeTrackerString_TextView)
    TextView appTypeTrackerString_TextView;
    @BindView(R.id.appDateTrackerString_TextView)
    TextView appDateTrackerString_TextView;
    @BindView(R.id.appTimeTrackerString_TextView)
    TextView appTimeTrackerString_TextView;
    @BindView(R.id.appAddressTrackerString_TextView)
    TextView appAddressTrackerString_TextView;
    @BindView(R.id.appStatusTrackerString_TextView)
    TextView appStatusTrackerString_TextView;

    @BindView(R.id.appRatingTrackerString_TextView)
    TextView appRatingTrackerString_TextView;
    @BindView(R.id.appFeedbackTrackerString_TextView)
    TextView appFeedbackTrackerString_TextView;
    @BindView(R.id.showroomLocNewCarTrackerString_TextView)
    TextView showroomLocNewCarTrackerString_TextView;
    @BindView(R.id.leadAssignDateNewCarTrackerString_TextView)
    TextView leadAssignDateNewCarTrackerString_TextView;
    @BindView(R.id.leadAssignTimeNewCarTrackerString_TextView)
    TextView leadAssignTimeNewCarTrackerString_TextView;

    @BindView(R.id.dseCallTimeNewCarTrackerString_TextView)
    TextView dseCallTimeNewCarTrackerString_TextView;
    @BindView(R.id.dseCallStatusNewCarTrackerString_TextView)
    TextView dseCallStatusNewCarTrackerString_TextView;

    @BindView(R.id.dseRemarkNewCarTrackerString_TextView)
    TextView dseRemarkNewCarTrackerString_TextView;
    @BindView(R.id.auditorFollowTimeNewCarTrackerString_TextView)
    TextView auditorFollowTimeNewCarTrackerString_TextView;
    @BindView(R.id.auditorFollowStatusNewCarTrackerString_TextView)
    TextView auditorFollowStatusNewCarTrackerString_TextView;
    @BindView(R.id.interestInFinanceNewCarTrackerString_TextView)
    TextView interestInFinanceNewCarTrackerString_TextView;
    @BindView(R.id.interestInAccessoriesNewCarTrackerString_TextView)
    TextView interestInAccessoriesNewCarTrackerString_TextView;
    @BindView(R.id.interestInInsuranceNewCarTrackerString_TextView)
    TextView interestInInsuranceNewCarTrackerString_TextView;
    @BindView(R.id.interestInEWNewCarTrackerString_TextView)
    TextView interestInEWNewCarTrackerString_TextView;

    @BindView(R.id.dseNameDateNewCarTrackerString_TextView)
    TextView dseNameDateNewCarTrackerString_TextView;
    @BindView(R.id.dseCallDateNewCarTrackerString_TextView)
    TextView dseCallDateNewCarTrackerString_TextView;
    @BindView(R.id.dseFeedbackNewCarTrackerString_TextView)
    TextView dseFeedbackNewCarTrackerString_TextView;

    @BindView(R.id.dseNextActionNewCarTrackerString_TextView)
    TextView dseNextActionNewCarTrackerString_TextView;
    @BindView(R.id.dseNFDNewCarTrackerString_TextView)
    TextView dseNFDNewCarTrackerString_TextView;

    @BindView(R.id.dseNFTNewCarTrackerString_TextView)
    TextView dseNFTNewCarTrackerString_TextView;
    @BindView(R.id.auditorNameNewCarTrackerString_TextView)
    TextView auditorNameNewCarTrackerString_TextView;

    @BindView(R.id.auditorFollowDateNewCarTrackerString_TextView)
    TextView auditorFollowDateNewCarTrackerString_TextView;
    @BindView(R.id.followUpPendingNewCarTrackerString_TextView)
    TextView followUpPendingNewCarTrackerString_TextView;
    @BindView(R.id.callReceivedSrNewCarTrackerString_TextView)
    TextView callReceivedSrNewCarTrackerString_TextView;

    @BindView(R.id.fakeUpdationNewCarTrackerString_TextView)
    TextView fakeUpdationNewCarTrackerString_TextView;
    @BindView(R.id.serviceFeedbackNewCarTrackerString_TextView)
    TextView serviceFeedbackNewCarTrackerString_TextView;
    @BindView(R.id.auditorRemarkNewCarTrackerString_TextView)
    TextView auditorRemarkNewCarTrackerString_TextView;

    @BindView(R.id.buyerTypeNewCarTrackerString_TextView)
    TextView buyerTypeNewCarTrackerString_TextView;
    @BindView(R.id.modelVariantNewCarTrackerString_TextView)
    TextView modelVariantNewCarTrackerString_TextView;

    //String for Used car
    @BindView(R.id.accClaimUsedCarTrackerString_TextView)
    TextView accClaimUsedCarTrackerString_TextView;
    @BindView(R.id.budgetToUsedCarTrackerString_TextView)
    TextView budgetToUsedCarTrackerString_TextView;

    @BindView(R.id.budgetFromUsedCarTrackerString_TextView)
    TextView budgetFromUsedCarTrackerString_TextView;
    @BindView(R.id.kmUsedCarTrackerString_TextView)
    TextView kmUsedCarTrackerString_TextView;

    @BindView(R.id.colorEvaluationTrackerString_TextView)
    TextView colorEvaluationTrackerString_TextView;
    @BindView(R.id.fuelTypeEvaluationTrackerString_TextView)
    TextView fuelTypeEvaluationTrackerString_TextView;
    @BindView(R.id.evaluationWithinDaysEvaluationTrackerString_TextView)
    TextView evaluationWithinDaysEvaluationTrackerString_TextView;

    @BindView(R.id.ownershipUsedCarTrackerString_TextView)
    TextView ownershipUsedCarTrackerString_TextView;
    @BindView(R.id.mfgYrUsedCarTrackerString_TextView)
    TextView mfgYrUsedCarTrackerString_TextView;
    @BindView(R.id.exchangeMakeModelUsedCarTrackerString_TextView)
    TextView exchangeMakeModelUsedCarTrackerString_TextView;

    //Relative layout for finance, service and accessories
    @BindView(R.id.DetailTrackerDescription_relativeLayout)
    CardView DetailTrackerDescription_relativeLayout;

    @BindView(R.id.newCarDetailTrackerDescription_relativeLayout)
    CardView newCarDetailTrackerDescription_relativeLayout;
    @BindView(R.id.usedCarDetailTrackerDescription_relativeLayout)
    CardView usedCarDetailTrackerDescription_relativeLayout;

    @BindView(R.id.serviceDetailTrackerDescription_relativeLayout)
    CardView serviceDetailTrackerDescription_relativeLayout;
    @BindView(R.id.accessoriesDetailTrackerDescription_relativeLayout)
    CardView accessoriesDetailTrackerDescription_relativeLayout;
    @BindView(R.id.financeDetailTrackerDescription_relativeLayout)
    CardView financeDetailTrackerDescription_relativeLayout;

    //Evaluator Heading
    @BindView(R.id.dseNameNewCarTrackerDetails_TextView)
    TextView dseNameNewCarTrackerDetails_TextView;
    @BindView(R.id.leadAssignDateNewCarTrackerDetails_TextView)
    TextView leadAssignDateNewCarTrackerDetails_TextView;
    @BindView(R.id.leadAssignTimeNewCarTrackerDetails_TextView)
    TextView leadAssignTimeNewCarTrackerDetails_TextView;

    @BindView(R.id.dseCallDateNewCarTrackerDetails_TextView)
    TextView dseCallDateNewCarTrackerDetails_TextView;
    @BindView(R.id.dseCallTimeNewCarTrackerDetails_TextView)
    TextView dseCallTimeNewCarTrackerDetails_TextView;
    @BindView(R.id.dseCallStatusNewCarTrackerDetails_TextView)
    TextView dseCallStatusNewCarTrackerDetails_TextView;
    @BindView(R.id.dseFeedbackNewCarTrackerDetails_TextView)
    TextView dseFeedbackNewCarTrackerDetails_TextView;
    @BindView(R.id.dseNextActionNewCarTrackerDetails_TextView)
    TextView dseNextActionNewCarTrackerDetails_TextView;
    @BindView(R.id.dseRemarkNewCarTrackerDetails_TextView)
    TextView dseRemarkNewCarTrackerDetails_TextView;
    @BindView(R.id.dseNFDNewCarTrackerDetails_TextView)
    TextView dseNFDNewCarTrackerDetails_TextView;
    @BindView(R.id.dseNFTNewCarTrackerDetails_TextView)
    TextView dseNFTNewCarTrackerDetails_TextView;

    //Linear layout for evaluator
    @BindView(R.id.interestInFinanceNewCarTrackerDetails_RelativeLayout)
    LinearLayout interestInFinanceNewCarTrackerDetails_RelativeLayout;
    @BindView(R.id.interestInAccessoriesNewCarTrackerDetails_RelativeLayout)
    LinearLayout interestInAccessoriesNewCarTrackerDetails_RelativeLayout;
    @BindView(R.id.interestInInsuranceNewCarTrackerDetails_RelativeLayout)
    LinearLayout interestInInsuranceNewCarTrackerDetails_RelativeLayout;
    @BindView(R.id.interestInEWNewCarTrackerDetails_RelativeLayout)
    LinearLayout interestInEWNewCarTrackerDetails_RelativeLayout;
    @BindView(R.id.buyerTypeNewCarTrackerDetails_RelativeLayout)
    LinearLayout buyerTypeNewCarTrackerDetails_RelativeLayout;
    @BindView(R.id.modelVariantNewCarTrackerDetails_RelativeLayout)
    LinearLayout modelVariantNewCarTrackerDetails_RelativeLayout;

    @BindView(R.id.accidentalClaimUsedCarTrackerDetails_RelativeLayout)
    LinearLayout accidentalClaimUsedCarTrackerDetails_RelativeLayout;
    @BindView(R.id.budgetToUsedCarTrackerDetails_RelativeLayout)
    LinearLayout budgetToUsedCarTrackerDetails_RelativeLayout ;
    @BindView(R.id.budgetFromUsedCarTrackerDetails_RelativeLayout)
    LinearLayout budgetFromUsedCarTrackerDetails_RelativeLayout;

    @BindView(R.id.evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout)
    LinearLayout evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout;
    @BindView(R.id.fuelTypeEvaluationTrackerDetails_RelativeLayout)
    LinearLayout fuelTypeEvaluationTrackerDetails_RelativeLayout;
    @BindView(R.id.colorEvaluationTrackerDetails_RelativeLayout)
    LinearLayout colorEvaluationTrackerDetails_RelativeLayout;

    @BindView(R.id.regNoEvaluationTrackerDetails_RelativeLayout)
    LinearLayout regNoEvaluationTrackerDetails_RelativeLayout;
    @BindView(R.id.quotePriceEvaluationTrackerDetails_RelativeLayout)
    LinearLayout quotePriceEvaluationTrackerDetails_RelativeLayout;
    @BindView(R.id.expectedPriceEvaluationTrackerDetails_RelativeLayout)
    LinearLayout expectedPriceEvaluationTrackerDetails_RelativeLayout;

    @BindView(R.id.regNoEvaluationTrackerString_TextView)
    TextView regNoEvaluationTrackerString_TextView;
    @BindView(R.id.quotePriceEvaluationTrackerString_TextView)
    TextView quotePriceEvaluationTrackerString_TextView;
    @BindView(R.id.expectedPriceEvaluationTrackerString_TextView)
    TextView expectedPriceEvaluationTrackerString_TextView;

    @BindView(R.id.nameTrackerDetail_cardView)
            CardView nameTrackerDetail_cardView;
    @BindView(R.id.complaintDetailTrackerDescription_cv)
            CardView complaintDetailTrackerDescription_cv;

    //Complaint String
    @BindView(R.id.leadSourceComplaintTrackerString_TextView)
    TextView leadSourceComplaintTrackerString_TextView;

    @BindView(R.id.complaintTypeComplaintTrackerString_TextView)
    TextView complaintTypeComplaintTrackerString_TextView;

    @BindView(R.id.serviceCenterComplaintTrackerString_TextView)
    TextView serviceCenterComplaintTrackerString_TextView;

    @BindView(R.id.customerRemarkComplaintTrackerString_TextView)
    TextView customerRemarkComplaintTrackerString_TextView;

    @BindView(R.id.leadAssignedDateComplaintTrackerString_TextView)
    TextView leadAssignedDateComplaintTrackerString_TextView;
    @BindView(R.id.currentUserComplaintTrackerString_TextView)
    TextView currentUserComplaintTrackerString_TextView;
    @BindView(R.id.leadAssignedTimeComplaintTrackerString_TextView)
    TextView leadAssignedTimeComplaintTrackerString_TextView;

    @BindView(R.id.callDateComplaintTrackerString_TextView)
    TextView callDateComplaintTrackerString_TextView;
    @BindView(R.id.feedbackStatusComplaintTrackerString_TextView)
    TextView feedbackStatusComplaintTrackerString_TextView;
    @BindView(R.id.nextActionComplaintTrackerString_TextView)
    TextView nextActionComplaintTrackerString_TextView;

    @BindView(R.id.nftComplaintTrackerString_TextView)
    TextView nftComplaintTrackerString_TextView;
    @BindView(R.id.nfdComplaintTrackerString_TextView)
    TextView nfdComplaintTrackerString_TextView;

    @BindView(R.id.remarkComplaintTrackerString_TextView)
    TextView remarkComplaintTrackerString_TextView;
    @BindView(R.id.complaintLocationComplaintTrackerString_TextView)
    TextView complaintLocationComplaintTrackerString_TextView;

    @BindView(R.id.regNoComplaintTrackerString_TextView)
    TextView regNoComplaintTrackerString_TextView;
    @BindView(R.id.auditorNameComplaintTrackerString_TextView)
    TextView auditorNameComplaintTrackerString_TextView;

    @BindView(R.id.auditorCallTimeComplaintTrackerString_TextView)
    TextView auditorCallTimeComplaintTrackerString_TextView;
    @BindView(R.id.auditorCallDateComplaintTrackerString_TextView)
    TextView auditorCallDateComplaintTrackerString_TextView;

    @BindView(R.id.auditorCallStatusComplaintTrackerString_TextView)
    TextView auditorCallStatusComplaintTrackerString_TextView ;
    @BindView(R.id.followUpPendingComplaintTrackerString_TextView)
    TextView followUpPendingComplaintTrackerString_TextView;

    @BindView(R.id.callReceivedShowroomComplaintTrackerString_TextView)
    TextView callReceivedShowroomComplaintTrackerString_TextView;
    @BindView(R.id.fakeUpdationComplaintTrackerString_TextView)
    TextView fakeUpdationComplaintTrackerString_TextView;

    @BindView(R.id.serviceFeedbackComplaintTrackerString_TextView)
    TextView serviceFeedbackComplaintTrackerString_TextView;
    @BindView(R.id.auditorRemarkComplaintTrackerString_TextView)
    TextView auditorRemarkComplaintTrackerString_TextView;


    SearchTrackerListBean.User_Details bean;
    String title, process_id;
    int position;
    private ArrayList<SearchTrackerListBean.User_Details> allLeadsBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_detail);

        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialiseUI();
    }

    private void initialiseUI(){

        process_id = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "");

        bean = TrackerDetailActivity.this.getIntent().getParcelableExtra("bean");
        position = TrackerDetailActivity.this.getIntent().getIntExtra("position", 0);
        title = TrackerDetailActivity.this.getIntent().getStringExtra("heading");

        leadBookingTrackerDetail_TextView.setVisibility(GONE);
        complaintDetailTrackerDescription_cv.setVisibility(GONE);
        setVisibility();

        lmsheading_TextView.setText("Tracker Details");

        if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            setCampaintTrackerDetails();
        }else {
            setTrackerDetailVisibility();
        }

    }

    private void setTrackerDetailVisibility(){

        //Common Details
        leadnameTrackerDetail_TextView.setText(bean.getName());
        leadContactnoTrackerDetail_TextView.setText(bean.getContact_no());
        leadAltContactnoTrackerDetail_TextView.setText("Alternate No : " + bean.getAlternate_contact_no());

        if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            leadEnqIDTrackerDetail_TextView.setText(bean.getComplaint_id());
        }else {
            leadEnqIDTrackerDetail_TextView.setText(bean.getEnq_id());
        }


        addressTrackerDetail_TextView.setText(bean.getAddress());
        emailIdTrackerDetail_TextView.setText(bean.getEmail());
        leadDateTrackerDetail_TextView.setText("Lead Date : " +bean.getLead_date());
        leadTimeTrackerDetail_TextView.setText("Lead Time : " +bean.getLead_time());

        if (bean.getDays60_booking().toString().equals("")){
            leadBookingDayTrackerDetail_LinearLayout.setVisibility(GONE);
        }
        //code for booking within days
        if ((bean.getDays60_booking().toString().equals("90")) || (bean.getDays60_booking().toString().equals(">60"))){
            leadBookingTrackerDetail_TextView.setVisibility(View.VISIBLE);
            leadBookingTrackerDetail_TextView.setText(bean.getDays60_booking() + " Days");
            leadBookingTrackerDetail_TextView.setBackgroundColor(getResources().getColor(R.color.green));
            leadBookingTrackerDetail_TextView.setTextColor(getResources().getColor(android.R.color.white));
        }else if (bean.getDays60_booking().toString().equals("30")){
            leadBookingTrackerDetail_TextView.setVisibility(View.VISIBLE);
            leadBookingTrackerDetail_TextView.setText(bean.getDays60_booking() +  " Days");
            leadBookingTrackerDetail_TextView.setBackgroundColor(getResources().getColor(R.color.red));
            leadBookingTrackerDetail_TextView.setTextColor(getResources().getColor(android.R.color.white));
        }else if (bean.getDays60_booking().toString().equals("60")){
            leadBookingTrackerDetail_TextView.setVisibility(View.VISIBLE);
            leadBookingTrackerDetail_TextView.setText(bean.getDays60_booking() + " Days");
            leadBookingTrackerDetail_TextView.setBackgroundColor(getResources().getColor(R.color.yellow));
            leadBookingTrackerDetail_TextView.setTextColor(getResources().getColor(android.R.color.black));
        }else if ((bean.getDays60_booking().toString().equals("Just Researching"))||(bean.getDays60_booking().equals("Undecided"))){
            leadBookingTrackerDetail_TextView.setVisibility(View.VISIBLE);
            leadBookingTrackerDetail_TextView.setText(bean.getDays60_booking());
            leadBookingTrackerDetail_TextView.setBackgroundColor(getResources().getColor(R.color.green));
            leadBookingTrackerDetail_TextView.setTextColor(getResources().getColor(android.R.color.white));
        }else if (bean.getDays60_booking().toString().equals("")){
            leadBookingTrackerDetail_TextView.setVisibility(View.GONE);
        }
        //new car TextView
        newCarDetailTracker_TextView.setText("Car Details");
        if (bean.getLead_source().equals("")){
            leadSourceNewCarTrackerString_TextView.setText("Web");
        }else if (bean.getLead_source().equals("Facebook")){
            leadSourceNewCarTrackerString_TextView.setText(bean.getEnquiry_for());
        }else {
            leadSourceNewCarTrackerString_TextView.setText(bean.getLead_source());
        }
        assReqNewCarTrackerString_TextView.setText(bean.getAssistance());
        customerLocNewCarTrackerString_TextView.setText(bean.getCustomer_location());
        showroomLocNewCarTrackerString_TextView.setText(bean.getShowroom_location());
        if (!(bean.getAssign_to_dse().equals("0") )){
            dseNameDateNewCarTrackerString_TextView.setText(bean.getDse_fname()+ " " + bean.getDse_lname());
            leadAssignDateNewCarTrackerString_TextView.setText(bean.getAssign_to_dse_date());
            leadAssignTimeNewCarTrackerString_TextView.setText(bean.getAssign_to_dse_time());

        }else if (!(bean.getAssign_to_dse_tl().equals("0") )) {
            dseNameDateNewCarTrackerString_TextView.setText(bean.getDsetl_fname() + " " + bean.getDsetl_lname());
            leadAssignDateNewCarTrackerString_TextView.setText(bean.getAssign_to_dse_tl_date());
            leadAssignTimeNewCarTrackerString_TextView.setText(bean.getAssign_to_dse_tl_time());
        }
        if (!(bean.getAssign_to_cse().equals("0"))){
            cseNameNewCarTrackerString_TextView.setText(bean.getCse_fname() + " " + bean.getCse_lname());
            leadAssignedDateNewCarTrackerString_TextView.setText(bean.getAssign_to_cse_date());
            leadAssignedTimeNewCarTrackerString_TextView.setText(bean.getAssign_to_cse_time());
        }else if (!(bean.getAssign_by_cse_tl().equals("0"))){
            cseNameNewCarTrackerString_TextView.setText(bean.getCsetl_fname() + " " + bean.getCsetl_lname());
            leadAssignedDateNewCarTrackerString_TextView.setText(bean.getAssign_to_cse_time());
            leadAssignedTimeNewCarTrackerString_TextView.setText(bean.getAssign_to_cse_time());
        }
        cseCallDateNewCarTrackerString_TextView.setText(bean.getCse_date());

        cseCallTimeNewCarTrackerString_TextView.setText(bean.getCse_time());
        cseCallStatusNewCarTrackerString_TextView.setText(bean.getCsecontactibility());
        cseFeedbackNewCarTrackerString_TextView.setText(bean.getCsefeedback());
        cseNextActionNewCarTrackerString_TextView.setText(bean.getCsenextAction());
        cseRemarkNewCarTrackerString_TextView.setText(bean.getCse_comment());
        cseNFDNewCarTrackerString_TextView.setText(bean.getCse_nfd());
        cseNFTNewCarTrackerString_TextView.setText(bean.getCse_nftime());

        appTypeTrackerString_TextView.setText(bean.getAppointment_type());
        appDateTrackerString_TextView.setText(bean.getAppointment_date());
        appTimeTrackerString_TextView.setText(bean.getAppointment_time());
        appAddressTrackerString_TextView.setText(bean.getAppointment_address());
        appStatusTrackerString_TextView.setText(bean.getAppointment_status());
        appRatingTrackerString_TextView.setText(bean.getAppointment_rating());
        appFeedbackTrackerString_TextView.setText(bean.getAppointment_feedback());

        showroomLocNewCarTrackerString_TextView.setText(bean.getShowroom_location());
        dseCallTimeNewCarTrackerString_TextView.setText(bean.getDse_time());
        dseCallStatusNewCarTrackerString_TextView.setText(bean.getDsecontactibility());

        dseRemarkNewCarTrackerString_TextView.setText(bean.getDse_comment());
        auditorFollowTimeNewCarTrackerString_TextView.setText(bean.getAuditor_time());
        auditorFollowStatusNewCarTrackerString_TextView.setText(bean.getAuditor_call_status());
        interestInFinanceNewCarTrackerString_TextView.setText(bean.getInterested_in_finance());
        interestInAccessoriesNewCarTrackerString_TextView.setText(bean.getInterested_in_accessories());
        interestInInsuranceNewCarTrackerString_TextView.setText(bean.getInterested_in_insurance());
        interestInEWNewCarTrackerString_TextView.setText(bean.getInterested_in_ew());

        dseCallDateNewCarTrackerString_TextView.setText(bean.getDse_date());
        dseFeedbackNewCarTrackerString_TextView.setText(bean.getDsefeedback());
        dseNextActionNewCarTrackerString_TextView.setText(bean.getDsenextAction());
        dseNFDNewCarTrackerString_TextView.setText(bean.getDse_nfd());
        dseNFTNewCarTrackerString_TextView.setText(bean.getDse_nftime());

        auditorNameNewCarTrackerString_TextView.setText(bean.getAuditfname() + " "+ bean.getAuditlname());
        auditorFollowDateNewCarTrackerString_TextView.setText(bean.getAuditor_date());
        followUpPendingNewCarTrackerString_TextView.setText(bean.getFollowup_pending());
        callReceivedSrNewCarTrackerString_TextView.setText(bean.getCall_received());
        fakeUpdationNewCarTrackerString_TextView.setText(bean.getFake_updation());
        serviceFeedbackNewCarTrackerString_TextView.setText(bean.getService_feedback());
        auditorRemarkNewCarTrackerString_TextView.setText(bean.getAuditor_remark());
        buyerTypeNewCarTrackerString_TextView.setText(bean.getBuyer_type());
        modelVariantNewCarTrackerString_TextView.setText(bean.getModel_name() +" "+ bean.getVariant_name());

        //Used car String
        accClaimUsedCarTrackerString_TextView.setText(bean.getAccidental_claim());
        budgetToUsedCarTrackerString_TextView.setText(bean.getBudget_to());
        budgetFromUsedCarTrackerString_TextView.setText(bean.getBudget_from());
        kmUsedCarTrackerString_TextView.setText(bean.getKm());
        ownershipUsedCarTrackerString_TextView.setText(bean.getOwnership());
        mfgYrUsedCarTrackerString_TextView.setText(bean.getManf_year());
        exchangeMakeModelUsedCarTrackerString_TextView.setText(bean.getExecutive_name());
        colorEvaluationTrackerString_TextView.setText(bean.getColor());
        fuelTypeEvaluationTrackerString_TextView.setText(bean.getFuel_type());
        evaluationWithinDaysEvaluationTrackerString_TextView.setText(bean.getEvaluation_within_days());
        regNoEvaluationTrackerString_TextView.setText(bean.getReg_no());
        quotePriceEvaluationTrackerString_TextView.setText(bean.getQuotated_price());
        expectedPriceEvaluationTrackerString_TextView.setText(bean.getExpected_price());

        //detail TextView
        if (bean.getLead_source().equals("")){
            leadSourceTrackerString_TextView.setText("Web");
        }else if (bean.getLead_source().equals("Facebook")){
            leadSourceTrackerString_TextView.setText(bean.getEnquiry_for());
        }else {
            leadSourceTrackerString_TextView.setText(bean.getLead_source());
        }

        currentUserTrackerDetails_TextView.setText(bean.getCse_fname() + " " + bean.getCse_lname());
        feedbackStatusTrackerDetailsString_TextView.setText(bean.getFeedbackStatus());
        nextActionTrackerDetailsString_TextView.setText(bean.getNextAction());
        eagernessTrackerDetailsString_TextView.setText(bean.getEagerness());
        nfdTrackerDetailsString_TextView.setText(bean.getCse_nfd());
        remarkTrackerDetailsString_TextView.setText(bean.getCse_comment());

        //deatil Service
        carModelServiceTrackerString_TextView.setText(bean.getModel_name());
        regNoServiceTrackerDetails_TextView.setText(bean.getReg_no());
        kmServiceTrackerDetails_TextView.setText(bean.getKm());
        serviceTypeTrackerDetailsString_TextView.setText(bean.getService_type());
        PickUpRequiredServiceTrackerDetailsString_TextView.setText(bean.getPickup_required());
        pickUpDateTrackerDetailsString_TextView.setText(bean.getPick_up_date());

        //detail Accessories
        carModelAccessoriesTrackerString_TextView.setText(bean.getModel_name());
        regNoAccessoriesTrackerDetails_TextView.setText(bean.getReg_no());
        accessoriesListTrackerDetailsString_TextView.setText(bean.getAccessoires_list());
        accessoriesPriceTrackerDetailsString_TextView.setText(bean.getAssessories_price());

        //Finance Textview
        carModelFinanceTrackerString_TextView.setText(bean.getModel_name());
        regNoFinanceTrackerDetails_TextView.setText(bean.getReg_no());
        bankNameFinanceTrackerDetailsString_TextView.setText(bean.getBank_name());
        loanTypeTrackerDetailsString_TextView.setText(bean.getLoan_type());

        roiFinanceTrackerDetailsString_TextView.setText(bean.getRoi());
        losNoFinanceTrackerDetailsString_TextView.setText(bean.getLos_no());
        tenureFinanceTrackerDetailsString_TextView.setText(bean.getTenure());
        amountFinanceTrackerDetailsString_TextView.setText(bean.getLoanamount());

        dealerDsaFinanceTrackerDetailsString_TextView.setText(bean.getDealer());
        cenFinanceTrackerDetailsString_TextView.setText(bean.getCse_lname());
        pickUpDateFinanceTrackerDetailsString_TextView.setText(bean.getPick_up_date());
        loginDateFinanceTrackerDetailsString_TextView.setText(bean.getFile_login_date());

        loanStatusFinanceTrackerDetailsString_TextView.setText(bean.getLogin_status_name());
        approvedDateFinanceTrackerDetailsString_TextView.setText(bean.getApproved_date());
        disburseDateFinanceTrackerDetailsString_TextView.setText(bean.getDisburse_date());
        disburseAmountFinanceTrackerDetailsString_TextView.setText(bean.getDisburse_amount());
        processingFeeFinanceTrackerDetailsString_TextView.setText(bean.getProcess_fee());
        emiFinanceTrackerDetailsString_TextView.setText(bean.getEmi());
    }

    private void setCampaintTrackerDetails(){
        nameTrackerDetail_cardView.setVisibility(View.VISIBLE);
        complaintDetailTrackerDescription_cv.setVisibility(View.VISIBLE);

        DetailTrackerDescription_relativeLayout.setVisibility(GONE);
        serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
        accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
        financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
        newCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
        usedCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);

        leadnameTrackerDetail_TextView.setText(bean.getName());
        leadContactnoTrackerDetail_TextView.setText(bean.getContact_no());
        leadAltContactnoTrackerDetail_TextView.setText("Alternate No : " + bean.getAlternate_contact_no());

        if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            leadEnqIDTrackerDetail_TextView.setText(bean.getComplaint_id());
        }else {
            leadEnqIDTrackerDetail_TextView.setText(bean.getEnq_id());
        }

        addressTrackerDetail_TextView.setText(bean.getAddress());
        emailIdTrackerDetail_TextView.setText(bean.getEmail());
        leadDateTrackerDetail_TextView.setText("Lead Date : " +bean.getLead_date());
        leadTimeTrackerDetail_TextView.setText("Lead Time : " +bean.getLead_time());

        if (bean.getLead_source().equals("")){
            leadSourceComplaintTrackerString_TextView.setText("Web");
        }else{
            leadSourceComplaintTrackerString_TextView.setText(bean.getLead_source());
        }

        complaintTypeComplaintTrackerString_TextView.setText(bean.getBusiness_area());
        serviceCenterComplaintTrackerString_TextView.setText(bean.getService_center());;
        customerRemarkComplaintTrackerString_TextView.setText(bean.getComment());

        leadAssignedDateComplaintTrackerString_TextView.setText(bean.getLead_date());
        currentUserComplaintTrackerString_TextView.setText(bean.getCse_fname() + " "+ bean.getCse_lname());
        leadAssignedTimeComplaintTrackerString_TextView.setText(bean.getLead_time());

        callDateComplaintTrackerString_TextView.setText(bean.getCse_date());;
        feedbackStatusComplaintTrackerString_TextView.setText(bean.getFeedbackStatus());
        nextActionComplaintTrackerString_TextView.setText(bean.getNextAction());

        nftComplaintTrackerString_TextView.setText(bean.getCse_nftime());
        nfdComplaintTrackerString_TextView.setText(bean.getCse_nfd());

        remarkComplaintTrackerString_TextView.setText(bean.getCse_comment());
        complaintLocationComplaintTrackerString_TextView.setText(bean.getLocation());;

        regNoComplaintTrackerString_TextView.setText(bean.getReg_no());
        auditorNameComplaintTrackerString_TextView.setText(bean.getAuditfname() +  " " + bean.getAuditlname());

        auditorCallTimeComplaintTrackerString_TextView.setText(bean.getAuditor_time());
        auditorCallDateComplaintTrackerString_TextView.setText(bean.getAuditor_date());

        auditorCallStatusComplaintTrackerString_TextView.setText(bean.getAuditor_call_status());
        followUpPendingComplaintTrackerString_TextView.setText(bean.getFollowup_pending());

        callReceivedShowroomComplaintTrackerString_TextView.setText(bean.getCall_received());
        fakeUpdationComplaintTrackerString_TextView.setText(bean.getFake_updation());

        serviceFeedbackComplaintTrackerString_TextView.setText(bean.getService_feedback());
        auditorRemarkComplaintTrackerString_TextView.setText(bean.getAuditor_remark());

    }

    @SuppressLint("SetTextI18n")
    private void setVisibility(){
        switch (process_id) {
            case "1":
                newCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                financeDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                DetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                usedCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);

                evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                fuelTypeEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                colorEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                regNoEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                quotePriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                expectedPriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                break;
            case "4":
                newCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                serviceDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                DetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                usedCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);

                evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                fuelTypeEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                colorEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                break;
            case "5":
                newCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                accessoriesDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                DetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                usedCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);

                evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                fuelTypeEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                colorEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                regNoEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                quotePriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                expectedPriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                break;
            case "6":
                newCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                DetailTrackerDescription_relativeLayout.setVisibility(GONE);
                usedCarDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);

                evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                fuelTypeEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                colorEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                regNoEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                quotePriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                expectedPriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                break;
            case "7":
                newCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                DetailTrackerDescription_relativeLayout.setVisibility(GONE);
                usedCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);

                evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                fuelTypeEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                colorEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                regNoEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                quotePriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                expectedPriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.GONE);
                break;
            case "8":
                dseNameNewCarTrackerDetails_TextView.setText("Evaluator Name");
                leadAssignDateNewCarTrackerDetails_TextView.setText("Assigned Date (Evaluator)");
                leadAssignTimeNewCarTrackerDetails_TextView.setText("Assigned Time (Evaluator)");
                dseCallDateNewCarTrackerDetails_TextView.setText("Evaluator Call Date");
                dseCallTimeNewCarTrackerDetails_TextView.setText("Evaluator Call Time");
                dseCallStatusNewCarTrackerDetails_TextView.setText("Evaluator Call Status");
                dseFeedbackNewCarTrackerDetails_TextView.setText("Evaluator Feedback");
                dseNextActionNewCarTrackerDetails_TextView.setText("Evaluator NextAction");
                dseRemarkNewCarTrackerDetails_TextView.setText("Evaluator Remark");
                dseNFDNewCarTrackerDetails_TextView.setText("Evaluator NFD");
                dseNFTNewCarTrackerDetails_TextView.setText("Evaluator NFT");

                newCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
                financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
                DetailTrackerDescription_relativeLayout.setVisibility(GONE);
                usedCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);

                interestInFinanceNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
                interestInAccessoriesNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
                interestInInsuranceNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
                interestInEWNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
                buyerTypeNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
                modelVariantNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);

                accidentalClaimUsedCarTrackerDetails_RelativeLayout.setVisibility(View.VISIBLE);
                budgetToUsedCarTrackerDetails_RelativeLayout.setVisibility(GONE);
                budgetFromUsedCarTrackerDetails_RelativeLayout.setVisibility(GONE);

                evaluationWithinDaysEvaluationTrackerDetails_RelativeLayout.setVisibility(View.VISIBLE);
                fuelTypeEvaluationTrackerDetails_RelativeLayout.setVisibility(View.VISIBLE);
                colorEvaluationTrackerDetails_RelativeLayout.setVisibility(View.VISIBLE);

                regNoEvaluationTrackerDetails_RelativeLayout.setVisibility(View.VISIBLE);
                quotePriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.VISIBLE);
                expectedPriceEvaluationTrackerDetails_RelativeLayout.setVisibility(View.VISIBLE);
                break;

        }
        /*  if (process_id.equals("1")){
            newCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            financeDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            DetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            usedCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
        }else if (process_id.equals("4")){
            newCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            DetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            usedCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
        }else if (process_id.equals("5")){
            newCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            DetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            usedCarDetailTrackerDescription_relativeLayout.setVisibility(GONE);
        }else if (process_id.equals("6")){
            newCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            DetailTrackerDescription_relativeLayout.setVisibility(GONE);
            usedCarDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);
        }else if (process_id.equals("7")){
            newCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            DetailTrackerDescription_relativeLayout.setVisibility(GONE);
            usedCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
        }else if (process_id.equals("8")) {
            dseNameNewCarTrackerDetails_TextView.setText("Evaluator Name");
            leadAssignDateNewCarTrackerDetails_TextView.setText("Assigned Date (Evaluator)");
            leadAssignTimeNewCarTrackerDetails_TextView.setText("Assigned Time (Evaluator)");
            dseCallDateNewCarTrackerDetails_TextView.setText("Evaluator Call Date");
            dseCallTimeNewCarTrackerDetails_TextView.setText("Evaluator Call Time");
            dseCallStatusNewCarTrackerDetails_TextView.setText("Evaluator Call Status");
            dseFeedbackNewCarTrackerDetails_TextView.setText("Evaluator Feedback");
            dseNextActionNewCarTrackerDetails_TextView.setText("Evaluator NextAction");
            dseRemarkNewCarTrackerDetails_TextView.setText("Evaluator Remark");
            dseNFDNewCarTrackerDetails_TextView.setText("Evaluator NFD");
            dseNFTNewCarTrackerDetails_TextView.setText("Evaluator NFT");

            newCarDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            financeDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(GONE);
            DetailTrackerDescription_relativeLayout.setVisibility(GONE);
            usedCarDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);

            interestInFinanceNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
            interestInAccessoriesNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
            interestInInsuranceNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
            interestInEWNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
            buyerTypeNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
            modelVariantNewCarTrackerDetails_RelativeLayout.setVisibility(GONE);
        }
        */
    }

    @OnClick(R.id.backButton_ImageView)
    public void backbtn(){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
