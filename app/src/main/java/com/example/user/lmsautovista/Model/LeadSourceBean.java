package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 3/31/2018.
*/

import java.util.ArrayList;

public class LeadSourceBean {

    public ArrayList<Select_Lead_Source> select_lead_source;
    public ArrayList<Select_Lead_Source> getSelect_lead_source() {
        return select_lead_source;
    }
    public void setSelect_lead_source(ArrayList<Select_Lead_Source> select_lead_source) {
        this.select_lead_source = select_lead_source;
    }

    public static class Select_Lead_Source{
        String id;
        String lead_source_name;
        String lead_source_value;
        String process_id;
        String leadsourceStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLead_source_name() {
            return lead_source_name;
        }

        public void setLead_source_name(String lead_source_name) {
            this.lead_source_name = lead_source_name;
        }

        public String getLead_source_value() {
            return lead_source_value;
        }

        public void setLead_source_value(String lead_source_value) {
            this.lead_source_value = lead_source_value;
        }

        public String getProcess_id() {
            return process_id;
        }

        public void setProcess_id(String process_id) {
            this.process_id = process_id;
        }

        public String getLeadsourceStatus() {
            return leadsourceStatus;
        }

        public void setLeadsourceStatus(String leadsourceStatus) {
            this.leadsourceStatus = leadsourceStatus;
        }

    }
}
