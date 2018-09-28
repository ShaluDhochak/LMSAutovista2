package com.excell.lms.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 2/21/2018.
*/

public class DashboardLeadDetailBean implements Parcelable {

    public ArrayList<Lead_Details_Count> lead_details_count;
    public ArrayList<Lead_Details> lead_details;

    public ArrayList<Lead_Details_Count> getLead_details_count() {
        return lead_details_count;
    }

    public void setLead_details_count(ArrayList<Lead_Details_Count> lead_details_count) {
        this.lead_details_count = lead_details_count;
    }

    public ArrayList<Lead_Details> getLead_details() {
        return lead_details;
    }

    public void setLead_details(ArrayList<Lead_Details> lead_details) {
        this.lead_details = lead_details;
    }

    public static class Lead_Details_Count implements Parcelable {
        String count_lead;

        public String getCount_lead() {
            return count_lead;
        }

        public void setCount_lead(String count_lead) {
            this.count_lead = count_lead;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.count_lead);
        }

        public Lead_Details_Count() {
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
        String cse_fname,cse_lname,csetl_fname,csetl_lname,cse_date,cse_nfd,cse_comment,assign_by_cse_tl,assign_to_cse;
        String cse_followup_id, lead_source, eagerness, enq_id, name, nextAction,feedbackStatus;
        String email, contact_no, enquiry_for, created_date, created_time;

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

        public String getCse_comment() {
            return cse_comment;
        }

        public void setCse_comment(String cse_comment) {
            this.cse_comment = cse_comment;
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

        public String getCse_followup_id() {
            return cse_followup_id;
        }

        public void setCse_followup_id(String cse_followup_id) {
            this.cse_followup_id = cse_followup_id;
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

        public String getNextAction() {
            return nextAction;
        }

        public void setNextAction(String nextAction) {
            this.nextAction = nextAction;
        }

        public String getFeedbackStatus() {
            return feedbackStatus;
        }

        public void setFeedbackStatus(String feedbackStatus) {
            this.feedbackStatus = feedbackStatus;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cse_fname);
            dest.writeString(this.cse_lname);
            dest.writeString(this.csetl_fname);
            dest.writeString(this.csetl_lname);
            dest.writeString(this.cse_date);
            dest.writeString(this.cse_nfd);
            dest.writeString(this.cse_comment);
            dest.writeString(this.assign_by_cse_tl);
            dest.writeString(this.assign_to_cse);
            dest.writeString(this.cse_followup_id);
            dest.writeString(this.lead_source);
            dest.writeString(this.eagerness);
            dest.writeString(this.enq_id);
            dest.writeString(this.name);
            dest.writeString(this.nextAction);
            dest.writeString(this.feedbackStatus);
            dest.writeString(this.email);
            dest.writeString(this.contact_no);
            dest.writeString(this.enquiry_for);
            dest.writeString(this.created_date);
            dest.writeString(this.created_time);
        }

        public Lead_Details() {
        }

        protected Lead_Details(Parcel in) {
            this.cse_fname = in.readString();
            this.cse_lname = in.readString();
            this.csetl_fname = in.readString();
            this.csetl_lname = in.readString();
            this.cse_date = in.readString();
            this.cse_nfd = in.readString();
            this.cse_comment = in.readString();
            this.assign_by_cse_tl = in.readString();
            this.assign_to_cse = in.readString();
            this.cse_followup_id = in.readString();
            this.lead_source = in.readString();
            this.eagerness = in.readString();
            this.enq_id = in.readString();
            this.name = in.readString();
            this.nextAction = in.readString();
            this.feedbackStatus = in.readString();
            this.email = in.readString();
            this.contact_no = in.readString();
            this.enquiry_for = in.readString();
            this.created_date = in.readString();
            this.created_time = in.readString();
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

    public DashboardLeadDetailBean() {
    }

    protected DashboardLeadDetailBean(Parcel in) {
        this.lead_details_count = in.createTypedArrayList(Lead_Details_Count.CREATOR);
        this.lead_details = in.createTypedArrayList(Lead_Details.CREATOR);
    }

    public static final Parcelable.Creator<DashboardLeadDetailBean> CREATOR = new Parcelable.Creator<DashboardLeadDetailBean>() {
        @Override
        public DashboardLeadDetailBean createFromParcel(Parcel source) {
            return new DashboardLeadDetailBean(source);
        }

        @Override
        public DashboardLeadDetailBean[] newArray(int size) {
            return new DashboardLeadDetailBean[size];
        }
    };
}

