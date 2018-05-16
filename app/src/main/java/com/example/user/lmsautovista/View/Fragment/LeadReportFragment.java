package com.example.user.lmsautovista.View.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lmsautovista.Model.DSEDailyReportLocationBean;
import com.example.user.lmsautovista.Model.LeadReportBean;
import com.example.user.lmsautovista.Presenter.LeadReportPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class LeadReportFragment extends Fragment implements IView.ILeadReportView{

    View view;
    @BindView(R.id.locationLeadReport_Spinner)
    Spinner locationLeadReport_Spinner;

    @BindView(R.id.fromDateLeadReport_TextView)
    TextView fromDateLeadReport_TextView;
    @BindView(R.id.toDateLeadReport_TextView)
    TextView toDateLeadReport_TextView;

    ArrayList<String> location;
    Map<String,String> locationMap = new HashMap<>();
    String leadLocationSpinner, selectedLeadLocationId;

    DatePickerDialog datePickerDialog;

    @BindView(R.id.searchLeadReport_Button)
    Button searchLeadReport_Button;
    @BindView(R.id.leadList_cardView)
    CardView leadList_cardView;

    //ProgressDialog
    ProgressDialog pDialog;

    //textView for Total count value
    @BindView(R.id.leadsValueReport_textView)
    TextView leadsValueReport_textView;
    @BindView(R.id.UnassignedLeadValueReport_textView)
    TextView UnassignedLeadValueReport_textView;
    @BindView(R.id.pending_new_leadsValueReport_textView)
    TextView pending_new_leadsValueReport_textView;
    @BindView(R.id.total_pending_followup_leadsValueReport_textView)
    TextView total_pending_followup_leadsValueReport_textView;

    @BindView(R.id.booking_30ValueReport_textView)
    TextView booking_30ValueReport_textView;
    @BindView(R.id.booking_60ValueReport_textView)
    TextView booking_60ValueReport_textView;
    @BindView(R.id.booking_greater_60ValueReport_textView)
    TextView booking_greater_60ValueReport_textView;

    //textview for Total_feedback
    @BindView(R.id.undecidedValueReport_textView)
    TextView undecidedValueReport_textView;
    @BindView(R.id.not_interestedValueReport_textView)
    TextView not_interestedValueReport_textView;
    @BindView(R.id.already_booked_from_usValueReport_textView)
    TextView already_booked_from_usValueReport_textView;
    @BindView(R.id.lost_to_codealerValueReport_textView)
    TextView lost_to_codealerValueReport_textView;

    @BindView(R.id.color_model_availabilityValueReport_textView)
    TextView color_model_availabilityValueReport_textView;
    @BindView(R.id.budget_issueValueReport_textView)
    TextView budget_issueValueReport_textView;
    @BindView(R.id.nearest_dealershipValueReport_textView)
    TextView nearest_dealershipValueReport_textView;

    @BindView(R.id.outstation_purchaseValueReport_textView)
    TextView outstation_purchaseValueReport_textView;
    @BindView(R.id.plan_cancelValueReport_textView)
    TextView plan_cancelValueReport_textView;
    @BindView(R.id.showroom_visitValueReport_textView)
    TextView showroom_visitValueReport_textView;

    @BindView(R.id.test_driveValueReport_textView)
    TextView test_driveValueReport_textView;
    @BindView(R.id.evaluation_allottedValueReport_textView)
    TextView evaluation_allottedValueReport_textView;
    @BindView(R.id.dealValueReport_textView)
    TextView dealValueReport_textView;

    @BindView(R.id.booked_from_autovistaValueReport_textView)
    TextView booked_from_autovistaValueReport_textView;
    @BindView(R.id.follow_upLeadValueReport_textView)
    TextView follow_upLeadValueReport_textView;
    @BindView(R.id.home_visitValueReport_textView)
    TextView home_visitValueReport_textView;

    LeadReportPresenter leadReportPresenter;
    String selectedLocation, selectedLocationId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lead_report, container, false);

        ButterKnife.bind(this, view);

        initialiseUI();
        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseUI(){
        leadList_cardView.setVisibility(View.GONE);
        leadReportPresenter =new LeadReportPresenter(this);
        leadReportPresenter.getLeadLocation(getActivity());
    }

    @Override
    public void showLocation(DSEDailyReportLocationBean jsonObject) {
        try{
        ArrayList<String> locationArrayList = new ArrayList<>();
        locationArrayList.add("Location");
        for(int i=0;i<jsonObject.getDaliy_dse_tracker_location().size();i++)
        {
            try {
                locationArrayList.add(jsonObject.getDaliy_dse_tracker_location().get(i).getLocation());
                locationMap.put(jsonObject.getDaliy_dse_tracker_location().get(i).getLocation_id(), jsonObject.getDaliy_dse_tracker_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, locationArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationLeadReport_Spinner.setAdapter(companiesArrayAdapter);

    }catch(Exception e) {
        }
    }

    //details
    @OnItemSelected(R.id.locationLeadReport_Spinner)
    public void locationSelected(Spinner spinner, int position)
    {
        selectedLocation = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLocation)) {
                selectedLocationId = (String) key;
                Log.i("Selected CSE : ",selectedLocationId);
                if (selectedLocation.equals("Location")){
                    selectedLocationId = "";
                }
            }
        }

    }

    @OnClick(R.id.toDateLeadReport_TextView)
    public void toDateReport(){
        final Calendar todatecalenderObject = Calendar.getInstance();
        int toYear = todatecalenderObject.get(Calendar.YEAR);
        int toMonth = todatecalenderObject.get(Calendar.MONTH);
        int toDay = todatecalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        toDateLeadReport_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, toYear, toMonth, toDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.fromDateLeadReport_TextView)
    public void fromDateReport(){
        final Calendar fromdatecalenderObject = Calendar.getInstance();
        int BookedYear = fromdatecalenderObject.get(Calendar.YEAR);
        int BookedMonth = fromdatecalenderObject.get(Calendar.MONTH);
        int BookedDay = fromdatecalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        fromDateLeadReport_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.searchLeadReport_Button)
    public void searchLeadReport(){
        searchLead();
    }

    public void searchLead(){
        try {
            String fromdate = fromDateLeadReport_TextView.getText().toString();
            String toDate = toDateLeadReport_TextView.getText().toString();
            if (!(selectedLocation.toString().equals("Location"))) {
                if (!(fromDateLeadReport_TextView.getText().toString().equals(""))) {
                    if (!(toDateLeadReport_TextView.getText().toString().equals(""))) {
                        leadReportPresenter.getSearchVialOcationList(selectedLocationId, fromdate, toDate, getActivity());
                    } else {
                        Toast.makeText(getActivity(), "Please select Date To.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please select Date From.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
        }
        //leadReportPresenter.getSearchViaContactNoList(selectedLocationId, fromdate, toDate,getActivity());
    }

    @Override
    public void showLeadReportView(LeadReportBean jsonObject) {
        try{
        if (jsonObject.getTotal_count().size()>0){
            leadList_cardView.setVisibility(View.VISIBLE);
            leadsValueReport_textView.setText(jsonObject.getTotal_count().get(0).getTotal_leads());
            UnassignedLeadValueReport_textView.setText(jsonObject.getTotal_count().get(0).getTotal_unassigned_leads());
            pending_new_leadsValueReport_textView.setText(jsonObject.getTotal_count().get(0).getTotal_pending_new_leads());
            total_pending_followup_leadsValueReport_textView.setText(jsonObject.getTotal_count().get(0).getTotal_pending_followup_leads());
            booking_30ValueReport_textView.setText(jsonObject.getTotal_count().get(0).getTotal_booking_30());
            booking_60ValueReport_textView.setText(jsonObject.getTotal_count().get(0).getTotal_booking_60());
            booking_greater_60ValueReport_textView.setText(jsonObject.getTotal_count().get(0).getTotal_booking_greater_60());
        }else{
            Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
        }

        if (jsonObject.getTotal_feedback().size()>0){
            undecidedValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getUndecided());
            not_interestedValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getNot_interested());
            already_booked_from_usValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getAlready_booked_from_us());
            lost_to_codealerValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getLost_to_codealer());
            color_model_availabilityValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getColor_model_availability());
            budget_issueValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getBudget_issue());
            nearest_dealershipValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getNearest_dealership());
            outstation_purchaseValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getOutstation_purchase());
            plan_cancelValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getPlan_cancel());
            showroom_visitValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getShowroom_visit());
            test_driveValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getTest_drive());
            evaluation_allottedValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getEvaluation_allotted());
            dealValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getDeal());
            booked_from_autovistaValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getBooked_from_autovista());
            follow_upLeadValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getFollow_up());
            home_visitValueReport_textView.setText(jsonObject.getTotal_feedback().get(0).getHome_visit());
        }else{
            Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
        }
        }catch(Exception e){
        }
    }
}

