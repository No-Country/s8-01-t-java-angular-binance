package com.s8.binance.security.util;

public class Operations {
    public static String trimBrackets(String message){
        return message.replaceAll("[\\[\\]]", "");
    }
}
