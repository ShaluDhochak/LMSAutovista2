package com.excell.lms.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 2/19/2018.
*/

public class DashboardCountBean implements Parcelable {
    public ArrayList<Dashboard_Count> dashboard_count;

    public ArrayList<Dashboard_Count> getDashboard_count() {
        return dashboard_count;
    }

    public void setDashboard_count(ArrayList<Dashboard_Count> dashboard_count) {
        this.dashboard_count = dashboard_count;
    }

    public static class Dashboard_Count implements Parcelable {

        String id,role,location_name,fname,lname,unassigned_leads, new_leads;
        String call_today,pending_new_leads,pending_followup;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

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

        public String getUnassigned_leads() {
            return unassigned_leads;
        }

        public void setUnassigned_leads(String unassigned_leads) {
            this.unassigned_leads = unassigned_leads;
        }

        public String getNew_leads() {
            return new_leads;
        }

        public void setNew_leads(String new_leads) {
            this.new_leads = new_leads;
        }

        public String getCall_today() {
            return call_today;
        }

        public void setCall_today(String call_today) {
            this.call_today = call_today;
        }

        public String getPending_new_leads() {
            return pending_new_leads;
        }

        public void setPending_new_leads(String pending_new_leads) {
            this.pending_new_leads = pending_new_leads;
        }

        public String getPending_followup() {
            return pending_followup;
        }

        public void setPending_followup(String pending_followup) {
            this.pending_followup = pending_followup;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.role);
            dest.writeString(this.location_name);
            dest.writeString(this.fname);
            dest.writeString(this.lname);
            dest.writeString(this.unassigned_leads);
            dest.writeString(this.new_leads);
            dest.writeString(this.call_today);
            dest.writeString(this.pending_new_leads);
            dest.writeString(this.pending_followup);
        }

        public Dashboard_Count() {
        }

        protected Dashboard_Count(Parcel in) {
            this.id = in.readString();
            this.role = in.readString();
            this.location_name = in.readString();
            this.fname = in.readString();
            this.lname = in.readString();
            this.unassigned_leads = in.readString();
            this.new_leads = in.readString();
            this.call_today = in.readString();
            this.pending_new_leads = in.readString();
            this.pending_followup = in.readString();
        }

        public static final Parcelable.Creator<Dashboard_Count> CREATOR = new Parcelable.Creator<Dashboard_Count>() {
            @Override
            public Dashboard_Count createFromParcel(Parcel source) {
                return new Dashboard_Count(source);
            }

            @Override
            public Dashboard_Count[] newArray(int size) {
                return new Dashboard_Count[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.dashboard_count);
    }

    public DashboardCountBean() {
    }

    protected DashboardCountBean(Parcel in) {
        this.dashboard_count = in.createTypedArrayList(Dashboard_Count.CREATOR);
    }

    public static final Parcelable.Creator<DashboardCountBean> CREATOR = new Parcelable.Creator<DashboardCountBean>() {
        @Override
        public DashboardCountBean createFromParcel(Parcel source) {
            return new DashboardCountBean(source);
        }

        @Override
        public DashboardCountBean[] newArray(int size) {
            return new DashboardCountBean[size];
        }
    };
}
