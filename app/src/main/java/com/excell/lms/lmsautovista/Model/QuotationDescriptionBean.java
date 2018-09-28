package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 4/23/2018.
 */

public class QuotationDescriptionBean {

    public ArrayList<Quoatation_Description> getQuotation_description() {
        return quotation_description;
    }

    public void setQuotation_description(ArrayList<Quoatation_Description> quotation_description) {
        this.quotation_description = quotation_description;
    }

    public ArrayList<Quoatation_Description> quotation_description;

    public static class Quoatation_Description{
        String variant;
        public String getVariant() {
            return variant;
        }

        public void setVariant(String variant) {
            this.variant = variant;
        }
    }

}

