package com.example.user.lmsautovista.Model;

/*
  Created by User on 4/21/2018.
*/

import java.util.ArrayList;

public class MakeBean {

    public ArrayList<Select_Car_Make> getSelect_car_make() {
        return select_car_make;
    }

    public void setSelect_car_make(ArrayList<Select_Car_Make> select_car_make) {
        this.select_car_make = select_car_make;
    }

    public ArrayList<Select_Car_Make> select_car_make;

    public static class Select_Car_Make{
        String make_id;
        String make_name;

        public String getMake_id() {
            return make_id;
        }
        public void setMake_id(String make_id) {
            this.make_id = make_id;
        }
        public String getMake_name() {
            return make_name;
        }
        public void setMake_name(String make_name) {
            this.make_name = make_name;
        }

    }
}
