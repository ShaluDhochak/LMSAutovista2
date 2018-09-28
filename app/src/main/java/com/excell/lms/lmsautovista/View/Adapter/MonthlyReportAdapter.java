package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 5/16/2018.
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.SearchTrackerListBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Activity.DailyReportDetailActivity;

import java.util.ArrayList;

public class MonthlyReportAdapter extends BaseAdapter {

    Context context;
    SearchTrackerListBean.User_Details bean;
    private ArrayList<SearchTrackerListBean.User_Details> dailyLeadBeanList = new ArrayList<>();
    LayoutInflater inflater;

    public MonthlyReportAdapter(Context context, ArrayList<SearchTrackerListBean.User_Details> dailyLeadBeanList)
    {
        this.context = context;
        this.dailyLeadBeanList.clear();
        this.dailyLeadBeanList.addAll(dailyLeadBeanList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getItem(int position) {
        return dailyLeadBeanList.get(position);
    }

    @Override
    public int getCount() {
        return dailyLeadBeanList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bean = dailyLeadBeanList.get(position);
        MonthlyReportAdapter.ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.followupdetails_listview,null);
            viewHolder = new MonthlyReportAdapter.ViewHolder();
            viewHolder.followUpName_TextView = (TextView) convertView.findViewById(R.id.followUpName_TextView);
            viewHolder.followUpContactNo_TextView = (TextView) convertView.findViewById(R.id.followUpContactNo_TextView);
            viewHolder.bookingWithInDays_TextView = (TextView) convertView.findViewById(R.id.bookingWithInDays_TextView);
            viewHolder.followUpInterestInString_TextView = (TextView) convertView.findViewById(R.id.followUpInterestInString_TextView);
            viewHolder.followUpLeadDateString_TextView = (TextView) convertView.findViewById(R.id.followUpLeadDateString_TextView);
            viewHolder.followUpStatusString_TextView = (TextView) convertView.findViewById(R.id.followUpStatusString_TextView);
            viewHolder.addFollowUp_ImageView = (ImageView) convertView.findViewById(R.id.addFollowUp_ImageView);
            viewHolder.customerDetails_ImageView = (ImageView) convertView.findViewById(R.id.customerDetails_ImageView);

            viewHolder.imageTextSeparator = (View) convertView.findViewById(R.id.imageTextSeparator);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (MonthlyReportAdapter.ViewHolder) convertView.getTag();

        viewHolder.followUpName_TextView.setText(bean.getName());
        viewHolder.followUpContactNo_TextView.setText(bean.getContact_no());
        viewHolder.followUpLeadDateString_TextView.setText(bean.getLead_date());
        viewHolder.followUpStatusString_TextView.setText(bean.getFeedbackStatus());
        viewHolder.addFollowUp_ImageView.setVisibility(View.GONE);
        viewHolder.customerDetails_ImageView.setVisibility(View.GONE);
        viewHolder.imageTextSeparator.setVisibility(View.GONE);

        //code for showing booking days
        if (viewHolder.bookingWithInDays_TextView.getText().toString().trim().length()>0){
            viewHolder.bookingWithInDays_TextView.setVisibility(View.VISIBLE);
        }else
        {
            viewHolder.bookingWithInDays_TextView.setVisibility(View.GONE);
        }

        //condition for showing booking days
        if ((bean.getDays60_booking().equals("90")) || (bean.getDays60_booking().equals(">60"))){
            viewHolder.bookingWithInDays_TextView.setVisibility(View.VISIBLE);
            viewHolder.bookingWithInDays_TextView.setText(bean.getDays60_booking() + " Days");
            viewHolder.bookingWithInDays_TextView.setBackgroundColor(context.getResources().getColor(R.color.green));
            viewHolder.bookingWithInDays_TextView.setTextColor(context.getResources().getColor(android.R.color.white));
        }else if (bean.getDays60_booking().equals("30")){
            viewHolder.bookingWithInDays_TextView.setVisibility(View.VISIBLE);
            viewHolder.bookingWithInDays_TextView.setText(bean.getDays60_booking() +  " Days");
            viewHolder.bookingWithInDays_TextView.setBackgroundColor(context.getResources().getColor(R.color.red));
            viewHolder.bookingWithInDays_TextView.setTextColor(context.getResources().getColor(android.R.color.white));
        }else if (bean.getDays60_booking().equals("60")){
            viewHolder.bookingWithInDays_TextView.setVisibility(View.VISIBLE);
            viewHolder.bookingWithInDays_TextView.setText(bean.getDays60_booking() + " Days");
            viewHolder.bookingWithInDays_TextView.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            viewHolder.bookingWithInDays_TextView.setTextColor(context.getResources().getColor(android.R.color.black));
        }if( (bean.getDays60_booking().equals("Just Researching")) ||(bean.getDays60_booking().equals("Undecided"))){
            viewHolder.bookingWithInDays_TextView.setVisibility(View.VISIBLE);
            viewHolder.bookingWithInDays_TextView.setText(bean.getDays60_booking());
            viewHolder.bookingWithInDays_TextView.setBackgroundColor(context.getResources().getColor(R.color.green));
            viewHolder.bookingWithInDays_TextView.setTextColor(context.getResources().getColor(android.R.color.white));
        }  else if (bean.getDays60_booking().equals("")){
            viewHolder.bookingWithInDays_TextView.setVisibility(View.GONE);
        }

        //code for showing interested days
        if (bean.getLead_source().equals("")){
            viewHolder.followUpInterestInString_TextView.setText("Web");
        }else if (bean.getLead_source().equals("Facebook")){
            viewHolder.followUpInterestInString_TextView.setText(bean.getEnquiry_for());
        }else{
            viewHolder.followUpInterestInString_TextView.setText(bean.getLead_source());
        }

        viewHolder.followUpName_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DailyReportDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", dailyLeadBeanList.get(position));
                intent.putExtra("heading", "Monthly Report Details");
                intent.putParcelableArrayListExtra("arrayList",dailyLeadBeanList);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ViewHolder {
        TextView followUpName_TextView, followUpContactNo_TextView, bookingWithInDays_TextView, followUpInterestInString_TextView, followUpLeadDateString_TextView, followUpStatusString_TextView;
        ImageView addFollowUp_ImageView,customerDetails_ImageView;
        View imageTextSeparator;
    }
}
