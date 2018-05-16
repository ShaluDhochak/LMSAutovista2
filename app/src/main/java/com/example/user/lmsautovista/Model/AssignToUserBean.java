package com.example.user.lmsautovista.Model;

/*
  Created by User on 5/5/2018.
*/

import java.util.ArrayList;

public class AssignToUserBean {

    public ArrayList<Assign_Transferred_Lead_to_user> getAssign_transferred_lead_to_user() {
        return assign_transferred_lead_to_user;
    }

    public void setAssign_transferred_lead_to_user(ArrayList<Assign_Transferred_Lead_to_user> assign_transferred_lead_to_user) {
        this.assign_transferred_lead_to_user = assign_transferred_lead_to_user;
    }

    public ArrayList<Assign_Transferred_Lead_to_user> assign_transferred_lead_to_user;

    public static class Assign_Transferred_Lead_to_user{
        String id,fname,lname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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
    }
}
