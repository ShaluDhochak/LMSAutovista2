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
        void saveLocationInfo(String location_id, String location_name, SharedPreferenceManager sharedPreferenceManager);
    }

    interface IDashboardPresenter{
        void getDashboardList(Context context);
        void getLocationList(Context context);
        void getLocationSpinnerList(Context context);
        void getDashboardLocationList(String location_id ,Context context);
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
        void getCampaignList(Context context);
        void getSearchTRackerList(Context context, String campaignName, String feedback, String nextAction, String dateType, String fromdate, String todate);
    }

    interface IAddNewLeadOresenter{
        void getAssignTo(String process, String location,Context context);
        void getLocation(String process, Context context);
        void getLeadSource(String process, Context context);
    }

    interface INewLeadCallingTaskPresenter{
        void getNewCallingTaskList(Context context);
    }

    interface ITodayCallingTaskPresenter{
        void getTodayCallingTaskList(Context context);
    }

    interface IPendingNewCallingTaskPresenter{
        void getPendingCallingTaskList(Context context);
    }

    interface IPendingLiveCallingTaskPresenter{
        void getPendingCallingTaskList(Context context);
    }

    interface IAllLeadCallingTaskPresenter{
        void getAllLeadCallingTaskList(Context context);
    }

    interface ICustomerDetailsPresenter{
        void getCustomerDetailsList(String enq_id,Context context);
    }

}
