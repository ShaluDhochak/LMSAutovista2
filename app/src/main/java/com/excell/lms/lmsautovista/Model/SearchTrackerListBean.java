package com.excell.lms.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 3/7/2018.
 */

public class SearchTrackerListBean implements Parcelable {

    public ArrayList<User_Details_Count> getUser_details_count() {
        return user_details_count;
    }

    public void setUser_details_count(ArrayList<User_Details_Count> user_details_count) {
        this.user_details_count = user_details_count;
    }

    public ArrayList<User_Details_Count> user_details_count;

    public ArrayList<User_Details> getUser_details() {
        return user_details;
    }

    public void setUser_details(ArrayList<User_Details> user_details) {
        this.user_details = user_details;
    }

    public ArrayList<User_Details> user_details;


    public static class User_Details_Count {
        public String getLead_count() {
            return lead_count;
        }

        public void setLead_count(String lead_count) {
            this.lead_count = lead_count;
        }

        String lead_count;
    }

    public static class User_Details implements Parcelable {
        String accessoires_list, assessories_price, model_name, service_center, service_type, pickup_required, pick_up_date, assistance, customer_location, days60_booking;
        String enq_id, lead_source, enquiry_for, process, name, contact_no, address, email, lead_date, feedbackStatus, nextAction, ownership;
        String budget_from, budget_to, accidental_claim, assign_by_cse_tl, assign_to_cse, reg_no, min_date, max_date, km, buyer_type, manf_year, cse_fname, cse_lname;
        String cse_role, csetl_fname, csetl_lname, csetl_role, showroom_location, assign_to_dse_tl, assign_to_dse;
        String dse_fname;
        String dse_lname;
        String dsetl_fname;
        String dse_role;
        String dsetl_lname;
        String dsetl_role;
        String dse_date;
        String dse_nfd;
        String dse_nftime;
        String dse_comment;
        String td_hv_date;
        String dsenextAction;
        String dsefeedback;
        String old_model_name;
        String make_name, variant_name, new_model_name, cse_date, cse_nfd, cse_nftime, cse_comment, csefeedback, csenextAction, auditfname, auditlname, followup_pending;
        String call_received, fake_updation, service_feedback, auditor_remark, auditor_date;

        String assign_to_cse_date, assign_to_cse_time, assign_to_dse_date, assign_to_dse_time, assign_to_dse_tl_date, assign_to_dse_tl_time;
        String appointment_type, appointment_date, appointment_time, appointment_address, appointment_status, appointment_rating, appointment_feedback, interested_in_finance;
        String interested_in_accessories, interested_in_insurance, interested_in_ew, dsecontactibility, dse_time, csecontactibility, cse_time, auditor_time, auditor_call_status;

        String file_login_date;
        String login_status_name;
        String approved_date;
        String disburse_date;
        String disburse_amount;
        String process_fee;
        String emi;
        String eagerness;
        String bank_name;
        String loan_type;
        String los_no;
        String roi;
        String tenure;
        String loanamount;
        String dealer;
        String executive_name;

        String evaluation_within_days;
        String fuel_type;

        String alternate_contact_no;
        String lead_time;

        String comment,business_area,complaint_id;
        String location;

        String quotated_price;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        String color;

        public String getAccessoires_list() {
            return accessoires_list;
        }

        public void setAccessoires_list(String accessoires_list) {
            this.accessoires_list = accessoires_list;
        }

        public String getAssessories_price() {
            return assessories_price;
        }

        public void setAssessories_price(String assessories_price) {
            this.assessories_price = assessories_price;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

        public String getService_center() {
            return service_center;
        }

        public void setService_center(String service_center) {
            this.service_center = service_center;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getPickup_required() {
            return pickup_required;
        }

        public void setPickup_required(String pickup_required) {
            this.pickup_required = pickup_required;
        }

        public String getPick_up_date() {
            return pick_up_date;
        }

        public void setPick_up_date(String pick_up_date) {
            this.pick_up_date = pick_up_date;
        }

        public String getAssistance() {
            return assistance;
        }

        public void setAssistance(String assistance) {
            this.assistance = assistance;
        }

        public String getCustomer_location() {
            return customer_location;
        }

        public void setCustomer_location(String customer_location) {
            this.customer_location = customer_location;
        }

        public String getDays60_booking() {
            return days60_booking;
        }

        public void setDays60_booking(String days60_booking) {
            this.days60_booking = days60_booking;
        }

        public String getEnq_id() {
            return enq_id;
        }

        public void setEnq_id(String enq_id) {
            this.enq_id = enq_id;
        }

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

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLead_date() {
            return lead_date;
        }

        public void setLead_date(String lead_date) {
            this.lead_date = lead_date;
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

        public String getOwnership() {
            return ownership;
        }

        public void setOwnership(String ownership) {
            this.ownership = ownership;
        }

        public String getBudget_from() {
            return budget_from;
        }

        public void setBudget_from(String budget_from) {
            this.budget_from = budget_from;
        }

        public String getBudget_to() {
            return budget_to;
        }

        public void setBudget_to(String budget_to) {
            this.budget_to = budget_to;
        }

        public String getAccidental_claim() {
            return accidental_claim;
        }

        public void setAccidental_claim(String accidental_claim) {
            this.accidental_claim = accidental_claim;
        }

        public String getAssign_by_cse_tl() {
            return assign_by_cse_tl;
        }

        public void setAssign_by_cse_tl(String assign_by_cse_tl) {
            this.assign_by_cse_tl = assign_by_cse_tl;
        }

        public String getAssign_to_cse() {
            return assign_to_cse;
        }

        public void setAssign_to_cse(String assign_to_cse) {
            this.assign_to_cse = assign_to_cse;
        }

        public String getReg_no() {
            return reg_no;
        }

        public void setReg_no(String reg_no) {
            this.reg_no = reg_no;
        }

        public String getMin_date() {
            return min_date;
        }

        public void setMin_date(String min_date) {
            this.min_date = min_date;
        }

        public String getMax_date() {
            return max_date;
        }

        public void setMax_date(String max_date) {
            this.max_date = max_date;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getBuyer_type() {
            return buyer_type;
        }

        public void setBuyer_type(String buyer_type) {
            this.buyer_type = buyer_type;
        }

        public String getManf_year() {
            return manf_year;
        }

        public void setManf_year(String manf_year) {
            this.manf_year = manf_year;
        }

        public String getCse_fname() {
            return cse_fname;
        }

        public void setCse_fname(String cse_fname) {
            this.cse_fname = cse_fname;
        }

        public String getCse_lname() {
            return cse_lname;
        }

        public void setCse_lname(String cse_lname) {
            this.cse_lname = cse_lname;
        }

        public String getCse_role() {
            return cse_role;
        }

        public void setCse_role(String cse_role) {
            this.cse_role = cse_role;
        }

        public String getCsetl_fname() {
            return csetl_fname;
        }

        public void setCsetl_fname(String csetl_fname) {
            this.csetl_fname = csetl_fname;
        }

        public String getCsetl_lname() {
            return csetl_lname;
        }

        public void setCsetl_lname(String csetl_lname) {
            this.csetl_lname = csetl_lname;
        }

        public String getCsetl_role() {
            return csetl_role;
        }

        public void setCsetl_role(String csetl_role) {
            this.csetl_role = csetl_role;
        }

        public String getShowroom_location() {
            return showroom_location;
        }

        public void setShowroom_location(String showroom_location) {
            this.showroom_location = showroom_location;
        }

        public String getAssign_to_dse_tl() {
            return assign_to_dse_tl;
        }

        public void setAssign_to_dse_tl(String assign_to_dse_tl) {
            this.assign_to_dse_tl = assign_to_dse_tl;
        }

        public String getAssign_to_dse() {
            return assign_to_dse;
        }

        public void setAssign_to_dse(String assign_to_dse) {
            this.assign_to_dse = assign_to_dse;
        }

        public String getDse_fname() {
            return dse_fname;
        }

        public void setDse_fname(String dse_fname) {
            this.dse_fname = dse_fname;
        }

        public String getDse_lname() {
            return dse_lname;
        }

        public void setDse_lname(String dse_lname) {
            this.dse_lname = dse_lname;
        }

        public String getDsetl_fname() {
            return dsetl_fname;
        }

        public void setDsetl_fname(String dsetl_fname) {
            this.dsetl_fname = dsetl_fname;
        }

        public String getDse_role() {
            return dse_role;
        }

        public void setDse_role(String dse_role) {
            this.dse_role = dse_role;
        }

        public String getDsetl_lname() {
            return dsetl_lname;
        }

        public void setDsetl_lname(String dsetl_lname) {
            this.dsetl_lname = dsetl_lname;
        }

        public String getDsetl_role() {
            return dsetl_role;
        }

        public void setDsetl_role(String dsetl_role) {
            this.dsetl_role = dsetl_role;
        }

        public String getDse_date() {
            return dse_date;
        }

        public void setDse_date(String dse_date) {
            this.dse_date = dse_date;
        }

        public String getDse_nfd() {
            return dse_nfd;
        }

        public void setDse_nfd(String dse_nfd) {
            this.dse_nfd = dse_nfd;
        }

        public String getDse_nftime() {
            return dse_nftime;
        }

        public void setDse_nftime(String dse_nftime) {
            this.dse_nftime = dse_nftime;
        }

        public String getDse_comment() {
            return dse_comment;
        }

        public void setDse_comment(String dse_comment) {
            this.dse_comment = dse_comment;
        }

        public String getTd_hv_date() {
            return td_hv_date;
        }

        public void setTd_hv_date(String td_hv_date) {
            this.td_hv_date = td_hv_date;
        }

        public String getDsenextAction() {
            return dsenextAction;
        }

        public void setDsenextAction(String dsenextAction) {
            this.dsenextAction = dsenextAction;
        }

        public String getDsefeedback() {
            return dsefeedback;
        }

        public void setDsefeedback(String dsefeedback) {
            this.dsefeedback = dsefeedback;
        }

        public String getOld_model_name() {
            return old_model_name;
        }

        public void setOld_model_name(String old_model_name) {
            this.old_model_name = old_model_name;
        }

        public String getMake_name() {
            return make_name;
        }

        public void setMake_name(String make_name) {
            this.make_name = make_name;
        }

        public String getVariant_name() {
            return variant_name;
        }

        public void setVariant_name(String variant_name) {
            this.variant_name = variant_name;
        }

        public String getNew_model_name() {
            return new_model_name;
        }

        public void setNew_model_name(String new_model_name) {
            this.new_model_name = new_model_name;
        }

        public String getCse_date() {
            return cse_date;
        }

        public void setCse_date(String cse_date) {
            this.cse_date = cse_date;
        }

        public String getCse_nfd() {
            return cse_nfd;
        }

        public void setCse_nfd(String cse_nfd) {
            this.cse_nfd = cse_nfd;
        }

        public String getCse_nftime() {
            return cse_nftime;
        }

        public void setCse_nftime(String cse_nftime) {
            this.cse_nftime = cse_nftime;
        }

        public String getCse_comment() {
            return cse_comment;
        }

        public void setCse_comment(String cse_comment) {
            this.cse_comment = cse_comment;
        }

        public String getCsefeedback() {
            return csefeedback;
        }

        public void setCsefeedback(String csefeedback) {
            this.csefeedback = csefeedback;
        }

        public String getCsenextAction() {
            return csenextAction;
        }

        public void setCsenextAction(String csenextAction) {
            this.csenextAction = csenextAction;
        }

        public String getAuditfname() {
            return auditfname;
        }

        public void setAuditfname(String auditfname) {
            this.auditfname = auditfname;
        }

        public String getAuditlname() {
            return auditlname;
        }

        public void setAuditlname(String auditlname) {
            this.auditlname = auditlname;
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

        public String getAuditor_remark() {
            return auditor_remark;
        }

        public void setAuditor_remark(String auditor_remark) {
            this.auditor_remark = auditor_remark;
        }

        public String getAuditor_date() {
            return auditor_date;
        }

        public void setAuditor_date(String auditor_date) {
            this.auditor_date = auditor_date;
        }

        public String getAssign_to_cse_date() {
            return assign_to_cse_date;
        }

        public void setAssign_to_cse_date(String assign_to_cse_date) {
            this.assign_to_cse_date = assign_to_cse_date;
        }

        public String getAssign_to_cse_time() {
            return assign_to_cse_time;
        }

        public void setAssign_to_cse_time(String assign_to_cse_time) {
            this.assign_to_cse_time = assign_to_cse_time;
        }

        public String getAssign_to_dse_date() {
            return assign_to_dse_date;
        }

        public void setAssign_to_dse_date(String assign_to_dse_date) {
            this.assign_to_dse_date = assign_to_dse_date;
        }

        public String getAssign_to_dse_time() {
            return assign_to_dse_time;
        }

        public void setAssign_to_dse_time(String assign_to_dse_time) {
            this.assign_to_dse_time = assign_to_dse_time;
        }

        public String getAssign_to_dse_tl_date() {
            return assign_to_dse_tl_date;
        }

        public void setAssign_to_dse_tl_date(String assign_to_dse_tl_date) {
            this.assign_to_dse_tl_date = assign_to_dse_tl_date;
        }

        public String getAssign_to_dse_tl_time() {
            return assign_to_dse_tl_time;
        }

        public void setAssign_to_dse_tl_time(String assign_to_dse_tl_time) {
            this.assign_to_dse_tl_time = assign_to_dse_tl_time;
        }

        public String getAppointment_type() {
            return appointment_type;
        }

        public void setAppointment_type(String appointment_type) {
            this.appointment_type = appointment_type;
        }

        public String getAppointment_date() {
            return appointment_date;
        }

        public void setAppointment_date(String appointment_date) {
            this.appointment_date = appointment_date;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }

        public String getAppointment_address() {
            return appointment_address;
        }

        public void setAppointment_address(String appointment_address) {
            this.appointment_address = appointment_address;
        }

        public String getAppointment_status() {
            return appointment_status;
        }

        public void setAppointment_status(String appointment_status) {
            this.appointment_status = appointment_status;
        }

        public String getAppointment_rating() {
            return appointment_rating;
        }

        public void setAppointment_rating(String appointment_rating) {
            this.appointment_rating = appointment_rating;
        }

        public String getAppointment_feedback() {
            return appointment_feedback;
        }

        public void setAppointment_feedback(String appointment_feedback) {
            this.appointment_feedback = appointment_feedback;
        }

        public String getInterested_in_finance() {
            return interested_in_finance;
        }

        public void setInterested_in_finance(String interested_in_finance) {
            this.interested_in_finance = interested_in_finance;
        }

        public String getInterested_in_accessories() {
            return interested_in_accessories;
        }

        public void setInterested_in_accessories(String interested_in_accessories) {
            this.interested_in_accessories = interested_in_accessories;
        }

        public String getInterested_in_insurance() {
            return interested_in_insurance;
        }

        public void setInterested_in_insurance(String interested_in_insurance) {
            this.interested_in_insurance = interested_in_insurance;
        }

        public String getInterested_in_ew() {
            return interested_in_ew;
        }

        public void setInterested_in_ew(String interested_in_ew) {
            this.interested_in_ew = interested_in_ew;
        }

        public String getDsecontactibility() {
            return dsecontactibility;
        }

        public void setDsecontactibility(String dsecontactibility) {
            this.dsecontactibility = dsecontactibility;
        }

        public String getDse_time() {
            return dse_time;
        }

        public void setDse_time(String dse_time) {
            this.dse_time = dse_time;
        }

        public String getCsecontactibility() {
            return csecontactibility;
        }

        public void setCsecontactibility(String csecontactibility) {
            this.csecontactibility = csecontactibility;
        }

        public String getCse_time() {
            return cse_time;
        }

        public void setCse_time(String cse_time) {
            this.cse_time = cse_time;
        }

        public String getAuditor_time() {
            return auditor_time;
        }

        public void setAuditor_time(String auditor_time) {
            this.auditor_time = auditor_time;
        }

        public String getAuditor_call_status() {
            return auditor_call_status;
        }

        public void setAuditor_call_status(String auditor_call_status) {
            this.auditor_call_status = auditor_call_status;
        }

        public String getFile_login_date() {
            return file_login_date;
        }

        public void setFile_login_date(String file_login_date) {
            this.file_login_date = file_login_date;
        }

        public String getLogin_status_name() {
            return login_status_name;
        }

        public void setLogin_status_name(String login_status_name) {
            this.login_status_name = login_status_name;
        }

        public String getApproved_date() {
            return approved_date;
        }

        public void setApproved_date(String approved_date) {
            this.approved_date = approved_date;
        }

        public String getDisburse_date() {
            return disburse_date;
        }

        public void setDisburse_date(String disburse_date) {
            this.disburse_date = disburse_date;
        }

        public String getDisburse_amount() {
            return disburse_amount;
        }

        public void setDisburse_amount(String disburse_amount) {
            this.disburse_amount = disburse_amount;
        }

        public String getProcess_fee() {
            return process_fee;
        }

        public void setProcess_fee(String process_fee) {
            this.process_fee = process_fee;
        }

        public String getEmi() {
            return emi;
        }

        public void setEmi(String emi) {
            this.emi = emi;
        }

        public String getEagerness() {
            return eagerness;
        }

        public void setEagerness(String eagerness) {
            this.eagerness = eagerness;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getLoan_type() {
            return loan_type;
        }

        public void setLoan_type(String loan_type) {
            this.loan_type = loan_type;
        }

        public String getLos_no() {
            return los_no;
        }

        public void setLos_no(String los_no) {
            this.los_no = los_no;
        }

        public String getRoi() {
            return roi;
        }

        public void setRoi(String roi) {
            this.roi = roi;
        }

        public String getTenure() {
            return tenure;
        }

        public void setTenure(String tenure) {
            this.tenure = tenure;
        }

        public String getLoanamount() {
            return loanamount;
        }

        public void setLoanamount(String loanamount) {
            this.loanamount = loanamount;
        }

        public String getDealer() {
            return dealer;
        }

        public void setDealer(String dealer) {
            this.dealer = dealer;
        }

        public String getExecutive_name() {
            return executive_name;
        }

        public void setExecutive_name(String executive_name) {
            this.executive_name = executive_name;
        }

        public String getEvaluation_within_days() {
            return evaluation_within_days;
        }

        public void setEvaluation_within_days(String evaluation_within_days) {
            this.evaluation_within_days = evaluation_within_days;
        }

        public String getFuel_type() {
            return fuel_type;
        }

        public void setFuel_type(String fuel_type) {
            this.fuel_type = fuel_type;
        }

        public String getAlternate_contact_no() {
            return alternate_contact_no;
        }

        public void setAlternate_contact_no(String alternate_contact_no) {
            this.alternate_contact_no = alternate_contact_no;
        }

        public String getLead_time() {
            return lead_time;
        }

        public void setLead_time(String lead_time) {
            this.lead_time = lead_time;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getBusiness_area() {
            return business_area;
        }

        public void setBusiness_area(String business_area) {
            this.business_area = business_area;
        }

        public String getComplaint_id() {
            return complaint_id;
        }

        public void setComplaint_id(String complaint_id) {
            this.complaint_id = complaint_id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getQuotated_price() {
            return quotated_price;
        }

        public void setQuotated_price(String quotated_price) {
            this.quotated_price = quotated_price;
        }

        public String getExpected_price() {
            return expected_price;
        }

        public void setExpected_price(String expected_price) {
            this.expected_price = expected_price;
        }

        String expected_price;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.accessoires_list);
            dest.writeString(this.assessories_price);
            dest.writeString(this.model_name);
            dest.writeString(this.service_center);
            dest.writeString(this.service_type);
            dest.writeString(this.pickup_required);
            dest.writeString(this.pick_up_date);
            dest.writeString(this.assistance);
            dest.writeString(this.customer_location);
            dest.writeString(this.days60_booking);
            dest.writeString(this.enq_id);
            dest.writeString(this.lead_source);
            dest.writeString(this.enquiry_for);
            dest.writeString(this.process);
            dest.writeString(this.name);
            dest.writeString(this.contact_no);
            dest.writeString(this.address);
            dest.writeString(this.email);
            dest.writeString(this.lead_date);
            dest.writeString(this.feedbackStatus);
            dest.writeString(this.nextAction);
            dest.writeString(this.ownership);
            dest.writeString(this.budget_from);
            dest.writeString(this.budget_to);
            dest.writeString(this.accidental_claim);
            dest.writeString(this.assign_by_cse_tl);
            dest.writeString(this.assign_to_cse);
            dest.writeString(this.reg_no);
            dest.writeString(this.min_date);
            dest.writeString(this.max_date);
            dest.writeString(this.km);
            dest.writeString(this.buyer_type);
            dest.writeString(this.manf_year);
            dest.writeString(this.cse_fname);
            dest.writeString(this.cse_lname);
            dest.writeString(this.cse_role);
            dest.writeString(this.csetl_fname);
            dest.writeString(this.csetl_lname);
            dest.writeString(this.csetl_role);
            dest.writeString(this.showroom_location);
            dest.writeString(this.assign_to_dse_tl);
            dest.writeString(this.assign_to_dse);
            dest.writeString(this.dse_fname);
            dest.writeString(this.dse_lname);
            dest.writeString(this.dsetl_fname);
            dest.writeString(this.dse_role);
            dest.writeString(this.dsetl_lname);
            dest.writeString(this.dsetl_role);
            dest.writeString(this.dse_date);
            dest.writeString(this.dse_nfd);
            dest.writeString(this.dse_nftime);
            dest.writeString(this.dse_comment);
            dest.writeString(this.td_hv_date);
            dest.writeString(this.dsenextAction);
            dest.writeString(this.dsefeedback);
            dest.writeString(this.old_model_name);
            dest.writeString(this.make_name);
            dest.writeString(this.variant_name);
            dest.writeString(this.new_model_name);
            dest.writeString(this.cse_date);
            dest.writeString(this.cse_nfd);
            dest.writeString(this.cse_nftime);
            dest.writeString(this.cse_comment);
            dest.writeString(this.csefeedback);
            dest.writeString(this.csenextAction);
            dest.writeString(this.auditfname);
            dest.writeString(this.auditlname);
            dest.writeString(this.followup_pending);
            dest.writeString(this.call_received);
            dest.writeString(this.fake_updation);
            dest.writeString(this.service_feedback);
            dest.writeString(this.auditor_remark);
            dest.writeString(this.auditor_date);
            dest.writeString(this.assign_to_cse_date);
            dest.writeString(this.assign_to_cse_time);
            dest.writeString(this.assign_to_dse_date);
            dest.writeString(this.assign_to_dse_time);
            dest.writeString(this.assign_to_dse_tl_date);
            dest.writeString(this.assign_to_dse_tl_time);
            dest.writeString(this.appointment_type);
            dest.writeString(this.appointment_date);
            dest.writeString(this.appointment_time);
            dest.writeString(this.appointment_address);
            dest.writeString(this.appointment_status);
            dest.writeString(this.appointment_rating);
            dest.writeString(this.appointment_feedback);
            dest.writeString(this.interested_in_finance);
            dest.writeString(this.interested_in_accessories);
            dest.writeString(this.interested_in_insurance);
            dest.writeString(this.interested_in_ew);
            dest.writeString(this.dsecontactibility);
            dest.writeString(this.dse_time);
            dest.writeString(this.csecontactibility);
            dest.writeString(this.cse_time);
            dest.writeString(this.auditor_time);
            dest.writeString(this.auditor_call_status);
            dest.writeString(this.file_login_date);
            dest.writeString(this.login_status_name);
            dest.writeString(this.approved_date);
            dest.writeString(this.disburse_date);
            dest.writeString(this.disburse_amount);
            dest.writeString(this.process_fee);
            dest.writeString(this.emi);
            dest.writeString(this.eagerness);
            dest.writeString(this.bank_name);
            dest.writeString(this.loan_type);
            dest.writeString(this.los_no);
            dest.writeString(this.roi);
            dest.writeString(this.tenure);
            dest.writeString(this.loanamount);
            dest.writeString(this.dealer);
            dest.writeString(this.executive_name);
            dest.writeString(this.evaluation_within_days);
            dest.writeString(this.fuel_type);
            dest.writeString(this.alternate_contact_no);
            dest.writeString(this.lead_time);
            dest.writeString(this.comment);
            dest.writeString(this.business_area);
            dest.writeString(this.complaint_id);
            dest.writeString(this.location);
            dest.writeString(this.quotated_price);
            dest.writeString(this.expected_price);
            dest.writeString(this.color);
        }

        public User_Details() {
        }

        protected User_Details(Parcel in) {
            this.accessoires_list = in.readString();
            this.assessories_price = in.readString();
            this.model_name = in.readString();
            this.service_center = in.readString();
            this.service_type = in.readString();
            this.pickup_required = in.readString();
            this.pick_up_date = in.readString();
            this.assistance = in.readString();
            this.customer_location = in.readString();
            this.days60_booking = in.readString();
            this.enq_id = in.readString();
            this.lead_source = in.readString();
            this.enquiry_for = in.readString();
            this.process = in.readString();
            this.name = in.readString();
            this.contact_no = in.readString();
            this.address = in.readString();
            this.email = in.readString();
            this.lead_date = in.readString();
            this.feedbackStatus = in.readString();
            this.nextAction = in.readString();
            this.ownership = in.readString();
            this.budget_from = in.readString();
            this.budget_to = in.readString();
            this.accidental_claim = in.readString();
            this.assign_by_cse_tl = in.readString();
            this.assign_to_cse = in.readString();
            this.reg_no = in.readString();
            this.min_date = in.readString();
            this.max_date = in.readString();
            this.km = in.readString();
            this.buyer_type = in.readString();
            this.manf_year = in.readString();
            this.cse_fname = in.readString();
            this.cse_lname = in.readString();
            this.cse_role = in.readString();
            this.csetl_fname = in.readString();
            this.csetl_lname = in.readString();
            this.csetl_role = in.readString();
            this.showroom_location = in.readString();
            this.assign_to_dse_tl = in.readString();
            this.assign_to_dse = in.readString();
            this.dse_fname = in.readString();
            this.dse_lname = in.readString();
            this.dsetl_fname = in.readString();
            this.dse_role = in.readString();
            this.dsetl_lname = in.readString();
            this.dsetl_role = in.readString();
            this.dse_date = in.readString();
            this.dse_nfd = in.readString();
            this.dse_nftime = in.readString();
            this.dse_comment = in.readString();
            this.td_hv_date = in.readString();
            this.dsenextAction = in.readString();
            this.dsefeedback = in.readString();
            this.old_model_name = in.readString();
            this.make_name = in.readString();
            this.variant_name = in.readString();
            this.new_model_name = in.readString();
            this.cse_date = in.readString();
            this.cse_nfd = in.readString();
            this.cse_nftime = in.readString();
            this.cse_comment = in.readString();
            this.csefeedback = in.readString();
            this.csenextAction = in.readString();
            this.auditfname = in.readString();
            this.auditlname = in.readString();
            this.followup_pending = in.readString();
            this.call_received = in.readString();
            this.fake_updation = in.readString();
            this.service_feedback = in.readString();
            this.auditor_remark = in.readString();
            this.auditor_date = in.readString();
            this.assign_to_cse_date = in.readString();
            this.assign_to_cse_time = in.readString();
            this.assign_to_dse_date = in.readString();
            this.assign_to_dse_time = in.readString();
            this.assign_to_dse_tl_date = in.readString();
            this.assign_to_dse_tl_time = in.readString();
            this.appointment_type = in.readString();
            this.appointment_date = in.readString();
            this.appointment_time = in.readString();
            this.appointment_address = in.readString();
            this.appointment_status = in.readString();
            this.appointment_rating = in.readString();
            this.appointment_feedback = in.readString();
            this.interested_in_finance = in.readString();
            this.interested_in_accessories = in.readString();
            this.interested_in_insurance = in.readString();
            this.interested_in_ew = in.readString();
            this.dsecontactibility = in.readString();
            this.dse_time = in.readString();
            this.csecontactibility = in.readString();
            this.cse_time = in.readString();
            this.auditor_time = in.readString();
            this.auditor_call_status = in.readString();
            this.file_login_date = in.readString();
            this.login_status_name = in.readString();
            this.approved_date = in.readString();
            this.disburse_date = in.readString();
            this.disburse_amount = in.readString();
            this.process_fee = in.readString();
            this.emi = in.readString();
            this.eagerness = in.readString();
            this.bank_name = in.readString();
            this.loan_type = in.readString();
            this.los_no = in.readString();
            this.roi = in.readString();
            this.tenure = in.readString();
            this.loanamount = in.readString();
            this.dealer = in.readString();
            this.executive_name = in.readString();
            this.evaluation_within_days = in.readString();
            this.fuel_type = in.readString();
            this.alternate_contact_no = in.readString();
            this.lead_time = in.readString();
            this.comment = in.readString();
            this.business_area = in.readString();
            this.complaint_id = in.readString();
            this.location = in.readString();
            this.quotated_price = in.readString();
            this.expected_price = in.readString();
            this.color = in.readString();
        }

        public static final Parcelable.Creator<User_Details> CREATOR = new Parcelable.Creator<User_Details>() {
            @Override
            public User_Details createFromParcel(Parcel source) {
                return new User_Details(source);
            }

            @Override
            public User_Details[] newArray(int size) {
                return new User_Details[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.user_details_count);
        dest.writeTypedList(this.user_details);
    }

    public SearchTrackerListBean() {
    }

    protected SearchTrackerListBean(Parcel in) {
        this.user_details_count = new ArrayList<User_Details_Count>();
        in.readList(this.user_details_count, User_Details_Count.class.getClassLoader());
        this.user_details = in.createTypedArrayList(User_Details.CREATOR);
    }

    public static final Parcelable.Creator<SearchTrackerListBean> CREATOR = new Parcelable.Creator<SearchTrackerListBean>() {
        @Override
        public SearchTrackerListBean createFromParcel(Parcel source) {
            return new SearchTrackerListBean(source);
        }

        @Override
        public SearchTrackerListBean[] newArray(int size) {
            return new SearchTrackerListBean[size];
        }
    };
}







