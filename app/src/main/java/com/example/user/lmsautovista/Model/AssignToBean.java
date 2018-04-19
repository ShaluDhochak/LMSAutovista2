package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/2/2018.
*/

import java.util.ArrayList;

public class AssignToBean {

    public ArrayList<Select_User> getSelect_user() {
        return select_user;
    }

    public void setSelect_user(ArrayList<Select_User> select_user) {
        this.select_user = select_user;
    }

    public ArrayList<Select_User> select_user;

    public static class Select_User{
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

        String id,fname,lname;
    }


}
