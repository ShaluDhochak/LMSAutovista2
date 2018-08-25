package com.excell.lms.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by User on 5/25/2018.
 */

public class POCCarStockCountBean implements Parcelable {

    public ArrayList<Poc_Stock_Count> getPoc_stock_count() {
        return poc_stock_count;
    }

    public void setPoc_stock_count(ArrayList<Poc_Stock_Count> poc_stock_count) {
        this.poc_stock_count = poc_stock_count;
    }

    public ArrayList<Poc_Stock_Count> poc_stock_count;

    public static class Poc_Stock_Count implements Parcelable {
        String make_name;
        String submodel;
        String model_count;

        public String getMake_name() {
            return make_name;
        }

        public void setMake_name(String make_name) {
            this.make_name = make_name;
        }

        public String getSubmodel() {
            return submodel;
        }

        public void setSubmodel(String submodel) {
            this.submodel = submodel;
        }

        public String getModel_count() {
            return model_count;
        }

        public void setModel_count(String model_count) {
            this.model_count = model_count;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.make_name);
            dest.writeString(this.submodel);
            dest.writeString(this.model_count);
        }

        public Poc_Stock_Count() {
        }

        protected Poc_Stock_Count(Parcel in) {
            this.make_name = in.readString();
            this.submodel = in.readString();
            this.model_count = in.readString();
        }

        public static final Parcelable.Creator<Poc_Stock_Count> CREATOR = new Parcelable.Creator<Poc_Stock_Count>() {
            @Override
            public Poc_Stock_Count createFromParcel(Parcel source) {
                return new Poc_Stock_Count(source);
            }

            @Override
            public Poc_Stock_Count[] newArray(int size) {
                return new Poc_Stock_Count[size];
            }
        };
    }

    public ArrayList<Poc_Stock_Filter> getPoc_stock_filter() {
        return poc_stock_filter;
    }

    public void setPoc_stock_filter(ArrayList<Poc_Stock_Filter> poc_stock_filter) {
        this.poc_stock_filter = poc_stock_filter;
    }

    public ArrayList<Poc_Stock_Filter> poc_stock_filter;

    public static class Poc_Stock_Filter implements Parcelable {
        String make;

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getStock_location() {
            return stock_location;
        }

        public void setStock_location(String stock_location) {
            this.stock_location = stock_location;
        }

        public String getMfg_year() {
            return mfg_year;
        }

        public void setMfg_year(String mfg_year) {
            this.mfg_year = mfg_year;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getAgeing() {
            return ageing;
        }

        public void setAgeing(String ageing) {
            this.ageing = ageing;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        String model;
        String stock_location;
        String mfg_year;
        String owner;
        String ageing;
        String price;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.make);
            dest.writeString(this.model);
            dest.writeString(this.stock_location);
            dest.writeString(this.mfg_year);
            dest.writeString(this.owner);
            dest.writeString(this.ageing);
            dest.writeString(this.price);
        }

        public Poc_Stock_Filter() {
        }

        protected Poc_Stock_Filter(Parcel in) {
            this.make = in.readString();
            this.model = in.readString();
            this.stock_location = in.readString();
            this.mfg_year = in.readString();
            this.owner = in.readString();
            this.ageing = in.readString();
            this.price = in.readString();
        }

        public static final Creator<Poc_Stock_Filter> CREATOR = new Creator<Poc_Stock_Filter>() {
            @Override
            public Poc_Stock_Filter createFromParcel(Parcel source) {
                return new Poc_Stock_Filter(source);
            }

            @Override
            public Poc_Stock_Filter[] newArray(int size) {
                return new Poc_Stock_Filter[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.poc_stock_count);
        dest.writeTypedList(this.poc_stock_filter);
    }

    public POCCarStockCountBean() {
    }

    protected POCCarStockCountBean(Parcel in) {
        this.poc_stock_count = in.createTypedArrayList(Poc_Stock_Count.CREATOR);
        this.poc_stock_filter = in.createTypedArrayList(Poc_Stock_Filter.CREATOR);
    }

    public static final Parcelable.Creator<POCCarStockCountBean> CREATOR = new Parcelable.Creator<POCCarStockCountBean>() {
        @Override
        public POCCarStockCountBean createFromParcel(Parcel source) {
            return new POCCarStockCountBean(source);
        }

        @Override
        public POCCarStockCountBean[] newArray(int size) {
            return new POCCarStockCountBean[size];
        }
    };
}
