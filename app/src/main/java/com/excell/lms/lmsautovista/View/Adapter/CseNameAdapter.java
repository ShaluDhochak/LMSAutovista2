package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 5/4/2018.
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.excell.lms.lmsautovista.Model.AssignNewLeadAssignUserBean;
import com.excell.lms.lmsautovista.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CseNameAdapter  extends BaseAdapter {
    Context context;
    AssignNewLeadAssignUserBean.Assign_New_Lead_Assign_User bean;
    boolean[] selectedCheckBox;
    List<AssignNewLeadAssignUserBean.Assign_New_Lead_Assign_User> cseNameBeanList = new ArrayList<>();
    LayoutInflater inflater;
    JSONArray selectedCSEJsonArray ;

    public CseNameAdapter(Context context, ArrayList<AssignNewLeadAssignUserBean.Assign_New_Lead_Assign_User> cseNameBeanList)
    {
        this.context = context;
        selectedCheckBox = new boolean[cseNameBeanList.size()];
        this.cseNameBeanList.clear();
        this.cseNameBeanList.addAll(cseNameBeanList);
        for(int i=0;i<selectedCheckBox.length;i++)
            selectedCheckBox[i] = false;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cseNameBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return cseNameBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bean = cseNameBeanList.get(position);
        final ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.cse_name_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.cseNameListView_CheckBox = (CheckBox) convertView.findViewById(R.id.cseNameListView_CheckBox);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        int loop1;
        for (loop1=0; loop1<cseNameBeanList.size();loop1++){
            viewHolder.cseNameListView_CheckBox.setText(bean.getFname() + " "+ bean.getLname());
        }

        if(selectedCheckBox[position])
            viewHolder.cseNameListView_CheckBox.setChecked(true);
        else
            viewHolder.cseNameListView_CheckBox.setChecked(false);

        viewHolder.cseNameListView_CheckBox.setOnClickListener(new View.OnClickListener() {

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
        return convertView;
    }

    public JSONArray getSelectedCSE()
    {
        selectedCSEJsonArray = new JSONArray();
        for (int i=0;i<selectedCheckBox.length;i++)
        {
            if(selectedCheckBox[i]) {
                selectedCSEJsonArray.put(cseNameBeanList.get(i).getId());
            }
        }
        return selectedCSEJsonArray;
    }

    public class ViewHolder{
        CheckBox cseNameListView_CheckBox;
    }
}

