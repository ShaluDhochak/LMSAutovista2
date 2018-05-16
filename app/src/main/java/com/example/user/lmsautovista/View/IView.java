package com.example.user.lmsautovista.View;

/*
  Created by Shalu Dhochak on 2/17/2018.
*/

import com.example.user.lmsautovista.Model.AssignLocationBean;
import com.example.user.lmsautovista.Model.AssignNewLeadAssignUserBean;
import com.example.user.lmsautovista.Model.AssignNewLeadCampaignBean;
import com.example.user.lmsautovista.Model.AssignToBean;
import com.example.user.lmsautovista.Model.AssignToUserBean;
import com.example.user.lmsautovista.Model.AssignTransferLocationBean;
import com.example.user.lmsautovista.Model.AssignTransferredCampignListBean;
import com.example.user.lmsautovista.Model.CallingTaskNewLeadBean;
import com.example.user.lmsautovista.Model.CarVariantBean;
import com.example.user.lmsautovista.Model.CheckFlowTransferLeadDetailBean;
import com.example.user.lmsautovista.Model.CustomerDetailsBean;
import com.example.user.lmsautovista.Model.DSEDailyReportLocationBean;
import com.example.user.lmsautovista.Model.DSEDailyReportViewBean;
import com.example.user.lmsautovista.Model.DSEReportBean;
import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Model.FeedbackListBean;
import com.example.user.lmsautovista.Model.FollowUpDetailsBean;
import com.example.user.lmsautovista.Model.FromUserAssignTransferBean;
import com.example.user.lmsautovista.Model.LeadReportBean;
import com.example.user.lmsautovista.Model.LeadSourceBean;
import com.example.user.lmsautovista.Model.LocationDashboardBean;
import com.example.user.lmsautovista.Model.LoginBean;
import com.example.user.lmsautovista.Model.MakeBean;
import com.example.user.lmsautovista.Model.MessageListBean;
import com.example.user.lmsautovista.Model.ModelBean;
import com.example.user.lmsautovista.Model.NewCarStockBean;
import com.example.user.lmsautovista.Model.NewStockFilterBean;
import com.example.user.lmsautovista.Model.NextActionListBean;
import com.example.user.lmsautovista.Model.POCSpinnerBean;
import com.example.user.lmsautovista.Model.POCStockModel;
import com.example.user.lmsautovista.Model.POCarStockBean;
import com.example.user.lmsautovista.Model.ProcessBean;
import com.example.user.lmsautovista.Model.QuotationDescriptionBean;
import com.example.user.lmsautovista.Model.QuotationLocationBean;
import com.example.user.lmsautovista.Model.QuotationModelBean;
import com.example.user.lmsautovista.Model.SearchCustomerBean;
import com.example.user.lmsautovista.Model.SearchTrackerListBean;
import com.example.user.lmsautovista.Model.TransferAssignToBean;
import com.example.user.lmsautovista.Model.TransferLocationBean;
import com.example.user.lmsautovista.Model.ViewMessageListBean;

public interface IView {

    interface LoginView{
        void showProgressDialog();
        void dismissProgressDialog();
        void loginSuccess();
        void loginFailure(String message);
        void showLocationList(LoginBean jsonObject);
        void ShowDashboardCount(DashboardCountBean jsonObject);
        void showLocationDashboard(LocationDashboardBean jsonObject);
        void showProcessDashboard(ProcessBean jsonObject);
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
        void ShowCustomerDetailsList(CustomerDetailsBean jsonObject);
    }

    interface FollowUpDetailsView{
        void ShowFollowUpDetailsList(FollowUpDetailsBean jsonObject);
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

    interface NewCarStockView {
        void showNewCarStockList(NewCarStockBean jsonObject);
        void shownewCarModelSpinner(ModelBean jsonObject);
        void showNewCarLocationSpinner(NewStockFilterBean jsonObject);
        void showNewCarColorSpinner(NewStockFilterBean jsonObject);
        void showNewCarFuelTypeSpinner(NewStockFilterBean jsonObject);
    }

    interface PocCarStockView{
        void showPOCCarStockList(POCarStockBean jsonObject);
        void showPOCCarModelSpinner(POCStockModel jsonObject);
        void showPOCCarLocationSpinner(POCSpinnerBean jsonObject);
        void showPOCCarColorSpinner(POCSpinnerBean jsonObject);
        void showPOCCarFuelTypeSpinner(POCSpinnerBean jsonObject);
        void showPocMakeSpinner(MakeBean jsonObject);
    }

    interface AddFollowUpView{
        void showFeedbackSpinnerList(FeedbackListBean jsonObject);
        void shownextActionAddFollowUpList(NextActionListBean jsonObject);
        void shownewCarModelSpinner(ModelBean jsonObject);
        void showNewCarVariantSpinner(CarVariantBean jsonObject);

        void showQuoatationLocSpinner(QuotationLocationBean jsonObject);
        void showQuoatationDescriptionSpinner(QuotationDescriptionBean jsonObject);
        void showQuotationModelSpinner(QuotationModelBean jsonObject);

        void showTransferProcess(ProcessBean jsonObject);
        void showTransferLocation(TransferLocationBean jsonObject);
        void showTransferAssignTo(TransferAssignToBean jsonObject);

        void showCarMake(MakeBean jsonObject);
        void showCarModel(POCStockModel jsonObject);

        void showOldCarMake(MakeBean jsonObject);
        void showOldCarModel(POCStockModel jsonObject);

    }

    interface CustomerSearchView{
        void showSearchCustomerDetails(SearchCustomerBean jsonObject);
    }

    interface EditCustomerListView{
        void showEditCustomerList(SearchCustomerBean jsonObject);
    }
    interface DSEDailyView{
        void showLocation(DSEDailyReportLocationBean jsonObject);
        void ShowDseDailyReportView(DSEDailyReportViewBean jsonObject);
    }

    interface IViewMessageView{
        void showViewMessageList(ViewMessageListBean jsonObject);
    }

    interface IAddMessageView{
        void showAddViewMessagelist(MessageListBean jsonObject);
    }

    interface ISubmitMessageView{
        void showLocation(DSEDailyReportLocationBean jsonObject);
    }

    interface IDailyReportView{
        void showTrackerListView(SearchTrackerListBean jsonObject);
    }

    interface IMonthlyReportView{
        void showTrackerListView(SearchTrackerListBean jsonObject);
    }

    interface IDsewiseReportView{
        void showLocation(DSEDailyReportLocationBean jsonObject);
        void showDseWiseListView(DSEReportBean jsonObject);
    }

    interface ILeadReportView{
        void showLocation(DSEDailyReportLocationBean jsonObject);
        void showLeadReportView(LeadReportBean jsonObject);
    }

    interface IAssignNewLeadView{
        void showAssignToLocation(AssignLocationBean jsonObject);
        void showAssignToView(AssignNewLeadAssignUserBean jsonObject);
        void showCampaignListView(AssignNewLeadCampaignBean jsonObject);

    }

    interface IAssignTransferredLeadView{
        void showAssignTransferredLocation(AssignTransferLocationBean jsonObject);
        void showAssignFromSpinnerView(FromUserAssignTransferBean jsonObject);
        void showAssignToView(AssignToUserBean jsonObject);
        void showAssignToLocationView(AssignTransferLocationBean jsonObject);
        void showAssignCampaignlist(AssignTransferredCampignListBean jsonObject);
    }

    interface ICheckFlowView{
        void showCheckFlow(CheckFlowTransferLeadDetailBean jsonObject);
    }
}
