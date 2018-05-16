package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.lmsautovista.Model.SearchCustomerBean;
import com.example.user.lmsautovista.Presenter.EditCustomerListPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Adapter.EditCustomerListAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCustomerListDetailActivity extends AppCompatActivity implements IView.EditCustomerListView{

    @BindView(R.id.editCutomerDetails_listView)
    ListView editCutomerDetails_listView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    EditCustomerListAdapter editCustomerListAdapter;

    EditCustomerListPresenter editCustomerListPresenter;
    SearchCustomerBean.Lead_Data bean;
    ArrayList<SearchCustomerBean.Lead_Data> dashboardCountList = new ArrayList<SearchCustomerBean.Lead_Data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_list_detail);

        ButterKnife.bind(this);
        editCustomerListPresenter = new EditCustomerListPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        String contact_no = getIntent().getStringExtra("contact_no");
        editCustomerListPresenter.getEditCustomerList(contact_no, this);
    }

    @Override
    public void showEditCustomerList(SearchCustomerBean jsonObject) {

        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getLead_data());
        editCustomerListAdapter = new EditCustomerListAdapter(EditCustomerListDetailActivity.this, jsonObject.getLead_data());
        editCutomerDetails_listView.setAdapter(editCustomerListAdapter);
        editCustomerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void backBtn(){
        super.onBackPressed();
    }
}
