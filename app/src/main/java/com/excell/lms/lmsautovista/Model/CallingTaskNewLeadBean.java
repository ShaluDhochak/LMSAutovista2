package com.excell.lms.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 4/18/2018.
*/

public class CallingTaskNewLeadBean implements Parcelable {

    public ArrayList<Lead_Details_Count> getLead_details_count() {
        return lead_details_count;
    }

    public void setLead_details_count(ArrayList<Lead_Details_Count> lead_details_count) {
        this.lead_details_count = lead_details_count;
    }

    public ArrayList<Lead_Details_Count> lead_details_count;
    public ArrayList<Lead_Details> lead_details;

    public ArrayList<Lead_Details> getLead_details() {
        return lead_details;
    }

    public void setLead_details(ArrayList<Lead_Details> lead_details) {
        this.lead_details = lead_details;
    }

    public static class Lead_Details_Count implements Parcelable {
        public String getCount_lead() {
            return count_lead;
        }

        public void setCount_lead(String count_lead) {
            this.count_lead = count_lead;
        }

        String count_lead;


        public Lead_Details_Count() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.count_lead);
        }

        protected Lead_Details_Count(Parcel in) {
            this.count_lead = in.readString();
        }

        public static final Parcelable.Creator<Lead_Details_Count> CREATOR = new Parcelable.Creator<Lead_Details_Count>() {
            @Override
            public Lead_Details_Count createFromParcel(Parcel source) {
                return new Lead_Details_Count(source);
            }

            @Override
            public Lead_Details_Count[] newArray(int size) {
                return new Lead_Details_Count[size];
            }
        };
    }

    public static class Lead_Details implements Parcelable {
        String cse_fname, cse_lname, dse_fname, dse_lname, csetl_fname, csetl_lname, dsetl_fname, dsetl_lname, cse_date, cse_nfd, cse_nftime, cse_comment;
        String dse_date, dse_nfd, dse_nftime, dse_comment, assign_to_dse, assign_to_dse_tl, assign_to_cse, assign_by_cse_tl, cse_followup_id;
        String dse_followup_id;
        String lead_source;
        String eagerness;
        String enq_id;
        String name;
        String email;
        String contact_no;
        String enquiry_for;
        String created_date;
        String created_time;
        String buyer_type;
        String esc_level1;
        String esc_level1_remark;
        String esc_level2;
        String esc_level2_remark;
        String esc_level3;
        String esc_level3_remark;
        String address;
        String buy_status, model_id, variant_id, old_make, old_model, ownership, manf_year, color, km, feedbackStatus, nextAction, days60_booking;
        String quotated_price;
        String expected_price;
        String reg_no;

        String complaint_id,business_area,service_center,comment,date,nextfollowupdate,nextfollowuptime,cse_name;

        public String getComplaint_id() {
            return complaint_id;
        }

        public void setComplaint_id(String complaint_id) {
            this.complaint_id = complaint_id;
        }

        public String getBusiness_area() {
            return business_area;
        }

        public void setBusiness_area(String business_area) {
            this.business_area = business_area;
        }

        public String getService_center() {
            return service_center;
        }

        public void setService_center(String service_center) {
            this.service_center = service_center;
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

        public String getCse_name() {
            return cse_name;
        }

        public void setCse_name(String cse_name) {
            this.cse_name = cse_name;
        }

        public static Creator<Lead_Details> getCREATOR() {
            return CREATOR;
        }

        public String getReg_no() {
            return reg_no;
        }

        public void setReg_no(String reg_no) {
            this.reg_no = reg_no;
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

        public String getAlternate_contact_no() {
            return alternate_contact_no;
        }

        public void setAlternate_contact_no(String alternate_contact_no) {
            this.alternate_contact_no = alternate_contact_no;
        }

        String alternate_contact_no;


        public String getEsc_level1() {
            return esc_level1;
        }

        public void setEsc_level1(String esc_level1) {
            this.esc_level1 = esc_level1;
        }

        public String getEsc_level1_remark() {
            return esc_level1_remark;
        }

        public void setEsc_level1_remark(String esc_level1_remark) {
            this.esc_level1_remark = esc_level1_remark;
        }

        public String getEsc_level2() {
            return esc_level2;
        }

        public void setEsc_level2(String esc_level2) {
            this.esc_level2 = esc_level2;
        }

        public String getEsc_level2_remark() {
            return esc_level2_remark;
        }

        public void setEsc_level2_remark(String esc_level2_remark) {
            this.esc_level2_remark = esc_level2_remark;
        }

        public String getEsc_level3() {
            return esc_level3;
        }

        public void setEsc_level3(String esc_level3) {
            this.esc_level3 = esc_level3;
        }

        public String getEsc_level3_remark() {
            return esc_level3_remark;
        }

        public void setEsc_level3_remark(String esc_level3_remark) {
            this.esc_level3_remark = esc_level3_remark;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getAssign_to_dse() {
            return assign_to_dse;
        }

        public void setAssign_to_dse(String assign_to_dse) {
            this.assign_to_dse = assign_to_dse;
        }

        public String getAssign_to_dse_tl() {
            return assign_to_dse_tl;
        }

        public void setAssign_to_dse_tl(String assign_to_dse_tl) {
            this.assign_to_dse_tl = assign_to_dse_tl;
        }

        public String getAssign_to_cse() {
            return assign_to_cse;
        }

        public void setAssign_to_cse(String assign_to_cse) {
            this.assign_to_cse = assign_to_cse;
        }

        public String getAssign_by_cse_tl() {
            return assign_by_cse_tl;
        }

        public void setAssign_by_cse_tl(String assign_by_cse_tl) {
            this.assign_by_cse_tl = assign_by_cse_tl;
        }

        public String getCse_followup_id() {
            return cse_followup_id;
        }

        public void setCse_followup_id(String cse_followup_id) {
            this.cse_followup_id = cse_followup_id;
        }

        public String getDse_followup_id() {
            return dse_followup_id;
        }

        public void setDse_followup_id(String dse_followup_id) {
            this.dse_followup_id = dse_followup_id;
        }

        public String getLead_source() {
            return lead_source;
        }

        public void setLead_source(String lead_source) {
            this.lead_source = lead_source;
        }

        public String getEagerness() {
            return eagerness;
        }

        public void setEagerness(String eagerness) {
            this.eagerness = eagerness;
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

        public String getEnquiry_for() {
            return enquiry_for;
        }

        public void setEnquiry_for(String enquiry_for) {
            this.enquiry_for = enquiry_for;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getBuyer_type() {
            return buyer_type;
        }

        public void setBuyer_type(String buyer_type) {
            this.buyer_type = buyer_type;
        }

        public String getBuy_status() {
            return buy_status;
        }

        public void setBuy_status(String buy_status) {
            this.buy_status = buy_status;
        }

        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }

        public String getVariant_id() {
            return variant_id;
        }

        public void setVariant_id(String variant_id) {
            this.variant_id = variant_id;
        }

        public String getOld_make() {
            return old_make;
        }

        public void setOld_make(String old_make) {
            this.old_make = old_make;
        }

        public String getOld_model() {
            return old_model;
        }

        public void setOld_model(String old_model) {
            this.old_model = old_model;
        }

        public String getOwnership() {
            return ownership;
        }

        public void setOwnership(String ownership) {
            this.ownership = ownership;
        }

        public String getManf_year() {
            return manf_year;
        }

        public void setManf_year(String manf_year) {
            this.manf_year = manf_year;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
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

        public String getDays60_booking() {
            return days60_booking;
        }

        public void setDays60_booking(String days60_booking) {
            this.days60_booking = days60_booking;
        }

        public Lead_Details() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cse_fname);
            dest.writeString(this.cse_lname);
            dest.writeString(this.dse_fname);
            dest.writeString(this.dse_lname);
            dest.writeString(this.csetl_fname);
            dest.writeString(this.csetl_lname);
            dest.writeString(this.dsetl_fname);
            dest.writeString(this.dsetl_lname);
            dest.writeString(this.cse_date);
            dest.writeString(this.cse_nfd);
            dest.writeString(this.cse_nftime);
            dest.writeString(this.cse_comment);
            dest.writeString(this.dse_date);
            dest.writeString(this.dse_nfd);
            dest.writeString(this.dse_nftime);
            dest.writeString(this.dse_comment);
            dest.writeString(this.assign_to_dse);
            dest.writeString(this.assign_to_dse_tl);
            dest.writeString(this.assign_to_cse);
            dest.writeString(this.assign_by_cse_tl);
            dest.writeString(this.cse_followup_id);
            dest.writeString(this.dse_followup_id);
            dest.writeString(this.lead_source);
            dest.writeString(this.eagerness);
            dest.writeString(this.enq_id);
            dest.writeString(this.name);
            dest.writeString(this.email);
            dest.writeString(this.contact_no);
            dest.writeString(this.enquiry_for);
            dest.writeString(this.created_date);
            dest.writeString(this.created_time);
            dest.writeString(this.buyer_type);
            dest.writeString(this.esc_level1);
            dest.writeString(this.esc_level1_remark);
            dest.writeString(this.esc_level2);
            dest.writeString(this.esc_level2_remark);
            dest.writeString(this.esc_level3);
            dest.writeString(this.esc_level3_remark);
            dest.writeString(this.address);
            dest.writeString(this.buy_status);
            dest.writeString(this.model_id);
            dest.writeString(this.variant_id);
            dest.writeString(this.old_make);
            dest.writeString(this.old_model);
            dest.writeString(this.ownership);
            dest.writeString(this.manf_year);
            dest.writeString(this.color);
            dest.writeString(this.km);
            dest.writeString(this.feedbackStatus);
            dest.writeString(this.nextAction);
            dest.writeString(this.days60_booking);
            dest.writeString(this.alternate_contact_no);
            dest.writeString(this.reg_no);
            dest.writeString(this.expected_price);
            dest.writeString(this.quotated_price);
            dest.writeString(this.complaint_id);
            dest.writeString(this.business_area);
            dest.writeString(this.service_center);
            dest.writeString(this.comment);
            dest.writeString(this.date);
            dest.writeString(this.nextfollowupdate);
            dest.writeString(this.nextfollowuptime);
            dest.writeString(this.cse_name);

        }

        protected Lead_Details(Parcel in) {
            this.cse_fname = in.readString();
            this.cse_lname = in.readString();
            this.dse_fname = in.readString();
            this.dse_lname = in.readString();
            this.csetl_fname = in.readString();
            this.csetl_lname = in.readString();
            this.dsetl_fname = in.readString();
            this.dsetl_lname = in.readString();
            this.cse_date = in.readString();
            this.cse_nfd = in.readString();
            this.cse_nftime = in.readString();
            this.cse_comment = in.readString();
            this.dse_date = in.readString();
            this.dse_nfd = in.readString();
            this.dse_nftime = in.readString();
            this.dse_comment = in.readString();
            this.assign_to_dse = in.readString();
            this.assign_to_dse_tl = in.readString();
            this.assign_to_cse = in.readString();
            this.assign_by_cse_tl = in.readString();
            this.cse_followup_id = in.readString();
            this.dse_followup_id = in.readString();
            this.lead_source = in.readString();
            this.eagerness = in.readString();
            this.enq_id = in.readString();
            this.name = in.readString();
            this.email = in.readString();
            this.contact_no = in.readString();
            this.enquiry_for = in.readString();
            this.created_date = in.readString();
            this.created_time = in.readString();
            this.buyer_type = in.readString();
            this.esc_level1 = in.readString();
            this.esc_level1_remark = in.readString();
            this.esc_level2 = in.readString();
            this.esc_level2_remark = in.readString();
            this.esc_level3 = in.readString();
            this.esc_level3_remark = in.readString();
            this.address = in.readString();
            this.buy_status = in.readString();
            this.model_id = in.readString();
            this.variant_id = in.readString();
            this.old_make = in.readString();
            this.old_model = in.readString();
            this.ownership = in.readString();
            this.manf_year = in.readString();
            this.color = in.readString();
            this.km = in.readString();
            this.feedbackStatus = in.readString();
            this.nextAction = in.readString();
            this.days60_booking = in.readString();
            this.alternate_contact_no = in.readString();
            this.quotated_price = in.readString();
            this.expected_price = in.readString();
            this.reg_no = in.readString();
            this.complaint_id = in.readString();
            this.business_area = in.readString();
            this.service_center = in.readString();
            this.comment = in.readString();
            this.date = in.readString();
            this.nextfollowupdate = in.readString();
            this.nextfollowuptime = in.readString();
            this.cse_name = in.readString();
        }

        public static final Parcelable.Creator<Lead_Details> CREATOR = new Parcelable.Creator<Lead_Details>() {
            @Override
            public Lead_Details createFromParcel(Parcel source) {
                return new Lead_Details(source);
            }

            @Override
            public Lead_Details[] newArray(int size) {
                return new Lead_Details[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.lead_details_count);
        dest.writeTypedList(this.lead_details);
    }

    public CallingTaskNewLeadBean() {
    }

    protected CallingTaskNewLeadBean(Parcel in) {
        this.lead_details_count = in.createTypedArrayList(Lead_Details_Count.CREATOR);
        this.lead_details = in.createTypedArrayList(Lead_Details.CREATOR);
    }

    public static final Parcelable.Creator<CallingTaskNewLeadBean> CREATOR = new Parcelable.Creator<CallingTaskNewLeadBean>() {
        @Override
        public CallingTaskNewLeadBean createFromParcel(Parcel source) {
            return new CallingTaskNewLeadBean(source);
        }

        @Override
        public CallingTaskNewLeadBean[] newArray(int size) {
            return new CallingTaskNewLeadBean[size];
        }
    };
}

