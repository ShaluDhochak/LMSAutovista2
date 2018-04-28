package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/20/2018.
*/

import java.util.ArrayList;

public class NewStockFilterBean {

    public ArrayList<New_Stock_Color> getNew_stock_color() {
        return new_stock_color;
    }

    public void setNew_stock_color(ArrayList<New_Stock_Color> new_stock_color) {
        this.new_stock_color = new_stock_color;
    }

    public ArrayList<New_Stock_Location> getNew_stock_location() {
        return new_stock_location;
    }

    public void setNew_stock_location(ArrayList<New_Stock_Location> new_stock_location) {
        this.new_stock_location = new_stock_location;
    }

    public ArrayList<New_stock_Fuel_Type> getNew_stock_fuel_type() {
        return new_stock_fuel_type;
    }

    public void setNew_stock_fuel_type(ArrayList<New_stock_Fuel_Type> new_stock_fuel_type) {
        this.new_stock_fuel_type = new_stock_fuel_type;
    }

    public ArrayList<New_Stock_Color> new_stock_color;
    public ArrayList<New_Stock_Location> new_stock_location;
    public ArrayList<New_stock_Fuel_Type> new_stock_fuel_type;

    public static class New_Stock_Location{
        public String getAssigned_location() {
            return assigned_location;
        }

        public void setAssigned_location(String assigned_location) {
            this.assigned_location = assigned_location;
        }
        String assigned_location;
    }

    public static class New_Stock_Color{
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
        String color;
    }

    public static class New_stock_Fuel_Type{
        public String getFuel_type() {
            return fuel_type;
        }

        public void setFuel_type(String fuel_type) {
            this.fuel_type = fuel_type;
        }
        String fuel_type;

    }


}
