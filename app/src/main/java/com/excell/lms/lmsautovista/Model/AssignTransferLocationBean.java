package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 5/5/2018.
*/

import java.util.ArrayList;

public class AssignTransferLocationBean {

    public ArrayList<From_Location> getFrom_location() {
        return from_location;
    }

    public void setFrom_location(ArrayList<From_Location> from_location) {
        this.from_location = from_location;
    }

    public ArrayList<To_Location> getTo_location() {
        return to_location;
    }

    public void setTo_location(ArrayList<To_Location> to_location) {
        this.to_location = to_location;
    }

    public ArrayList<From_Location> from_location;
    public ArrayList<To_Location> to_location;

    public static class From_Location{
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

        String location_id,location;
    }

    public static class To_Location{
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

