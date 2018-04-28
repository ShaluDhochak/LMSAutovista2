package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/21/2018.
*/

import java.util.ArrayList;

public class POCStockModel {
    public ArrayList<Poc_Stock_Color> poc_stock_color;

    public ArrayList<Poc_Stock_Color> getPoc_stock_color() {
        return poc_stock_color;
    }

    public void setPoc_stock_color(ArrayList<Poc_Stock_Color> poc_stock_color) {
        this.poc_stock_color = poc_stock_color;
    }

    public static class Poc_Stock_Color{
        String model;
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
    }
}
