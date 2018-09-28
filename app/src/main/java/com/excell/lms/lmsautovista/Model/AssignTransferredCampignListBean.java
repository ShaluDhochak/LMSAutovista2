package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by User on 5/7/2018.
 */

public class AssignTransferredCampignListBean {

    public ArrayList<Assign_Transferred_Leads_all_Count> getAssign_transferred_leads_all_count() {
        return assign_transferred_leads_all_count;
    }

    public void setAssign_transferred_leads_all_count(ArrayList<Assign_Transferred_Leads_all_Count> assign_transferred_leads_all_count) {
        this.assign_transferred_leads_all_count = assign_transferred_leads_all_count;
    }

    public ArrayList<Assign_Transferred_Leads_source> getAssign_transferred_leads_source() {
        return assign_transferred_leads_source;
    }

    public void setAssign_transferred_leads_source(ArrayList<Assign_Transferred_Leads_source> assign_transferred_leads_source) {
        this.assign_transferred_leads_source = assign_transferred_leads_source;
    }

    public ArrayList<Assign_Transferred_Leads_all_Count> assign_transferred_leads_all_count;
    public ArrayList<Assign_Transferred_Leads_source> assign_transferred_leads_source;

    public static class Assign_Transferred_Leads_all_Count{
        public String getAcount() {
            return acount;
        }

        public void setAcount(String acount) {
            this.acount = acount;
        }

        String acount;
    }

    public static class Assign_Transferred_Leads_source{
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

}

