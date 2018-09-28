package com.excell.lms.lmsautovista.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.SearchTrackerListBean;
import com.excell.lms.lmsautovista.Presenter.DailyReportPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Adapter.DailyReportAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyReportFragment extends Fragment  implements IView.IDailyReportView{

    View view;

    @BindView(R.id.dailyReport_listView)
    ListView dailyReport_listView;

    DailyReportPresenter dailyReportPresenter;
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
        dailyReportPresenter = new DailyReportPresenter(this);

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        String date = year + "-" + month + "-" + day;
        dailyReportPresenter.getSearchTRackerList(getActivity(), date);
    }

    @Override
    public void showTrackerListView(SearchTrackerListBean jsonObject) {
        try{
            if (jsonObject.getUser_details().size()>0) {
                leadsArrayList.clear();
                leadsArrayList.addAll(jsonObject.getUser_details());
                dailyReportAdapter = new DailyReportAdapter(getActivity(), jsonObject.getUser_details());
                dailyReport_listView.setAdapter(dailyReportAdapter);
                dailyReportAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(), "No Record Found.", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
