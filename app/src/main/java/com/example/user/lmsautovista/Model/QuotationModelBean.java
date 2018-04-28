package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/23/2018.
*/

import java.util.ArrayList;

public class QuotationModelBean {

    public ArrayList<Quotation_Nodel_name> getQuotation_model_name() {
        return quotation_model_name;
    }

    public void setQuotation_model_name(ArrayList<Quotation_Nodel_name> quotation_model_name) {
        this.quotation_model_name = quotation_model_name;
    }

    public ArrayList<Quotation_Nodel_name> quotation_model_name;

    public static class Quotation_Nodel_name{
        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
        String model;
    }

}
