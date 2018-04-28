package com.example.user.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/28/2018.
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.SearchCustomerBean;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Activity.EditCustomerDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class EditCustomerListAdapter  extends BaseAdapter {
    Context context;
    SearchCustomerBean.Lead_Data bean;
    private List<SearchCustomerBean.Lead_Data> allFollowupDetailsBeanList = new ArrayList<>();
    LayoutInflater inflater;

    public EditCustomerListAdapter(Context context, ArrayList<SearchCustomerBean.Lead_Data> allFollowupDetailsBeanList)
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
        EditCustomerListAdapter.ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.edit_customerlist_layout,null);
            viewHolder = new EditCustomerListAdapter.ViewHolder();

            viewHolder.nameEditCustomerDetailsList_txtView = (TextView) convertView.findViewById(R.id.nameEditCustomerDetailsList_txtView);
            viewHolder.contactNoEditCustomer_txtView = (TextView) convertView.findViewById(R.id.contactNoEditCustomer_txtView);
            viewHolder.emailEditCustomer_txtView = (TextView) convertView.findViewById(R.id.emailEditCustomer_txtView);
            viewHolder.addressEditCustomer_txtView = (TextView) convertView.findViewById(R.id.addressEditCustomer_txtView);
            viewHolder.editBoxSearch_textView = (TextView) convertView.findViewById(R.id.editBoxSearch_textView);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (EditCustomerListAdapter.ViewHolder) convertView.getTag();

        viewHolder.nameEditCustomerDetailsList_txtView.setText(bean.getName());
        viewHolder.contactNoEditCustomer_txtView.setText(bean.getContact_no());
        viewHolder.emailEditCustomer_txtView.setText(bean.getEmail());
        viewHolder.addressEditCustomer_txtView.setText(bean.getAddress());

        viewHolder.editBoxSearch_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditCustomerDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", allFollowupDetailsBeanList.get(position));
               context.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        TextView  nameEditCustomerDetailsList_txtView,contactNoEditCustomer_txtView,emailEditCustomer_txtView,addressEditCustomer_txtView;
        TextView editBoxSearch_textView;



    }

}
