package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.DashboardLeadDetailBean;
import com.example.user.lmsautovista.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardCountDetailActivity extends AppCompatActivity {

    @BindView(R.id.NotificationDetails_TextView)
    TextView NotificationDetails_TextView;

    @BindView(R.id.leadnameNotification_TextView)
    TextView leadnameNotification_TextView;

    @BindView(R.id.leadDateNotification_TextView)
    TextView leadDateNotification_TextView;

    @BindView(R.id.leadContactnoNotification_TextView)
    TextView leadContactnoNotification_TextView;

    @BindView(R.id.leadEnqIDNotification_TextView)
    TextView leadEnqIDNotification_TextView;

    @BindView(R.id.interestedInNotificationString_TextView)
    TextView interestedInNotificationString_TextView;

    @BindView(R.id.feedbackStatusNotificationDetails_TextView)
    TextView feedbackStatusNotificationDetails_TextView;

    @BindView(R.id.nextActionNotificationDetailsString_TextView)
    TextView nextActionNotificationDetailsString_TextView;

    @BindView(R.id.currentUserNotificationDetailsString_TextView)
    TextView currentUserNotificationDetailsString_TextView;

    @BindView(R.id.callDateNotificationDetailsString_TextView)
    TextView callDateNotificationDetailsString_TextView;

    @BindView(R.id.nfdNotificationDetailsString_TextView)
    TextView nfdNotificationDetailsString_TextView;

    @BindView(R.id.remarkNotificationDetailsString_TextView)
    TextView remarkNotificationDetailsString_TextView;

    DashboardLeadDetailBean.Lead_Details bean;
    String title;
    int position;
    private ArrayList<DashboardLeadDetailBean.Lead_Details> allLeadsBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_count_detail);

        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialiseUI();
    }

    private void initialiseUI(){

        bean = DashboardCountDetailActivity.this.getIntent().getParcelableExtra("bean");
        position = DashboardCountDetailActivity.this.getIntent().getIntExtra("position", 0);
        title = DashboardCountDetailActivity.this.getIntent().getStringExtra("heading");

        NotificationDetails_TextView.setText(title);

        leadnameNotification_TextView.setText(bean.getName());
        leadContactnoNotification_TextView.setText(bean.getContact_no());
        leadDateNotification_TextView.setText(bean.getCreated_date());
        leadEnqIDNotification_TextView.setText(bean.getEnq_id());

        if (bean.getLead_source().equals("")){
            interestedInNotificationString_TextView.setText("Web");
        }else if (bean.getLead_source().equals("Facebook")){
            interestedInNotificationString_TextView.setText(bean.getEnquiry_for());
        }else {
            interestedInNotificationString_TextView.setText(bean.getLead_source());
        }

        feedbackStatusNotificationDetails_TextView.setText(bean.getFeedbackStatus());
        nextActionNotificationDetailsString_TextView.setText(bean.getNextAction());

        if ((bean.getAssign_to_cse().equals("0")) && (!(bean.getAssign_by_cse_tl().equals("0")))) {
            currentUserNotificationDetailsString_TextView.setText(String.format("%s %s", bean.getCsetl_fname(), bean.getCsetl_lname()));
            callDateNotificationDetailsString_TextView.setText(bean.getCse_date());
            nfdNotificationDetailsString_TextView.setText(bean.getCse_nfd());
            remarkNotificationDetailsString_TextView.setText(bean.getCse_comment());
        }else{
            currentUserNotificationDetailsString_TextView.setText(String.format("%s %s", bean.getCse_fname(), bean.getCse_lname()));
            callDateNotificationDetailsString_TextView.setText(bean.getCse_date());
            nfdNotificationDetailsString_TextView.setText(bean.getCse_nfd());
            remarkNotificationDetailsString_TextView.setText(bean.getCse_comment());
        }

    }


}
