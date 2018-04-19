package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/*
  Created by Shalu Dhochak on 2/20/2018.
*/

public class LoginBean {

    public int isSuccess() {
        return success;
    }
    public void setSuccess(int success) {
        this.success = success;
    }

    int success;

    public ArrayList<User_Details> user_detail;

    public static class User_Details{
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

        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        String role,id,fname,lname,process_id,process_name,location_id,location;

    }

    public ArrayList<Session_data> session_data;

    public static class Session_data{
        String user_id;
        String username;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public ArrayList<Process> getProcess() {
            return process;
        }

        public void setProcess(ArrayList<Process> process) {
            this.process = process;
        }

        String role;
        public ArrayList<Process> process;

        public static class Process{
            String process_id,process_name;

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

            public ArrayList<Location> getLocation() {
                return location;
            }

            public void setLocation(ArrayList<Location> location) {
                this.location = location;
            }

            public ArrayList<Location> location;

            public static class Location{
                String location_id;
                String location;

                public String getLocation_id() {
                    return location_id;
                }

                public void setLocation_id(String location_id) {
                    this.location_id = location_id;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

            }
        }
    }

    public ArrayList<User_Details> getUser_detail() {
        return user_detail;
    }

    public void setUser_detail(ArrayList<User_Details> user_detail) {
        this.user_detail = user_detail;
    }

    public ArrayList<Session_data> getSession_data() {
        return session_data;
    }

    public void setSession_data(ArrayList<Session_data> session_data) {
        this.session_data = session_data;
    }

    public ArrayList<Rights> getRights() {
        return rights;
    }

    public void setRights(ArrayList<Rights> rights) {
        this.rights = rights;
    }

    public ArrayList<Rights> rights;

    public static class Rights{

        String right_id,user_id,form_name,controller_name,view,insert,modify,delete,process_id;
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

        public String getProcess_id() {
            return process_id;
        }

        public void setProcess_id(String process_id) {
            this.process_id = process_id;
        }

    }

}


/*
 "success": 1,
    "user_detail": [
        {

        }
    ],
    "session_data": [
        {
            "": "1",
            "": "Admin Autovista",
            "": "1",
            "process": [
                {
                    "": "6",
                    "": "New Car",
                    "location": [
                        {
                            "location_id": "1",
                            "location": "Pune New Car"
                        },

 */

/*
  "rights": [
        {

        },

 */