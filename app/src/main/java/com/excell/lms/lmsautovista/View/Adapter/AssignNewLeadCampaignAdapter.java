package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.AssignNewLeadCampaignBean;
import com.excell.lms.lmsautovista.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 Created by Shalu Dhochak on 5/4/2018.
*/

public class AssignNewLeadCampaignAdapter extends BaseAdapter {
    Context context;
    private List<AssignNewLeadCampaignBean.Assign_New_Leads_Source> allCampaignBeanList1 = new ArrayList<>();
    LayoutInflater inflater;
    private boolean[] selectedCheckBox;

    public AssignNewLeadCampaignAdapter(Context context, ArrayList<AssignNewLeadCampaignBean.Assign_New_Leads_Source> allCampaignBeanList) {
        this.context = context;
        this.allCampaignBeanList1.clear();
        this.allCampaignBeanList1.addAll(allCampaignBeanList);
        selectedCheckBox = new boolean[allCampaignBeanList.size()];
        String[] campaignAssignArray = new String[allCampaignBeanList.size()];
        for(int i=0;i<selectedCheckBox.length;i++) {
            selectedCheckBox[i] = false;
            campaignAssignArray[i] = "0";
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return allCampaignBeanList1.size();
    }

    @Override
    public Object getItem(int position) {
        return allCampaignBeanList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AssignNewLeadCampaignBean.Assign_New_Leads_Source bean = allCampaignBeanList1.get(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.addcampaignlist_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.camapignTotalCountAssignLead_TextView = (TextView) convertView.findViewById(R.id.camapignTotalCountAssignLead_TextView);
            viewHolder.addCampaignListAssignLead_RadioButton = (RadioButton) convertView.findViewById(R.id.addCampaignListAssignLead_RadioButton);
            viewHolder.campaignAssignCountAssignLead_EditText = (EditText) convertView.findViewById(R.id.campaignAssignCountAssign_EditText);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.camapignTotalCountAssignLead_TextView.setText(bean.getWcount());

        if(bean.getLead_source().equals("")){
            viewHolder.addCampaignListAssignLead_RadioButton.setText("Web");
        }else{
            viewHolder.addCampaignListAssignLead_RadioButton.setText(bean.getLead_source());
        }

        /*
        else if (bean.getLead_source().equals("Facebook")) {
            viewHolder.addCampaignListAssignLead_RadioButton.setText(bean.getEnquiry_for());
        }
         */

        if(selectedCheckBox[position]) {
            viewHolder.addCampaignListAssignLead_RadioButton.setChecked(true);
        }
        else
            viewHolder.addCampaignListAssignLead_RadioButton.setChecked(false);

        viewHolder.addCampaignListAssignLead_RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<selectedCheckBox.length;i++){
                    selectedCheckBox[i] = i == position;
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public HashMap<String,String> getSelectedCampaign()
    {
        HashMap<String,String> selectedCampaign = new HashMap<>();
        for (int i=0;i<selectedCheckBox.length;i++) {
            if(selectedCheckBox[i])
            {

                if(allCampaignBeanList1.get(i).getLead_source().equals("")){
                    selectedCampaign.put("campaignName","Web");
                }else{
                    selectedCampaign.put("campaignName", allCampaignBeanList1.get(i).getLead_source());
                }
                /*else if (allCampaignBeanList1.get(i).getLead_source().equals("Facebook")) {
                    selectedCampaign.put("campaignName", allCampaignBeanList1.get(i).getEnquiry_for());
                }
                */
                selectedCampaign.put("campaignCount",allCampaignBeanList1.get(i).getWcount());
                break;
            }
        }
        return selectedCampaign;
    }

    public class ViewHolder {
        TextView camapignTotalCountAssignLead_TextView;
        RadioButton addCampaignListAssignLead_RadioButton;
        EditText campaignAssignCountAssignLead_EditText;

    }
}


