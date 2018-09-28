package com.excell.lms.lmsautovista.View.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.DashboardCountBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Presenter.DashboardPresenter;
import com.excell.lms.lmsautovista.Presenter.LoginPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Activity.ViewMessageActivity;
import com.excell.lms.lmsautovista.View.Adapter.DashboardAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class DashboardFragment extends Fragment implements IView.LoginView{

    View view;
    ProgressDialog progressDialog;
    DashboardPresenter dashboardPresenter;
    LoginPresenter loginPresenter;

    @BindView(R.id.countLocationWise_ListView)
    RecyclerView countLocationWise_ListView;

    @BindView(R.id.locationWiseDashboard_spinner)
    Spinner locationWiseDashboard_spinner;

    @BindView(R.id.viewMessage_textView)
    TextView viewMessage_textView;

    @BindView(R.id.toolbarProcess_spinner)
    Spinner toolbarProcess_spinner;

    @BindView(R.id.toolbarLocation_spinner)
    Spinner toolbarLocation_spinner;

    @BindView(R.id.toolbarProcess_tv)
    TextView toolbarProcess_tv;

    @BindView(R.id.toolbarLocation_tv)
    TextView toolbarLocation_tv;


    ArrayList<DashboardCountBean.Dashboard_Count> dashboardCountList = new ArrayList<DashboardCountBean.Dashboard_Count>();
    Map<String, String> locationMap = new HashMap<>();
    Map<String, String> processMap = new HashMap<>();
    Map<String, String> locationToolMap = new HashMap<>();
    String selectedLocationDashboard, selectedLocationDashboardId = "";
    String selectedProcessDashboard, selectedProcessDashboardId = "";
    String selectedLocToolDashboard, selectedLocToolDashboardId = "";
    String rolestr;

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  =inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        viewMessage_textView.setVisibility(View.GONE);
        toolbarLocation_tv.setVisibility(View.GONE);
        toolbarProcess_tv.setText("Selected Process : " +SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_NAME, ""));
      //  toolbarLocation_tv.setText(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.LOCATION_SESSION, ""));

        dashboardPresenter = new DashboardPresenter(this);
        loginPresenter = new LoginPresenter(this);
        countLocationWise_ListView.setNestedScrollingEnabled(false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        rolestr = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");

        if (NetworkUtilities.isInternet(getActivity())){
            checkRole();
            dashboardPresenter.getDashboardProcessList(getActivity());
            dashboardPresenter.getDashboardHeaderLocationList(getActivity());

            dashboardPresenter.getDashboardLocationList("", getActivity());
        }else{
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkRole(){
        if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("1")){
            viewMessage_textView.setVisibility(View.GONE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("2")){
            viewMessage_textView.setVisibility(View.GONE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")){
            viewMessage_textView.setVisibility(View.GONE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("4")){
            viewMessage_textView.setVisibility(View.VISIBLE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("5")){
            viewMessage_textView.setVisibility(View.VISIBLE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }
    }

    @OnClick(R.id.viewMessage_textView)
    public void messageList(){
        Intent viewMessageIntent = new Intent(getActivity(), ViewMessageActivity.class);
        startActivity(viewMessageIntent);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailure(String message) {

    }

    @OnItemSelected(R.id.toolbarLocation_spinner)
    public void locationToolNameSelected(Spinner spinner, int position)
    {
        selectedLocToolDashboard = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : locationToolMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLocToolDashboard)) {
                selectedLocToolDashboardId = (String) key;
                Log.i("Selected CSE : ",selectedLocToolDashboardId);

                toolbarLocation_tv.setVisibility(View.VISIBLE);
                toolbarLocation_tv.setText("Selected Location : " + selectedLocToolDashboard);
                loginPresenter.saveLocationInfo((String)key, (String) value, SharedPreferenceManager.getInstance(getActivity()));
                // dashboardPresenter.getLocationSpinnerList(selectedLocationId, this);
            }
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }
    }

    @Override
    public void showLocationList(LoginBean jsonObject) {
        try {
            ArrayList<String> locationProArrayList = new ArrayList<>();
            locationProArrayList.clear();
            locationProArrayList.add("Select Location");
            if (jsonObject.getSession_data().get(0).getProcess().size() > 0) {
                for (int i = 0; i < jsonObject.getSession_data().get(0).getProcess().size(); i++) {
                    if (jsonObject.getSession_data().get(0).getProcess().get(i).getProcess_id().equals(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, ""))) {
                        for (int j = 0; j < jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().size(); j++) {
                            try {
                                locationProArrayList.add(jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation());
                                locationToolMap.put(jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation_id(), jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_white_text, locationProArrayList);
            locationArrayAdapter.setDropDownViewResource(R.layout.spinner_checked_textview);
            toolbarLocation_spinner.setAdapter(locationArrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void ShowDashboardCount(DashboardCountBean jsonObject) {
        try {
            dashboardCountList.clear();
            dashboardCountList.addAll(jsonObject.getDashboard_count());

            DashboardAdapter dashboardRepsAdapter = new DashboardAdapter(getActivity(), jsonObject.getDashboard_count());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            countLocationWise_ListView.setLayoutManager(mLayoutManager);
            countLocationWise_ListView.setItemAnimator(new DefaultItemAnimator());
            countLocationWise_ListView.setAdapter(dashboardRepsAdapter);
        }catch (Exception e){
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
            locationWiseDashboard_spinner.setAdapter(locationDashboardArrayAdapter);
        }catch (Exception e){
        }
    }

    @Override
    public void showProcessDashboard(LoginBean jsonObject) {
        try {
            ArrayList<String> processDashboardArrayList = new ArrayList<>();
            processDashboardArrayList.add("Select Process");

            if (jsonObject.getSession_data().get(0).getProcess().size() > 0) {
                for (int i = 0; i < jsonObject.getSession_data().get(0).getProcess().size(); i++) {
                        try {
                            processDashboardArrayList.add(jsonObject.getSession_data().get(0).getProcess().get(i).getProcess_name());
                            processMap.put(jsonObject.getSession_data().get(0).getProcess().get(i).getProcess_id(), jsonObject.getSession_data().get(0).getProcess().get(i).getProcess_name());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
                ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_white_text, processDashboardArrayList);
                locationDashboardArrayAdapter.setDropDownViewResource(R.layout.spinner_checked_textview);
                toolbarProcess_spinner.setAdapter(locationDashboardArrayAdapter);
            }catch(Exception e){
            }
    }

    @Override
    public void showLocationDashboardCount(LocationWiseDashboardCountBean jsonObject) {

    }

    @Override
    public void showLocationwithoutSeelctionCount(LocationWiseDashboardCountBean jsonObject) {

    }

    @OnItemSelected(R.id.toolbarProcess_spinner)
    public void processNameSelected(Spinner spinner, int position)
    {
        selectedProcessDashboard = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : processMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedProcessDashboard)) {
                selectedProcessDashboardId = (String) key;
                Log.i("Selected CSE : ",selectedProcessDashboardId);

                toolbarProcess_tv.setVisibility(View.VISIBLE);
                toolbarProcess_tv.setText("Selected Process : " + selectedProcessDashboard);

                loginPresenter.saveProcessInfo((String)key, (String) value, SharedPreferenceManager.getInstance(getActivity()));
            }
            dashboardPresenter.getDashboardHeaderLocationList(getActivity());
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }
    }

    @OnItemSelected(R.id.locationWiseDashboard_spinner)
    public void locationNameSelected(Spinner spinner, int position)
    {
        selectedLocationDashboard = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if (value.equals(selectedLocationDashboard)) {
                selectedLocationDashboardId = (String) key;
                Log.i("Selected CSE : ", selectedLocationDashboardId);
                if (selectedLocationDashboardId.equals("Select Location")) {
                    dashboardPresenter.getDashboardLocationList("", getActivity());
                }else{
                    dashboardPresenter.getDashboardLocationList(selectedLocationDashboardId, getActivity());
                }
            }
        }
    }

}