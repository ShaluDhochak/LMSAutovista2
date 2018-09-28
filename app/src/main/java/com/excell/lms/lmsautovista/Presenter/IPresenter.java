package com.excell.lms.lmsautovista.Presenter;

import android.content.Context;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Model.NewCarStock1Bean;
import com.excell.lms.lmsautovista.Model.POCCarStockCountBean;

/*
  Created by Shalu Dhochak on 2/17/2018.
*/

public interface IPresenter {

    interface ILoginPresenter {
        void checkLogin(String emailId, String password, Context context);
        void saveUserInfo(String email, String password, LoginBean jsonObject, SharedPreferenceManager sharedPreferenceManager);
        void saveProcessInfo(String process_id, String process_name, SharedPreferenceManager sharedPreferenceManager);
        void saveLocationInfo(String location_id, String location_name, SharedPreferenceManager sharedPreferenceManager);
        void getLocationList(Context context);
    }

    interface IDashboardPresenter{
        void getDashboardHeaderLocationList(Context context);
        void getDashboardProcessList(Context context);
        void getDashboardList(Context context);
        void getLocationSpinnerList(String location, String process,Context context);
        void getDashboardLocationList(String location_id ,Context context);
    }

    interface ILocationWiseDashboardPresenter{
        void getDashboardCountLocationList(String user_type,String location_id ,Context context);
        void getDashboardWithoutSelectionDseList(String location_id, Context context);
    }

    interface IDailyProductivityReportPresnter{
        void getDailyProductivityReportList(String user_type, String location_id, Context context);
        void getLocationSpinnerList(Context context);
    }

    interface IDashboardDetailPresnter{
        void getUnassignedDashboardDetailList(String role, String user_id,Context context);
        void getNewDashboardDetailList(String role, String user_id,Context context);
        void getCallTodayDashboardDetailList(String role, String user_id,Context context);
        void getPendingNewDashboardDetailList(String role, String user_id,Context context);
        void getPendingFollowUpDashboardDetailList(String role, String user_id,Context context);
        void getAllLeadCallingTaskList(String role, String user_id,Context context);

        void getHomevisitDashboardList(String role, String user_id, Context context);
        void getTestDriveDashboardList(String role, String user_id, Context context);
        void getEvaluationTodayDashboardList(String role, String user_id, Context context);
        void getShowroomDriveDashboardList(String role, String user_id, Context context);

        void getEscalationLevel1DashboardList(String role, String user_id, Context context);
        void getEscalationLevel2DashboardList(String role, String user_id, Context context);
        void getEscalationLevel3DashboardList(String role, String user_id, Context context);
    }

    interface ITrackerPresenter{
        void getCompaniesList(Context context);
        void getNextActionList(Context context, String selectedCompany);
        void getCampaignList(Context context);
        void getSearchTRackerList(Context context, String campaignName, String feedback, String nextAction, String dateType, String fromdate, String todate);
    }

    interface IAddNewLeadOresenter{
        void getProcess(Context context);
        void getAssignTo(String process, String location,Context context);
        void getLocation(String process, Context context);
        void getLeadSource(String process, Context context);
        void getAssignToLocation(String process_id,Context context);
    }

    interface INewLeadCallingTaskPresenter{
        void getNewCallingTaskList(Context context);
        void getHomeVisitTodayList(Context context);
        void getShowroomVisitTodayList(Context context);
        void getEvaluationVisitList(Context context);
        void getTestDriveTodayList(Context context);
    }

    interface ITodayCallingTaskPresenter{
        void getTodayCallingTaskList(Context context);
        void getCurrentTodayTaskList(final Context context);
    }

    interface IPendingNewCallingTaskPresenter{
        void getPendingCallingTaskList(Context context);
    }

    interface IPendingLiveCallingTaskPresenter{
        void getPendingCallingTaskList(Context context);
    }

    interface IAllLeadCallingTaskPresenter{
        void getAllLeadCallingTaskList(String page_no,Context context);
        void getAllSearchedLeadCallingTask(String contact_n, String page_no,Context context);
    }

    interface INewCarStockViewPresenter{
        void getnewCarStockList(String model,String location,String color,String fuelType, Context context);
        void getNewCarStockList(Context context);
        void getLocation(Context context);
        void getFuelType(Context context);
        void getColor(Context context);
        void getModel(Context context);
    }

    interface INewCarStock1Presenter{
        void getLocation(Context context);
        void getModel(Context context);
        void getNewCarStockEmptyCount(Context context);
        void getNewCarStockCount(String model, String assigned_location, String vehicle_status, String ageing, String price, Context context);
        void showProcessData(String model, String assigned_location, String vehicl_status, String ageing, String price, NewCarStock1Bean jsonObject, SharedPreferenceManager sharedPreferenceManager );
    }

    interface IPOCCarStockViewPresenter{
        void getPocCarStockList(String make,String model,String location,String color,String fuelType, Context context);
        void getPocCarStockCount(String make, String model, String stock_location, String mfg_year, String owner, String ageing, String price, Context context);
        void getPocCarStockList(Context context);
        void getLocation(Context context);
        void getFuelType(Context context);
        void getColor(Context context);
        void getModel(String make,Context context);
        void getMake(Context context);
        void getPocCarStockFirstCount(Context context);

        void saveProcessInfo(String make, String model, String stock_location, String mfg_year, String owner, String ageing, String price, POCCarStockCountBean jsonObject, SharedPreferenceManager sharedPreferenceManager);
    }

    interface IPOCStockCarCountPresenter{
        void getPocCarStockCount( String model, String stock_location, String mfg_year, String owner, String ageing, String price, Context context);
        void getPocCarStockCount1( Context context);
        void getPocCarStockMakeCount1(String model, Context context);
    }

    interface INewCarStockCountPresenter{
        void getNewCarStockCountDetail(Context context);
        void getNewCarStockCountModelDetail(String model, Context context);
    }

    interface ICustomerDetailsPresenter{
        void getCustomerDetailsList(String enq_id,Context context);
    }

    interface IFollowUpDetailsPresenter{
        void getFollowUpDetailsList(String enq_id, Context context);
    }

    interface IAuditorDetailsPresenter{
        void getAuditorDetailsList(String enq_id, Context context);
    }

    interface IAddFollowUpPresenter{
        void getFeedbackList(Context context);
        void getNextActionList(Context context, String selectedFeedback);
        void getNewCarModel(String make,Context context);
        void getNewVariant(String model,Context context);
        void getQuotationLocation(Context context);
        void getQuotationDescription(String location,String model,Context context);
        void getQuotataionModel(String location, Context context);
        void getQuotationAccessoriesPackage(String model, Context context);
        void getTransferProcess(Context context);
        void getTransferLocation(String process,Context context);
        void getTransferAssignTo(String process, String location, Context context);
        void getUsedcarMake(Context context);
        void getUsedCarModel(String make_id,Context context);
        void getOldCarMake(Context context);
        void getOldCarModel(String make_id, Context context);
        void getCorporateName(Context context);

        void getEvaluationLocation(String process, Context context);
        void getEvaluationAssignTo(String process, String location, Context context);

    }

    interface ISearchCustomerPresenter{
       void getSearchViaContactNoList(String contact_no,Context context);
    }

    interface IEditCustomerOperationPresenter{
        void getEditCustomerList(String contact_no, Context context);
    }

    interface IAddMessagePresenter{
        void getAddViewMessageList(Context context);
    }

    interface IAssignNewLeadPresenter{
      void getAssignLocation(Context context);
      void getAssignToList(String location_id, Context context);
      void getCamapignList(Context context);
    }

    interface IAssignTransferLeadPresenter{
        void getAssignLocation(Context context);
        void getAssignFromList(String location_id, Context context);
        void getAssignToList(String location_id,String fromUser,Context context);
        void getAssignToLocation(Context context);
        void getAssignCampaignList(String fromUser, Context context);
    }

    interface ICheckFlowCustomer{
        void getCheckFlow(String enq_id, Context context);
    }

    interface IDailyReportPresenter{
        void getSearchTRackerList(Context context, String currentDate);

    }

    interface IDSEDailyReportViewPresenter{
        void getLocation(String process,Context context);
        void getSearchViaContactNoList(String status, String location_id, String fromdate,Context context);

    }

    interface IDsewiseReportPresenter{
        void getLocation( Context context);
        void getSearchViaContactNoList(String location_id, String fromdate, String todate, Context context);

    }

    interface ILeadReportPresenter{
        void getLeadLocation(Context context);
        void getSearchVialOcationList(String location_id, String fromdate, String todate, Context context);
    }

    interface IMonthlyReportPresenter{
        void getSearchTRackerList(Context context, String currentDate);
    }

    interface ISubmitMessagepresenter{
        void getLocation( Context context);

    }

    interface  IViewMessagePresenter{
        void getViewMessageList(Context context);
    }
}
