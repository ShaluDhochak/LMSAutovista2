package com.excell.lms.lmsautovista.Model;

/*
 Created by Shalu Dhochak on 5/23/2018.
 */

import java.util.ArrayList;

public class PocStockCountBean {

    public ArrayList<Poc_Stock_Count> getPoc_stock_count() {
        return poc_stock_count;
    }

    public void setPoc_stock_count(ArrayList<Poc_Stock_Count> poc_stock_count) {
        this.poc_stock_count = poc_stock_count;
    }

    public ArrayList<Poc_Stock_Count> poc_stock_count;


    public static class Poc_Stock_Count{
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

        public String getMfg_year_1() {
            return mfg_year_1;
        }

        public void setMfg_year_1(String mfg_year_1) {
            this.mfg_year_1 = mfg_year_1;
        }

        public String getMfg_year_2() {
            return mfg_year_2;
        }

        public void setMfg_year_2(String mfg_year_2) {
            this.mfg_year_2 = mfg_year_2;
        }

        public String getMfg_year_3() {
            return mfg_year_3;
        }

        public void setMfg_year_3(String mfg_year_3) {
            this.mfg_year_3 = mfg_year_3;
        }

        public String getMfg_year_4() {
            return mfg_year_4;
        }

        public void setMfg_year_4(String mfg_year_4) {
            this.mfg_year_4 = mfg_year_4;
        }

        public String getOwner_1() {
            return owner_1;
        }

        public void setOwner_1(String owner_1) {
            this.owner_1 = owner_1;
        }

        public String getOwner_2() {
            return owner_2;
        }

        public void setOwner_2(String owner_2) {
            this.owner_2 = owner_2;
        }

        public String getOwner_3() {
            return owner_3;
        }

        public void setOwner_3(String owner_3) {
            this.owner_3 = owner_3;
        }

        public String getAgeing_1() {
            return ageing_1;
        }

        public void setAgeing_1(String ageing_1) {
            this.ageing_1 = ageing_1;
        }

        public String getAgeing_2() {
            return ageing_2;
        }

        public void setAgeing_2(String ageing_2) {
            this.ageing_2 = ageing_2;
        }

        public String getAgeing_3() {
            return ageing_3;
        }

        public void setAgeing_3(String ageing_3) {
            this.ageing_3 = ageing_3;
        }

        public String getAgeing_4() {
            return ageing_4;
        }

        public void setAgeing_4(String ageing_4) {
            this.ageing_4 = ageing_4;
        }

        public String getPrice_1() {
            return price_1;
        }

        public void setPrice_1(String price_1) {
            this.price_1 = price_1;
        }

        public String getPrice_2() {
            return price_2;
        }

        public void setPrice_2(String price_2) {
            this.price_2 = price_2;
        }

        public String getPrice_3() {
            return price_3;
        }

        public void setPrice_3(String price_3) {
            this.price_3 = price_3;
        }

        String mfg_year_1;
        String mfg_year_2;
        String mfg_year_3;
        String mfg_year_4;
        String owner_1;
        String owner_2;
        String owner_3;
        String ageing_1;
        String ageing_2;
        String ageing_3,ageing_4,price_1,price_2,price_3;

    }
}

/*

    "": [
        {

        },
 */
