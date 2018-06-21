package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.R;

import java.util.List;

/**
 * Created by User on 6/5/2018.
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

            escalatedL3Lead_tv = (TextView) itemView.findViewById(R.id.escalatedL3Lead_tv);
            escalatedL2Lead_tv = (TextView) itemView.findViewById(R.id.escalatedL2Lead_tv);
            escalatedL1Lead_tv = (TextView) itemView.findViewById(R.id.escalatedL1Lead_tv);
        }
    }
}
