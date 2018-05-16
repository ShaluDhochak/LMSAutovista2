package com.example.user.lmsautovista.View.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Presenter.AllLeadCallingTaskPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.NetworkUtilities;
import com.example.user.lmsautovista.View.Adapter.CallingTaskDetailAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllLeadFragment extends Fragment implements IView.AllLeadCallingTaskView{

    ProgressDialog progressDialog;
    AllLeadCallingTaskPresenter allLeadCallingTaskPresenter;

    @BindView(R.id.search_cardView)
    CardView search_cardView;

    @BindView(R.id.search_EditText)
    EditText search_EditText;

    @BindView(R.id.customerDetails_ListView)
    RecyclerView customerDetails_ListView;

    @BindView(R.id.searchViaDateHeading_TextView)
    TextView searchViaDateHeading_TextView;

    @BindView(R.id.search_button)
    ImageView search_button;

    //load more and load less
    @BindView(R.id.loadMoreAllLead_btn)
    Button loadMoreAllLead_btn;

    @BindView(R.id.loadLessAllLead_btn)
    Button loadLessAllLead_btn;

    @BindView(R.id.nextPreviousBtn_linearlayout)
    LinearLayout nextPreviousBtn_linearlayout;

    int currentPageNo = -1 ;
    View view;
    ArrayList<CallingTaskNewLeadBean.Lead_Details> leadsCountList = new ArrayList<CallingTaskNewLeadBean.Lead_Details>();
    ArrayList<CallingTaskNewLeadBean.Lead_Details> tempArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lead_callingtask, container, false);

        ButterKnife.bind(this, view);
        initialiseUI();
        return view;
    }

    private void initialiseUI(){
        allLeadCallingTaskPresenter = new AllLeadCallingTaskPresenter(this);

        search_cardView.setVisibility(View.VISIBLE);
        nextPreviousBtn_linearlayout.setVisibility(View.VISIBLE);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onResume(){
        super.onResume();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if (NetworkUtilities.isInternet(getActivity())) {
            allLeadCallingTaskPresenter.getAllLeadCallingTaskList("-1",getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
      //  getSearchOperation();
    }

    private void getSearchOperation(){
        search_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                tempArrayList.clear();
                for(CallingTaskNewLeadBean.Lead_Details leads : leadsCountList)
                {
                    if(leads.getName().contains(s.toString()))
                    {
                        tempArrayList.add(leads);
                        CallingTaskDetailAdapter dashboardAdapter = new CallingTaskDetailAdapter(getActivity(), tempArrayList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        customerDetails_ListView.setLayoutManager(mLayoutManager);
                        customerDetails_ListView.setItemAnimator(new DefaultItemAnimator());
                        customerDetails_ListView.setAdapter(dashboardAdapter);
                    }
                }
            }
        });
    };

    @OnClick(R.id.search_button)
    public void getSearchBtn(){
        String contact_no = search_EditText.getText().toString();
        if (search_EditText.getText().length()>0){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            allLeadCallingTaskPresenter.getAllSearchedLeadCallingTask(contact_no,"-1",getActivity());
        }else{
            Toast.makeText(getActivity(), "Please Type Name or Contact no.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.loadMoreAllLead_btn)
    public void loadMore(){
        try {
            if ((currentPageNo >= -1)) {
                currentPageNo = currentPageNo + 1;

                allLeadCallingTaskPresenter.getAllLeadCallingTaskList(String.valueOf(currentPageNo), getActivity());
            } else {
                Toast.makeText(getActivity(), "Not possible", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
        }
    }

    @OnClick(R.id.loadLessAllLead_btn)
    public void loadLess(){
        try {
            if (currentPageNo > -1) {
                currentPageNo = currentPageNo - 1;

                allLeadCallingTaskPresenter.getAllLeadCallingTaskList(String.valueOf(currentPageNo), getActivity());
            } else {
                Toast.makeText(getActivity(), "Not possible", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
        }

    }

    @Override
    public void showAllLeadDetails(CallingTaskNewLeadBean jsonObject) {
                progressDialog.dismiss();

                if (jsonObject.getLead_details_count().size()>0){
                    searchViaDateHeading_TextView.setText("Total Leads: " + jsonObject.getLead_details_count().get(0).getCount_lead());
                }

                    leadsCountList.clear();
                    leadsCountList.addAll(jsonObject.getLead_details());
                    CallingTaskDetailAdapter dashboardAdapter = new CallingTaskDetailAdapter(getActivity(), jsonObject.getLead_details());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    customerDetails_ListView.setLayoutManager(mLayoutManager);
                    customerDetails_ListView.setItemAnimator(new DefaultItemAnimator());
                    customerDetails_ListView.setAdapter(dashboardAdapter);

    }
}
