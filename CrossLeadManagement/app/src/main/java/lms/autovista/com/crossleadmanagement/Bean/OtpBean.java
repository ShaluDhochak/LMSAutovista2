package lms.autovista.com.crossleadmanagement.Bean;

import java.util.ArrayList;

public class OtpBean {

    public static class Otp{
        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        String otp;
    }

    public ArrayList<Otp> getOtp() {
        return otp;
    }

    public void setOtp(ArrayList<Otp> otp) {
        this.otp = otp;
    }

    public ArrayList<Otp> otp;
}
