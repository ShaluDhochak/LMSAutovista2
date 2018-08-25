package com.excell.lms.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.NewCarStock1Bean;
import com.excell.lms.lmsautovista.Model.NewCarStockCountDetailsBean;
import com.excell.lms.lmsautovista.Presenter.NewCarStockCountDetailPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.NewCarStockDetailAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewCarStockCountDetailActivity extends AppCompatActivity implements IView.NewCarStockCountDetailView {

    @BindView(R.id.newCarStockFilterDetails_ListView)
    RecyclerView newCarStockFilterDetails_ListView;
    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    NewCarStockCountDetailPresenter newCarStockCountDetailPresenter;
    ArrayList<NewCarStockCountDetailsBean.New_Stock_List> newStockList = new ArrayList<NewCarStockCountDetailsBean.New_Stock_List>();
    NewCarStockCountDetailsBean.New_Stock_List bean;
    NewCarStock1Bean.New_stock_Count bean1;

    String ageing, model, location, visit_status, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car_stock_count_detail);

        ButterKnife.bind(this);
        newCarStockCountDetailPresenter = new NewCarStockCountDetailPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        lmsheading_TextView.setText("New Car Stock Details");
        if (NetworkUtilities.isInternet(NewCarStockCountDetailActivity.this)) {
            bean1 = getIntent().getParcelableExtra("bean");
            model = bean1.getSubmodel().toString();
            location = bean1.getAssigned_location().toString();
            if (model.equals("")){
                newCarStockCountDetailPresenter.getNewCarStockCountDetail(NewCarStockCountDetailActivity.this);
            }else{
                newCarStockCountDetailPresenter.getNewCarStockCountModelDetail(model,NewCarStockCountDetailActivity.this);
            }
         } else {
            Toast.makeText(NewCarStockCountDetailActivity.this, "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.backButton_ImageView)
    public void onbackpressReg(){
        onBackPressed();
    }

    @Override
    public void showNewCarStockCountList(NewCarStockCountDetailsBean jsonObject) {
        try {
            newStockList.clear();
            newStockList.addAll(jsonObject.getNew_stock_list());

            NewCarStockDetailAdapter pocStockAdapter = new NewCarStockDetailAdapter(NewCarStockCountDetailActivity.this, jsonObject.getNew_stock_list());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NewCarStockCountDetailActivity.this, LinearLayoutManager.VERTICAL, false);
            newCarStockFilterDetails_ListView.setLayoutManager(mLayoutManager);
            newCarStockFilterDetails_ListView.setItemAnimator(new DefaultItemAnimator());
            newCarStockFilterDetails_ListView.setAdapter(pocStockAdapter);
        } catch (Exception e) {
        }
    }
}