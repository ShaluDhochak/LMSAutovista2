package com.example.user.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 3/7/2018.
 */

public class SearchTrackerListBean implements Parcelable {

    public ArrayList<User_Details_Count> getUser_details_count() {
        return user_details_count;
    }

    public void setUser_details_count(ArrayList<User_Details_Count> user_details_count) {
        this.user_details_count = user_details_count;
    }

    public ArrayList<User_Details_Count> user_details_count;


    public static class User_Details_Count implements Parcelable {
        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        String count;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.count);
        }

        public User_Details_Count() {
        }

        protected User_Details_Count(Parcel in) {
            this.count = in.readString();
        }

        public static final Creator<User_Details_Count> CREATOR = new Creator<User_Details_Count>() {
            @Override
            public User_Details_Count createFromParcel(Parcel source) {
                return new User_Details_Count(source);
            }

            @Override
            public User_Details_Count[] newArray(int size) {
                return new User_Details_Count[size];
            }
        };
    }

    public ArrayList<User_Details> getUser_details() {
        return user_details;
    }

    public void setUser_details(ArrayList<User_Details> user_details) {
        this.user_details = user_details;
    }

    public ArrayList<User_Details> user_details;

    public static class User_Details implements Parcelable {
        String accessoires_list,assessories_price,model_name,service_center,service_type,pickup_required,pick_up_date;
        String cse_fname,cse_lname,km,nextfollowupdate,comment,executive_name,file_login_date,login_status_name,approved_date;
        String disburse_date,disburse_amount,process_fee,emi,enq_id,lead_source,enquiry_for,process,name,contact_no, address,email;
        String lead_date,feedbackStatus,nextAction,eagerness,bank_name,loan_type,reg_no,los_no,roi,tenure,loanamount,dealer;
        String min_date,max_date;

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

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getNextfollowupdate() {
            return nextfollowupdate;
        }

        public void setNextfollowupdate(String nextfollowupdate) {
            this.nextfollowupdate = nextfollowupdate;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getExecutive_name() {
            return executive_name;
        }

        public void setExecutive_name(String executive_name) {
            this.executive_name = executive_name;
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

        public String getReg_no() {
            return reg_no;
        }

        public void setReg_no(String reg_no) {
            this.reg_no = reg_no;
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
            dest.writeString(this.cse_fname);
            dest.writeString(this.cse_lname);
            dest.writeString(this.km);
            dest.writeString(this.nextfollowupdate);
            dest.writeString(this.comment);
            dest.writeString(this.executive_name);
            dest.writeString(this.file_login_date);
            dest.writeString(this.login_status_name);
            dest.writeString(this.approved_date);
            dest.writeString(this.disburse_date);
            dest.writeString(this.disburse_amount);
            dest.writeString(this.process_fee);
            dest.writeString(this.emi);
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
            dest.writeString(this.eagerness);
            dest.writeString(this.bank_name);
            dest.writeString(this.loan_type);
            dest.writeString(this.reg_no);
            dest.writeString(this.los_no);
            dest.writeString(this.roi);
            dest.writeString(this.tenure);
            dest.writeString(this.loanamount);
            dest.writeString(this.dealer);
            dest.writeString(this.min_date);
            dest.writeString(this.max_date);
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
            this.cse_fname = in.readString();
            this.cse_lname = in.readString();
            this.km = in.readString();
            this.nextfollowupdate = in.readString();
            this.comment = in.readString();
            this.executive_name = in.readString();
            this.file_login_date = in.readString();
            this.login_status_name = in.readString();
            this.approved_date = in.readString();
            this.disburse_date = in.readString();
            this.disburse_amount = in.readString();
            this.process_fee = in.readString();
            this.emi = in.readString();
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
            this.eagerness = in.readString();
            this.bank_name = in.readString();
            this.loan_type = in.readString();
            this.reg_no = in.readString();
            this.los_no = in.readString();
            this.roi = in.readString();
            this.tenure = in.readString();
            this.loanamount = in.readString();
            this.dealer = in.readString();
            this.min_date = in.readString();
            this.max_date = in.readString();
        }

        public static final Creator<User_Details> CREATOR = new Creator<User_Details>() {
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
        dest.writeList(this.user_details);
    }

    public SearchTrackerListBean() {
    }

    protected SearchTrackerListBean(Parcel in) {
        this.user_details_count = new ArrayList<User_Details_Count>();
        in.readList(this.user_details_count, User_Details_Count.class.getClassLoader());
        this.user_details = new ArrayList<User_Details>();
        in.readList(this.user_details, User_Details.class.getClassLoader());
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

/*

    "user_details_count": [
        {
            "": "4"
        }
    ],
    "user_details": [
        {
            "": "2017-11-04",
            "": "2017-11-04"
        },

 */