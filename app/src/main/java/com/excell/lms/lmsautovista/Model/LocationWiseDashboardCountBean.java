package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 5/29/2018.
 */

public class LocationWiseDashboardCountBean {
    public ArrayList<New_Dashboard> getNew_dashboard() {
        return new_dashboard;
    }

    public void setNew_dashboard(ArrayList<New_Dashboard> new_dashboard) {
        this.new_dashboard = new_dashboard;
    }

    public ArrayList<New_Dashboard> new_dashboard;
    public static class New_Dashboard{
        String id,role,location_name,fname,lname,unassigned_leads,new_leads,call_today,pending_new_leads,pending_followup;
        String evaluation_count;
        String test_drive_count;
        String home_visit_count;
        String showroom_visit_count;
        String escalation_level_1;
        String escalation_level_2;
        String escalation_level_3;

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

        public String getUnassigned_leads() {
            return unassigned_leads;
        }

        public void setUnassigned_leads(String unassigned_leads) {
            this.unassigned_leads = unassigned_leads;
        }

        public String getNew_leads() {
            return new_leads;
        }

        public void setNew_leads(String new_leads) {
            this.new_leads = new_leads;
        }

        public String getCall_today() {
            return call_today;
        }

        public void setCall_today(String call_today) {
            this.call_today = call_today;
        }

        public String getPending_new_leads() {
            return pending_new_leads;
        }

        public void setPending_new_leads(String pending_new_leads) {
            this.pending_new_leads = pending_new_leads;
        }

        public String getPending_followup() {
            return pending_followup;
        }

        public void setPending_followup(String pending_followup) {
            this.pending_followup = pending_followup;
        }

        public String getEvaluation_count() {
            return evaluation_count;
        }

        public void setEvaluation_count(String evaluation_count) {
            this.evaluation_count = evaluation_count;
        }

        public String getTest_drive_count() {
            return test_drive_count;
        }

        public void setTest_drive_count(String test_drive_count) {
            this.test_drive_count = test_drive_count;
        }

        public String getHome_visit_count() {
            return home_visit_count;
        }

        public void setHome_visit_count(String home_visit_count) {
            this.home_visit_count = home_visit_count;
        }

        public String getShowroom_visit_count() {
            return showroom_visit_count;
        }

        public void setShowroom_visit_count(String showroom_visit_count) {
            this.showroom_visit_count = showroom_visit_count;
        }

        public String getEscalation_level_1() {
            return escalation_level_1;
        }

        public void setEscalation_level_1(String escalation_level_1) {
            this.escalation_level_1 = escalation_level_1;
        }

        public String getEscalation_level_2() {
            return escalation_level_2;
        }

        public void setEscalation_level_2(String escalation_level_2) {
            this.escalation_level_2 = escalation_level_2;
        }

        public String getEscalation_level_3() {
            return escalation_level_3;
        }

        public void setEscalation_level_3(String escalation_level_3) {
            this.escalation_level_3 = escalation_level_3;
        }
    }
}

