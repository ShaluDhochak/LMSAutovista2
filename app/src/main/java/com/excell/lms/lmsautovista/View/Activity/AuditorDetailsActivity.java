package com.excell.lms.lmsautovista.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.AuditorRemarkBean;
import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.Model.FollowUpDetailsBean;
import com.excell.lms.lmsautovista.Presenter.AuditorDetailPresenter;
import com.excell.lms.lmsautovista.Presenter.FollowUpDetailPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.AuditorDetailsAdapter;
import com.excell.lms.lmsautovista.View.Adapter.FollowUpDetailsAdapter;
import com.excell.lms.lmsautovista.View.Adapter.FollowUpDetailsComplainAdapter;
import com.excell.lms.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuditorDetailsActivity extends AppCompatActivity  implements IView.AuditorDetailsView{

    @BindView(R.id.activityFeedHeading_TextView)
    TextView activityFeedHeading_TextView;

    CallingTaskNewLeadBean.Lead_Details beanList;
    AuditorDetailPresenter auditorDetailPresenter;

    @BindView(R.id.followupBasedonBookingId_rv)
    RecyclerView followupBasedonBookingId_rv;

    @BindView(R.id.followUpDetailsBookingId_TextView)
    TextView followUpDetailsBookingId_TextView;

    @BindView(R.id.followUpDetailsContactNo_TextView)
    TextView followUpDetailsContactNo_TextView;

    @BindView(R.id.followUpDetailsName_TextView)
    TextView followUpDetailsName_TextView;

    @BindView(R.id.customer_followDetails_fab)
    FloatingActionButton customer_followDetails_fab;

    @BindView(R.id.add_followUpDetails_fab)
    FloatingActionButton add_followUpDetails_fab;

    @BindView(R.id.auditorDetail_auditorDetails_fab)
    FloatingActionButton auditorDetail_auditorDetails_fab;

    @BindView(R.id.addAuditor_auditorDetails_fab)
    FloatingActionButton addAuditor_auditorDetails_fab;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    //Cardview for FollowUP
    @BindView(R.id.followUpDetails_cv)
    CardView followUpDetails_cv;

    ArrayList<AuditorRemarkBean.Auditor_Remark_Details> dashboardCountList = new ArrayList<AuditorRemarkBean.Auditor_Remark_Details>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor_details);

        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtilities.isInternet(this)) {
            initialiseUI();
        } else {
            Toast.makeText(AuditorDetailsActivity.this, "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialiseUI(){

        activityFeedHeading_TextView.setText("Auditor Remark Detail");

        beanList = getIntent().getParcelableExtra("bean");
        //String enquiryId = beanList.getEnq_id().toString();
        auditorDetailPresenter = new AuditorDetailPresenter(this);

        if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
            String enquiryId = beanList.getComplaint_id().toString();
            followUpDetailsBookingId_TextView.setText(beanList.getComplaint_id());
            followUpDetailsContactNo_TextView.setText(beanList.getContact_no());
            followUpDetailsName_TextView.setText(beanList.getName());

            auditorDetailPresenter.getAuditorDetailsList(enquiryId, AuditorDetailsActivity.this);
        }else {
            String enquiryId = beanList.getEnq_id().toString();
            followUpDetailsBookingId_TextView.setText(beanList.getEnq_id());
            followUpDetailsContactNo_TextView.setText(beanList.getContact_no());
            followUpDetailsName_TextView.setText(beanList.getName());

            auditorDetailPresenter.getAuditorDetailsList(enquiryId, AuditorDetailsActivity.this);
        }
    }

    @OnClick(R.id.customer_followDetails_fab)
    public void onclickCustomerFab(){
        Intent customerDetailsIntent = new Intent(AuditorDetailsActivity.this, CustomerDetailsActivity.class);
        customerDetailsIntent.putExtra("bean", beanList);
        customerDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        customerDetailsIntent.putParcelableArrayListExtra("arrayList",getIntent().getParcelableArrayListExtra("arrayList"));
        startActivity(customerDetailsIntent);
    }

    @OnClick(R.id.add_followUpDetails_fab)
    public void onClickFollowUpDetails(){
        Intent followUpDetailsIntent = new Intent(AuditorDetailsActivity.this, AddFollowUpActivity.class);
        followUpDetailsIntent.putExtra("bean", beanList);
        followUpDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(followUpDetailsIntent);
    }

    @OnClick(R.id.auditorDetail_auditorDetails_fab)
    public void onClickAuditorDetails(){
        Intent auditorDetailsIntent = new Intent(AuditorDetailsActivity.this, AuditorDetailsActivity.class);
        auditorDetailsIntent.putExtra("bean", beanList);
        auditorDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(auditorDetailsIntent);
    }

    @OnClick(R.id.addAuditor_auditorDetails_fab)
    public void onClickAddAuditor(){
        Intent aaddAuditorIntent = new Intent(AuditorDetailsActivity.this, AddAuditorRemarkDetailActivity.class);
        aaddAuditorIntent.putExtra("bean", beanList);
        aaddAuditorIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(aaddAuditorIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void onBackBtnPressed(){
        onBackPressed();
    }

    @Override
    public void ShowAuditorDetailsList(AuditorRemarkBean jsonObject) {
        try {

            if ((SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, "")).equals("9")) {
                dashboardCountList.clear();
                dashboardCountList.addAll(jsonObject.getAuditor_remark_detail());

                AuditorDetailsAdapter auditorDetailsAdapter = new AuditorDetailsAdapter(AuditorDetailsActivity.this, jsonObject.getAuditor_remark_detail());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AuditorDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
                followupBasedonBookingId_rv.setLayoutManager(mLayoutManager);
                followupBasedonBookingId_rv.setItemAnimator(new DefaultItemAnimator());
                followupBasedonBookingId_rv.setAdapter(auditorDetailsAdapter);
            }else {
                dashboardCountList.clear();
                dashboardCountList.addAll(jsonObject.getAuditor_remark_detail());

                AuditorDetailsAdapter auditorDetailsAdapter = new AuditorDetailsAdapter(AuditorDetailsActivity.this, jsonObject.getAuditor_remark_detail());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AuditorDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
                followupBasedonBookingId_rv.setLayoutManager(mLayoutManager);
                followupBasedonBookingId_rv.setItemAnimator(new DefaultItemAnimator());
                followupBasedonBookingId_rv.setAdapter(auditorDetailsAdapter);
            }
        }catch (Exception e){
        }
    }
}
