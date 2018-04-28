package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.SearchCustomerBean;
import com.example.user.lmsautovista.Presenter.SearchCustomerPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Adapter.SearchViaContcatNoAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchLeadViaContactNoActivity extends AppCompatActivity implements IView.CustomerSearchView{

    @BindView(R.id.searchContactNoCutomerDetails_listView)
    ListView searchContactNoCutomerDetails_listView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    SearchCustomerPresenter searchCustomerPresenter;
    SearchViaContcatNoAdapter searchViaContcatNoAdapter;
    SearchCustomerBean.Lead_Data bean;
    ArrayList<SearchCustomerBean.Lead_Data> dashboardCountList = new ArrayList<SearchCustomerBean.Lead_Data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lead_via_contact_no);

        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
        String contact_no = getIntent().getStringExtra("contact_no");
        searchCustomerPresenter = new SearchCustomerPresenter(this);
        searchCustomerPresenter.getSearchViaContactNoList(contact_no, SearchLeadViaContactNoActivity.this);
    }

    @Override
    public void showSearchCustomerDetails(SearchCustomerBean jsonObject) {
        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getLead_data());
        searchViaContcatNoAdapter = new SearchViaContcatNoAdapter(SearchLeadViaContactNoActivity.this, jsonObject.getLead_data());
        searchContactNoCutomerDetails_listView.setAdapter(searchViaContcatNoAdapter);
        searchViaContcatNoAdapter.notifyDataSetChanged();

    }
}
