package com.example.firebaseapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public  static String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        public  static String mobileRegex="^[6-9]\\d{9}$";
        public static boolean isValidName(String name)
        {
            if(name!=null && !name.isBlank() && name.length()>1)
            {
                return true;
            }else {
                return false;
            }
        }
        public static boolean isValidEmail(String email){
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher=pattern.matcher(email);
            return matcher.matches();
        }
        public static boolean isValidPassword(String password) {
            if (password != null && !password.isBlank() && password.length() > 7) {
                return true;
            } else {
                return false;
            }
        }
    }