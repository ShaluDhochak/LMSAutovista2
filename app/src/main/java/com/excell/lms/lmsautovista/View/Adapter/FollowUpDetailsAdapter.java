package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/22/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.FollowUpDetailsBean;
import com.excell.lms.lmsautovista.R;

import java.util.List;

public class FollowUpDetailsAdapter  extends RecyclerView.Adapter<FollowUpDetailsAdapter.MyViewHolder>{

    private List<FollowUpDetailsBean.FollowUp_details> dashboardList;
    Context context;

    public FollowUpDetailsAdapter(Context context,List<FollowUpDetailsBean.FollowUp_details> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.followup_details_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        final FollowUpDetailsBean.FollowUp_details bean = dashboardList.get(position);
        holder.remarkFollowUpDetailsString_TextView.setText(bean.getComment());
        holder.nextActionFollowUpDetailsString_TextView.setText(bean.getNextAction());
        holder.feedbackStatusFollowUpDetailsString_TextView.setText(bean.getFeedbackStatus());
        holder.nFDFollowUpDetailsString_TextView.setText(bean.getNextfollowupdate());
        holder.callStatusFollowUpDetailsString_TextView.setText(bean.getContactibility());
        holder.callDateFollowUpDetailsString_TextView.setText(bean.getCall_date());
        holder.followUpByFollowUpDetailsString_TextView.setText(bean.getFname() + " "+bean.getLname());
        holder.callTimeFollowUpDetailsString_TextView.setText(bean.getCall_time());
        holder.nFtFollowUpDetailsString_TextView.setText(bean.getNextfollowuptime());
        holder.escalationTypeFollowUpDetails_LinearLayout.setVisibility(View.GONE);
        holder.escalationRemarkFollowUpDetails_LinearLayout.setVisibility(View.GONE);
        holder.escalationTypeFollowUpDetailsString_TextView.setText(bean.getEscalation_type());
        holder.escalationRemarkFollowUpDetailsString_TextView.setText(bean.getEscalation_remark());

    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView remarkFollowUpDetailsString_TextView,nextActionFollowUpDetailsString_TextView,feedbackStatusFollowUpDetailsString_TextView,nFDFollowUpDetailsString_TextView;
        TextView callDateFollowUpDetailsString_TextView,followUpByFollowUpDetailsString_TextView, callStatusFollowUpDetailsString_TextView;
        TextView escalationTypeFollowUpDetailsString_TextView,escalationRemarkFollowUpDetailsString_TextView;
        TextView callTimeFollowUpDetailsString_TextView, nFtFollowUpDetailsString_TextView;

        LinearLayout escalationTypeFollowUpDetails_LinearLayout, escalationRemarkFollowUpDetails_LinearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            remarkFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkFollowUpDetailsString_TextView);
            nextActionFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.nextActionFollowUpDetailsString_TextView);
            feedbackStatusFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.feedbackStatusFollowUpDetailsString_TextView);
            nFDFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.nFDFollowUpDetailsString_TextView);

            callStatusFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.callStatusFollowUpDetailsString_TextView);
            callDateFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.callDateFollowUpDetailsString_TextView);
            followUpByFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.followUpByFollowUpDetailsString_TextView);
            callTimeFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.callTimeFollowUpDetailsString_TextView);
            nFtFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.nFtFollowUpDetailsString_TextView);
            escalationTypeFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.escalationTypeFollowUpDetailsString_TextView);
            escalationRemarkFollowUpDetailsString_TextView = (TextView) itemView.findViewById(R.id.escalationRemarkFollowUpDetailsString_TextView);


            escalationTypeFollowUpDetails_LinearLayout = (LinearLayout) itemView.findViewById(R.id.escalationTypeFollowUpDetails_LinearLayout);
            escalationRemarkFollowUpDetails_LinearLayout = (LinearLayout) itemView.findViewById(R.id.escalationRemarkFollowUpDetails_LinearLayout);
        }
    }
}
