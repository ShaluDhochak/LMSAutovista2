package com.example.user.lmsautovista.View.Fragment;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Model.LocationDashboardBean;
import com.example.user.lmsautovista.Model.LoginBean;
import com.example.user.lmsautovista.Presenter.DashboardPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.NetworkUtilities;
import com.example.user.lmsautovista.View.Adapter.DashboardAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class DashboardFragment extends Fragment implements IView.LoginView{

    View view;
    ProgressDialog progressDialog;
    DashboardPresenter dashboardPresenter;

    @BindView(R.id.countLocationWise_ListView)
    RecyclerView countLocationWise_ListView;

    @BindView(R.id.locationWiseDashboard_spinner)
    Spinner locationWiseDashboard_spinner;

    ArrayList<DashboardCountBean.Dashboard_Count> dashboardCountList = new ArrayList<DashboardCountBean.Dashboard_Count>();
    Map<String, String> locationMap = new HashMap<>();
    String selectedLocationDashboard, selectedLocationDashboardId;

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  =inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        dashboardPresenter = new DashboardPresenter(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtilities.isInternet(getActivity())) {
            dashboardPresenter.getLocationSpinnerList(getActivity());
            dashboardPresenter.getDashboardLocationList(selectedLocationDashboardId, getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void showLocationList(LoginBean jsonObject) {

    }

    @Override
    public void ShowDashboardCount(DashboardCountBean jsonObject) {
        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getDashboard_count());

        DashboardAdapter dashboardRepsAdapter = new DashboardAdapter(getActivity(), jsonObject.getDashboard_count());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        countLocationWise_ListView.setLayoutManager(mLayoutManager);
        countLocationWise_ListView.setItemAnimator(new DefaultItemAnimator());
        countLocationWise_ListView.setAdapter(dashboardRepsAdapter);
    }

    @Override
    public void showLocationDashboard(LocationDashboardBean locationObject) {
        try {
            ArrayList<String> locationDashboardArrayList = new ArrayList<>();
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

    @OnItemSelected(R.id.locationWiseDashboard_spinner)
    public void locationNameSelected(Spinner spinner, int position)
    {
        selectedLocationDashboard = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLocationDashboard)) {
                selectedLocationDashboardId = (String) key;
                Log.i("Selected CSE : ",selectedLocationDashboardId);
             //   loginPresenter.saveLocationInfo((String)key, (String) value, SharedPreferenceManager.getInstance(this));
            }
            dashboardPresenter.getDashboardLocationList(selectedLocationDashboardId, getActivity());

        }

    }

}