package com.example.user.lmsautovista.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Activity.CallTodayDashboardActivity;
import com.example.user.lmsautovista.View.Activity.NewLeadDashboardActivity;
import com.example.user.lmsautovista.View.Activity.PendingFollowUpDashboardActivity;
import com.example.user.lmsautovista.View.Activity.PendingNewLeadActivity;
import com.example.user.lmsautovista.View.Activity.UnassignedDashboardLeadActivity;

import java.util.List;

/*
  Created by Shalu Dhochak on 2/17/2018.
*/

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder>{

    private List<DashboardCountBean.Dashboard_Count> dashboardCount;
    Context context;

    public DashboardAdapter(Context context, List<DashboardCountBean.Dashboard_Count> dashboardCount ){
        this.dashboardCount = dashboardCount;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {

        final DashboardCountBean.Dashboard_Count bean = dashboardCount.get(position);


        holder.countHeading_tv.setText(bean.getFname() + " "+ bean.getLname());
        holder.newLead_tv.setText(bean.getNew_leads().toString());
        holder.callTodayLead_tv.setText(bean.getCall_today());
        holder.pendingFollowUpLead_tv.setText(bean.getPending_followup());
        holder.pendingNewLead_tv.setText(bean.getPending_new_leads());
        holder.unassignedLead_tv.setText(bean.getUnassigned_leads());

        holder.newLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewLeadDashboardActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", dashboardCount.get(position));
                context.startActivity(intent);
            }
        });

        holder.unassignedLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(context, UnassignedDashboardLeadActivity.class);
                  intent.putExtra("position",position);
                  intent.putExtra("bean", dashboardCount.get(position));
                  context.startActivity(intent);
            }
        });

        holder.callTodayLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CallTodayDashboardActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", dashboardCount.get(position));
                context.startActivity(intent);
            }
        });

        holder.pendingNewLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PendingNewLeadActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", dashboardCount.get(position));
                context.startActivity(intent);
            }
        });

        holder.pendingFollowUpLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PendingFollowUpDashboardActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", dashboardCount.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dashboardCount.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pendingFollowUpLead_tv,unassignedLead_tv, newLead_tv,callTodayLead_tv,pendingNewLead_tv,countHeading_tv ;

        public MyViewHolder(View itemView) {
            super(itemView);

            countHeading_tv = (TextView) itemView.findViewById(R.id.countHeading_tv);

            newLead_tv = (TextView) itemView.findViewById(R.id.newLead_tv);
            pendingFollowUpLead_tv = (TextView) itemView.findViewById(R.id.pendingFollowUpLead_tv);
            unassignedLead_tv = (TextView) itemView.findViewById(R.id.unassignedLead_tv);
            callTodayLead_tv = (TextView) itemView.findViewById(R.id.callTodayLead_tv);
            pendingNewLead_tv = (TextView) itemView.findViewById(R.id.pendingNewLead_tv);
        }
    }
}
