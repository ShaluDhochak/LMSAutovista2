package com.excell.lms.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.POCCarStockCountBean;
import com.excell.lms.lmsautovista.Model.POCStockCountDetailListBean;
import com.excell.lms.lmsautovista.Presenter.PocStockCountPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.POCStockAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class POCCarStockCountDetailActivity extends AppCompatActivity implements IView.PocCarStockCountDetailView{

    @BindView(R.id.newCarStockFilterDetails_ListView)
    RecyclerView newCarStockFilterDetails_ListView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    PocStockCountPresenter pocStockPresenter;
    ArrayList<POCStockCountDetailListBean.Poc_Stock_List> newStockList = new ArrayList<POCStockCountDetailListBean.Poc_Stock_List>();

    POCCarStockCountBean.Poc_Stock_Count bean;

    String make,model, ageing, owner, mfg_year, stock_location, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poccar_stock_count_detail);

        ButterKnife.bind(this);
        pocStockPresenter = new PocStockCountPresenter(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        lmsheading_TextView.setText("POC Stock Details");
        if (NetworkUtilities.isInternet(POCCarStockCountDetailActivity.this)) {

            bean = getIntent().getParcelableExtra("bean");
            Log.i("Selected Bean:", bean.getMake_name());
            model = bean.getSubmodel().toString();

            if(model.equals("")){
                pocStockPresenter.getPocCarStockCount1(POCCarStockCountDetailActivity.this);
            }else{
                pocStockPresenter.getPocCarStockMakeCount1(model, POCCarStockCountDetailActivity.this);
            }
         } else {
            Toast.makeText(POCCarStockCountDetailActivity.this, "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.backButton_ImageView)
    public void onbackpressReg(){
        onBackPressed();
    }

    @Override
    public void showPOCCarStockCountList(POCStockCountDetailListBean jsonObject) {
        try{
            newStockList.clear();
            newStockList.addAll(jsonObject.getPoc_stock_list());

            POCStockAdapter pocStockAdapter = new POCStockAdapter(POCCarStockCountDetailActivity.this,jsonObject.getPoc_stock_list());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(POCCarStockCountDetailActivity.this, LinearLayoutManager.VERTICAL, false);
            newCarStockFilterDetails_ListView.setLayoutManager(mLayoutManager);
            newCarStockFilterDetails_ListView.setItemAnimator(new DefaultItemAnimator());
            newCarStockFilterDetails_ListView.setAdapter(pocStockAdapter);
        }catch(Exception e){
        }
    }
}
