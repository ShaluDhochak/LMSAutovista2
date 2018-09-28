package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/23/2018.
*/

import java.util.ArrayList;

public class QuotationModelBean {
    public ArrayList<Quotation_Model_Name> getQuotation_model_name() {
        return quotation_model_name;
    }

    public void setQuotation_model_name(ArrayList<Quotation_Model_Name> quotation_model_name) {
        this.quotation_model_name = quotation_model_name;
    }

    public ArrayList<Quotation_Model_Name> quotation_model_name;

    public static class Quotation_Model_Name{
        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        String model_id,model;
    }

}
