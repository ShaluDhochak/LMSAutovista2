package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 5/30/2018.
*/

import java.util.ArrayList;

public class DailyProductivityReportBean {

    public ArrayList<Daily_Productivity_resport> getDaily_productivity_report() {
        return daily_productivity_report;
    }

    public void setDaily_productivity_report(ArrayList<Daily_Productivity_resport> daily_productivity_report) {
        this.daily_productivity_report = daily_productivity_report;
    }

    public ArrayList<Daily_Productivity_resport> daily_productivity_report;


    public static class Daily_Productivity_resport{
        String id;
        String role;
        String location_name;
        String fname;
        String lname;
        String total_called;
        String total_connected;
        String total_not_connected;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getTotal_called() {
            return total_called;
        }

        public void setTotal_called(String total_called) {
            this.total_called = total_called;
        }

        public String getTotal_connected() {
            return total_connected;
        }

        public void setTotal_connected(String total_connected) {
            this.total_connected = total_connected;
        }

        public String getTotal_not_connected() {
            return total_not_connected;
        }

        public void setTotal_not_connected(String total_not_connected) {
            this.total_not_connected = total_not_connected;
        }

    }

}


