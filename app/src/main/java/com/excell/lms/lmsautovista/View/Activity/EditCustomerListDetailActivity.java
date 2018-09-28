package com.excell.lms.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.excell.lms.lmsautovista.Model.SearchCustomerBean;
import com.excell.lms.lmsautovista.Presenter.EditCustomerListPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Adapter.EditCustomerListAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCustomerListDetailActivity extends AppCompatActivity implements IView.EditCustomerListView{

    @BindView(R.id.editCutomerDetails_listView)
    ListView editCutomerDetails_listView;
    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.editCustomerDetails_cardView)
    CardView editCustomerDetails_cardView;

    EditCustomerListAdapter editCustomerListAdapter;
    ProgressDialog pDialog;

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
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

        String contact_no = getIntent().getStringExtra("contact_no");
        editCustomerDetails_cardView.setVisibility(View.GONE);
        editCustomerListPresenter.getEditCustomerList(contact_no, this);
    }

    @Override
    public void showEditCustomerList(SearchCustomerBean jsonObject) {
     try{
         if (jsonObject.getLead_data().size()>0) {
             pDialog.dismiss();
             editCustomerDetails_cardView.setVisibility(View.VISIBLE);
             dashboardCountList.clear();
             dashboardCountList.addAll(jsonObject.getLead_data());
             editCustomerListAdapter = new EditCustomerListAdapter(EditCustomerListDetailActivity.this, jsonObject.getLead_data());
             editCutomerDetails_listView.setAdapter(editCustomerListAdapter);
             editCustomerListAdapter.notifyDataSetChanged();
         }else{
            pDialog.dismiss();
             editCustomerDetails_cardView.setVisibility(View.GONE);
         }
      }catch (Exception e){
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
}
