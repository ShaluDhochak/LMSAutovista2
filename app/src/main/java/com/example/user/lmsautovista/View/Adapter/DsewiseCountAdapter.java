package com.example.user.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 5/4/2018.
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.DSEReportBean;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Activity.DseWiseReportDetailActivity;

import java.util.ArrayList;

public class DsewiseCountAdapter   extends BaseAdapter {

    Context context;
    DSEReportBean.Dsewise_Count bean;
    ArrayList<DSEReportBean.Dsewise_Count> allLeadsBeanList = new ArrayList<>();
    LayoutInflater inflater;

    public DsewiseCountAdapter(Context context, ArrayList<DSEReportBean.Dsewise_Count> allLeadsBeanList)
    {
        this.context = context;
        this.allLeadsBeanList.clear();
        this.allLeadsBeanList.addAll(allLeadsBeanList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getItem(int position) {
        return allLeadsBeanList.get(position);
    }

    @Override
    public int getCount() {
        return allLeadsBeanList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bean = allLeadsBeanList.get(position);
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.dse_report_list,null);
            viewHolder = new ViewHolder();
            viewHolder.dseNameListReport_TextView = (TextView) convertView.findViewById(R.id.dseNameListReport_TextView);
            viewHolder.dseLnameListReport_TextView = (TextView) convertView.findViewById(R.id.dseLnameListReport_TextView);
            viewHolder.viewDetailsDseList_TextView = (TextView) convertView.findViewById(R.id.viewDetailsDseList_TextView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.dseNameListReport_TextView.setText(bean.getFname());
        viewHolder.dseLnameListReport_TextView.setText(bean.getLname());

        viewHolder.viewDetailsDseList_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DseWiseReportDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", allLeadsBeanList.get(position));
                intent.putParcelableArrayListExtra("arrayList",allLeadsBeanList);
                context.startActivity(intent);

            }
        });


        return convertView;
    }

    public class ViewHolder {
        TextView dseNameListReport_TextView, dseLnameListReport_TextView, viewDetailsDseList_TextView;
    }
}


