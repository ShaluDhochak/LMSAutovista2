package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 5/2/2018.
*/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class DSEReportBean implements Parcelable {
    public ArrayList<Dsewise_Count> dsewise_count;

    public ArrayList<Dsewise_Count> getDsewise_count() {
        return dsewise_count;
    }

    public void setDsewise_count(ArrayList<Dsewise_Count> dsewise_count) {
        this.dsewise_count = dsewise_count;
    }

    public static class Dsewise_Count implements Parcelable {
        String fname,dse_id,lname,pending_new,pending_followup,home_visit_count,showroom_visit_count,evaluation_count;
        String test_drive_count,follow_up_count,undecided_count,not_interested_count,deal_count,already_booked_with_autovista_count;
        String lost_to_co_dealer_count,lost_to_competition_brand_count,color_model_availability_count,low_budget_count;
        String plan_cancelled_count,booked_count;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getDse_id() {
            return dse_id;
        }

        public void setDse_id(String dse_id) {
            this.dse_id = dse_id;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getPending_new() {
            return pending_new;
        }

        public void setPending_new(String pending_new) {
            this.pending_new = pending_new;
        }

        public String getPending_followup() {
            return pending_followup;
        }

        public void setPending_followup(String pending_followup) {
            this.pending_followup = pending_followup;
        }

        public String getHome_visit_count() {
            return home_visit_count;
        }

        public void setHome_visit_count(String home_visit_count) {
            this.home_visit_count = home_visit_count;
        }

        public String getShowroom_visit_count() {
            return showroom_visit_count;
        }

        public void setShowroom_visit_count(String showroom_visit_count) {
            this.showroom_visit_count = showroom_visit_count;
        }

        public String getEvaluation_count() {
            return evaluation_count;
        }

        public void setEvaluation_count(String evaluation_count) {
            this.evaluation_count = evaluation_count;
        }

        public String getTest_drive_count() {
            return test_drive_count;
        }

        public void setTest_drive_count(String test_drive_count) {
            this.test_drive_count = test_drive_count;
        }

        public String getFollow_up_count() {
            return follow_up_count;
        }

        public void setFollow_up_count(String follow_up_count) {
            this.follow_up_count = follow_up_count;
        }

        public String getUndecided_count() {
            return undecided_count;
        }

        public void setUndecided_count(String undecided_count) {
            this.undecided_count = undecided_count;
        }

        public String getNot_interested_count() {
            return not_interested_count;
        }

        public void setNot_interested_count(String not_interested_count) {
            this.not_interested_count = not_interested_count;
        }

        public String getDeal_count() {
            return deal_count;
        }

        public void setDeal_count(String deal_count) {
            this.deal_count = deal_count;
        }

        public String getAlready_booked_with_autovista_count() {
            return already_booked_with_autovista_count;
        }

        public void setAlready_booked_with_autovista_count(String already_booked_with_autovista_count) {
            this.already_booked_with_autovista_count = already_booked_with_autovista_count;
        }

        public String getLost_to_co_dealer_count() {
            return lost_to_co_dealer_count;
        }

        public void setLost_to_co_dealer_count(String lost_to_co_dealer_count) {
            this.lost_to_co_dealer_count = lost_to_co_dealer_count;
        }

        public String getLost_to_competition_brand_count() {
            return lost_to_competition_brand_count;
        }

        public void setLost_to_competition_brand_count(String lost_to_competition_brand_count) {
            this.lost_to_competition_brand_count = lost_to_competition_brand_count;
        }

        public String getColor_model_availability_count() {
            return color_model_availability_count;
        }

        public void setColor_model_availability_count(String color_model_availability_count) {
            this.color_model_availability_count = color_model_availability_count;
        }

        public String getLow_budget_count() {
            return low_budget_count;
        }

        public void setLow_budget_count(String low_budget_count) {
            this.low_budget_count = low_budget_count;
        }

        public String getPlan_cancelled_count() {
            return plan_cancelled_count;
        }

        public void setPlan_cancelled_count(String plan_cancelled_count) {
            this.plan_cancelled_count = plan_cancelled_count;
        }

        public String getBooked_count() {
            return booked_count;
        }

        public void setBooked_count(String booked_count) {
            this.booked_count = booked_count;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.fname);
            dest.writeString(this.dse_id);
            dest.writeString(this.lname);
            dest.writeString(this.pending_new);
            dest.writeString(this.pending_followup);
            dest.writeString(this.home_visit_count);
            dest.writeString(this.showroom_visit_count);
            dest.writeString(this.evaluation_count);
            dest.writeString(this.test_drive_count);
            dest.writeString(this.follow_up_count);
            dest.writeString(this.undecided_count);
            dest.writeString(this.not_interested_count);
            dest.writeString(this.deal_count);
            dest.writeString(this.already_booked_with_autovista_count);
            dest.writeString(this.lost_to_co_dealer_count);
            dest.writeString(this.lost_to_competition_brand_count);
            dest.writeString(this.color_model_availability_count);
            dest.writeString(this.low_budget_count);
            dest.writeString(this.plan_cancelled_count);
            dest.writeString(this.booked_count);
        }

        public Dsewise_Count() {
        }

        protected Dsewise_Count(Parcel in) {
            this.fname = in.readString();
            this.dse_id = in.readString();
            this.lname = in.readString();
            this.pending_new = in.readString();
            this.pending_followup = in.readString();
            this.home_visit_count = in.readString();
            this.showroom_visit_count = in.readString();
            this.evaluation_count = in.readString();
            this.test_drive_count = in.readString();
            this.follow_up_count = in.readString();
            this.undecided_count = in.readString();
            this.not_interested_count = in.readString();
            this.deal_count = in.readString();
            this.already_booked_with_autovista_count = in.readString();
            this.lost_to_co_dealer_count = in.readString();
            this.lost_to_competition_brand_count = in.readString();
            this.color_model_availability_count = in.readString();
            this.low_budget_count = in.readString();
            this.plan_cancelled_count = in.readString();
            this.booked_count = in.readString();
        }

        public static final Parcelable.Creator<Dsewise_Count> CREATOR = new Parcelable.Creator<Dsewise_Count>() {
            @Override
            public Dsewise_Count createFromParcel(Parcel source) {
                return new Dsewise_Count(source);
            }

            @Override
            public Dsewise_Count[] newArray(int size) {
                return new Dsewise_Count[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.dsewise_count);
    }

    public DSEReportBean() {
    }

    protected DSEReportBean(Parcel in) {
        this.dsewise_count = in.createTypedArrayList(Dsewise_Count.CREATOR);
    }

    public static final Parcelable.Creator<DSEReportBean> CREATOR = new Parcelable.Creator<DSEReportBean>() {
        @Override
        public DSEReportBean createFromParcel(Parcel source) {
            return new DSEReportBean(source);
        }

        @Override
        public DSEReportBean[] newArray(int size) {
            return new DSEReportBean[size];
        }
    };
}


