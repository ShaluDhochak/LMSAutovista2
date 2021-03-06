package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.NewCarStockCountDetailsBean;
import com.excell.lms.lmsautovista.R;

import java.util.List;

/*
 * Created by User on 5/26/2018.
 */

public class NewCarStockDetailAdapter  extends RecyclerView.Adapter<NewCarStockDetailAdapter.MyViewHolder>{

    private List<NewCarStockCountDetailsBean.New_Stock_List> dashboardList;
    Context context;

    public NewCarStockDetailAdapter(Context context,List<NewCarStockCountDetailsBean.New_Stock_List> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public NewCarStockDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_car_stock_layout, parent, false);
        return new NewCarStockDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewCarStockDetailAdapter.MyViewHolder holder,final int position) {
        final NewCarStockCountDetailsBean.New_Stock_List bean = dashboardList.get(position);

        holder.newCarStockSubModel_TextView.setText(bean.getSubmodel().toString());
        holder.fuelTypeNewCarStockString_TextView.setText(bean.getFuel_type());
        holder.VehicleStatusNewCarStockString_TextView.setText(bean.getVehicle_status());
        holder.loctaionNewCarStockString_TextView.setText(bean.getAssigned_location());
        holder.ageingNewCarStockString_TextView.setText(bean.getAgeing());
        holder.LocationNewCarStockHeading_TextView.setText("Location : ");

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
        TextView LocationNewCarStockHeading_TextView;
        View imageTextSeparator;

        public MyViewHolder(View itemView) {
            super(itemView);

            newCarStockSubModel_TextView = (TextView) itemView.findViewById(R.id.newCarStockSubModel_TextView);
            fuelTypeNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.fuelTypeNewCarStockString_TextView);
            VehicleStatusNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.VehicleStatusNewCarStockString_TextView);
            loctaionNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.loctaionNewCarStockString_TextView);
            ageingNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.ageingNewCarStockString_TextView);

            LocationNewCarStockHeading_TextView = (TextView) itemView.findViewById(R.id.LocationNewCarStockHeading_TextView);
            imageTextSeparator = (View) itemView.findViewById(R.id.imageTextSeparator);
            colorStrinfNewCarStock_TextView = (TextView) itemView.findViewById(R.id.colorStrinfNewCarStock_TextView);
            lastUpdateDateNewCarStockString_TextView = (TextView) itemView.findViewById(R.id.lastUpdateDateNewCarStockString_TextView);
        }
    }
}


