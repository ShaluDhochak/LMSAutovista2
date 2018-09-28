package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

public class AuditorRemarkBean {

    public static class Auditor_Remark_Details{
        String remark_id;
        String lead_id;
        String user_id;
        String followup_pending;
        String call_received;
        String fake_updation;
        String service_feedback;
        String remark;
        String created_date;
        String lname;
        String fname;

        public String getRemark_id() {
            return remark_id;
        }

        public void setRemark_id(String remark_id) {
            this.remark_id = remark_id;
        }

        public String getLead_id() {
            return lead_id;
        }

        public void setLead_id(String lead_id) {
            this.lead_id = lead_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFollowup_pending() {
            return followup_pending;
        }

        public void setFollowup_pending(String followup_pending) {
            this.followup_pending = followup_pending;
        }

        public String getCall_received() {
            return call_received;
        }

        public void setCall_received(String call_received) {
            this.call_received = call_received;
        }

        public String getFake_updation() {
            return fake_updation;
        }

        public void setFake_updation(String fake_updation) {
            this.fake_updation = fake_updation;
        }

        public String getService_feedback() {
            return service_feedback;
        }

        public void setService_feedback(String service_feedback) {
            this.service_feedback = service_feedback;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }
    }

    public ArrayList<Auditor_Remark_Details> getAuditor_remark_detail() {
        return auditor_remark_detail;
    }

    public void setAuditor_remark_detail(ArrayList<Auditor_Remark_Details> auditor_remark_detail) {
        this.auditor_remark_detail = auditor_remark_detail;
    }

    public ArrayList<Auditor_Remark_Details> auditor_remark_detail;

}

