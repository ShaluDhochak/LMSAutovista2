package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 5/12/2018.
*/

import java.util.ArrayList;

public class CheckFlowTransferLeadDetailBean {

    public ArrayList<Lead_Data> getLead_data() {
        return lead_data;
    }

    public void setLead_data(ArrayList<Lead_Data> lead_data) {
        this.lead_data = lead_data;
    }

    public ArrayList<Lead_Data> lead_data;

    public static class Lead_Data{
        String fname, lname, u1name, u1lname, created_date;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getU1name() {
            return u1name;
        }

        public void setU1name(String u1name) {
            this.u1name = u1name;
        }

        public String getU1lname() {
            return u1lname;
        }

        public void setU1lname(String u1lname) {
            this.u1lname = u1lname;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

    }
}

