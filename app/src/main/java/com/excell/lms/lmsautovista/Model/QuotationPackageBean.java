package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 6/25/2018.
 */

public class QuotationPackageBean {

    public ArrayList<Accessories_Package> getAccessories_package() {
        return accessories_package;
    }

    public void setAccessories_package(ArrayList<Accessories_Package> accessories_package) {
        this.accessories_package = accessories_package;
    }

    public ArrayList<Accessories_Package> accessories_package;


    public static class Accessories_Package{
        String package_name;

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

    }
}
