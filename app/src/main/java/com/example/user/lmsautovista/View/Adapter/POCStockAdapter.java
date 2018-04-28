package com.example.user.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/21/2018.
*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.user.lmsautovista.Model.POCarStockBean;
import com.example.user.lmsautovista.R;
import java.util.List;

public class POCStockAdapter extends RecyclerView.Adapter<POCStockAdapter.MyViewHolder>{

    private List<POCarStockBean.Poc_stock> dashboardList;
    Context context;

    public POCStockAdapter(Context context,List<POCarStockBean.Poc_stock> dashboardList){
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
        final POCarStockBean.Poc_stock bean = dashboardList.get(position);

        holder.newCarStockSubModel_TextView.setText(bean.getSubmodel().toString());
        holder.fuelTypeNewCarStockString_TextView.setText(bean.getFuel_type());
        holder.VehicleStatusNewCarStockString_TextView.setText(bean.getVehicle_status());
        holder.loctaionNewCarStockString_TextView.setText(bean.getStock_location());
        holder.ageingNewCarStockString_TextView.setText(bean.getStock_ageing());

        holder.colorStrinfNewCarStock_TextView.setText(bean.getColor());
        holder.lastUpdateDateNewCarStockString_TextView.setText(bean.getCreated_date());
        holder.imageTextSeparator.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newCarStockSubModel_TextView,fuelTypeNewCarStockString_TextView,VehicleStatusNewCarStockString_TextView,loctaionNewCarStockString_TextView;
        TextView ageingNewCarStockString_TextView,colorStrinfNewCarStock_TextView, lastUpdateDateNewCarStockString_TextView;
        View imageTextSeparator;

        public MyViewHolder(View itemView) {
            super(itemView);

            newCarStockSubModel_TextView = (TextView) itemView.findViewById(R.id.newCarStockSubModel_TextView);
            fuelTypeNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.fuelTypeNewCarStockString_TextView);
            VehicleStatusNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.VehicleStatusNewCarStockString_TextView);
            loctaionNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.loctaionNewCarStockString_TextView);
            ageingNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.ageingNewCarStockString_TextView);

            imageTextSeparator = (View) itemView.findViewById(R.id.imageTextSeparator);
            colorStrinfNewCarStock_TextView = (TextView) itemView.findViewById(R.id.colorStrinfNewCarStock_TextView);
            lastUpdateDateNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.lastUpdateDateNewCarStockString_TextView);
        }
    }
}


