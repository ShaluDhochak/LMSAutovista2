package com.example.user.lmsautovista.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.AssignToBean;
import com.example.user.lmsautovista.Model.LeadSourceBean;
import com.example.user.lmsautovista.Presenter.AddNewLeadPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    AddNewLeadPresenter addNewLeadPresenter;
    String user_name, user_id;
    String selectedLeadProcessId, selectedLeadProcess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_new_lead, container, false);
        // Inflate the layout for this fragment

        ButterKnife.bind(this, view);

        addNewLeadPresenter = new AddNewLeadPresenter(this);
        getProcessTypeDetails();
        return view;

    }

    @OnClick(R.id.addNewLeadSubmit_Button)
    public void addNewLead(){

        user_id = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_ID, "");
        user_name = SharedPreferenceManager.getInstance(getActivity()).getPreference(Constants.USER_NAME, "");
        String fname, email, address, assign, contact_no, comment, lead_source, assign_by,username, process_name;
        fname =leadfirstname_EditText.getText().toString();
        email= leademail_EditText.getText().toString();
        address=   leadaddress_EditText.getText().toString();
        assign = "194";
       // assign = assignTo_Spinner;
        contact_no =leadContactno_EditText.getText().toString();
        comment =leadcomment_EditText.getText().toString();
        lead_source = "Web" ;
        process_name = "Finance";
        //lead_source = leadSource_spinner
        assign_by = user_id;
        username = user_name;
       // process_name =leadProcess_spinner

        addNewLeadPresenter.saveNewLeadInfo(fname, email, address, assign, contact_no, comment, lead_source, assign_by, username, process_name, getActivity());
    }

    public void getProcessTypeDetails(){
        ArrayList<String> referralTypesArrayList = new ArrayList<>();
        referralTypesArrayList.add("Select Process");
        referralTypesArrayList.add("Finance");
        referralTypesArrayList.add("Service");
        referralTypesArrayList.add("Accessories");
        referralTypesArrayList.add("New Car");
        referralTypesArrayList.add("Used Car");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, referralTypesArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leadProcess_spinner.setAdapter(contactArrayAdapter);

        leadProcess_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLeadProcess = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedLeadProcess.equals("Select Process")){
                    selectedLeadProcessId = "";
                }else {
                    selectedLeadProcessId = selectedLeadProcess;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void addNewLeadSuccess() {
        Toast.makeText(getActivity(), "New Customer Successfully Added!..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLeadSource(LeadSourceBean jsonObject) {

    }

    @Override
    public void showLocation() {

    }

    @Override
    public void showAssignTo(AssignToBean jsonObject) {

    }

    @Override
    public void addNewLeadFailure(String message) {
        Toast.makeText(getActivity(), "Not able to added successfully....", Toast.LENGTH_SHORT).show();
    }
}
