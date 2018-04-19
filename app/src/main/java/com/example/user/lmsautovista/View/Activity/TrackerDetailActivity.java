package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.SearchTrackerListBean;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;

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
    @BindView(R.id.leadEnqIDTrackerDetail_TextView)
    TextView leadEnqIDTrackerDetail_TextView;
    @BindView(R.id.addressTrackerDetail_TextView)
    TextView addressTrackerDetail_TextView;
    @BindView(R.id.emailIdTrackerDetail_TextView)
    TextView emailIdTrackerDetail_TextView;
    @BindView(R.id.leadDateTrackerDetail_TextView)
    TextView leadDateTrackerDetail_TextView;

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

    @BindView(R.id.showroomLocNewCarTrackerString_TextView)
    TextView showroomLocNewCarTrackerString_TextView;
    @BindView(R.id.cseNameNewCarTrackerString_TextView)
    TextView cseNameNewCarTrackerString_TextView;

    @BindView(R.id.cseCallDateNewCarTrackerString_TextView)
    TextView cseCallDateNewCarTrackerString_TextView;
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

    @BindView(R.id.tdHvDateNewCarTrackerString_TextView)
    TextView tdHvDateNewCarTrackerString_TextView;
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

        if (process_id.equals("1")){
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
        }

        //header
        lmsheading_TextView.setText("Tracker Details");

        //Common Details
        leadnameTrackerDetail_TextView.setText(bean.getName());
        leadContactnoTrackerDetail_TextView.setText(bean.getContact_no());
        leadEnqIDTrackerDetail_TextView.setText(bean.getEnq_id());
        addressTrackerDetail_TextView.setText(bean.getAddress());
        emailIdTrackerDetail_TextView.setText(bean.getEmail());
        leadDateTrackerDetail_TextView.setText(bean.getLead_date());
        leadBookingDayTrackerDetail_TextView.setText("Booking withIn Days: " + bean.getDays60_booking());

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
        cseNameNewCarTrackerString_TextView.setText(bean.getCse_fname() + " "+ bean.getCse_lname());
        cseCallDateNewCarTrackerString_TextView.setText(bean.getCse_date());
        cseFeedbackNewCarTrackerString_TextView.setText(bean.getCsefeedback());
        cseNextActionNewCarTrackerString_TextView.setText(bean.getCse_nfd());
        cseRemarkNewCarTrackerString_TextView.setText(bean.getCse_comment());
        cseNFDNewCarTrackerString_TextView.setText(bean.getCse_nfd());
        cseNFTNewCarTrackerString_TextView.setText(bean.getCse_nftime());
        tdHvDateNewCarTrackerString_TextView.setText(bean.getTd_hv_date());
        dseNameDateNewCarTrackerString_TextView.setText(bean.getDse_fname() +  " "+ bean.getDse_lname());
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

        //detail TextView
        if (bean.getLead_source().equals("")){
            leadSourceTrackerString_TextView.setText("Web");
        }else if (bean.getLead_source().equals("Facebook")){
            leadSourceTrackerString_TextView.setText(bean.getEnquiry_for());
        }else {
            leadSourceTrackerString_TextView.setText(bean.getLead_source());
        }

       // leadSourceTrackerString_TextView.setText(bean.getLead_source());
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

    @OnClick(R.id.backButton_ImageView)
    public void backbtn(){
        super.onBackPressed();
    }
}
