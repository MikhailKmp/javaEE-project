package com.kamenev.util;

public final class ParseUtil {

    public static Long parseLong(String string) {
        if (string != null && !string.isEmpty()) {
            try {
                return Long.parseLong(string);
            }
            catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

}
