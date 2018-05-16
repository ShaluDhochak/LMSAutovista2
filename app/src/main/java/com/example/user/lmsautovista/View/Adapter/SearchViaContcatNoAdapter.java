package com.example.user.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/26/2018.
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
import com.example.user.lmsautovista.View.Activity.CheckFlowSearchActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchViaContcatNoAdapter extends BaseAdapter {

    Context context;
    SearchCustomerBean.Lead_Data bean;
    private List<SearchCustomerBean.Lead_Data> allFollowupDetailsBeanList = new ArrayList<>();
    LayoutInflater inflater;

    public SearchViaContcatNoAdapter(Context context, ArrayList<SearchCustomerBean.Lead_Data> allFollowupDetailsBeanList) {
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_contactno_listview, null);
            viewHolder = new ViewHolder();

            viewHolder.leadnameSearch_TextView = (TextView) convertView.findViewById(R.id.leadnameSearch_TextView);
            viewHolder.leademailSearch_TextView = (TextView) convertView.findViewById(R.id.leadEmailSearch_TextView);
            viewHolder.leadContactnoSearch_TextView = (TextView) convertView.findViewById(R.id.leadContactnoSearch_TextView);
            viewHolder.leadaddressSearch_TextView = (TextView) convertView.findViewById(R.id.leadaddressSearch_TextView);

            viewHolder.leadSourceSearchString_textView = (TextView) convertView.findViewById(R.id.leadSourceSearchString_textView);
            viewHolder.showroomLocationSearchString_textView = (TextView) convertView.findViewById(R.id.showroomLocationSearchString_textView);
            viewHolder.cseNameSearchString_textView = (TextView) convertView.findViewById(R.id.cseNameSearchString_textView);
            viewHolder.cseCallDateSearchString_textView = (TextView) convertView.findViewById(R.id.cseCallDateSearchString_textView);
            viewHolder.cseNextActionSearchString_textView = (TextView) convertView.findViewById(R.id.cseNextActionSearchString_textView);
            viewHolder.cseFeedBackSearchString_textView = (TextView) convertView.findViewById(R.id.cseFeedBackSearchString_textView);
            viewHolder.cseRemarkSearchString_textView = (TextView) convertView.findViewById(R.id.cseRemarkSearchString_textView);
            viewHolder.cseTDHVDateSearchString_textView = (TextView) convertView.findViewById(R.id.cseTDHVDateSearchString_textView);
            viewHolder.dseNameSearchString_textView = (TextView) convertView.findViewById(R.id.dseNameSearchString_textView);
            viewHolder.dseNextActionSearchString_textView = (TextView) convertView.findViewById(R.id.dseNextActionSearchString_textView);
            viewHolder.dseFeedBackSearchString_textView = (TextView) convertView.findViewById(R.id.dseFeedBackSearchString_textView);
            viewHolder.dseRemarkSearchString_textView = (TextView) convertView.findViewById(R.id.dseRemarkSearchString_textView);
            viewHolder.dseNFDSearchString_textView = (TextView) convertView.findViewById(R.id.dseNFDSearchString_textView);
            viewHolder.dseCallDateSearchString_textView = (TextView) convertView.findViewById(R.id.dseCallDateSearchString_textView);
            viewHolder.bookingWithInDaysSearchString_textView = (TextView) convertView.findViewById(R.id.bookingWithInDaysSearchString_textView);
            viewHolder.buyerTypeSearchString_textView = (TextView) convertView.findViewById(R.id.buyerTypeSearchString_textView);
            viewHolder.modelVariantSearchString_textView = (TextView) convertView.findViewById(R.id.modelVariantSearchString_textView);
            viewHolder.exchangeMakeModelSearchString_textView = (TextView) convertView.findViewById(R.id.exchangeMakeModelSearchString_textView);
            viewHolder.manufacturingYearSearchString_textView = (TextView) convertView.findViewById(R.id.manufacturingYearSearchString_textView);

            viewHolder.checkFlowBoxSearch_textView = (TextView) convertView.findViewById(R.id.checkFlowBoxSearch_textView);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.leadnameSearch_TextView.setText(bean.getName());
        viewHolder.leademailSearch_TextView.setText(bean.getEmail());
        viewHolder.leadContactnoSearch_TextView.setText(bean.getContact_no());
        viewHolder.leadaddressSearch_TextView.setText(bean.getAddress());
        // viewHolder.leadEnqIDSearch_TextView.setText(bean.getEnq_id());

        viewHolder.showroomLocationSearchString_textView.setText(bean.getShowroom_location());
        viewHolder.cseCallDateSearchString_textView.setText(bean.getCse_date());
        viewHolder.cseNextActionSearchString_textView.setText(bean.getCsenextAction());
        viewHolder.cseFeedBackSearchString_textView.setText(bean.getCsefeedback());
        viewHolder.cseRemarkSearchString_textView.setText(bean.getCse_comment());
        viewHolder.cseTDHVDateSearchString_textView.setText(bean.getTd_hv_date());
        viewHolder.dseNextActionSearchString_textView.setText(bean.getDsenextAction());
        viewHolder.dseFeedBackSearchString_textView.setText(bean.getDse_comment());
        viewHolder.dseRemarkSearchString_textView.setText(bean.getDse_comment());
        viewHolder.dseNFDSearchString_textView.setText(bean.getDse_nfd());
        viewHolder.dseCallDateSearchString_textView.setText(bean.getDse_date());
        viewHolder.bookingWithInDaysSearchString_textView.setText(bean.getDays60_booking());
        viewHolder.buyerTypeSearchString_textView.setText(bean.getBuyer_type());
        viewHolder.modelVariantSearchString_textView.setText(bean.getNew_model_name() + " " + bean.getVariant_name());
        viewHolder.exchangeMakeModelSearchString_textView.setText(bean.getMake_name() + " " + bean.getOld_model_name());
        viewHolder.manufacturingYearSearchString_textView.setText(bean.getManf_year());

        if (bean.getLead_source().equals("")) {
            viewHolder.leadSourceSearchString_textView.setText("Web");
        } else if (bean.getLead_source().equals("Facebook")) {
            viewHolder.leadSourceSearchString_textView.setText(bean.getEnquiry_for());
        } else {
            viewHolder.leadSourceSearchString_textView.setText(bean.getLead_source());
        }

        if ((bean.getAssign_to_dse().equals("0"))) {
            viewHolder.dseNameSearchString_textView.setText(bean.getDsetl_fname() + " " + bean.getDsetl_lname());
        } else {
            viewHolder.dseNameSearchString_textView.setText(bean.getDse_fname() + " " + bean.getDse_lname());
        }

        if ((bean.getAssign_to_cse().equals("0"))) {
            viewHolder.cseNameSearchString_textView.setText(bean.getCsetl_fname() + " " + bean.getCsetl_lname());
        } else {
            viewHolder.cseNameSearchString_textView.setText(bean.getCse_fname() + " " + bean.getCse_lname());
        }

        viewHolder.checkFlowBoxSearch_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckFlowSearchActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("bean", allFollowupDetailsBeanList.get(position));
                context.startActivity(intent);

            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView leadnameSearch_TextView, leademailSearch_TextView, leadContactnoSearch_TextView, leadaddressSearch_TextView;

        TextView leadSourceSearchString_textView, showroomLocationSearchString_textView, cseNameSearchString_textView, cseCallDateSearchString_textView;
        TextView cseNextActionSearchString_textView, cseFeedBackSearchString_textView, cseTDHVDateSearchString_textView, dseNameSearchString_textView;
        TextView dseNextActionSearchString_textView, dseFeedBackSearchString_textView, dseRemarkSearchString_textView, dseNFDSearchString_textView;
        TextView dseCallDateSearchString_textView, bookingWithInDaysSearchString_textView, buyerTypeSearchString_textView, modelVariantSearchString_textView;
        TextView exchangeMakeModelSearchString_textView, manufacturingYearSearchString_textView;

        TextView checkFlowBoxSearch_textView, cseRemarkSearchString_textView;

    }

}
