package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhcohak on 5/5/2018.
*/

import java.util.ArrayList;

public class FromUserAssignTransferBean {
    public ArrayList<Assign_transferred_Lead_from_User> assign_transferred_lead_from_user;

    public ArrayList<Assign_transferred_Lead_from_User> getAssign_transferred_lead_from_user() {
        return assign_transferred_lead_from_user;
    }

    public void setAssign_transferred_lead_from_user(ArrayList<Assign_transferred_Lead_from_User> assign_transferred_lead_from_user) {
        this.assign_transferred_lead_from_user = assign_transferred_lead_from_user;
    }

    public static class Assign_transferred_Lead_from_User{
        String id;
        String fname;
        String lname;

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

/*

    "": [
        {
            "": "125",
            "": "Akshay Ghorpade",
            "": "PUNE NEW CAR"
        },
 */
