package com.excell.lms.lmsautovista.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 5/25/2018.
*/

public class POCStockCountDetailListBean implements Parcelable {
    public ArrayList<Poc_Stock_List> getPoc_stock_list() {
        return poc_stock_list;
    }

    public void setPoc_stock_list(ArrayList<Poc_Stock_List> poc_stock_list) {
        this.poc_stock_list = poc_stock_list;
    }

    public ArrayList<Poc_Stock_List> poc_stock_list;

    public static class Poc_Stock_List implements Parcelable {
        String make_name,submodel,color,fuel_type,owner,mfg_year,odo_meter,mileage,insurance_type,insurance_expiry_date,category;
        String vehicle_status,stock_location,expt_selling_price,stock_ageing,total_landing_cost,created_date;

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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getFuel_type() {
            return fuel_type;
        }

        public void setFuel_type(String fuel_type) {
            this.fuel_type = fuel_type;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getMfg_year() {
            return mfg_year;
        }

        public void setMfg_year(String mfg_year) {
            this.mfg_year = mfg_year;
        }

        public String getOdo_meter() {
            return odo_meter;
        }

        public void setOdo_meter(String odo_meter) {
            this.odo_meter = odo_meter;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getInsurance_type() {
            return insurance_type;
        }

        public void setInsurance_type(String insurance_type) {
            this.insurance_type = insurance_type;
        }

        public String getInsurance_expiry_date() {
            return insurance_expiry_date;
        }

        public void setInsurance_expiry_date(String insurance_expiry_date) {
            this.insurance_expiry_date = insurance_expiry_date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getVehicle_status() {
            return vehicle_status;
        }

        public void setVehicle_status(String vehicle_status) {
            this.vehicle_status = vehicle_status;
        }

        public String getStock_location() {
            return stock_location;
        }

        public void setStock_location(String stock_location) {
            this.stock_location = stock_location;
        }

        public String getExpt_selling_price() {
            return expt_selling_price;
        }

        public void setExpt_selling_price(String expt_selling_price) {
            this.expt_selling_price = expt_selling_price;
        }

        public String getStock_ageing() {
            return stock_ageing;
        }

        public void setStock_ageing(String stock_ageing) {
            this.stock_ageing = stock_ageing;
        }

        public String getTotal_landing_cost() {
            return total_landing_cost;
        }

        public void setTotal_landing_cost(String total_landing_cost) {
            this.total_landing_cost = total_landing_cost;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.make_name);
            dest.writeString(this.submodel);
            dest.writeString(this.color);
            dest.writeString(this.fuel_type);
            dest.writeString(this.owner);
            dest.writeString(this.mfg_year);
            dest.writeString(this.odo_meter);
            dest.writeString(this.mileage);
            dest.writeString(this.insurance_type);
            dest.writeString(this.insurance_expiry_date);
            dest.writeString(this.category);
            dest.writeString(this.vehicle_status);
            dest.writeString(this.stock_location);
            dest.writeString(this.expt_selling_price);
            dest.writeString(this.stock_ageing);
            dest.writeString(this.total_landing_cost);
            dest.writeString(this.created_date);
        }

        public Poc_Stock_List() {
        }

        protected Poc_Stock_List(Parcel in) {
            this.make_name = in.readString();
            this.submodel = in.readString();
            this.color = in.readString();
            this.fuel_type = in.readString();
            this.owner = in.readString();
            this.mfg_year = in.readString();
            this.odo_meter = in.readString();
            this.mileage = in.readString();
            this.insurance_type = in.readString();
            this.insurance_expiry_date = in.readString();
            this.category = in.readString();
            this.vehicle_status = in.readString();
            this.stock_location = in.readString();
            this.expt_selling_price = in.readString();
            this.stock_ageing = in.readString();
            this.total_landing_cost = in.readString();
            this.created_date = in.readString();
        }

        public static final Parcelable.Creator<Poc_Stock_List> CREATOR = new Parcelable.Creator<Poc_Stock_List>() {
            @Override
            public Poc_Stock_List createFromParcel(Parcel source) {
                return new Poc_Stock_List(source);
            }

            @Override
            public Poc_Stock_List[] newArray(int size) {
                return new Poc_Stock_List[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.poc_stock_list);
    }

    public POCStockCountDetailListBean() {
    }

    protected POCStockCountDetailListBean(Parcel in) {
        this.poc_stock_list = in.createTypedArrayList(Poc_Stock_List.CREATOR);
    }

    public static final Parcelable.Creator<POCStockCountDetailListBean> CREATOR = new Parcelable.Creator<POCStockCountDetailListBean>() {
        @Override
        public POCStockCountDetailListBean createFromParcel(Parcel source) {
            return new POCStockCountDetailListBean(source);
        }

        @Override
        public POCStockCountDetailListBean[] newArray(int size) {
            return new POCStockCountDetailListBean[size];
        }
    };
}
