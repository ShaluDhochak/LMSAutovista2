package com.example.user.lmsautovista.Utils;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
  Created by User on 2/17/2018.
*/

public class StringUtils {
    public static void internetError(Context context)
    {
        Toast.makeText(context,"Check Internet connectivity",Toast.LENGTH_SHORT).show();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
