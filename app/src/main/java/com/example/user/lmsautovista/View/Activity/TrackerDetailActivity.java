package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.SearchTrackerListBean;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackerDetailActivity extends AppCompatActivity {

    @BindView(R.id.leadnameTrackerDetail_TextView)
    TextView leadnameTrackerDetail_TextView;

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

    //Relative layout for finance, service and accessories
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
            financeDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);
        }else if (process_id.equals("4")){
            financeDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
        }else if (process_id.equals("5")){
            financeDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);
            accessoriesDetailTrackerDescription_relativeLayout.setVisibility(View.VISIBLE);
            serviceDetailTrackerDescription_relativeLayout.setVisibility(View.GONE);
        }


        leadnameTrackerDetail_TextView.setText(bean.getName());
        leadContactnoTrackerDetail_TextView.setText(bean.getContact_no());
        leadEnqIDTrackerDetail_TextView.setText(bean.getEnq_id());
        addressTrackerDetail_TextView.setText(bean.getAddress());
        emailIdTrackerDetail_TextView.setText(bean.getEmail());
        leadDateTrackerDetail_TextView.setText(bean.getLead_date());

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
        nfdTrackerDetailsString_TextView.setText(bean.getNextfollowupdate());
        remarkTrackerDetailsString_TextView.setText(bean.getComment());

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
}
