package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.View.Activity.CallTodayDashboardActivity;
import com.excell.lms.lmsautovista.View.Activity.EscalationLevel1Activity;
import com.excell.lms.lmsautovista.View.Activity.EscalationLevel2Activity;
import com.excell.lms.lmsautovista.View.Activity.EscalationLevel3Activity;
import com.excell.lms.lmsautovista.View.Activity.EvaluationDashboardActivity;
import com.excell.lms.lmsautovista.View.Activity.HomeVisitDashboardActivity;
import com.excell.lms.lmsautovista.View.Activity.NewLeadDashboardActivity;
import com.excell.lms.lmsautovista.View.Activity.PendingFollowUpDashboardActivity;
import com.excell.lms.lmsautovista.View.Activity.PendingNewLeadActivity;
import com.excell.lms.lmsautovista.View.Activity.ShowroomVisitDashboardActivity;
import com.excell.lms.lmsautovista.View.Activity.TestDriveDashboardActivity;
import com.excell.lms.lmsautovista.View.Activity.UnassignedDashboardLeadActivity;

import java.util.List;

/**
 * Created by Shalu Dhochak on 5/29/2018.
 */

public class LocationDashboardAdapter  extends RecyclerView.Adapter<LocationDashboardAdapter.MyViewHolder>{

    private List<LocationWiseDashboardCountBean.New_Dashboard> dashboardCount;
    Context context;

    public LocationDashboardAdapter(Context context, List<LocationWiseDashboardCountBean.New_Dashboard> dashboardCount ){
        this.dashboardCount = dashboardCount;
        this.context = context;
    }

    @Override
    public LocationDashboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_dashboard, parent, false);
        return new LocationDashboardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LocationDashboardAdapter.MyViewHolder holder, final int position) {

        final LocationWiseDashboardCountBean.New_Dashboard bean = dashboardCount.get(position);

        holder.countHeading_tv.setText(bean.getFname() + " "+ bean.getLname());
        holder.newLead_tv.setText(bean.getNew_leads().toString());
        holder.callTodayLead_tv.setText(bean.getCall_today());
        holder.pendingFollowUpLead_tv.setText(bean.getPending_followup());
        holder.pendingNewLead_tv.setText(bean.getPending_new_leads());
        holder.unassignedLead_tv.setText(bean.getUnassigned_leads());

        if ((SharedPreferenceManager.getInstance(context).getPreference(Constants.PROCESS_ID, "").equals("8")) ){
            holder.showroomVisitCountLead_tv.setVisibility(View.GONE);
            holder.homeVisitCountLead_tv.setVisibility(View.GONE);
            holder.testDriveCountLead_tv.setVisibility(View.GONE);
            holder.evaluationCountLead_tv.setVisibility(View.GONE);
            holder.separatorBelowevaluationCount_view.setVisibility(View.GONE);
            holder.separatorBelowtestDrive_view.setVisibility(View.GONE);
            holder.separatorBelowhomeVisit_view.setVisibility(View.GONE);
            holder.separatorBelowShowroomVisit_view.setVisibility(View.GONE);
        }

        holder.showroomVisitCountLead_tv.setText(bean.getShowroom_visit_count());
        holder.homeVisitCountLead_tv.setText(bean.getHome_visit_count());
        holder.testDriveCountLead_tv.setText(bean.getTest_drive_count());
        holder.evaluationCountLead_tv.setText(bean.getEvaluation_count());

        holder.escalatedL1Lead_tv.setText(bean.getEscalation_level_1());
        holder.escalatedL2Lead_tv.setText(bean.getEscalation_level_2());
        holder.escalatedL3Lead_tv.setText(bean.getEscalation_level_3());

        holder.newLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewLeadDashboardActivity.class);
                intent.putExtra("position",position);
              //  intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

        holder.unassignedLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UnassignedDashboardLeadActivity.class);
                intent.putExtra("position",position);
           //     intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());

                context.startActivity(intent);
            }
        });

        holder.callTodayLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CallTodayDashboardActivity.class);
                intent.putExtra("position",position);
          //      intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());

                context.startActivity(intent);
            }
        });

        holder.pendingNewLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PendingNewLeadActivity.class);
                intent.putExtra("position",position);
         //       intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());

                context.startActivity(intent);
            }
        });

        holder.pendingFollowUpLead_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PendingFollowUpDashboardActivity.class);
                intent.putExtra("position",position);
       //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

        holder.escalatedL1Lead_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EscalationLevel1Activity.class);
                intent.putExtra("position",position);
                //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);

            }
        });

        holder.escalatedL2Lead_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EscalationLevel2Activity.class);
                intent.putExtra("position",position);
                //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

        holder.escalatedL3Lead_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EscalationLevel3Activity.class);
                intent.putExtra("position",position);
                //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

        holder.showroomVisitCountLead_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowroomVisitDashboardActivity.class);
                intent.putExtra("position",position);
                //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

        holder.homeVisitCountLead_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, HomeVisitDashboardActivity.class);
                intent.putExtra("position",position);
                //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

        holder.testDriveCountLead_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TestDriveDashboardActivity.class);
                intent.putExtra("position",position);
                //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

        holder.evaluationCountLead_tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EvaluationDashboardActivity.class);
                intent.putExtra("position",position);
                //         intent.putExtra("bean", dashboardCount.get(position));
                intent.putExtra("user_id", bean.getId());
                intent.putExtra("role_id", bean.getRole());
                context.startActivity(intent);
            }
        });

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
