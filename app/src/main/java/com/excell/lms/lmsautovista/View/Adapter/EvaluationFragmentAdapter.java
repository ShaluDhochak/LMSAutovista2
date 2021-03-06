package com.excell.lms.lmsautovista.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.excell.lms.lmsautovista.View.Fragment.AllLeadFragment;
import com.excell.lms.lmsautovista.View.Fragment.NewLeadNotificationFragment;
import com.excell.lms.lmsautovista.View.Fragment.PendingLiveLeadsNotificationFragment;
import com.excell.lms.lmsautovista.View.Fragment.PendingNewLeadsNotificationFragment;
import com.excell.lms.lmsautovista.View.Fragment.TodayFollowupNotificationFragment;

import java.util.List;

/**
 * Created by Shalu Dhochak on 7/6/2018.
 */

public class EvaluationFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragmentList;

    public EvaluationFragmentAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
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
                title = "All Leads";
                break;

        }
        return title;
    }
}


