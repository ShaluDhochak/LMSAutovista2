package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by User on 5/26/2018.
 */

public class NewCarStockLocationModel {

    public ArrayList<New_Stock_Location> getNew_stock_location() {
        return new_stock_location;
    }

    public void setNew_stock_location(ArrayList<New_Stock_Location> new_stock_location) {
        this.new_stock_location = new_stock_location;
    }

    public ArrayList<New_Stock_Location> new_stock_location;

    public static class New_Stock_Location{
        public String getAssigned_location() {
            return assigned_location;
        }

        public void setAssigned_location(String assigned_location) {
            this.assigned_location = assigned_location;
        }

        String assigned_location;
    }

    public ArrayList<New_Stock_Model> getNew_stock_model() {
        return new_stock_model;
    }

    public void setNew_stock_model(ArrayList<New_Stock_Model> new_stock_model) {
        this.new_stock_model = new_stock_model;
    }

    public ArrayList<New_Stock_Model> new_stock_model;

    public static class New_Stock_Model{
        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        String model;
    }
}
