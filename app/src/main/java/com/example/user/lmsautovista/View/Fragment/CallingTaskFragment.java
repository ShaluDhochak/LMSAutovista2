package com.example.user.lmsautovista.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Adapter.NotificationFragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallingTaskFragment extends Fragment {

    View view;
    Context context;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    NotificationFragmentPageAdapter notificationFragmentPageAdapter;
    ImageView backButton_ImageView;

    public CallingTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calling_task, container, false);

        ButterKnife.bind(this, view);
        initialiseUI();
        return view;

    }

    private void initialiseUI(){
        NewLeadNotificationFragment newLeadNotificationFragment = new NewLeadNotificationFragment();
        TodayFollowupNotificationFragment todayFollowupNotificationFragment = new TodayFollowupNotificationFragment();
        PendingLiveLeadsNotificationFragment pendingLiveLeadsNotificationFragment = new PendingLiveLeadsNotificationFragment();
        PendingNewLeadsNotificationFragment pendingNewLeadsNotificationFragment = new PendingNewLeadsNotificationFragment();
        AllLeadFragment allLeadFragment = new AllLeadFragment();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(newLeadNotificationFragment);
        fragmentList.add(todayFollowupNotificationFragment);
        fragmentList.add(pendingLiveLeadsNotificationFragment);
        fragmentList.add(pendingNewLeadsNotificationFragment);
        fragmentList.add(allLeadFragment);

        notificationFragmentPageAdapter = new NotificationFragmentPageAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(notificationFragmentPageAdapter);
        tabs.setViewPager(viewPager);
    }


}
