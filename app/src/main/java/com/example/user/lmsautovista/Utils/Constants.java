package com.example.user.lmsautovista.Utils;

/*
  Created by Shalu Dhochak on 2/17/2018.
 */

public interface Constants {
    // API
    String BASE_URL = "http://vistacars.in/lms/api/";
    String BASE_IMAGE_URL = "";
    String LOGIN_URL = "login_user";

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
    String LOCATION ="location";
}
