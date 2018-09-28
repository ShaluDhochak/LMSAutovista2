package com.excell.lms.lmsautovista.View.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.AssignToBean;
import com.excell.lms.lmsautovista.Model.AssignTransferLocationBean;
import com.excell.lms.lmsautovista.Model.LeadSourceBean;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Presenter.AddNewLeadPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.Activity.SearchLeadViaContactNoActivity;
import com.excell.lms.lmsautovista.View.IView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AddNewLeadFragment extends Fragment implements IView.AddNewLeadView{

    View view;

    @BindView(R.id.leadfirstname_EditText)
    EditText leadfirstname_EditText;
    @BindView(R.id.leademail_EditText)
    EditText leademail_EditText;
    @BindView(R.id.leadContactno_EditText)
    EditText leadContactno_EditText;
    @BindView(R.id.leadaddress_EditText)
    EditText leadaddress_EditText;

    @BindView(R.id.leadProcess_spinner)
    Spinner leadProcess_spinner;
    @BindView(R.id.leadSource_spinner)
    Spinner leadSource_spinner;
    @BindView(R.id.location_spinner)
    Spinner location_spinner;
    @BindView(R.id.assignTo_Spinner)
    Spinner assignTo_Spinner;
    @BindView(R.id.leadcomment_EditText)
    EditText leadcomment_EditText;
    @BindView(R.id.addNewLeadSubmit_Button)
    Button addNewLeadSubmit_Button;
    @BindView(R.id.resetAddNewLead_Button)
    Button resetAddNewLead_Button;

    //search via Contact No Button
    @BindView(R.id.contactNoSearch_imageView)
    ImageView contactNoSearch_imageView;

    //Location spinner layout
    @BindView(R.id.leadLocation_LinearLayout)
    LinearLayout leadLocation_LinearLayout;

    //map for assign tO
    Map<String, String> assignToMap = new HashMap<>();
    Map<String, String> locationMap = new HashMap<>();
    Map<String, String> leadSourceMap = new HashMap<>();
    Map<String, String> processMap = new HashMap<>();

    //map assign to
    ArrayList<String> assignToArrayList = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();
    ArrayList<String> referralTypesArrayList = new ArrayList<>();
    ArrayList<String> leadSourceArrayList = new ArrayList<>();

    //JSONParser in
    JSONParser jsonParser = new JSONParser();;
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    AddNewLeadPresenter addNewLeadPresenter;
    String user_name, user_id, role;
    String selectedLeadProcessId, selectedLeadProcess, selectedAssignTo, selectedAssignToId, selectedLeadSource,selectedLeadSourceId, selectedLocationId, selectedLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_new_lead, container, false);
        ButterKnife.bind(this, view);

        selectedLeadProcessId = "0";
        selectedLocationId = "0";
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        addNewLeadPresenter = new AddNewLeadPresenter(this);
        addNewLeadPresenter.getProcess(getActivity());
        if (selectedLocationId.equals("0")){
            addNewLeadPresenter.getAssignToLocation(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, ""), getActivity());
        }else{
            addNewLeadPresenter.getAssignToLocation(selectedLocationId, getActivity());
        }
        if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")){
            leadLocation_LinearLayout.setVisibility(View.GONE);
            addNewLeadPresenter.getAssignTo(selectedLeadProcessId, SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.LOCATION_SESSION, "") ,getActivity());
        }else{
            leadLocation_LinearLayout.setVisibility(View.VISIBLE);
            addNewLeadPresenter.getAssignTo(selectedLeadProcessId, selectedLocationId ,getActivity());
        }
    }

    @OnClick(R.id.addNewLeadSubmit_Button)
    public void addNewLead(){
        if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")) {
            if (validateText()) {
                if (ValidateProcess()) {
                    if (ValidateLeadSource()) {
                            if (checkSpinnerValidation()) {
                                if (leadcomment_EditText.getText().length() > 0) {
                                    new CreateNewLead().execute();
                                } else {
                                    Toast.makeText(getActivity(), "Please enter Remark.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                }
            }
        }else{
            if (validateText()) {
                if (ValidateProcess()) {
                    if (ValidateLeadSource()) {
                        if (ValidateLocation()) {
                            if (checkSpinnerValidation()) {
                                if (leadcomment_EditText.getText().length() > 0) {
                                    new CreateNewLead().execute();
                                } else {
                                    Toast.makeText(getActivity(), "Please enter Remark.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateText() {
        if (leadfirstname_EditText.getText().toString().trim().length() > 0) {
            if (leademail_EditText.getText().toString().trim().length() > 0) {
                if (isEmailValid(leademail_EditText.getText().toString().trim())) {
                    if (leadContactno_EditText.getText().toString().trim().length() > 0) {
                        if (leadContactno_EditText.getText().toString().trim().length() == 10) {
                            if (leadaddress_EditText.getText().toString().trim().length() > 0) {
                                return true;
                            } else {
                                Toast.makeText(getActivity(), "Please enter Address", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        } else {
                            Utilities.showToast(getActivity(), "Please enter 10 digits Contact No.");
                            return false;
                        }
                    } else {
                        Utilities.showToast(getActivity(), "Please enter Contact No.");
                        return false;
                    }
                } else {
                    Toast.makeText(getActivity(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Utilities.showToast(getActivity(), "Please enter Email");
                return false;
            }
        } else {
            Utilities.showToast(getActivity(), "Enter name");
            return false;
        }
    }

    private boolean checkSpinnerValidation(){
        if (selectedAssignTo.equals("Assign To")) {
            Utilities.showToast(getActivity(),"Please Select Assign To");
            return false;
        }else {
            return true;
        }
    }

    private boolean ValidateLeadSource(){
        if (selectedLeadSource.equals("Lead Source")) {
            Utilities.showToast(getActivity(),"Please Select Lead Source");
            return false;
        }else {
            return true;
        }
    }

    private boolean ValidateLocation(){
        if (selectedLocation.equals("Location")) {
            Utilities.showToast(getActivity(),"Please Select Location");
            return false;
        }else {
            return true;
        }
    }

    private boolean ValidateProcess(){
        if (selectedLeadProcess.equals("Process")) {
            Utilities.showToast(getActivity(),"Please Select Process");
            return false;
        }else {
            return true;
        }
    }

    @OnClick(R.id.resetAddNewLead_Button)
    public void resetLead(){
        clearAll();
    }

    @OnClick(R.id.contactNoSearch_imageView)
    public void contactNoSearch(){
        String contact_no = leadContactno_EditText.getText().toString();
        if (leadContactno_EditText.getText().toString().length()>0 && leadContactno_EditText.getText().toString().length()==10) {
            Intent contactNoSearchIntent = new Intent(getActivity(), SearchLeadViaContactNoActivity.class);
            Bundle searchNoSearchBundle = new Bundle();
            searchNoSearchBundle.putString("contact_no", contact_no);
            contactNoSearchIntent.putExtras(searchNoSearchBundle);
            startActivity(contactNoSearchIntent);
        }else{
            Toast.makeText(getActivity(), "Please Enter Valid Contact No", Toast.LENGTH_SHORT).show();
        }
    }

    private class CreateNewLead extends AsyncTask<String, JSONObject, JSONObject> {
          String fname, email, address, assign, contact_no, comment, lead_source, assign_by,username,process_id,location,role_session;
          String user_id_session;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_ID, "");
            user_name = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_NAME, "");
            role = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "");

            fname =leadfirstname_EditText.getText().toString();
            email= leademail_EditText.getText().toString();
            address=   leadaddress_EditText.getText().toString();
            assign = selectedAssignToId;
            contact_no =leadContactno_EditText.getText().toString();
            comment =leadcomment_EditText.getText().toString();
            lead_source = selectedLeadSource ;
            process_id = selectedLeadProcessId;
            location = selectedLocation;
            assign_by = user_id;
            username = user_name;
            role_session = role;
            user_id_session = user_id;

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Adding Lead...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();

            params.add(new BasicNameValuePair("fname", fname));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("assign", assign));
            params.add(new BasicNameValuePair("contact_no", contact_no));
            params.add(new BasicNameValuePair("comment", comment));
            params.add(new BasicNameValuePair("assignby", assign_by));
            params.add(new BasicNameValuePair("lead_source", lead_source));
            params.add(new BasicNameValuePair("process_id", process_id));
            params.add(new BasicNameValuePair("location", location));
            params.add(new BasicNameValuePair("role_session", role_session));
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("user_id_session", user_id_session));

            String url_add_lead = Constants.BASE_URL + Constants.ADD_NEW_LEAD;
            JSONObject json = jsonParser.makeHttpRequest(url_add_lead, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                if (success == 1 && message.equals("Data successfully Inserted.")) {
                    return json;
                }
                else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject response) {
            try {
                pDialog.dismiss();
                if (!(response == null)) {
                    Toast.makeText(getActivity(), "Data successfully Inserted.", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
                else {
                    Toast.makeText(getActivity(), "Already Inserted ", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public void getProcessTypeDetails(){
        referralTypesArrayList.clear();
        referralTypesArrayList.add("Process");
        referralTypesArrayList.add("Finance");
        referralTypesArrayList.add("Service");
        referralTypesArrayList.add("Accessories");
        referralTypesArrayList.add("New Car");
        referralTypesArrayList.add("Used Car");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, referralTypesArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leadProcess_spinner.setAdapter(contactArrayAdapter);

    }

    @OnItemSelected(R.id.leadProcess_spinner)
        public void processNameSelected(Spinner spinner, int position)
        {
            selectedLeadProcess = spinner.getSelectedItem().toString();
            for (Map.Entry<String, String> e : processMap.entrySet()) {
                Object key = e.getKey();
                Object value = e.getValue();
                if(value.equals(selectedLeadProcess)) {
                    selectedLeadProcessId = (String) key;
                    Log.i("Selected CSE : ",selectedLeadProcessId);
                    addNewLeadPresenter.getAssignToLocation(selectedLeadProcessId, getActivity());


                    if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")){
                        leadLocation_LinearLayout.setVisibility(View.GONE);
                        addNewLeadPresenter.getAssignTo(selectedLeadProcessId, SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.LOCATION_SESSION, "") ,getActivity());
                    }else{
                        leadLocation_LinearLayout.setVisibility(View.VISIBLE);
                        addNewLeadPresenter.getAssignTo(selectedLeadProcessId, selectedLocationId ,getActivity());
                    }

                    //   loginPresenter.saveLocationInfo((String)key, (String) value, SharedPreferenceManager.getInstance(this));
                }else if (selectedLeadProcess.equals("Process")){
                    selectedLeadProcessId = "";

                    if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")){
                        leadLocation_LinearLayout.setVisibility(View.GONE);
                        addNewLeadPresenter.getAssignTo(selectedLeadProcessId, SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.LOCATION_SESSION, "") ,getActivity());
                    }else{
                        leadLocation_LinearLayout.setVisibility(View.VISIBLE);
                        addNewLeadPresenter.getAssignTo(selectedLeadProcessId, selectedLocationId ,getActivity());
                    }
                    addNewLeadPresenter.getAssignToLocation(SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.PROCESS_ID, ""), getActivity());
                    //   selectedLeadProcess = "";
                }
            }

       addNewLeadPresenter.getLeadSource(selectedLeadProcessId,getActivity());
    }

    @Override
    public void addNewLeadSuccess() {
        Toast.makeText(getActivity(), "Successfully inserted...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProcess(LoginBean jsonObject) {
        try {
            ArrayList<String> processDashboardArrayList = new ArrayList<>();
            processDashboardArrayList.add("Process");

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
            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, processDashboardArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            leadProcess_spinner.setAdapter(locationDashboardArrayAdapter);
        }catch(Exception e){
        }
    }

    @Override
    public void showLeadSource(LeadSourceBean jsonObject) {
        leadSourceArrayList.clear();
        leadSourceArrayList.add("Lead Source");
        for(int i=0;i<jsonObject.getSelect_lead_source().size();i++)
        {
            try {
            leadSourceArrayList.add(jsonObject.getSelect_lead_source().get(i).getLead_source_name());
            leadSourceMap.put(jsonObject.getSelect_lead_source().get(i).getId(),jsonObject.getSelect_lead_source().get(i).getLead_source_name());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, leadSourceArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leadSource_spinner.setAdapter(companiesArrayAdapter);
    }

    @OnItemSelected(R.id.leadSource_spinner)
    public void leadSourceSelected(Spinner spinner, int position){
        selectedLeadSource = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : leadSourceMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLeadSource)) {
                selectedLeadSourceId = (String) key;
                Log.i("Selected CSE : ",selectedLeadSourceId);
            }else if (selectedLeadSource.equals("Lead Source")){
                selectedLeadSourceId = "";
            }
        }
    }

    @Override
    public void showLocation(AssignTransferLocationBean jsonObject) {
       locationArrayList.clear();
       locationArrayList.add("Location");
        for(int i=0;i<jsonObject.getTo_location().size();i++)
        {
            try {
                locationArrayList.add(jsonObject.getTo_location().get(i).getLocation());
                locationMap.put(jsonObject.getTo_location().get(i).getLocation_id(),jsonObject.getTo_location().get(i).getLocation());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, locationArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_spinner.setAdapter(companiesArrayAdapter);
    }

    @OnItemSelected(R.id.location_spinner)
    public void locationSelected(Spinner spinner, int position){
        selectedLocation = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLocation)) {
                selectedLocationId = (String) key;
                addNewLeadPresenter.getAssignTo(selectedLeadProcessId, selectedLocationId ,getActivity());
                Log.i("Selected CSE : ",selectedLocationId);
            }else if (selectedLocation.equals("Location")){
                selectedLocationId = "";
                addNewLeadPresenter.getAssignTo(selectedLeadProcessId, selectedLocationId ,getActivity());
            }
        }
    }

    @Override
    public void showAssignTo(AssignToBean jsonObject) {
        assignToArrayList.clear();
        assignToArrayList.add("Assign To");
        for(int i=0;i<jsonObject.getSelect_user().size();i++)
        {
            if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("3")){
                if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_ID, "").equals(jsonObject.getSelect_user().get(i).getId())){
                    try {
                        assignToArrayList.add(jsonObject.getSelect_user().get(i).getFname() + " "+ jsonObject.getSelect_user().get(i).getLname());
                        assignToMap.put(jsonObject.getSelect_user().get(i).getId(),jsonObject.getSelect_user().get(i).getFname() + " "+ jsonObject.getSelect_user().get(i).getLname());
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }else if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.ROLE_ID, "").equals("4")){
            if (SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_ID, "").equals(jsonObject.getSelect_user().get(i).getId())){
                try {
                    assignToArrayList.add(jsonObject.getSelect_user().get(i).getFname() + " "+ jsonObject.getSelect_user().get(i).getLname());
                    assignToMap.put(jsonObject.getSelect_user().get(i).getId(),jsonObject.getSelect_user().get(i).getFname() + " "+ jsonObject.getSelect_user().get(i).getLname());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        } else{
                try {
                    assignToArrayList.add(jsonObject.getSelect_user().get(i).getFname() + " "+ jsonObject.getSelect_user().get(i).getLname());
                    assignToMap.put(jsonObject.getSelect_user().get(i).getId(),jsonObject.getSelect_user().get(i).getFname() + " "+ jsonObject.getSelect_user().get(i).getLname());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        ArrayAdapter<String> companiesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview, assignToArrayList);
        companiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignTo_Spinner.setAdapter(companiesArrayAdapter);
    }

    @OnItemSelected(R.id.assignTo_Spinner)
    public void assignToSelected(Spinner spinner, int position){
        selectedAssignTo = spinner.getSelectedItem().toString();

        for (Map.Entry<String, String> e : assignToMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedAssignTo)) {
                selectedAssignToId = (String) key;
                Log.i("Selected CSE : ",selectedLocationId);
                if (selectedAssignTo.equals("Assign To")) {
                    selectedAssignToId = "";
                }
            }
        }
    }

  private void clearAll(){
      leadfirstname_EditText.setText("");
      leademail_EditText.setText("");
      leadContactno_EditText.setText("");
      leadaddress_EditText.setText("");
      leadcomment_EditText.setText("");
      leadProcess_spinner.setSelection(0);
      leadSource_spinner.setSelection(0);
      location_spinner.setSelection(0);
      assignTo_Spinner.setSelection(0);
  }
}
