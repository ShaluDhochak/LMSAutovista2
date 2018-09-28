package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/23/2018.
*/

import java.util.ArrayList;

public class TransferLocationBean {
    public ArrayList<Select_Transfer_Locatin> select_transfer_location;
    public ArrayList<Select_Transfer_Locatin> getSelect_transfer_location() {
        return select_transfer_location;
    }

    public void setSelect_transfer_location(ArrayList<Select_Transfer_Locatin> select_transfer_location) {
        this.select_transfer_location = select_transfer_location;
    }

    public static class Select_Transfer_Locatin{

        String location_id,location;

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

