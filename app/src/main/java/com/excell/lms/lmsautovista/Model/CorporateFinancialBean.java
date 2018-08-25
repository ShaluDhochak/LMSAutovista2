package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 6/12/2018.
*/

import java.util.ArrayList;

public class CorporateFinancialBean {

    public ArrayList<Customer_Corporate_Name> getCustomer_corporate_name() {
        return customer_corporate_name;
    }

    public void setCustomer_corporate_name(ArrayList<Customer_Corporate_Name> customer_corporate_name) {
        this.customer_corporate_name = customer_corporate_name;
    }

    public ArrayList<Customer_Corporate_Name> customer_corporate_name;

    public static class Customer_Corporate_Name{
        String corporate_id;
        String corporate_name;

        public String getCorporate_id() {
            return corporate_id;
        }

        public void setCorporate_id(String corporate_id) {
            this.corporate_id = corporate_id;
        }

        public String getCorporate_name() {
            return corporate_name;
        }

        public void setCorporate_name(String corporate_name) {
            this.corporate_name = corporate_name;
        }
    }
}

/*
"customer_corporate_name": [
        {
            "": "1",
            "": "LIFE CORPORATION OF INDIA (LIC)"
        },
 */