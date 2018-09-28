package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 4/23/2018.
*/

public class QuotationLocationBean {

    public ArrayList<Quotation_Location> getQuotation_location() {
        return quotation_location;
    }

    public void setQuotation_location(ArrayList<Quotation_Location> quotation_location) {
        this.quotation_location = quotation_location;
    }

    public ArrayList<Quotation_Location> quotation_location;

    public static class Quotation_Location{

        String location;
        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

    }
}

