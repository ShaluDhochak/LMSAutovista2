package com.example.user.lmsautovista.View.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.AssignLocationBean;
import com.example.user.lmsautovista.Model.AssignNewLeadAssignUserBean;
import com.example.user.lmsautovista.Model.AssignNewLeadCampaignBean;
import com.example.user.lmsautovista.Presenter.AssignNewLeadPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.GSONRequest;
import com.example.user.lmsautovista.Utils.Utilities;
import com.example.user.lmsautovista.View.Adapter.AssignNewLeadCampaignAdapter;
import com.example.user.lmsautovista.View.Adapter.CseNameAdapter;
import com.example.user.lmsautovista.View.IView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AssignNewLeadFragment extends Fragment implements IView.IAssignNewLeadView{

    @BindView(R.id.locationAssignNew_spinner)
    Spinner locationAssignNew_spinner;

    @BindView(R.id.cseName_TextView)
    TextView cseName_TextView;
    @BindView(R.id.addCampaignHeading_TextView)
    TextView addCampaignHeading_TextView;
    @BindView(R.id.totalCampaignCount_TextView)
    TextView totalCampaignCount_TextView;

    @BindView(R.id.addCSENameList_ListView)
    ListView addCSENameList_ListView;
    @BindView(R.id.campaignNameLeadSourceList_ListView)
    ListView campaignNameLeadSourceList_ListView;

    @BindView(R.id.submitAssignLead_button)
    Button submitAssignLead_button;
    @BindView(R.id.resetAssignLead_button)
    Button resetAssignLead_button;

    /* EditText for campaign count */
    @BindView(R.id.enterCampaignCount_EditText)
    EditText enterCampaignCount_EditText;

    SharedPreferences pref;
    String userStr, processIdPref, processNamePref;
    String selectedLocation, selectedLocationId = "";
    View view;

    ArrayList<String> locationAssignArrayList = new ArrayList<>();
    ArrayList<AssignNewLeadCampaignBean.Assign_New_Leads_Source> leadSourceCountArrayList = new ArrayList<>();

    Map<String, String> locationMap = new HashMap<>();

    CseNameAdapter adapter;
    AssignNewLeadCampaignAdapter leadSourceAdapter;
    AssignNewLeadPresenter assignNewLeadPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_assign_new_lead, container, false);
        ButterKnife.bind(this, view);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        initialiseUI();
    }

    private void initialiseUI(){
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userStr = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");
        processIdPref = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, "");
        processNamePref = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_NAME, "");

        assignNewLeadPresenter = new AssignNewLeadPresenter(this);
        assignNewLeadPresenter.getAssignLocation(getActivity());
        assignNewLeadPresenter.getCamapignList(getActivity());
        assignNewLeadPresenter.getAssignToList(selectedLocationId, getActivity());
    }


    @Override
    public void showAssignToLocation(AssignLocationBean jsonObject) {
        locationAssignArrayList.clear();
        locationAssignArrayList.add("Location");
        for(int i=0;i<jsonObject.getProcess_all_location().size();i++)
        {
            try {
                locationAssignArrayList.add(jsonObject.getProcess_all_location().get(i).getLocation());
                locationMap.put(jsonObject.getProcess_all_location().get(i).getLocation_id(), jsonObject.getProcess_all_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, locationAssignArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationAssignNew_spinner.setAdapter(companiesArrayAdapter);

    }
    //details
    @OnItemSelected(R.id.locationAssignNew_spinner)
    public void locationSelected(Spinner spinner, int position)
    {
        selectedLocation = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLocation)) {
                selectedLocationId = (String) key;
                Log.i("Selected CSE : ",selectedLocationId);

            }else if (selectedLocation.equals("Location")){
                selectedLocationId = "";
                addCSENameList_ListView.setVisibility(View.GONE);
            }
        }
        assignNewLeadPresenter.getAssignToList(selectedLocationId, getActivity());

    }

    @Override
    public void showAssignToView(AssignNewLeadAssignUserBean jsonObject) {
        if (jsonObject.getAssign_new_lead_assign_user().size()>0) {
            addCSENameList_ListView.clearTextFilter();
            addCSENameList_ListView.setVisibility(View.VISIBLE);
            adapter = new CseNameAdapter(getActivity(), jsonObject.getAssign_new_lead_assign_user());
            addCSENameList_ListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
          //  Toast.makeText(getActivity(), "No Record Found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCampaignListView(AssignNewLeadCampaignBean jsonObject) {
        leadSourceCountArrayList.clear();
        leadSourceCountArrayList.addAll(jsonObject.getAssign_new_leads_source());

        if (jsonObject.getAssign_new_leads_all_count().size()>0){
            totalCampaignCount_TextView.setText("Total Count : " +jsonObject.getAssign_new_leads_all_count().get(0).getAcount());
        }else{
            Toast.makeText(getActivity(), "No Record Found for Total count", Toast.LENGTH_SHORT).show();
        }
        if (jsonObject.getAssign_new_leads_source().size() > 0) {
            leadSourceAdapter = new AssignNewLeadCampaignAdapter(getActivity(), leadSourceCountArrayList);
            campaignNameLeadSourceList_ListView.setAdapter(leadSourceAdapter);
            leadSourceAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "No Record Found for lead source count lead", Toast.LENGTH_SHORT).show();
        }

    }
    /*
        addCampaignHeading_TextView.setOnClickListener(this);
        submitAssignLead__button.setOnClickListener(this);
        resetAssignLead_button.setOnClickListener(this);
     */


    @OnClick(R.id.submitAssignLead_button)
    public void submitAssignLead(){
        if (!selectedLocation.equals("Location")) {
            if (adapter.getSelectedCSE().length() > 0) {
                if (leadSourceAdapter.getSelectedCampaign().size() > 0) {
                    if (enterCampaignCount_EditText.getText().toString().length() > 0) {
                        getSubmitData();
                    } else
                        Toast.makeText(getActivity(), "Please Enter Campaign Count", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "Select Campaign", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getActivity(), "Select CSE Name", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getActivity(), "Select Location", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.resetAssignLead_button)
    public void resetAssignLead(){
        clearAllData();
    }

    private void clearAllData(){
        locationAssignNew_spinner.setSelection(0);
        assignNewLeadPresenter.getAssignLocation(getActivity());
        assignNewLeadPresenter.getCamapignList(getActivity());
        assignNewLeadPresenter.getAssignToList("", getActivity());
        addCSENameList_ListView.setVisibility(View.GONE);
        enterCampaignCount_EditText.setText("0");
    }

    private void getSubmitData(){
        try {
            JSONArray cseJSONArray;

            String lead_count1 = enterCampaignCount_EditText.getText().toString();
            cseJSONArray = adapter.getSelectedCSE();

            HashMap<String, String> campaignHashMap = leadSourceAdapter.getSelectedCampaign();
            Log.i("Length :", cseJSONArray.toString());

            Map<String, String> assignLeadHashMap = new HashMap<>();
            assignLeadHashMap.put("cse_name", cseJSONArray.toString());
            assignLeadHashMap.put("leads1", campaignHashMap.get("campaignName"));
            assignLeadHashMap.put("lead_count1", lead_count1);
            assignLeadHashMap.put("web_count",campaignHashMap.get("campaignCount"));
            assignLeadHashMap.put("user_id", userStr);
            assignLeadHashMap.put("process_id", processIdPref);
            assignLeadHashMap.put("process_name", processNamePref);
            String url = Constants.BASE_URL + Constants.ASSIGN_SUBMIT;
            GSONRequest<AssignBean> gsonRequest = new GSONRequest<AssignBean>(
                    Request.Method.POST, url, AssignBean.class, assignLeadHashMap,
                    new Response.Listener<AssignBean>() {
                        @Override
                        public void onResponse(AssignBean response) {

                            Toast.makeText(getActivity(), response.message, Toast.LENGTH_SHORT).show();
                            clearAllData();
                            Log.i("Response : ", response.getSuccess());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), "server Error Here.", Toast.LENGTH_SHORT).show();
                    Log.i("Response : ", error.getMessage());
                }
            }
            );
            Utilities.getRequestQueue(getActivity()).add(gsonRequest);
        }catch (Exception e){
            Toast.makeText(getActivity(), "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show();
        }
    }

    private class AssignBean {
        String success, message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSuccess() { return success; }

        public void setSuccess(String success) {
            this.success = success;
        }
    }


}
