package com.example.user.lmsautovista.View.Adapter;

/*
 Created by Shalu Dhochak on 4/30/2018.
*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.MessageListBean;
import com.example.user.lmsautovista.R;

import java.util.ArrayList;
import java.util.List;

public class MessageLocationListAdapter  extends RecyclerView.Adapter<MessageLocationListAdapter.MyViewHolder> {
    private List<MessageListBean.Message_List.Location> moviesList;

    ArrayList<String> locationList = new ArrayList<>();
    Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView locationNameMsgList_textView,dseTlNameMsgList_textView, dseNameMsgList_textView;
        MyViewHolder(View view) {
            super(view);
            locationNameMsgList_textView = (TextView) view.findViewById(R.id.locationNameMsgList_textView);
            dseTlNameMsgList_textView = (TextView) view.findViewById(R.id.dseTlNameMsgList_textView);
            dseNameMsgList_textView = (TextView) view.findViewById(R.id.dseNameMsgList_textView);
        }
    }

    public MessageLocationListAdapter(Context context,List<MessageListBean.Message_List.Location> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MessageLocationListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_lv_msg_layout, parent, false);
        return new MessageLocationListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageLocationListAdapter.MyViewHolder holder, final int position) {
        MessageListBean.Message_List.Location locationBean = moviesList.get(position);
        for (int i = 0; i<= position; i++){
            holder.locationNameMsgList_textView.setText( " > " +locationBean.getLocation());
            if (locationBean.getTl().toString().equals("0")){
                holder.dseTlNameMsgList_textView.setText("      ");
            }else{
                holder.dseTlNameMsgList_textView.setText("DSE-TL");
            }

            if (locationBean.getDse().toString().equals("0")){
                holder.dseNameMsgList_textView.setText("     ");
            }else{
                holder.dseNameMsgList_textView.setText("DSE");
            }
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}


