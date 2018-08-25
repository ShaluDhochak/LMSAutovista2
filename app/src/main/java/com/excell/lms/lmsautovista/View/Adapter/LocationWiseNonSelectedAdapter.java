package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;

import java.util.List;

/*
  Created by Shalu Dhochak on 6/5/2018.
 */

public class LocationWiseNonSelectedAdapter  extends RecyclerView.Adapter<LocationWiseNonSelectedAdapter.MyViewHolder>{

    private List<LocationWiseDashboardCountBean.New_Dashboard> dashboardCount;
    Context context;

    public LocationWiseNonSelectedAdapter(Context context, List<LocationWiseDashboardCountBean.New_Dashboard> dashboardCount ){
        this.dashboardCount = dashboardCount;
        this.context = context;
    }

    @Override
    public LocationWiseNonSelectedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_dashboard, parent, false);
        return new LocationWiseNonSelectedAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LocationWiseNonSelectedAdapter.MyViewHolder holder, final int position) {
        final LocationWiseDashboardCountBean.New_Dashboard bean = dashboardCount.get(position);

        holder.countHeading_tv.setText("Count");
        holder.newLead_tv.setText(bean.getNew_leads().toString());
        holder.callTodayLead_tv.setText(bean.getCall_today());
        holder.pendingFollowUpLead_tv.setText(bean.getPending_followup());
        holder.pendingNewLead_tv.setText(bean.getPending_new_leads());
        holder.unassignedLead_tv.setText(bean.getUnassigned_leads());
        try {
            if ((SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, "").equals("8"))) {
                holder.showroomVisitCountLead_tv.setVisibility(View.GONE);
                holder.homeVisitCountLead_tv.setVisibility(View.GONE);
                holder.testDriveCountLead_tv.setVisibility(View.GONE);
                holder.evaluationCountLead_tv.setVisibility(View.GONE);
                holder.separatorBelowevaluationCount_view.setVisibility(View.GONE);
                holder.separatorBelowtestDrive_view.setVisibility(View.GONE);
                holder.separatorBelowhomeVisit_view.setVisibility(View.GONE);
                holder.separatorBelowShowroomVisit_view.setVisibility(View.GONE);
            }
        }catch(Exception e){
            Toast.makeText(context, "No record found", Toast.LENGTH_SHORT).show();
        }
        holder.showroomVisitCountLead_tv.setText(bean.getShowroom_visit_count());
        holder.homeVisitCountLead_tv.setText(bean.getHome_visit_count());
        holder.testDriveCountLead_tv.setText(bean.getTest_drive_count());
        holder.evaluationCountLead_tv.setText(bean.getEvaluation_count());
        holder.escalatedL1Lead_tv.setText(bean.getEscalation_level_1());
        holder.escalatedL2Lead_tv.setText(bean.getEscalation_level_2());
        holder.escalatedL3Lead_tv.setText(bean.getEscalation_level_3());
    }

    @Override
    public int getItemCount() {
        return dashboardCount.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pendingFollowUpLead_tv,unassignedLead_tv, newLead_tv,callTodayLead_tv,pendingNewLead_tv,countHeading_tv, escalatedL2Lead_tv, escalatedL1Lead_tv ;
        TextView escalatedL3Lead_tv, showroomVisitCountLead_tv,homeVisitCountLead_tv,testDriveCountLead_tv,evaluationCountLead_tv;
        View separatorBelowevaluationCount_view,separatorBelowtestDrive_view,separatorBelowhomeVisit_view,separatorBelowShowroomVisit_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            countHeading_tv = (TextView) itemView.findViewById(R.id.countHeading_tv);

            newLead_tv = (TextView) itemView.findViewById(R.id.newLead_tv);
            pendingFollowUpLead_tv = (TextView) itemView.findViewById(R.id.pendingFollowUpLead_tv);
            unassignedLead_tv = (TextView) itemView.findViewById(R.id.unassignedLead_tv);
            callTodayLead_tv = (TextView) itemView.findViewById(R.id.callTodayLead_tv);
            pendingNewLead_tv = (TextView) itemView.findViewById(R.id.pendingNewLead_tv);

            showroomVisitCountLead_tv = (TextView) itemView.findViewById(R.id.showroomVisitCountLead_tv);
            homeVisitCountLead_tv = (TextView) itemView.findViewById(R.id.homeVisitCountLead_tv);
            testDriveCountLead_tv = (TextView) itemView.findViewById(R.id.testDriveCountLead_tv);
            evaluationCountLead_tv = (TextView) itemView.findViewById(R.id.evaluationCountLead_tv);

            separatorBelowevaluationCount_view = (View) itemView.findViewById(R.id.separatorBelowevaluationCount_view);
            separatorBelowtestDrive_view = (View) itemView.findViewById(R.id.separatorBelowtestDrive_view);
            separatorBelowhomeVisit_view = (View) itemView.findViewById(R.id.separatorBelowhomeVisit_view);
            separatorBelowShowroomVisit_view = (View) itemView.findViewById(R.id.separatorBelowShowroomVisit_view);

            escalatedL3Lead_tv = (TextView) itemView.findViewById(R.id.escalatedL3Lead_tv);
            escalatedL2Lead_tv = (TextView) itemView.findViewById(R.id.escalatedL2Lead_tv);
            escalatedL1Lead_tv = (TextView) itemView.findViewById(R.id.escalatedL1Lead_tv);
        }
    }
}
