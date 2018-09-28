package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 5/7/2018.
*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.AssignTransferredCampignListBean;
import com.excell.lms.lmsautovista.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampaignAssignTransferAdapter  extends RecyclerView.Adapter<CampaignAssignTransferAdapter.MyViewHolder>{

    private List<AssignTransferredCampignListBean.Assign_Transferred_Leads_source> dashboardList = new ArrayList<>();
    Context context;
    boolean[] selectedCheckBox;
    JSONArray selectedCSEJsonArray ;
    LayoutInflater inflater;

    public CampaignAssignTransferAdapter(Context context,List<AssignTransferredCampignListBean.Assign_Transferred_Leads_source> dashboardList) {
        this.context = context;
        selectedCheckBox = new boolean[dashboardList.size()];
        this.dashboardList.clear();
        this.dashboardList.addAll(dashboardList);

        String[] campaignAssignArray = new String[dashboardList.size()];
        for (int i = 0; i < selectedCheckBox.length; i++) {
            selectedCheckBox[i] = false;
            campaignAssignArray[i] = "0";
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public CampaignAssignTransferAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.addcampaignlist_listview, parent, false);
        return new CampaignAssignTransferAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CampaignAssignTransferAdapter.MyViewHolder holder,final int position) {
        final AssignTransferredCampignListBean.Assign_Transferred_Leads_source bean = dashboardList.get(position);

        holder.camapignTotalCountAssignLead_TextView.setText(bean.getWcount());


        if(bean.getLead_source().equals("")){
            holder.addCampaignListAssignLead_RadioButton.setText("Web");
        }else{
            holder.addCampaignListAssignLead_RadioButton.setText(bean.getLead_source());
        }
        if(selectedCheckBox[position]) {
            holder.addCampaignListAssignLead_RadioButton.setChecked(true);
        }
        else
            holder.addCampaignListAssignLead_RadioButton.setChecked(false);

        holder.addCampaignListAssignLead_RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<selectedCheckBox.length;i++){
                    selectedCheckBox[i] = i == position;
                }
                notifyDataSetChanged();
            }
        });

    }
    public HashMap<String,String> getSelectedCampaign()
    {
        HashMap<String,String> selectedCampaign = new HashMap<>();
        for (int i=0;i<selectedCheckBox.length;i++) {
            if(selectedCheckBox[i])
            {

                if(dashboardList.get(i).getLead_source().equals("")){
                    selectedCampaign.put("campaignName","Web");
                }else{
                    selectedCampaign.put("campaignName", dashboardList.get(i).getLead_source());
                }
                /*else if (allCampaignBeanList1.get(i).getLead_source().equals("Facebook")) {
                    selectedCampaign.put("campaignName", allCampaignBeanList1.get(i).getEnquiry_for());
                }
                */
                selectedCampaign.put("campaignCount",dashboardList.get(i).getWcount());
                break;
            }
        }
        return selectedCampaign;
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView camapignTotalCountAssignLead_TextView;
        RadioButton addCampaignListAssignLead_RadioButton;


        public MyViewHolder(View itemView) {
            super(itemView);
            camapignTotalCountAssignLead_TextView = (TextView) itemView.findViewById(R.id.camapignTotalCountAssignLead_TextView);
            addCampaignListAssignLead_RadioButton = (RadioButton) itemView.findViewById(R.id.addCampaignListAssignLead_RadioButton);

        }
    }
}


