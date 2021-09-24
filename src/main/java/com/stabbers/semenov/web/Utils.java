package com.stabbers.semenov.web;

import static org.springframework.util.StringUtils.hasText;

public class Utils {

    public static String getTokenFromHeader(String bearer) {
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
