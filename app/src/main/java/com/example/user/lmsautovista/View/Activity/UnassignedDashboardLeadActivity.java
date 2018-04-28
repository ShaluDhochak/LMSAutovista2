package com.example.user.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Presenter.DashboardDetailPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.NetworkUtilities;
import com.example.user.lmsautovista.View.Adapter.UnassignedDashboardDetailAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnassignedDashboardLeadActivity extends AppCompatActivity implements IView.DashboardDetailView{

    ProgressDialog progressDialog;
    DashboardDetailPresenter dashboardPresenter;

    @BindView(R.id.notificationLead_ListView)
    RecyclerView notificationLead_ListView;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    @BindView(R.id.totalLeadHeading_TextView)
    TextView totalLeadHeading_TextView;


    ArrayList<CallingTaskNewLeadBean.Lead_Details> dashboardCountList = new ArrayList<CallingTaskNewLeadBean.Lead_Details>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_lead_detail_list);
        ButterKnife.bind(this);

        lmsheading_TextView.setText("Unassigned Leads");
      //  bean = UnassignedLeadDashboardActivity.this.getIntent().getParcelableExtra("bean");
        dashboardPresenter = new DashboardDetailPresenter(UnassignedDashboardLeadActivity.this);

        progressDialog = new ProgressDialog(UnassignedDashboardLeadActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);


    }

    @Override
    public void onResume() {
        super.onResume();

        if (NetworkUtilities.isInternet(this)) {
            dashboardPresenter.getUnassignedDashboardDetailList(UnassignedDashboardLeadActivity.this);
        } else {
            Toast.makeText(UnassignedDashboardLeadActivity.this, "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void ShowDashboardDetailCount(CallingTaskNewLeadBean jsonObject) {
        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getLead_details());
        totalLeadHeading_TextView.setText("Total Leads: "  +jsonObject.getLead_details_count().get(0).getCount_lead());


        UnassignedDashboardDetailAdapter dashboardAdapter = new UnassignedDashboardDetailAdapter(UnassignedDashboardLeadActivity.this,jsonObject.getLead_details());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UnassignedDashboardLeadActivity.this, LinearLayoutManager.VERTICAL, false);
        notificationLead_ListView.setLayoutManager(mLayoutManager);
        notificationLead_ListView.setItemAnimator(new DefaultItemAnimator());
        notificationLead_ListView.setAdapter(dashboardAdapter);
    }
}
