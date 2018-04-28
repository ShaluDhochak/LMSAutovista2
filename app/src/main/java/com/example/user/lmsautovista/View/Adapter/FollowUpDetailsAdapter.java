package com.example.user.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/22/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.FollowUpDetailsBean;
import com.example.user.lmsautovista.R;

import java.util.List;

public class FollowUpDetailsAdapter  extends RecyclerView.Adapter<FollowUpDetailsAdapter.MyViewHolder>{

    private List<FollowUpDetailsBean.FollowUp_details> dashboardList;
    Context context;

    public FollowUpDetailsAdapter(Context context,List<FollowUpDetailsBean.FollowUp_details> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public FollowUpDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.followup_details_layout, parent, false);
        return new FollowUpDetailsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FollowUpDetailsAdapter.MyViewHolder holder,final int position) {
        final FollowUpDetailsBean.FollowUp_details bean = dashboardList.get(position);

        holder.remarkFollowUpDetailsString_TextView.setText(bean.getComment());
        holder.nextActionFollowUpDetailsString_TextView.setText(bean.getNextAction());
        holder.feedbackStatusFollowUpDetailsString_TextView.setText(bean.getFeedbackStatus());
        holder.nFDFollowUpDetailsString_TextView.setText(bean.getNextfollowupdate());

        holder.callDateFollowUpDetailsString_TextView.setText(bean.getCall_date());
        holder.followUpByFollowUpDetailsString_TextView.setText(bean.getFname() + " "+bean.getLname());

    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView remarkFollowUpDetailsString_TextView,nextActionFollowUpDetailsString_TextView,feedbackStatusFollowUpDetailsString_TextView,nFDFollowUpDetailsString_TextView;
        TextView callDateFollowUpDetailsString_TextView,followUpByFollowUpDetailsString_TextView;


        public MyViewHolder(View itemView) {
            super(itemView);

            remarkFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkFollowUpDetailsString_TextView);
            nextActionFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkFollowUpDetailsString_TextView);
            feedbackStatusFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkFollowUpDetailsString_TextView);
            nFDFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkFollowUpDetailsString_TextView);

            callDateFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkFollowUpDetailsString_TextView);
            followUpByFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkFollowUpDetailsString_TextView);


        }
    }
}
