package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.SearchCustomerBean;
import com.excell.lms.lmsautovista.Presenter.SearchCustomerPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Adapter.SearchViaContcatNoAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchLeadViaContactNoActivity extends AppCompatActivity implements IView.CustomerSearchView{

    @BindView(R.id.searchContactNoCutomerDetails_listView)
    ListView searchContactNoCutomerDetails_listView;
    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;
    @BindView(R.id.CustomerDeatilsSearch_cardView)
    CardView CustomerDeatilsSearch_cardView;

    SearchCustomerPresenter searchCustomerPresenter;
    SearchViaContcatNoAdapter searchViaContcatNoAdapter;
    SearchCustomerBean.Lead_Data bean;
    ArrayList<SearchCustomerBean.Lead_Data> dashboardCountList = new ArrayList<SearchCustomerBean.Lead_Data>();
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lead_via_contact_no);
        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
        String contact_no = getIntent().getStringExtra("contact_no");

        pDialog = new ProgressDialog(SearchLeadViaContactNoActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        searchCustomerPresenter = new SearchCustomerPresenter(this);
        searchCustomerPresenter.getSearchViaContactNoList(contact_no, SearchLeadViaContactNoActivity.this);
    }

    @Override
    public void showSearchCustomerDetails(SearchCustomerBean jsonObject) {
        pDialog.dismiss();
        try {
            if (jsonObject.getLead_data().size()>0) {
                dashboardCountList.clear();
                dashboardCountList.addAll(jsonObject.getLead_data());
                searchViaContcatNoAdapter = new SearchViaContcatNoAdapter(SearchLeadViaContactNoActivity.this, jsonObject.getLead_data());
                searchContactNoCutomerDetails_listView.setAdapter(searchViaContcatNoAdapter);
                searchViaContcatNoAdapter.notifyDataSetChanged();
            }else{
                CustomerDeatilsSearch_cardView.setVisibility(View.GONE);
            }
        }catch(Exception e){
        }
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
