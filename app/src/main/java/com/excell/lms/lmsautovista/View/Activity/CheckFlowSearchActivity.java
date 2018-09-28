package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.CheckFlowTransferLeadDetailBean;
import com.excell.lms.lmsautovista.Model.SearchCustomerBean;
import com.excell.lms.lmsautovista.Presenter.CheckFlowPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Adapter.CheckFlowLeadDetailsAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckFlowSearchActivity extends AppCompatActivity implements IView.ICheckFlowView{

    @BindView(R.id.checkflow_listView)
    ListView checkflow_listView;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    @BindView(R.id.checkFlow_cardView)
    CardView checkFlow_cardView;

    SearchCustomerBean.Lead_Data bean;

    CheckFlowLeadDetailsAdapter checkFlowLeadDetailsAdapter;
    CheckFlowPresenter checkFlowPresenter;
    ArrayList<CheckFlowTransferLeadDetailBean.Lead_Data> leadsArrayList = new ArrayList<>();

    //Back Button ImageView
    ImageView backButton_ImageView;

    String enq_id;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_flow_search);

        ButterKnife.bind(this);
        initialiseUI();
    }

private void initialiseUI(){
        lmsheading_TextView.setText("Transferred Lead Details");

            pDialog = new ProgressDialog(CheckFlowSearchActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

        checkFlowPresenter = new CheckFlowPresenter(this);
        bean = this.getIntent().getParcelableExtra("bean");
        enq_id = bean.getEnq_id();
        checkFlowPresenter.getCheckFlow(enq_id, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void backBtnPress(){
        super.onBackPressed();
    }

    @Override
    public void showCheckFlow(CheckFlowTransferLeadDetailBean jsonObject) {
        pDialog.dismiss();
        try{
            if (jsonObject.getLead_data().size()>0) {
                checkFlow_cardView.setVisibility(View.VISIBLE);
                leadsArrayList.clear();
                leadsArrayList.addAll(jsonObject.getLead_data());
                checkFlowLeadDetailsAdapter = new CheckFlowLeadDetailsAdapter(CheckFlowSearchActivity.this, jsonObject.getLead_data());
                checkflow_listView.setAdapter(checkFlowLeadDetailsAdapter);
                checkFlowLeadDetailsAdapter.notifyDataSetChanged();
            }else{
                checkFlow_cardView.setVisibility(View.GONE);
            }
        }catch(Exception e){

        }
    }
}
