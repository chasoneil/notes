package com.chason.util;

public class CheckUtil {

    private static boolean data = true;

    public static boolean checkLine (String line) {

        if (StringUtil.isEmpty(line)) {
            return false;
        }

        if (line.trim().startsWith("//")) {
            return false;
        }

        if (line.trim().startsWith("/*")) {
            data = false;
        }

        if (line.trim().endsWith("*/")) {
            data = true;
        }

        return data;
    }

}
