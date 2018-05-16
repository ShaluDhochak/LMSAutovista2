package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 5/4/2018.
 */

public class AssignNewLeadCampaignBean {

    public static class Process_All_Location{
        String location_id;
        String location;

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

    public static class Assign_New_Leads_All_Count{
        public String getAcount() {
            return acount;
        }

        public void setAcount(String acount) {
            this.acount = acount;
        }

        String acount;
    }

    public static class Assign_New_Leads_Source{
        String lead_source,enquiry_for,wcount;

        public String getLead_source() {
            return lead_source;
        }

        public void setLead_source(String lead_source) {
            this.lead_source = lead_source;
        }

        public String getEnquiry_for() {
            return enquiry_for;
        }

        public void setEnquiry_for(String enquiry_for) {
            this.enquiry_for = enquiry_for;
        }

        public String getWcount() {
            return wcount;
        }

        public void setWcount(String wcount) {
            this.wcount = wcount;
        }
    }

    public ArrayList<Process_All_Location> getProcess_all_location() {
        return process_all_location;
    }

    public void setProcess_all_location(ArrayList<Process_All_Location> process_all_location) {
        this.process_all_location = process_all_location;
    }

    public ArrayList<Process_All_Location> process_all_location;

    public ArrayList<Assign_New_Leads_All_Count> getAssign_new_leads_all_count() {
        return assign_new_leads_all_count;
    }

    public void setAssign_new_leads_all_count(ArrayList<Assign_New_Leads_All_Count> assign_new_leads_all_count) {
        this.assign_new_leads_all_count = assign_new_leads_all_count;
    }

    public ArrayList<Assign_New_Leads_All_Count> assign_new_leads_all_count;

    public ArrayList<Assign_New_Leads_Source> getAssign_new_leads_source() {
        return assign_new_leads_source;
    }

    public void setAssign_new_leads_source(ArrayList<Assign_New_Leads_Source> assign_new_leads_source) {
        this.assign_new_leads_source = assign_new_leads_source;
    }

    public ArrayList<Assign_New_Leads_Source> assign_new_leads_source;
}

