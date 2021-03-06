package com.excell.lms.lmsautovista.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.Presenter.TodayFollowUpPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.CallingTaskDetailAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TodayFollowupNotificationFragment extends Fragment implements IView.TodayLeadCallingTaskView{

    TodayFollowUpPresenter todayFollowupPresenter;

    @BindView(R.id.search_cardView)
    CardView search_cardView;

    @BindView(R.id.customerDetails_ListView)
    RecyclerView customerDetails_ListView;

    @BindView(R.id.searchViaDateHeading_TextView)
    TextView searchViaDateHeading_TextView;

    @BindView(R.id.currentFollowUp_txtView)
    TextView currentFollowUp_txtView;

    @BindView(R.id.todayFollowUp_txtView)
    TextView todayFollowUp_txtView;

    @BindView(R.id.callTodayBtn_ll)
    LinearLayout callTodayBtn_ll;

    //no record found Visibility
    @BindView(R.id.NoRecordFoundHeading_TextView)
    TextView NoRecordFoundHeading_TextView;

    View view;
    ArrayList<CallingTaskNewLeadBean.Lead_Details> dashboardCountList = new ArrayList<CallingTaskNewLeadBean.Lead_Details>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_lead_callingtask, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        initialiseUI();
        return view;
    }

    private void initialiseUI(){
        todayFollowupPresenter = new TodayFollowUpPresenter(this);

        search_cardView.setVisibility(View.GONE);
        callTodayBtn_ll.setVisibility(View.VISIBLE);
        NoRecordFoundHeading_TextView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtilities.isInternet(getActivity())) {
            todayFollowup();
            todayFollowupPresenter.getTodayCallingTaskList(getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.currentFollowUp_txtView)
    public void currentFollowup(){

    currentFollowUp();
        todayFollowupPresenter.getCurrentTodayTaskList(getActivity());
    }

    private void currentFollowUp(){
        currentFollowUp_txtView.setTextColor(getResources().getColor(R.color.lightGrey1));
        currentFollowUp_txtView.setBackgroundColor(getResources().getColor(R.color.darkBlueWebsite));

        todayFollowUp_txtView.setBackgroundColor(getResources().getColor(R.color.lightGrey1));
        todayFollowUp_txtView.setTextColor(getResources().getColor(R.color.darkBlueWebsite));
    }

    private void todayFollowup(){
        todayFollowUp_txtView.setBackgroundColor(getResources().getColor(R.color.darkBlueWebsite));
        todayFollowUp_txtView.setTextColor(getResources().getColor(R.color.lightGrey1));

        currentFollowUp_txtView.setTextColor(getResources().getColor(R.color.darkBlueWebsite));
        currentFollowUp_txtView.setBackgroundColor(getResources().getColor(R.color.lightGrey1));
    }


    @OnClick(R.id.todayFollowUp_txtView)
    public void todayFollowUp(){
       todayFollowup();
        todayFollowupPresenter.getTodayCallingTaskList(getActivity());
    }
    @Override
    public void ShowTodayLeadDetailCount(CallingTaskNewLeadBean jsonObject) {
        try{
            if (jsonObject.getLead_details().size()>0) {

        dashboardCountList.clear();
        dashboardCountList.addAll(jsonObject.getLead_details());
     //   searchViaDateHeading_TextView.setText("Total Leads: " +jsonObject.getLead_details_count().get(0).getCount_lead());

        CallingTaskDetailAdapter dashboardAdapter = new CallingTaskDetailAdapter(getActivity(),jsonObject.getLead_details());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        customerDetails_ListView.setLayoutManager(mLayoutManager);
        customerDetails_ListView.setItemAnimator(new DefaultItemAnimator());
        customerDetails_ListView.setAdapter(dashboardAdapter);
            }else{
                NoRecordFoundHeading_TextView.setVisibility(View.GONE);
            }
        }catch(Exception e){
        }
    }
}
