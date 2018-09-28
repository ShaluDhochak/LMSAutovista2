package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/22/2018.
*/

import java.util.ArrayList;

public class CustomerDetailsBean {

    public ArrayList<Customer_details> getCustomer_details() {
        return customer_details;
    }

    public void setCustomer_details(ArrayList<Customer_details> customer_details) {
        this.customer_details = customer_details;
    }

    public ArrayList<Customer_details> customer_details;

    public static class Customer_details{
        String enq_id,name,email,contact_no,address,feedbackStatus,nextAction,eagerness,service_type,service_center,km;
        String pick_up_date,pickup_required,buyer_type,bank_name,loan_type,reg_no,roi,los_no,tenure,loanamount,dealer,model_name;
        String color,manf_year,ownership,accidental_claim,budget_from,budget_to,old_model,old_make,buy_make,buy_model,variant_name;
        String cse_fname,cse_lname,dse_fname,dse_lname,csetl_fname,csetl_lname,dsetl_fname,dsetl_lname;
        String appointment_type, appointment_date,appointment_time, appointment_address;
        String appointment_status,appointment_rating,appointment_feedback;
        String interested_in_finance,interested_in_accessories,interested_in_insurance, interested_in_ew;
        String customer_occupation;
        String customer_corporate_name;
        String customer_designation;
        String alternate_contact_no;
        String days60_booking;
        String assign_by_cse_tl;
        String assign_to_cse;
        String assign_to_dse_tl;
        String assign_to_dse;
        String cse_call_date;
        String cse_remark;
        String cse_nfd;
        String cse_nft;
        String dse_call_date;
        String dse_remark;
        String dse_nfd;
        String dse_nft;
        String dse_call_time,cse_call_time,cse_contactibility,dse_contactibility;
        String quotated_price, expected_price;


        String complaint_id;
        String lead_source;
        String business_area;
        String created_date;
        String comment;
        String date;
        String nextfollowupdate;
        String nextfollowuptime;
        String cse_comment;

        public String getComplaint_id() {
            return complaint_id;
        }

        public void setComplaint_id(String complaint_id) {
            this.complaint_id = complaint_id;
        }

        public String getLead_source() {
            return lead_source;
        }

        public void setLead_source(String lead_source) {
            this.lead_source = lead_source;
        }

        public String getBusiness_area() {
            return business_area;
        }

        public void setBusiness_area(String business_area) {
            this.business_area = business_area;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getNextfollowupdate() {
            return nextfollowupdate;
        }

        public void setNextfollowupdate(String nextfollowupdate) {
            this.nextfollowupdate = nextfollowupdate;
        }

        public String getNextfollowuptime() {
            return nextfollowuptime;
        }

        public void setNextfollowuptime(String nextfollowuptime) {
            this.nextfollowuptime = nextfollowuptime;
        }

        public String getCse_comment() {
            return cse_comment;
        }

        public void setCse_comment(String cse_comment) {
            this.cse_comment = cse_comment;
        }

        public String getCse_name() {
            return cse_name;
        }

        public void setCse_name(String cse_name) {
            this.cse_name = cse_name;
        }

        String cse_name;

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

        public String getDse_call_time() {
            return dse_call_time;
        }

        public void setDse_call_time(String dse_call_time) {
            this.dse_call_time = dse_call_time;
        }

        public String getCse_call_time() {
            return cse_call_time;
        }

        public void setCse_call_time(String cse_call_time) {
            this.cse_call_time = cse_call_time;
        }

        public String getCse_contactibility() {
            return cse_contactibility;
        }

        public void setCse_contactibility(String cse_contactibility) {
            this.cse_contactibility = cse_contactibility;
        }

        public String getDse_contactibility() {
            return dse_contactibility;
        }

        public void setDse_contactibility(String dse_contactibility) {
            this.dse_contactibility = dse_contactibility;
        }

        public String getDays60_booking() {
            return days60_booking;
        }

        public void setDays60_booking(String days60_booking) {
            this.days60_booking = days60_booking;
        }

        public String getCustomer_occupation() {
            return customer_occupation;
        }

        public void setCustomer_occupation(String customer_occupation) {
            this.customer_occupation = customer_occupation;
        }

        public String getCustomer_corporate_name() {
            return customer_corporate_name;
        }

        public void setCustomer_corporate_name(String customer_corporate_name) {
            this.customer_corporate_name = customer_corporate_name;
        }

        public String getCustomer_designation() {
            return customer_designation;
        }

        public void setCustomer_designation(String customer_designation) {
            this.customer_designation = customer_designation;
        }

        public String getAlternate_contact_no() {
            return alternate_contact_no;
        }

        public void setAlternate_contact_no(String alternate_contact_no) {
            this.alternate_contact_no = alternate_contact_no;
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

        public String getEnq_id() {
            return enq_id;
        }

        public void setEnq_id(String enq_id) {
            this.enq_id = enq_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getEagerness() {
            return eagerness;
        }

        public void setEagerness(String eagerness) {
            this.eagerness = eagerness;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getService_center() {
            return service_center;
        }

        public void setService_center(String service_center) {
            this.service_center = service_center;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getPick_up_date() {
            return pick_up_date;
        }

        public void setPick_up_date(String pick_up_date) {
            this.pick_up_date = pick_up_date;
        }

        public String getPickup_required() {
            return pickup_required;
        }

        public void setPickup_required(String pickup_required) {
            this.pickup_required = pickup_required;
        }

        public String getBuyer_type() {
            return buyer_type;
        }

        public void setBuyer_type(String buyer_type) {
            this.buyer_type = buyer_type;
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

        public String getReg_no() {
            return reg_no;
        }

        public void setReg_no(String reg_no) {
            this.reg_no = reg_no;
        }

        public String getRoi() {
            return roi;
        }

        public void setRoi(String roi) {
            this.roi = roi;
        }

        public String getLos_no() {
            return los_no;
        }

        public void setLos_no(String los_no) {
            this.los_no = los_no;
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

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getManf_year() {
            return manf_year;
        }

        public void setManf_year(String manf_year) {
            this.manf_year = manf_year;
        }

        public String getOwnership() {
            return ownership;
        }

        public void setOwnership(String ownership) {
            this.ownership = ownership;
        }

        public String getAccidental_claim() {
            return accidental_claim;
        }

        public void setAccidental_claim(String accidental_claim) {
            this.accidental_claim = accidental_claim;
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

        public String getOld_model() {
            return old_model;
        }

        public void setOld_model(String old_model) {
            this.old_model = old_model;
        }

        public String getOld_make() {
            return old_make;
        }

        public void setOld_make(String old_make) {
            this.old_make = old_make;
        }

        public String getBuy_make() {
            return buy_make;
        }

        public void setBuy_make(String buy_make) {
            this.buy_make = buy_make;
        }

        public String getBuy_model() {
            return buy_model;
        }

        public void setBuy_model(String buy_model) {
            this.buy_model = buy_model;
        }

        public String getVariant_name() {
            return variant_name;
        }

        public void setVariant_name(String variant_name) {
            this.variant_name = variant_name;
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

        public String getDsetl_fname() {
            return dsetl_fname;
        }

        public void setDsetl_fname(String dsetl_fname) {
            this.dsetl_fname = dsetl_fname;
        }

        public String getDsetl_lname() {
            return dsetl_lname;
        }

        public void setDsetl_lname(String dsetl_lname) {
            this.dsetl_lname = dsetl_lname;
        }

        public String getCse_call_date() {
            return cse_call_date;
        }

        public void setCse_call_date(String cse_call_date) {
            this.cse_call_date = cse_call_date;
        }

        public String getCse_remark() {
            return cse_remark;
        }

        public void setCse_remark(String cse_remark) {
            this.cse_remark = cse_remark;
        }

        public String getCse_nfd() {
            return cse_nfd;
        }

        public void setCse_nfd(String cse_nfd) {
            this.cse_nfd = cse_nfd;
        }

        public String getCse_nft() {
            return cse_nft;
        }

        public void setCse_nft(String cse_nft) {
            this.cse_nft = cse_nft;
        }

        public String getDse_call_date() {
            return dse_call_date;
        }

        public void setDse_call_date(String dse_call_date) {
            this.dse_call_date = dse_call_date;
        }

        public String getDse_remark() {
            return dse_remark;
        }

        public void setDse_remark(String dse_remark) {
            this.dse_remark = dse_remark;
        }

        public String getDse_nfd() {
            return dse_nfd;
        }

        public void setDse_nfd(String dse_nfd) {
            this.dse_nfd = dse_nfd;
        }

        public String getDse_nft() {
            return dse_nft;
        }

        public void setDse_nft(String dse_nft) {
            this.dse_nft = dse_nft;
        }

    }
}



