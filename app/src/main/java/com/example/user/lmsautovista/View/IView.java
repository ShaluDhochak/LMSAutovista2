package com.example.user.lmsautovista.View;

/*
  Created by User on 2/17/2018.
*/

import com.example.user.lmsautovista.Model.AssignToBean;
import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Model.FeedbackListBean;
import com.example.user.lmsautovista.Model.LeadSourceBean;
import com.example.user.lmsautovista.Model.LocationDashboardBean;
import com.example.user.lmsautovista.Model.LoginBean;
import com.example.user.lmsautovista.Model.NextActionListBean;
import com.example.user.lmsautovista.Model.SearchTrackerListBean;

public interface IView {

    interface LoginView{
        void showProgressDialog();
        void dismissProgressDialog();
        void loginSuccess();
        void loginFailure(String message);
        void showLocationList(LoginBean jsonObject);
        void ShowDashboardCount(DashboardCountBean jsonObject);
        void showLocationDashboard(LocationDashboardBean jsonObject);
    }

    interface AddNewLeadView{
        void showProgressDialog();
        void dismissProgressDialog();
        void addNewLeadSuccess();
        void showLeadSource(LeadSourceBean jsonObject);
        void showLocation(LocationDashboardBean jsonObject);
        void showAssignTo(AssignToBean jsonObject);

    }

    interface DashboardView{
        void showProgressDialog();
        void dismissProgressDialog();
        void showDailyAppointmentsCount(String new_lead, String unassigend_leads, String call_today, String pending_newLeads, String pending_followup);
        void ShowDashboardCount(DashboardCountBean jsonObject);

    }

    interface DashboardDetailView{
        void showProgressDialog();
        void dismissProgressDialog();
        void ShowDashboardDetailCount(CallingTaskNewLeadBean jsonObject);
    }

    interface TrackerView{
        void showProgressDialog();
        void dismissProgressDialog();
        void showFeedbackView(FeedbackListBean jsonObject);
        void showNextActionView(NextActionListBean jsonObject);
        void showTrackerListView(SearchTrackerListBean jsonObject);
        void showCampignListView(LeadSourceBean jsonObject);
    }

    interface NewLeadCallingTaskView{
        void showProgressDialog();
        void dismissProgressDialog();
        void ShowNewLeadDetailCount(CallingTaskNewLeadBean jsonObject);
    }

    interface CustomerDetailsTaskView{
        void ShowCustomerDetailsList(CallingTaskNewLeadBean jsonObject);
    }

    interface TodayLeadCallingTaskView{
        void ShowTodayLeadDetailCount(CallingTaskNewLeadBean jsonObject);
    }

    interface PendingTodayCallingTaskView{
        void ShowPendingLeadDetailCount(CallingTaskNewLeadBean jsonObject);
    }

    interface PendingLiveCallingTaskView{
        void showPendingLiveLeadDetails(CallingTaskNewLeadBean jsonObject);
    }

    interface AllLeadCallingTaskView{
        void showAllLeadDetails(CallingTaskNewLeadBean jsonObject);
    }
}
