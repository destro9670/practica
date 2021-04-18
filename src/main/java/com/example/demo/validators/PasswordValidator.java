package com.example.demo.validators;

import java.util.regex.Pattern;

public class PasswordValidator {

    private final static Pattern hasUppercase = Pattern.compile("[A-Z]");
    private final static Pattern hasLowercase = Pattern.compile("[a-z]");
    private final static Pattern hasNumber = Pattern.compile("\\d");

    public static int validate(String pass1, String pass2) {
        if (pass1 == null || pass2 == null) {
            ///("Passwords = null");
            return 1; // field is empty
        }


        if (pass1.isEmpty() || pass2.isEmpty()) {
            return 1;
        }

        if (pass1.equals(pass2)) {
            ///(pass1 + " = " + pass2);

            if (pass1.length() < 8) {
                ///(pass1 + " is length < 11");
                return 2; // no rules
            }

            if (!hasUppercase.matcher(pass1).find()) {
                ///(pass1 + " <-- needs uppercase");
                return 2; // no rules
            }

            if (!hasLowercase.matcher(pass1).find()) {
                ///(pass1 + " <-- needs lowercase");
                return 2; // no rules
            }

            if (!hasNumber.matcher(pass1).find()) {
                ///(pass1 + "<-- needs a number");
                return 2; // no rules
            }


        } else {
            ///(pass1 + " != " + pass2);
            return 3; // no much
        }

        return  0;
    }

}
