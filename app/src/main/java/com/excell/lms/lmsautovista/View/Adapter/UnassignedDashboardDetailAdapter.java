package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 2/21/2018.
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Activity.CustomerDetailsActivity;

import java.util.List;

public class UnassignedDashboardDetailAdapter extends RecyclerView.Adapter<UnassignedDashboardDetailAdapter.MyViewHolder>{

    private List<CallingTaskNewLeadBean.Lead_Details> dashboardList;
    Context context;

    public UnassignedDashboardDetailAdapter(Context context,List<CallingTaskNewLeadBean.Lead_Details> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.followup_detail_listview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UnassignedDashboardDetailAdapter.MyViewHolder holder,final int position) {
        final CallingTaskNewLeadBean.Lead_Details bean = dashboardList.get(position);

        holder.leadName_TextView.setText(bean.getName().toString());
        holder.leadContactNo_TextView.setText(bean.getContact_no());
        holder.interestInString_TextView.setText(bean.getEnquiry_for());
        holder.leadDateString_TextView.setText(bean.getCreated_date());
        holder.statusString_TextView.setText(bean.getFeedbackStatus());

        if (bean.getLead_source().equals("")){
            holder.interestInString_TextView.setText("Web");
        }else if (bean.getLead_source().equals("Facebook")){
            holder.interestInString_TextView.setText(bean.getEnquiry_for());
        }else {
            holder.interestInString_TextView.setText(bean.getLead_source());
        }

        holder.leadName_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CustomerDetailsActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("heading", "New Leads");
                intent.putExtra("bean", dashboardList.get(position));
                //  intent.putParcelableArrayListExtra("arrayList", );
                context.startActivity(intent);

                /*  Intent intent = new Intent(context, DashboardCountDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("heading", "Unassigned Leads");
                intent.putExtra("bean", dashboardList.get(position));
                //  intent.putParcelableArrayListExtra("arrayList", );
                context.startActivity(intent);
                */

            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView leadName_TextView,leadContactNo_TextView,interestInString_TextView,leadDateString_TextView,statusString_TextView;
        public MyViewHolder(View itemView) {
            super(itemView);

            leadName_TextView = (TextView) itemView.findViewById(R.id.leadName_TextView);
            leadContactNo_TextView = (TextView) itemView.findViewById(R.id.leadContactNo_TextView);
            interestInString_TextView = (TextView) itemView.findViewById(R.id.interestInString_TextView);
            leadDateString_TextView = (TextView) itemView.findViewById(R.id.leadDateString_TextView);
            statusString_TextView = (TextView) itemView.findViewById(R.id.statusString_TextView);
        }
    }
}
