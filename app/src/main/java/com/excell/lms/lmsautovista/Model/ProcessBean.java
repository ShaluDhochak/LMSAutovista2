package com.excell.lms.lmsautovista.Model;

/*
 Created by Shalu Dhochak on 4/23/2018.
*/

import java.util.ArrayList;

public class ProcessBean {
    public ArrayList<All_Process> all_process;

    public ArrayList<All_Process> getAll_process() {
        return all_process;
    }

    public void setAll_process(ArrayList<All_Process> all_process) {
        this.all_process = all_process;
    }

    public static class All_Process{
        String process_id;
        String process_name;

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
    }

}

