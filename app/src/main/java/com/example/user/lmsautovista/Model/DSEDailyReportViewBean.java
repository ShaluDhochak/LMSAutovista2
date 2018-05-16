package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 4/30/2018.
 */

public class DSEDailyReportViewBean {
    public ArrayList<Daily_Tracker_Show_Data> daily_tracker_show_data;

    public ArrayList<Daily_Tracker_Show_Data> getDaily_tracker_show_data() {
        return daily_tracker_show_data;
    }

    public void setDaily_tracker_show_data(ArrayList<Daily_Tracker_Show_Data> daily_tracker_show_data) {
        this.daily_tracker_show_data = daily_tracker_show_data;
    }

    public static class Daily_Tracker_Show_Data{
        String traking_id,enquiry_count,enquiry_remark,walk_in_count,walk_in_remark,home_visit_count,home_visit_remark;
        String test_drive_count,test_drive_remark,booking_count,booking_remark,gatepass_count,gatepass_remark;
        String evaluation_count; String evaluation_remark;
        String delivery_count,delivery_remark,user_id,location_id,report_date;
        String report_time,status,fname,lname;

        public String getTraking_id() {
            return traking_id;
        }

        public void setTraking_id(String traking_id) {
            this.traking_id = traking_id;
        }

        public String getEnquiry_count() {
            return enquiry_count;
        }

        public void setEnquiry_count(String enquiry_count) {
            this.enquiry_count = enquiry_count;
        }

        public String getEnquiry_remark() {
            return enquiry_remark;
        }

        public void setEnquiry_remark(String enquiry_remark) {
            this.enquiry_remark = enquiry_remark;
        }

        public String getWalk_in_count() {
            return walk_in_count;
        }

        public void setWalk_in_count(String walk_in_count) {
            this.walk_in_count = walk_in_count;
        }

        public String getWalk_in_remark() {
            return walk_in_remark;
        }

        public void setWalk_in_remark(String walk_in_remark) {
            this.walk_in_remark = walk_in_remark;
        }

        public String getHome_visit_count() {
            return home_visit_count;
        }

        public void setHome_visit_count(String home_visit_count) {
            this.home_visit_count = home_visit_count;
        }

        public String getHome_visit_remark() {
            return home_visit_remark;
        }

        public void setHome_visit_remark(String home_visit_remark) {
            this.home_visit_remark = home_visit_remark;
        }

        public String getTest_drive_count() {
            return test_drive_count;
        }

        public void setTest_drive_count(String test_drive_count) {
            this.test_drive_count = test_drive_count;
        }

        public String getTest_drive_remark() {
            return test_drive_remark;
        }

        public void setTest_drive_remark(String test_drive_remark) {
            this.test_drive_remark = test_drive_remark;
        }

        public String getBooking_count() {
            return booking_count;
        }

        public void setBooking_count(String booking_count) {
            this.booking_count = booking_count;
        }

        public String getBooking_remark() {
            return booking_remark;
        }

        public void setBooking_remark(String booking_remark) {
            this.booking_remark = booking_remark;
        }

        public String getGatepass_count() {
            return gatepass_count;
        }

        public void setGatepass_count(String gatepass_count) {
            this.gatepass_count = gatepass_count;
        }

        public String getGatepass_remark() {
            return gatepass_remark;
        }

        public void setGatepass_remark(String gatepass_remark) {
            this.gatepass_remark = gatepass_remark;
        }

        public String getEvaluation_count() {
            return evaluation_count;
        }

        public void setEvaluation_count(String evaluation_count) {
            this.evaluation_count = evaluation_count;
        }

        public String getEvaluation_remark() {
            return evaluation_remark;
        }

        public void setEvaluation_remark(String evaluation_remark) {
            this.evaluation_remark = evaluation_remark;
        }

        public String getDelivery_count() {
            return delivery_count;
        }

        public void setDelivery_count(String delivery_count) {
            this.delivery_count = delivery_count;
        }

        public String getDelivery_remark() {
            return delivery_remark;
        }

        public void setDelivery_remark(String delivery_remark) {
            this.delivery_remark = delivery_remark;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public String getReport_date() {
            return report_date;
        }

        public void setReport_date(String report_date) {
            this.report_date = report_date;
        }

        public String getReport_time() {
            return report_time;
        }

        public void setReport_time(String report_time) {
            this.report_time = report_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
    }
}

