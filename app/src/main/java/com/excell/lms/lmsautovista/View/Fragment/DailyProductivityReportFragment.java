package com.excell.lms.lmsautovista.View.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.DailyProductivityReportBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Presenter.DailyProductivityReportPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.DailyProductivityReportAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class DailyProductivityReportFragment extends Fragment  implements IView.DailyProductivityReportPresnter {

    View view;
    ProgressDialog progressDialog;
    DailyProductivityReportPresenter dashboardPresenter;

    @BindView(R.id.countDailyProdReport_ListView)
    RecyclerView countDailyProdReport_ListView;

    @BindView(R.id.locationWiseDailyProdReport_spinner)
    Spinner locationWiseDailyProdReport_spinner;

    @BindView(R.id.DailyProdReport_radioBtn)
    RadioGroup DailyProdReport_radioBtn;

    @BindView(R.id.radioDailyProdReport_tl)
    RadioButton radioDailyProdReport_tl;

    @BindView(R.id.radioDailyProdReport_dse)
    RadioButton radioDailyProdReport_dse;

    //used as Submit Btn
    @BindView(R.id.SubmitDailyProdReport_btn)
    Button SubmitDailyProdReport_btn;

    ArrayList<DailyProductivityReportBean.Daily_Productivity_resport> CountList = new ArrayList<DailyProductivityReportBean.Daily_Productivity_resport>();
    Map<String, String> locationMap = new HashMap<>();
    String selectedLocationDashboard, selectedLocationDashboardId = "";
    String rolestr;

    String selectedRadio;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_daily_productivity_report, container, false);
        ButterKnife.bind(this, view);

        dashboardPresenter = new DailyProductivityReportPresenter(this);
        countDailyProdReport_ListView.setNestedScrollingEnabled(false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        // Inflate the layout for this fragment
        return view;

    }

    @OnClick(R.id.SubmitDailyProdReport_btn)
    public void submitBtnClick(){
        submitBtn();
    }

    private void submitBtn(){
        SubmitDailyProdReport_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioDailyProdReport_tl.isChecked()) {
                    selectedRadio = radioDailyProdReport_tl.getText().toString();
                } else if (radioDailyProdReport_dse.isChecked()) {
                    selectedRadio = radioDailyProdReport_dse.getText().toString();
                }

                if (!(selectedLocationDashboard.equals("Select Location"))) {
                    dashboardPresenter.getDailyProductivityReportList(selectedRadio, selectedLocationDashboardId, getActivity());
                }else{
                    Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(getActivity(), selectedRadio, Toast.LENGTH_LONG).show(); // print the value of selected super star
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        rolestr = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");

        if (NetworkUtilities.isInternet(getActivity())) {
            dashboardPresenter.getLocationSpinnerList(getActivity());

            dashboardPresenter.getDailyProductivityReportList(selectedRadio,"", getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showLocationDashboard(LocationDashboardBean locationObject) {
        try {
            ArrayList<String> locationDashboardArrayList = new ArrayList<>();
            locationDashboardArrayList.clear();
            locationDashboardArrayList.add("Select Location");
            if (locationObject.getSelect_location().size() > 0) {
                for (int i = 0; i < locationObject.getSelect_location().size(); i++) {
                    try {
                        locationDashboardArrayList.add(locationObject.getSelect_location().get(i).getLocation());
                        locationMap.put(locationObject.getSelect_location().get(i).getLocation_id(), locationObject.getSelect_location().get(i).getLocation());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locationDashboardArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            locationWiseDailyProdReport_spinner.setAdapter(locationDashboardArrayAdapter);
        }catch (Exception e){
        }
    }

    @Override
    public void showDashboardCount(DailyProductivityReportBean jsonObject) {
        try {
            CountList.clear();
            CountList.addAll(jsonObject.getDaily_productivity_report());

            DailyProductivityReportAdapter dashboardRepsAdapter = new DailyProductivityReportAdapter(getActivity(), jsonObject.getDaily_productivity_report());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL , false);
            countDailyProdReport_ListView.setLayoutManager(mLayoutManager);
            countDailyProdReport_ListView.setItemAnimator(new DefaultItemAnimator());
            countDailyProdReport_ListView.setAdapter(dashboardRepsAdapter);
        }catch (Exception e){

        }
    }

    @OnItemSelected(R.id.locationWiseDailyProdReport_spinner)
    public void locationNameSelected(Spinner spinner, int position)
    {
        selectedLocationDashboard = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (value.equals(selectedLocationDashboard)) {
                selectedLocationDashboardId = (String) key;
                Log.i("Selected CSE : ", selectedLocationDashboardId);
                if (selectedLocationDashboard.equals("Select Location")) {
                    //     dashboardPresenter.getDashboardLocationList("", getActivity());
                }else{
                    //   dashboardPresenter.getDashboardLocationList(selectedLocationDashboardId, getActivity());
                }
            }

        }
    }

}
