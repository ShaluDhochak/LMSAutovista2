package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/21/2018.
 */

import java.util.ArrayList;

public class POCSpinnerBean {
    public ArrayList<Poc_Stock_Color> poc_stock_color;

    public ArrayList<Poc_Stock_Fuel_Type> poc_stock_fuel_type;

    public ArrayList<Poc_Stock_Location> poc_stock_location;

    public ArrayList<Poc_Stock_Color> getPoc_stock_color() {
        return poc_stock_color;
    }

    public void setPoc_stock_color(ArrayList<Poc_Stock_Color> poc_stock_color) {
        this.poc_stock_color = poc_stock_color;
    }

    public ArrayList<Poc_Stock_Fuel_Type> getPoc_stock_fuel_type() {
        return poc_stock_fuel_type;
    }

    public void setPoc_stock_fuel_type(ArrayList<Poc_Stock_Fuel_Type> poc_stock_fuel_type) {
        this.poc_stock_fuel_type = poc_stock_fuel_type;
    }

    public ArrayList<Poc_Stock_Location> getPoc_stock_location() {
        return poc_stock_location;
    }

    public void setPoc_stock_location(ArrayList<Poc_Stock_Location> poc_stock_location) {
        this.poc_stock_location = poc_stock_location;
    }

    public static class Poc_Stock_Location{
        String stock_location;
        public String getStock_location() {
            return stock_location;
        }

        public void setStock_location(String stock_location) {
            this.stock_location = stock_location;
        }

    }

    public static class Poc_Stock_Color{
        String color;
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public static class Poc_Stock_Fuel_Type{
        String fuel_type;
        public String getFuel_type() {
            return fuel_type;
        }

        public void setFuel_type(String fuel_type) {
            this.fuel_type = fuel_type;
        }

    }
}

