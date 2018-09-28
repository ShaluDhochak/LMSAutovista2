package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.SearchTrackerListBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Activity.TrackerDetailActivity;

import java.util.List;

/**
 * Created by Shalu Dhochak on 3/7/2018.
 */

public class TrackerSearchListAdapter extends RecyclerView.Adapter<TrackerSearchListAdapter.MyViewHolder>{

    private List<SearchTrackerListBean.User_Details> dashboardList;
    Context context;

    public TrackerSearchListAdapter(Context context,List<SearchTrackerListBean.User_Details> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public TrackerSearchListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.followup_detail_listview, parent, false);
        return new TrackerSearchListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrackerSearchListAdapter.MyViewHolder holder,final int position) {
        final SearchTrackerListBean.User_Details bean = dashboardList.get(position);
        holder.leadName_TextView.setText(bean.getName().toString());
        holder.leadContactNo_TextView.setText(bean.getContact_no());
        holder.interestInString_TextView.setText(bean.getEnquiry_for());
        holder.leadDateString_TextView.setText(bean.getLead_date());
        if (bean.getFeedbackStatus().equals("")) {
            holder.statusString_TextView.setText("Not Mentioned");
        }else{
            holder.statusString_TextView.setText(bean.getFeedbackStatus());
        }
        if (bean.getLead_source().equals("")){
            holder.interestInString_TextView.setText("Web");
        }else if (bean.getLead_source().equals("Facebook")){
            holder.interestInString_TextView.setText(bean.getEnquiry_for());
        }else {
            holder.interestInString_TextView.setText(bean.getLead_source());
        }
        holder.customerDetails_ImageView.setVisibility(View.GONE);
        holder.addFollowUp_ImageView.setVisibility(View.GONE);
        holder.imageTextSeparator.setVisibility(View.GONE);

        holder.leadName_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrackerDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("heading", "New Leads");
                intent.putExtra("bean", dashboardList.get(position));
                //  intent.putParcelableArrayListExtra("arrayList", );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView leadName_TextView,leadContactNo_TextView,interestInString_TextView,leadDateString_TextView,statusString_TextView;
        View imageTextSeparator;
        ImageView customerDetails_ImageView,addFollowUp_ImageView;

        public MyViewHolder(View itemView) {
                super(itemView);

            leadName_TextView = (TextView) itemView.findViewById(R.id.leadName_TextView);
            leadContactNo_TextView = (TextView) itemView.findViewById(R.id.leadContactNo_TextView);
            interestInString_TextView = (TextView) itemView.findViewById(R.id.interestInString_TextView);
            leadDateString_TextView = (TextView) itemView.findViewById(R.id.leadDateString_TextView);
            statusString_TextView = (TextView) itemView.findViewById(R.id.statusString_TextView);

            imageTextSeparator = (View) itemView.findViewById(R.id.imageTextSeparator);
            customerDetails_ImageView = (ImageView) itemView.findViewById(R.id.customerDetails_ImageView);
            addFollowUp_ImageView = (ImageView) itemView.findViewById(R.id.addFollowUp_ImageView);
        }
    }
}
