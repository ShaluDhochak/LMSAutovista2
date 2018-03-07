package com.example.user.lmsautovista.Presenter;

import android.content.Context;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.LoginBean;

/**
 * Created by User on 2/17/2018.
 */

public interface IPresenter {
    interface ILoginPresenter {
        void checkLogin(String emailId, String password, Context context);
        void saveUserInfo(String email, String password, LoginBean jsonObject, SharedPreferenceManager sharedPreferenceManager);
        void saveProcessInfo(String process_id, String process_name, SharedPreferenceManager sharedPreferenceManager);
    }

    interface IDashboardPresenter{
        void getDashboardList(Context context);

    }

    interface IDashboardDetailPresnter{
        void getUnassignedDashboardDetailList(Context context);
        void getNewDashboardDetailList(Context context);
        void getCallTodayDashboardDetailList(Context context);
        void getPendingNewDashboardDetailList(Context context);
        void getPendingFollowUpDashboardDetailList(Context context);
        void getAllLeadCallingTaskList(Context context);
    }

    interface ITrackerPresenter{
        void getCompaniesList(Context context);
        void getNextActionList(Context context, String selectedCompany);

        void getSearchTRackerList(Context context);
    }

}
