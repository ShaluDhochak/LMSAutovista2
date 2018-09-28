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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
import com.excell.lms.lmsautovista.View.Adapter.LocationDashboardAdapter;
import com.excell.lms.lmsautovista.View.Adapter.LocationWiseNonSelectedAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class LocationWiseFragment extends Fragment implements IView.LoginView {
    View view;
    ProgressDialog pDialog;
    DashboardPresenter dashboardPresenter;
    LoginPresenter loginPresenter;

    @BindView(R.id.countLocationWiseLoc_ListView)
    RecyclerView countLocationWiseLoc_ListView;
    @BindView(R.id.locationWiseLocDashboard_spinner)
    Spinner locationWiseLocDashboard_spinner;
    @BindView(R.id.viewMessageLoc_textView)
    TextView viewMessageLoc_textView;
    @BindView(R.id.toolbarProcess_spinner)
    Spinner toolbarProcess_spinner;
    @BindView(R.id.toolbarLocation_spinner)
    Spinner toolbarLocation_spinner;
    @BindView(R.id.toolbarProcess_tv)
    TextView toolbarProcess_tv;
    @BindView(R.id.toolbarLocation_tv)
    TextView toolbarLocation_tv;
    @BindView(R.id.radioBtn)
    RadioGroup radioGrp;
    @BindView(R.id.radio_tl)
    RadioButton radio_tl;
    @BindView(R.id.radio_dse)
    RadioButton radio_dse;
    @BindView(R.id.totalCount_dse)
    RadioButton totalCount_dse;
    //relativelayout for
    @BindView(R.id.leadListLoc2_lineralayout)
    RelativeLayout leadListLoc2_lineralayout;
    @BindView(R.id.leadListLoc_lineralayout)
    RelativeLayout leadListLoc_lineralayout;

    //listview without selection
    @BindView(R.id.countLocationWiseLoc2_ListView)
    RecyclerView countLocationWiseLoc2_ListView;
    //separator
    @BindView(R.id.separatorRightLeadLoc_view)
    TextView separatorRightLeadLoc_view;

    //used as Submit Btn
    @BindView(R.id.LeadFollowUpLocDetails_btn)
    Button LeadFollowUpLocDetails_btn;

    @BindView(R.id.separatorEvaluationCountLoc_view)
    View separatorEvaluationCountLoc_view;
    @BindView(R.id.evaluationCountHeadingLoc_tv)
    TextView evaluationCountHeadingLoc_tv;
    @BindView(R.id.separatorTestDriveLoc_view)
    View separatorTestDriveLoc_view;
    @BindView(R.id.testDriveHeadingLoc_tv)
    TextView testDriveHeadingLoc_tv;
    @BindView(R.id.separatorHomeVisitLoc_view)
    View separatorHomeVisitLoc_view;
    @BindView(R.id.homeVisitHeadingLoc_tv)
    TextView homeVisitHeadingLoc_tv;
    @BindView(R.id.separatorShowroomVisitLoc_view)
    View separatorShowroomVisitLoc_view;
    @BindView(R.id.showroomVisitHeadingLoc_tv)
    TextView showroomVisitHeadingLoc_tv;

    @BindView(R.id.evaluationCountHeadingLoc2_tv)
    TextView evaluationCountHeadingLoc2_tv;
    @BindView(R.id.separatorEvaluationCountLoc2_view)
    View separatorEvaluationCountLoc2_view;
    @BindView(R.id.testDriveHeadingLoc2_tv)
    TextView testDriveHeadingLoc2_tv;
    @BindView(R.id.separatorTestDriveLoc2_view)
    View separatorTestDriveLoc2_view;
    @BindView(R.id.homeVisitHeadingLoc2_tv)
    TextView homeVisitHeadingLoc2_tv;
    @BindView(R.id.separatorHomeVisitLoc2_view)
    View separatorHomeVisitLoc2_view;
    @BindView(R.id.showroomVisitHeadingLoc2_tv)
    TextView showroomVisitHeadingLoc2_tv;
    @BindView(R.id.separatorShowroomVisitLoc2_view)
    View separatorShowroomVisitLoc2_view;

    ArrayList<LocationWiseDashboardCountBean.New_Dashboard> dashboardCountList = new ArrayList<LocationWiseDashboardCountBean.New_Dashboard>();
    Map<String, String> locationMap = new HashMap<>();
    Map<String, String> processMap = new HashMap<>();
    Map<String, String> locationToolMap = new HashMap<>();
    String selectedLocationDashboard, selectedLocationDashboardId = "";
    String selectedProcessDashboard, selectedProcessDashboardId = "";
    String selectedLocToolDashboard, selectedLocToolDashboardId = "";
    String rolestr;
    String selectedRadio = "";
    String role ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_location_wise, container, false);
        view  =inflater.inflate(R.layout.fragment_location_wise, container, false);
        ButterKnife.bind(this, view);



        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        return view;
    }

    @OnClick(R.id.LeadFollowUpLocDetails_btn)
    public void submitBtnClick(){

        role = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");

        if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")){
            selectedRadio = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_NAME, "");
            leadListLoc2_lineralayout.setVisibility(View.GONE);
            leadListLoc_lineralayout.setVisibility(View.VISIBLE);
            if (!(selectedLocationDashboard.equals("Select Location"))) {
                pDialog.show();
                dashboardPresenter.getDashboardCountLocationList(selectedRadio, selectedLocationDashboardId, getActivity());
            }else{
                Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
            }
        }else if(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("4")){
            selectedRadio = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_NAME, "");
            leadListLoc2_lineralayout.setVisibility(View.GONE);
            leadListLoc_lineralayout.setVisibility(View.VISIBLE);
            if (!(selectedLocationDashboard.equals("Select Location"))) {
                pDialog.show();
                dashboardPresenter.getDashboardCountLocationList(selectedRadio, selectedLocationDashboardId, getActivity());
            }else{
                Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
            }
        }else if(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("16")){
            selectedRadio = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_NAME, "");
            leadListLoc2_lineralayout.setVisibility(View.GONE);
            radioGrp.setVisibility(View.GONE);
            leadListLoc_lineralayout.setVisibility(View.VISIBLE);
            if (!(selectedLocationDashboard.equals("Select Location"))) {
                pDialog.show();
                dashboardPresenter.getDashboardCountLocationList(selectedRadio, selectedLocationDashboardId, getActivity());
            }else{
                Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
            }
        }else{
            if (radio_tl.isChecked()) {
                selectedRadio = radio_tl.getText().toString();
                leadListLoc2_lineralayout.setVisibility(View.GONE);
                leadListLoc_lineralayout.setVisibility(View.VISIBLE);
                if (!(selectedLocationDashboard.equals("Select Location"))) {
                    pDialog.show();
                    dashboardPresenter.getDashboardCountLocationList(selectedRadio, selectedLocationDashboardId, getActivity());
                }else{
                    Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
                }
            } else if (radio_dse.isChecked()) {
                selectedRadio = radio_dse.getText().toString();
                if (selectedRadio.equals("Executive")){
                    selectedRadio = "DSE";
                }
                leadListLoc2_lineralayout.setVisibility(View.GONE);
                leadListLoc_lineralayout.setVisibility(View.VISIBLE);
                if (!(selectedLocationDashboard.equals("Select Location"))) {
                    pDialog.show();
                    dashboardPresenter.getDashboardCountLocationList(selectedRadio, selectedLocationDashboardId, getActivity());
                }else{
                    Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
                }
            }else if (totalCount_dse.isChecked()){
                leadListLoc2_lineralayout.setVisibility(View.VISIBLE);
                leadListLoc_lineralayout.setVisibility(View.GONE);
                if (!(selectedLocationDashboard.equals("Select Location"))) {
                    pDialog.show();
                    dashboardPresenter.getDashboardWithoutSelectionDseList(selectedLocationDashboardId, getActivity());
                }else{
                    Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
                }
            }else  if ((selectedLocationDashboard.equals("Select Location"))) {
                Toast.makeText(getActivity(), "Please select Location.", Toast.LENGTH_SHORT).show();
            }else if(!(totalCount_dse.isChecked()) && !(radio_dse.isChecked()) && !(radio_tl.isChecked())){
                Toast.makeText(getActivity(), "Please select anyone of TL, Executive, Total count  checkbox.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        rolestr = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");
        if (NetworkUtilities.isInternet(getActivity())) {
            setVisibility();

            viewMessageLoc_textView.setVisibility(View.GONE);
            toolbarLocation_tv.setVisibility(View.GONE);

            toolbarProcess_tv.setText("Selected Process : " + SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_NAME, ""));
            String toolbarText= SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_NAME, "").toString();
            //  toolbarLocation_tv.setText(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.LOCATION_SESSION, ""));
            setEvaluationVisibility(toolbarText);
            dashboardPresenter = new DashboardPresenter(this);
            loginPresenter = new LoginPresenter(this);
            countLocationWiseLoc_ListView.setNestedScrollingEnabled(false);

            dashboardPresenter.getDashboardProcessList(getActivity());
            dashboardPresenter.getDashboardHeaderLocationList(getActivity());
            dashboardPresenter.getDashboardCountLocationList(selectedRadio,"", getActivity());

            checkRole();
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setVisibility(){
        role = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");

        if (role.equals("15") ){
            separatorEvaluationCountLoc_view.setVisibility(View.GONE);
            evaluationCountHeadingLoc_tv.setVisibility(View.GONE);
            separatorTestDriveLoc_view.setVisibility(View.GONE);
            testDriveHeadingLoc_tv.setVisibility(View.GONE);
            separatorHomeVisitLoc_view.setVisibility(View.GONE);
            homeVisitHeadingLoc_tv.setVisibility(View.GONE);
            separatorShowroomVisitLoc_view.setVisibility(View.GONE);
            showroomVisitHeadingLoc_tv.setVisibility(View.GONE);
            separatorEvaluationCountLoc2_view.setVisibility(View.GONE);
            evaluationCountHeadingLoc2_tv.setVisibility(View.GONE);
            separatorTestDriveLoc2_view.setVisibility(View.GONE);
            testDriveHeadingLoc2_tv.setVisibility(View.GONE);
            separatorHomeVisitLoc2_view.setVisibility(View.GONE);
            homeVisitHeadingLoc2_tv.setVisibility(View.GONE);
            separatorShowroomVisitLoc2_view.setVisibility(View.GONE);
            showroomVisitHeadingLoc2_tv.setVisibility(View.GONE);
        }else if(role.equals("16")) {
            separatorEvaluationCountLoc_view.setVisibility(View.GONE);
            evaluationCountHeadingLoc_tv.setVisibility(View.GONE);
            separatorTestDriveLoc_view.setVisibility(View.GONE);
            testDriveHeadingLoc_tv.setVisibility(View.GONE);
            separatorHomeVisitLoc_view.setVisibility(View.GONE);
            homeVisitHeadingLoc_tv.setVisibility(View.GONE);
            separatorShowroomVisitLoc_view.setVisibility(View.GONE);
            showroomVisitHeadingLoc_tv.setVisibility(View.GONE);

            separatorEvaluationCountLoc2_view.setVisibility(View.GONE);
            evaluationCountHeadingLoc2_tv.setVisibility(View.GONE);
            separatorTestDriveLoc2_view.setVisibility(View.GONE);
            testDriveHeadingLoc2_tv.setVisibility(View.GONE);
            separatorHomeVisitLoc2_view.setVisibility(View.GONE);
            homeVisitHeadingLoc2_tv.setVisibility(View.GONE);
            separatorShowroomVisitLoc2_view.setVisibility(View.GONE);
            showroomVisitHeadingLoc2_tv.setVisibility(View.GONE);
        }else if (role.equals("1")){
            if ((SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, "").equals("8")) ) {
                separatorEvaluationCountLoc_view.setVisibility(View.GONE);
                evaluationCountHeadingLoc_tv.setVisibility(View.GONE);
                separatorTestDriveLoc_view.setVisibility(View.GONE);
                testDriveHeadingLoc_tv.setVisibility(View.GONE);
                separatorHomeVisitLoc_view.setVisibility(View.GONE);
                homeVisitHeadingLoc_tv.setVisibility(View.GONE);
                separatorShowroomVisitLoc_view.setVisibility(View.GONE);
                showroomVisitHeadingLoc_tv.setVisibility(View.GONE);

                separatorEvaluationCountLoc2_view.setVisibility(View.GONE);
                evaluationCountHeadingLoc2_tv.setVisibility(View.GONE);
                separatorTestDriveLoc2_view.setVisibility(View.GONE);
                testDriveHeadingLoc2_tv.setVisibility(View.GONE);
                separatorHomeVisitLoc2_view.setVisibility(View.GONE);
                homeVisitHeadingLoc2_tv.setVisibility(View.GONE);
                separatorShowroomVisitLoc2_view.setVisibility(View.GONE);
                showroomVisitHeadingLoc2_tv.setVisibility(View.GONE);
            }else{
                separatorEvaluationCountLoc2_view.setVisibility(View.VISIBLE);
                evaluationCountHeadingLoc2_tv.setVisibility(View.VISIBLE);
                separatorTestDriveLoc2_view.setVisibility(View.VISIBLE);
                testDriveHeadingLoc2_tv.setVisibility(View.VISIBLE);
                separatorHomeVisitLoc2_view.setVisibility(View.VISIBLE);
                homeVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);
                separatorShowroomVisitLoc2_view.setVisibility(View.VISIBLE);
                showroomVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);
                separatorEvaluationCountLoc_view.setVisibility(View.VISIBLE);
                evaluationCountHeadingLoc_tv.setVisibility(View.VISIBLE);
                separatorTestDriveLoc_view.setVisibility(View.VISIBLE);
                testDriveHeadingLoc_tv.setVisibility(View.VISIBLE);
                separatorHomeVisitLoc_view.setVisibility(View.VISIBLE);
                homeVisitHeadingLoc_tv.setVisibility(View.VISIBLE);
                separatorShowroomVisitLoc_view.setVisibility(View.VISIBLE);
                showroomVisitHeadingLoc_tv.setVisibility(View.VISIBLE);
            }
        }else if (role.equals("2")){
            if ((SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, "").equals("8")) ) {
                separatorEvaluationCountLoc_view.setVisibility(View.GONE);
                evaluationCountHeadingLoc_tv.setVisibility(View.GONE);
                separatorTestDriveLoc_view.setVisibility(View.GONE);
                testDriveHeadingLoc_tv.setVisibility(View.GONE);
                separatorHomeVisitLoc_view.setVisibility(View.GONE);
                homeVisitHeadingLoc_tv.setVisibility(View.GONE);
                separatorShowroomVisitLoc_view.setVisibility(View.GONE);
                showroomVisitHeadingLoc_tv.setVisibility(View.GONE);

                separatorEvaluationCountLoc2_view.setVisibility(View.GONE);
                evaluationCountHeadingLoc2_tv.setVisibility(View.GONE);
                separatorTestDriveLoc2_view.setVisibility(View.GONE);
                testDriveHeadingLoc2_tv.setVisibility(View.GONE);
                separatorHomeVisitLoc2_view.setVisibility(View.GONE);
                homeVisitHeadingLoc2_tv.setVisibility(View.GONE);
                separatorShowroomVisitLoc2_view.setVisibility(View.GONE);
                showroomVisitHeadingLoc2_tv.setVisibility(View.GONE);
            }else{
                separatorEvaluationCountLoc2_view.setVisibility(View.VISIBLE);
                evaluationCountHeadingLoc2_tv.setVisibility(View.VISIBLE);
                separatorTestDriveLoc2_view.setVisibility(View.VISIBLE);
                testDriveHeadingLoc2_tv.setVisibility(View.VISIBLE);
                separatorHomeVisitLoc2_view.setVisibility(View.VISIBLE);
                homeVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);
                separatorShowroomVisitLoc2_view.setVisibility(View.VISIBLE);
                showroomVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);

                separatorEvaluationCountLoc_view.setVisibility(View.VISIBLE);
                evaluationCountHeadingLoc_tv.setVisibility(View.VISIBLE);
                separatorTestDriveLoc_view.setVisibility(View.VISIBLE);
                testDriveHeadingLoc_tv.setVisibility(View.VISIBLE);
                separatorHomeVisitLoc_view.setVisibility(View.VISIBLE);
                homeVisitHeadingLoc_tv.setVisibility(View.VISIBLE);
                separatorShowroomVisitLoc_view.setVisibility(View.VISIBLE);
                showroomVisitHeadingLoc_tv.setVisibility(View.VISIBLE);
            }
        }else{
            separatorEvaluationCountLoc2_view.setVisibility(View.VISIBLE);
            evaluationCountHeadingLoc2_tv.setVisibility(View.VISIBLE);
            separatorTestDriveLoc2_view.setVisibility(View.VISIBLE);
            testDriveHeadingLoc2_tv.setVisibility(View.VISIBLE);
            separatorHomeVisitLoc2_view.setVisibility(View.VISIBLE);
            homeVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);
            separatorShowroomVisitLoc2_view.setVisibility(View.VISIBLE);
            showroomVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);
        }

    }

    private void checkRole(){
        if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("1")){
            viewMessageLoc_textView.setVisibility(View.GONE);
            radioGrp.setVisibility(View.VISIBLE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("2")){
            viewMessageLoc_textView.setVisibility(View.GONE);
            radioGrp.setVisibility(View.VISIBLE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")){
            viewMessageLoc_textView.setVisibility(View.GONE);
            radioGrp.setVisibility(View.GONE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("4")){
            viewMessageLoc_textView.setVisibility(View.VISIBLE);
            radioGrp.setVisibility(View.GONE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("5")){
            viewMessageLoc_textView.setVisibility(View.VISIBLE);
            radioGrp.setVisibility(View.VISIBLE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("15")){
            viewMessageLoc_textView.setVisibility(View.GONE);
            radioGrp.setVisibility(View.VISIBLE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("16")){
            viewMessageLoc_textView.setVisibility(View.GONE);
            radioGrp.setVisibility(View.GONE);
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }
    }

    @OnClick(R.id.viewMessageLoc_textView)
    public void messageList(){
        Intent viewMessageIntent = new Intent(getActivity(), ViewMessageActivity.class);
        startActivity(viewMessageIntent);
    }
    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailure(String message) {

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
            locationWiseLocDashboard_spinner.setAdapter(locationDashboardArrayAdapter);
            if (locationObject.getSelect_location().size() == 1){
                locationWiseLocDashboard_spinner.setSelection(1);
            }
        }catch (Exception e){
        }
    }

    @OnItemSelected(R.id.locationWiseLocDashboard_spinner)
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
            if (jsonObject.getSession_data().get(0).getProcess().size() == 1){
                toolbarProcess_spinner.setSelection(1);
            }
        }catch(Exception e){
        }
    }

    @Override
    public void showLocationDashboardCount(LocationWiseDashboardCountBean jsonObject) {
        pDialog.dismiss();
       try {
           dashboardCountList.clear();
           dashboardCountList.addAll(jsonObject.getNew_dashboard());
           LocationDashboardAdapter dashboardRepsAdapter = new LocationDashboardAdapter(getActivity(), jsonObject.getNew_dashboard());
           RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
           countLocationWiseLoc_ListView.setLayoutManager(mLayoutManager);
           countLocationWiseLoc_ListView.setItemAnimator(new DefaultItemAnimator());
           countLocationWiseLoc_ListView.setAdapter(dashboardRepsAdapter);
       }catch (Exception e){
       }
    }

    @Override
    public void showLocationwithoutSeelctionCount(LocationWiseDashboardCountBean jsonObject) {
        pDialog.dismiss();
        try {
            dashboardCountList.clear();
            dashboardCountList.addAll(jsonObject.getNew_dashboard());
            LocationWiseNonSelectedAdapter dashboardRepsAdapter = new LocationWiseNonSelectedAdapter(getActivity(), jsonObject.getNew_dashboard());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            countLocationWiseLoc2_ListView.setLayoutManager(mLayoutManager);
            countLocationWiseLoc2_ListView.setItemAnimator(new DefaultItemAnimator());
            countLocationWiseLoc2_ListView.setAdapter(dashboardRepsAdapter);
        }catch (Exception e){
        }
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
                Log.i("Selected Process : ",selectedProcessDashboardId);
                toolbarProcess_tv.setVisibility(View.VISIBLE);
                toolbarProcess_tv.setText("Selected Process : " + selectedProcessDashboard);
                setEvaluationVisibility(selectedProcessDashboard);
                loginPresenter.saveProcessInfo((String)key, (String) value, SharedPreferenceManager.getInstance(getActivity()));
            }
            dashboardPresenter.getDashboardHeaderLocationList(getActivity());
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());

        }
    }

    private void setEvaluationVisibility(String selectedProcessDashboard){
        if (selectedProcessDashboard.equals("Evaluation")){
            separatorEvaluationCountLoc_view.setVisibility(View.GONE);
            evaluationCountHeadingLoc_tv.setVisibility(View.GONE);
            separatorTestDriveLoc_view.setVisibility(View.GONE);
            testDriveHeadingLoc_tv.setVisibility(View.GONE);
            separatorHomeVisitLoc_view.setVisibility(View.GONE);
            homeVisitHeadingLoc_tv.setVisibility(View.GONE);
            separatorShowroomVisitLoc_view.setVisibility(View.GONE);
            showroomVisitHeadingLoc_tv.setVisibility(View.GONE);

            separatorEvaluationCountLoc2_view.setVisibility(View.GONE);
            evaluationCountHeadingLoc2_tv.setVisibility(View.GONE);
            separatorTestDriveLoc2_view.setVisibility(View.GONE);
            testDriveHeadingLoc2_tv.setVisibility(View.GONE);
            separatorHomeVisitLoc2_view.setVisibility(View.GONE);
            homeVisitHeadingLoc2_tv.setVisibility(View.GONE);
            separatorShowroomVisitLoc2_view.setVisibility(View.GONE);
            showroomVisitHeadingLoc2_tv.setVisibility(View.GONE);
        }else{
            separatorEvaluationCountLoc_view.setVisibility(View.VISIBLE);
            evaluationCountHeadingLoc_tv.setVisibility(View.VISIBLE);
            separatorTestDriveLoc_view.setVisibility(View.VISIBLE);
            testDriveHeadingLoc_tv.setVisibility(View.VISIBLE);
            separatorHomeVisitLoc_view.setVisibility(View.VISIBLE);
            homeVisitHeadingLoc_tv.setVisibility(View.VISIBLE);
            separatorShowroomVisitLoc_view.setVisibility(View.VISIBLE);
            showroomVisitHeadingLoc_tv.setVisibility(View.VISIBLE);

            separatorEvaluationCountLoc2_view.setVisibility(View.VISIBLE);
            evaluationCountHeadingLoc2_tv.setVisibility(View.VISIBLE);
            separatorTestDriveLoc2_view.setVisibility(View.VISIBLE);
            testDriveHeadingLoc2_tv.setVisibility(View.VISIBLE);
            separatorHomeVisitLoc2_view.setVisibility(View.VISIBLE);
            homeVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);
            separatorShowroomVisitLoc2_view.setVisibility(View.VISIBLE);
            showroomVisitHeadingLoc2_tv.setVisibility(View.VISIBLE);
        }
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
            }
            dashboardPresenter.getLocationSpinnerList(selectedLocToolDashboardId,selectedProcessDashboardId,getActivity());
        }
    }

}
