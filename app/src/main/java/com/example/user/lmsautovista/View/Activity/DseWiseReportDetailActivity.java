package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.DSEReportBean;
import com.example.user.lmsautovista.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DseWiseReportDetailActivity extends AppCompatActivity {

    HomeActivity homeActivity;

    //Image for back button
    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    DSEReportBean.Dsewise_Count bean;

    @BindView(R.id.nameDseWiseLeadReport_txtView)
    TextView nameDseWiseLeadReport_txtView;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;
    @BindView(R.id.dseLeadsValueReport_textView)
    TextView dseLeadsValueReport_textView;
    @BindView(R.id.pendingFollowUpDseLeadValueReport_textView)
    TextView pendingFollowUpDseLeadValueReport_textView;
    @BindView(R.id.bookedFromAVDseLeadsValueReport_textView)
    TextView bookedFromAVDseLeadsValueReport_textView;
    @BindView(R.id.homeVisitDseLeadsValueReport_textView)
    TextView homeVisitDseLeadsValueReport_textView;
    @BindView(R.id.showroomVisitDseLeadValueReport_textView)
    TextView showroomVisitDseLeadValueReport_textView;
    @BindView(R.id.evaluationAlotDseValueReport_textView)
    TextView evaluationAlotDseValueReport_textView;
    @BindView(R.id.testDriveDseLeadValueReport_textView)
    TextView testDriveDseLeadValueReport_textView;
    @BindView(R.id.followUpDseLeadValueReport_textView)
    TextView followUpDseLeadValueReport_textView;
    @BindView(R.id.undecidedDseLeadValueReport_textView)
    TextView undecidedDseLeadValueReport_textView;

    @BindView(R.id.dealDseLeadValueReport_textView)
    TextView dealDseLeadValueReport_textView;
    @BindView(R.id.notInterestedDseLeadValueReport_textView)
    TextView notInterestedDseLeadValueReport_textView;
    @BindView(R.id.lostCodealerDseLeadValueReport_textView)
    TextView lostCodealerDseLeadValueReport_textView;
    @BindView(R.id.lostCompetitorBrandDseLeadValueReport_textView)
    TextView lostCompetitorBrandDseLeadValueReport_textView;
    @BindView(R.id.colorModelAvailableDseLeadValueReport_textView)
    TextView colorModelAvailableDseLeadValueReport_textView;
    @BindView(R.id.budgetIssueDseLeadValueReport_textView)
    TextView budgetIssueDseLeadValueReport_textView;
    @BindView(R.id.planCancelledDseLeadValueReport_textView)
    TextView planCancelledDseLeadValueReport_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dse_wise_report_detail);

        ButterKnife.bind(this);
    }

    private void initialiseUI(){
        lmsheading_TextView.setText("DseWise Report");
        //setValue To text
        setTextValue();
    }

    private void setTextValue(){
        bean = this.getIntent().getParcelableExtra("bean");

        nameDseWiseLeadReport_txtView.setText(bean.getFname() + " " + bean.getLname());

        dseLeadsValueReport_textView.setText(bean.getPending_new());
        pendingFollowUpDseLeadValueReport_textView.setText(bean.getPending_followup());
        bookedFromAVDseLeadsValueReport_textView.setText(bean.getAlready_booked_with_autovista_count());
        homeVisitDseLeadsValueReport_textView.setText(bean.getHome_visit_count());
        showroomVisitDseLeadValueReport_textView.setText(bean.getShowroom_visit_count());
        evaluationAlotDseValueReport_textView.setText(bean.getEvaluation_count());
        testDriveDseLeadValueReport_textView.setText(bean.getTest_drive_count());
        followUpDseLeadValueReport_textView.setText(bean.getFollow_up_count());
        undecidedDseLeadValueReport_textView.setText(bean.getUndecided_count());
        dealDseLeadValueReport_textView.setText(bean.getDeal_count());
        notInterestedDseLeadValueReport_textView.setText(bean.getNot_interested_count());
        lostCodealerDseLeadValueReport_textView.setText(bean.getLost_to_co_dealer_count());
        lostCompetitorBrandDseLeadValueReport_textView.setText(bean.getLost_to_competition_brand_count());
        colorModelAvailableDseLeadValueReport_textView.setText(bean.getColor_model_availability_count());
        budgetIssueDseLeadValueReport_textView.setText(bean.getLow_budget_count());
        planCancelledDseLeadValueReport_textView.setText(bean.getPlan_cancelled_count());

    }

    @OnClick(R.id.backButton_ImageView)
    public void backBtn(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
