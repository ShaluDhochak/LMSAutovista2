package com.excell.lms.lmsautovista.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.ViewMessageListBean;
import com.excell.lms.lmsautovista.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shalu Dhochak on 4/30/2018.
 */

public class NewMessageViewAdapter  extends BaseAdapter {

    Context context;
    List<ViewMessageListBean.Message_Home> allNewCar_ModelList = new ArrayList<>();
    LayoutInflater inflater;

    public NewMessageViewAdapter(Context context, ArrayList<ViewMessageListBean.Message_Home> allNewCar_ModelList)
    {
        this.context = context;
        this.allNewCar_ModelList.addAll(allNewCar_ModelList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return allNewCar_ModelList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return allNewCar_ModelList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewMessageListBean.Message_Home bean = allNewCar_ModelList.get(position);
        NewMessageViewAdapter.ViewHolder viewHolder;
        if(convertView ==null)
        {
            convertView=inflater.inflate(R.layout.message_view_list, null);
            viewHolder = new NewMessageViewAdapter.ViewHolder();

            viewHolder.messageTxtString_txtView = (TextView) convertView.findViewById(R.id.messageTxtString_txtView);
            viewHolder.sendByString_txtView = (TextView) convertView.findViewById(R.id.sendByString_txtView);
            viewHolder.createDateString_txtView = (TextView) convertView.findViewById(R.id.createDateString_txtView);

            convertView.setTag(viewHolder);
        }else
            viewHolder = (NewMessageViewAdapter.ViewHolder) convertView.getTag();

        viewHolder.messageTxtString_txtView.setText(bean.getMessage());
        viewHolder.sendByString_txtView.setText( bean.getFname() + " "+ bean.getLname());
        viewHolder.createDateString_txtView.setText(bean.getCreated_date());

        return convertView;
    }

    public class ViewHolder {
        TextView messageTxtString_txtView,sendByString_txtView, createDateString_txtView;
    }
}

