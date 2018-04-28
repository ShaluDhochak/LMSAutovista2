package com.example.user.lmsautovista.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Model.CustomerDetailsBean;
import com.example.user.lmsautovista.Presenter.CustomerDetailsPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerDetailsActivity extends AppCompatActivity implements IView.CustomerDetailsTaskView{

    CallingTaskNewLeadBean.Lead_Details beanList;

    @BindView(R.id.add_followUp_fab)
    FloatingActionButton add_followUp_fab;
    @BindView(R.id.followUpDetails_fab)
    FloatingActionButton followUpDetails_fab;


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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void ShowCustomerDetailsList(CustomerDetailsBean jsonObject) {

       NavigationLeadeagernessDetails_TextView.setVisibility(View.GONE);

       NavigationLeadname_TextView.setText(jsonObject.getCustomer_details().get(0).getName());
    //   NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getEagerness());
       NavigationLeademail_TextView.setText(jsonObject.getCustomer_details().get(0).getEmail());
       NavigationLeadContactno_TextView.setText(jsonObject.getCustomer_details().get(0).getContact_no());
       followupByNotificationCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getDealer());
       newVaraintCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getVariant_name());
       newModelCustomerDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getModel_name());
   //    navigationLeadBookedWithIn_textView.setText(jsonObject.getCustomer_details().get(0).getEagerness());
       feedbackStatusCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getFeedbackStatus());
       NavigationLeadAddressModel_TextView.setText(jsonObject.getCustomer_details().get(0).getAddress());
       NavigationLeadEnqID_TextView.setText(jsonObject.getCustomer_details().get(0).getEnq_id());
       nextActionCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getNextAction());
       callDateNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).getNextAction());

        //code for booking within days
        if ((jsonObject.getCustomer_details().get(0).getEagerness().toString().equals("90")) || (jsonObject.getCustomer_details().get(0).getEagerness().toString().equals(">60"))){
            NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
            NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getEagerness() + " Days");
            NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.green));
            NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.white));
        }else if (jsonObject.getCustomer_details().get(0).getEagerness().toString().equals("30")){
            NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
            NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getEagerness() +  " Days");
            NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.red));
            NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.white));
        }else if (jsonObject.getCustomer_details().get(0).getEagerness().toString().equals("60")){
            NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
            NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getEagerness() + " Days");
            NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.yellow));
            NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.black));
        }
        else if ((jsonObject.getCustomer_details().get(0).getEagerness().toString().equals("Just Researching"))||(jsonObject.getCustomer_details().get(0).getEagerness().equals("Undecided"))){
            NavigationLeadeagernessDetails_TextView.setVisibility(View.VISIBLE);
            NavigationLeadeagernessDetails_TextView.setText(jsonObject.getCustomer_details().get(0).getEagerness());
            NavigationLeadeagernessDetails_TextView.setBackgroundColor(getResources().getColor(R.color.green));
            NavigationLeadeagernessDetails_TextView.setTextColor(getResources().getColor(android.R.color.white));
        }
        else if (jsonObject.getCustomer_details().get(0).getEagerness().toString().equals("")){
            NavigationLeadeagernessDetails_TextView.setVisibility(View.GONE);
        }
      // remarkNotificationCustomerDetailsString_TextView.setText(jsonObject.getCustomer_details().get(0).get());
    }

    @OnClick(R.id.followUpDetails_fab)
    public void followUpDetails(){
        Intent followuPdetailIntent = new Intent(this, FollowUpDetailsActivity.class);
        followuPdetailIntent.putExtra("bean",beanList);
        followuPdetailIntent.putExtra("position",getIntent().getIntExtra("position",0));

        startActivity(followuPdetailIntent);
    }

    @OnClick(R.id.add_followUp_fab)
    public void addFollowUp(){
        Intent addFollowUpIntent = new Intent(this, AddFollowUpActivity.class);
        addFollowUpIntent.putExtra("bean",beanList);
        addFollowUpIntent.putExtra("position",getIntent().getIntExtra("position",0));
        startActivity(addFollowUpIntent);
    }

}
