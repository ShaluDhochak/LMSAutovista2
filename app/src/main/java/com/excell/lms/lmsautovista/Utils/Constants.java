package com.excell.lms.lmsautovista.Utils;

/*
  Created by Shalu Dhochak on 2/17/2018.
*/

public interface Constants {
    //String BASE_URL = "https://autovista.in/all_lms_demo/index.php/api/";  //testing database
    String BASE_URL = "https://autovista.in/all_lms/index.php/api/";   // live database
    String LOGIN_URL = "login_user";
    String CHANGE_PASSWORD = "change_password";
    //API for Dashboard Location Spinner
    String DASHBOARD_LOCATION_SPINNER = "select_location";
    //API for AssignTo spinner
    String ASSIGN_TO_SPINNER = "select_user_for_new_customer";
    //API for Dashboard Count
    String DASHBOARD_COUNT = "dashboard_count";  //this is old one, not in use
    //api for locationwise Dashboard Count
    String LOCATION_DASHBOARD_COUNT = "new_dashboard";  //current dashboard
    //api for Daily Productivity Report
    String DAILY_PRODUCTIVITY_REPORT = "daily_productivity_report";
    //api for Unassigned Count
    String UNASSIGNED_DASHBOARD_DETAIL = "select_unassigned_lead";
    //api for New Lead Count
    String NEW_LEAD_DETAIL = "select_new_lead";
    //api for Call Today lead
    String CALL_TODAY_DASHBOARD = "select_today_followup_lead";
    //api for Pending All Lead
    String PENDING_NEW_LEAD_DASHBOARD = "select_pending_new_lead";
    //Api for Pending Followup Lead
    String PENDING_FOLLOW_UP_LEAD_DASHBOARD = "select_pending_followup_lead";
    //Api for All Lead
    String ALL_LAED_CALLING_TASK = "select_all_followup_lead";
    //Api for HomeVisit Lead
    String HOME_VISIT_DASHBOARD = "select_home_visit_today";
    //Api for Test Drive Lead
    String TEST_DRIVE_DASHBOARD ="select_test_drive_today";
    //api for Showroom Drive Lead
    String SHOWROOM_DRIVE_DASHBOARD = "select_showroom_visit_today";
    //api for Evaluation Visit
    String EVALUATION_DASHBOARD = "select_evaluation_today";
    //api for Escalation Level1
    String ESCALATION_lEVEL1_DASHBOARD = "select_escalation_level1";
    //api for Escalation Level2
    String ESCALATION_LEVEL2_DASHBOARD = "select_escalation_level2";
    //api for Escalation Level3
    String ESCALATION_LEVEL3_DASHBOARD = "select_escalation_level3";
    //api for Select Feedback
    String SELECT_FEEDBACK = "select_feedback";
    //api for Select_NextAction
    String SELECT_NEXTACTION = "select_next_action_feedback";
    //api for Search Tracker
    String SEARCH_TRACKER = "select_lead_tracker";
    //api for Campaign Lead Source List
    String SELECT_LEAD_SOURCE_SPINNER = "select_lead_source";
    //api for corporate Name
    String CORPORATE_NAME = "customer_corporate_name";
    //api for Add New Lead
    String ADD_NEW_LEAD = "add_customer";
    //api for New car Stock
    String NEW_CAR_STOCK = "new_car_stock";
    String STOCK_FILTER = "new_stock_spinner";
    String NEW_CAR_STOCK_FILTER = "new_stock_count";
    String NEW_CAR_STOCK_LOCATION = "new_stock_spinner";
    String NEW_CAR_STOCK_LIST = "new_stock_list";
    //api for POC stock
    String POC_CAR_STOCK = "poc_stock";
    String POC_CAR_STOCK_COUNT = "poc_stock_count";
    String POC_CAR_MODEL = "poc_stock_model";
    String POC_STOCK_SPINNER = "poc_stock_spinner";
    String POC_STOCK_LIST = "poc_stock_list";
    //api for Model
    String MODEL_SPINNER = "select_car_model";
    String VARAINT_SPINNER = "select_car_variant";
    //api for make
    String MAKE_SPINNER = "select_car_make";

    String PROCESS = "all_process";
    //Api for Quotation
    String QUOTATION_LOCATION= "quotation_location";
    String QUOTATION_MODEL = "quotation_model_name";
    String QUOATATION_DESCRIPTION = "quotation_description";
    String QUOTATION_ACCESSORIES_PACKAGE= "accessories_package";
    //api for Transfer
    String TRANSFER_LOCATION = "select_transfer_location";
    String TRANSFER_PROCESS = "evaluation_process_removed";
  //  String TRANSFER_PROCESS= "all_process";
    String TRANSFER_ASSIGN_TO = "select_transfer_to_user";
    //api for Customer Details
    String CUSTOMER_DETAILS = "customer_details";
    String FOLLOWUP_DETAILS = "followup_details";
    String ADD_FOLLOWUP_NEW_CAR = "insert_new_car_followup";
    String ADD_FOLLOWUP_USED_CAR = "insert_used_car_followup";
    String ADD_FOLLOWUP_EVALUATION_CAR ="insert_evaluation_followup";

    String ADD_ESCALATION_TYPE = "insert_escalation_detail";
    //api for Search Customer
    String CUSTOMER_SEARCH = "search_by_name_contact";
    String EDIT_SEARCH_OPERATION = "update_customer";
    String CHECK_FLOW = "searchCustomer_flow";
    //api for DSE_DAILY_REPORT_INSERT
    String DSE_DAILY_REPORT_INSERT = "insert_dse_daily_report";
    String DSE_DAILY_REPORT_VIEW = "daily_tracker_show_data";
    String DSE_DAILY_REPORT_LOCATION = "daliy_dse_tracker_location";
    //api for DSEWISE Report
    String DSEWISE_REPORT = "dsewiseReport";
    String LEAD_REPORT = "leadReport";
    //api for view Message
    String VIEW_MESSAGE = "message_home";
    String DELETE_MESSAGE = "message_delete";
    String MESSAGE_LIST = "message_list";
    String ADD_MESSAGE= "message_insert";
    //api for Assign New Lead
    String ASSIGN_LOCATION_CAMPAIGN = "assign_new_lead_spinner";
    String ASSIGN_ASSIGN_TO = "assign_new_lead_assign_user";
    String ASSIGN_SUBMIT = "assign_new_lead_update";
    String ASSIGN_LOCATION = "assign_new_lead_location";
    //api for assign transfer lead
    String ASSIGN_TRANSFER_SUBMIT = "assign_transferred_lead_update";
    String ASSIGN_TRANSFER_CAMPAIGN_LIST= "assign_transferred_lead_check_lead";
    String ASSIGN_TRANSFER_TO_USER= "assign_transferred_lead_to_user";
    String ASSIGN_TRANSFER_FROM_USER = "assign_transferred_lead_from_user";
    String ASSIGN_TRANSFER_LOCATION = "assign_transferred_lead_spinner";
    //api for Tome interval
    String DSE_MESSAGE_FOR_TIME_INTERVAL ="daily_dse_tracker_check_time";

    //api for complaint
    String ALL_LEAD_COMPLAINT = "select_all_followup_lead_complaint";

    //api for auditor
    String AUDITOR_INSERT = "insert_auditor_remark";
    String AUDITOR_DETAIL = "auditor_remark_detail";

    // Utils
    String USERPREFERENCE = "LMSfis_Preference";
    String USER_EMAIL = "email";
    String USER_PASSWORD = "password";
    String ROLE_ID = "role";
    String ROLE_NAME = "role_name";
    String PROCESS_ID_initial = "process_id";
    String PROCESS_NAME_INITIAL = "process_name";
    String TOKEN = "token";
    String PROCESS_ID = "process_id";
    String PROCESS_NAME = "process_name";
    String USER_ID = "id";
    String USER_NAME = "username";
    String LOCATION_SESSION ="location";
    String LOCATION_ID = "location_id";
    String LOCATION_NAME = "location_name";
}
