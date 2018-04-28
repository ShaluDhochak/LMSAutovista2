package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/26/2018.
*/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class SearchCustomerBean implements Parcelable {
    public ArrayList<Lead_Data> lead_data;

    public ArrayList<Lead_Data> getLead_data() {
        return lead_data;
    }

    public void setLead_data(ArrayList<Lead_Data> lead_data) {
        this.lead_data = lead_data;
    }

    public static class Lead_Data implements Parcelable {
        String accessoires_list,assessories_price,model_name,service_center,service_type,pickup_required,pick_up_date,assistance;
        String customer_location,days60_booking,enq_id,lead_source,enquiry_for,process,name,contact_no,address,email,lead_date;
        String feedbackStatus,nextAction,ownership,budget_from,budget_to,accidental_claim,assign_by_cse_tl,assign_to_cse;
        String reg_no,min_date,max_date,km,buyer_type,manf_year,cse_fname,cse_lname,cse_role,csetl_fname;
        String csetl_lname,csetl_role,showroom_location,assign_to_dse_tl,assign_to_dse,dse_fname,dse_lname,dse_role;
        String dsetl_fname;
        String dsetl_lname;
        String dsetl_role;
        String dse_date;
        String dse_nfd;
        String dse_nftime;
        String dse_comment;
        String td_hv_date;
        String dsenextAction;
        String old_model_name;
        String make_name,variant_name,new_model_name,cse_date,cse_nfd,cse_nftime,cse_comment,csefeedback,csenextAction,auditfname;
        String auditlname,followup_pending,call_received,fake_updation,service_feedback,auditor_remark,auditor_date;

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

        public String getDse_role() {
            return dse_role;
        }

        public void setDse_role(String dse_role) {
            this.dse_role = dse_role;
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
            dest.writeString(this.dse_role);
            dest.writeString(this.dsetl_fname);
            dest.writeString(this.dsetl_lname);
            dest.writeString(this.dsetl_role);
            dest.writeString(this.dse_date);
            dest.writeString(this.dse_nfd);
            dest.writeString(this.dse_nftime);
            dest.writeString(this.dse_comment);
            dest.writeString(this.td_hv_date);
            dest.writeString(this.dsenextAction);
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
        }

        public Lead_Data() {
        }

        protected Lead_Data(Parcel in) {
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
            this.dse_role = in.readString();
            this.dsetl_fname = in.readString();
            this.dsetl_lname = in.readString();
            this.dsetl_role = in.readString();
            this.dse_date = in.readString();
            this.dse_nfd = in.readString();
            this.dse_nftime = in.readString();
            this.dse_comment = in.readString();
            this.td_hv_date = in.readString();
            this.dsenextAction = in.readString();
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
        }

        public static final Parcelable.Creator<Lead_Data> CREATOR = new Parcelable.Creator<Lead_Data>() {
            @Override
            public Lead_Data createFromParcel(Parcel source) {
                return new Lead_Data(source);
            }

            @Override
            public Lead_Data[] newArray(int size) {
                return new Lead_Data[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.lead_data);
    }

    public SearchCustomerBean() {
    }

    protected SearchCustomerBean(Parcel in) {
        this.lead_data = in.createTypedArrayList(Lead_Data.CREATOR);
    }

    public static final Parcelable.Creator<SearchCustomerBean> CREATOR = new Parcelable.Creator<SearchCustomerBean>() {
        @Override
        public SearchCustomerBean createFromParcel(Parcel source) {
            return new SearchCustomerBean(source);
        }

        @Override
        public SearchCustomerBean[] newArray(int size) {
            return new SearchCustomerBean[size];
        }
    };
}
