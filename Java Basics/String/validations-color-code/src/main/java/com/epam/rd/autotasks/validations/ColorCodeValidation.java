package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
public static boolean validateColorCode(String color) {

        if (color == null) { return false;}

        Pattern pattern = Pattern.compile("^#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})$");
        Matcher matcher = pattern.matcher(color);

        return matcher.matches();
    }
}





