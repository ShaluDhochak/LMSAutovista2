package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/20/2018.
*/

import java.util.ArrayList;

public class NewCarStockBean {

    public ArrayList<New_Car_Stock> new_car_stock;

    public ArrayList<New_Car_Stock> getNew_car_stock() {
        return new_car_stock;
    }

    public void setNew_car_stock(ArrayList<New_Car_Stock> new_car_stock) {
        this.new_car_stock = new_car_stock;
    }

    public static class New_Car_Stock{

        String submodel,color,fuel_type,vehicle_status,assigned_location,ageing,created_date,model_name;

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

        public String getVehicle_status() {
            return vehicle_status;
        }

        public void setVehicle_status(String vehicle_status) {
            this.vehicle_status = vehicle_status;
        }

        public String getAssigned_location() {
            return assigned_location;
        }

        public void setAssigned_location(String assigned_location) {
            this.assigned_location = assigned_location;
        }

        public String getAgeing() {
            return ageing;
        }

        public void setAgeing(String ageing) {
            this.ageing = ageing;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

    }

}
