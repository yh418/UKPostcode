package org.example.util;

public class StringUtil {
    public static String nullToEmpty(Object object) {
        return object == null ? "" : String.valueOf(object);
    }
}
