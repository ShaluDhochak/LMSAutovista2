package com.example.user.lmsautovista.View.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AllLeadFragment extends Fragment implements IView.AllLeadCallingTaskView{

    ProgressDialog progressDialog;
    AllLeadCallingTaskPresenter allLeadCallingTaskPresenter;

    @BindView(R.id.customerDetails_ListView)
    RecyclerView customerDetails_ListView;

    @BindView(R.id.searchViaDateHeading_TextView)
    TextView searchViaDateHeading_TextView;

    View view;
    ArrayList<CallingTaskNewLeadBean.Lead_Details> dashboardCountList = new ArrayList<CallingTaskNewLeadBean.Lead_Details>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lead_callingtask, container, false);

        ButterKnife.bind(this, view);
        initialiseUI();
        return view;
    }

    private void initialiseUI(){
        allLeadCallingTaskPresenter = new AllLeadCallingTaskPresenter(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (NetworkUtilities.isInternet(getActivity())) {
            allLeadCallingTaskPresenter.getAllLeadCallingTaskList(getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showAllLeadDetails(CallingTaskNewLeadBean jsonObject) {
        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getLead_details());
        searchViaDateHeading_TextView.setText("Total Leads: " +jsonObject.getLead_details_count().get(0).getCount_lead());

        CallingTaskDetailAdapter dashboardAdapter = new CallingTaskDetailAdapter(getActivity(),jsonObject.getLead_details());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        customerDetails_ListView.setLayoutManager(mLayoutManager);
        customerDetails_ListView.setItemAnimator(new DefaultItemAnimator());
        customerDetails_ListView.setAdapter(dashboardAdapter);
    }
}
