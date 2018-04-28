package com.example.user.lmsautovista.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Model.FollowUpDetailsBean;
import com.example.user.lmsautovista.Presenter.FollowUpDetailPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Adapter.FollowUpDetailsAdapter;
import com.example.user.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FollowUpDetailsActivity extends AppCompatActivity implements IView.FollowUpDetailsView{

    CallingTaskNewLeadBean.Lead_Details beanList;
    FollowUpDetailPresenter followUpDetailPresenter;

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

    ArrayList<FollowUpDetailsBean.FollowUp_details> dashboardCountList = new ArrayList<FollowUpDetailsBean.FollowUp_details>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_details);

        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
        beanList = getIntent().getParcelableExtra("bean");
        String enquiryId = beanList.getEnq_id().toString();

        followUpDetailsBookingId_TextView.setText(beanList.getEnq_id());
        followUpDetailsContactNo_TextView.setText(beanList.getContact_no());
        followUpDetailsName_TextView.setText(beanList.getName());

        followUpDetailPresenter = new FollowUpDetailPresenter(this);
        followUpDetailPresenter.getFollowUpDetailsList(enquiryId, FollowUpDetailsActivity.this);
    }

    @OnClick(R.id.customer_followDetails_fab)
    public void onclickCustomerFab(){
        Intent customerDetailsIntent = new Intent(FollowUpDetailsActivity.this, CustomerDetailsActivity.class);
        customerDetailsIntent.putExtra("bean", beanList);
        customerDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
        customerDetailsIntent.putParcelableArrayListExtra("arrayList",getIntent().getParcelableArrayListExtra("arrayList"));
        startActivity(customerDetailsIntent);
    }
    @OnClick(R.id.add_followUpDetails_fab)
    public void onClickFollowUpDetails(){
        Intent followUpDetailsIntent = new Intent(FollowUpDetailsActivity.this, AddFollowUpActivity.class);
        followUpDetailsIntent.putExtra("bean", beanList);
        followUpDetailsIntent.putExtra("position",getIntent().getIntExtra("position",0));
       // customerDetailsIntent.putParcelableArrayListExtra("arrayList",getIntent().getParcelableArrayListExtra("arrayList"));
        startActivity(followUpDetailsIntent);
    }

    @Override
    public void ShowFollowUpDetailsList(FollowUpDetailsBean jsonObject) {
        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getFollowup_details());

        FollowUpDetailsAdapter dashboardAdapter = new FollowUpDetailsAdapter(FollowUpDetailsActivity.this,jsonObject.getFollowup_details());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FollowUpDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
        followupBasedonBookingId_rv.setLayoutManager(mLayoutManager);
        followupBasedonBookingId_rv.setItemAnimator(new DefaultItemAnimator());
        followupBasedonBookingId_rv.setAdapter(dashboardAdapter);
    }
}
