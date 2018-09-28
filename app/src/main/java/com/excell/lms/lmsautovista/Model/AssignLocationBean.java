package com.excell.lms.lmsautovista.Model;

/*
 Created by Shalu Dhochak on 5/5/2018.
*/

import java.util.ArrayList;

public class AssignLocationBean {
    public ArrayList<Process_All_Location> process_all_location;

    public ArrayList<Process_All_Location> getProcess_all_location() {
        return process_all_location;
    }
    public void setProcess_all_location(ArrayList<Process_All_Location> process_all_location) {
        this.process_all_location = process_all_location;
    }
    public static class Process_All_Location {
        String location_id, location;

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
    }
}
