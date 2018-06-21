package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.Presenter.DashboardDetailPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.PendingDashboardAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EscalationLevel3Activity extends AppCompatActivity  implements IView.DashboardDetailView{

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

    @BindView(R.id.nextPreviousBtnNotification_linearlayout)
    LinearLayout nextPreviousBtnNotification_linearlayout;

    String role, user_id;
    ArrayList<CallingTaskNewLeadBean.Lead_Details> dashboardCountList = new ArrayList<CallingTaskNewLeadBean.Lead_Details>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_lead_detail_list);

        ButterKnife.bind(this);

        nextPreviousBtnNotification_linearlayout.setVisibility(View.GONE);
        lmsheading_TextView.setText("Escalation Level 3 Leads");
        //  bean = UnassignedLeadDashboardActivity.this.getIntent().getParcelableExtra("bean");
        dashboardPresenter = new DashboardDetailPresenter(EscalationLevel3Activity.this);

        progressDialog = new ProgressDialog(EscalationLevel3Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtilities.isInternet(this)) {
            role = getIntent().getStringExtra("role_id");
            user_id =getIntent().getStringExtra("user_id") ;
            dashboardPresenter.getEscalationLevel3DashboardList(role, user_id,EscalationLevel3Activity.this);
        } else {
            Toast.makeText(EscalationLevel3Activity.this, "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void backBtn(){
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
    public void ShowDashboardDetailCount(CallingTaskNewLeadBean jsonObject) {
        try {
            dashboardCountList.clear();
            dashboardCountList.addAll(jsonObject.getLead_details());
            totalLeadHeading_TextView.setText("Total Leads: " + jsonObject.getLead_details_count().get(0).getCount_lead());

            PendingDashboardAdapter dashboardAdapter = new PendingDashboardAdapter(EscalationLevel3Activity.this, jsonObject.getLead_details());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(EscalationLevel3Activity.this, LinearLayoutManager.VERTICAL, false);
            notificationLead_ListView.setLayoutManager(mLayoutManager);
            notificationLead_ListView.setItemAnimator(new DefaultItemAnimator());
            notificationLead_ListView.setAdapter(dashboardAdapter);
        }catch (Exception e){

        }
    }
}
