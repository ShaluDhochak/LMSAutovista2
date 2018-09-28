package com.excell.lms.lmsautovista.View;

/*
  Created by Shalu Dhochak on 2/17/2018.
*/

import com.excell.lms.lmsautovista.Model.AssignLocationBean;
import com.excell.lms.lmsautovista.Model.AssignNewLeadAssignUserBean;
import com.excell.lms.lmsautovista.Model.AssignNewLeadCampaignBean;
import com.excell.lms.lmsautovista.Model.AssignToBean;
import com.excell.lms.lmsautovista.Model.AssignToUserBean;
import com.excell.lms.lmsautovista.Model.AssignTransferLocationBean;
import com.excell.lms.lmsautovista.Model.AssignTransferredCampignListBean;
import com.excell.lms.lmsautovista.Model.AuditorRemarkBean;
import com.excell.lms.lmsautovista.Model.CallingTaskNewLeadBean;
import com.excell.lms.lmsautovista.Model.CarVariantBean;
import com.excell.lms.lmsautovista.Model.CheckFlowTransferLeadDetailBean;
import com.excell.lms.lmsautovista.Model.CorporateFinancialBean;
import com.excell.lms.lmsautovista.Model.CustomerDetailsBean;
import com.excell.lms.lmsautovista.Model.DSEDailyReportLocationBean;
import com.excell.lms.lmsautovista.Model.DSEDailyReportViewBean;
import com.excell.lms.lmsautovista.Model.DSEReportBean;
import com.excell.lms.lmsautovista.Model.DailyProductivityReportBean;
import com.excell.lms.lmsautovista.Model.DashboardCountBean;
import com.excell.lms.lmsautovista.Model.FeedbackListBean;
import com.excell.lms.lmsautovista.Model.FollowUpDetailsBean;
import com.excell.lms.lmsautovista.Model.FromUserAssignTransferBean;
import com.excell.lms.lmsautovista.Model.LeadReportBean;
import com.excell.lms.lmsautovista.Model.LeadSourceBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Model.MakeBean;
import com.excell.lms.lmsautovista.Model.MessageListBean;
import com.excell.lms.lmsautovista.Model.ModelBean;
import com.excell.lms.lmsautovista.Model.NewCarStock1Bean;
import com.excell.lms.lmsautovista.Model.NewCarStockBean;
import com.excell.lms.lmsautovista.Model.NewCarStockCountDetailsBean;
import com.excell.lms.lmsautovista.Model.NewCarStockLocationModel;
import com.excell.lms.lmsautovista.Model.NewStockFilterBean;
import com.excell.lms.lmsautovista.Model.NextActionListBean;
import com.excell.lms.lmsautovista.Model.POCCarStockCountBean;
import com.excell.lms.lmsautovista.Model.POCSpinnerBean;
import com.excell.lms.lmsautovista.Model.POCStockCountDetailListBean;
import com.excell.lms.lmsautovista.Model.POCStockModel;
import com.excell.lms.lmsautovista.Model.POCarStockBean;
import com.excell.lms.lmsautovista.Model.ProcessBean;
import com.excell.lms.lmsautovista.Model.QuotationDescriptionBean;
import com.excell.lms.lmsautovista.Model.QuotationLocationBean;
import com.excell.lms.lmsautovista.Model.QuotationModelBean;
import com.excell.lms.lmsautovista.Model.QuotationPackageBean;
import com.excell.lms.lmsautovista.Model.SearchCustomerBean;
import com.excell.lms.lmsautovista.Model.SearchTrackerListBean;
import com.excell.lms.lmsautovista.Model.TransferAssignToBean;
import com.excell.lms.lmsautovista.Model.TransferLocationBean;
import com.excell.lms.lmsautovista.Model.ViewMessageListBean;

public interface IView {

    interface LoginView{
        void showProgressDialog();
        void dismissProgressDialog();
        void loginSuccess();
        void loginFailure(String message);
        void showLocationList(LoginBean jsonObject);
        void ShowDashboardCount(DashboardCountBean jsonObject);
        void showLocationDashboard(LocationDashboardBean jsonObject);
        void showProcessDashboard(LoginBean jsonObject);
        void showLocationDashboardCount(LocationWiseDashboardCountBean jsonObject);
        void showLocationwithoutSeelctionCount(LocationWiseDashboardCountBean jsonObject);
    }

    interface DailyProductivityReportPresnter{
        void showLocationDashboard(LocationDashboardBean jsonObject);
        void showDashboardCount(DailyProductivityReportBean jsonObject);
    }
    
    interface AddNewLeadView{
        void addNewLeadSuccess();
        void showProcess(LoginBean jsonObject);
        void showLeadSource(LeadSourceBean jsonObject);
        void showLocation(AssignTransferLocationBean jsonObject);
        void showAssignTo(AssignToBean jsonObject);
    }

    interface DashboardView{
        void showProgressDialog();
        void dismissProgressDialog();
        void showDailyAppointmentsCount(String new_lead, String unassigend_leads, String call_today, String pending_newLeads, String pending_followup);
        void ShowDashboardCount(DashboardCountBean jsonObject);
    }

    interface DashboardDetailView{
        void ShowDashboardDetailCount(CallingTaskNewLeadBean jsonObject);
    }

    interface TrackerView{
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

    interface AuditorDetailsView{
        void ShowAuditorDetailsList(AuditorRemarkBean jsonObject);
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

    interface NewCarStockFilter{
        void shownewCarModelSpinner(NewCarStockLocationModel jsonObject);
        void showNewCarLocationSpinner(NewCarStockLocationModel jsonObject);
        void showNewCarStockCountList(NewCarStock1Bean jsonObject);
    }

    interface PocCarStockView{
        void showPOCCarStockList(POCarStockBean jsonObject);
        void showPocCarStockCount(POCCarStockCountBean jsonObject);
        void showPOCCarModelSpinner(POCStockModel jsonObject);
        void showPOCCarLocationSpinner(POCSpinnerBean jsonObject);
        void showPOCCarColorSpinner(POCSpinnerBean jsonObject);
        void showPOCCarFuelTypeSpinner(POCSpinnerBean jsonObject);
        void showPocMakeSpinner(MakeBean jsonObject);
    }

    interface PocCarStockCountDetailView{
        void showPOCCarStockCountList(POCStockCountDetailListBean jsonObject);
    }

    interface NewCarStockCountDetailView{
        void showNewCarStockCountList(NewCarStockCountDetailsBean jsonObject);
    }

    interface AddFollowUpView{
        void showFeedbackSpinnerList(FeedbackListBean jsonObject);
        void shownextActionAddFollowUpList(NextActionListBean jsonObject);
        void shownewCarModelSpinner(ModelBean jsonObject);
        void showNewCarVariantSpinner(CarVariantBean jsonObject);

        void showQuoatationLocSpinner(QuotationLocationBean jsonObject);
        void showQuoatationDescriptionSpinner(QuotationDescriptionBean jsonObject);
        void showQuotationModelSpinner(QuotationModelBean jsonObject);
        void showQuotationAccessoriesPackageSpinner(QuotationPackageBean jsonObject);

        void showTransferProcess(ProcessBean jsonObject);
        void showTransferLocation(TransferLocationBean jsonObject);
        void showTransferAssignTo(TransferAssignToBean jsonObject);

        void showEvaluationLocation(TransferLocationBean jsonObject);
        void showEvaluationAssignTo(TransferAssignToBean jsonObject);

        void showCarMake(MakeBean jsonObject);
        void showCarModel(ModelBean jsonObject);

        void showOldCarMake(MakeBean jsonObject);
        void showOldCarModel(ModelBean jsonObject);
        void showCustomerCorporateName(CorporateFinancialBean jsonObject);
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
        void showLocation(LocationDashboardBean jsonObject);
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
