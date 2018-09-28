package lms.autovista.com.crossleadmanagement.Bean;

import java.util.ArrayList;

public class LocationBean {

    public ArrayList<Location> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Location> location) {
        this.location = location;
    }

    public ArrayList<Location> location;

    public static class Location{
        String location_id;

        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        String location_name;

    }
}
