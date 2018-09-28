package com.excell.lms.lmsautovista.View.Adapter;

/*
  Created by Shalu Dhochak on 2/26/2018.
*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.excell.lms.lmsautovista.View.Fragment.AllLeadFragment;
import com.excell.lms.lmsautovista.View.Fragment.EvaluationTodayFragment;
import com.excell.lms.lmsautovista.View.Fragment.HomeVisitTodayFragment;
import com.excell.lms.lmsautovista.View.Fragment.NewLeadNotificationFragment;
import com.excell.lms.lmsautovista.View.Fragment.PendingLiveLeadsNotificationFragment;
import com.excell.lms.lmsautovista.View.Fragment.PendingNewLeadsNotificationFragment;
import com.excell.lms.lmsautovista.View.Fragment.ShowroomVisitTodayFragment;
import com.excell.lms.lmsautovista.View.Fragment.TestDriveTodayFragment;
import com.excell.lms.lmsautovista.View.Fragment.TodayFollowupNotificationFragment;

import java.util.List;

public class NotificationFragmentPageAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragmentList;

    public NotificationFragmentPageAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new NewLeadNotificationFragment();
                break;
            case 1:
                fragment = new TodayFollowupNotificationFragment();
                break;
            case 2 :
                fragment = new PendingLiveLeadsNotificationFragment();
                break;
            case 3 :
                fragment = new PendingNewLeadsNotificationFragment();
                break;
            case 4:
                fragment = new HomeVisitTodayFragment();
                break;
            case 5:
                fragment = new ShowroomVisitTodayFragment();
                break;
            case 6:
                fragment = new TestDriveTodayFragment();
                break;
            case 7:
                fragment = new EvaluationTodayFragment();
                break;
            case 8:
                fragment = new AllLeadFragment();
                break;
        }
        return fragment;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title = "";
        switch(position){
            case 0 :
                title = "New Leads";
                break;
            case 1 :
                title = "Today's Follow-Up";
                break;
            case 2 :
                title = "Pending Follow-Up Leads";
                break;
            case 3:
                title = "Pending(New) Leads";
                break;
            case 4:
                title = "Home Visit Today";
                break;
            case 5:
                title = "Showroom Visit Today";
                break;
            case 6:
                title = "Test Drive Today";
                break;
            case 7:
                title = "Evaluation Today";
                break;
            case 8:
                title = "All Leads";
                break;

        }
        return title;
    }
}

