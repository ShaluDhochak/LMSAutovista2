package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.DailyProductivityReportBean;
import com.excell.lms.lmsautovista.R;

import java.util.List;

/**
 * Created by User on 5/30/2018.
 */

public class DailyProductivityReportAdapter  extends RecyclerView.Adapter<DailyProductivityReportAdapter.MyViewHolder>{

    private List<DailyProductivityReportBean.Daily_Productivity_resport> dashboardCount;
    Context context;

    public DailyProductivityReportAdapter(Context context, List<DailyProductivityReportBean.Daily_Productivity_resport> dashboardCount ){
        this.dashboardCount = dashboardCount;
        this.context = context;
    }

    @Override
    public DailyProductivityReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_productivity_report_layout, parent, false);
        return new DailyProductivityReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DailyProductivityReportAdapter.MyViewHolder holder, final int position) {

        final DailyProductivityReportBean.Daily_Productivity_resport bean = dashboardCount.get(position);

        holder.userNameHeading_tv.setText(bean.getFname() + " "+ bean.getLname());
        holder.totalCallingCount_tv.setText(bean.getTotal_called().toString());
        holder.totalConnectedCount_tv.setText(bean.getTotal_connected());
        holder.totalNotConnectedCount_tv.setText(bean.getTotal_not_connected());

      /*  holder.pendingFollowUpLead_tv.setOnClickListener(new View.OnClickListener() {
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
*/
    }

    @Override
    public int getItemCount() {
        return dashboardCount.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView totalNotConnectedCount_tv,totalConnectedCount_tv, totalCallingCount_tv,userNameHeading_tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            userNameHeading_tv = (TextView) itemView.findViewById(R.id.userNameHeading_tv);

            totalNotConnectedCount_tv = (TextView) itemView.findViewById(R.id.totalNotConnectedCount_tv);
            totalConnectedCount_tv = (TextView) itemView.findViewById(R.id.totalConnectedCount_tv);
            totalCallingCount_tv = (TextView) itemView.findViewById(R.id.totalCallingCount_tv);

        }
    }
}
