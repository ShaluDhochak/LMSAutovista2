package com.excell.lms.lmsautovista.View.Adapter;

/*
 Created by Shalu Dhochak on 5/7/2018.
*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.excell.lms.lmsautovista.Model.AssignToUserBean;
import com.excell.lms.lmsautovista.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AssignToCseListAdapter  extends RecyclerView.Adapter<AssignToCseListAdapter.MyViewHolder>{

private List<AssignToUserBean.Assign_Transferred_Lead_to_user> dashboardList = new ArrayList<>();
    Context context;
    boolean[] selectedCheckBox;
    JSONArray selectedCSEJsonArray ;
    LayoutInflater inflater;

public AssignToCseListAdapter(Context context,List<AssignToUserBean.Assign_Transferred_Lead_to_user> dashboardList) {
    this.context = context;
    //  this.dashboardList = dashboardList;
    selectedCheckBox = new boolean[dashboardList.size()];
 //   this.dashboardList.clear();
    this.dashboardList.addAll(dashboardList);

    String[] campaignAssignArray = new String[dashboardList.size()];
    for (int i = 0; i < selectedCheckBox.length; i++) {
        selectedCheckBox[i] = false;
        campaignAssignArray[i] = "0";
    }
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}
@Override
public AssignToCseListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cse_name_listview, parent, false);
        return new AssignToCseListAdapter.MyViewHolder(itemView);
        }

@Override
public void onBindViewHolder(AssignToCseListAdapter.MyViewHolder holder,final int position) {
    final AssignToUserBean.Assign_Transferred_Lead_to_user bean = dashboardList.get(position);

    holder.cseNameListView_CheckBox.setText(bean.getFname().toString() + " " +bean.getLname().toString());

    if(selectedCheckBox[position])
        holder.cseNameListView_CheckBox.setChecked(true);
    else
        holder.cseNameListView_CheckBox.setChecked(false);

    holder.cseNameListView_CheckBox.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            for (int i=0;i<selectedCheckBox.length;i++){
                if(i==position){
                    if(selectedCheckBox[i])
                        selectedCheckBox[i]=false;
                    else
                        selectedCheckBox[i]=true;
                }
            }
            notifyDataSetChanged();
        }
    });

}
    public JSONArray getSelectedCSE()
    {
        selectedCSEJsonArray = new JSONArray();
        for (int i=0;i<selectedCheckBox.length;i++)
        {
            if(selectedCheckBox[i]) {
                selectedCSEJsonArray.put(dashboardList.get(i).getId());
            }
        }
        return selectedCSEJsonArray;
    }

@Override
public int getItemCount() {
        return dashboardList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {

    CheckBox cseNameListView_CheckBox;

    public MyViewHolder(View itemView) {
        super(itemView);

        cseNameListView_CheckBox = (CheckBox) itemView.findViewById(R.id.cseNameListView_CheckBox);

    }
}
}


