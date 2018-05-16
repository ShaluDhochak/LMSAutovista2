package com.example.user.lmsautovista.View.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lmsautovista.Model.FeedbackListBean;
import com.example.user.lmsautovista.Model.LeadSourceBean;
import com.example.user.lmsautovista.Model.NextActionListBean;
import com.example.user.lmsautovista.Model.SearchTrackerListBean;
import com.example.user.lmsautovista.Presenter.TrackerPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Adapter.TrackerSearchListAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class TrackerFragment extends Fragment implements IView.TrackerView{

    View view;
    @BindView(R.id.campaignNameTracker_Spinner)
    Spinner campaignNameTracker_Spinner;

    @BindView(R.id.feedbackStatusTracker_Spinner)
    Spinner feedbackStatusTracker_Spinner;

    @BindView(R.id.nextActionTracker_Spinner)
    Spinner nextActionTracker_Spinner;

    @BindView(R.id.fromDateTracker_TextView)
    TextView fromDateTracker_TextView;

    @BindView(R.id.toDateTracker_TextView)
    TextView toDateTracker_TextView;

    @BindView(R.id.dateTypeTracker_Spinner)
    Spinner dateTypeTracker_Spinner;

    @BindView(R.id.searchTracker_Button)
    Button searchTracker_Button;

    @BindView(R.id.trackerList_listView)
    RecyclerView trackerList_listView;

    @BindView(R.id.resetTracker_Button)
    Button resetTracker_Button;

    @BindView(R.id.trackerSpinner_LinearLayout)
    LinearLayout trackerSpinner_LinearLayout;

    ProgressDialog progressDialog;
    TrackerPresenter trackerPresenter;
    DatePickerDialog datePickerDialog;

    ArrayList<String> nextActionArrayList = new ArrayList<>();
    ArrayList<SearchTrackerListBean.User_Details> dashboardCountList = new ArrayList<SearchTrackerListBean.User_Details>();
    ArrayList<String> campaignNameArrayList = new ArrayList<>();

    String selectedFeedback, selectedFeedbackId, selectedNextAction, selectedNextActionId, selectedCampaignName, selectedCampaignNameId;
    String selectedDateType, selectedDateTypeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tracker, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        trackerList_listView.setVisibility(View.GONE);
        resetTracker_Button.setVisibility(View.GONE);
        trackerSpinner_LinearLayout.setVisibility(View.VISIBLE);

        trackerPresenter= new TrackerPresenter(this);
        trackerPresenter.getCompaniesList(getActivity());
        nextActionArrayList.add("Next Action");
        selectedFeedback = "Feedback";
        trackerPresenter.getNextActionList(getActivity(), selectedFeedback);

        trackerPresenter.getCampaignList(getActivity());

        getDataTypeDetails();
    }

    @OnClick(R.id.fromDateTracker_TextView)
    public void fromDateButton(){
        final Calendar fromdatecalenderObject = Calendar.getInstance();
        int BookedYear = fromdatecalenderObject.get(Calendar.YEAR);
        int BookedMonth = fromdatecalenderObject.get(Calendar.MONTH);
        int BookedDay = fromdatecalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        fromDateTracker_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.toDateTracker_TextView)
    public void toDateButton(){
        final Calendar todatecalenderObject = Calendar.getInstance();
        int toYear = todatecalenderObject.get(Calendar.YEAR);
        int toMonth = todatecalenderObject.get(Calendar.MONTH);
        int toDay = todatecalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        toDateTracker_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, toYear, toMonth, toDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.searchTracker_Button)
    public void searcTrackerButton(){
                        if (getFromDateValidation()) {
                                if (getToDateValidation()) {
                                   if (getDateTypeValidation()) {
                                        progressDialog = new ProgressDialog(getActivity());
                                        progressDialog.setMessage("Loading Leads...");
                                        progressDialog.setIndeterminate(false);
                                        progressDialog.setCancelable(true);
                                        progressDialog.show();
                                        trackerList_listView.setVisibility(View.VISIBLE);
                                        resetTracker_Button.setVisibility(View.VISIBLE);
                                        trackerSpinner_LinearLayout.setVisibility(View.GONE);
                                        searchTracker();
                                  }
                                }
                            }
    }

    @OnClick(R.id.resetTracker_Button)
    public void resetButton(){
        trackerList_listView.setVisibility(View.GONE);
        resetTracker_Button.setVisibility(View.GONE);
        trackerSpinner_LinearLayout.setVisibility(View.VISIBLE);
        clearData();
    }

    private void clearData(){
        fromDateTracker_TextView.setText("");
        toDateTracker_TextView.setText("");
        feedbackStatusTracker_Spinner.setSelection(0);
        nextActionTracker_Spinner.setSelection(0);
        campaignNameTracker_Spinner.setSelection(0);
        dateTypeTracker_Spinner.setSelection(0);
    }

    private boolean getFromDateValidation() {
        if (fromDateTracker_TextView.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Please Select From Date", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean getToDateValidation() {
        if (toDateTracker_TextView.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Please Select To Date", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean getDateTypeValidation() {
        if (selectedDateTypeId.toString().equals("")) {
            Toast.makeText(getActivity(), "Please Select Date Type", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    public void searchTracker(){
        String fromdate = fromDateTracker_TextView.getText().toString();
        String toDate = toDateTracker_TextView.getText().toString();

        trackerPresenter.getSearchTRackerList(getActivity(),selectedCampaignNameId, selectedFeedbackId, selectedNextActionId, selectedDateTypeId,fromdate, toDate );
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @OnItemSelected(R.id.feedbackStatusTracker_Spinner)
    public void companyNameSelected(Spinner spinner, int position)
    {
        selectedFeedback = spinner.getSelectedItem().toString();
        if (selectedFeedback.equals("Feedback")){
            selectedFeedbackId = "";
        }else if (!(selectedFeedback.equals("Feedback"))){
            selectedFeedbackId = selectedFeedback;
        }else {
            selectedFeedbackId = "";
        }
        trackerPresenter.getNextActionList(getActivity(), selectedFeedback);
    }

    @OnItemSelected(R.id.nextActionTracker_Spinner)
    public void nextActionSelected(Spinner spinner, int position)
    {
        selectedNextAction = spinner.getSelectedItem().toString();

        if (selectedNextAction.equals("Next Action")){
            selectedNextActionId = "";
        }else if (!(selectedNextAction.equals("Next Action"))){
            selectedNextActionId = selectedNextAction;
        }else {
            selectedNextActionId = "";
        }
    }

    @OnItemSelected(R.id.campaignNameTracker_Spinner)
    public void campaignNameSelected(Spinner spinner, int position)
    {
        selectedCampaignName = spinner.getSelectedItem().toString();

        if (selectedCampaignName.equals("Campaign Name")){
            selectedCampaignNameId = "";
        }else if (selectedCampaignName.equals("All")){
            selectedCampaignNameId = "All";
        }else{
            selectedCampaignNameId = selectedCampaignName;
        }
    }

    @Override
    public void showFeedbackView(FeedbackListBean jsonObject) {
        ArrayList<String> feedbackArrayList = new ArrayList<>();
        feedbackArrayList.add("Feedback");
        for(int i=0;i<jsonObject.getSelect_feedback().size();i++)
        {
            try {
                feedbackArrayList.add(jsonObject.getSelect_feedback().get(i).getFeedbackStatusName());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, feedbackArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feedbackStatusTracker_Spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void showNextActionView(NextActionListBean jsonObject) {
        nextActionArrayList.clear();
        nextActionArrayList.add("Next Action");
        for(int i=0;i<jsonObject.getSelect_nextaction().size();i++)
        {
            try {
                nextActionArrayList.add(jsonObject.getSelect_nextaction().get(i).getNextActionName());
             }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview,nextActionArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nextActionTracker_Spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void showTrackerListView(SearchTrackerListBean jsonObject) {
        progressDialog.dismiss();
        try {
            dashboardCountList.clear();
            dashboardCountList.addAll(jsonObject.getUser_details());

            TrackerSearchListAdapter dashboardAdapter = new TrackerSearchListAdapter(getActivity(), jsonObject.getUser_details());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            trackerList_listView.setLayoutManager(mLayoutManager);
            trackerList_listView.setItemAnimator(new DefaultItemAnimator());
            trackerList_listView.setAdapter(dashboardAdapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showCampignListView(LeadSourceBean jsonObject) {
        try{
        campaignNameArrayList.clear();
        campaignNameArrayList.add("Campaign Name");
        campaignNameArrayList.add("All");
        for(int i=0;i<jsonObject.getSelect_lead_source().size();i++)
        {
            try {
                campaignNameArrayList.add(jsonObject.getSelect_lead_source().get(i).getLead_source_name());
             }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview,campaignNameArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campaignNameTracker_Spinner.setAdapter(companiesArrayAdapter);
        }catch(Exception e){

        }
    }

    public void getDataTypeDetails(){
        ArrayList<String> referralTypesArrayList = new ArrayList<>();
        referralTypesArrayList.add("Date Type");
        referralTypesArrayList.add("Lead Date");
        referralTypesArrayList.add("CSE Call Date");
        referralTypesArrayList.add("DSE Call Date");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, referralTypesArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateTypeTracker_Spinner.setAdapter(contactArrayAdapter);

        dateTypeTracker_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDateType = (String) parent.getItemAtPosition(position);
           //     selectedDateTypeId = String.valueOf(position);

                if (selectedDateType.equals("Date Type")){
                    selectedDateTypeId = "";
                }else if (selectedDateType.equals("Lead Date")){
                    selectedDateTypeId = "Lead";
                }else if (selectedDateType.equals("CSE Call Date")){
                    selectedDateTypeId = "CSE";
                }else if (selectedDateType.equals("DSE Call Date")){
                    selectedDateTypeId = "DSE";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
