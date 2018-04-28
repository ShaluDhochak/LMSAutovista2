package com.example.user.lmsautovista.Model;

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
    }
}



