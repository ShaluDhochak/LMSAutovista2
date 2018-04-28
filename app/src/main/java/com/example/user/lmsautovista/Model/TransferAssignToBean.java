package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/24/2018.
*/

import java.util.ArrayList;

public class TransferAssignToBean {
    public ArrayList<Select_Transfer_to_User> select_transfer_to_user;

    public ArrayList<Select_Transfer_to_User> getSelect_transfer_to_user() {
        return select_transfer_to_user;
    }

    public void setSelect_transfer_to_user(ArrayList<Select_Transfer_to_User> select_transfer_to_user) {
        this.select_transfer_to_user = select_transfer_to_user;
    }

    public static class Select_Transfer_to_User{
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

