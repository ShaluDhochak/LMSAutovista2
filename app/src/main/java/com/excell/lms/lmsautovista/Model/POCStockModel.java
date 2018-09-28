package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/21/2018.
*/

import java.util.ArrayList;

public class POCStockModel {
    public ArrayList<Poc_Stock_Model> poc_stock_model;

    public ArrayList<Poc_Stock_Model> getPoc_stock_color() {
        return poc_stock_model;
    }

    public void setPoc_stock_model(ArrayList<Poc_Stock_Model> poc_stock_model) {
        this.poc_stock_model = poc_stock_model;
    }

    public static class Poc_Stock_Model{
        String model;
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
    }
}
