package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {

    public static boolean validateEpamEmail(String email) {
        
        if(email == null) {return false;}
        
        Pattern pattenr = Pattern.compile("^[a-zA-Z]+_[a-zA-Z]+[1-9]*[0-9]*@epam\\.com$");
        Matcher matcher = pattenr.matcher(email);

        boolean matched = matcher.matches();

        return (matched);
    }
}





