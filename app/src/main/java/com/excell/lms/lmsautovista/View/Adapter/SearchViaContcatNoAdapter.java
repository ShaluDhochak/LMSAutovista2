package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 4/26/2018.
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.excell.lms.lmsautovista.Model.SearchCustomerBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Activity.CheckFlowSearchActivity;

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
            viewHolder.leadAltContactnoSearch_TextView = (TextView) convertView.findViewById(R.id.leadAltContactnoSearch_TextView);
            viewHolder.leadaddressSearch_TextView = (TextView) convertView.findViewById(R.id.leadaddressSearch_TextView);

            viewHolder.leadSourceSearchString_textView = (TextView) convertView.findViewById(R.id.leadSourceSearchString_textView);
            viewHolder.showroomLocationSearchString_textView = (TextView) convertView.findViewById(R.id.showroomLocationSearchString_textView);
            viewHolder.cseNameSearchString_textView = (TextView) convertView.findViewById(R.id.cseNameSearchString_textView);
            viewHolder.cseCallDateSearchString_textView = (TextView) convertView.findViewById(R.id.cseCallDateSearchString_textView);
            viewHolder.cseNextActionSearchString_textView = (TextView) convertView.findViewById(R.id.cseNextActionSearchString_textView);
            viewHolder.cseFeedBackSearchString_textView = (TextView) convertView.findViewById(R.id.cseFeedBackSearchString_textView);
            viewHolder.cseRemarkSearchString_textView = (TextView) convertView.findViewById(R.id.cseRemarkSearchString_textView);
        //    viewHolder.cseTDHVDateSearchString_textView = (TextView) convertView.findViewById(R.id.cseTDHVDateSearchString_textView);
            viewHolder.dseNameSearchString_textView = (TextView) convertView.findViewById(R.id.dseNameSearchString_textView);
            viewHolder.dseNextActionSearchString_textView = (TextView) convertView.findViewById(R.id.dseNextActionSearchString_textView);
            viewHolder.dseFeedBackSearchString_textView = (TextView) convertView.findViewById(R.id.dseFeedBackSearchString_textView);
            viewHolder.dseRemarkSearchString_textView = (TextView) convertView.findViewById(R.id.dseRemarkSearchString_textView);
            viewHolder.dseNFDSearchString_textView = (TextView) convertView.findViewById(R.id.dseNFDSearchString_textView);
            viewHolder.dseCallDateSearchString_textView = (TextView) convertView.findViewById(R.id.dseCallDateSearchString_textView);
            viewHolder.buyerTypeSearchString_textView = (TextView) convertView.findViewById(R.id.buyerTypeSearchString_textView);
            viewHolder.modelVariantSearchString_textView = (TextView) convertView.findViewById(R.id.modelVariantSearchString_textView);


            viewHolder.auditorNameSearchString_textView = (TextView) convertView.findViewById(R.id.auditorNameSearchString_textView);
            viewHolder.cseNFTSearchString_textView = (TextView) convertView.findViewById(R.id.cseNFTSearchString_textView);
            viewHolder.cseNFDSearchString_textView = (TextView) convertView.findViewById(R.id.cseNFDSearchString_textView);
            viewHolder.leadDateSearchString_textView = (TextView) convertView.findViewById(R.id.leadDateSearchString_textView);
            viewHolder.assistanceRequiredSearchString_textView = (TextView) convertView.findViewById(R.id.assistanceRequiredSearchString_textView);
            viewHolder.bookingWithinDaysSearchString_textView = (TextView) convertView.findViewById(R.id.bookingWithinDaysSearchString_textView);

            viewHolder.auditorRemarkSearchString_textView = (TextView) convertView.findViewById(R.id.auditorRemarkSearchString_textView);
            viewHolder.serviceFeedbackSearchString_textView = (TextView) convertView.findViewById(R.id.serviceFeedbackSearchString_textView);
            viewHolder.fakeUpdationSearchString_textView = (TextView) convertView.findViewById(R.id.fakeUpdationSearchString_textView);
            viewHolder.callReceivedFromShowroomSearchString_textView = (TextView) convertView.findViewById(R.id.callReceivedFromShowroomSearchString_textView);
            viewHolder.followUpPendingSearchString_textView = (TextView) convertView.findViewById(R.id.followUpPendingSearchString_textView);
            viewHolder.auditorFollowUpDateSearchString_textView = (TextView) convertView.findViewById(R.id.auditorFollowUpDateSearchString_textView);
            viewHolder.dseNFTSearchString_textView = (TextView) convertView.findViewById(R.id.dseNFTSearchString_textView);


            viewHolder.leadTimeSearchString_textView = (TextView) convertView.findViewById(R.id.leadTimeSearchString_textView);
            viewHolder.cseAssignedTimeSearchString_textView = (TextView) convertView.findViewById(R.id.cseAssignedTimeSearchString_textView);
            viewHolder.cseAssignedDateSearchString_textView = (TextView) convertView.findViewById(R.id.cseAssignedDateSearchString_textView);
            viewHolder.cseCallTimeSearchString_textView = (TextView) convertView.findViewById(R.id.cseCallTimeSearchString_textView);
            viewHolder.cseCallStatusSearchString_textView = (TextView) convertView.findViewById(R.id.cseCallStatusSearchString_textView);
            viewHolder.assignedDateDseSearchString_textView = (TextView) convertView.findViewById(R.id.assignedDateDseSearchString_textView);


            viewHolder.customerLocationSearchString_textView = (TextView) convertView.findViewById(R.id.customerLocationSearchString_textView);
            viewHolder.appointmentFeedbackSearchString_textView = (TextView) convertView.findViewById(R.id.appointmentFeedbackSearchString_textView);
            viewHolder.appointmentRatingSearchString_textView = (TextView) convertView.findViewById(R.id.appointmentRatingSearchString_textView);
            viewHolder.appointmentStatusSearchString_textView = (TextView) convertView.findViewById(R.id.appointmentStatusSearchString_textView);
            viewHolder.appointmentAddressSearchString_textView = (TextView) convertView.findViewById(R.id.appointmentAddressSearchString_textView);
            viewHolder.appointmentDateSearchString_textView = (TextView) convertView.findViewById(R.id.appointmentDateSearchString_textView);


            viewHolder.appointmentTypeSearchString_textView = (TextView) convertView.findViewById(R.id.appointmentTypeSearchString_textView);
            viewHolder.assignedTimeDseSearchString_textView = (TextView) convertView.findViewById(R.id.assignedTimeDseSearchString_textView);
            viewHolder.dseCallStatusSearchString_textView = (TextView) convertView.findViewById(R.id.dseCallStatusSearchString_textView);
            viewHolder.dseCallTimeSearchString_textView = (TextView) convertView.findViewById(R.id.dseCallTimeSearchString_textView);
            viewHolder.interestedInFinanceSearchString_textView = (TextView) convertView.findViewById(R.id.interestedInFinanceSearchString_textView);
            viewHolder.interestedInEWSearchString_textView = (TextView) convertView.findViewById(R.id.interestedInEWSearchString_textView);


            viewHolder.interestedInInsuranceSearchString_textView = (TextView) convertView.findViewById(R.id.interestedInInsuranceSearchString_textView);
            viewHolder.interestedInAccessoriesSearchString_textView = (TextView) convertView.findViewById(R.id.interestedInAccessoriesSearchString_textView);
            viewHolder.auditorFollowUpStatusSearchString_textView = (TextView) convertView.findViewById(R.id.auditorFollowUpStatusSearchString_textView);
            viewHolder.auditorFollowUpTimeSearchString_textView = (TextView) convertView.findViewById(R.id.auditorFollowUpTimeSearchString_textView);

            //Used Car Layout
            viewHolder.accidentalClaimSearch_linearlayout = (LinearLayout) convertView.findViewById(R.id.accidentalClaimSearch_linearlayout);
            viewHolder.budgetToOldCarSearch_linearlayout = (LinearLayout) convertView.findViewById(R.id.budgetToOldCarSearch_linearlayout);
            viewHolder.budgetFromOldCarSearch_linearlayout = (LinearLayout) convertView.findViewById(R.id.budgetFromOldCarSearch_linearlayout);
            viewHolder.kmOldCarSearch_linearlayout = (LinearLayout) convertView.findViewById(R.id.kmOldCarSearch_linearlayout);
            viewHolder.ownershiSearch_linearlayout = (LinearLayout) convertView.findViewById(R.id.ownershiSearch_linearlayout);
            viewHolder.manfYearSearch_linearlayout = (LinearLayout) convertView.findViewById(R.id.manfYearSearch_linearlayout);
            viewHolder.exchangeMakeModelSearch_linearlayout = (LinearLayout) convertView.findViewById(R.id.exchangeMakeModelSearch_linearlayout);
            //Used Car
            viewHolder.exchangeMakeModelSearchString_textView = (TextView) convertView.findViewById(R.id.exchangeMakeModelSearchString_textView);
            viewHolder.manfYearSearchString_textView = (TextView) convertView.findViewById(R.id.manfYearSearchString_textView);
            viewHolder.ownershiSearchString_textView = (TextView) convertView.findViewById(R.id.ownershiSearchString_textView);
            viewHolder.kmOldCarSearchString_textView = (TextView) convertView.findViewById(R.id.kmOldCarSearchString_textView);
            viewHolder.budgetFromOldCarSearchString_textView = (TextView) convertView.findViewById(R.id.budgetFromOldCarSearchString_textView);
            viewHolder.budgetToOldCarSearchString_textView = (TextView) convertView.findViewById(R.id.budgetToOldCarSearchString_textView);
            viewHolder.accidentalClaimSearchString_textView = (TextView) convertView.findViewById(R.id.accidentalClaimSearchString_textView);


            viewHolder.checkFlowBoxSearch_textView = (TextView) convertView.findViewById(R.id.checkFlowBoxSearch_textView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        if (bean.getProcess().equals("New Car")){
            viewHolder.accidentalClaimSearch_linearlayout.setVisibility(View.GONE);
            viewHolder.budgetToOldCarSearch_linearlayout.setVisibility(View.GONE);
            viewHolder.budgetFromOldCarSearch_linearlayout.setVisibility(View.GONE);
            viewHolder.kmOldCarSearch_linearlayout.setVisibility(View.GONE);
            viewHolder.ownershiSearch_linearlayout.setVisibility(View.GONE);
            viewHolder.manfYearSearch_linearlayout.setVisibility(View.GONE);
            viewHolder.exchangeMakeModelSearch_linearlayout.setVisibility(View.GONE);
        }else if (bean.getProcess().equals("Used Car")){
            viewHolder.accidentalClaimSearch_linearlayout.setVisibility(View.VISIBLE);
            viewHolder.budgetToOldCarSearch_linearlayout.setVisibility(View.VISIBLE);
            viewHolder.budgetFromOldCarSearch_linearlayout.setVisibility(View.VISIBLE);
            viewHolder.kmOldCarSearch_linearlayout.setVisibility(View.VISIBLE);
            viewHolder.ownershiSearch_linearlayout.setVisibility(View.VISIBLE);
            viewHolder.manfYearSearch_linearlayout.setVisibility(View.VISIBLE);
            viewHolder.exchangeMakeModelSearch_linearlayout.setVisibility(View.VISIBLE);
        }

        viewHolder.leadnameSearch_TextView.setText(bean.getName());
        viewHolder.leademailSearch_TextView.setText(bean.getEmail());
        viewHolder.leadContactnoSearch_TextView.setText(bean.getContact_no());
        viewHolder.leadAltContactnoSearch_TextView.setText(bean.getAlternate_contact_no());
        viewHolder.leadaddressSearch_TextView.setText(bean.getAddress());
        // viewHolder.leadEnqIDSearch_TextView.setText(bean.getEnq_id());

        viewHolder.showroomLocationSearchString_textView.setText(bean.getShowroom_location());
        viewHolder.cseCallDateSearchString_textView.setText(bean.getCse_date());
        viewHolder.cseNextActionSearchString_textView.setText(bean.getCsenextAction());
        viewHolder.cseFeedBackSearchString_textView.setText(bean.getCsefeedback());
        viewHolder.cseRemarkSearchString_textView.setText(bean.getCse_comment());
    //    viewHolder.cseTDHVDateSearchString_textView.setText(bean.getTd_hv_date());
        viewHolder.dseNextActionSearchString_textView.setText(bean.getDsenextAction());
        viewHolder.dseFeedBackSearchString_textView.setText(bean.getDse_comment());
        viewHolder.dseRemarkSearchString_textView.setText(bean.getDse_comment());
        viewHolder.dseNFDSearchString_textView.setText(bean.getDse_nfd());
        viewHolder.dseCallDateSearchString_textView.setText(bean.getDse_date());
        viewHolder.buyerTypeSearchString_textView.setText(bean.getBuyer_type());
        viewHolder.modelVariantSearchString_textView.setText(bean.getNew_model_name() + " " + bean.getVariant_name());


        viewHolder.bookingWithinDaysSearchString_textView.setText(bean.getDays60_booking());
        viewHolder.assistanceRequiredSearchString_textView.setText(bean.getAssistance());
        viewHolder.leadDateSearchString_textView.setText(bean.getLead_date());
        viewHolder.cseNFDSearchString_textView.setText(bean.getCse_nfd());
        viewHolder.cseNFTSearchString_textView.setText(bean.getCse_nftime());
        viewHolder.auditorNameSearchString_textView.setText(bean.getAuditfname() + " "+ bean.getAuditlname());
        viewHolder.dseNFTSearchString_textView.setText(bean.getDse_nftime());
        viewHolder.auditorFollowUpDateSearchString_textView.setText(bean.getAuditor_date());
        viewHolder.followUpPendingSearchString_textView.setText(bean.getFollowup_pending());
        viewHolder.callReceivedFromShowroomSearchString_textView.setText(bean.getCall_received());
        viewHolder.fakeUpdationSearchString_textView.setText(bean.getFake_updation());
        viewHolder.serviceFeedbackSearchString_textView.setText(bean.getService_feedback());
        viewHolder.auditorRemarkSearchString_textView.setText(bean.getAuditor_remark());

        viewHolder.leadTimeSearchString_textView.setText(bean.getLead_time());
        viewHolder.cseAssignedTimeSearchString_textView.setText(bean.getAssign_to_cse_time());
        viewHolder.cseAssignedDateSearchString_textView.setText(bean.getAssign_to_cse_date());
        viewHolder.cseCallTimeSearchString_textView.setText(bean.getCse_time());
        viewHolder.cseCallStatusSearchString_textView.setText(bean.getCsecontactibility());
        viewHolder.assignedDateDseSearchString_textView.setText(bean.getAssign_to_dse_date());

        viewHolder.customerLocationSearchString_textView.setText(bean.getCustomer_location());
        viewHolder.appointmentFeedbackSearchString_textView.setText(bean.getAppointment_feedback());
        viewHolder.appointmentRatingSearchString_textView.setText(bean.getAppointment_rating());
        viewHolder.appointmentStatusSearchString_textView.setText(bean.getAppointment_status());
        viewHolder.appointmentAddressSearchString_textView.setText(bean.getAppointment_address());
        viewHolder.appointmentDateSearchString_textView.setText(bean.getAppointment_date());


        viewHolder.appointmentTypeSearchString_textView.setText(bean.getAppointment_type());
        viewHolder.assignedTimeDseSearchString_textView.setText(bean.getAppointment_time());
        viewHolder.dseCallStatusSearchString_textView.setText(bean.getDsecontactibility());
        viewHolder.dseCallTimeSearchString_textView.setText(bean.getDse_time());
        viewHolder.interestedInFinanceSearchString_textView.setText(bean.getInterested_in_finance());
        viewHolder.interestedInEWSearchString_textView.setText(bean.getInterested_in_ew());


        viewHolder.interestedInInsuranceSearchString_textView.setText(bean.getInterested_in_insurance());
        viewHolder.interestedInAccessoriesSearchString_textView.setText(bean.getInterested_in_accessories());
        viewHolder.auditorFollowUpStatusSearchString_textView.setText(bean.getAuditor_call_status());
        viewHolder.auditorFollowUpTimeSearchString_textView.setText(bean.getAuditor_time());


        //Used CAr
        viewHolder.exchangeMakeModelSearchString_textView.setText(bean.getOld_model_name() );
        viewHolder.manfYearSearchString_textView.setText(bean.getManf_year());
        viewHolder.ownershiSearchString_textView.setText(bean.getOwnership());
        viewHolder.kmOldCarSearchString_textView.setText(bean.getKm());
        viewHolder.budgetFromOldCarSearchString_textView.setText(bean.getBudget_from());
        viewHolder.budgetToOldCarSearchString_textView.setText(bean.getBudget_to());
        viewHolder.accidentalClaimSearchString_textView.setText(bean.getAccidental_claim());


        if (bean.getLead_source().equals("")) {
            viewHolder.leadSourceSearchString_textView.setText("Web");
        } else if (bean.getLead_source().equals("Facebook")) {
            viewHolder.leadSourceSearchString_textView.setText(bean.getLead_source());
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
        TextView leadnameSearch_TextView, leademailSearch_TextView, leadContactnoSearch_TextView, leadAltContactnoSearch_TextView,leadaddressSearch_TextView;

        TextView leadSourceSearchString_textView, customerLocationSearchString_textView, cseNameSearchString_textView, cseCallDateSearchString_textView;
        TextView cseNextActionSearchString_textView, cseFeedBackSearchString_textView, cseTDHVDateSearchString_textView, dseNameSearchString_textView;
        TextView dseNextActionSearchString_textView, dseFeedBackSearchString_textView, dseRemarkSearchString_textView, dseNFDSearchString_textView;
        TextView dseCallDateSearchString_textView, bookingWithInDaysSearchString_textView, buyerTypeSearchString_textView, modelVariantSearchString_textView;
       // TextView exchangeMakeModelSearchString_textView, manufacturingYearSearchString_textView;

        TextView checkFlowBoxSearch_textView, cseRemarkSearchString_textView;

        TextView locationSearchString_textView,assistanceRequiredSearchString_textView,bookingWithinDaysSearchString_textView,leadDateSearchString_textView;
        TextView cseNFDSearchString_textView, cseNFTSearchString_textView,dseNFTSearchString_textView,auditorNameSearchString_textView;
        TextView auditorFollowUpDateSearchString_textView, followUpPendingSearchString_textView,callReceivedFromShowroomSearchString_textView;
        TextView fakeUpdationSearchString_textView,serviceFeedbackSearchString_textView,auditorRemarkSearchString_textView;

       // TextView exchangeModelVariantSearchString_textView,mfgYearSearchString_textView,ownershipSearchString_textView,kmSearchString_textView;
       // TextView budgetFromSearchString_textView,budgetToSearchString_textView,accidentalClaimSearchString_textView;

        TextView leadTimeSearchString_textView,cseAssignedTimeSearchString_textView,cseAssignedDateSearchString_textView,cseCallTimeSearchString_textView;
        TextView cseCallStatusSearchString_textView,assignedDateDseSearchString_textView,showroomLocationSearchString_textView, appointmentFeedbackSearchString_textView;
        TextView appointmentRatingSearchString_textView,appointmentStatusSearchString_textView,appointmentAddressSearchString_textView;
        TextView appointmentDateSearchString_textView,appointmentTypeSearchString_textView,assignedTimeDseSearchString_textView,dseCallStatusSearchString_textView;
        TextView dseCallTimeSearchString_textView, interestedInFinanceSearchString_textView,interestedInEWSearchString_textView;
        TextView interestedInInsuranceSearchString_textView,interestedInAccessoriesSearchString_textView,auditorFollowUpStatusSearchString_textView;
        TextView auditorFollowUpTimeSearchString_textView;

        TextView manfYearSearchString_textView,exchangeMakeModelSearchString_textView,ownershiSearchString_textView, kmOldCarSearchString_textView;
        TextView budgetFromOldCarSearchString_textView,budgetToOldCarSearchString_textView, accidentalClaimSearchString_textView;

        LinearLayout ownershiSearch_linearlayout,manfYearSearch_linearlayout,exchangeMakeModelSearch_linearlayout,kmOldCarSearch_linearlayout;
        LinearLayout budgetFromOldCarSearch_linearlayout,budgetToOldCarSearch_linearlayout, accidentalClaimSearch_linearlayout;



    }

}
