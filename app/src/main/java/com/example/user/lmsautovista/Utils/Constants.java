package com.example.user.lmsautovista.Utils;

/*
  Created by Shalu Dhochak on 2/17/2018.
*/

public interface Constants {

    // API
    String BASE_URL = "http://vistacars.in/all_lms/index.php/api/";
    String BASE_IMAGE_URL = "";
    String LOGIN_URL = "login_user";
    String CHANGE_PASSWORD = "change_password";

    //API for Dashboard Location Spinner
    String DASHBOARD_LOCATION_SPINNER = "select_location";
    //API for AssignTo spinner
    String ASSIGN_TO_SPINNER = "select_user_for_new_customer";

    //API for Dashboard Count
    String DASHBOARD_COUNT = "dashboard_count";
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

    //api for Select Feedback
    String SELECT_FEEDBACK = "select_feedback";
    //api for Select_NextAction
    String SELECT_NEXTACTION = "select_next_action_feedback";
    //api for Search Tracker
    String SEARCH_TRACKER = "select_lead_tracker";
    //api for Campaign Lead Source List
    String SELECT_LEAD_SOURCE_SPINNER = "select_lead_source";

    //api for Add New Lead
    String ADD_NEW_LEAD = "add_customer";

    //api for New car Stock
    String NEW_CAR_STOCK = "new_car_stock";
    String STOCK_FILTER = "new_stock_spinner";

    //api for POC stock
    String POC_CAR_STOCK = "poc_stock";
    String POC_CAR_MODEL = "poc_stock_model";
    String POC_STOCK_SPINNER = "poc_stock_spinner";

    //api for Model
    String MODEL_SPINNER = "select_car_model";
    String VARAINT_SPINNER = "select_car_variant";
    //api for make
    String MAKE_SPINNER = "select_car_make";

    //Api for Quotation
    String QUOTATION_LOCATION= "quotation_location";
    String QUOTATION_MODEL = "quotation_model_name";
    String QUOATATION_DESCRIPTION = "quotation_description";

    //api for Transfer
    String TRANSFER_LOCATION = "select_transfer_location";
    String TRANSFER_PROCESS= "all_process";
    String TRANSFER_ASSIGN_TO = "select_transfer_to_user";

    //api for Customer Details
    String CUSTOMER_DETAILS = "customer_details";
    String FOLLOWUP_DETAILS = "followup_details";
    String ADD_FOLLOWUP_NEW_CAR = "insert_new_car_followup";
    String ADD_FOLLOWUP_USED_CAR = "insert_used_car_followup";

    //api for Search Customer
    String CUSTOMER_SEARCH = "search_by_name_contact";
    String EDIT_SEARCH_OPERATION = "update_customer";

    //api for DSE_DAILY_REPORT_INSERT
    String DSE_DAILY_REPORT_INSERT = "insert_dse_daily_report";
    String DSE_DAILY_REPORT_VIEW = "daily_tracker_show_data";
    String DSE_DAILY_REPORT_LOCATION = "daliy_dse_tracker_location";

    // Utils
    String USERPREFERENCE = "LMSfis_Preference";
    String USER_EMAIL = "email";
    String USER_PASSWORD = "password";
    String ROLE_ID = "role";
    String PROCESS_ID_initial = "process_id";
    String PROCESS_NAME_INITIAL = "process_name";
    String PROCESS_ID = "process_id";
    String PROCESS_NAME = "process_name";
    String USER_ID = "id";
    String USER_NAME = "username";
    String LOCATION_SESSION ="location";
    String LOCATION_ID = "location_id";
    String LOCATION_NAME = "location_name";

}
