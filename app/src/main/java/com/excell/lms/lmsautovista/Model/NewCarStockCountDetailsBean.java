package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by User on 5/26/2018.
 */

public class NewCarStockCountDetailsBean {

    public ArrayList<New_Stock_List> getNew_stock_list() {
        return new_stock_list;
    }

    public void setNew_stock_list(ArrayList<New_Stock_List> new_stock_list) {
        this.new_stock_list = new_stock_list;
    }

    public ArrayList<New_Stock_List> new_stock_list;

public static class New_Stock_List{
    String model_name;
    String submodel;
    String color;
    String fuel_type;

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
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

    String vehicle_status;
    String assigned_location;
    String ageing;
    String created_date;
}
}



/*
": [
        {
            "": "Alto 800",
            "": "ALTO 800 MC GREEN LXI",
            "": "BLAZING RED",
            "": "CNG",
            "": "FREE",
            "assigned_location": "Kharghar",
            "": "91",
            "": "2018-04-18"
        },
 */