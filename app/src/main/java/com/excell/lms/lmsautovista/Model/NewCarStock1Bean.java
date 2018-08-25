package com.excell.lms.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/*
  Created by User on 5/26/2018.
*/

public class NewCarStock1Bean implements Parcelable {

    public ArrayList<New_stock_Count> getNew_stock_count() {
        return new_stock_count;
    }

    public void setNew_stock_count(ArrayList<New_stock_Count> new_stock_count) {
        this.new_stock_count = new_stock_count;
    }

    public ArrayList<New_stock_Count> new_stock_count;

    public static class New_stock_Count implements Parcelable {

        String model_count,submodel,model_name,assigned_location;
        public String getModel_count() {
            return model_count;
        }

        public void setModel_count(String model_count) {
            this.model_count = model_count;
        }

        public String getSubmodel() {
            return submodel;
        }

        public void setSubmodel(String submodel) {
            this.submodel = submodel;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

        public String getAssigned_location() {
            return assigned_location;
        }

        public void setAssigned_location(String assigned_location) {
            this.assigned_location = assigned_location;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.model_count);
            dest.writeString(this.submodel);
            dest.writeString(this.model_name);
            dest.writeString(this.assigned_location);
        }

        public New_stock_Count() {
        }

        protected New_stock_Count(Parcel in) {
            this.model_count = in.readString();
            this.submodel = in.readString();
            this.model_name = in.readString();
            this.assigned_location = in.readString();
        }

        public static final Parcelable.Creator<New_stock_Count> CREATOR = new Parcelable.Creator<New_stock_Count>() {
            @Override
            public New_stock_Count createFromParcel(Parcel source) {
                return new New_stock_Count(source);
            }

            @Override
            public New_stock_Count[] newArray(int size) {
                return new New_stock_Count[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.new_stock_count);
    }

    public NewCarStock1Bean() {
    }

    protected NewCarStock1Bean(Parcel in) {
        this.new_stock_count = in.createTypedArrayList(New_stock_Count.CREATOR);
    }

    public static final Parcelable.Creator<NewCarStock1Bean> CREATOR = new Parcelable.Creator<NewCarStock1Bean>() {
        @Override
        public NewCarStock1Bean createFromParcel(Parcel source) {
            return new NewCarStock1Bean(source);
        }

        @Override
        public NewCarStock1Bean[] newArray(int size) {
            return new NewCarStock1Bean[size];
        }
    };
}

