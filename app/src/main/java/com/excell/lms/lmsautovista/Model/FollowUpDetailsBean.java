package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/22/2018.
*/

import java.util.ArrayList;

public class FollowUpDetailsBean {

    public ArrayList<FollowUp_details> getFollowup_details() {
        return followup_details;
    }

    public void setFollowup_details(ArrayList<FollowUp_details> followup_details) {
        this.followup_details = followup_details;
    }

    public ArrayList<FollowUp_details> followup_details;

    public static class FollowUp_details{
        String fname, lname, feedbackStatus, nextAction, contactibility, call_time, nextfollowuptime, assign_to, call_date, comment, nextfollowupdate, pick_up_date;
        String visit_status;
        String visit_location;
        String visit_booked;
        String visit_booked_date;
        String sale_status;
        String car_delivered;
        String escalation_type;
        String escalation_remark;

        String followup_id;
        String c_date;
        String f_comment;
        String created_time;

        public String getFollowup_id() {
            return followup_id;
        }

        public void setFollowup_id(String followup_id) {
            this.followup_id = followup_id;
        }

        public String getC_date() {
            return c_date;
        }

        public void setC_date(String c_date) {
            this.c_date = c_date;
        }

        public String getF_comment() {
            return f_comment;
        }

        public void setF_comment(String f_comment) {
            this.f_comment = f_comment;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
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

        public String getFeedbackStatus() {
            return feedbackStatus;
        }

        public void setFeedbackStatus(String feedbackStatus) {
            this.feedbackStatus = feedbackStatus;
        }

        public String getNextAction() {
            return nextAction;
        }

        public void setNextAction(String nextAction) {
            this.nextAction = nextAction;
        }

        public String getContactibility() {
            return contactibility;
        }

        public void setContactibility(String contactibility) {
            this.contactibility = contactibility;
        }

        public String getCall_time() {
            return call_time;
        }

        public void setCall_time(String call_time) {
            this.call_time = call_time;
        }

        public String getNextfollowuptime() {
            return nextfollowuptime;
        }

        public void setNextfollowuptime(String nextfollowuptime) {
            this.nextfollowuptime = nextfollowuptime;
        }

        public String getAssign_to() {
            return assign_to;
        }

        public void setAssign_to(String assign_to) {
            this.assign_to = assign_to;
        }

        public String getCall_date() {
            return call_date;
        }

        public void setCall_date(String call_date) {
            this.call_date = call_date;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getNextfollowupdate() {
            return nextfollowupdate;
        }

        public void setNextfollowupdate(String nextfollowupdate) {
            this.nextfollowupdate = nextfollowupdate;
        }

        public String getPick_up_date() {
            return pick_up_date;
        }

        public void setPick_up_date(String pick_up_date) {
            this.pick_up_date = pick_up_date;
        }

        public String getVisit_status() {
            return visit_status;
        }

        public void setVisit_status(String visit_status) {
            this.visit_status = visit_status;
        }

        public String getVisit_location() {
            return visit_location;
        }

        public void setVisit_location(String visit_location) {
            this.visit_location = visit_location;
        }

        public String getVisit_booked() {
            return visit_booked;
        }

        public void setVisit_booked(String visit_booked) {
            this.visit_booked = visit_booked;
        }

        public String getVisit_booked_date() {
            return visit_booked_date;
        }

        public void setVisit_booked_date(String visit_booked_date) {
            this.visit_booked_date = visit_booked_date;
        }

        public String getSale_status() {
            return sale_status;
        }

        public void setSale_status(String sale_status) {
            this.sale_status = sale_status;
        }

        public String getCar_delivered() {
            return car_delivered;
        }

        public void setCar_delivered(String car_delivered) {
            this.car_delivered = car_delivered;
        }

        public String getEscalation_type() {
            return escalation_type;
        }

        public void setEscalation_type(String escalation_type) {
            this.escalation_type = escalation_type;
        }

        public String getEscalation_remark() {
            return escalation_remark;
        }

        public void setEscalation_remark(String escalation_remark) {
            this.escalation_remark = escalation_remark;
        }



    }
}

