package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.AuditorRemarkBean;
import com.excell.lms.lmsautovista.Model.FollowUpDetailsBean;
import com.excell.lms.lmsautovista.R;

import java.util.List;

public class AuditorDetailsAdapter  extends RecyclerView.Adapter<AuditorDetailsAdapter.MyViewHolder>{

    private List<AuditorRemarkBean.Auditor_Remark_Details> dashboardList;
    Context context;

    public AuditorDetailsAdapter(Context context,List<AuditorRemarkBean.Auditor_Remark_Details> dashboardList){
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public AuditorDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.auditor_detail_layout, parent, false);
        return new AuditorDetailsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AuditorDetailsAdapter.MyViewHolder holder, final int position) {
        final AuditorRemarkBean.Auditor_Remark_Details bean = dashboardList.get(position);

        holder.followUpByAuditorDetailsString_TextView.setText(bean.getFname() + " "+ bean.getLname() );
        holder.pendingFollowUpAuditorDetailsString_TextView.setText(bean.getFollowup_pending());
        holder.followupDateAuditorDetailsString_TextView.setText(bean.getCreated_date());
        holder.callReceivedAuditorDetailsString_TextView.setText(bean.getCall_received());
        holder.fakeUpdationAuditorDetailsString_TextView.setText(bean.getFake_updation());
        holder.serviceFeedbackAuditorDetailsString_TextView.setText(bean.getService_feedback());
        holder.remarkAuditorDetailsString_TextView.setText(bean.getRemark());

    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView followUpByAuditorDetailsString_TextView,pendingFollowUpAuditorDetailsString_TextView,followupDateAuditorDetailsString_TextView,callReceivedAuditorDetailsString_TextView;
        TextView fakeUpdationAuditorDetailsString_TextView,serviceFeedbackAuditorDetailsString_TextView, remarkAuditorDetailsString_TextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            followUpByAuditorDetailsString_TextView = (TextView) itemView.findViewById(R.id.followUpByAuditorDetailsString_TextView);
            pendingFollowUpAuditorDetailsString_TextView = (TextView) itemView.findViewById(R.id.pendingFollowUpAuditorDetailsString_TextView);
            followupDateAuditorDetailsString_TextView = (TextView) itemView.findViewById(R.id.followupDateAuditorDetailsString_TextView);
            callReceivedAuditorDetailsString_TextView = (TextView) itemView.findViewById(R.id.callReceivedAuditorDetailsString_TextView);
            fakeUpdationAuditorDetailsString_TextView = (TextView) itemView.findViewById(R.id.fakeUpdationAuditorDetailsString_TextView);
            serviceFeedbackAuditorDetailsString_TextView = (TextView) itemView.findViewById(R.id.serviceFeedbackAuditorDetailsString_TextView);
            remarkAuditorDetailsString_TextView = (TextView) itemView.findViewById(R.id.remarkAuditorDetailsString_TextView);

        }
    }
}
