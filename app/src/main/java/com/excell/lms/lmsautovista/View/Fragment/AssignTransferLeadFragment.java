package com.excell.lms.lmsautovista.View.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.AssignToUserBean;
import com.excell.lms.lmsautovista.Model.AssignTransferLocationBean;
import com.excell.lms.lmsautovista.Model.AssignTransferredCampignListBean;
import com.excell.lms.lmsautovista.Model.FromUserAssignTransferBean;
import com.excell.lms.lmsautovista.Presenter.AssignTransferredLeadPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.Adapter.AssignToCseListAdapter;
import com.excell.lms.lmsautovista.View.Adapter.AssignTransferUserToAdapter;
import com.excell.lms.lmsautovista.View.Adapter.CampaignAssignTransferAdapter;
import com.excell.lms.lmsautovista.View.IView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AssignTransferLeadFragment extends Fragment implements IView.IAssignTransferredLeadView{
    View view;
    private ProgressDialog pDialog;

    @BindView(R.id.fromAssignTransferredLead_textView)
    TextView fromAssignTransferredLead_textView;
    @BindView(R.id.toAssignTransferredLead_textView)
    TextView toAssignTransferredLead_textView;

    //layout for from assign
    @BindView(R.id.leadLocationAssignFromTransfer_ll)
    LinearLayout leadLocationAssignFromTransfer_ll;
    @BindView(R.id.leadLocationAssignToTransfer_ll)
    LinearLayout leadLocationAssignToTransfer_ll;

    //layout for from location
    @BindView(R.id.locationAssignFromTransfer_spinner)
    Spinner locationAssignFromTransfer_spinner;
    @BindView(R.id.fromUserAssignTransfer_spinner)
    Spinner fromUserAssignTransfer_spinner;

    //layout for TO Location
    @BindView(R.id.toLocationAssignTransfer_spinner)
    Spinner toLocationAssignTransfer_spinner;
    @BindView(R.id.addCSENameToLocationList_ListView)
    RecyclerView addCSENameToLocationList_ListView;

    //recycle view for campaign List
    @BindView(R.id.campaignNameLeadSourceList_ListView)
    RecyclerView campaignNameLeadSourceList_ListView;
    @BindView(R.id.totalCampaignCount_TextView)
    TextView totalCampaignCount_TextView;
    @BindView(R.id.enterCampaignCount_EditText)
    EditText enterCampaignCount_EditText;

    //submit button
    @BindView(R.id.submitAssignLead_button)
    Button submitAssignLead_button;

    CampaignAssignTransferAdapter campaignAssignTransferAdapter;

    SharedPreferences pref;
    String userStr, processIdPref, processNamePref, roleStr;
    public ArrayList<String> location= new ArrayList<>();
    Map<String, String> locationMap = new HashMap<>();
    String leadLocationSpinner, selectedLeadLocationId = "";

    public ArrayList<String> locationTo = new ArrayList<>();;
    Map<String, String> locationToMap = new HashMap<>();
    String leadLocationToSpinner, selectedLeadLocationToId = "";

    public ArrayList<String> fromUser;
    Map<String, String> fromUserMap = new HashMap<>();
    String fromUserSpinner, fromUserSpinnerId = "";

    AssignTransferUserToAdapter assignTransferUserToAdapter;
    AssignTransferredLeadPresenter assignTransferredLeadPresenter;
    AssignToCseListAdapter assignToCseListAdapter;

    ArrayList<AssignToUserBean.Assign_Transferred_Lead_to_user> dashboardCountList = new ArrayList<AssignToUserBean.Assign_Transferred_Lead_to_user>();
    ArrayList<AssignTransferredCampignListBean.Assign_Transferred_Leads_source> campaignCountList = new ArrayList<AssignTransferredCampignListBean.Assign_Transferred_Leads_source>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_assign_transfer_lead, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        initialiseUI();
    }

    private void initialiseUI(){
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userStr = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_ID, "");
        processIdPref = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, "");
        processNamePref = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_NAME, "");
        roleStr = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");

        campaignNameLeadSourceList_ListView.setNestedScrollingEnabled(false);
        addCSENameToLocationList_ListView.setNestedScrollingEnabled(false);

        leadLocationAssignFromTransfer_ll.setVisibility(View.VISIBLE);
        leadLocationAssignToTransfer_ll.setVisibility(View.VISIBLE);
        addCSENameToLocationList_ListView.setVisibility(View.VISIBLE);
        campaignNameLeadSourceList_ListView.setVisibility(View.VISIBLE);
        assignTransferredLeadPresenter = new AssignTransferredLeadPresenter(this);

        assignTransferredLeadPresenter.getAssignLocation(getActivity());
        assignTransferredLeadPresenter.getAssignCampaignList(fromUserSpinnerId, getActivity());
        assignTransferredLeadPresenter.getAssignFromList(selectedLeadLocationId, getActivity());

        assignTransferredLeadPresenter.getAssignToLocation(getActivity());
        assignTransferredLeadPresenter.getAssignToList(leadLocationToSpinner,fromUserSpinnerId, getActivity());
    }

   /* @OnClick(R.id.fromAssignTransferredLead_textView)
    public void fromAssignTransfered() {
        try{
            assignTransferredLeadPresenter.getAssignLocation(getActivity());
            assignTransferredLeadPresenter.getAssignCampaignList(fromUserSpinnerId, getActivity());
            assignTransferredLeadPresenter.getAssignFromList(selectedLeadLocationId, getActivity());
        }catch(Exception e){
        }
    }
    */

   /* @OnClick(R.id.toAssignTransferredLead_textView)
    public void toAssignTransfered(){
        try {
            assignTransferredLeadPresenter.getAssignToLocation(getActivity());
            assignTransferredLeadPresenter.getAssignToList(leadLocationToSpinner,fromUserSpinnerId, getActivity());
        }catch(Exception e){
        }
    }
    */

    @OnClick(R.id.submitAssignLead_button)
    public void submitAssignTransferred() {

        String noAssigned = enterCampaignCount_EditText.getText().toString();
        try {

            if (!leadLocationSpinner.equals("Location")) {
                if (!fromUserSpinner.equals("From User")) {
                    if (!leadLocationToSpinner.equals("Location")) {
                        try {
                            if (assignToCseListAdapter.getSelectedCSE().length() > 0) {
                                if (campaignAssignTransferAdapter.getSelectedCampaign().size() > 0) {
                                    if (!(noAssigned.equals(""))) {
                                        getSubmitData();
                                    } else
                                        Toast.makeText(getActivity(), "Please enter count, you want to assign", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getActivity(), "Select Campaign", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getActivity(), "Select Assign to Person", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Not allowed to select CSE Name Exception", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(getActivity(), "Select Transferred To Location", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(), "Select From User", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getActivity(), "Select Transferred From Location", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    @Override
    public void showAssignTransferredLocation(AssignTransferLocationBean jsonObject) {
        try{
        location.clear();
        location.add("Location");
        for(int i=0;i<jsonObject.getFrom_location().size();i++)
        {
            try {
                location.add(jsonObject.getFrom_location().get(i).getLocation());
                locationMap.put(jsonObject.getFrom_location().get(i).getLocation_id(), jsonObject.getFrom_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, location);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationAssignFromTransfer_spinner.setAdapter(companiesArrayAdapter);

            if (jsonObject.getFrom_location().size() == 1){
                locationAssignFromTransfer_spinner.setSelection(1);
            }
        companiesArrayAdapter.notifyDataSetChanged();
    }catch(Exception e){
    }
}

    //location from selected
    @OnItemSelected(R.id.locationAssignFromTransfer_spinner)
    public void locationSelected(Spinner spinner, int position)
    {
        leadLocationSpinner = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(leadLocationSpinner)) {
                selectedLeadLocationId = (String) key;
            }else if (leadLocationSpinner.equals("Location")){
                selectedLeadLocationId = "";
             }
        }
        assignTransferredLeadPresenter.getAssignFromList(selectedLeadLocationId, getActivity());
    }

    @Override
    public void showAssignFromSpinnerView(FromUserAssignTransferBean jsonObject) {
    try{
        fromUser = new ArrayList<String>();
        fromUser.add("From User");
        for(int i=0;i<jsonObject.getAssign_transferred_lead_from_user().size();i++)
        {
            try {
                fromUser.add(jsonObject.getAssign_transferred_lead_from_user().get(i).getFname() +  " " +jsonObject.getAssign_transferred_lead_from_user().get(i).getLname());
                fromUserMap.put(jsonObject.getAssign_transferred_lead_from_user().get(i).getId(), jsonObject.getAssign_transferred_lead_from_user().get(i).getFname() + " "+ jsonObject.getAssign_transferred_lead_from_user().get(i).getLname());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, fromUser);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUserAssignTransfer_spinner.setAdapter(companiesArrayAdapter);
        if (jsonObject.getAssign_transferred_lead_from_user().size() == 1){
            fromUserAssignTransfer_spinner.setSelection(1);

        }
    }catch(Exception e){
    }
    }

    //from assign  from selected
    @OnItemSelected(R.id.fromUserAssignTransfer_spinner)
    public void fromUserSelected(Spinner spinner, int position)
    {
        fromUserSpinner = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : fromUserMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(fromUserSpinner)) {
                fromUserSpinnerId = (String) key;
                if (fromUserSpinnerId.equals(userStr)){
                    assignTransferredLeadPresenter.getAssignToLocation(getActivity());
                    assignTransferredLeadPresenter.getAssignCampaignList(fromUserSpinnerId, getActivity());
                }else{
                    pDialog = new ProgressDialog(getActivity());
                    pDialog.setMessage("Loading Leads...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                    assignTransferredLeadPresenter.getAssignToLocation(getActivity());
                    assignTransferredLeadPresenter.getAssignCampaignList(fromUserSpinnerId, getActivity());
                }

            }else if (fromUserSpinner.equals("From User")) {
                fromUserSpinnerId = "";
                assignTransferredLeadPresenter.getAssignToLocation(getActivity());
                assignTransferredLeadPresenter.getAssignCampaignList("", getActivity());
            }
        }
    }

    @Override
    public void showAssignToView(AssignToUserBean jsonObject) {
        if (fromUserSpinnerId.equals(userStr)) {
            pDialog.dismiss();
        }
        else {
            pDialog.dismiss();
        }
    try{
        if (jsonObject.getAssign_transferred_lead_to_user().size()>0) {
            dashboardCountList.clear();
            dashboardCountList.addAll(jsonObject.getAssign_transferred_lead_to_user());

            assignToCseListAdapter = new AssignToCseListAdapter(getActivity(), jsonObject.getAssign_transferred_lead_to_user());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            addCSENameToLocationList_ListView.setLayoutManager(mLayoutManager);
            addCSENameToLocationList_ListView.setItemAnimator(new DefaultItemAnimator());
            addCSENameToLocationList_ListView.setAdapter(assignToCseListAdapter);
        }
    }catch(Exception e){
    }
    }

    @Override
    public void showAssignToLocationView(AssignTransferLocationBean jsonObject) {
        if (fromUserSpinnerId.equals(userStr)) {
        }
        else {
            pDialog.dismiss();
        }
        try{
        locationTo.clear();
        locationTo.add("Location");
        for(int i=0;i<jsonObject.getTo_location().size();i++)
        {
            try {
                locationTo.add(jsonObject.getTo_location().get(i).getLocation());
                locationToMap.put(jsonObject.getTo_location().get(i).getLocation_id(), jsonObject.getTo_location().get(i).getLocation());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> locationToArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, locationTo);
        locationToArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toLocationAssignTransfer_spinner.setAdapter(locationToArrayAdapter);
        }catch(Exception e){
        }
    }

    //location to selected
    @OnItemSelected(R.id.toLocationAssignTransfer_spinner)
    public void locationToSelected(Spinner spinner, int position)
    {
        leadLocationToSpinner = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : locationToMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(leadLocationToSpinner)) {
                selectedLeadLocationToId = (String) key;
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Loading Assign To...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
                addCSENameToLocationList_ListView.setVisibility(View.VISIBLE);
                assignTransferredLeadPresenter.getAssignToList(selectedLeadLocationToId,fromUserSpinnerId, getActivity());
            }else if (leadLocationToSpinner.equals("Location")) {
                selectedLeadLocationToId = "";
                addCSENameToLocationList_ListView.setVisibility(View.GONE);
                assignTransferredLeadPresenter.getAssignToList("", fromUserSpinnerId, getActivity());
            }
        }
    }

    @Override
    public void showAssignCampaignlist(AssignTransferredCampignListBean jsonObject) {
        if (fromUserSpinnerId.equals(userStr)) {
            try {
                if (!(jsonObject.getAssign_transferred_leads_all_count().get(0).equals("0"))) {
                    campaignCountList.clear();
                    campaignCountList.addAll(jsonObject.getAssign_transferred_leads_source());
                    totalCampaignCount_TextView.setVisibility(View.VISIBLE);
                    totalCampaignCount_TextView.setText(jsonObject.assign_transferred_leads_all_count.get(0).getAcount());
                    campaignAssignTransferAdapter = new CampaignAssignTransferAdapter(getActivity(), jsonObject.getAssign_transferred_leads_source());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    campaignNameLeadSourceList_ListView.setLayoutManager(mLayoutManager);
                    campaignNameLeadSourceList_ListView.setItemAnimator(new DefaultItemAnimator());
                    campaignNameLeadSourceList_ListView.setAdapter(campaignAssignTransferAdapter);
                }
            }catch(Exception e){
            }
        }
        else{
            pDialog.dismiss();
            try {
                if (!(jsonObject.getAssign_transferred_leads_all_count().get(0).equals("0"))) {
                    campaignCountList.clear();
                    campaignCountList.addAll(jsonObject.getAssign_transferred_leads_source());
                    totalCampaignCount_TextView.setVisibility(View.VISIBLE);
                    totalCampaignCount_TextView.setText(jsonObject.assign_transferred_leads_all_count.get(0).getAcount());
                    campaignAssignTransferAdapter = new CampaignAssignTransferAdapter(getActivity(), jsonObject.getAssign_transferred_leads_source());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    campaignNameLeadSourceList_ListView.setLayoutManager(mLayoutManager);
                    campaignNameLeadSourceList_ListView.setItemAnimator(new DefaultItemAnimator());
                    campaignNameLeadSourceList_ListView.setAdapter(campaignAssignTransferAdapter);
                }
            }catch(Exception e){
            }
        }

    }

    private void getSubmitData(){
        try {
            JSONArray cseJSONArray;
            String lead_count1 = enterCampaignCount_EditText.getText().toString();
            cseJSONArray =assignToCseListAdapter.getSelectedCSE();
            HashMap<String, String> campaignHashMap = campaignAssignTransferAdapter.getSelectedCampaign();

            Map<String, String> assignLeadHashMap = new HashMap<>();
            assignLeadHashMap.put("cse_name", cseJSONArray.toString());
            assignLeadHashMap.put("leads1", campaignHashMap.get("campaignName"));
            assignLeadHashMap.put("lead_count1", lead_count1);
            assignLeadHashMap.put("web_count",campaignHashMap.get("campaignCount"));
            assignLeadHashMap.put("fromUser", fromUserSpinnerId);
            assignLeadHashMap.put("process_id", processIdPref);
            assignLeadHashMap.put("process_name", processNamePref);

            String url = Constants.BASE_URL + Constants.ASSIGN_TRANSFER_SUBMIT;
            GSONRequest<AssignBean> gsonRequest = new GSONRequest<AssignBean>(
                    Request.Method.POST, url, AssignBean.class, assignLeadHashMap,
                    new Response.Listener<AssignBean>() {
                        @Override
                        public void onResponse(AssignBean response) {
                            try {
                                Toast.makeText(getActivity(), response.message, Toast.LENGTH_SHORT).show();
                                clearAllData();
                            }catch(Exception e){
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), "server Error Here.", Toast.LENGTH_SHORT).show();
                }
            }
            );
            Utilities.getRequestQueue(getActivity()).add(gsonRequest);
        }catch (Exception e){
            Toast.makeText(getActivity(), "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearAllData(){
        toLocationAssignTransfer_spinner.setSelection(0);
        assignTransferredLeadPresenter.getAssignToList("","", getActivity());
        assignTransferredLeadPresenter.getAssignToLocation(getActivity());
        assignTransferredLeadPresenter.getAssignCampaignList("", getActivity());
        fromUserAssignTransfer_spinner.setSelection(0);
        assignTransferredLeadPresenter.getAssignFromList("", getActivity());
        campaignNameLeadSourceList_ListView.setVisibility(View.GONE);
        addCSENameToLocationList_ListView.setVisibility(View.GONE);
        enterCampaignCount_EditText.setText("0");
        locationAssignFromTransfer_spinner.setSelection(0);
        totalCampaignCount_TextView.setVisibility(View.GONE);
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
