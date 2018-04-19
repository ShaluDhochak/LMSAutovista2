package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 4/13/2018.
 */

public class LocationDashboardBean {
    public ArrayList<Select_Location> select_location;

    public ArrayList<Select_Location> getSelect_location() {
        return select_location;
    }

    public void setSelect_location(ArrayList<Select_Location> select_location) {
        this.select_location = select_location;
    }

    public static class Select_Location{
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
