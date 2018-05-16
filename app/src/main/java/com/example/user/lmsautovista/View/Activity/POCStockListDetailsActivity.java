package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.POCarStockBean;
import com.example.user.lmsautovista.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class POCStockListDetailsActivity extends AppCompatActivity {

    POCarStockBean.Poc_stock bean;

    @BindView(R.id.pocStockMileageDetailsString_TextView)
    TextView pocStockMileageDetailsString_TextView;
    @BindView(R.id.pocStockOwnerDetailsString_TextView)
    TextView pocStockOwnerDetailsString_TextView;

    @BindView(R.id.pocStockMfgYearDetailsString_TextView)
    TextView pocStockMfgYearDetailsString_TextView;
    @BindView(R.id.pocStockFuelTypeDetailsString_TextView)
    TextView pocStockFuelTypeDetailsString_TextView;

    @BindView(R.id.pocStockColorDetailsString_TextView)
    TextView pocStockColorDetailsString_TextView;
    @BindView(R.id.pocStockModelNameDetailsString_TextView)
    TextView pocStockModelNameDetailsString_TextView;

    @BindView(R.id.pocStockMakeDetails_TextView)
    TextView pocStockMakeDetails_TextView;
    @BindView(R.id.pocStockOdoMeterDetailsString_TextView)
    TextView pocStockOdoMeterDetailsString_TextView;

    @BindView(R.id.pocStockInsuranceDetailsString_TextView)
    TextView pocStockInsuranceDetailsString_TextView;
    @BindView(R.id.pocStockInsuranceExpiryDetailsString_TextView)
    TextView pocStockInsuranceExpiryDetailsString_TextView;

    @BindView(R.id.pocStockCategoryDetailsString_TextView)
    TextView pocStockCategoryDetailsString_TextView;
    @BindView(R.id.pocStockStockLocationDetailsString_TextView)
    TextView pocStockStockLocationDetailsString_TextView;

    @BindView(R.id.pocStockExptSellingDetailsString_TextView)
    TextView pocStockExptSellingDetailsString_TextView;
    @BindView(R.id.pocStockTotalPendingCostDetailsString_TextView)
    TextView pocStockTotalPendingCostDetailsString_TextView;

    @BindView(R.id.pocStockTotalAgeingDetailsString_TextView)
    TextView pocStockTotalAgeingDetailsString_TextView;
    @BindView(R.id.pocStockTotalLastUpdateDetailsString_TextView)
    TextView pocStockTotalLastUpdateDetailsString_TextView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocstock_list_details);

        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
        bean = getIntent().getParcelableExtra("bean");
        Log.i("Selected Bean:", bean.getMake_name());

        pocStockMakeDetails_TextView.setText(bean.getMake_name());
        pocStockMileageDetailsString_TextView.setText(bean.getMileage());
        pocStockOwnerDetailsString_TextView.setText(bean.getOwner());
        pocStockMfgYearDetailsString_TextView.setText(bean.getMfg_year());
        pocStockFuelTypeDetailsString_TextView.setText(bean.getFuel_type());
        pocStockColorDetailsString_TextView.setText(bean.getColor());
        pocStockModelNameDetailsString_TextView.setText(bean.getSubmodel());
        pocStockOdoMeterDetailsString_TextView.setText(bean.getOdo_meter());
        pocStockInsuranceDetailsString_TextView.setText(bean.getInsurance_type());
        pocStockInsuranceExpiryDetailsString_TextView.setText(bean.getInsurance_expiry_date());
        pocStockCategoryDetailsString_TextView.setText(bean.getCategory());
        pocStockStockLocationDetailsString_TextView.setText(bean.getStock_location());
        pocStockExptSellingDetailsString_TextView.setText(bean.getExpt_selling_price());
        pocStockTotalPendingCostDetailsString_TextView.setText(bean.getTotal_landing_cost());
        pocStockTotalAgeingDetailsString_TextView.setText(bean.getStock_ageing());
        pocStockTotalLastUpdateDetailsString_TextView.setText(bean.getCreated_date());
    }

    @OnClick(R.id.backButton_ImageView)
    public void getBackBtn(){
        super.onBackPressed();
    }
}
