package com.excell.lms.lmsautovista.View.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.DSEDailyReportLocationBean;
import com.excell.lms.lmsautovista.Model.DSEDailyReportViewBean;
import com.excell.lms.lmsautovista.Presenter.DSEDailyReportViewPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.View.Adapter.DSEDailyReportViewAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;


public class DseDailyReportViewFragment extends Fragment implements IView.DSEDailyView{

    @BindView(R.id.countDSEDailyReport_ListView)
    RecyclerView countDSEDailyReport_ListView;
    @BindView(R.id.dseDailyReportSearch_btn)
    Button dseDailyReportSearch_btn;
    @BindView(R.id.locationDseDailyView_spinner)
    Spinner locationDseDailyView_spinner;
    @BindView(R.id.selectDseDailyDashboard_spinner)
    Spinner selectDseDailyDashboard_spinner;
    @BindView(R.id.dateSelectionDSEDailyReport_txtview)
    EditText dateSelectionDSEDailyReport_txtview;

    View view;
    SharedPreferences pref;
    String locationPref, rolePref, userIdPref, process;
    DatePickerDialog datePickerDialog;
    ProgressDialog pDialog;
    DSEDailyReportViewPresenter dseDailyReportViewPresenter;

    ArrayList<String> locationArrayList = new ArrayList<>();

    String selectedLocationId, selectedLocation, dateTypeDSETrackerString;

    ArrayList<String> location;
    Map<String,String> locationMap = new HashMap<>();

    //ARRAYLIST FOR COUNT LISTVIEW
    ArrayList<DSEDailyReportViewBean.Daily_Tracker_Show_Data> countDseReportList = new ArrayList<>();
    DSEDailyReportViewAdapter dseDailyReportViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dse_daily_report_view, container, false);
        ButterKnife.bind(this, view);
        initialiseUI();
        return view;
    }

    private void initialiseUI(){
        pref= PreferenceManager.getDefaultSharedPreferences(getActivity());
        userIdPref = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_ID, "");
        locationPref = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.LOCATION_SESSION, "");
        rolePref = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");
        process = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, "");

        dseDailyReportViewPresenter = new DSEDailyReportViewPresenter(this);

        dseDailyReportViewPresenter.getLocation(process, getActivity());
        setSpinnerData();
    }

    private void setSpinnerData(){
        //Customer Details- Date Type
        List<String> dateTypeSpinner = new ArrayList<String>();
        dateTypeSpinner.add("Status");
        dateTypeSpinner.add("All");
        dateTypeSpinner.add("Latest");

        ArrayAdapter<String> days60BookingdataAdapter;
        days60BookingdataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnertextview, dateTypeSpinner);
        days60BookingdataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        selectDseDailyDashboard_spinner.setAdapter(days60BookingdataAdapter);

        selectDseDailyDashboard_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                dateTypeDSETrackerString = (String) parent.getItemAtPosition(position);
                if(dateTypeDSETrackerString.equals("Status")) {
                    dateTypeDSETrackerString = "";
                }else if (dateTypeDSETrackerString.equals("All")){
                    dateTypeDSETrackerString = "All";
                }else if (dateTypeDSETrackerString.equals("Latest")){
                    dateTypeDSETrackerString = "Latest";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @OnClick(R.id.dseDailyReportSearch_btn)
    public void dseDailyReportSearch(){
        String fromdate = dateSelectionDSEDailyReport_txtview.getText().toString();
        dseDailyReportViewPresenter.getSearchViaContactNoList(dateTypeDSETrackerString,selectedLocationId, fromdate, getActivity());
    }

    @OnClick(R.id.dateSelectionDSEDailyReport_txtview)
    public void dateSelectionDse(){
        final Calendar dseDailyReportcalenderObject = Calendar.getInstance();
        int BookedYear = dseDailyReportcalenderObject.get(Calendar.YEAR);
        int BookedMonth = dseDailyReportcalenderObject.get(Calendar.MONTH);
        int BookedDay = dseDailyReportcalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateSelectionDSEDailyReport_txtview.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }

    @Override
    public void showLocation(DSEDailyReportLocationBean jsonObject) {
        locationArrayList.clear();
        locationArrayList.add("Location");
        for(int i=0;i<jsonObject.getDaliy_dse_tracker_location().size();i++)
        {
            try {
                locationArrayList.add(jsonObject.getDaliy_dse_tracker_location().get(i).getLocation());
                locationMap.put(jsonObject.getDaliy_dse_tracker_location().get(i).getLocation_id(),jsonObject.getDaliy_dse_tracker_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, locationArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationDseDailyView_spinner.setAdapter(companiesArrayAdapter);
    }

    @Override
    public void ShowDseDailyReportView(DSEDailyReportViewBean jsonObject) {
        try{
        countDseReportList.clear();
        countDseReportList.addAll(jsonObject.getDaily_tracker_show_data());

        dseDailyReportViewAdapter = new DSEDailyReportViewAdapter(getActivity(),jsonObject.getDaily_tracker_show_data());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        countDSEDailyReport_ListView.setLayoutManager(mLayoutManager);
        countDSEDailyReport_ListView.setItemAnimator(new DefaultItemAnimator());
        countDSEDailyReport_ListView.setAdapter(dseDailyReportViewAdapter);
        }catch(Exception e){

        }
    }

    @OnItemSelected(R.id.locationDseDailyView_spinner)
    public void locationSelected(Spinner spinner, int position){
        selectedLocation = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLocation)) {
                selectedLocationId = (String) key;
                Log.i("Selected CSE : ",selectedLocationId);
            }else if (selectedLocation.equals("Location")){
                selectedLocationId = "";
                selectedLocation = "";
            }
        }
    }


}
