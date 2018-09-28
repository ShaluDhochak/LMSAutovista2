package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/30/2018.
*/

import java.util.ArrayList;

public class ViewMessageListBean {
    public ArrayList<Message_Home> getMessage_home() {
        return message_home;
    }

    public void setMessage_home(ArrayList<Message_Home> message_home) {
        this.message_home = message_home;
    }

    public ArrayList<Message_Home> message_home;

    public static class Message_Home{
        String message_id, message,user_id, message_status, created_date, message_location_id, location_id, tl,dse, fname, lname;

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMessage_status() {
            return message_status;
        }

        public void setMessage_status(String message_status) {
            this.message_status = message_status;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getMessage_location_id() {
            return message_location_id;
        }

        public void setMessage_location_id(String message_location_id) {
            this.message_location_id = message_location_id;
        }

        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public String getTl() {
            return tl;
        }

        public void setTl(String tl) {
            this.tl = tl;
        }

        public String getDse() {
            return dse;
        }

        public void setDse(String dse) {
            this.dse = dse;
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
