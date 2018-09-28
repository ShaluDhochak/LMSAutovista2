package com.excell.lms.lmsautovista.View.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.DSEReportBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Presenter.DSEWiseReportPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Adapter.DsewiseCountAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;


public class DSEReportFragment extends Fragment implements IView.IDsewiseReportView{

    @BindView(R.id.dseWiseList_RelativeLayout)
    RelativeLayout dseWiseList_RelativeLayout;
    @BindView(R.id.dseWiseReport_listView)
    ListView dseWiseReport_listView;
    @BindView(R.id.locationReport_Spinner)
    Spinner locationReport_Spinner;
    @BindView(R.id.fromDateReport_TextView)
    TextView fromDateReport_TextView;
    @BindView(R.id.toDateReport_TextView)
    TextView toDateReport_TextView;
    @BindView(R.id.searchReport_Button)
    Button searchReport_Button;

    String selectedLocation, selectedLocationId;
    DatePickerDialog datePickerDialog;
    Map<String, String> locationMap = new HashMap<>();

    View view;
    DSEWiseReportPresenter dseWiseReportPresenter;
    DsewiseCountAdapter dsewiseCountAdapter;
    ProgressDialog pDialog;
    ArrayList<DSEReportBean.Dsewise_Count> dseWiseList = new ArrayList<DSEReportBean.Dsewise_Count>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dsereport, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        initialiseUI();

    }

    private void initialiseUI(){
        dseWiseReportPresenter = new DSEWiseReportPresenter( this);
        dseWiseReportPresenter.getLocation(getActivity());
        fromDateReport_TextView.setText("");
        toDateReport_TextView.setText("");
        dseWiseList_RelativeLayout.setVisibility(View.GONE);
      //  dseWiseReportPresenter.getSearchViaContactNoList("", "", "", getActivity());
    }

    @Override
    public void showLocation(LocationDashboardBean jsonObject) {
        ArrayList<String> locationArrayList = new ArrayList<>();
        locationArrayList.add("Location");
       /* for(int i=0;i<jsonObject.getDaliy_dse_tracker_location().size();i++)
        {
            try {
                locationArrayList.add(jsonObject.getDaliy_dse_tracker_location().get(i).getLocation());
                locationMap.put(jsonObject.getDaliy_dse_tracker_location().get(i).getLocation_id(), jsonObject.getDaliy_dse_tracker_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        */
        for(int i=0;i<jsonObject.getSelect_location().size();i++)
        {
            try {
                locationArrayList.add(jsonObject.getSelect_location().get(i).getLocation());
                locationMap.put(jsonObject.getSelect_location().get(i).getLocation_id(), jsonObject.getSelect_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, locationArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationReport_Spinner.setAdapter(companiesArrayAdapter);
    }

    //details
    @OnItemSelected(R.id.locationReport_Spinner)
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

    @OnClick(R.id.toDateReport_TextView)
    public void toDateReport(){
        final Calendar todatecalenderObject = Calendar.getInstance();
        int toYear = todatecalenderObject.get(Calendar.YEAR);
        int toMonth = todatecalenderObject.get(Calendar.MONTH);
        int toDay = todatecalenderObject.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        toDateReport_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, toYear, toMonth, toDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.fromDateReport_TextView)
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
                        fromDateReport_TextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, BookedYear, BookedMonth, BookedDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.searchReport_Button)
    public void searchButtonReport(){
        searchDseWise();
    }

    public void searchDseWise(){
        try {
            String fromdate = fromDateReport_TextView.getText().toString();
            String toDate = toDateReport_TextView.getText().toString();
            if (!(selectedLocation.toString().equals("Location"))) {
                if (!(fromDateReport_TextView.getText().toString().equals(""))) {
                    if (!(toDateReport_TextView.getText().toString().equals(""))) {
                        pDialog = new ProgressDialog(getActivity());
                        pDialog.setMessage("Loading ...");
                        pDialog.setCanceledOnTouchOutside(false);
                        pDialog.show();
                        dseWiseList_RelativeLayout.setVisibility(View.VISIBLE);
                        dseWiseReportPresenter.getSearchViaContactNoList(selectedLocationId, fromdate, toDate, getActivity());
                    } else {
                        Toast.makeText(getActivity(), "Please select Date to.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please select Date From.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
        }
     //   dseWiseReportPresenter.getSearchViaContactNoList(selectedLocationId, fromdate, toDate,getActivity());
    }

    @Override
    public void showDseWiseListView(DSEReportBean jsonObject) {
        pDialog.dismiss();
        try {
            if (jsonObject.getDsewise_count().size()>0) {
                dseWiseList.clear();
                dseWiseList.addAll(jsonObject.getDsewise_count());

                dsewiseCountAdapter = new DsewiseCountAdapter(getActivity(), jsonObject.getDsewise_count());
                dseWiseReport_listView.setAdapter(dsewiseCountAdapter);
                dsewiseCountAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(), "No Record Found.", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
        }
    }
}
