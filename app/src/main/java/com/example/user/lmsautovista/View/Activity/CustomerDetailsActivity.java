package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Presenter.CustomerDetailsPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerDetailsActivity extends AppCompatActivity implements IView.CustomerDetailsTaskView{

    CallingTaskNewLeadBean.Lead_Details beanList;

    @BindView(R.id.add_followUp_floating_action_button)
    FloatingActionButton add_followUp_floating_action_button;
    @BindView(R.id.followUpDetails_Notification_floating_action_button)
    FloatingActionButton followUpDetails_Notification_floating_action_button;


    @BindView(R.id.NavigationLeadname_TextView)
    TextView NavigationLeadname_TextView;
    @BindView(R.id.NavigationLeadeagernessDetails_TextView)
    TextView NavigationLeadeagernessDetails_TextView;
    @BindView(R.id.NavigationLeademail_TextView)
    TextView NavigationLeademail_TextView;
    @BindView(R.id.NavigationLeadContactno_TextView)
    TextView NavigationLeadContactno_TextView;

    @BindView(R.id.followupByNotificationCustomerDetails_TextView)
    TextView followupByNotificationCustomerDetails_TextView;
    @BindView(R.id.newVaraintCustomerDetails_TextView)
    TextView newVaraintCustomerDetails_TextView;
    @BindView(R.id.newModelCustomerDetails_TextView)
    TextView newModelCustomerDetails_TextView;

    @BindView(R.id.navigationLeadBookedWithIn_textView)
    TextView navigationLeadBookedWithIn_textView;
    @BindView(R.id.feedbackStatusCustomerDetailsString_TextView)
    TextView feedbackStatusCustomerDetailsString_TextView;
    @BindView(R.id.NavigationLeadAddressModel_TextView)
    TextView NavigationLeadAddressModel_TextView;

    @BindView(R.id.NavigationLeadEnqID_TextView)
    TextView NavigationLeadEnqID_TextView;
    @BindView(R.id.nextActionCustomerDetailsString_TextView)
    TextView nextActionCustomerDetailsString_TextView;
    @BindView(R.id.callDateNotificationCustomerDetailsString_TextView)
    TextView callDateNotificationCustomerDetailsString_TextView;
    @BindView(R.id.nfdNotificationCustomerDetailsString_TextView)
    TextView nfdNotificationCustomerDetailsString_TextView;
    @BindView(R.id.remarkNotificationCustomerDetailsString_TextView)
    TextView remarkNotificationCustomerDetailsString_TextView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.dialContactNotification_Button)
    ImageView dialContactNotification_Button;

    //presenter class
    CustomerDetailsPresenter customerDetailsPresenter;

    /*
     NewCarCallingTaskBean.Notification bean;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
        customerDetailsPresenter = new CustomerDetailsPresenter(this);

        beanList = getIntent().getParcelableExtra("bean");
        String enquiryId = beanList.getEnq_id().toString();

        customerDetailsPresenter.getCustomerDetailsList(enquiryId,CustomerDetailsActivity.this);

    }

    @Override
    public void ShowCustomerDetailsList(CallingTaskNewLeadBean jsonObject) {

    }
}
