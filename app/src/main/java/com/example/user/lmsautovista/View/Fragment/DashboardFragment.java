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
import android.widget.Toast;

import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Presenter.DashboardPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.NetworkUtilities;
import com.example.user.lmsautovista.View.Adapter.DashboardAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment implements IView.DashboardView{

    View view;
    ProgressDialog progressDialog;
    DashboardPresenter dashboardPresenter;

    @BindView(R.id.countLocationWise_ListView)
    RecyclerView countLocationWise_ListView;

    ArrayList<DashboardCountBean.Dashboard_Count> dashboardCountList = new ArrayList<DashboardCountBean.Dashboard_Count>();

    public DashboardFragment() {
        // Required empty public constructor
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
            dashboardPresenter.getDashboardList(getActivity());
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
    public void showDailyAppointmentsCount(String new_lead, String unassigend_leads, String call_today, String pending_newLeads, String pending_followup) {
        try {
                try {
                    DashboardCountBean.Dashboard_Count repsInfo = new DashboardCountBean.Dashboard_Count();
                    repsInfo.setNew_leads(new_lead);
                    repsInfo.setCall_today(unassigend_leads);
                    repsInfo.setPending_followup(call_today);
                    repsInfo.setPending_new_leads(pending_newLeads);
                    repsInfo.setUnassigned_leads(pending_followup);

                    dashboardCountList.add(repsInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            DashboardAdapter dashboardRepsAdapter = new DashboardAdapter(getActivity(),dashboardCountList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            countLocationWise_ListView.setLayoutManager(mLayoutManager);
            countLocationWise_ListView.setItemAnimator(new DefaultItemAnimator());
            countLocationWise_ListView.setAdapter(dashboardRepsAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ShowDashboardCount(DashboardCountBean jsonObject) {
        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getDashboard_count());

        DashboardAdapter dashboardRepsAdapter = new DashboardAdapter(getActivity(),jsonObject.getDashboard_count());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        countLocationWise_ListView.setLayoutManager(mLayoutManager);
        countLocationWise_ListView.setItemAnimator(new DefaultItemAnimator());
        countLocationWise_ListView.setAdapter(dashboardRepsAdapter);

    }


}
