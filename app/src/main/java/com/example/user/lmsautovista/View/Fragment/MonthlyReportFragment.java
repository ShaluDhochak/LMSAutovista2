package com.example.user.lmsautovista.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.lmsautovista.Model.SearchTrackerListBean;
import com.example.user.lmsautovista.Presenter.MonthlyReportPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Adapter.DailyReportAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MonthlyReportFragment extends Fragment implements IView.IMonthlyReportView{


    View view;

    @BindView(R.id.dailyReport_listView)
    ListView dailyReport_listView;

    MonthlyReportPresenter monthlyReportPresenter;
    DailyReportAdapter dailyReportAdapter;
    ArrayList<SearchTrackerListBean.User_Details> leadsArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_daily_report, container, false);
        initialiseUI();
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        return view;
    }

    public void initialiseUI(){

    }

    @Override
    public void onResume() {
        super.onResume();
        monthlyReportPresenter = new MonthlyReportPresenter(this);

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        String date = year + "-" + month + "-" + day;
        monthlyReportPresenter.getSearchTRackerList(getActivity(), date);
    }


    @Override
    public void showTrackerListView(SearchTrackerListBean jsonObject) {
        leadsArrayList.clear();
        leadsArrayList.addAll(jsonObject.getUser_details());
        dailyReportAdapter = new DailyReportAdapter(getActivity(),jsonObject.getUser_details());
        dailyReport_listView.setAdapter(dailyReportAdapter);
        dailyReportAdapter.notifyDataSetChanged();
    }
}
