package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/21/2018.
*/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class POCarStockBean implements Parcelable {
    public ArrayList<Poc_stock> poc_stock;

    public ArrayList<Poc_stock> getPoc_stock() {
        return poc_stock;
    }

    public void setPoc_stock(ArrayList<Poc_stock> poc_stock) {
        this.poc_stock = poc_stock;
    }

    public static class Poc_stock implements Parcelable {
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

        public Poc_stock() {
        }

        protected Poc_stock(Parcel in) {
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

        public static final Parcelable.Creator<Poc_stock> CREATOR = new Parcelable.Creator<Poc_stock>() {
            @Override
            public Poc_stock createFromParcel(Parcel source) {
                return new Poc_stock(source);
            }

            @Override
            public Poc_stock[] newArray(int size) {
                return new Poc_stock[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.poc_stock);
    }

    public POCarStockBean() {
    }

    protected POCarStockBean(Parcel in) {
        this.poc_stock = in.createTypedArrayList(Poc_stock.CREATOR);
    }

    public static final Parcelable.Creator<POCarStockBean> CREATOR = new Parcelable.Creator<POCarStockBean>() {
        @Override
        public POCarStockBean createFromParcel(Parcel source) {
            return new POCarStockBean(source);
        }

        @Override
        public POCarStockBean[] newArray(int size) {
            return new POCarStockBean[size];
        }
    };
}
