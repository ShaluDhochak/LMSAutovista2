package com.excell.lms.lmsautovista.View.Adapter;

/*
 Created by Shalu Dhochak on 4/30/2018.
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.DSEDailyReportLocationBean;
import com.excell.lms.lmsautovista.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class LocationAddMessageAdapter  extends BaseAdapter {

    Context context;
    DSEDailyReportLocationBean.Daily_Dse_Tracker_Location bean;
    ArrayList<DSEDailyReportLocationBean.Daily_Dse_Tracker_Location> locationBeanList = new ArrayList<>();
    LayoutInflater inflater;

    ArrayList<String> selectedDseTlList = new ArrayList<>();

    JSONArray selectedDSETlJsonArray ;
    JSONArray selectedLocation;
    JSONArray selectedDSEJsonArray ;
    boolean[] selectedCheckBoxDseTl;
    boolean[] selectedCheckBoxDse;

    public LocationAddMessageAdapter(Context context, ArrayList<DSEDailyReportLocationBean.Daily_Dse_Tracker_Location> locationBeanList)
    {
        this.context = context;
        this.locationBeanList.clear();
        this.locationBeanList.addAll(locationBeanList);
        selectedCheckBoxDseTl = new boolean[locationBeanList.size()];
        for(int i=0;i<selectedCheckBoxDseTl.length;i++)
            selectedCheckBoxDseTl[i] = false;

        //Code for Dse
        selectedCheckBoxDse = new boolean[locationBeanList.size()];
        for(int i=0;i<selectedCheckBoxDse.length;i++)
            selectedCheckBoxDse[i] = false;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getItem(int position) {
        return locationBeanList.get(position);
    }

    @Override
    public int getCount() {
        return locationBeanList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bean = locationBeanList.get(position);
        LocationAddMessageAdapter.ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.location_addmessage_list,null);
            viewHolder = new LocationAddMessageAdapter.ViewHolder();
            viewHolder.locationAddMessageString_tv = (TextView) convertView.findViewById(R.id.locationAddMessageString_tv);
            viewHolder.dseTlAddMessageString_checkbox = (CheckBox) convertView.findViewById(R.id.dseTlAddMessageString_checkbox);

            viewHolder.dseAddMessageString_checkBox = (CheckBox) convertView.findViewById(R.id.dseAddMessageString_checkBox);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (LocationAddMessageAdapter.ViewHolder) convertView.getTag();

        viewHolder.locationAddMessageString_tv.setText(bean.getLocation());

        //Code for DseTl
        if(selectedCheckBoxDseTl[position])
            viewHolder.dseTlAddMessageString_checkbox.setChecked(true);
        else
            viewHolder.dseTlAddMessageString_checkbox.setChecked(false);

        viewHolder.dseTlAddMessageString_checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i=0;i<selectedCheckBoxDseTl.length;i++){
                    if(i==position){
                        if(selectedCheckBoxDseTl[i])
                            selectedCheckBoxDseTl[i]=false;
                        else
                            selectedCheckBoxDseTl[i]=true;
                    }
                }
                notifyDataSetChanged();
            }
        });

        //Code for Dse
        if(selectedCheckBoxDse[position])
            viewHolder.dseAddMessageString_checkBox.setChecked(true);
        else
            viewHolder.dseAddMessageString_checkBox.setChecked(false);

        viewHolder.dseAddMessageString_checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i=0;i<selectedCheckBoxDse.length;i++){
                    if(i==position){
                        if(selectedCheckBoxDse[i])
                            selectedCheckBoxDse[i]=false;
                        else
                            selectedCheckBoxDse[i]=true;
                    }
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public JSONArray getSelectedDseTl()
    {
        selectedDSETlJsonArray = new JSONArray();
        for (int i=0;i<selectedCheckBoxDseTl.length;i++)
        {
            if(selectedCheckBoxDseTl[i]) {
                selectedDSETlJsonArray.put("1");
                //selectedDSETlJsonArray.put(locationBeanList.get(i).getLocation_id());
            }else{
                selectedDSETlJsonArray.put("0");
                //selectedDSETlJsonArray.put(locationBeanList.get(i).getLocation_id());
            }
        }
        return selectedDSETlJsonArray;
    }

    public ArrayList<String> getSelectedTlArray(){
        ArrayList<String> listItems = new ArrayList<String>();

        selectedDSETlJsonArray = new JSONArray();
        for (int i=0;i<selectedCheckBoxDseTl.length;i++)
        {
            if(selectedCheckBoxDseTl[i]) {
                listItems.add(String.valueOf(selectedDSETlJsonArray.put(1)));
            }else{
            }
        }
        return listItems;
    }

    public ArrayList<String> getSelectedDseArray(){
        ArrayList<String> listDseItems = new ArrayList<String>();

        selectedDSEJsonArray = new JSONArray();
        for (int i=0;i<selectedCheckBoxDse.length;i++)
        {
            if(selectedCheckBoxDse[i]) {
                listDseItems.add(String.valueOf(selectedDSEJsonArray.put(1)));
            }else{
            }
        }
        return listDseItems;
    }

    public JSONArray getSelectedLocation()
    {
        selectedLocation = new JSONArray();
        for (int i=0;i<locationBeanList.size();i++)
        {
            selectedLocation.put(locationBeanList.get(i).getLocation_id());

        }
        return selectedLocation;
    }

    //method for Dse
    public JSONArray getSelectedDse()
    {
        selectedDSEJsonArray = new JSONArray();
        for (int i=0;i<selectedCheckBoxDse.length;i++)
        {
            if(selectedCheckBoxDse[i]) {
                selectedDSEJsonArray.put("1");
            }else{
                selectedDSEJsonArray.put("0");
            }
        }
        return selectedDSEJsonArray;
    }


    public class ViewHolder {
        TextView locationAddMessageString_tv;
        CheckBox dseTlAddMessageString_checkbox, dseAddMessageString_checkBox;
    }
}
