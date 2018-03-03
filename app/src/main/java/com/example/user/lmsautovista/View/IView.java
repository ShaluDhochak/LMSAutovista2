package com.example.user.lmsautovista.View;

/*
  Created by User on 2/17/2018.
*/

import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Model.DashboardLeadDetailBean;
import com.example.user.lmsautovista.Model.FeedbackListBean;
import com.example.user.lmsautovista.Model.NextActionListBean;

public interface IView {

    interface LoginView{
        void showProgressDialog();
        void dismissProgressDialog();
        void loginSuccess();
        void loginFailure(String message);
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
        void ShowDashboardDetailCount(DashboardLeadDetailBean jsonObject);
    }

    interface TrackerView{
        void showProgressDialog();
        void dismissProgressDialog();
        void showFeedbackView(FeedbackListBean jsonObject);
        void showNextActionView(NextActionListBean jsonObject);
    }
}
