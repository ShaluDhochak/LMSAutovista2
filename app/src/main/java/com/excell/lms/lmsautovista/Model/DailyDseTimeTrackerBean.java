package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by User on 5/17/2018.
 */

public class DailyDseTimeTrackerBean {

    public ArrayList<Daily_Dse_Tracker_Time> getDaliy_dse_tracker_time() {
        return daliy_dse_tracker_time;
    }

    public void setDaliy_dse_tracker_time(ArrayList<Daily_Dse_Tracker_Time> daliy_dse_tracker_time) {
        this.daliy_dse_tracker_time = daliy_dse_tracker_time;
    }

    public ArrayList<Daily_Dse_Tracker_Time> daliy_dse_tracker_time;

    public static class Daily_Dse_Tracker_Time{
        String report_time,user_id;

        public String getReport_time() {
            return report_time;
        }

        public void setReport_time(String report_time) {
            this.report_time = report_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }

}


