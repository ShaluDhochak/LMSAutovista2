package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/30/2018.
*/

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.MessageListBean;
import com.excell.lms.lmsautovista.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class MessageListAdapter   extends BaseAdapter {

    Context context;
    MessageListBean.Message_List bean;

    boolean[] selectedCheckBox;
    ArrayList<MessageListBean.Message_List> allMessageBeanList = new ArrayList<>();
    LayoutInflater inflater;
    JSONArray selectedCSEJsonArray ;

    public MessageListAdapter(Context context, ArrayList<MessageListBean.Message_List> allMessageBeanList)
    {
        this.context = context;
        this.allMessageBeanList.clear();
        this.allMessageBeanList.addAll(allMessageBeanList);

        selectedCheckBox = new boolean[allMessageBeanList.size()];
        for(int i=0;i<selectedCheckBox.length;i++)
            selectedCheckBox[i] = false;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getItem(int position) {
        return allMessageBeanList.get(position);
    }

    @Override
    public int getCount() {
        return allMessageBeanList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bean = allMessageBeanList.get(position);
        MessageListAdapter.ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.message_list_layout,null);
            viewHolder = new MessageListAdapter.ViewHolder();
            viewHolder.deleteMsgFromList_checkBox = (CheckBox) convertView.findViewById(R.id.deleteMsgFromList_checkBox);
            viewHolder.messageNameList_textView = (TextView) convertView.findViewById(R.id.messageNameList_textView);
            viewHolder.location_rv = (RecyclerView) convertView.findViewById(R.id.location_rv);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (MessageListAdapter.ViewHolder) convertView.getTag();

        //  viewHolder.deleteMsgFromList_TextView.setText(bean.getMessage());
        viewHolder.messageNameList_textView.setText(bean.getMessage());


        MessageLocationListAdapter messageLocationListAdapter;
        messageLocationListAdapter = new MessageLocationListAdapter(context,bean.getLocation());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        viewHolder.location_rv.setLayoutManager(mLayoutManager);
        viewHolder.location_rv.setItemAnimator(new DefaultItemAnimator());
        viewHolder.location_rv.setAdapter(messageLocationListAdapter);

        /*if (bean.getLocation().size()>0) {
            MessageLocationListAdapter messageLocationListAdapter;
            messageLocationListAdapter = new MessageLocationListAdapter(context, bean.getLocation());
            viewHolder.location_listView.setAdapter(messageLocationListAdapter);
            messageLocationListAdapter.notifyDataSetChanged();
        }
        */

        if(selectedCheckBox[position])
            viewHolder.deleteMsgFromList_checkBox.setChecked(true);
        else
            viewHolder.deleteMsgFromList_checkBox.setChecked(false);

        viewHolder.deleteMsgFromList_checkBox.setOnClickListener(new View.OnClickListener() {
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

    public JSONArray getSelectedMessageId()
    {
        selectedCSEJsonArray = new JSONArray();
        for (int i=0;i<selectedCheckBox.length;i++)
        {
            if(selectedCheckBox[i]) {
                selectedCSEJsonArray.put(allMessageBeanList.get(i).getMessage_id());
            }
        }
        return selectedCSEJsonArray;
    }

    public class ViewHolder {
        TextView messageNameList_textView;
        CheckBox deleteMsgFromList_checkBox;
        RecyclerView location_rv;
    }
}


