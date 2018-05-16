package com.example.user.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 5/12/2018.
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.CheckFlowTransferLeadDetailBean;
import com.example.user.lmsautovista.R;

import java.util.ArrayList;
import java.util.List;

public class CheckFlowLeadDetailsAdapter  extends BaseAdapter {
    Context context;
    CheckFlowTransferLeadDetailBean.Lead_Data bean;
    private List<CheckFlowTransferLeadDetailBean.Lead_Data> allFollowupDetailsBeanList = new ArrayList<>();
    LayoutInflater inflater;

    public CheckFlowLeadDetailsAdapter(Context context, ArrayList<CheckFlowTransferLeadDetailBean.Lead_Data> allFollowupDetailsBeanList)
    {
        this.context = context;
        this.allFollowupDetailsBeanList.clear();
        this.allFollowupDetailsBeanList.addAll(allFollowupDetailsBeanList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return allFollowupDetailsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return allFollowupDetailsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bean = allFollowupDetailsBeanList.get(position);
        CheckFlowLeadDetailsAdapter.ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.content_check_flow_search,null);
            viewHolder = new CheckFlowLeadDetailsAdapter.ViewHolder();

            viewHolder.assignToSearchString_textView = (TextView) convertView.findViewById(R.id.assignToSearchString_textView);
            viewHolder.assignBySearchString_textView = (TextView) convertView.findViewById(R.id.assignBySearchString_textView);
            viewHolder.transferDateSearchString_textView = (TextView) convertView.findViewById(R.id.transferDateSearchString_textView);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.assignToSearchString_textView.setText(bean.getFname() + " " + bean.getFname());
        viewHolder.assignBySearchString_textView.setText(bean.getU1name() + " " + bean.getU1lname());
        viewHolder.transferDateSearchString_textView.setText(bean.getCreated_date());

        return convertView;
    }

    public class ViewHolder{
        TextView assignToSearchString_textView, assignBySearchString_textView, transferDateSearchString_textView;
    }
}
