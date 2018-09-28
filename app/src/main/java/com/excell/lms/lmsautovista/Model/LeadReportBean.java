package com.excell.lms.lmsautovista.Model;

/*
 Created by Shalu Dhochak on 5/4/2018.
*/

import java.util.ArrayList;

public class LeadReportBean {
    public static class Total_Count{
        String total_leads;

        public String getTotal_leads() {
            return total_leads;
        }

        public void setTotal_leads(String total_leads) {
            this.total_leads = total_leads;
        }

        String total_unassigned_leads;

        public String getTotal_unassigned_leads() {
            return total_unassigned_leads;
        }

        public void setTotal_unassigned_leads(String total_unassigned_leads) {
            this.total_unassigned_leads = total_unassigned_leads;
        }

        public String getTotal_pending_new_leads() {
            return total_pending_new_leads;
        }

        public void setTotal_pending_new_leads(String total_pending_new_leads) {
            this.total_pending_new_leads = total_pending_new_leads;
        }

        public String getTotal_pending_followup_leads() {
            return total_pending_followup_leads;
        }

        public void setTotal_pending_followup_leads(String total_pending_followup_leads) {
            this.total_pending_followup_leads = total_pending_followup_leads;
        }

        String total_pending_new_leads;
        String total_pending_followup_leads;

    }

    public static class Total_Feedback{
        String follow_up,interested,busy,lost_to_co_dealer ;

        public String getFollow_up() {
            return follow_up;
        }

        public void setFollow_up(String follow_up) {
            this.follow_up = follow_up;
        }

        public String getInterested() {
            return interested;
        }

        public void setInterested(String interested) {
            this.interested = interested;
        }

        public String getBusy() {
            return busy;
        }

        public void setBusy(String busy) {
            this.busy = busy;
        }

        public String getLost_to_co_dealer() {
            return lost_to_co_dealer;
        }

        public void setLost_to_co_dealer(String lost_to_co_dealer) {
            this.lost_to_co_dealer = lost_to_co_dealer;
        }
    }

    public ArrayList<Total_Feedback> total_feedback;
    public ArrayList<Total_Count> total_count;

    public ArrayList<Total_Feedback> getTotal_feedback() {
        return total_feedback;
    }

    public void setTotal_feedback(ArrayList<Total_Feedback> total_feedback) {
        this.total_feedback = total_feedback;
    }

    public ArrayList<Total_Count> getTotal_count() {
        return total_count;
    }

    public void setTotal_count(ArrayList<Total_Count> total_count) {
        this.total_count = total_count;
    }

}

