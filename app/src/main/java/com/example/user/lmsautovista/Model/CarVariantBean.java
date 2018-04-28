package com.example.user.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/23/2018.
*/

import java.util.ArrayList;

public class CarVariantBean {
    public ArrayList<Select_Car_Variant> select_car_variant;

    public ArrayList<Select_Car_Variant> getSelect_car_variant() {
        return select_car_variant;
    }

    public void setSelect_car_variant(ArrayList<Select_Car_Variant> select_car_variant) {
        this.select_car_variant = select_car_variant;
    }

    public static class Select_Car_Variant{
        String variant_id,variant_name;

        public String getVariant_id() {
            return variant_id;
        }

        public void setVariant_id(String variant_id) {
            this.variant_id = variant_id;
        }

        public String getVariant_name() {
            return variant_name;
        }

        public void setVariant_name(String variant_name) {
            this.variant_name = variant_name;
        }

    }

}

