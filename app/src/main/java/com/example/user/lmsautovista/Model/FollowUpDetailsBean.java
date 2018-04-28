package com.example.user.lmsautovista.Model;

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
        String fname,lname,feedbackStatus,nextAction,assign_to,call_date,comment,nextfollowupdate,pick_up_date,visit_status;
        String visit_location,car_delivered,visit_booked,visit_booked_date,sale_status;

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
    }
}

