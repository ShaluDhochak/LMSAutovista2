package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by User on 4/21/2018.
 */

public class ModelBean {

    public ArrayList<Select_Car_Model> getSelect_car_model() {
        return select_car_model;
    }

    public void setSelect_car_model(ArrayList<Select_Car_Model> select_car_model) {
        this.select_car_model = select_car_model;
    }

    public ArrayList<Select_Car_Model> select_car_model;

    public static class Select_Car_Model{
        String make_id,model_id,model_name;
        public String getMake_id() {
            return make_id;
        }

        public void setMake_id(String make_id) {
            this.make_id = make_id;
        }

        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

    }
}
