package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/30/2018.
*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.DSEDailyReportViewBean;
import com.excell.lms.lmsautovista.R;

import java.util.List;

public class DSEDailyReportViewAdapter extends RecyclerView.Adapter<DSEDailyReportViewAdapter.MyViewHolder> {
    private List<DSEDailyReportViewBean.Daily_Tracker_Show_Data> moviesList;
    Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView evaluationCount_tv,deliveryCount_tv, gatePassCount_txtView,bookingCount_tv,testDriveCount_tv, homeVisitCount_tv;
        TextView walkInCount_tv, enquiryCount_tv, countHeading_tv;

        MyViewHolder(View view) {
            super(view);
            countHeading_tv = (TextView) view.findViewById(R.id.countHeading_tv);

            evaluationCount_tv = (TextView) view.findViewById(R.id.evaluationCount_tv);
            deliveryCount_tv = (TextView) view.findViewById(R.id.deliveryCount_tv);
            gatePassCount_txtView = (TextView) view.findViewById(R.id.gatePassCount_txtView);
            bookingCount_tv = (TextView) view.findViewById(R.id.bookingCount_tv);
            testDriveCount_tv = (TextView) view.findViewById(R.id.testDriveCount_tv);
            homeVisitCount_tv = (TextView) view.findViewById(R.id.homeVisitCount_tv);
            walkInCount_tv = (TextView) view.findViewById(R.id.walkInCount_tv);
            enquiryCount_tv = (TextView) view.findViewById(R.id.enquiryCount_tv);
        }
    }

    public DSEDailyReportViewAdapter(Context context,List<DSEDailyReportViewBean.Daily_Tracker_Show_Data> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public DSEDailyReportViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dse_daily_view_report_list, parent, false);
        return new DSEDailyReportViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DSEDailyReportViewAdapter.MyViewHolder holder, final int position) {
        DSEDailyReportViewBean.Daily_Tracker_Show_Data bean = moviesList.get(position);

        holder.countHeading_tv.setText(bean.getFname() + " " + bean.getLname());

        holder.evaluationCount_tv.setText(bean.getEvaluation_count());
        holder.deliveryCount_tv.setText(bean.getDelivery_count());
        holder.gatePassCount_txtView.setText(bean.getGatepass_count());
        holder.bookingCount_tv.setText(bean.getBooking_count());
        holder.testDriveCount_tv.setText(bean.getTest_drive_count());
        holder.homeVisitCount_tv.setText(bean.getHome_visit_count());
        holder.walkInCount_tv.setText(bean.getWalk_in_count());
        holder.enquiryCount_tv.setText(bean.getEnquiry_count());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}

