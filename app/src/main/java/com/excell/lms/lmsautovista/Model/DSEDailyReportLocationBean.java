package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 4/30/2018.
 */

public class DSEDailyReportLocationBean {

    public ArrayList<Daily_Dse_Tracker_Location> daliy_dse_tracker_location;

    public ArrayList<Daily_Dse_Tracker_Location> getDaliy_dse_tracker_location() {
        return daliy_dse_tracker_location;
    }

    public void setDaliy_dse_tracker_location(ArrayList<Daily_Dse_Tracker_Location> daliy_dse_tracker_location) {
        this.daliy_dse_tracker_location = daliy_dse_tracker_location;
    }

    public static class Daily_Dse_Tracker_Location{
        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        String location_id,location;

    }

}
