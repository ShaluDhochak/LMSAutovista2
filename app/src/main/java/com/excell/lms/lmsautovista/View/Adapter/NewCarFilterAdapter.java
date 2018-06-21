package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.NewCarStock1Bean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Activity.NewCarStockCountDetailActivity;

import java.util.List;

/**
 * Created by User on 5/26/2018.
 */

public class NewCarFilterAdapter  extends RecyclerView.Adapter<NewCarFilterAdapter.MyViewHolder>{

    private List<NewCarStock1Bean.New_stock_Count> dashboardList;

    Context context;

    public NewCarFilterAdapter(Context context,List<NewCarStock1Bean.New_stock_Count> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public NewCarFilterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.poc_stock_count_layout, parent, false);
        return new NewCarFilterAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewCarFilterAdapter.MyViewHolder holder,final int position) {
        final NewCarStock1Bean.New_stock_Count bean = dashboardList.get(position);
        //     final  POCCarStockCountBean.Poc_Stock_Filter filterBean = filterList.get(position);
        holder.countStockCount_tv.setText(bean.getModel_count());
        holder.modelNameStockCount_tv.setText(bean.getSubmodel());
        holder.makeNameStockCount_tv.setText(bean.getModel_name());

        holder.countStockCount_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewCarStockCountDetailActivity.class);
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
        TextView countStockCount_tv,modelNameStockCount_tv,makeNameStockCount_tv;
        public MyViewHolder(View itemView) {
            super(itemView);

            countStockCount_tv = (TextView) itemView.findViewById(R.id.countStockCount_tv);
            modelNameStockCount_tv = (TextView) itemView.findViewById(R.id.modelNameStockCount_tv);
            makeNameStockCount_tv = (TextView) itemView.findViewById(R.id.makeNameStockCount_tv);

        }
    }
}
