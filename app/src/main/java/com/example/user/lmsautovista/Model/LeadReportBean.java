package com.example.user.lmsautovista.Model;

/*
 Created by Shalu Dhochak on 5/4/2018.
*/

import java.util.ArrayList;

public class LeadReportBean {

    public ArrayList<Total_Feedback> total_feedback;
    public ArrayList<Total_Count> total_count;

    public ArrayList<Total_Count> getTotal_count() {
        return total_count;
    }

    public void setTotal_count(ArrayList<Total_Count> total_count) {
        this.total_count = total_count;
    }

    public ArrayList<Total_Feedback> getTotal_feedback() {
        return total_feedback;
    }

    public void setTotal_feedback(ArrayList<Total_Feedback> total_feedback) {
        this.total_feedback = total_feedback;
    }

    public static class Total_Count{
        String total_leads,total_unassigned_leads,total_pending_new_leads,total_pending_followup_leads,total_booking_30;
        String total_booking_60,total_booking_greater_60;

        public String getTotal_leads() {
            return total_leads;
        }

        public void setTotal_leads(String total_leads) {
            this.total_leads = total_leads;
        }

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

        public String getTotal_booking_30() {
            return total_booking_30;
        }

        public void setTotal_booking_30(String total_booking_30) {
            this.total_booking_30 = total_booking_30;
        }

        public String getTotal_booking_60() {
            return total_booking_60;
        }

        public void setTotal_booking_60(String total_booking_60) {
            this.total_booking_60 = total_booking_60;
        }

        public String getTotal_booking_greater_60() {
            return total_booking_greater_60;
        }

        public void setTotal_booking_greater_60(String total_booking_greater_60) {
            this.total_booking_greater_60 = total_booking_greater_60;
        }
    }

    public static class Total_Feedback{
        String booked_from_autovista, follow_up,home_visit,showroom_visit,test_drive,evaluation_allotted,deal;
        String undecided,not_interested,already_booked_from_us,lost_to_codealer,color_model_availability,budget_issue;
        String nearest_dealership,outstation_purchase,plan_cancel;

        public String getBooked_from_autovista() {
            return booked_from_autovista;
        }

        public void setBooked_from_autovista(String booked_from_autovista) {
            this.booked_from_autovista = booked_from_autovista;
        }

        public String getFollow_up() {
            return follow_up;
        }

        public void setFollow_up(String follow_up) {
            this.follow_up = follow_up;
        }

        public String getHome_visit() {
            return home_visit;
        }

        public void setHome_visit(String home_visit) {
            this.home_visit = home_visit;
        }

        public String getShowroom_visit() {
            return showroom_visit;
        }

        public void setShowroom_visit(String showroom_visit) {
            this.showroom_visit = showroom_visit;
        }

        public String getTest_drive() {
            return test_drive;
        }

        public void setTest_drive(String test_drive) {
            this.test_drive = test_drive;
        }

        public String getEvaluation_allotted() {
            return evaluation_allotted;
        }

        public void setEvaluation_allotted(String evaluation_allotted) {
            this.evaluation_allotted = evaluation_allotted;
        }

        public String getDeal() {
            return deal;
        }

        public void setDeal(String deal) {
            this.deal = deal;
        }

        public String getUndecided() {
            return undecided;
        }

        public void setUndecided(String undecided) {
            this.undecided = undecided;
        }

        public String getNot_interested() {
            return not_interested;
        }

        public void setNot_interested(String not_interested) {
            this.not_interested = not_interested;
        }

        public String getAlready_booked_from_us() {
            return already_booked_from_us;
        }

        public void setAlready_booked_from_us(String already_booked_from_us) {
            this.already_booked_from_us = already_booked_from_us;
        }

        public String getLost_to_codealer() {
            return lost_to_codealer;
        }

        public void setLost_to_codealer(String lost_to_codealer) {
            this.lost_to_codealer = lost_to_codealer;
        }

        public String getColor_model_availability() {
            return color_model_availability;
        }

        public void setColor_model_availability(String color_model_availability) {
            this.color_model_availability = color_model_availability;
        }

        public String getBudget_issue() {
            return budget_issue;
        }

        public void setBudget_issue(String budget_issue) {
            this.budget_issue = budget_issue;
        }

        public String getNearest_dealership() {
            return nearest_dealership;
        }

        public void setNearest_dealership(String nearest_dealership) {
            this.nearest_dealership = nearest_dealership;
        }

        public String getOutstation_purchase() {
            return outstation_purchase;
        }

        public void setOutstation_purchase(String outstation_purchase) {
            this.outstation_purchase = outstation_purchase;
        }

        public String getPlan_cancel() {
            return plan_cancel;
        }

        public void setPlan_cancel(String plan_cancel) {
            this.plan_cancel = plan_cancel;
        }
    }
}

