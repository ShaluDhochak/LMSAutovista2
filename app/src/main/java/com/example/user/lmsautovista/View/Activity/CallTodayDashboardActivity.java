package com.example.user.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lmsautovista.Model.DashboardLeadDetailBean;
import com.example.user.lmsautovista.Presenter.DashboardDetailPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.NetworkUtilities;
import com.example.user.lmsautovista.View.Adapter.CallTodayDashboardDetailAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallTodayDashboardActivity extends AppCompatActivity implements IView.DashboardDetailView{

    ProgressDialog progressDialog;
    DashboardDetailPresenter dashboardPresenter;

    @BindView(R.id.notificationLead_ListView)
    RecyclerView notificationLead_ListView;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    @BindView(R.id.totalLeadHeading_TextView)
            TextView totalLeadHeading_TextView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;



    ArrayList<DashboardLeadDetailBean.Lead_Details> dashboardCountList = new ArrayList<DashboardLeadDetailBean.Lead_Details>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_lead_detail_list);

        ButterKnife.bind(this);

        lmsheading_TextView.setText("Call Today Leads");
        //  bean = UnassignedLeadDashboardActivity.this.getIntent().getParcelableExtra("bean");
        dashboardPresenter = new DashboardDetailPresenter(CallTodayDashboardActivity.this);

        progressDialog = new ProgressDialog(CallTodayDashboardActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (NetworkUtilities.isInternet(this)) {
            dashboardPresenter.getCallTodayDashboardDetailList(CallTodayDashboardActivity.this);
        } else {
            Toast.makeText(CallTodayDashboardActivity.this, "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.backButton_ImageView)
    public void OnBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void ShowDashboardDetailCount(DashboardLeadDetailBean jsonObject) {
        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getLead_details());
        totalLeadHeading_TextView.setText("Total Leads: " +jsonObject.getLead_details_count().get(0).getCount_lead());

        CallTodayDashboardDetailAdapter dashboardAdapter = new CallTodayDashboardDetailAdapter(CallTodayDashboardActivity.this,jsonObject.getLead_details());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CallTodayDashboardActivity.this, LinearLayoutManager.VERTICAL, false);
        notificationLead_ListView.setLayoutManager(mLayoutManager);
        notificationLead_ListView.setItemAnimator(new DefaultItemAnimator());
        notificationLead_ListView.setAdapter(dashboardAdapter);
    }
}

