package com.chason.util;

public class StringUtil {

    public static boolean isEmpty (String string) {

        if (string == null || string.equals("") || string.equals("\n")) {
            return true;
        }

        return false;
    }

}
