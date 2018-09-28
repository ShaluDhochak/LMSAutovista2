package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/21/2018.
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.POCStockCountDetailListBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Activity.POCStockListDetailsActivity;

import java.util.List;

public class POCStockAdapter extends RecyclerView.Adapter<POCStockAdapter.MyViewHolder>{

    private List<POCStockCountDetailListBean.Poc_Stock_List> dashboardList;
    Context context;

    public POCStockAdapter(Context context,List<POCStockCountDetailListBean.Poc_Stock_List> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public POCStockAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_car_stock_layout, parent, false);
        return new POCStockAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(POCStockAdapter.MyViewHolder holder,final int position) {
        final POCStockCountDetailListBean.Poc_Stock_List bean = dashboardList.get(position);

        holder.newCarStockSubModel_TextView.setText(bean.getMake_name().toString()+ " " +bean.getSubmodel().toString());
        holder.fuelTypeNewCarStockString_TextView.setText(bean.getFuel_type());
        holder.VehicleStatusNewCarStockString_TextView.setText(bean.getVehicle_status());
        holder.loctaionNewCarStockString_TextView.setText(bean.getStock_location());
        holder.ageingNewCarStockString_TextView.setText(bean.getStock_ageing());

        holder.LocationNewCarStockHeading_TextView.setText("Stock Location : ");
        holder.priceNewCarStockHeading_Textview.setVisibility(View.VISIBLE);
        holder.priceStrinfNewCarStock_TextView.setVisibility(View.VISIBLE);
        holder.priceStrinfNewCarStock_TextView.setText(bean.getExpt_selling_price());

        holder.colorStrinfNewCarStock_TextView.setText(bean.getColor());
        holder.lastUpdateDateNewCarStockString_TextView.setText(bean.getCreated_date());
        holder.imageTextSeparator.setVisibility(View.VISIBLE);

        holder.newCarStockSubModel_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, POCStockListDetailsActivity.class);
                intent.putExtra("bean", dashboardList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newCarStockSubModel_TextView,fuelTypeNewCarStockString_TextView,VehicleStatusNewCarStockString_TextView,loctaionNewCarStockString_TextView;
        TextView ageingNewCarStockString_TextView,colorStrinfNewCarStock_TextView, lastUpdateDateNewCarStockString_TextView;
        View imageTextSeparator;

        TextView LocationNewCarStockHeading_TextView, priceNewCarStockHeading_Textview, priceStrinfNewCarStock_TextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            newCarStockSubModel_TextView = (TextView) itemView.findViewById(R.id.newCarStockSubModel_TextView);
            fuelTypeNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.fuelTypeNewCarStockString_TextView);
            VehicleStatusNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.VehicleStatusNewCarStockString_TextView);
            loctaionNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.loctaionNewCarStockString_TextView);
            ageingNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.ageingNewCarStockString_TextView);

            LocationNewCarStockHeading_TextView = (TextView) itemView.findViewById(R.id.LocationNewCarStockHeading_TextView);
            priceNewCarStockHeading_Textview = (TextView) itemView.findViewById(R.id.priceNewCarStockHeading_Textview);
            priceStrinfNewCarStock_TextView = (TextView) itemView.findViewById(R.id.priceStrinfNewCarStock_TextView);

            imageTextSeparator = (View) itemView.findViewById(R.id.imageTextSeparator);
            colorStrinfNewCarStock_TextView = (TextView) itemView.findViewById(R.id.colorStrinfNewCarStock_TextView);
            lastUpdateDateNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.lastUpdateDateNewCarStockString_TextView);
        }
    }
}


