package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by User on 2/20/2018.
 */

public class LoginBean {
    int success;
    public ArrayList<User_Detail> user_detail;
    public ArrayList<Session_Data> session_data;
    public ArrayList<Rights> rights;

    public int isSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        success = success;
    }

    public ArrayList<User_Detail> getUser_detail() {
        return user_detail;
    }

    public void setUser_detail(ArrayList<User_Detail> user_detail) {
        this.user_detail = user_detail;
    }

    public ArrayList<Session_Data> getSession_data() {
        return session_data;
    }

    public void setSession_data(ArrayList<Session_Data> session_data) {
        this.session_data = session_data;
    }

    public ArrayList<Rights> getRights() {
        return rights;
    }

    public void setRights(ArrayList<Rights> rights) {
        this.rights = rights;
    }

    public static class User_Detail{
        String role;
        String id;
        String fname;
        String lname;
        String location;
        String process_id;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getProcess_id() {
            return process_id;
        }

        public void setProcess_id(String process_id) {
            this.process_id = process_id;
        }

        public String getProcess_name() {
            return process_name;
        }

        public void setProcess_name(String process_name) {
            this.process_name = process_name;
        }

        String process_name;
    }

    public static class Session_Data{
        String user_id;
        String process_id;
        String username;
        String role;
        String process_name;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getProcess_id() {
            return process_id;
        }

        public void setProcess_id(String process_id) {
            this.process_id = process_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getProcess_name() {
            return process_name;
        }

        public void setProcess_name(String process_name) {
            this.process_name = process_name;
        }


    }

    public static class Rights{
        String right_id,user_id,form_name,controller_name,view,insert,modify,delete;

        public String getRight_id() {
            return right_id;
        }

        public void setRight_id(String right_id) {
            this.right_id = right_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getForm_name() {
            return form_name;
        }

        public void setForm_name(String form_name) {
            this.form_name = form_name;
        }

        public String getController_name() {
            return controller_name;
        }

        public void setController_name(String controller_name) {
            this.controller_name = controller_name;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        public String getInsert() {
            return insert;
        }

        public void setInsert(String insert) {
            this.insert = insert;
        }

        public String getModify() {
            return modify;
        }

        public void setModify(String modify) {
            this.modify = modify;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

    }

}

/*
 "success": 1,
    "user_detail": [

    ],
    "session_data": [
        {
        }
    ],
    "rights": [
        {
        },
 */