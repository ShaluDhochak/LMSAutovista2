package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/2/2018.
*/

public class AddNewLeadBean {
    public int isSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    int success;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
