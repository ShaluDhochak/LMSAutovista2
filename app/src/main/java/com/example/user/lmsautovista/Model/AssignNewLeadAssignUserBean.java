package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by User on 5/4/2018.
 */

public class AssignNewLeadAssignUserBean {
    public ArrayList<Assign_New_Lead_Assign_User> assign_new_lead_assign_user;

    public ArrayList<Assign_New_Lead_Assign_User> getAssign_new_lead_assign_user() {
        return assign_new_lead_assign_user;
    }

    public void setAssign_new_lead_assign_user(ArrayList<Assign_New_Lead_Assign_User> assign_new_lead_assign_user) {
        this.assign_new_lead_assign_user = assign_new_lead_assign_user;
    }

    public static class Assign_New_Lead_Assign_User{
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
